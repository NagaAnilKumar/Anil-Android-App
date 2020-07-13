package coding.androidcoding.ui

import android.graphics.Color
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coding.androidcoding.R
import coding.androidcoding.data.db.CardEntity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.image_title_description_item.view.*
import kotlinx.android.synthetic.main.title_description_item.view.description
import kotlinx.android.synthetic.main.title_item.view.title

class CardAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var data = listOf<CardEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            1 -> TitleViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.title_item, parent, false)
            );
            2 -> TitleDescriptionViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.title_description_item, parent, false)
            );
            3 -> ImageTitleDescriptionViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.image_title_description_item, parent, false)
            )
            else -> TitleViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.title_item, parent, false)
            );
        }
    }

    fun setData(cards: List<CardEntity>) {
        data = cards
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = data[position]
        when (getItemViewType(position)) {
            1 -> {
                val titleViewHolder = holder as TitleViewHolder
                titleViewHolder.bindView(item)
            }
            2 -> {
                val titleDescVH = holder as TitleDescriptionViewHolder
                titleDescVH.bindView(item)
            }
            3 -> {
                val imageTitleDescriptionViewHolder = holder as ImageTitleDescriptionViewHolder
                imageTitleDescriptionViewHolder.bindView(item)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = data.get(position);
        return when (item.cardType) {
            "text" -> 1;
            "title_description" -> 2;
            "image_title_description" -> 3;
            else -> 1;
        };
    }

    override fun getItemCount() = data.size

    class TitleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(card: CardEntity) {
            itemView.title.text = card.cardDetail.value
            itemView.title.setTextColor(Color.parseColor(card.cardDetail.attributes.textColor))
            itemView.title.setTextSize(
                TypedValue.COMPLEX_UNIT_SP,
                card.cardDetail.attributes.font.size.toFloat()
            )

        }
    }

    class TitleDescriptionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(card: CardEntity) {
            itemView.title.text = card.cardDetail.title.value
            itemView.title.setTextColor(Color.parseColor(card.cardDetail.title.attributes.textColor))
            itemView.title.setTextSize(
                TypedValue.COMPLEX_UNIT_SP,
                card.cardDetail.title.attributes.font.size.toFloat()
            )

            itemView.description.text = card.cardDetail.description.value
            itemView.description.setTextColor(Color.parseColor(card.cardDetail.description.attributes.textColor))
            itemView.description.setTextSize(
                TypedValue.COMPLEX_UNIT_SP,
                card.cardDetail.description.attributes.font.size.toFloat()
            )
        }
    }

    class ImageTitleDescriptionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(card: CardEntity) {
            //title
            itemView.title.text = card.cardDetail.title.value;
            itemView.title.setTextColor(Color.parseColor(card.cardDetail.title.attributes.textColor))
            itemView.title.setTextSize(
                TypedValue.COMPLEX_UNIT_SP,
                card.cardDetail.title.attributes.font.size.toFloat()
            )

            //description
            itemView.description.text = card.cardDetail.description.value;
            itemView.description.setTextColor(Color.parseColor(card.cardDetail.description.attributes.textColor))
            itemView.description.setTextSize(
                TypedValue.COMPLEX_UNIT_SP,
                card.cardDetail.description.attributes.font.size.toFloat()
            )

            //image
            itemView.layoutParams.height = card.cardDetail.image.size.height
            itemView.layoutParams.width = card.cardDetail.image.size.width
            Picasso.get().load(card.cardDetail.image.url).into(itemView.image)
        }
    }
}