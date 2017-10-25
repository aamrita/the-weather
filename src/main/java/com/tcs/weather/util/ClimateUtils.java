package com.tcs.weather.util;

import com.tcs.weather.bean.SimulatorOutput;
import com.tcs.weather.constants.ConstantParam;

/**
 * @author amrutha
 *
 *         For calculating the mean value of weather parameters, Predicting
 *         weather conditions based on the simulated values.
 */
public class ClimateUtils {

	/**
	 * @param temp1
	 *            : holds the weather parameter at 9 am(Say,temperature at 9am)
	 * @param temp2
	 *            : holds the weather parameter at 3 pm(Say,temperature at 3pm)
	 * 
	 * @return : is a double value which is a mean value of the input values.
	 */
	public static Double getMean(String temp1, String temp2) {
		Double meanTemp = new Double(0);
		temp1 = temp1.trim();
		temp2 = temp2.trim();
		if (!(temp1.isEmpty() || temp2.isEmpty())) {
			meanTemp = (Double.parseDouble(temp1) + Double.parseDouble(temp2)) / 2;
		}
		return meanTemp;
	}

	/**
	 * @param simulatorOutput
	 *            : object of SimulatorOutput.
	 * @return : hot/warm/sunny etc
	 * 
	 * 
	 *         based on the simulated weather parameters, predicts the weather
	 *         condition of the location.
	 */
	public static String getWeatherCondition(SimulatorOutput simulatorOutput) {
		String weatherCondition = null;
		double temperature = simulatorOutput.getForecastTemp();
		double pressure = simulatorOutput.getForecastPressure();
		double humidity = simulatorOutput.getForecastHumidity();

		if ((temperature > 27 && humidity < 60) || (temperature > 29)) {
			weatherCondition = ConstantParam.SUNNY;
		} else if (temperature < 17 && (humidity > 65 || pressure > 1015)) {
			weatherCondition = ConstantParam.SHWR_TWO;
		} else if (temperature < 16 && (humidity > 60 ||humidity <70)) {
			weatherCondition = ConstantParam.FOGGY;
		} else if (temperature >= 24 && temperature <= 26) {
			weatherCondition = ConstantParam.CLOUDY;
		} else if ((temperature >= 20 && temperature <= 24 && humidity > 56)
				|| (temperature >= 20 && temperature <= 24)) {
			weatherCondition = ConstantParam.POSSIBLE_LATE_SHWR;
		} else if (temperature >= 17 && temperature < 20 && humidity < 60) {
			weatherCondition = ConstantParam.COOL;
		} else if (temperature < 15) {
			weatherCondition = ConstantParam.COLD;
		}else if (temperature<=30 || temperature>=25 && humidity > 70){
			weatherCondition = ConstantParam.WARM;
		}
		return weatherCondition;
	}

}
