package coding.androidcoding.data.db.dataconverter

import androidx.room.TypeConverter
import coding.androidcoding.data.models.CardDetailModel
import com.google.gson.Gson

class CardDetailConverter {

    var gson = Gson()

    @TypeConverter
    fun jsonToCardDetail(data: String?): CardDetailModel? {
        if (data.isNullOrEmpty()) {
            return null
        }
        return gson.fromJson(data, CardDetailModel::class.java)
    }

    @TypeConverter
    fun cardDetailToJson(cardDetail: CardDetailModel?): String? {
        return gson.toJson(cardDetail)
    }

}