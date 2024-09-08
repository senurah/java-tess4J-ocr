package ocr;

import net.sourceforge.tess4j.TesseractException;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.io.IOException;

public class ReadImagesCLI {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java -jar ReadImagesCLI.jar <image_or_pdf_path> <language>");
            System.out.println("Example: java -jar ReadImagesCLI.jar C:/path/to/image_or_pdf.png eng");
            return;
        }

        String filePath = args[0];
        String language = args[1];

        //Load file
        File file = new File(filePath);
        if (!file.exists()) {
            System.err.println("Error: The file does not exist at the path: " + filePath);
            return;
        }

        try {
            String result;
            OCRProcessor ocrProcessor = new OCRProcessor(language);
            //check file type
            if (filePath.endsWith(".pdf")) {
                // Process PDF
                result = ocrProcessor.extractTextFromPDF(file);
            } else {
                // Process image
                result = ocrProcessor.extractTextFromImage(file);
            }

            // Extracted text
            System.out.println("Extracted Text:\n" + result);

            // Copy the extracted text to the clipboard
            StringSelection selection = new StringSelection(result);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(selection, null);

            System.out.println("Text copied to clipboard!");

        } catch (IOException | TesseractException e) {
            System.err.println("Error during OCR: " + e.getMessage());
        }
    }
}



