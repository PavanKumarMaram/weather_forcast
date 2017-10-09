Run:
===
This project is compatible with SBT and also MAVEN

The application expects, 2 mandatory command line parms 'year' and 'month'.

year should be between 2000 and 2017 
 

Steps to run:
=============
Step1 : build the jar file using sbt
	cmd:- sbt package

- compiled jar is available in (....weather_forcast\target\scala-2.11\weather_forcast_2.11-1.0-SNAPSHOT.jar)

step2 : run using spark-submit cmd 
	cmd:- spark-submit --class org.toy.weather.simulation.forcast.ToyWeatherSimulation --master local[#No] application-jar application-arguments:
	
 eg-cmd:- spark-submit --class org.toy.weather.simulation.forcast.ToyWeatherSimulation --master local[#No]  E:\Edu\Scala\workspace\weather_forcast\target\scala-2.11\weather_forcast_2.11-1.0-SNAPSHOT.jar  2016 12

Sample-Output
==============
Sydney|-33.86,151.21,39.0|2017-01-10T12:57:09Z|Sunny|+41.5|1000|74
Melbourne|-37.81,144.96,7.0|2017-01-10T12:57:09Z|Sunny|+41.3|1000|66
Adelaide|-34.92,138.61,48.0|2017-01-10T12:57:09Z|Sunny|+40.9|1000|46
Canberra|-35.25,149.13,599.0|2017-01-10T12:57:09Z|Sunny|+41.3|999.8|77
Brisbane|-27.47,153.02,150.0|2017-01-10T12:57:09Z|Sunny|+41.3|1000|69
Perth|-31.95,115.86,300.0|2017-01-10T12:57:09Z|Sunny|+37.4|999.9|82
Gold Coast|-28.0,153.43,6.0|2017-01-10T12:57:09Z|Sunny|+37.9|1000|69
Newcastle|54.97,-1.62,180.0|2017-01-10T12:57:09Z|Rainy|+22.1|999.9|593
Wollongong|-34.43,150.89,350.0|2017-01-10T12:57:09Z|Sunny|+41.4|999.9|78
Logan City|41.73,-111.84,64.0|2017-01-10T12:57:09Z|Sunny|+59.3|1000|964



 