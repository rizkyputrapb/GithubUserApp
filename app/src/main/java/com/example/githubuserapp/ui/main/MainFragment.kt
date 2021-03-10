package com.example.githubuserapp.ui.main

import android.content.res.TypedArray
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuserapp.R
import com.example.githubuserapp.databinding.MainFragmentBinding
import com.example.githubuserapp.model.User


class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private var viewModel: MainViewModel? = null
    private lateinit var binding: MainFragmentBinding
    private lateinit var dataUsername: Array<String>
    private lateinit var dataName: Array<String>
    private lateinit var dataLocation: Array<String>
    private lateinit var dataRepo: Array<String>
    private lateinit var dataComp: Array<String>
    private lateinit var dataFollowers: Array<String>
    private lateinit var dataFollowing: Array<String>
    private lateinit var dataAvatar: TypedArray
    var userList: MutableList<User>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dataAdd()
        val mainViewModelFactory = MainViewModelFactory(userList!!)
        viewModel = ViewModelProvider(this, mainViewModelFactory).get(MainViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false)
        binding.viewmodel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRVUser()
    }

    private fun setupRVUser() {
        val recyclerView = binding.rvUser
        var adapter = UserAdapter(object: OnItemUserListener {
            override fun OnUserClicked(user: User?) {
                viewModel?.onUserClicked(user)
            }
        })
        recyclerView.setLayoutManager(LinearLayoutManager(context))
        recyclerView.setHasFixedSize(true)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                DividerItemDecoration.VERTICAL
            )
        )
        recyclerView.adapter = adapter
        viewModel?.listUserLiveData()?.observe(viewLifecycleOwner, { userList ->
            adapter.setUserList(
                userList
            )
        })
        viewModel?.navigatetoDetail()?.observe(viewLifecycleOwner, {user ->
            if (user != null) {
                val action: NavDirections = MainFragmentDirections.actionMainFragmentToDetailFragment(user)
                Navigation.findNavController(requireView()).navigate(action)
                viewModel!!.onUserMainDetailNavigated()
            }
        })
        adapter.notifyDataSetChanged()
    }

    fun dataAdd() {
        dataUsername = resources.getStringArray(R.array.username)
        dataName = resources.getStringArray(R.array.name)
        dataLocation = resources.getStringArray(R.array.location)
        dataRepo = resources.getStringArray(R.array.repository)
        dataComp = resources.getStringArray(R.array.company)
        dataFollowers = resources.getStringArray(R.array.followers)
        dataFollowing = resources.getStringArray(R.array.following)
        dataAvatar = resources.obtainTypedArray(R.array.avatar)
        userList = ArrayList()
        for (position in dataName.indices) {
            val user = User(
                avatar = dataAvatar.getResourceId(position, -1),
                name = dataName[position],
                username = dataUsername[position],
                location = dataLocation[position],
                repository = dataRepo[position],
                company = dataComp[position],
                followers = dataFollowers[position],
                following = dataFollowing[position]
            )
            userList?.add(user)
        }
    }

}