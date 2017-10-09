package org.toy.weather.simulation.data

object DateOperations {
  
  class DateTimeISO8601(year: Int=2016, month: Int=1, date: Int=1,hour: Int=0,minute: Int=0,second: Int=0) {
    if(year%4 == 0)
      CommonData.feb_days+= 1;
    var daysInMonths = Array(31, CommonData.feb_days, 31,30,31,30,31,31,30,31,30,31);

    if(month > 12 || date > daysInMonths(month-1)) {println("Wrong date");System.exit(1)}
    
    def getHour: Int = hour;

    //To make single digit numbers as double digit..
    def getString(value: Int) : String = {
      if(value < 10)
        return "0"+value;
      else
        return value+"";
    }

    override def toString: String = year+"-"+getString(month)+"-"+getString(date)+"T"+getString(hour)+":"+getString(minute)+":"+getString(second)+"Z";
  }

}