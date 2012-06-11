import scalacl._
import org.junit.{Assert, Test, Ignore}

/////
// see: https://github.com/ochafik/nativelibs4java/issues/209
/////

class Test216 {

  private implicit val context = Context.best

  // Using IndexedSeq

  def calc1(vx: IndexedSeq[Float], vy: IndexedSeq[Float], mx: Float, my: Float): Float = {
    //(vx zip vy).par.map( { case (x,y) => { (x - mx) * (y - my) } } ).sum
    (vx.cl zip vy.cl).par.map({
      case (x, y) => {
        (x - mx) * (y - my)
      }
    }).sum
  }

  @Test
  def testCalc1Array(): Unit = {
    val a = Array[Float](1.0F, 2.0F, 3.0F)
    val b = Array[Float](10.0F, 20.0F, 30.0F)

    val f = calc1(a, b, 2.0F, 20.0F)
    println("Test216.testCalc1Array   :: f=%f".format(f))
  }

  @Test
  def testCalc1CLArray(): Unit = {
    val a = Array[Float](1.0F, 2.0F, 3.0F)
    val b = Array[Float](10.0F, 20.0F, 30.0F)

    val f = calc1(a.cl, b.cl, 2.0F, 20.0F) //TODO FIXME:: this call fails :(
    println("Test216.testCalc1CLArray :: f=%f".format(f))
  }


  // Using CLArray

  def calc2(vx: CLArray[Float], vy: CLArray[Float], mx: Float, my: Float): Float = {
    (vx zip vy).par.map({
      case (x, y) => {
        (x - mx) * (y - my)
      }
    }).sum
  }

  @Test
  def testCalc2CLArray(): Unit = {
    val a = Array[Float](1.0F, 2.0F, 3.0F)
    val b = Array[Float](10.0F, 20.0F, 30.0F)

    val f = calc2(a.cl, b.cl, 2.0F, 20.0F)
    println("Test216.testCalc2CLArray :: f=%f".format(f))
  }

}
