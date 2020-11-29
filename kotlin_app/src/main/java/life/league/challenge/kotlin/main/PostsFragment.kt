package life.league.challenge.kotlin.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.posts_fragment.*
import life.league.challenge.kotlin.R
import life.league.challenge.kotlin.main.adapters.BaseViewHolder
import life.league.challenge.kotlin.main.adapters.DataAdapter
import life.league.challenge.kotlin.main.adapters.PostViewHolder
import life.league.challenge.kotlin.model.DataItem
import life.league.challenge.kotlin.model.UserPostsItem

@Suppress("UNCHECKED_CAST")
class PostsFragment : Fragment() {

    companion object {
        private const val TAG = "PostsFragment"
        fun newInstance() = PostsFragment()
    }

    private var userPostsList: List<UserPostsItem.UserPost> = emptyList()
    private lateinit var viewModel: PostsViewModel
    private val adapter: DataAdapter by lazy {
        object : DataAdapter() {
            override fun provideViewHolder(parentViewGroup: ViewGroup): BaseViewHolder<DataItem> {
                return PostViewHolder.newInstance(parentViewGroup) as BaseViewHolder<DataItem>
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.let {
            viewModel = PostsViewModel.Factory(it.application).create(PostsViewModel::class.java)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.posts_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        post_list_rv.layoutManager = LinearLayoutManager(requireContext())
        post_list_rv.adapter = adapter
        if(userPostsList.isEmpty()) {
            viewModel.fetchPosts()
            viewModel.postList.observe(viewLifecycleOwner, Observer { list ->
                error_tv.visibility = View.GONE
                post_list_rv.visibility = View.VISIBLE
                userPostsList = list
                adapter.updateData(userPostsList)
            })
        }
        viewModel.responseError.observe(viewLifecycleOwner, Observer {
            error_tv.visibility = View.VISIBLE
            post_list_rv.visibility = View.GONE
        })
    }
}