package com.tcs.weather.sweep.test;

import static org.junit.Assert.*;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.tcs.weather.bean.SimulatorInput;
import com.tcs.weather.bean.SimulatorOutput;
import com.tcs.weather.exception.WeatherException;
import com.tcs.weather.sweep.SweepData;

public class SweepDataTest {
	List<String> historyFileList = new ArrayList<String>();
	SimulatorInput simInput = new SimulatorInput();
	SimulatorOutput simOutput = new SimulatorOutput();
	List<Double> tempratureList, humidityList,
			pressureList = new ArrayList<Double>();
	SweepData sweepdata = new SweepData();
	PrintWriter writer = null;

	@Before
	public void initializeValues() {
		tempratureList = Arrays.asList(10.0, 10.0, 10.0, 10.0, 10.0, 10.0,
				10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0,
				10.0);
		humidityList = Arrays.asList(10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0,
				10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0);
		pressureList = Arrays.asList(10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0,
				10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0);
		simOutput.setLocation("Sydney");
	}

	@Test
	public void pullDataInTest() throws WeatherException {
		simInput.setInpTemp(tempratureList);
		simInput.setInpHumidity(humidityList);
		simInput.setInpPressure(pressureList);
		historyFileList.add("data/SYDNEY/01.csv");

		SimulatorInput simInputCheck = sweepdata.pullDataIn(historyFileList,
				simInput);
		assertNotNull(simInputCheck.getInpTemp());
		assertNotNull(simInputCheck.getInpTemp());
		assertNotNull(simInputCheck.getInpTemp());
	}
}
