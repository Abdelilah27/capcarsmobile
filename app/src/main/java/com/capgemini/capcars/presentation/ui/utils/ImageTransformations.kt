package com.capgemini.capcars.presentation.ui.utils

import android.graphics.Bitmap
import coil.size.Size
import coil.transform.Transformation

// Function to crop the right half of the Bitmap (first part of the width)
fun cropLeftBitmap(bitmap: Bitmap): Bitmap {
    val width = bitmap.width
    val height = bitmap.height
    val cropWidth = (width * 0.5).toInt() // Take the right half of the image
    return Bitmap.createBitmap(bitmap, 0, 0, cropWidth, height) // Crop the left side
}

/**
 * Custom Coil transformation to crop the top half of the image.
 * This transformation crops the right half of the image when applied.
 */

class CropTopTransformation : Transformation {
    override val cacheKey: String = "crop_top_transformation"

    /**
     * Transforms the input bitmap by cropping the left half of the image.
     *
     * @param input The original bitmap to be transformed.
     * @param size The target size for the image (not used in this transformation).
     * @return The transformed bitmap, which is the left half of the original bitmap.
     */

    override suspend fun transform(input: Bitmap, size: Size): Bitmap {
        return cropLeftBitmap(input)
    }
}