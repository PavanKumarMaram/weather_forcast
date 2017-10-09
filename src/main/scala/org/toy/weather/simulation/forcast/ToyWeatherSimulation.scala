package org.toy.weather.simulation.forcast

import org.toy.weather.simulation.data.CommonData
import org.toy.weather.simulation.data.DateOperations
import org.apache.spark.sql.SparkSession

object ToyWeatherSimulation {
  
  def main(args: Array[String]) {
    
   //It performs validation to ensure all mandatory parms are provided as command line input.  
  val yearAndMonth : List[Int] = CommonData.validateInput(args)
  
  var locations = CommonData.initLocation();
  var startDate = new DateOperations.DateTimeISO8601(yearAndMonth(0),yearAndMonth(1),10,(scala.util.Random).nextInt(24),(scala.util.Random).nextInt(61),(scala.util.Random).nextInt(61));
  for(l <- locations)
      {
        println(l + CommonData.defaultWriteSep +startDate+CommonData.defaultWriteSep+ PredictFunctions.getStat(l, startDate.getHour))
      }  
   }
}