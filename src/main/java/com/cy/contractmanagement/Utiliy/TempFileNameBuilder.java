package com.cy.contractmanagement.Utiliy;

import java.util.UUID;

public class TempFileNameBuilder {
    private static final String XLS_EXTENSION = ".xls";

    public static String getTempXlsFileName() {
        String tempDir = System.getProperty("java.io.tmpdir");
        UUID uuid = UUID.randomUUID();
        return tempDir + "/" + uuid.toString() + XLS_EXTENSION;
    }
}
