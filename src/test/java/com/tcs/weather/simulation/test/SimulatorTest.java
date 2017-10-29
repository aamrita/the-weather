package com.tcs.weather.simulation.test;

import static org.junit.Assert.assertNotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.tcs.weather.bean.Cordinate;
import com.tcs.weather.bean.SimulatorInput;
import com.tcs.weather.constants.ConstantParam;
import com.tcs.weather.simulation.Simulator;

public class SimulatorTest {
	SimulatorInput simulator = new SimulatorInput();
	private Map<String, Cordinate> cordMap = new HashMap<String, Cordinate>();
	Cordinate cordinate = new Cordinate();
	SimpleDateFormat sdf = new SimpleDateFormat(ConstantParam.DATE_FORMAT);
	List<Double> tempratureList, humidityList,
			pressureList = new ArrayList<Double>();

	@Before
	public void initialzeValues() {
		tempratureList = Arrays.asList(10.0, 20.1, 10.0, 20.1, 10.0, 20.1,
				10.0, 20.1, 10.0, 20.1, 10.0, 20.1, 10.0, 20.1);
		pressureList = Arrays.asList(10.0, 20.1, 10.0, 20.1, 10.0, 20.1, 10.0,
				20.1, 10.0, 20.1, 10.0, 20.1, 10.0, 20.1);
		humidityList = Arrays.asList(10.0, 20.1, 10.0, 20.1, 10.0, 20.1, 10.0,
				20.1, 10.0, 20.1, 10.0, 20.1, 10.0, 20.1);

	}

	@Test
	public void simulateTest() throws ParseException {
		cordinate.setLat(22.5);
		cordinate.setLon(35.7);
		cordinate.setEle(18);
		cordMap.put("Sydney", cordinate);
		simulator.setCordMap(cordMap);
		simulator.setInpTemp(tempratureList);
		simulator.setInpPressure(pressureList);
		simulator.setInpHumidity(humidityList);
		simulator.setLocation("Sydney");
		simulator.setInpRefDate(sdf.parse("10/29/2017"));

		assertNotNull(Simulator.simulate(simulator));
	}

}
