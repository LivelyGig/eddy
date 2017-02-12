package io.omoikane.eddy.ed25519

import org.scalatest._

class PublicKeyTest extends FreeSpec with Matchers {
  "Private Key [0,...,31]" - {
    val privateKey: PrivateKey = PrivateKey((0 until 32).map(_.toByte).toArray)
    val publicKey              = PublicKey.fromPrivateKey(privateKey)
    "Recover Public Key" in {
      publicKey.bytes should be(
        Array(3, -95, 7, -65, -13, -50, 16, -66, 29, 112, -35, 24, -25, 75, -64, -103, 103, -28, -42, 48, -101, -91, 13, 95, 29, -36, -122,
          100, 18, 85, 49, -72).map(_.toByte))
    }
    """"hello"""" - {
      "Sign" in {
        privateKey.signString("Hello") should be(
          Array(59, -63, 14, -100, -82, 120, 116, -66, 82, -74, -71, 68, -60, 114, 111, -108, -111, 54, 46, -100, -8, -115, 10, 117, -55,
            11, -112, -46, -126, -15, -17, 9, -5, 11, -81, -118, 122, 34, -67, -5, 63, -102, 16, -29, -48, 94, -84, 83, -44, 79, 24, -15,
            18, -51, -13, 28, -31, -52, 104, -35, 88, 127, -68, 4))
      }
      "Verify" in {
        publicKey.verifyString("Hello", privateKey.signString("Hello")) should be(true)
      }
      "Verify (sad path)" in {
        publicKey.verifyString("Foo", privateKey.signString("Hello")) should be(false)
      }
    }

    """"هم خدا را خواستن و هم خرما را‎‎"""" - {
      val message = "هم خدا را خواستن و هم خرما را‎‎"
      "Sign" in {
        privateKey.signString(message) should be(
          Array(82, 89, 65, 62, -28, 24, -89, 78, 122, -93, 125, 110, 85, -19, -80, -43, 33, -32, 12, 111, 57, 98, -104, -87, -48, 114,
            -117, 122, 55, 43, -73, -109, 100, -3, 74, -21, 60, 59, -34, 46, -80, 12, -51, 79, -2, 64, -66, 70, 32, 22, 22, -8, -60, 78,
            -67, 32, 117, 15, -86, 101, -107, -97, 66, 3))
      }
      "Verify" in {
        publicKey.verifyString(message, privateKey.signString(message)) should be(true)
      }
      "Verify (sad path)" in {
        publicKey.verifyString("Foo", privateKey.signString(message)) should be(false)
      }
    }
  }

}
