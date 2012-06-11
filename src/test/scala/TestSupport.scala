import collection.mutable.ArrayBuffer

class TestSupport {

  protected def skipStressTests() : Boolean = {
    "true".equals(System.getProperty("skipStressTests"))
  }
}
