package coding.androidcoding.data.models

data class CardDetailModel(
    val value: String,
    val attributes: Attributes,
    val title: ValueAttributes,
    val description: ValueAttributes,
    val image: Image
)