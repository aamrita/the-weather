package com.tcs.weather.bean;

/**
 * @author amrutha
 *
 *         bean class for output object along with getters and setters. Output
 *         generated after running the algorithm is set to this object
 */
public class SimulatorOutput {

	private String location;
	private Double forecastTemp;
	private Double forecastPressure;
	private Double forecastHumidity;
	private String predictionDate;
	private String cordinate;
	private String condition;

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Double getForecastTemp() {
		return forecastTemp;
	}

	public void setForecastTemp(Double forecastTemp) {
		this.forecastTemp = forecastTemp;
	}

	public Double getForecastPressure() {
		return forecastPressure;
	}

	public void setForecastPressure(Double forecastPressure) {
		this.forecastPressure = forecastPressure;
	}

	public Double getForecastHumidity() {
		return forecastHumidity;
	}

	public void setForecastHumidity(Double forecastHumidity) {
		this.forecastHumidity = forecastHumidity;
	}

	public String getPredictionDate() {
		return predictionDate;
	}

	public void setPredictionDate(String predictionDate) {
		this.predictionDate = predictionDate;
	}

	public String getCordinate() {
		return cordinate;
	}

	public void setCordinate(String cordinate) {
		this.cordinate = cordinate;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(location + "|" + cordinate + "|" + predictionDate + "|"
				+ condition + "|" + forecastTemp + "|" + forecastPressure + "|"
				+ forecastHumidity);
		return builder.toString();
	}
}