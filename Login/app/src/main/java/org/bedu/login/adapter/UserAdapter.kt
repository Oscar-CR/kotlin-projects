package org.bedu.login.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.bedu.login.databinding.ItemContactBinding
import org.bedu.login.models.User
import org.bedu.login.userlist.UserListViewModel

class UserAdapter (private val viewModel: UserListViewModel):
    ListAdapter<User, UserAdapter.ViewHolder>(UserDiffCallback()){

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(viewGroup)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val item = getItem(position)
        viewHolder.bind(viewModel,item)
    }

    class ViewHolder private constructor(val binding: ItemContactBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(viewModel: UserListViewModel, item: User) {

            binding.viewModel = viewModel
            binding.user = item
            binding.executePendingBindings()

        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemContactBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }

    }

}

class UserDiffCallback : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }
}