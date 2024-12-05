package org.example;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Generate an Excel file with some data.
 */
public class App {
    public static void main( String[] args ) {
        System.out.println( "Generating Xlsx file!" );
        generateExcel();
    }

    public static void generateExcel() {
        List<String[]> data = new ArrayList<>();
        data.add(new String[]{"Email", "Display name"});

        for (int i = 0; i < 1000; i++) {
            data.add(new String[]{"person." + i + "@domain.com", "Person " + i});
        }
        String filePath = "output.xlsx";

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Sheet1");

        for (int i = 0; i < data.size(); i++) {
            Row row = sheet.createRow(i);
            String[] rowData = data.get(i);
            for (int j = 0; j < rowData.length; j++) {
                Cell cell = row.createCell(j);
                cell.setCellValue(rowData[j]);
            }
        }

        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Excel file generated at: " + filePath);
    }
}
