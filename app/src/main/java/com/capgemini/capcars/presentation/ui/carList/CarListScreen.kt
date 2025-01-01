package com.capgemini.capcars.presentation.ui.carList

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.capgemini.capcars.R
import com.capgemini.capcars.data.network.CarItem
import com.capgemini.capcars.presentation.ui.utils.CropTopTransformation
import com.capgemini.commons.ui.animation.AnimatedCardContent
import com.capgemini.commons.ui.components.ErrorAlertDialog
import com.capgemini.commons.ui.components.LoadingIndicator
import com.capgemini.commons.ui.components.PrimaryButton
import com.capgemini.commons.ui.theme.BodyLight
import com.capgemini.commons.ui.theme.HeadlineExtraLarge
import com.capgemini.commons.ui.theme.LabelValue
import com.capgemini.commons.ui.theme.Secondary
import com.capgemini.commons.ui.theme.SubHeadlineExtraLarge
import com.capgemini.commons.ui.theme.SubHeadlineSmall
import com.capgemini.commons.ui.theme.Surface
import com.capgemini.commons.ui.theme.extraLargeFontSize
import com.capgemini.commons.ui.theme.extraLargeSpacing
import com.capgemini.commons.ui.theme.iconSize
import com.capgemini.commons.ui.theme.largeCardHeight
import com.capgemini.commons.ui.theme.largeCardWidth
import com.capgemini.commons.ui.theme.largeFontSize
import com.capgemini.commons.ui.theme.largeSpacing
import com.capgemini.commons.ui.theme.mediumFontSize
import com.capgemini.commons.ui.theme.mediumSpacing
import com.capgemini.commons.ui.theme.regularFontSize
import com.capgemini.commons.ui.theme.regularSpacing
import com.capgemini.commons.ui.theme.smallCardHeight
import com.capgemini.commons.ui.theme.smallCardWidth
import com.capgemini.commons.ui.theme.smallFontSize
import com.capgemini.commons.ui.theme.smallSpacing
import com.capgemini.commons.ui.theme.tinyFontSize
import com.capgemini.commons.ui.theme.tinySpacing
import timber.log.Timber

@Composable
fun CarListScreen(
    carListState: CarListState,
    onBackClicked: () -> Unit,
    onRetryClicked: () -> Unit
) {
    val context = LocalContext.current

    var showRetryDialog by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(largeSpacing)
                .fillMaxSize()
        ) {
            ArrowIcon(onBackClicked)
            Spacer(modifier = Modifier.height(extraLargeSpacing))
            CarListHeader()
            Spacer(modifier = Modifier.weight(1.1f))

            when (carListState) {
                is CarListState.Error -> {
                    errorMessage = carListState.message.getMessage(context)
                    showRetryDialog = true
                    Timber.tag("CarListScreen").d("Error: $errorMessage")
                }

                CarListState.Loading -> {
                    LoadingIndicator(message = stringResource(R.string.loading_message))
                }

                CarListState.NoState -> {
                    Timber.tag("CarListScreen").d("NoState")
                }

                is CarListState.Success -> {
                    CarList(cars = carListState.cars)
                }
            }
        }
    }

    if (showRetryDialog) {
        ErrorAlertDialog(
            errorMessage = errorMessage,
            onRetry = {
                onRetryClicked()
                showRetryDialog = false
            },
            onDismiss = {
                onBackClicked()
                showRetryDialog = false
            }
        )
    }
}

@Composable
private fun ArrowIcon(onBackClicked: () -> Unit) {
    Icon(
        painter = painterResource(id = R.drawable.baseline_keyboard_backspace_24),
        contentDescription = "Back",
        tint = Secondary,
        modifier = Modifier
            .size(iconSize)
            .clickable { onBackClicked() }
    )
}

@Composable
private fun CarListHeader() {
    Column(horizontalAlignment = Alignment.Start) {
        Text(
            text = stringResource(R.string.title_see_available_vehicle),
            style = HeadlineExtraLarge
        )
    }
}

@Composable
private fun CarList(cars: List<CarItem>) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(mediumSpacing),
    ) {
        items(cars) { car ->
            CarCard(car = car)
        }
    }
}

