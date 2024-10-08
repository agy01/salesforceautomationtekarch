package utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import contants.FileConstants;

public class FileUtils {

	public static String readLoginPropertiesFile(String key) throws FileNotFoundException, IOException {
		Properties p = new Properties();
		p.load(new FileReader(FileConstants.LOGIN_TEST_DATA_FILE_PATH));
		return p.getProperty(key);
	}
	
	public static String readMyProfilePropertiesFile(String key) throws FileNotFoundException, IOException {
		Properties p = new Properties();
		p.load(new FileReader(FileConstants.MYPROFILE_TEST_DATA_FILE_PATH));
		return p.getProperty(key);
	}
	
	public static String readMySettingsPropertiesFile(String key) throws FileNotFoundException, IOException {
		Properties p = new Properties();
		p.load(new FileReader(FileConstants.MYSETTINGS_TEST_DATA_FILE_PATH));
		return p.getProperty(key);
	}
	
	public static String readDeveloperConsolePropertiesFile(String key) throws FileNotFoundException, IOException {
		Properties p = new Properties();
		p.load(new FileReader(FileConstants.DEVCONSOLE_TEST_DATA_FILE_PATH));
		return p.getProperty(key);
	}
	
	public static String readAccountsTabPropertiesFile(String key) throws FileNotFoundException, IOException {
		Properties p = new Properties();
		p.load(new FileReader(FileConstants.ACCOUNTSTAB_TEST_DATA_FILE_PATH));
		return p.getProperty(key);
	}
}
