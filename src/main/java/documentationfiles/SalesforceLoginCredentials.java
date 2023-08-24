package documentationfiles;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class SalesforceLoginCredentials {
	private static FileInputStream file;
	private static String filePath;
	private static int rowNumber;
	private static  String USERNAME;
	private static String PASSWORD;
	private static String LOGINURL;
	private static String GRANTSERVICE;
	private static String CLIENTID;
	private static String CLIENTSECRET;
	private static String AfterInstanceUrlUptoSID;
	
	public SalesforceLoginCredentials(int rowNumber, String filePath ) {
		this.filePath=filePath;
		this.rowNumber=rowNumber;
	}
	
	public static void setFilePath(String filePath) throws FileNotFoundException {
		file=new FileInputStream(new File(filePath));
	}
	
	public static String getUSERNAME() {
		try {
			setFilePath(filePath);
            Workbook workbook = WorkbookFactory.create(file);
            Sheet sheet = workbook.getSheetAt(0); 
            Row row = sheet.getRow(rowNumber); 
            if (row != null) {
                Cell cell = row.getCell(1); 
                if (cell != null  ) {
                	USERNAME = cell.getStringCellValue();
                 }
            }
            workbook.close();
            file.close();
     	} catch (Exception e) {
     	       e.printStackTrace();
     	}
		return USERNAME;
	}
	public static String getPASSWORD() {
		try {
			setFilePath(filePath);
            Workbook workbook = WorkbookFactory.create(file);
            Sheet sheet = workbook.getSheetAt(0); 
            Row row = sheet.getRow(rowNumber); 
            if (row != null) {
                Cell cell = row.getCell(2); 
                if (cell != null  ) {
                	PASSWORD = cell.getStringCellValue();
                 }
            }
            workbook.close();
            file.close();
     	} catch (Exception e) {
     	       e.printStackTrace();
     	}
		return PASSWORD;
	}
	public static String getLOGINURL() {
		try {
			setFilePath(filePath);
            Workbook workbook = WorkbookFactory.create(file);
            Sheet sheet = workbook.getSheetAt(0); 
            Row row = sheet.getRow(rowNumber); 
            if (row != null) {
                Cell cell = row.getCell(3); 
                if (cell != null  ) {
                	LOGINURL = cell.getStringCellValue();
                 }
            }
            workbook.close();
            file.close();
     	} catch (Exception e) {
     	       e.printStackTrace();
     	}
		return LOGINURL;
	}
	public static String getGRANTSERVICE() {
		try {
			setFilePath(filePath);
            Workbook workbook = WorkbookFactory.create(file);
            Sheet sheet = workbook.getSheetAt(0); 
            Row row = sheet.getRow(rowNumber); 
            if (row != null) {
                Cell cell = row.getCell(4); 
                if (cell != null  ) {
                	GRANTSERVICE = cell.getStringCellValue();
                 }
            }
            workbook.close();
            file.close();
     	} catch (Exception e) {
     	       e.printStackTrace();
     	}
		return GRANTSERVICE;
	}
	public static String getCLIENTID() {
		try {
			setFilePath(filePath);
            Workbook workbook = WorkbookFactory.create(file);
            Sheet sheet = workbook.getSheetAt(0); 
            Row row = sheet.getRow(rowNumber); 
            if (row != null) {
                Cell cell = row.getCell(5); 
                if (cell != null  ) {
                	CLIENTID = cell.getStringCellValue();
                 }
            }
            workbook.close();
            file.close();
     	} catch (Exception e) {
     	       e.printStackTrace();
     	}	
		return CLIENTID;
	}
	public static String getCLIENTSECRET() {
		try {
			setFilePath(filePath);
            Workbook workbook = WorkbookFactory.create(file);
            Sheet sheet = workbook.getSheetAt(0); 
            Row row = sheet.getRow(rowNumber); 
            if (row != null) {
                Cell cell = row.getCell(6); 
                if (cell != null  ) {
                	CLIENTSECRET = cell.getStringCellValue();
                 }
            }
            workbook.close();
            file.close();
     	} catch (Exception e) {
     	       e.printStackTrace();
     	}
		return CLIENTSECRET;
	}
	public static String getAfterInstanceUrlUptoSID() {
		try {
			setFilePath(filePath);
            Workbook workbook = WorkbookFactory.create(file);
            Sheet sheet = workbook.getSheetAt(0); 
            Row row = sheet.getRow(rowNumber); 
            if (row != null) {
                Cell cell = row.getCell(7); 
                if (cell != null  ) {
                	AfterInstanceUrlUptoSID = cell.getStringCellValue();
                 }
            }
            workbook.close();
            file.close();
     	} catch (Exception e) {
     	       e.printStackTrace();
     	}
		return AfterInstanceUrlUptoSID;
	}

}
