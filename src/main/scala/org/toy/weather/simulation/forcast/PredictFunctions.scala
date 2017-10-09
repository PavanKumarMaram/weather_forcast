package org.toy.weather.simulation.forcast

import org.toy.weather.simulation.data.CommonData
import java.text.DateFormat
import java.text.DecimalFormat

object PredictFunctions {
  
    // Get sun intensity based upon the time of the day
  // It is highest at 12 noon.. 100% intensity
  def getSunIntensity(hour: Int): Double = {
    return 100.0 - (math.abs(hour - 12) * 100.0/12.0);
  }
  
  // Assuming, farther the place from the ocean, less humid it would be..
  // so from a central point (-22.5,135) calculating distance of other locations
  // closer to the center would mean less humidity.
  def oceanDistanceAffect(location: CommonData.TopoLoc) : Double = {
    var lat_cent: Double = -22.5;
    var long_cent: Double = 135;
    var dist: Double = math.sqrt(math.pow(lat_cent - location.getLatitude(),2)+math.pow(long_cent- location.getLongitude(),2));
    var variance : Double = (scala.util.Random).nextInt(50)/10;
    //just substracted some random variance
    return (dist*100/22 - variance);
  }
  
  // calculating humidity by giving weightage of 5:1 to closeness to ocean and
  // elevation above the sea level
  def getHumidity(location: CommonData.TopoLoc) : Double = {
    return (5*oceanDistanceAffect(location) + 1*(location.getElevation()/1500)*100)/6;
  }
  
   // calculating pressure by assuming a mean of 1000 that gets displaced by temperature,
  // wind + humidity and elevation from the ground.. all of these affect it inversely..
  // so we substract fractions of them from 1000
  // elevation is normalized by 1500 m so that we dont get undue contribution from low elevations
  // even 1500 is low yet it works ok in our case..
  //it is assumed that 0.7 fraction of temperature, 0.7 fraction of combined effect of wind+ humidity
  // and 0.05 of normalized elevation decrease the pressure somewhat from 1000 units
  def getPressure(temp: Double, humid: Double, elevation: Double): Double = {
    return 1000 - (temp*(70/100) + getWindEffect*humid*(70/100) + (elevation/1500)*50/100)
  }
  
  
  // Get wind contribution in affecting temperature and pressure.. this is random value
  // to give us some variance
  // wind + humidity usually decreases the temperature.. so used it for some randomness
  // in data
  def getWindEffect : Double = (scala.util.Random).nextInt(20)/10;

  
  def getStat(location: CommonData.TopoLoc,hour: Int): String = {
    
   var sun : Double = getSunIntensity(hour);
   
    var humidity : Double = getHumidity(location);
    
    // assuming that 4/50th of suns intensity helps in increasing temperature and 1/50 of the total
    // affect of humidity is assumed so as to give us better looking temperature values..
    // still these values would be  very high if we don't consider the effects of phenomenons
    // that may decrease the temperature those are elevation from the sea level (normalized) and himidity+wind
    // causing the temperature to fall
    var temperature : Double = ((4.0*sun) + (1.0*humidity)/5.0)*(10.0/100.0) - (humidity*getWindEffect*5.0/100.0 + (location.getElevation/1500.0)*(70.0/100.0));
    var pressure : Double = getPressure(temperature,humidity,location.getElevation);

    var cloudStatus : String = "Sunny";
    if(temperature < 0)
      cloudStatus = "Snow";
    else if(temperature < 15)
    {
      if(humidity > 60)
        cloudStatus = "Rainy";
    }
    else if(temperature < 30)
    {
      if(humidity > 80)
        cloudStatus = "Rainy";
    }
    else
      cloudStatus = "Sunny";
    val formatter = new DecimalFormat("####.#");
    return cloudStatus+CommonData.defaultWriteSep+ (if(temperature>0)"+" else "-")+formatter.format(temperature)+ CommonData.defaultWriteSep +formatter.format(pressure)+"|"+(humidity.toInt);
  }

}