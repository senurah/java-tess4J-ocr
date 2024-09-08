package ocr;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class OCRProcessor {
    private String language;
    private Tesseract tesseract;

    public OCRProcessor(String language) {
        this.language = language;
        this.tesseract = new Tesseract();

        // Set the correct datapath to your tessdata folder
        this.tesseract.setDatapath("C:/Users/Projects/java-ocr-tess4j/tessdata");

        // Set the language for Tesseract
        this.tesseract.setLanguage(this.language);
    }

    // Method to extract text from image
    public String extractTextFromImage(File imageFile) throws TesseractException {
        return tesseract.doOCR(imageFile);
    }

    // Method to process PDFs using PDFBox and Tess4J
    public String extractTextFromPDF(File pdfFile) throws IOException, TesseractException {
        PDDocument document = PDDocument.load(pdfFile);
        PDFRenderer pdfRenderer = new PDFRenderer(document);

        StringBuilder resultText = new StringBuilder();

        for (int page = 0; page < document.getNumberOfPages(); ++page) {
            // Render PDF page to BufferedImage
            BufferedImage image = pdfRenderer.renderImageWithDPI(page, 300);  // 300 DPI is recommended for OCR

            // Save the image for debugging purposes (optional)
            File tempImageFile = new File("temp_page_" + (page + 1) + ".png");
            ImageIO.write(image, "png", tempImageFile);

            // Perform OCR on the rendered image
            String pageText = extractTextFromImage(tempImageFile);
            resultText.append(pageText).append("\n");
        }

        document.close();
        return resultText.toString();
    }
}

