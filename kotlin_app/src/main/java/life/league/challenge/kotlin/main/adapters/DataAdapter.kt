package life.league.challenge.kotlin.main.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import life.league.challenge.kotlin.model.DataItem

abstract class DataAdapter: RecyclerView.Adapter<BaseViewHolder<DataItem>>(){

    abstract fun provideViewHolder(parentViewGroup: ViewGroup): BaseViewHolder<DataItem>

    private var data: List<DataItem> = emptyList()

    override fun getItemCount(): Int {
        return data.size
    }

    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<DataItem> {
        return provideViewHolder(parent)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<DataItem>, position: Int) {
        holder.bindView(data[position])
    }

    override fun onViewRecycled(holder: BaseViewHolder<DataItem>) {
        super.onViewRecycled(holder)
        holder.recyclerView()
    }

    fun updateData(newData: List<DataItem>) {
        data = newData
        notifyDataSetChanged()
    }
}