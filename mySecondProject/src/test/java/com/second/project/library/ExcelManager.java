package com.second.project.library;

import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Iterator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/***
 * This is a Excel class that handle reading and writing Microsoft excel file
 * using Apache poi library
 * 
 * @author Alvis
 */
public class ExcelManager {
public static final Logger log = LogManager.getLogger(ExcelManager.class);
	
	private static String filePath;
	private static Workbook wb;
	private static Sheet sh;

	/***
	 * This is the Constructor1 - reads sheet by String name
	 * 
	 * @param excelFile
	 * @param sheetName
	 */
	public ExcelManager(String excelFile, String sheetName) {
		try {
			File excelDataFile = new File(excelFile);
			filePath = excelDataFile.getAbsolutePath();
			log.info("Reading Excel file ---> " + filePath);

			FileInputStream fs = new FileInputStream(excelDataFile);
			wb = getWorkbook(fs, filePath);
			sh = wb.getSheet(sheetName);
		} catch (Exception e) {
			log.error("Error: ", e);
		}
	}

	/***
	 * This is the Constructor2 - reads sheet by index
	 * 
	 * @param excelFile
	 * @param sheetIndex
	 */
	public ExcelManager(String excelFile, int sheetIndex) {
		try {
			File excelDataFile = new File(excelFile);
			filePath = excelDataFile.getAbsolutePath();
			log.info("Reading Excel file ---> " + filePath);

			FileInputStream fs = new FileInputStream(excelDataFile);
			wb = getWorkbook(fs, filePath);
			sh = wb.getSheetAt(sheetIndex);
		} catch (Exception e) {
			log.error("Error: ", e);
		}
	}

	public String readExcelDataCell(int rowIndex, int colIndex) {
		String cellData = null;
		try {
			Row row = sh.getRow(rowIndex);
			if (row != null) {
				Cell cell = row.getCell(colIndex);
				cellData = formatDataCellToString(cell);
			}
		} catch (Exception e) {

		}
		return cellData;
	}

	public String[][] getExcelData() {
		String[][] arrayExcelData = null;
		try {
			Iterator<Row> iterator = sh.iterator();
			Row tempRow = sh.getRow(0);
			if (tempRow != null) {
				int totalCols = tempRow.getPhysicalNumberOfCells();
				int totalRows = sh.getPhysicalNumberOfRows();
				arrayExcelData = new String[totalRows- 1][totalCols];
				int iRowCount = 0;

				while (iterator.hasNext()) {
					Row row = iterator.next();
					// skipping row 1, because it's table header info
					if (iRowCount > 0) {
						Iterator<Cell> colIterator = row.iterator();
						int iColCount = 0;
						while (colIterator.hasNext()) {
							Cell cell = colIterator.next();
							// need to forma the cells before Read it as a string
							String data = formatDataCellToString(cell);
							arrayExcelData[iRowCount - 1][iColCount] = data;
							log.debug("Row: " + iRowCount + ", Col: " + iColCount + ", Data: " + data);
							iColCount++;
						}
					}
					iRowCount++;
				}
			}
		} catch (Exception e) {
			log.error("Error: ", e);
		}
		return arrayExcelData;
	}

	// --- these methods below are helper methods and declared in private -------
	// Method to identify the excel file version and returns the correct workbook
	// object
	private Workbook getWorkbook(FileInputStream fis, String excelFilePath) {
		Workbook workbook = null;
		try {
			if (excelFilePath.toLowerCase().endsWith(".xlsx")) {
				workbook = new XSSFWorkbook(fis);
			} else if (excelFilePath.toLowerCase().endsWith(".xls")) {
				workbook = new HSSFWorkbook(fis);
			} else {
				throw new IllegalArgumentException("The specified file is not an 'Excel' data file.");
			}
		} catch (Exception e) {
			log.error("Error: ", e);
		}
		return workbook;
	}

	private String formatDataCellToString(Cell cell) {
		String cellString = null;
		try {
			DataFormatter formatter = new DataFormatter();
			cellString = formatter.formatCellValue(cell);
		} catch (Exception e) {
			log.error("Error: ", e);
		}
		return cellString;
	}

	/// testing this class using main method
	public static void main(String[] args) {
		try {
			//String excelFile1 = "src/test/resources/data/CalculaterTestData1.xlsx";
			String excelFile2 = "src/test/resources/data/CalculaterTestData1.xlsx";
			//String file3 = "C:/abc/r4/frank/myPet.txt";

			ExcelManager excel1 = new ExcelManager(excelFile2, "mortgage_data");
			//ExcelManager excel = new ExcelManager(file3, 0);
			log.info("Excel data ---------");
			String[][] result = excel1.getExcelData();
			log.info(result);
			
			log.info(Arrays.deepToString(result));
			String excelData = excel1.readExcelDataCell(3, 10);
			log.info("exceldata: " + excelData);
		} catch (Exception e) {
			log.error("Error: ", e);
		}
	}

	
	
	
}
