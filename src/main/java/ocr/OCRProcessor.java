package ocr;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class OCRProcessor {
    //Default language
    private String language = "eng";

    public OCRProcessor(String language) {
        this.language = language;
    }

    public String extractTextFromImage2(File file) throws IOException, TesseractException {
        ITesseract instance = new Tesseract();
        instance.setDatapath("C:\\Users\\Senura\\IdeaProjects\\java-ocr-tess4j\\tessdata");
        instance.setLanguage(language);
        BufferedImage image = ImageIO.read(file);
        return instance.doOCR(image);
    }

    public String extractTextFromImage(File file) throws TesseractException {
        ITesseract instance = new Tesseract();
        instance.setDatapath("C:\\Users\\Senura\\IdeaProjects\\java-ocr-tess4j\\tessdata");
        instance.setLanguage(language);

        return instance.doOCR(file);
    }

}
