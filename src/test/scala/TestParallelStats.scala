import java.util.Date
import scala.math._

import org.junit.{Assert, Test, Ignore}
import collection.mutable.ArrayBuffer

/////
// Our objective here is demonstrate how it works, from the convenience of
// a Maven command line.
//
// We call all tests sequentially under a single test in order to avoid that tests are
// started in parallel under certain circumstances.
//
// Assertions involving 'precision' were intentionally removed.
/////

class TestParallelStats extends TestMatrixSupport( "src/test/resources/200attributes-1000instances.csv", 200, 1000 ) {

  private val precision = 0.000001F;

  @Test
  def bulkTests() : Unit = {
    testMean()
    testVariance()
    testStddev()
    testNormalize()
  }

  def testMean() : Unit = {
    print("+--- TestParallelStats :: mean        ... ")
    val s = new ParallelStats
    val start = new Date().getTime
    val a = s.mean(matrix)
    val elapsed = new Date().getTime - start
    println("%5dms  :: %d attributes :: %d instances".format(elapsed,attributes_,instances_))
  }

  def testVariance() : Unit = {
    print("+--- TestParallelStats :: variance    ... ")
    val s = new ParallelStats
    val start = new Date().getTime
    val a = s.variance(matrix)
    val elapsed = new Date().getTime - start
    println("%5dms  :: %d attributes :: %d instances".format(elapsed,attributes_,instances_))
  }

  def testStddev() : Unit = {
    print("+--- TestParallelStats :: stddev      ... ")
    val s = new ParallelStats
    val start = new Date().getTime
    val a = s.stddev(matrix)
    val elapsed = new Date().getTime - start
    println("%5dms  :: %d attributes :: %d instances".format(elapsed,attributes_,instances_))
  }

  def testNormalize() : Unit = {
    print("+--- TestParallelStats :: normalize   ... ")
    val s = new ParallelStats
    val start = new Date().getTime
    val a = s.normalize(matrix)
    val elapsed = new Date().getTime - start
    val sum = a(0).sum + a(1).sum
    println("%5dms  :: %d attributes :: %d instances".format(elapsed,attributes_,instances_))
  }

}
