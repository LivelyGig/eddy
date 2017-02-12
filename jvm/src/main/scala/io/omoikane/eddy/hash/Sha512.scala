package io.omoikane.eddy.hash

import java.security.MessageDigest

import io.omoikane.eddy.interfaces.crypto.CryptographicHash

object Sha512 extends CryptographicHash {
  def hashBytes(bytes: Array[Byte]): Array[Byte] =
    MessageDigest.getInstance("SHA-512").digest(bytes)
}
