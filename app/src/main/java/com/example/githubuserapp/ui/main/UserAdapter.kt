package com.example.githubuserapp.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.bumptech.glide.Glide
import com.example.githubuserapp.databinding.ItemUserBinding
import com.example.githubuserapp.model.User

class UserAdapter(private var userList: List<User>?) : Adapter<UserAdapter.UserViewHolder>() {

    fun setUserList(userList: List<User>) {
        this.userList = userList
        notifyDataSetChanged()
    }

    inner class UserViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User?) {
            binding.user = user
            Glide.with(itemView)
                .load(user!!.avatar)
                .into(binding.imageView)
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemUserBinding.inflate(layoutInflater, parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = userList!![position]
        holder.bind(user)
    }

    override fun getItemCount(): Int {
        return if (userList != null) {
            userList!!.size
        } else {
            0
        }
    }
}