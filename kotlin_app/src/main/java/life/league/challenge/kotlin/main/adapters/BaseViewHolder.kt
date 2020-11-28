package life.league.challenge.kotlin.main.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import life.league.challenge.kotlin.model.DataItem

abstract class BaseViewHolder<T: DataItem>(itemView: View): RecyclerView.ViewHolder(itemView) {
    abstract fun bindView(item: T)
    abstract fun recyclerView()
}