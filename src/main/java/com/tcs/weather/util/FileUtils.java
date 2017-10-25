/**
 * 
 */
package com.tcs.weather.util;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import com.tcs.weather.constants.ConstantParam;
import com.tcs.weather.exception.WeatherException;

/**
 * @author amrutha
 *
 *         to perform operations related to files like creating output file.
 */
public class FileUtils {

	/**
	 * @param inpDate
	 *            : user input date.
	 * @return : an object of PrintWriter. a fie is created in the form
	 *         MM_dd_yyyy_results.txt
	 * @throws WeatherException
	 */
	public static PrintWriter createOutputFile(String inpDate)
			throws WeatherException {
		// TODO Auto-generated method stub
		PrintWriter writer = null;
		String outputLocationName = inpDate.replaceAll(
				ConstantParam.FWRD_SLASH, ConstantParam.UNDERSCORE)
				+ ConstantParam.UNDERSCORE + ConstantParam.OUTPUT_RESULTS;
		try {
			writer = new PrintWriter(outputLocationName);
		} catch (FileNotFoundException e) {
			throw new WeatherException("File cannot be created");
		}
		return writer;
	}

}
