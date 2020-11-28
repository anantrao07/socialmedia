package life.league.challenge.kotlin.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import kotlinx.android.synthetic.main.user_fragment.*
import life.league.challenge.kotlin.R
import life.league.challenge.kotlin.database.UsersTable


class UserFragment : Fragment() {

    private var userId = 0
    companion object {
        private const val USER_ID_KEY = "userId"
        @JvmStatic
        fun newInstance(id: Int) = UserFragment().apply {
            val bundle = Bundle()
            bundle.putInt(USER_ID_KEY, id)
            arguments = bundle
        }
    }

    private lateinit var viewModel: UserViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.let{
            viewModel = UserViewModel.Factory(it.application).create(UserViewModel::class.java)
        }
        arguments?.let {
            userId = it.getInt(USER_ID_KEY)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.user_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchUser(userId)
        viewModel.userData.observe(viewLifecycleOwner, Observer { userData ->
            setUpUi(userData)
        })
    }

    private fun setUpUi(userData: UsersTable) {
        user_name_tv.text = userData.name
        phone_number_tv.text = userData.phone
        user_email_tv.text = userData.email
        website_url.text = userData.website
        Glide.with(requireContext())
                .load(userData.avatar)
                .placeholder(R.drawable.sharp_person_black)
                .error(R.drawable.sharp_person_black)
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .transform(CircleCrop())
                .into(profile_iv)
    }
}