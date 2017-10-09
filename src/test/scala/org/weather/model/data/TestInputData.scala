import org.toy.weather.simulation.data.CommonData
import org.scalatest._


class TestInputData extends FlatSpec  {

  " validateInput method " should " throw IllegalArgumentException if arg size is wrong " in {
    val list = new Array[String]()
    AssertThrows[IllegalArgumentException]{
    list(0)
    }
    
  }
  
}