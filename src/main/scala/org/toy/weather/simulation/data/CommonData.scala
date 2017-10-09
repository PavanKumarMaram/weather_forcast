package org.toy.weather.simulation.data

import scala.io.Codec
import java.time.{ZonedDateTime ,ZoneOffset }
import scala.util.Try
import java.util.Calendar

/**
 * @author Pavan Kumar Maram
 * @written 6 Oct 2017
 * @description
 * Object to hold some of the Common data structures & constants used in various places of the application.
 */

object CommonData {
  
  
  def validateInput(inputData : Array[String]) : List[Int]= {
    
    if (inputData.size != 2) {
      throw new  IllegalArgumentException("Number of args must be 2!!") ;
      System.exit(1);
    }
    
   if (Try(inputData(0).toInt).isFailure ||  Try(inputData(1).toInt).isFailure ) {
      throw new NumberFormatException("Year and month should be numeric ");
      System.exit(1);
    }
   
     
    val year : Int  = inputData(0).toInt
    val month : Int = inputData(1).toInt
   
    // year range check( 2000 to 2017 )
    // month range check( 1 to 12)
        if( year < 2000 || year > 2017 ){
          throw new IllegalArgumentException("Year value must be between 2000 and 2017 ")
          System.exit(1)
        }
        if(month <1 || month >12){
          throw new IllegalArgumentException("month value must be between 1 to 12")
          System.exit(1)
        }
        
        if(Calendar.YEAR == year){
          if(Calendar.MONTH < month){
          throw new IllegalArgumentException("month value must not be future")
          }
        
        }
  // return year and month values
    List(year , month)
    
  }
  
  //Bureau of Meterology sample Urls to get the data in csv format 
  
 /*  
Main Urls
==========
Canberra	- http://www.bom.gov.au/climate/dwo/201709/html/IDCJDW2801.201709.shtml
Mount Ginini	- http://www.bom.gov.au/climate/dwo/201709/html/IDCJDW2804.201709.shtml
Tuggeranong	- http://www.bom.gov.au/climate/dwo/201709/html/IDCJDW2802.201709.shtml
Sydney		- http://www.bom.gov.au/climate/dwo/201709/html/IDCJDW2124.201709.shtml
Melbourne	- http://www.bom.gov.au/climate/dwo/201709/html/IDCJDW3050.201709.shtml
Adelaide(Kent Town)-http://www.bom.gov.au/climate/dwo/201709/html/IDCJDW5002.201709.shtml


CSV data urls
==============
Canberra	- http://www.bom.gov.au/climate/dwo/201709/text/IDCJDW2801.201709.csv
Mount Ginini	- http://www.bom.gov.au/climate/dwo/201709/text/IDCJDW2804.201709.csv
Tuggeranong	- http://www.bom.gov.au/climate/dwo/201709/text/IDCJDW2802.201709.csv
*/

  
  val bomBaseUrl = "http://www.bom.gov.au/climate/dwo/" //url prefix for bom data.
  val bomFileCodec = Codec("windows-1252") //Codec of bom data file.

  val defaultFieldSep = "\\|" //psv field separator when reading & splitting.
  val defaultWriteSep = "|" //pipe separator when writing 
  val bomFieldSep = "," //Field separator for bom observations data.

  //Date range where the bom data is available. This can be overridden by dateRange command line param.
  val defaultDateRange = "201705|201710"
  
  var feb_days=28;
  
  
   //Topological location using elevation as well
  class TopoLoc(name: String,latitude: Double,longitude: Double,elevation: Double) {
    def getName(): String = return name;
    def getLatitude():Double = return latitude;
    def getLongitude():Double = return longitude;
    def getElevation():Double = return elevation;

    override def toString : String = name + defaultWriteSep + latitude+ bomFieldSep + longitude+ bomFieldSep +elevation
  }
  
  
  
  // Australia each city Geographic Information can get from
  // https://www.latlong.net/
  //https://www.latlong.net/place/sydney-opera-house-sydney-nsw-australia-3894.html
    
   def initLocation(): List[TopoLoc] = {
    return List(
      new TopoLoc("Sydney",-33.86,151.21,39),
      new TopoLoc("Melbourne",-37.81,144.96,7),
      new TopoLoc("Adelaide",-34.92,138.61,48),
      new TopoLoc("Canberra",-35.25,149.13,599),            
      new TopoLoc("Brisbane",-27.47,153.02,150),
      new TopoLoc("Perth",-31.95,115.86,300),      
      new TopoLoc("Gold Coast",-28,153.43,6),      
      new TopoLoc("Newcastle",54.97,-1.62,180),
      new TopoLoc("Wollongong",-34.43,150.89,350),
      new TopoLoc("Logan City",41.73,-111.84,64)
    );
  }
  
}