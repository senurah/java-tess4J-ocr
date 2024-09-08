package ocr;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.FileChooser;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser.ExtensionFilter;

import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.io.File;
import net.sourceforge.tess4j.TesseractException;

public class ReadImagesGUI extends Application {

    private String selectedLanguage = "eng";  // Default language

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("OCR Tool");

        // Label to show instructions
        Label label = new Label("Select a language, then drag and drop an image or browse for a file:");

        // Language selection ComboBox
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
        Button browseButton = new Button("Browse for an Image");
        Label resultLabel = new Label();

        // ImageView to display selected image
        ImageView imageView = new ImageView();
        imageView.setFitWidth(300);
        imageView.setPreserveRatio(true);

        // File chooser to select image files
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));

        // Handle file browsing
        browseButton.setOnAction(event -> {
            File selectedFile = fileChooser.showOpenDialog(primaryStage);
            if (selectedFile != null) {
                try {
                    Image image = new Image(selectedFile.toURI().toString());
                    imageView.setImage(image);
                    performOCR(selectedFile, resultLabel);  // Call the OCR logic
                } catch (Exception e1) {
                    resultLabel.setText("Failed to load the image.");
                }
            }
        });

        // Main layout
        VBox root = new VBox(10);
        root.getChildren().addAll(label, languageBox, browseButton, imageView, resultLabel);

        Scene scene = new Scene(root, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Method to perform OCR and copy text to clipboard
    private void performOCR(File file, Label resultLabel) {
        OCRProcessor ocrProcessor = new OCRProcessor(selectedLanguage);  // Reuse OCR logic from shared class

        try {
            String result = ocrProcessor.extractTextFromImage(file);
            resultLabel.setText("Extracted Text:\n" + result);

            // Copy to clipboard
            StringSelection selection = new StringSelection(result);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(selection, null);
            resultLabel.setText("Text extracted and copied to clipboard!");

        } catch (TesseractException e) {
            resultLabel.setText("Error during OCR: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);  // Start JavaFX Application
    }
}
