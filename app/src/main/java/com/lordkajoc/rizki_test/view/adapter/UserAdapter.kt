package com.lordkajoc.rizki_test.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lordkajoc.rizki_test.databinding.ItemUserBinding
import com.lordkajoc.rizki_test.model.user.DataGetUserResponseItem

class UserAdapter(
    private var listuser: List<DataGetUserResponseItem>,
    private var itemClick: ListUserClickInterface
) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: DataGetUserResponseItem) {
            binding.user = item
            Glide.with(itemView)
                .load(item.avatar)
                .into(binding.ivAvataruser)
            binding.cvCarduser.setOnClickListener {
                item.id.let { it1 -> itemClick.onItemClickUser(it1) }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listuser[position])
    }

    override fun getItemCount(): Int {
        return listuser.size
    }

    interface ListUserClickInterface {
        fun onItemClickUser(id: Int)
    }
}