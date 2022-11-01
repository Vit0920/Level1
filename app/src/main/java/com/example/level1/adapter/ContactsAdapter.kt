package com.example.level1.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.level1.R
import com.example.level1.model.ContactModel
import kotlinx.android.synthetic.main.contact_model_layout.view.*

class  ContactsAdapter : RecyclerView.Adapter<ContactsAdapter.MyViewHolder>() {

    var userList = emptyList<ContactModel>()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.contact_model_layout,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemView.tv_model_user_name.text = userList[position].name
        holder.itemView.tv_user_model_career.text = userList[position].career
    }

    override fun getItemCount(): Int {
        return userList.size
    }
}