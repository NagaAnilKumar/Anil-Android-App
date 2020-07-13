package coding.androidcoding.data.models

import com.google.gson.annotations.SerializedName

data class CardModel(
    @SerializedName("card_type")
    val cardType: String,
    @SerializedName("card")
    val cardDetail: CardDetailModel
)
