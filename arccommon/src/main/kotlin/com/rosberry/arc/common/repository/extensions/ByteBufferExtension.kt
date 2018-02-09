package com.rosberry.arc.common.repository.extensions

import java.nio.ByteBuffer

/**
 * Created by Evgeniy Nagibin on 02/11/2017.
 */
fun ByteBuffer.putByteArray(array: ByteArray): ByteBuffer {
    for (item in array) {
        this.put(item)
    }
    return this
}

fun ByteBuffer.putShortArray(array: IntArray): ByteBuffer {
    for (item in array) {
        this.putShort(item.toShort())
    }
    return this
}
