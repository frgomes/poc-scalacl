import scalacl._
import scala.math._

import java.util.Date

class ParallelStats {
  private implicit val context = Context.best


  /////
  // functions started with '$' take CLArrays
  // see: http://code.google.com/p/scalacl/wiki/ScalaCLCollections
  /////


  def $mean(av : Array[CLArray[Float]]) : CLArray[Float] = {
    av.map(
      v => { v.sum / v.length }
    ).cl
  }

  def $variance(av : Array[CLArray[Float]], am : CLArray[Float]) : CLArray[Float] = {
    (av zip am).map(
      { case (v,m) => { v.map( x => { (x - m) * (x - m) } ).sum / v.length } }
    ).cl
  }

  def $stddev(av : Array[CLArray[Float]], am : CLArray[Float]) : CLArray[Float] = {
    (av zip am).map(
      { case (v,m) => { sqrt( v.map( x => { (x - m) * (x - m) } ).sum / v.length ).asInstanceOf[Float] } }
    ).cl
  }

  def $normalize(av : Array[CLArray[Float]], am : CLArray[Float], as : CLArray[Float]) : Array[CLArray[Float]] = {
    (av zip (am zip as)).map(
      { case (v, (m, s)) => { v.map( x => { (x - m) / s } ).cl } }
    )
  }


  /////
  // functions not started with '$' take mundane Scala Arrays
  /////

  def mean(av : Array[Array[Float]]) : Array[Float] = {
    val pv : Array[CLArray[Float]] = av.map(v => { v.cl })
    $mean(pv).toArray
  }

  def variance(av : Array[Array[Float]]) : Array[Float] = {
    val pv : Array[CLArray[Float]] = av.map(v => { v.cl })
    val am = $mean(pv)
    $variance(pv, am).toArray
  }

  def stddev(av : Array[Array[Float]]) : Array[Float] = {
    val pv : Array[CLArray[Float]] = av.map(v => { v.cl })
    val am = $mean(pv)
    $stddev(pv, am).toArray
  }

  def normalize(av : Array[Array[Float]]) : Array[Array[Float]] = {
    val pv : Array[CLArray[Float]] = av.map(v => { v.cl })
    val am = $mean(pv)
    val as = $stddev(pv, am)
    $normalize(pv, am, as).map(v => { v.toArray } ).toArray
  }

}
