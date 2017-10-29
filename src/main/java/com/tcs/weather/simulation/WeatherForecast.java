package com.tcs.weather.simulation;

import com.tcs.weather.constants.ConstantParam;
import com.tcs.weather.util.ClimateUtils;
import com.workday.insights.timeseries.arima.Arima;
import com.workday.insights.timeseries.arima.struct.ArimaParams;
import com.workday.insights.timeseries.arima.struct.ForecastResult;

/**
 * @author amrutha
 *
 */
public class WeatherForecast {

	/**
	 * @param tempList
	 * @return : a double value,mean of upper and lower configuration values
	 *         simulated by the algorithm(rmse added /subtracted accordingly).
	 */
	public double getForecastData(double[] tempList) {
		// Set ARIMA model parameters.
		// Setting ARIMA(1,0,1) which work as a combination of AR and MA models
		int p = ConstantParam.ARIMA_PARAM_p;
		int d = ConstantParam.ARIMA_PARAM_d;
		int q = ConstantParam.ARIMA_PARAM_q;

		int P = ConstantParam.ARIMA_PARAM_P;
		int D = ConstantParam.ARIMA_PARAM_D;
		int Q = ConstantParam.ARIMA_PARAM_Q;
		int m = ConstantParam.ARIMA_PARAM_m;

		// setting the forecast size.
		int forecastSize = 1;

		ArimaParams arimaParams = new ArimaParams(p, d, q, P, D, Q, m);

		// Obtaining the forecast result.
		ForecastResult forecastResult = Arima.forecast_arima(tempList,
				forecastSize, arimaParams);

		// Along with root mean square error , Upper and lower bound of forecast
		// are read.
		forecastResult.getForecast();
		double[] upperconf = forecastResult.getForecastUpperConf();
		double[] lowerconf = forecastResult.getForecastLowerConf();
		double rmse = forecastResult.getRMSE();
		Double upperbound = null;
		Double lowerbound = null;
		for (double uconf : upperconf) {
			upperbound = uconf - rmse;
		}
		for (double lconf : lowerconf) {
			lowerbound = lconf + rmse;
		}
		return ClimateUtils.getMean(upperbound, lowerbound);
	}
}
