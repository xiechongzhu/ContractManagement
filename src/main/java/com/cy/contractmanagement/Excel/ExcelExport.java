package com.cy.contractmanagement.Excel;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ExcelExport {
    private List<List<String>> title = new ArrayList<>();
    private List<List<Object>> data = new ArrayList<>();
    private List<CellRangeAddress> merge = new ArrayList<>();

    public void setTitle(List<List<String>> title) {
        this.title = title;
    }

    public void setData(List<List<Object>> data) {
        this.data = data;
    }

    public void addTitle(List<String> title) {
        this.title.add(title);
    }

    public void clearTitle() {
        this.title.clear();
    }

    public void addData(List<Object> data) {
        this.data.add(data);
    }

    public void setMerge(List<CellRangeAddress> merge) {
        this.merge = merge;
    }

    public void exportToXls(String filename) throws Exception {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("sheet");

        //标题
        for (int i = 0; i < title.size(); ++i) {
            List<String> inner = title.get(i);
            HSSFRow row = sheet.createRow(i);
            for (int j = 0; j < inner.size(); ++j) {
                HSSFCell cell = row.createCell(j);
                cell.setCellValue(String.valueOf(inner.get(j)));
            }
        }

        //内容
        for (int i = 0; i < data.size(); ++i) {
            List<Object> inner = data.get(i);
            HSSFRow row = sheet.createRow(i + title.size());
            for (int j = 0; j < inner.size(); ++j) {
                HSSFCell cell = row.createCell(j);
                if (inner.get(j) instanceof Date) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    String strDate = sdf.format(inner.get(j));
                    cell.setCellValue(strDate);
                } else {
                    cell.setCellValue(String.valueOf(inner.get(j)));
                }
            }
        }

        //合并单元格
        for (CellRangeAddress addr : merge) {
            sheet.addMergedRegion(addr);
        }

        FileOutputStream stream = new FileOutputStream(filename);
        wb.write(stream);
        stream.close();
        wb.close();
    }
}
