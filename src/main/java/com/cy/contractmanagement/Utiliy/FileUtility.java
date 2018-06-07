package com.cy.contractmanagement.Utiliy;

import java.io.File;
import java.util.UUID;

public class FileUtility {
    public static String getUuidString() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replace("-", "");
    }

    public static String getFileExtension(String fileName) {
        File f = new File(fileName);
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    public static String getTempFileName(String fileExt) {
        String tempDir = System.getProperty("java.io.tmpdir");
        return tempDir + "/" + getUuidString() + fileExt;
    }

    public static String makeProjectAlertDirectory() throws Exception {
        File f = new File("");
        File dir = new File(f.getAbsolutePath(), "ProjectAlertFile");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dir.getAbsolutePath();
    }

    public static String getWordTemplateDirectory() {
        File f = new File("");
        File dir = new File(f.getAbsolutePath(), "wordTemplate");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dir.getAbsolutePath();
    }
}
