# The Weather

[![Build Status](https://travis-ci.org/aamrita/the-weather.svg?branch=master)](https://travis-ci.org/aamrita/the-weather)
[![codecov](https://codecov.io/gh/aamrita/the-weather/branch/master/graph/badge.svg)](https://codecov.io/gh/aamrita/the-weather)

Weather Simulator in Java

# Problem Statement
Create a toy simulation of the environment (taking into account things like atmosphere, topography, geography, oceanography, or similar) that evolves overtime.
# Prerequisites
- JDK 1.8 (JAVA_HOME and PATH set) for compile and execution.
- Apache Maven 3.3 or higher (MVN_HOME and PATH set) for build.

# Solution approach
In this project, forecast of the below weather parameters are made.
 - **Temperature**
 - **Pressure**
 - **Humidity**
 - **Weather Condition**

First three being continuous values are simulated using an extension of the [ARIMA](https://github.com/signaflo/java-timeseries/wiki/ARIMA-models) model  and based on these values the Weather condition(Sunny/Cloudy/Rainy etc.) is predicted . An analysis of the general pattern in which Temperature , Pressure, Humidity effect the weather condition of a place was made.

This application make use of the ARIMA (1,0,1) which makes our model a combination of AR and MA. **_Also the upper and lower configuration values along with an error factor is taken from the model and final simulated value is calculated out of it_**. 

# Why ARIMA?
We have multiple algorithms available for Prediction/Forecasting like the Average approach, Naïve approach, TimeSeries models etc. of which TimeSeries can be used in our case since the weather parameters are those that evolve over time. 
It brings together the benifits of regressive model and the average approach and is one best fit for our project. 

# Input 
Input to the application is a date from the user-end which is taken in as an argument. Note that input date has to follow the format _MM/dd/yyyy HH_ and it has to be a future date. The input can also be of the form _MM/dd/yyyy HH:mm:ss_ ;only the HH part of the time is taken into consideration for simulation though. **HH is expected on a 24 hour cycle that is a number from 0 - 24**

    Pattern : MM/dd/yyyy HH
    e.g., 12/20/2018 16

_Apart from the user-input date there are a couple of inputs that need to be fed in to the program._

[location.txt](/src/main/resources/locations.txt) : This file contain names of locations for which historical weather data has already been collected and put in the ‘data’ directory under resources. Simulation is performed for locations which are present in the location.txt file alone.

[data](/src/main/resources/data) : This directory contain historic weather data files which is put under each month of the year(named by the sequence number of the months).This is in turn grouped under the corresponding locations.

The historic weather data can be obtained from API services or verified weather information websites. For example, [The Bureau of Meteorology, Govt. of Australia](http://www.bom.gov.au/climate/dwo/), [Wunderground](https://www.wunderground.com/) etc.

In this application data has been collected from The Bureau of Meteorology, Govt. of Australia.

[coordinates.txt](/src/main/resources/coordinates.txt)  : This file contains mapping of each  locations to its corresponding coordinates in a general form as location:latitude,longitude,elevation

# Steps to run the application
**Step 1** : Clone the repository.

     git clone https://github.com/aamrita/the-weather
     
**Step 2** : Build the project. 

Navigate to the root directory of the project and build the project using the below command:
      
    mvn clean install
**Step 3** : Run the application
By now you will get a jar generated in the target folder. To run the jar execute the below command:

     Pattern : java -jar jar-name.jar MM/dd/yyyy HH
     e.g.,  java -jar target/TheWeather-0.0.1-SNAPSHOT-jar-with-dependencies.jar 12/20/2018 16    

# Output
For an input date and hour of the day, the program generates a list of parameters and is written to an output file of the form MM_dd_yyyy_results in the parent location itself.


Each line in the output file include name of the location, its position (combination of its latitude, longitude & elevation), prediction date( ISO8601), weather condition, temperature(°C), pressure(hPa) and relative humidity(%).

    Pattern: location|latitude,longitude,elevation|date_time|conditions|temperature|pressure|humidity
    e.g., Sydney|-33.86,151.2,20|2018-09-20T00:00:00Z|Rainy|14.2|1020.3|59.6


# How does it work?

On a high level the functionality can be thought to be taking place in 3 phases.

> Fetching the reference data for simulation based on the input.

The program takes a date (MM/dd/yyyy HH) from the user as an input argument. After validating the date, historic data corresponding to the input month (inferred from the input date) is taken. In this solution **three months historic data** are considered for simulation. That is, if the input date falls in May, the simulation will be based on the historic data collected from different locations for May, April and March.

Input hour field also has a major role in deciding what data needs to be considered for simulation. If the input hour is 0 - 12 that is until the 12th hour of the day; simulation is drawn out from the historic data collected at 9am. On the other hand for range 13 - 24 historic data recorded at 3 pm(15th hour in 24hr clock) will be considered. This makes the simulation a bit more accurate and reliable.


> Simulation of Temperature, Pressure and Humidity values.

Now that we have the historic data, the prediction of weather parameters (temperature, pressure & relative humidity) is performed based on ARIMA Model, respective past weather parameters obtained from the historic data. Also the data that is taken up for the weather simulation depends not only on the input date but on the hour (HH field - second input argument to the application) also. 


> Weather Condition prediction.

Based on the forecast values simulated by ARIMA, predictions about the Weather Condition for the input date are made. For this, a general trend as to how Temperature, Pressure and Humidity affect the weather condition of a place was analysed from the historic data and break points were fixed.
 
# References
https://en.wikipedia.org/wiki/Autoregressive_integrated_moving_average


