import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers
import scodec.bits.ByteVector
import snapshot4s.Repr
import snapshot4s.generated.*
import snapshot4s.scalatest.SnapshotAssertions

// initially you should run sbt snapshot4sPromote to generate snapshot
class MySuite extends AnyFunSpec, SnapshotAssertions, Matchers {
  describe("Module") {
    it("should emit lipsum -- inline snapshot") {
      assertInlineSnapshot(
        Module.lipsum,
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
      ) // this line is automatically rewritten. initially, you should write it as ???
    }

    it("should emit lipsum -- file snapshot") {
      // you should specify snapshot file name.
      // file is under src/test/resources/snapshot/
      assertFileSnapshot(Module.lipsum, "lipsum.txt")
    }
  }

  describe("JsonModule") {
    it("should return JSON -- inline snapshot") {
      assertInlineSnapshot(
        JsonModule.makeObject(10).toString,
        """{
  "fact" : 3628800,
  "power" : 100
}"""
      )
    }

    it("should return JSON -- file snapshot") {
      assertFileSnapshot(JsonModule.makeObject(10).toString, "json.json")
    }
  }

  describe("BinaryModule") {
    given Repr[ByteVector] =
      (bv: ByteVector) => s"""{ import scodec.bits.hex; hex"${bv.toHex}" }"""

    it("should return UTF-8 w/BOM -- inline snapshot") {
      assertInlineSnapshot(
        BinaryModule.withBOM("windymelt"), {
          import scodec.bits.hex
          hex"efbbbf77696e64796d656c74"
        }
      )
    }

    it("should return UTF-8 w/BOM -- file snapshot") {
      assertFileSnapshot(
        BinaryModule.withBOM("windymelt").toHexDump,
        "binary.txt"
      )
    }
  }
}
