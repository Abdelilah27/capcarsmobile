package com.capgemini.capcars.presentation.ui.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.capgemini.capcars.R
import com.capgemini.commons.ui.theme.BodySmall
import com.capgemini.commons.ui.theme.GradientEnd
import com.capgemini.commons.ui.theme.GradientStart
import com.capgemini.commons.ui.theme.HeadlineExtraLarge
import com.capgemini.commons.ui.theme.OnPrimary
import com.capgemini.commons.ui.theme.SubtitleLightLarge
import com.capgemini.commons.ui.theme.Surface
import com.capgemini.commons.ui.theme.dividerThickness
import com.capgemini.commons.ui.theme.hugeSpacing
import com.capgemini.commons.ui.theme.iconSize
import com.capgemini.commons.ui.theme.largeSpacing
import com.capgemini.commons.ui.theme.smallSpacing
import com.capgemini.commons.ui.theme.tinySpacing

@Composable
fun OnboardingScreen(onNavigate: () -> Unit) {
    val gradient = Brush.verticalGradient(
        colors = listOf(GradientStart, GradientEnd)
    )
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(gradient)
    ) { paddingValues ->
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .padding(paddingValues) // TODO SCAT
                .padding(24.dp)
                .fillMaxSize()
        ) {
            OnboardingContent()
            Spacer(modifier = Modifier.weight(1f))
            OnboardingFooter(onNavigate)
        }
    }
}

@Composable
private fun OnboardingContent() {
    // Title Section
    Column(horizontalAlignment = Alignment.Start) {
        OnboardingTitle()
        OnboardingSubtitle()
        OnboardingBodyText()
    }
}

@Composable
private fun OnboardingTitle() {
    Text(
        text = stringResource(R.string.title_check_vehicle_availability),
        style = HeadlineExtraLarge
    )
}

@Composable
private fun OnboardingSubtitle() {
    Spacer(modifier = Modifier.height(hugeSpacing))
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            painter = painterResource(id = R.drawable.baseline_arrow_right_alt_24),
            contentDescription = null,
            tint = OnPrimary,
            modifier = Modifier.size(iconSize)
        )
        Spacer(modifier = Modifier.width(smallSpacing))
        Text(
            text = stringResource(R.string.subtitle_explore_car_models),
            style = SubtitleLightLarge
        )
    }
}

@Composable
private fun OnboardingBodyText() {
    Spacer(modifier = Modifier.height(largeSpacing))
    Text(
        text = stringResource(R.string.body_car_availability_info),
        style = BodySmall
    )
}

@Composable
private fun OnboardingFooter(onNavigate: () -> Unit) {
    // Car Image
    Image(
        painter = painterResource(id = R.drawable.onboarding_porsche),
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    )

    // Divider
    Divider(
        color = Surface,
        thickness = dividerThickness,
        modifier = Modifier.fillMaxWidth()
    )

    Spacer(modifier = Modifier.height(largeSpacing))

    // Sign-In Section
    OnboardingSignInSection(onNavigate)
}

@Composable
private fun OnboardingSignInSection(onNavigate: () -> Unit) {
    Column(horizontalAlignment = Alignment.Start) {
        Text(
            text = stringResource(R.string.subtitle_browse_car_collection),
            style = BodySmall
        )
        Spacer(modifier = Modifier.height(tinySpacing))
        SignInRow(onNavigate)
    }
}

@Composable
private fun SignInRow(onNavigate: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable {
            onNavigate()
        }
    ) {
        Text(
            text = stringResource(R.string.subtitle_explore_car_models),
            style = BodySmall
        )
        Spacer(modifier = Modifier.width(smallSpacing))
        Icon(
            painter = painterResource(id = R.drawable.baseline_arrow_right_alt_24),
            contentDescription = null,
            tint = OnPrimary,
            modifier = Modifier.size(iconSize)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun OnboardingScreenPreview() {
    OnboardingScreen(onNavigate = {})
}