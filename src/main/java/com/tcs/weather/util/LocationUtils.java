/**
 * 
 */
package com.tcs.weather.util;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.tcs.weather.bean.Cordinate;
import com.tcs.weather.constants.ConstantParam;
import com.tcs.weather.exception.WeatherException;

/**
 * @author amrutha
 *
 *         to perform operations related to input location name like reading
 *         location names from locations.txt file, frame file path to historic
 *         data from input location and month(MM) and to get the coordinate
 *         information of the corresponding locations.
 */
public class LocationUtils {

	/**
	 * @return : list of locations present in the locations.txt file.
	 * @throws WeatherException
	 */
	public List<String> getDistinctLocations() throws WeatherException {
		List<String> locationlist = new ArrayList<String>();
		ClassLoader classLoader = getClass().getClassLoader();
		try {
            BufferedReader in = new BufferedReader(new InputStreamReader(classLoader.getResourceAsStream(ConstantParam.LOCATIONS_FILE)));
		    String line;
            while ((line = in.readLine()) != null) {
                locationlist.add(line);
            }
		} catch (Exception e1) {
			throw new WeatherException(ConstantParam.LOCATIONS_NT);
		}
		return locationlist;
	}

	/**
	 * @param inputLoc
	 *            : location name
	 * @param intMonth
	 *            : sequence number of the month in the input date
	 * @return
	 */
	public static String getFilePath(String inputLoc, Integer intMonth) {
		String strMonth = intMonth.toString();
		if (strMonth.length() < 2) {
			strMonth = ConstantParam.MNTH_PREFIX + strMonth;
		}
		return ConstantParam.INP_LOC_DIR + "/" + inputLoc.toUpperCase() + "/"
				+ strMonth + ConstantParam.DOT_OPR + ConstantParam.FILE_EXTNTN;
	}

	/**
	 * @param loc
	 *            : location name
	 * @return : a map with key as the location name, value as an object of
	 *         Cordinate. Cordinate is a bean class of
	 *         latitude,longitude,elevetion.
	 * @throws WeatherException
	 */
	public Map<String, Cordinate> getLatLonEle(String loc)
			throws WeatherException {
		Map<String, Cordinate> cordinateMap = new HashMap<String, Cordinate>();
		ClassLoader classLoader = getClass().getClassLoader();

		try {
            BufferedReader in = new BufferedReader(new InputStreamReader(classLoader.getResourceAsStream(ConstantParam.COORDINATE_FILE)));
            String fileLine;
            while ((fileLine = in.readLine()) != null) {
				String[] firstSplitRes = fileLine.split(
						ConstantParam.COLON_OPR, -1);
				if (loc.equalsIgnoreCase(firstSplitRes[0])) {
					Cordinate cordinate = new Cordinate();
					String[] cords = firstSplitRes[1].split(
							ConstantParam.COMA_OPR, -1);
					cordinate.setLat(Double.parseDouble(cords[0]));
					cordinate.setLon(Double.parseDouble(cords[1]));
					cordinate.setEle(Integer.parseInt(cords[2]));
					cordinateMap.put(loc, cordinate);
					break;
				}
            }
		} catch (Exception e) {
			throw new WeatherException(ConstantParam.CORD_NT);
		}
		return cordinateMap;
	}

}
