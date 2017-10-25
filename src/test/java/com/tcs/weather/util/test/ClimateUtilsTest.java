package com.tcs.weather.util.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.tcs.weather.util.ClimateUtils;
import com.tcs.weather.bean.SimulatorOutput;
import com.tcs.weather.constants.ConstantParam;

public class ClimateUtilsTest {
    private static Double value;
    SimulatorOutput simulatorOutput = new SimulatorOutput();

	@Test
	public void getMeanTest(){
		value = (double) 20;
		assertEquals(value,ClimateUtils.getMean("30", "10"));
	}

    @Test
	public void getWeatherCondition(){
		simulatorOutput.setForecastTemp(14.3);
		simulatorOutput.setForecastHumidity(62.0);
		simulatorOutput.setForecastPressure(1018.0);
		assertEquals(ConstantParam.SHWR_TWO,ClimateUtils.getWeatherCondition(simulatorOutput));
	}

}
