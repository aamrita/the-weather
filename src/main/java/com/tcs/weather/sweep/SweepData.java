package com.tcs.weather.sweep;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.tcs.weather.bean.SimulatorInput;
import com.tcs.weather.bean.SimulatorOutput;
import com.tcs.weather.constants.ConstantParam;
import com.tcs.weather.exception.WeatherException;

/**
 * @author amrutha
 *
 *         This class is to pull data from historic weather data based on
 *         user-input date(data of prior two months is also considered).
 */
public class SweepData {
	PrintWriter out = null;

	/**
	 * @param historyFileList
	 *            : file location of the data which needs to be pulled. Say, if
	 *            the input is 10/30/2018(October month)then data for October,
	 *            September and August will be pulled.
	 * @param simInput
	 *            : object of SimulatorInput class in which location,date and
	 *            coordinate info are already set.
	 * @return : object of SimulatorInput class in which temperature,pressure
	 *         and humidity values from historic data is set in addition to the
	 *         already set value(location name,date,coordinate)
	 * 
	 * @throws WeatherException
	 */
	public SimulatorInput pullDataIn(List<String> historyFileList,SimulatorInput simInput) throws WeatherException {
		List<Double> tempratureList = new ArrayList<Double>();
		List<Double> humidityList = new ArrayList<Double>();
		List<Double> pressureList = new ArrayList<Double>();
		int hour = simInput.getHour();

		for (String eachfile : historyFileList) {
			// Get file from resources folder
			ClassLoader classLoader = getClass().getClassLoader();
		
			try {
				BufferedReader in = new BufferedReader(new InputStreamReader(
						classLoader.getResourceAsStream(eachfile)));
				in.readLine();
				String line;
				while ((line = in.readLine()) != null) {
					String[] lineSplits = line
							.split(ConstantParam.COMA_OPR, -1);
					// adding weather parameter reading at 9am to list (in if
					// condition).
					// adding weather parameter reading at 3pm to list (in else
					// condition).
					if (hour <= 12) {
						tempratureList.add(Double.parseDouble(lineSplits[9]));
						humidityList.add(Double.parseDouble(lineSplits[10]));
						pressureList.add(Double.parseDouble(lineSplits[14]));
					} else {
						tempratureList.add(Double.parseDouble(lineSplits[15]));
						humidityList.add(Double.parseDouble(lineSplits[16]));
						pressureList.add(Double.parseDouble(lineSplits[20]));
					}

				}
			} catch (Exception e1) {
				throw new WeatherException(e1, ConstantParam.INP_MNTH_FILE_NT);
			}
		}
		simInput.setInpTemp(tempratureList);
		simInput.setInpHumidity(humidityList);
		simInput.setInpPressure(pressureList);

		return simInput;
	}

	// Writing the results back to the output file after simulation and weather
	// condtion prediction.
	public void pushDataOut(SimulatorOutput simulatoroutput, PrintWriter writer) {
		writer.println(simulatoroutput.toString());

	}
}