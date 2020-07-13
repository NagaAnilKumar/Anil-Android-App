package coding.androidcoding.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import coding.androidcoding.data.db.AppDatabase
import coding.androidcoding.data.db.CardEntity
import coding.androidcoding.data.models.PageResponseModel
import coding.androidcoding.data.network.MyApi
import coding.androidcoding.data.network.SafeApiRequest
import coding.androidcoding.data.preferences.PreferenceProvider
import coding.androidcoding.util.Coroutines
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDateTime

//hour * minutes * seconds * milliseconds
const val FETCH_INTERVAL = 1 * 60 * 60 * 1000
class CardRepository(
    private val api: MyApi,
    private val db: AppDatabase,
    private val prefs: PreferenceProvider
) : SafeApiRequest() {

    suspend fun getAllCards(): LiveData<List<CardEntity>> {
        return withContext(Dispatchers.IO) {
            fetchRemotePageData()
            db.getCardDao().getAllCards()
        }
    }

    private suspend fun fetchRemotePageData() {

        if (isFetchNeeded()) {
            try {
                val pageResponseModel = apiRequest { api.getPage() }
                val cardEntities = mutableListOf<CardEntity>()
                pageResponseModel.page.cards.forEach {
                    val cardEntity = CardEntity(it.cardType, it.cardDetail);
                    cardEntities.add(cardEntity)
                }
                db.getCardDao().saveAllCards(cardEntities)
                prefs.saveLastSavedAt(System.currentTimeMillis())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun isFetchNeeded(): Boolean {
        val lastSavedAt = prefs.getLastSavedAt()

        return lastSavedAt == 0L || (System.currentTimeMillis() - lastSavedAt) >= FETCH_INTERVAL
    }

}