package io.omoikane.eddy.interfaces.crypto

import java.nio.charset.StandardCharsets.UTF_8

@SuppressWarnings(Array("org.wartremover.warts.Overloading"))
trait CryptographicHash {
  def hashBytes(input: Array[Byte]): Array[Byte]
  def hashString(input: String): Array[Byte] = hashBytes(input.getBytes(UTF_8))
}
