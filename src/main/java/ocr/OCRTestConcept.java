package ocr;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Scanner;

public class OCRTestConcept {
    public static void main(String[] args) {

        ITesseract instance = new Tesseract();
        instance.setDatapath("./tessdata");// Set the tessdata folder path here
        //instance.setLanguage("sin");
        // Set the language to 'eng' and 'sin' for English and Sinhala respectively
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select the language(Enter 1 or 2) :\n\t1)English\n\t2)Sinhala\nYour Option :");
        while(true){
            try{
                int choice = scanner.nextInt();
                if(choice == 1){
                    instance.setLanguage("eng");
                    break;
                }else if(choice == 2){
                    instance.setLanguage("sin");
                    break;
                }else{
                    System.out.println("Invalid input. Please enter 1 or 2");
                }

            }
            catch(Exception e){
                System.out.println("Invalid input. Please enter 1 or 2");
            }

        }
        scanner.nextLine();
        String path = "";
        while(true){
            System.out.println("Enter the path of the image :");
            path = scanner.next();
            if(new File(path).exists() || path.equals("1")){
                break;
            }else{
                System.out.println("Invalid path. Please enter a valid path.press(1) to exit.");
            }
        }

        try {
            BufferedImage image = ImageIO.read(new File(path)); // Replace with actual path
            String result = instance.doOCR(image);
            System.out.println(result);
        } catch (TesseractException | IOException e) {
            System.err.println(e.getMessage());
        }

    }
}
