import java.util.Date
import scala.math._

import org.junit.{Assert, Test, Ignore}

/////
// Our objective here is demonstrate how it works, from the convenience of
// a Maven command line.
//
// We call all tests sequentially under a single test in order to avoid that tests are
// started in parallel under certain circumstances.
//
// Assertions involving 'precision' are kept in this test class.
/////

class TestStats extends TestMatrixSupport( "src/test/resources/200attributes-1000instances.csv", 2, 1000 ) {

  private val precision = 0.000001F;

  @Test
  def simpleTests() : Unit = {
    testMean()
    testVariance()
    testStddev()
    testNormalize()
  }

  def testMean() : Unit = {
    print("+--- TestStats         :: mean        ... ")
    val s  = new Stats
    val start = new Date().getTime
    val m0 = s.mean(matrix(0))
    val m1 = s.mean(matrix(1))
    val elapsed = new Date().getTime - start
    println("%5dms  :: %d attributes :: %d instances".format(elapsed,attributes_, instances_))
    Assert.assertTrue(abs(0.5140871045F - m0) < precision)
    Assert.assertTrue(abs(0.4963106388F - m1) < precision)
  }

  def testVariance() : Unit = {
    print("+--- TestStats         :: variance    ... ")
    val s  = new Stats
    val start = new Date().getTime
    val m0 = s.mean(matrix(0))
    val m1 = s.mean(matrix(1))
    val v0 = s.variance(matrix(0), m0)
    val v1 = s.variance(matrix(1), m1)
    val elapsed = new Date().getTime - start
    println("%5dms  :: %d attributes :: %d instances".format(elapsed,attributes_, instances_))
    Assert.assertTrue(abs(0.5140871045F - m0) < precision)
    Assert.assertTrue(abs(0.4963106388F - m1) < precision)
  }

  def testStddev() : Unit = {
    print("+--- TestStats         :: stddev      ... ")
    val s  = new Stats
    val start = new Date().getTime
    val m0 = s.mean(matrix(0))
    val m1 = s.mean(matrix(1))
    val s0 = s.stddev(matrix(0), m0)
    val s1 = s.stddev(matrix(1), m1)
    val elapsed = new Date().getTime - start
    println("%5dms  :: %d attributes :: %d instances".format(elapsed,attributes_, instances_))
    Assert.assertTrue(abs(0.5140871045F - m0) < precision)
    Assert.assertTrue(abs(0.4963106388F - m1) < precision)
  }

  def testNormalize() : Unit = {
    print("+--- TestStats         :: normalize   ... ")
    val s  = new Stats
    val start = new Date().getTime
    val m0 = s.mean(matrix(0))
    val m1 = s.mean(matrix(1))
    val s0 = s.stddev(matrix(0), m0)
    val s1 = s.stddev(matrix(1), m1)
    val a0 = s.normalize(matrix(0), m0, s0)
    val a1 = s.normalize(matrix(1), m1, s1)
    val sum = a0.sum + a1.sum
    val elapsed = new Date().getTime - start
    println("%5dms  :: %d attributes :: %d instances".format(elapsed,attributes_, instances_))
    Assert.assertTrue(abs(0.5140871045F - m0) < precision)
    Assert.assertTrue(abs(0.4963106388F - m1) < precision)
  }

}
