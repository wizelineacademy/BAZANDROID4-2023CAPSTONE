package com.jecruzv.capstonewl.ui.fragment

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.core.os.bundleOf
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.jecruzv.capstonewl.R
import com.jecruzv.capstonewl.ui.fragment.ui.theme.spacing
import com.jecruzv.capstonewl.ui.viewmodels.MovieViewModel
import com.jecruzv.capstonewl.util.*
import dagger.hilt.android.AndroidEntryPoint
import java.lang.Float.min

/**
 * Composable function of the Movie Screen.
 */

@Composable
fun MovieScreen(navController: NavController, viewModel: MovieViewModel = hiltViewModel()) {


    val scrollState = rememberScrollState()
    val movieState = viewModel.movieState.value

    Surface(color = MaterialTheme.colors.background, modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            if (movieState.isLoading) LoadingScreen()
            else {
                MainBoard(scrollState, navController)
                InfoBoard(scrollState, navController)
            }
        }
    }
}

@Composable
fun MainBoard(
    scrollState: ScrollState,
    navController: NavController,
    viewModel: MovieViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val movie = viewModel.movieState.value.movie

    Column {
        BackgroundImage(
            modifier = Modifier
                .fillMaxWidth()
                .height((200 - (scrollState.value * 0.15f)).dp)
                .graphicsLayer {
                    alpha = min(1f, 1 - (scrollState.value / 600f))
                    translationY = -scrollState.value * 0.1f
                },
            imageUrl = movie?.backdrop_path,
            onBack = { navController.popBackStack() },
            onShare = {
                context.shareLink(
                    movie?.homepage ?: Constants.MOVIE_THEMOVIEDB + movie?.id
                )
            }
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = MaterialTheme.spacing.default,
                    vertical = MaterialTheme.spacing.small
                )
        ) {
            ImageCard(
                imageUrl = movie?.poster_path,
                title = movie?.title,
                indication = null
            ) {}
            Column(
                modifier = Modifier
                    .padding(horizontal = MaterialTheme.spacing.small)
                    .height(150.dp)
            ) {
                Text(
                    text = buildAnnotatedString {
                        append("${movie?.title}")
                        append(
                            AnnotatedString(
                                " (${movie?.release_date?.onlyYear()})",
                                spanStyle = SpanStyle(
                                    color = MaterialTheme.colors.secondaryVariant,
                                    fontWeight = FontWeight.Light
                                )
                            )
                        )
                    },
                    style = MaterialTheme.typography.h6,
                    color = MaterialTheme.colors.onBackground,
                    textAlign = TextAlign.Start,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraSmall))
                Text(
                    text = buildAnnotatedString {
                        append(stringResource(id = R.string.runtime))
                        append(
                            AnnotatedString(
                                ": ${movie?.runtime} ${stringResource(id = R.string.text_minutes)}",
                                spanStyle = SpanStyle(
                                    color = MaterialTheme.colors.secondaryVariant,
                                    fontWeight = FontWeight.Normal
                                )
                            )
                        )
                    },
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.onBackground,
                    textAlign = TextAlign.Start
                )
                Text(
                    text = buildAnnotatedString {
                        append(stringResource(id = R.string.text_vote))
                        append(
                            AnnotatedString(
                                ": ${movie!!.vote_average ?: "-"}",
                                spanStyle = SpanStyle(
                                    color = MaterialTheme.colors.secondaryVariant,
                                    fontWeight = FontWeight.Normal
                                )
                            )
                        )
                    },
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.onBackground,
                    textAlign = TextAlign.Start
                )
                Text(
                    text = buildAnnotatedString {
                        append(stringResource(id = R.string.text_count))
                        append(
                            AnnotatedString(
                                ": ${movie!!.vote_count ?: "-"}",
                                spanStyle = SpanStyle(
                                    color = MaterialTheme.colors.secondaryVariant,
                                    fontWeight = FontWeight.Normal
                                )
                            )
                        )
                    },
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.onBackground,
                    textAlign = TextAlign.Start
                )
            }
        }
    }
}

@Composable
fun InfoBoard(
    scrollState: ScrollState,
    navController: NavController,
    viewModel: MovieViewModel = hiltViewModel()
) {
    val movieState = viewModel.movieState.value
    val videosState = viewModel.videosState.value
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(horizontal = MaterialTheme.spacing.default)
            .padding(bottom = MaterialTheme.spacing.default)
    ) {
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
        if (videosState.videos.isNotEmpty()) {
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
            DetailTitle(text = R.string.text_videos)
            DetailsCard {
                LazyRow(
                    modifier = Modifier.padding(MaterialTheme.spacing.medium),
                    horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small)
                ) {
                    videosState.videos.let { videos ->
                        items(videos.size) {
                            val video = videos[it]
                            val imageUrl = video.site?.takeVideoImageUrl(video.key)
                            VideoCard(imageUrl = imageUrl, title = video.name) {
                                context.openVideo(video.site, video.key)
                            }
                        }
                    }
                }
            }
        }
        movieState.movie?.let { movie ->
            if (!movie.overview.isNullOrEmpty()) {
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
                DetailTitle(text = R.string.text_description)
                DetailsCard { ExpandableText(text = movie.overview.orEmpty()) }
            }
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
            DetailTitle(text = R.string.text_details)
            DetailsCard {
                Column(modifier = Modifier.padding(MaterialTheme.spacing.medium)) {
                    DetailText(
                        name = R.string.text_original_title,
                        detail = ": ${movie.original_title}"
                    )
                    if (!movie.release_date.isNullOrBlank()) DetailText(
                        name = R.string.text_release_date,
                        detail = ": ${movie.release_date.normalDate(context)}"
                    )
                    if (movie.budget != 0L) DetailText(
                        name = R.string.text_budget,
                        detail = ": ${movie.budget.toString().moneyFormat()}"
                    )
                    if (movie.revenue != 0L) DetailText(
                        name = R.string.text_revenue,
                        detail = ": ${movie.revenue.toString().moneyFormat()}"
                    )
                    val languages =
                        movie.spoken_languages?.map { it.english_name }?.toFormattedString()
                    if (!languages.isNullOrBlank()) DetailText(
                        name = R.string.text_spoken_languages,
                        detail = ": $languages"
                    )
                    val genres = movie.genres?.map { it.name }?.toFormattedString()
                    if (!genres.isNullOrBlank()) DetailText(
                        name = R.string.text_genres,
                        detail = ": $genres"
                    )
                }
            }
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
            SimilarMovies(navController)
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
        }
    }
}

@Composable
fun SimilarMovies(
    navController: NavController,
    viewModel: MovieViewModel = hiltViewModel()
) {
    val similarState = viewModel.similarState.value
    val page = viewModel.similarMoviesPage.value

    if (similarState.movies.isNotEmpty()) {
        DetailTitle(text = R.string.text_similar)
        DetailsCard {
            LazyRow(
                modifier = Modifier.padding(MaterialTheme.spacing.medium),
                horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small)
            ) {
                itemsIndexed(items = similarState.movies) { index, movie ->
                    viewModel.onChangePosition(index)
                    if ((index + 1) >= (page * 20)) {
                        viewModel.getMoreSimilarMovies()
                    }
                    ImageCard(
                        imageUrl = movie.poster_path,
                        title = movie.title,
                        rating = movie.vote_average
                    ) {
                        val bundle = bundleOf(Constants.PARAM_MOVIE_ID to movie.id,
                            Constants.PARAM_MOVIE_RATING to movie.vote_average)
                        navController.navigate(R.id.action_detailFragment_to_detailFragment, bundle)
                    }
                }
            }
        }
    }
}