//package ocr;
//
//import net.sourceforge.tess4j.TesseractException;
//
//import java.awt.*;
//import java.awt.datatransfer.Clipboard;
//import java.awt.datatransfer.StringSelection;
//import java.io.File;
//
//public class ReadImagesCLI {
//    public static void main(String[] args) {
//        if(args.length<2){
//            System.out.println("Usage: java -jar ReadImagesCLI.jar <image_path> <language>");
//            System.out.println("Example: java -jar ReadImagesCLI.jar ./src/images/testocr.png eng");
//            return;
//        }
//        String imagePath = args[0];
//        String language = args[1];
//
//        OCRProcessor ocrProcessor = new OCRProcessor(language);
//        try {
//            File imageFile = new File(imagePath);
//            String result = ocrProcessor.extractTextFromImage(imageFile);
//
//            // Print the extracted text
//            System.out.println("Extracted Text:\n" + result);
//
//            // Copy the extracted text to the clipboard
//            StringSelection selection = new StringSelection(result);
//            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
//            clipboard.setContents(selection, null);
//
//            System.out.println("Text copied to clipboard!");
//
//        } catch (TesseractException e) {
//            System.err.println("Error during OCR: " + e.getMessage());
//        }
//    }
//
//}
package ocr;

import net.sourceforge.tess4j.TesseractException;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ReadImagesCLI {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java -jar ReadImagesCLI.jar <image_path> <language>");
            System.out.println("Example: java -jar ReadImagesCLI.jar C:/path/to/image/testocr.png eng");
            return;
        }

        // Get arguments: image path and language
        String imagePath = args[0];
        String language = args[1];

        // Load the image file
        File imageFile = new File(imagePath);

        // Check if the image file exists
        if (!imageFile.exists()) {
            System.err.println("Error: The image file does not exist at the path: " + imagePath);
            return;
        }

        // Check if the image is readable
        try {
            BufferedImage img = ImageIO.read(imageFile);
            if (img == null) {
                System.err.println("Error: The image could not be read. It might be corrupt or in an unsupported format.");
                return;
            }
            System.out.println("Image loaded successfully. Dimensions: " + img.getWidth() + "x" + img.getHeight());
        } catch (IOException e) {
            System.err.println("Error loading the image: " + e.getMessage());
            return;
        }

        // Initialize the OCRProcessor
        OCRProcessor ocrProcessor = new OCRProcessor(language);

        try {
            // Perform OCR on the image file
            String result = ocrProcessor.extractTextFromImage(imageFile);

            // Print the extracted text
            System.out.println("Extracted Text:\n" + result);

            // Copy the extracted text to the clipboard
            StringSelection selection = new StringSelection(result);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(selection, null);

            System.out.println("Text copied to clipboard!");

        } catch (TesseractException e) {
            System.err.println("Error during OCR: " + e.getMessage());
        }
    }
}

