package com.capgemini.commons.ui.theme

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.capgemini.commons.R

object Fonts {
    val oswald = FontFamily(
        Font(R.font.oswald_light),
        Font(R.font.oswald_regular),
        Font(R.font.oswald_bold, FontWeight.Bold),
    )

    val poppins = FontFamily(
        Font(R.font.poppins_light),
        Font(R.font.poppins_regular),
        Font(R.font.poppins_bold, FontWeight.Bold),
    )
}