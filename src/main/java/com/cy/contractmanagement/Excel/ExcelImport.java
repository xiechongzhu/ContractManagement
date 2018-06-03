package com.cy.contractmanagement.Excel;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExcelImport {
    public List<ArrayList<String>> parseExcelData(String fileName, int startRow, int colCount) throws Exception {
        ArrayList<ArrayList<String>> resultList = new ArrayList<>();
        FileInputStream inputStream = new FileInputStream(fileName);
        try {
            HSSFWorkbook wb = new HSSFWorkbook(inputStream);
            HSSFSheet sheet = wb.getSheet("sheet");
            if (sheet != null) {
                for (int rowNum = startRow; rowNum <= sheet.getLastRowNum(); ++rowNum) {
                    HSSFRow hssfRow = sheet.getRow(rowNum);
                    if (hssfRow != null) {
                        ArrayList<String> rowData = new ArrayList<>();
                        for (int columnNum = 0; columnNum < colCount; columnNum++) {
                            HSSFCell cell = hssfRow.getCell(columnNum);
                            //rowData.add(cell == null ? "" : cell.getcellval);
                            if (cell == null) {
                                rowData.add("");
                            } else {
                                switch (cell.getCellTypeEnum()) {
                                    case NUMERIC:
                                        rowData.add(String.valueOf(cell.getNumericCellValue()));
                                        break;
                                    case STRING:
                                        rowData.add(cell.getStringCellValue());
                                        break;
                                    case FORMULA:
                                        rowData.add(cell.getCellFormula());
                                        break;
                                    case BOOLEAN:
                                        rowData.add(String.valueOf(cell.getBooleanCellValue()));
                                        break;
                                    default:
                                        rowData.add("");
                                        break;
                                }
                            }
                        }
                        resultList.add(rowData);
                    }
                }
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            inputStream.close();
        }
        return resultList;
    }
}
