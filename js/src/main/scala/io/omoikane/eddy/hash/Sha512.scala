package io.omoikane.eddy.hash

import io.omoikane.eddy.codec.Uint8ArrayConverter
import io.omoikane.eddy.impl.facades.TweetNaClJS
import io.omoikane.eddy.interfaces.crypto.CryptographicHash

object Sha512 extends CryptographicHash {
  def hashBytes(bytes: Array[Byte]): Array[Byte] =
    Uint8ArrayConverter.toBytes(TweetNaClJS.hash(Uint8ArrayConverter.fromBytes(bytes)))
}
