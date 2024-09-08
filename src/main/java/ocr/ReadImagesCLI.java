package ocr;

import net.sourceforge.tess4j.TesseractException;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.File;

public class ReadImagesCLI {
    public static void main(String[] args) {
        if(args.length<2){
            System.out.println("Usage: java -jar ReadImagesCLI.jar <image_path> <language>");
            System.out.println("Example: java -jar ReadImagesCLI.jar ./src/images/testocr.png eng");
            return;
        }
        String imagePath = args[0];
        String language = args[1];

        OCRProcessor ocrProcessor = new OCRProcessor(language);
        try {
            File imageFile = new File(imagePath);
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
