package com.tcs.weather.util.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.tcs.weather.bean.SimulatorOutput;
import com.tcs.weather.constants.ConstantParam;
import com.tcs.weather.util.ClimateUtils;

public class ClimateUtilsTest {
 private static Double value;
 private static SimulatorOutput simulatorOutput = new SimulatorOutput();

 
 @Test
	public void getMeanTest(){
		value = (double) 10;
		assertEquals(value,ClimateUtils.getMean(10.0, 10.0));
	}
	
	@Test
	public void getWeatherCondition(){
		simulatorOutput.setForecastTemp(14.3);
		simulatorOutput.setForecastHumidity(62.0);
		simulatorOutput.setForecastPressure(1018.0);
		
		assertEquals(ConstantParam.SHWR_TWO,ClimateUtils.getWeatherCondition(simulatorOutput));
	}

}
