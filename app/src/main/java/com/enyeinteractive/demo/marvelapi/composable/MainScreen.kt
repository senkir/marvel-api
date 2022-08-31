import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import com.enyeinteractive.demo.marvelapi.viewmodel.MarvelViewModel

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

            Text("Stub")
        }
    }
}