package coding.androidcoding.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import coding.androidcoding.data.repositories.CardRepository

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(
    private val repository: CardRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }
}