@Composable
fun CarCard(car: CarItem) {
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxWidth()
            .padding(smallSpacing)
    ) {
        // Dynamic width and height based on screen size
        val cardWidth = if (maxWidth < largeCardWidth) smallCardWidth else largeCardWidth
        val cardHeight = if (maxHeight < smallCardHeight) smallCardHeight else largeCardHeight

        AnimatedCardContent(
            modifier = Modifier
                .width(cardWidth)
                .height(cardHeight)
        ) { modifier ->
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top,
                modifier = modifier
                    .fillMaxSize()
                    .background(Surface)
                    .padding(top = smallSpacing, bottom = smallSpacing)
            ) {
                CarInfoSection(car)

                Spacer(modifier = Modifier.height(if (LocalConfiguration.current.screenWidthDp < 450) tinySpacing else regularSpacing))

                // Performance Section (MPG, HP, Perf)
                PerformanceSection(car)

                // Car Image
                CarImage(imageUrl = car.image)

                Spacer(modifier = Modifier.weight(1f))

                // Adjusted button
                CarActionButton()
            }
        }
    }
}

@Composable
private fun CarInfoSection(car: CarItem) {
    // Car Brand
    Text(
        text = car.brand,
        style = SubHeadlineExtraLarge.copy(fontSize = if (LocalConfiguration.current.screenWidthDp < 450) largeFontSize else extraLargeFontSize),
        modifier = Modifier.padding(start = mediumSpacing, end = mediumSpacing)
    )

    // Year and Model
    Text(
        text = "${car.year} - ${car.model}",
        style = SubHeadlineSmall.copy(fontSize = if (LocalConfiguration.current.screenWidthDp < 450) tinyFontSize else smallFontSize),
        modifier = Modifier.padding(start = mediumSpacing, end = mediumSpacing)
    )
}

@Composable
private fun PerformanceSection(car: CarItem) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(smallSpacing),
        modifier = Modifier
            .fillMaxWidth(0.7f)
            .padding(top = mediumSpacing, start = mediumSpacing, end = mediumSpacing)
    ) {
        PerformanceMetric(
            iconRes = R.drawable.baseline_local_gas_station_24,
            label = stringResource(R.string.mpg_label),
            value = car.mpg
        ) // MPG
        PerformanceMetric(
            iconRes = R.drawable.baseline_auto_graph_24,
            label = stringResource(R.string.hp_label),
            value = car.hp.toString()
        ) // HP
        PerformanceMetric(
            iconRes = R.drawable.baseline_speed_24,
            label = stringResource(R.string.zero_to_sixty_label),
            value = car.processedPerf
        ) // 0-60
    }
}

@Composable
fun PerformanceMetric(iconRes: Int, label: String, value: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = null,
            modifier = Modifier.size(largeSpacing)
        )

        Spacer(modifier = Modifier.width(smallSpacing))

        Text(
            text = label,
            style = BodyLight.copy(fontSize = if (LocalConfiguration.current.screenWidthDp < 450) tinyFontSize else smallFontSize)
        )

        Spacer(modifier = Modifier.weight(0.3f))

        // Centered value
        Text(
            text = value,
            style = LabelValue.copy(fontSize = if (LocalConfiguration.current.screenWidthDp < 450) regularFontSize else mediumFontSize),
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }
}

@Composable
private fun CarImage(imageUrl: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .transformations(CropTopTransformation())
                .build(),
            contentDescription = null,
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
private fun CarActionButton() {
    PrimaryButton(
        onClick = { /* Handle button click, e.g., navigate to vehicle details */ },
        text = stringResource(id = R.string.see_this_vehicle),
        modifier = Modifier
            .padding(start = mediumSpacing, end = mediumSpacing, bottom = smallSpacing)
            .fillMaxWidth()
            .heightIn(min = 48.dp)
    )
}

@Preview(showBackground = true)
@Composable
private fun CarListScreenPreview() {
    CarListScreen(CarListState.NoState, {}, {})
    CarListScreen(CarListState.Loading, {}, {})
}