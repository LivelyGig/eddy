package io.omoikane.eddy.codec

import scala.scalajs.js.typedarray.Uint8Array

object Uint8ArrayConverter {

  def fromBytes(bytes: Array[Byte]): Uint8Array = {
    val output: Uint8Array = new Uint8Array(bytes.length)
    bytes.zipWithIndex.foreach {
      case (byte, index) =>
        output.set(index, (byte & 0xFF).toShort)
    }
    output
  }

  def toBytes(uint8Array: Uint8Array): Array[Byte] =
    (0 until uint8Array.length).map((index: Int) => uint8Array.get(index).toByte).toArray

}
