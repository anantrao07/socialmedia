package life.league.challenge.kotlin.main.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import life.league.challenge.kotlin.R
import life.league.challenge.kotlin.model.UserPostsItem

class PostViewAdapter(private var list: List<UserPostsItem.UserPost>) : RecyclerView.Adapter<PostViewAdapter.PostViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewAdapter.PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.posts_list_item, parent, false)
        return PostViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: PostViewAdapter.PostViewHolder, position: Int) {
        val post = list[position]
        post.run { holder.bind(this) }
    }

    fun updateData(newData: List<UserPostsItem.UserPost>) {
        list = newData
        notifyDataSetChanged()
    }

    inner class PostViewHolder(@NonNull itemView: View): RecyclerView.ViewHolder(itemView) {
        private val userImage = itemView.findViewById<ImageView>(R.id.user_iv)
        private val title = itemView.findViewById<TextView>(R.id.title_tv)
        private val userName = itemView.findViewById<TextView>(R.id.user_name_tv)
        private val description = itemView.findViewById<TextView>(R.id.description_tv)

        fun bind(post: UserPostsItem.UserPost) {
            title.text = post.title
            description.text = post.description
        }
    }
}