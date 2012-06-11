import scalacl._
import org.junit.{Assert, Test, Ignore}

/////
// see: https://github.com/ochafik/nativelibs4java/issues/209
/////

class Test209 {
  private implicit val context = Context.best
  println(context)

  def $mean(av: Array[CLArray[Float]]): Array[Float] = {
    av.map(v => {
      v.sum / v.length
    })
  }

  def mean(av: Array[Array[Float]]): Array[Float] = {
    $mean(av.map(v => {
      v.view.cl
    }))
  }

  @Test
  def testMean(): Unit = {
    val a = Array[Float](1.2F, 1.5F, 1.6F, 1.8F)
    val b = Array[Float](2.2F, 2.5F, 2.6F, 2.8F)
    val m = Array(a, b)
    mean(m).foreach(f => println("Test209.testMean :: f=%f".format(f)))
  }

}
