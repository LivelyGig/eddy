package io.omoikane.eddy.interfaces.crypto
import io.omoikane.eddy.hash.Sha512

@SuppressWarnings(Array("org.wartremover.warts.DefaultArguments"))
trait CryptographicVerifier {
  def verifyHash(hash: Array[Byte], signature: Array[Byte]): Boolean
  def verifyString(message: String, signature: Array[Byte], cryptographicHash: CryptographicHash = Sha512): Boolean =
    verifyHash(cryptographicHash.hashString(message), signature)
}
