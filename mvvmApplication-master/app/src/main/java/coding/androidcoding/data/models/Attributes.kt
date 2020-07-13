package coding.androidcoding.data.models

import com.google.gson.annotations.SerializedName

data class Attributes(
    @SerializedName("text_color")
    val textColor: String,
    val font: Font
)