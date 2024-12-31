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

val HeadlineExtraLarge = TextStyle(
    fontSize = 38.sp,
    lineHeight = 46.sp,
    fontFamily = FontFamily(Font(R.font.poppins_bold)),
    fontWeight = FontWeight.Bold,
    color = OnPrimary
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

val SubtitleExtraLarge = TextStyle(
    fontSize = 40.sp,
    lineHeight = 48.sp,
    fontFamily = FontFamily(Font(R.font.oswald_light)),
    fontWeight = FontWeight.Bold,
    color = OnSurface
)

val SubtitleLightLarge = TextStyle(
    fontSize = 24.sp,
    lineHeight = 30.sp,
    fontFamily = FontFamily(Font(R.font.oswald_light)),
    fontWeight = FontWeight.Normal,
    color = OnPrimary
)

val SubtitleMedium = TextStyle(
    fontSize = 24.sp,
    lineHeight = 30.sp,
    fontFamily = FontFamily(Font(R.font.oswald_medium)),
    fontWeight = FontWeight.Medium,
    color = OnSurface
)

val SubtitleLightSmall = TextStyle(
    fontSize = 14.sp,
    lineHeight = 20.sp,
    fontFamily = FontFamily(Font(R.font.oswald_light)),
    fontWeight = FontWeight.Light,
    color = OnSurface
)

val SubtitleExtraSmall = TextStyle(
    fontSize = 14.sp,
    lineHeight = 18.sp,
    fontFamily = FontFamily(Font(R.font.oswald_medium)),
    fontWeight = FontWeight.Medium,
    color = LightGray
)

val BodySmall = TextStyle(
    fontSize = 12.sp,
    lineHeight = 18.sp,
    fontFamily = FontFamily(Font(R.font.poppins_regular)),
    fontWeight = FontWeight.Normal,
    color = OnPrimary
)

val PrimaryButtonText = TextStyle(
    fontSize = 14.sp,
    lineHeight = 24.sp,
    fontFamily = FontFamily(Font(R.font.oswald_medium)),
    fontWeight = FontWeight.Medium,
    color = OnSecondary
)