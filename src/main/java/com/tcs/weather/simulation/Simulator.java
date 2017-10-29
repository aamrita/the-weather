package com.tcs.weather.simulation;

import java.util.List;

import com.tcs.weather.bean.SimulatorInput;
import com.tcs.weather.bean.SimulatorOutput;
import com.tcs.weather.constants.ConstantParam;
import com.tcs.weather.util.ClimateUtils;
import com.tcs.weather.util.DateUtils;

/**
 * @author amrutha
 *
 */
public class Simulator {

	/**
	 * @param inputs
	 *            : object of SimulatorInput class. It has the input
	 *            date,location name,coordinate information,historic
	 *            pressure,temperature and humidity lists already set.
	 * 
	 * @return : an object of SimulatorOutput class.
	 * 
	 *         Takes the historic weather parameters that are already set in the
	 *         incoming bean class and pass it on to the ARIMA model in-order to
	 *         calculate the forecast values.
	 */
	public static SimulatorOutput simulate(SimulatorInput inputs) {
		// TODO Auto-generated method stub
		List<Double> temperatureList = inputs.getInpTemp();
		List<Double> pressureList = inputs.getInpPressure();
		List<Double> humidityList = inputs.getInpHumidity();
		SimulatorOutput simulatorOutput = new SimulatorOutput();
		WeatherForecast weatherforecast = new WeatherForecast();

		// Converting List<Double> to Double[] since argument to getForecastData
		// is in the format Double[]
		double[] temperatureArray = temperatureList.stream()
				.mapToDouble(Double::doubleValue).toArray();

		double[] pressureArray = pressureList.stream()
				.mapToDouble(Double::doubleValue).toArray();

		double[] humidityArray = humidityList.stream()
				.mapToDouble(Double::doubleValue).toArray();

		// set location,coordinate information,input date(converted to ISO
		// format) , forecasted weather parameters to the output object.

		simulatorOutput.setLocation(inputs.getLocation());
		simulatorOutput.setCordinate(createCordinateFromMap(inputs));
		simulatorOutput.setPredictionDate(DateUtils.convertToIso(inputs
				.getInpRefDate()));
		double temperatureOutput = weatherforecast
				.getForecastData(temperatureArray);
		simulatorOutput
				.setForecastTemp((Math.round(temperatureOutput * 10D) / 10D));
		double pressure = weatherforecast.getForecastData(pressureArray);
		simulatorOutput.setForecastPressure((Math.round(pressure * 10D) / 10D));
		double humidity = weatherforecast.getForecastData(humidityArray);
		simulatorOutput.setForecastHumidity((Math.round(humidity * 10D) / 10D));

		// Predicting weather condition based on the forecast
		// temperature,pressure and humidity values.
		simulatorOutput.setCondition(ClimateUtils
				.getWeatherCondition(simulatorOutput));

		return simulatorOutput;
	}

	// Outputs a string taking latitude , longitude and elevation from the
	// cordMap which was
	// earlier set in input data.
	private static String createCordinateFromMap(SimulatorInput inputs) {
		Double lat = inputs.getCordMap().get(inputs.getLocation()).getLat();
		Double lon = inputs.getCordMap().get(inputs.getLocation()).getLon();
		int ele = inputs.getCordMap().get(inputs.getLocation()).getEle();
		return lat + ConstantParam.COMA_OPR + lon + ConstantParam.COMA_OPR
				+ ele;
	}

}
