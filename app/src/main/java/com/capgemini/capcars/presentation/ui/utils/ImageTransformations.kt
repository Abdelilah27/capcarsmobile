package com.capgemini.capcars.presentation.ui.utils

import android.graphics.Bitmap
import coil.size.Size
import coil.transform.Transformation

// Function to crop the left half of the Bitmap (first part of the width)
fun cropLeftBitmap(bitmap: Bitmap): Bitmap {
    val width = bitmap.width
    val height = bitmap.height
    val cropWidth = (width * 0.5).toInt() // Take the left half of the image
    return Bitmap.createBitmap(bitmap, 0, 0, cropWidth, height) // Crop the left side
}

// Transformation to crop the image using Coil
class CropTopTransformation : Transformation {
    override val cacheKey: String = "crop_top_transformation"

    override suspend fun transform(input: Bitmap, size: Size): Bitmap {
        return cropLeftBitmap(input)
    }
}