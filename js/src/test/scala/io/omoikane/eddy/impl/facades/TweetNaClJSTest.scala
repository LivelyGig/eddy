package io.omoikane.eddy.impl.facades

import io.omoikane.eddy.impl.facades.TweetNaClJS.CryptographicKeyPair

import scala.scalajs.js
import org.scalatest.{FreeSpec, Matchers}

import scala.scalajs.js.typedarray.Uint8Array

class TweetNaClJSTest extends FreeSpec with Matchers {
  def uint8ArrayToByteArray(uint8Array: Uint8Array): Array[Byte] =
    (0 until uint8Array.length).toArray.map((index: Int) => uint8Array.get(index).toByte)

  "hashLength is 64" in {
    TweetNaClJS.Hash.hashLength should be(64)
  }

  "NaCl.hash([1,2,3])" in {
    uint8ArrayToByteArray(TweetNaClJS.hash(new Uint8Array(js.Array(1, 2, 3)))) should be(
      Array(39, -122, 76, -59, 33, -102, -107, 26, 122, 110, 82, -72, -56, -35, -33, 105, -127, -48, -104, -38, 22, 88, -39, 98, 88, -56,
        112, -78, -56, -115, -5, -53, 81, -124, 26, -22, 23, 42, 40, -70, -6, 106, 121, 115, 17, 101, 88, 70, 119, 6, 96, 69, -55, 89, -19,
        15, -103, 41, 104, -115, 4, -34, -4, 41).map(_.toByte))
  }

  "NaCl.Hash.apply([1,2,3])" in {
    uint8ArrayToByteArray(TweetNaClJS.Hash(new Uint8Array(js.Array(1, 2, 3)))) should be(
      Array(39, -122, 76, -59, 33, -102, -107, 26, 122, 110, 82, -72, -56, -35, -33, 105, -127, -48, -104, -38, 22, 88, -39, 98, 88, -56,
        112, -78, -56, -115, -5, -53, 81, -124, 26, -22, 23, 42, 40, -70, -6, 106, 121, 115, 17, 101, 88, 70, 119, 6, 96, 69, -55, 89, -19,
        15, -103, 41, 104, -115, 4, -34, -4, 41).map(_.toByte))
  }

  "Random NaCl.Sign.KeyPair test" in {
    val keyPair: CryptographicKeyPair = TweetNaClJS.Sign.keyPair()

    uint8ArrayToByteArray(TweetNaClJS.Sign.KeyPair.fromSecretKey(keyPair.secretKey).publicKey) should be(uint8ArrayToByteArray(keyPair.publicKey))
  }
}
