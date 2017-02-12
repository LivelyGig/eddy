package io.omoikane.eddy.impl.facades

import scala.scalajs.js
import scala.scalajs.js.annotation.JSName
import scala.scalajs.js.typedarray.Uint8Array

@JSName("nacl")
@js.native
object TweetNaClJS extends js.Object {

  @js.native
  trait CryptographicKeyPair extends js.Object {
    def publicKey: Uint8Array
    def secretKey: Uint8Array
  }

  def hash(message: Uint8Array): Uint8Array = js.native

  @JSName("hash")
  @js.native
  object Hash extends js.Object {
    def apply(message: Uint8Array): Uint8Array = js.native
    def hashLength: Int = js.native
  }

  @JSName("sign")
  @js.native
  object Sign extends js.Object {

    def detached(msg: Uint8Array, secretKey: Uint8Array): Uint8Array = js.native

    @JSName("detached")
    @js.native
    object Detached extends js.Object {
      def verify(msg: Uint8Array, sig: Uint8Array, publicKey: Uint8Array): Boolean = js.native
    }

    def keyPair(): CryptographicKeyPair = js.native

    @JSName("keyPair")
    @js.native
    object KeyPair extends js.Object {
      def apply(): CryptographicKeyPair = js.native
      def fromSecretKey(seed: Uint8Array): CryptographicKeyPair = js.native
      def fromSeed(seed: Uint8Array): CryptographicKeyPair = js.native
    }
  }
}
