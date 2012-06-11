import scala.math._
import scalacl._

class Stats {
  private implicit val context = Context.best

  /////
  // functions started with '$' take CLArrays
  // see: http://code.google.com/p/scalacl/wiki/ScalaCLCollections
  /////

  def $mean(v : CLArray[Float]) : Float = {
    v.sum / v.length
  }

  def $variance(v : CLArray[Float], m : Float) : Float = {
    v.par.map(x => { (x - m) * (x - m) } ).sum / v.length
  }

  def $stddev(v : CLArray[Float], m : Float) : Float = {
    sqrt( $variance(v, m) ).asInstanceOf[Float]
  }

  def $normalize(v : CLArray[Float], m : Float, s : Float) : CLArray[Float] = {
    v.par.map(x => { (x - m) / s }).toArray.cl
  }


  /////
  // functions not started with '$' take mundane Scala Arrays
  /////

  def mean(v : Array[Float]) : Float = { $mean(v.cl) }
  def variance(v : Array[Float], m : Float) : Float = { $variance(v.cl, m) }
  def stddev(v : Array[Float], m : Float) : Float = { $stddev(v.cl, m) }
  def normalize(v : Array[Float], m : Float, s : Float) : CLArray[Float] = { $normalize(v.cl, m, s) }
}
