package com.rosberry.arc.common.repository.image

import android.graphics.Bitmap
import io.reactivex.Observable
import java.io.ByteArrayOutputStream
import java.io.File

/**
 * Created by Evgeniy Nagibin on 24/01/2018.
 */
object BitmapHelper {

    fun writeBitmap(bitmap: Bitmap?, dir: File): Observable<File> {
        return Observable.create {

            if (bitmap == null) {
                it.onError(NullPointerException("Bitmap is null. What are you doing?"))

            } else {

                val file = File(dir, CropImageConstants.CROPPED_IMAGE_TITLE + "_${System.currentTimeMillis()}")
                file.createNewFile()

                val byteStream = ByteArrayOutputStream()
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteStream)

                file.writeBytes(byteStream.toByteArray())

                it.onNext(file)
            }
        }
    }
}