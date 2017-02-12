package io.omoikane.eddy.ed25519

import io.omoikane.eddy.ed25519.TweetNaClJVM.Signature
import io.omoikane.eddy.interfaces.crypto.CryptographicSigner

final case class PrivateKey(bytes: Array[Byte]) extends CryptographicSigner {
  private val signer: Signature = new Signature(Array.empty, Signature.keyPair_fromSeed(bytes).getSecretKey)
  def signHash(hash: Array[Byte]): Array[Byte] =
    signer.detached(hash)
}
