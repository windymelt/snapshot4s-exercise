object Module {
  def lipsum =
    """Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."""
}

object JsonModule {
  import io.circe.generic.auto.{*, given}
  import io.circe.syntax.{*, given}
  import io.circe.{*, given}

  case class JsonResult(fact: BigDecimal, power: BigDecimal)

  private def fact(n: BigDecimal): BigDecimal = n match {
    case n if n <= 1 => 1
    case n           => n * fact(n - 1)
  }

  private def power(n: BigDecimal) = n.pow(2)

  def makeObject(n: Int) = JsonResult(fact(n), power(n)).asJson
}

object BinaryModule {
  import scodec.Codec
  import scodec.bits.hex
  import scodec.bits.ByteVector

  val StringBOMCodec = scodec.codecs.utf8
  def withBOM(s: String): ByteVector =
    hex"EF BB BF" ++ scodec.codecs.utf8.encode(s).require.bytes
}

@main def run = {
  println(Module.lipsum)
  println(JsonModule.makeObject(10))
  println(BinaryModule.withBOM("windymelt"))
}
