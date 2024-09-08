package ocr;

import javafx.application.Application;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import net.sourceforge.tess4j.TesseractException;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.io.IOException;

public class ReadImagesGUI extends Application {

    private String selectedLanguage = "eng";  // Default language

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("OCR Tool - GUI");

        // Language selection ComboBox
        Label languageLabel = new Label("Select OCR Language:");
        ComboBox<String> languageBox = new ComboBox<>();
        languageBox.getItems().addAll("English (eng)", "Sinhala (sin)");
        languageBox.setValue("English (eng)");  // Default selection

        // Handle language selection
        languageBox.setOnAction(e -> {
            if (languageBox.getValue().equals("English (eng)")) {
                selectedLanguage = "eng";
            } else if (languageBox.getValue().equals("Sinhala (sin)")) {
                selectedLanguage = "sin";
            }
        });

        // Button to open file chooser
        Button browseButton = new Button("Browse for an Image or PDF");
        Label resultLabel = new Label();

        // ImageView to display selected image
        ImageView imageView = new ImageView();
        imageView.setFitWidth(300);
        imageView.setPreserveRatio(true);

        // File chooser to select image or PDF files
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image or PDF Files", "*.png", "*.jpg", "*.jpeg", "*.pdf"));

        // Handle file browsing
        browseButton.setOnAction(event -> {
            File selectedFile = fileChooser.showOpenDialog(primaryStage);
            if (selectedFile != null) {
                try {
                    OCRProcessor ocrProcessor = new OCRProcessor(selectedLanguage);

                    if (selectedFile.getName().endsWith(".pdf")) {
                        // Process PDF using the method from OCRProcessor
                        resultLabel.setText("Processing PDF...");
                        String extractedText = ocrProcessor.extractTextFromPDF(selectedFile);
                        resultLabel.setText("Extracted Text:\n" + extractedText);
                        copyTextToClipboard(extractedText);

                    } else {
                        // Display image
                        Image image = new Image(selectedFile.toURI().toString());
                        imageView.setImage(image);

                        // Process image using the method from OCRProcessor
                        resultLabel.setText("Processing image...");
                        String extractedText = ocrProcessor.extractTextFromImage(selectedFile);
                        resultLabel.setText("Extracted Text:\n" + extractedText);
                        copyTextToClipboard(extractedText);
                    }

                } catch (IOException | TesseractException e1) {
                    resultLabel.setText("Error: " + e1.getMessage());
                }
            }
        });

        // Main layout
        VBox root = new VBox(10);
        root.getChildren().addAll(languageLabel, languageBox, browseButton, imageView, resultLabel);

        Scene scene = new Scene(root, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Method to copy text to clipboard
    private void copyTextToClipboard(String text) {
        StringSelection selection = new StringSelection(text);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, null);
        System.out.println("Text copied to clipboard!");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
