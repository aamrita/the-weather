/**
 * 
 */
package com.tcs.weather.entry;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import com.tcs.weather.bean.Cordinate;
import com.tcs.weather.bean.SimulatorInput;
import com.tcs.weather.bean.SimulatorOutput;
import com.tcs.weather.constants.ConstantParam;
import com.tcs.weather.exception.WeatherException;
import com.tcs.weather.simulation.Simulator;
import com.tcs.weather.sweep.SweepData;
import com.tcs.weather.util.DateUtils;
import com.tcs.weather.util.FileUtils;
import com.tcs.weather.util.LocationUtils;

/**
 * @author amrutha
 * 
 *         This is the entry point for the program.
 * 
 *         This class takes date(MM/dd/yyyy) and hour(in the 24 hour clock) for
 *         which output needs to be simulated as input arguments; validate it
 *         and simulates output parameters.
 * 
 */
public class EntryPoint {

	private static final Logger LOGGER = Logger.getLogger(EntryPoint.class);

	/**
	 * @param args
	 *            :user input date in MM/dd/yyyy format and hour(either as HH or
	 *            HH:mm:ss).
	 * 
	 * 
	 *            Output is generated as simulated weather data for each
	 *            locations in the location.txt file and is written back to a
	 *            new file which will be created under the parent folder in the
	 *            format MM_dd_yyyy_results.
	 */

	public static void main(String[] args) {
		BasicConfigurator.configure();

		try {
			LOGGER.debug(ConstantParam.AT_ENTRY);

			// Validates the input argument null check and count
			if (args == null || args.length > 2) {
				LOGGER.error(ConstantParam.INVALID_ARGUMENTS);
				System.exit(1);
			}

			String inpDate = args[0];
			String inputHour = args[1];
			int hour = DateUtils.validateHour(inputHour);
			List<String> locations;
			Date inpRefDate = new Date();
			PrintWriter printwriter = null;

			// Convert inpDate from string to date format ( MM/dd/yyyy HH:00:00)
			inpRefDate = DateUtils.formatDate(inpDate, hour);

			// Check if the input date is a future date.
			boolean futureDate = DateUtils.checkIfFutureDate(inpDate);
			if (!futureDate) {
				LOGGER.error(ConstantParam.FUTURE_DATE);
				System.exit(1);
			}

			// Slices only the month from input date.
			int refMonthInt = Integer.parseInt(DateUtils.sliceMonth(inpDate));

			// Create the output file.
			printwriter = FileUtils.createOutputFile(inpDate);

			/* Reading training data and simulates output for each location. */

			// Reading location names from locations.txt file from resources.
			locations = new LocationUtils().getDistinctLocations();

			for (String location : locations) {
				List<String> historyFileList = new ArrayList<String>();
				SimulatorInput simulatorinput = new SimulatorInput();

				// For each location,returns the coordinate informations -
				// Latitude, Longitude and Elevation which is read from
				// latLongEle.txt file.
				Map<String, Cordinate> cordinateMap = new LocationUtils()
						.getLatLonEle(location);

				// fetches past 3 months weather data from the available
				// historic data files based on
				// input date
				List<Integer> priorMonths = DateUtils
						.getPreviousMonths(refMonthInt);
				for (int eachMonth : priorMonths) {
					String inpFile = LocationUtils.getFilePath(location,
							eachMonth);
					historyFileList.add(inpFile);
				}

				// populating SimulatorInput bean class with location
				// name,date and coordinate information.
				simulatorinput.setLocation(location);
				simulatorinput.setInpRefDate(inpRefDate);
				simulatorinput.setHour(hour);
				simulatorinput.setCordMap(cordinateMap);

				SweepData sweepdata = new SweepData();

				// Read the historic file contents and set the required inputs
				// for the ARIMA algorithm to run and simulate weather for the
				// input date.
				simulatorinput = sweepdata.pullDataIn(historyFileList,
						simulatorinput);

				// populating SimulatorOutput bean class with the simulated
				// values.
				SimulatorOutput simulatoroutput = Simulator
						.simulate(simulatorinput);

				// Writing the generated outputs to an output file
				// <MM_dd_yyyy_results>.txt
				sweepdata.pushDataOut(simulatoroutput, printwriter);
			}
			// closing the writer.
			printwriter.close();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (WeatherException e) {
			LOGGER.error(e);
		}
		LOGGER.debug("Weather simulation completed.");
	}
}
