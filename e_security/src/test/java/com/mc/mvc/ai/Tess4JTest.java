package com.mc.mvc.ai;

import java.io.File;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

@SpringBootTest
public class Tess4JTest {

	@Test
	public void testOCR() {
        File imageFile = new File("C:\\Program Files\\CODE\\tessdata\\poem.jpg");
        ITesseract instance = new Tesseract();  // JNA Interface Mapping
        // ITesseract instance = new Tesseract1(); // JNA Direct Mapping
        instance.setDatapath("C:\\Program Files\\CODE\\tessdata"); // path to tessdata directory
        instance.setLanguage("kor");
        try {
            String result = instance.doOCR(imageFile);
            System.out.println(result);
        } catch (TesseractException e) {
            System.err.println(e.getMessage());
        }
	}
	
	
}
