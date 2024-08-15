package com.azrxr.test_suitmedia

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

data class User(val name: String, val email: String, val avatar: String)
class UsersAdapter (private val listUsers: ArrayList<User>) : RecyclerView.Adapter<UsersAdapter.ViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_users, viewGroup, false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val user = listUsers[position]
        viewHolder.tvNameUsers.text = user.name
        viewHolder.tvEmailUsers.text = user.email

        Glide.with(viewHolder.itemView.context)
            .load(user.avatar)
            .circleCrop()
            .into(viewHolder.photoUsers)

        viewHolder.itemView.setOnClickListener {
            val intent = Intent(viewHolder.itemView.context, Screen2::class.java)
            intent.putExtra("USER_NAME", user.name)
            viewHolder.itemView.context.startActivity(intent)
        }
    }
    override fun getItemCount(): Int {
        return listUsers.size
    }
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvNameUsers: TextView = view.findViewById(R.id.tv_name)
        val tvEmailUsers: TextView = view.findViewById(R.id.tv_email)
        val photoUsers: ImageView = view.findViewById(R.id.photo)
    }
}