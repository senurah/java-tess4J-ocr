package ocr.demo;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ReadImages {
    public static void main(String[] args) {
        ITesseract instance = new Tesseract();
        instance.setDatapath("./tessdata"); // Set the tessdata folder path here
        instance.setLanguage("eng"); // Set the language to 'eng'

        try {
            BufferedImage image = ImageIO.read(new File("src/images/testocr.png")); // Replace with actual path
            String result = instance.doOCR(image);
            System.out.println(result);
        } catch (TesseractException | IOException e) {
            System.err.println(e.getMessage());
        }

    }
}
