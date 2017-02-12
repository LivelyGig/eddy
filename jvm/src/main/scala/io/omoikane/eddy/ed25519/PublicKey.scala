package io.omoikane.eddy.ed25519

import io.omoikane.eddy.ed25519.TweetNaClJVM.Signature
import io.omoikane.eddy.interfaces.crypto.CryptographicVerifier

final case class PublicKey(bytes: Array[Byte]) extends CryptographicVerifier {
  private val verifier: Signature = new Signature(bytes, Array.empty[Byte])
  def verifyHash(hash: Array[Byte], signature: Array[Byte]): Boolean =
    verifier.detached_verify(hash, signature)
}

object PublicKey {
  def fromPrivateKey(privateKey: PrivateKey): PublicKey =
    PublicKey(TweetNaClJVM.Signature.keyPair_fromSeed(privateKey.bytes).getPublicKey)
}
