package com.example.proyekakhircompose.ui.screen.detail

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.proyekakhircompose.data.PlayerRepository
import com.example.proyekakhircompose.ui.ViewModelFactory
import com.example.proyekakhircompose.ui.common.UiState
import com.example.proyekakhircompose.R
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.proyekakhircompose.ui.theme.ProyekAkhirComposeTheme

@Composable
fun DetailScreen(
    playerId: String,
    viewModel: DetailViewModel = viewModel(
        factory = ViewModelFactory(
            PlayerRepository()
        )
    ),
    navigateBack: () -> Unit,
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getPlayerById(playerId)
            }
            is UiState.Success -> {
                val data = uiState.data
                DetailContent(
                    data.photo,
                    data.name,
                    data.description,
                    data.awards,
                    onBackClick = navigateBack
                )
            }
            is UiState.Error -> {}
        }
    }
}

@Composable
fun DetailContent(
    photoUrl: String,
    name: String,
    desc: String,
    awards: String,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .weight(1f)
        ) {
            Box {
                AsyncImage(
                    model = photoUrl,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(5.dp)
                        .height(400.dp)
                        .clip(CircleShape)
                )
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(R.string.back),
                    modifier = Modifier
                        .padding(16.dp)
                        .clickable { onBackClick() }
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(16.dp)
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.Center)
            ) {
                Text(
                    text = name,
                    color = MaterialTheme.colors.primary,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.h5.copy(
                        fontWeight = FontWeight.ExtraBold
                    ),
                )
            }
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = stringResource(R.string.brief_desc),
                    color = MaterialTheme.colors.secondary,
                    textAlign = TextAlign.Left,
                    style = MaterialTheme.typography.h6.copy(
                        fontWeight = FontWeight.ExtraBold
                    ),
                )
                Text(
                    text = desc,
                    style = MaterialTheme.typography.body2,
                    textAlign = TextAlign.Justify,
                )
            }
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = stringResource(R.string.awards),
                    color = MaterialTheme.colors.secondary,
                    textAlign = TextAlign.Left,
                    style = MaterialTheme.typography.h6.copy(
                        fontWeight = FontWeight.ExtraBold
                    ),
                )
                Text(
                    text = awards,
                    style = MaterialTheme.typography.body2,
                    textAlign = TextAlign.Justify,
                )
            }
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun DetailContentPreview() {
    ProyekAkhirComposeTheme {
        DetailContent(
            photoUrl = "https://storage.googleapis.com/afs-prod/media/a553afa79a204507a759e7a940fcd899/3000.webp",
            name = "David Johan",
            desc = "LeBron James is a basketball legend widely regarded as one of the greatest players of all time. He has won four NBA championships, been named the league\\'s Most Valuable Player four times, and is known for his exceptional skills on the court, including his athleticism and ability to play multiple positions. Off the court, James is a philanthropist, entrepreneur, and activist, using his platform to advocate for social justice and support various charitable causes.",
            awards = "One NBA Most Valuable Player award, nine NBA All-Star selections, two All-NBA First Team honors, seven All-NBA Second Team honors, two NBA scoring titles, two NBA assists titles, two NBA All-Star Game MVP awards, and an Olympic gold medal. Additionally, Westbrook is the NBA\\'s all-time leader in triple-doubles, having recorded the statistical feat 182 times as of the end of the 2020-2021 NBA season.",
            onBackClick = {}
        )
    }
}