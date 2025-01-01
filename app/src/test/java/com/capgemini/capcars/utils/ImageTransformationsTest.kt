package com.capgemini.capcars.utils

import android.graphics.Bitmap
import coil.size.Size
import com.capgemini.capcars.presentation.ui.utils.CropTopTransformation
import com.capgemini.capcars.presentation.ui.utils.cropLeftBitmap
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.mockkStatic
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CropLeftBitmapTest {

    @Before
    fun setup() {
        // Mock static method createBitmap in Bitmap
        mockkStatic(Bitmap::class)
    }

    @Test
    fun `should crop the left half of the bitmap`() {
        // Given: a sample bitmap with known width and height
        val originalBitmap = mockk<Bitmap>()
        val width = 200
        val height = 100
        coEvery { originalBitmap.width } returns width
        coEvery { originalBitmap.height } returns height

        // Mocking Bitmap.createBitmap to return a cropped bitmap with half the width
        val croppedBitmap = mockk<Bitmap>()
        coEvery {
            Bitmap.createBitmap(
                originalBitmap,
                0,
                0,
                width / 2,
                height
            )
        } returns croppedBitmap

        // When: we call cropLeftBitmap
        val result = cropLeftBitmap(originalBitmap)

        // Then: the result should be the mocked croppedBitmap
        assertEquals(croppedBitmap, result)
    }
}

class CropTopTransformationTest {

    private val cropTopTransformation = CropTopTransformation()

    @Before
    fun setup() {
        mockkStatic(Bitmap::class)
    }

    @Test
    fun `should apply cropLeftBitmap transformation`() = runTest {

        val originalBitmap = mockk<Bitmap>()
        val width = 200
        val height = 100
        coEvery { originalBitmap.width } returns width
        coEvery { originalBitmap.height } returns height

        // Mocking Bitmap.createBitmap to return a cropped bitmap with half the width
        val transformedBitmap = mockk<Bitmap>()
        coEvery {
            Bitmap.createBitmap(
                originalBitmap,
                0,
                0,
                width / 2,
                height
            )
        } returns transformedBitmap

        val result = cropTopTransformation.transform(originalBitmap, Size(width, height))

        assertEquals(transformedBitmap, result)
    }
}