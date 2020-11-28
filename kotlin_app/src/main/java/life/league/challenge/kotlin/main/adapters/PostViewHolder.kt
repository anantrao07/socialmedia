package life.league.challenge.kotlin.main.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import life.league.challenge.kotlin.R
import life.league.challenge.kotlin.main.PostsFragmentDirections
import life.league.challenge.kotlin.model.UserPostsItem

class PostViewHolder (itemView: View): BaseViewHolder<UserPostsItem.UserPost>(itemView) {

    private val userImage = itemView.findViewById<ImageView>(R.id.user_iv)
    private val title = itemView.findViewById<TextView>(R.id.title_tv)
    private val userName = itemView.findViewById<TextView>(R.id.user_name_tv)
    private val description = itemView.findViewById<TextView>(R.id.description_tv)

    init {
        userImage.setOnClickListener {  }
    }
    override fun recyclerView() {
    }

    override fun bindView(item: UserPostsItem.UserPost) {
        userName.text = item.name
        description.text = item.description
        title.text = item.title
        Glide.with(itemView.context)
                .load(item.avatar)
                .placeholder(R.drawable.sharp_person_black)
                .error(R.drawable.sharp_person_black)
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .transform(CircleCrop())
                .into(userImage)
        title.setOnClickListener {view ->
            view?.let {
                val action = PostsFragmentDirections.actionPostsFragmentToUserFragment(item.userId)
                it.findNavController().navigate(action)
            }
        }
        userImage.setOnClickListener {view ->
            view?.let {
                val action = PostsFragmentDirections.actionPostsFragmentToUserFragment(item.userId)
                it.findNavController().navigate(action)
            }
        }
    }

    companion object{
        fun newInstance(parent: ViewGroup): BaseViewHolder<UserPostsItem.UserPost> {
            return PostViewHolder(
                    LayoutInflater.from(parent.context).inflate(
                            R.layout.posts_list_item,
                            parent,
                            false)
            )
        }
    }
}