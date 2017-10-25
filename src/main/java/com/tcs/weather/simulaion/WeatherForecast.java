package com.tcs.weather.simulaion;

import com.tcs.weather.constants.ConstantParam;
import com.workday.insights.timeseries.arima.Arima;
import com.workday.insights.timeseries.arima.struct.ArimaParams;
import com.workday.insights.timeseries.arima.struct.ForecastResult;


/**
 * @author amrutha
 *
 */
public class WeatherForecast {
	
	public double[] getForecastData(double[] tempList) {
		// Set ARIMA model parameters.
		int p = ConstantParam.ARIMA_PARAM_p;
		int d = ConstantParam.ARIMA_PARAM_d;
		int q = ConstantParam.ARIMA_PARAM_q;
		

		int P = ConstantParam.ARIMA_PARAM_P;
		int D = ConstantParam.ARIMA_PARAM_D;
		int Q = ConstantParam.ARIMA_PARAM_Q;
		int m = ConstantParam.ARIMA_PARAM_m;
		
		//setting the forecast size. 
		int forecastSize = 1;
		
		ArimaParams arimaParams = new ArimaParams(p, d, q, P, D, Q, m);
		
		// Obtaining the forecast result.
		ForecastResult forecastResult = Arima.forecast_arima(tempList, forecastSize, arimaParams);

		// Read forecast values
		double[] forecastData = forecastResult.getForecast();
		return forecastData;
	}

}
