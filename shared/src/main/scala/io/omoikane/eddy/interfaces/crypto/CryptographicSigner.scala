package io.omoikane.eddy.interfaces.crypto
import io.omoikane.eddy.hash.Sha512

@SuppressWarnings(Array("org.wartremover.warts.DefaultArguments"))
trait CryptographicSigner {
  def signHash(hash: Array[Byte]): Array[Byte]
  def signString(message: String, cryptographicHash: CryptographicHash = Sha512): Array[Byte] =
    signHash(cryptographicHash.hashString(message))
}
