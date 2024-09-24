package contants;

import utils.CommonUtils;

public class FileConstants {
	
	public static final String ROOT_PATH = System.getProperty("user.dir");
	public static final String LOGIN_TEST_DATA_FILE_PATH = ROOT_PATH + "/src/main/java/testdata/logintestdata.properties";
	public static final String MYPROFILE_TEST_DATA_FILE_PATH = ROOT_PATH + "/src/main/java/testdata/myprofiletestdata.properties";
	public static final String MYSETTINGS_TEST_DATA_FILE_PATH = ROOT_PATH + "/src/main/java/testdata/mysettingtestdata.properties";
	public static final String TEST_FILE_UPLOAD_PATH = ROOT_PATH + "/src/main/resources/SFDC 37 Testcases.xlsx";
	public static final String TEST_PHOTO_UPLOAD_PATH = ROOT_PATH + "/src/main/resources/GoToWebinar 000.png";
	public static final String DEVCONSOLE_TEST_DATA_FILE_PATH = ROOT_PATH + "/src/main/java/testdata/developerconsoletestdata.properties";
	public static final String ACCOUNTSTAB_TEST_DATA_FILE_PATH = ROOT_PATH + "/src/main/java/testdata/accountstabtestdata.properties";
	
	public static final String SCREENSHOTS_FOLDER_PATH = ROOT_PATH + "/src/main/resources/reports/" + CommonUtils.getTimeStamp() + "_SFDC.png";
	public static final String REPORT_FILE_PATH = ROOT_PATH + "/src/main/resources/reports/" + CommonUtils.getTimeStamp() + "_SFDC.html";
 
}
