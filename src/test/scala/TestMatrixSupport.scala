import java.io.FileInputStream
import com.Ostermiller.util.CSVParser
import collection.mutable.ArrayBuffer
import java.util.Date

class TestMatrixSupport(val file : String, val attributes : Int = 100, val instances : Int = 1000) extends TestSupport {

  protected val attributes_ : Int = System.getProperty("attributes", attributes.toString).toInt
  protected val instances_  : Int = System.getProperty("instances", instances.toString).toInt
  protected val limit_      : Int = System.getProperty("limit",   "10").toInt
  protected val priming_    : Int = System.getProperty("priming", "10").toInt
  protected val stress_     : Int = System.getProperty("stress", "100").toInt
  protected val matrix      : Array[Array[Float]] = Array.fill[Float](attributes_,instances_)(0.0F)

  private val csv = new CSVParser(new FileInputStream(file))
  for (row <- 0 until instances_) {
    val line = csv.getLine
    for (col <- 0 until attributes_) {
      matrix(col)(row) = line(col).toFloat
    }
  }
  csv.close()


  protected def dump( m : Array[Array[Float]] ) : Unit = {
    val cols = m.length
    val rows = m(0).length
    if (cols <= limit_) {
      for (row <- 0 until rows) {
        for (col <- 0 until cols) {
          print("        ")
          print(m(col)(row))
        }
        println()
      }
    }
  }

}
