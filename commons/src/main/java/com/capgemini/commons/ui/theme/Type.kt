package com.capgemini.commons.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.capgemini.commons.R

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = Fonts.poppins,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = Fonts.poppins,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.2.sp
    ),
)

val HeadlineLarge = TextStyle(
    fontSize = 30.sp,
    lineHeight = 36.sp,
    fontFamily = FontFamily(Font(R.font.poppins_bold)),
    fontWeight = FontWeight.Bold,
    color = OnPrimary
)

val HeadlineMedium = TextStyle(
    fontSize = 24.sp,
    lineHeight = 30.sp,
    fontFamily = FontFamily(Font(R.font.poppins_bold)),
    fontWeight = FontWeight.Bold,
    color = OnPrimary
)

val SubtitleLarge = TextStyle(
    fontSize = 24.sp,
    lineHeight = 30.sp,
    fontFamily = FontFamily(Font(R.font.oswald_light)),
    fontWeight = FontWeight.Normal,
    color = OnPrimary
)

val SubtitleSmall = TextStyle(
    fontSize = 20.sp,
    lineHeight = 24.sp,
    fontFamily = FontFamily(Font(R.font.oswald_light)),
    fontWeight = FontWeight.Normal,
    color = OnPrimary
)

val BodyMedium = TextStyle(
    fontSize = 14.sp,
    lineHeight = 16.sp,
    fontFamily = FontFamily(Font(R.font.poppins_regular)),
    fontWeight = FontWeight.Normal,
    color = OnPrimary
)

val BodySmall = TextStyle(
    fontSize = 12.sp,
    lineHeight = 18.sp,
    fontFamily = FontFamily(Font(R.font.poppins_regular)),
    fontWeight = FontWeight.Normal,
    color = OnPrimary
)