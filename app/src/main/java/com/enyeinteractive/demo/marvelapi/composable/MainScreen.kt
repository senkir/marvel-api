import android.annotation.SuppressLint
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.enyeinteractive.demo.marvelapi.composable.ComicView
import com.enyeinteractive.demo.marvelapi.viewmodel.MarvelRepository
import com.enyeinteractive.demo.marvelapi.viewmodel.MarvelViewModel
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(viewModel: MarvelViewModel) {
    MaterialTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text("Sample Marvel Api")
                    },
                    backgroundColor = MaterialTheme.colors.onBackground
                )
            }) {
            //content

            val comicData = remember {
                mutableStateOf<List<MarvelRepository.ComicViewData>>(listOf())
            }

            val scope = rememberCoroutineScope()
//
//            LaunchedEffect(key1 = Unit) {
//                scope.launch {
//                    val result = viewModel.loadData()
//                    comicData.value = result
//                }
//            }

            if (comicData.value.isEmpty()) {
                Button(onClick = {
                    scope.launch {
                        val result = viewModel.loadData()
                        comicData.value = result
                    }
                }) {
                    Text("Request")
                }
            } else {
                LazyColumn {
                    items(comicData.value) { comicData ->
                        ComicView(
                            data = comicData
                        )
                    }
                }
            }
        }
    }
}