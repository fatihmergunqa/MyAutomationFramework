package com.gmail.fatihmergunqa.utils;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * ExcelReader class to facilitate reading data from Excel files.
 * It supports loading data from a specified sheet into various formats,
 * such as a list of maps or a 2D array.
 */
public class ExcelReader {
    private static Workbook book; // Represents the Excel workbook
    private static Sheet sheet;   // Represents the currently loaded sheet

    /**
     * Opens the Excel file at the specified file path.
     *
     * @param filePath the path of the Excel file to open
     */
    private static void openExcel(String filePath) {
        try {
            FileInputStream fis = new FileInputStream(filePath);
            book = new XSSFWorkbook(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads the specified sheet from the opened workbook.
     *
     * @param sheetName the name of the sheet to load
     */
    private static void loadSheet(String sheetName) {
        sheet = book.getSheet(sheetName);
    }

    /**
     * Gets the number of physical rows in the currently loaded sheet.
     *
     * @return the number of physical rows
     */
    private static int getRowCount() {
        return sheet.getPhysicalNumberOfRows();
    }

    /**
     * Gets the number of columns in a specified row.
     *
     * @param rowIndex the index of the row
     * @return the number of columns in the row
     */
    private static int getColumnCount(int rowIndex) {
        return sheet.getRow(rowIndex).getLastCellNum();
    }

    /**
     * Retrieves the data from a specific cell in the currently loaded sheet.
     *
     * @param rowCount    the index of the row
     * @param columnCount the index of the column
     * @return the data from the specified cell as a String
     */
    private static String getCellData(int rowCount, int columnCount) {
        return sheet.getRow(rowCount).getCell(columnCount).toString();
    }

    /**
     * Reads data from the specified Excel file and sheet into a list of maps.
     * Each map represents a row, with keys as column headers and values as cell data.
     *
     * @param filePath  the path of the Excel file
     * @param sheetName the name of the sheet to read from
     * @return a List of Maps containing the data from the sheet
     */
    public static List<Map<String, String>> excelToListOfMaps(String filePath, String sheetName) {
        // Open the Excel file located at the specified filePath
        openExcel(filePath);

        // Load the Excel sheet by its name (sheetName)
        loadSheet(sheetName);

        // Get the total number of rows in the sheet
        int rowNumber = getRowCount();

        // Initialize a list to store each row's data as a map (key-value pairs)
        List<Map<String, String>> data = new ArrayList<>();

        // Loop through each row starting from row index 1 (ignoring the header row at index 0)
        for (int row = 1; row < rowNumber; row++) {
            // Create a LinkedHashMap to maintain the order of insertion for each row's data
            Map<String, String> rowMap = new LinkedHashMap<>();

            // Loop through each column in the current row
            for (int column = 0; column < getColumnCount(row); column++) {
                // Get the header (key) from the first row (index 0)
                String key = getCellData(0, column);

                // Get the cell value for the current row and column
                String value = getCellData(row, column);

                // Store the header-value pair in the map
                rowMap.put(key, value);
            }

            // Add the current row's map of data to the list
            data.add(rowMap);
        }

        // Return the list of maps, where each map represents a row with key-value pairs
        return data;
    }

    /**
     * Reads data from the specified Excel file and sheet into a 2D array.
     * The first row is ignored as it is considered a header.
     *
     * @param filePath  the path of the Excel file
     * @param sheetName the name of the sheet to read from
     * @return a 2D array containing the data from the sheet
     */
    public static Object[][] excelToArray(String filePath, String sheetName) {
        openExcel(filePath);
        loadSheet(sheetName);

        int rowCount = getRowCount();
        int columnCount = getColumnCount(0);

        Object[][] data = new Object[rowCount - 1][columnCount];

        // for each row (ignore the first row - row index 0 - because it's the header)
        for (int row = 1; row < rowCount; row++) {
            // for each column on a row
            for (int column = 0; column < columnCount; column++) {
                data[row - 1][column] = getCellData(row, column);
            }
        }

        return data;
    }
}