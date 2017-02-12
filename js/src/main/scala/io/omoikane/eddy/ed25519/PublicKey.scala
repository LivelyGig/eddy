package io.omoikane.eddy.ed25519

import io.omoikane.eddy.codec.Uint8ArrayConverter
import io.omoikane.eddy.impl.facades.TweetNaClJS
import io.omoikane.eddy.interfaces.crypto.CryptographicVerifier

import scala.scalajs.js.typedarray.Uint8Array

final case class PublicKey(bytes: Array[Byte]) extends CryptographicVerifier {
  private val publicKeyData: Uint8Array = Uint8ArrayConverter.fromBytes(bytes)
  def verifyHash(hash: Array[Byte], signature: Array[Byte]): Boolean =
    TweetNaClJS.Sign.Detached
      .verify(Uint8ArrayConverter.fromBytes(hash), Uint8ArrayConverter.fromBytes(signature), publicKeyData)
}

object PublicKey {
  def fromPrivateKey(privateKey: PrivateKey): PublicKey =
    PublicKey(Uint8ArrayConverter.toBytes(TweetNaClJS.Sign.KeyPair.fromSeed(Uint8ArrayConverter.fromBytes(privateKey.bytes)).publicKey))
}
