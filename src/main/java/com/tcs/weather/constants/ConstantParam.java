package com.tcs.weather.constants;

/**
 * @author amrutha
 * 
 * All the constant values used in the application.
 */
public class ConstantParam {
	// Constant operations and symbols
	public static final String COLON_OPR = ":";
	public static final String COMA_OPR = ",";
	public static final String FWRD_SLASH = "/";
	public static final String UNDERSCORE = "_";
	public static final String SPACE = " ";

	// Constants for input/output files
	public static final String DOT_OPR = ".";
	public static final String FILE_EXTNTN = "csv";
	public static final String INP_LOC_DIR = "data";
	public static final String OUTPUT_RESULTS = "results";
	public static final String COORDINATE_FILE = "coordinates.txt";
	public static final String LOCATIONS_FILE = "locations.txt";

	// Constants for date formatting
	public static final int DEF_NUM_MNTH = 3;
	public static final Integer DEF_DEC_MNTH = 12;
	public static final Integer DEF_NOV_MNTH = 11;
	public static final String ZERO_PREFIX = "0";
	public static final String DATE_FORMAT = "MM/dd/yyyy";
	public static final String DATE_FORMAT_HR = "MM/dd/yyyy HH";
	public static final String ISO_DATE = "yyyy-MM-dd'T'HH:mm:ss'Z'";

	// Constants for ARIMA model
	public static final int ARIMA_PARAM_p = 1;
	public static final int ARIMA_PARAM_d = 0;
	public static final int ARIMA_PARAM_q = 1;
	public static final int ARIMA_PARAM_P = 1;
	public static final int ARIMA_PARAM_D = 1;
	public static final int ARIMA_PARAM_Q = 0;
	public static final int ARIMA_PARAM_m = 0;
	
	//Constants for weather condition
	public static final String SUNNY = "Sunny";
	public static final String SHWR_TWO = "Rainy";
	public static final String FOGGY = "Foggy";
	public static final String CLOUDY = "Cloudy";
	public static final String POSSIBLE_LATE_SHWR = "Possible Late Shower";
	public static final String COOL = "Cool";
	public static final String COLD = "Cold";
	public static final String WARM = "Warm";

	//Constants for Logger
	public static final Object INVALID_ARGUMENTS = "Invalid input. Please enter a valid date in the format MM/dd/yyyy HH or MM/dd/yyyy HH:mm:ss as input";
	public static final Object AT_ENTRY = "You are at the EntryPoint";
	public static final Object FUTURE_DATE = "Invalid Input! Please enter a future date in the format MM/dd/yyyy";
	
	//Constants for exceptions
	public static final String CORD_NT = "coordinates.txt not found! Make sure the file is present in the resources.";
	public static final String LOCATIONS_NT ="locations.txt not found! Make sure the file is present in the resources.";
	public static final String LOC_ISSUE ="Issue in reading locations.txt";
	public static final String INP_MNTH_FILE_NT = "Input file for the corresponding month not found... Looks like you have entered input date in dd/MM/yyyy format. USE MM/dd/yyyy INSTEAD !";
	public static final String HISTORY_NT = "trouble in reading historic data";
	public static final String VALID_DATE_MSG = "Please enter a valid input date in the format - ";
	
}
