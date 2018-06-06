package com.cy.contractmanagement.Utiliy;

import com.aspose.words.Document;
import com.aspose.words.License;
import com.aspose.words.SaveFormat;
import java.io.File;
import java.io.FileOutputStream;

public class FileConvert {
    public static void wordToPdf(String wordFilePath, String pdfFilePath) throws Exception {
        FileOutputStream fileOutputStream = new FileOutputStream(new File(pdfFilePath));
        Document doc = new Document(wordFilePath);
        doc.save(fileOutputStream, SaveFormat.PDF);
        fileOutputStream.close();
    }
}
