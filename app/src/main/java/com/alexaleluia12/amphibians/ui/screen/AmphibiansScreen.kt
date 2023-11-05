package com.alexaleluia12.amphibians.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontSynthesis
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.alexaleluia12.amphibians.R
import com.alexaleluia12.amphibians.data.fakeAmphibians
import com.alexaleluia12.amphibians.model.Amphibian
import com.alexaleluia12.amphibians.ui.theme.AmphibiansTheme

@Composable
fun AmphibiansApp() {

    Scaffold(
        topBar = { AppBar() }
    ) { contentPadding ->
        Surface(modifier = Modifier
            .padding(contentPadding)
            .fillMaxSize()) {
            val viewModel: AmphibiansViewModel = viewModel()

            AmphibiansList(uiState = viewModel.uiState)
        }
    }

}

@Composable
fun AmphibiansList(uiState: AmphibiansUiState) {
    when (uiState) {
        is AmphibiansUiState.Error -> ErrorScreen()
        is AmphibiansUiState.Loading -> LoadingScreen()
        is AmphibiansUiState.Success -> AmphibiansScreen(uiState.amphibians)
    }
}

@Composable
fun LoadingScreen() {
    // TODO usar img de loading
    Text("Carreando", style = MaterialTheme.typography.displayLarge)
}

@Composable
fun ErrorScreen() {
    // TODO usar img de quebrada
    Text("Erro ao buscar dados", style = MaterialTheme.typography.displayMedium)
}

@Composable
fun AmphibiansScreen(amphibians: List<Amphibian>) {
    LazyColumn {
        items(items = amphibians, key = { item -> item.name }) {
            AmphibianCard(amphibian = it, modifier = Modifier.padding(4.dp) )
        }
    }
}

@Composable
fun AppBar() {
    Text(
        text = stringResource(R.string.app_name),
        fontSize = 28.sp,
        style = MaterialTheme.typography.displayLarge,
        modifier = Modifier.padding(start = 8.dp, bottom = 8.dp)
    )
}

@Composable
fun AmphibianCard(amphibian: Amphibian, modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        Column {
            Text(
                text = stringResource(
                    R.string.card_amphibian_header,
                    amphibian.name,
                    amphibian.type
                ),
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                style = MaterialTheme.typography.displaySmall,
                modifier = Modifier.padding(8.dp)
            )
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .crossfade(true)
                    .data(amphibian.imgSrc)
                    .build(),
                placeholder = painterResource(R.drawable.loading_img),
                error = painterResource(R.drawable.ic_broken_image),
                contentDescription = stringResource(R.string.frog_description),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .aspectRatio(1.5f)
                    .fillMaxWidth()
            )
            Text(
                text = amphibian.description,
                textAlign = TextAlign.Justify,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AmphibiansScreenPreview() {
    AmphibiansTheme {
        AmphibiansScreen(fakeAmphibians)
    }
}