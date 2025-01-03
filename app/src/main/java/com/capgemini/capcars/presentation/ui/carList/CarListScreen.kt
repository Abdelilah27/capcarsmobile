package com.capgemini.capcars.presentation.ui.carList

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
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
import com.capgemini.capcars.presentation.ui.theme.LocalScreenWidthDp
import com.capgemini.capcars.presentation.ui.utils.CropTopTransformation
import com.capgemini.capcars.presentation.ui.utils.getFontSize
import com.capgemini.commons.ui.animation.AnimatedCardContent
import com.capgemini.commons.ui.components.CustomBackHeader
import com.capgemini.commons.ui.components.ErrorAlertDialog
import com.capgemini.commons.ui.components.LoadingIndicator
import com.capgemini.commons.ui.components.PrimaryButton
import com.capgemini.commons.ui.theme.BodyLight
import com.capgemini.commons.ui.theme.HeadlineExtraLarge
import com.capgemini.commons.ui.theme.LabelValue
import com.capgemini.commons.ui.theme.SubHeadlineExtraLarge
import com.capgemini.commons.ui.theme.SubHeadlineSmall
import com.capgemini.commons.ui.theme.Surface
import com.capgemini.commons.ui.theme.extraLargeFontSize
import com.capgemini.commons.ui.theme.extraLargeSpacing
import com.capgemini.commons.ui.theme.largeFontSize
import com.capgemini.commons.ui.theme.largeSpacing
import com.capgemini.commons.ui.theme.mediumFontSize
import com.capgemini.commons.ui.theme.mediumSpacing
import com.capgemini.commons.ui.theme.regularFontSize
import com.capgemini.commons.ui.theme.regularSpacing
import com.capgemini.commons.ui.theme.smallFontSize
import com.capgemini.commons.ui.theme.smallSpacing
import com.capgemini.commons.ui.theme.tinyFontSize
import com.capgemini.commons.ui.theme.tinySpacing
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import timber.log.Timber

@Composable
fun CarListScreen(
    carListState: CarListState,
    onBackClicked: () -> Unit,
    onRetryClicked: () -> Unit,
    onRefresh: () -> Unit
) {
    val context = LocalContext.current
    var showRetryDialog by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }
    var isRefreshing by remember { mutableStateOf(false) } // Tracks refresh state

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(largeSpacing)
                .fillMaxSize()
        ) {
            CustomBackHeader(onBackClicked = onBackClicked)
            Spacer(modifier = Modifier.height(extraLargeSpacing))
            CarListHeader()
            Spacer(modifier = Modifier.weight(1.1f))

            // Wrap LazyRow with SwipeRefresh
            SwipeRefresh(
                state = rememberSwipeRefreshState(isRefreshing),
                onRefresh = {
                    isRefreshing = true
                    onRefresh() // Trigger refresh action in the ViewModel
                }
            ) {
                when (carListState) {
                    is CarListState.Error -> {
                        LaunchedEffect(carListState) {
                            val errorResId = carListState.message.getMessageResource()
                            errorMessage = context.getString(errorResId)
                            showRetryDialog = true
                        }
                    }

                    CarListState.Loading -> {
                        LoadingIndicator(message = stringResource(R.string.loading_message))
                    }

                    CarListState.NoState -> {
                        Timber.tag("CarListScreen").d("NoState")
                    }

                    is CarListState.Success -> {
                        CarList(cars = carListState.cars)
                        isRefreshing = false // Set refreshing to false once data is loaded
                    }
                }
            }
        }
    }

    // Retry dialog
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
    Box {
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(mediumSpacing),
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .align(Alignment.BottomCenter) // Align at the bottom of the Box
        ) {
            items(cars) { car ->
                CarCard(car = car)
            }
        }
    }
}

@Composable
fun CarCard(car: CarItem) {
    // Dynamic width and height based on screen size
    val screenWidthDp = LocalConfiguration.current.screenWidthDp
    val screenHeightDp = LocalConfiguration.current.screenHeightDp
    val cardWidth = screenWidthDp.dp * 0.7f
    val cardHeight = screenHeightDp.dp * 0.64f

    CompositionLocalProvider(LocalScreenWidthDp provides screenWidthDp) {
        AnimatedCardContent(
            modifier = Modifier
                .padding(smallSpacing)
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

                Spacer(modifier = Modifier.height(if (screenWidthDp < 420) tinySpacing else regularSpacing))

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
        style = SubHeadlineExtraLarge.copy(
            fontSize = getFontSize(
                LocalScreenWidthDp.current,
                largeFontSize,
                extraLargeFontSize
            )
        ),
        modifier = Modifier.padding(start = mediumSpacing, end = mediumSpacing)
    )

    // Year and Model
    Text(
        text = "${car.year} - ${car.model}",
        style = SubHeadlineSmall.copy(
            fontSize = getFontSize(
                LocalScreenWidthDp.current,
                tinyFontSize,
                smallFontSize
            )
        ),
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
            style = BodyLight.copy(
                fontSize = getFontSize(
                    LocalScreenWidthDp.current,
                    tinyFontSize,
                    smallFontSize
                )
            )
        )

        Spacer(modifier = Modifier.weight(0.3f))

        // Centered value
        Text(
            text = value,
            style = LabelValue.copy(
                fontSize = getFontSize(
                    LocalScreenWidthDp.current,
                    regularFontSize,
                    mediumFontSize
                )
            ),
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
        // Asynchronously loads and displays image
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
        onClick = { /* Handle button click, e.g., navigate to car details */ },
        text = stringResource(id = R.string.see_this_vehicle),
        modifier = Modifier
            .padding(
                top = mediumSpacing,
                start = mediumSpacing,
                end = mediumSpacing,
                bottom = smallSpacing
            )
            .fillMaxWidth()
    )
}

@Preview(showBackground = true)
@Composable
private fun CarListScreenPreview() {
    CarListScreen(CarListState.NoState, {}, {}, {})
    CarListScreen(CarListState.Loading, {}, {}, {})
}