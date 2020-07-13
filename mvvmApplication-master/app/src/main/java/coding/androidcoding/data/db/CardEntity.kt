package coding.androidcoding.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import coding.androidcoding.data.models.CardDetailModel

@Entity(tableName = "cards")
data class CardEntity (
    @ColumnInfo(name = "card_type") val cardType: String,
    @ColumnInfo(name = "card_detail") val cardDetail: CardDetailModel
){
    @PrimaryKey(autoGenerate = true) var id: Int = 0;
}