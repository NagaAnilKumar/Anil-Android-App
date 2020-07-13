package coding.androidcoding.ui

import androidx.lifecycle.ViewModel
import coding.androidcoding.data.repositories.CardRepository
import coding.androidcoding.util.lazyDeferred

class MainViewModel(
    repository: CardRepository
) : ViewModel() {

    val cardList by lazyDeferred {
        repository.getAllCards()
    }
}