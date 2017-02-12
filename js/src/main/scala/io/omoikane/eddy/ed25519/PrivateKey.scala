package io.omoikane.eddy.ed25519

import io.omoikane.eddy.codec.Uint8ArrayConverter
import io.omoikane.eddy.impl.facades.TweetNaClJS
import io.omoikane.eddy.interfaces.crypto.CryptographicSigner

import scala.scalajs.js.typedarray.Uint8Array

final case class PrivateKey(bytes: Array[Byte]) extends CryptographicSigner {
  private val privateKeyData: Uint8Array = TweetNaClJS.Sign.KeyPair.fromSeed(Uint8ArrayConverter.fromBytes(bytes)).secretKey
  def signHash(hash: Array[Byte]): Array[Byte] =
    Uint8ArrayConverter.toBytes(TweetNaClJS.Sign.detached(Uint8ArrayConverter.fromBytes(hash), privateKeyData))
}
