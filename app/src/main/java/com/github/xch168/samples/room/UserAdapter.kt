package com.github.xch168.samples.room

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.xch168.samples.R


class UserAdapter : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    private val userList = mutableListOf<User>()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val idView: TextView = itemView.findViewById(R.id.tv_id)
        val firstNameView: TextView = itemView.findViewById(R.id.tv_first)
        val lastNameView: TextView = itemView.findViewById(R.id.tv_last)

    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView  =  LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = userList[position]
        holder.idView.text = user.uid.toString()
        holder.firstNameView.text = user.firstName
        holder.lastNameView.text = user.lastName
    }

    fun replace(users: List<User>) {
        userList.clear()
        userList.addAll(users)
        notifyDataSetChanged()
    }
}