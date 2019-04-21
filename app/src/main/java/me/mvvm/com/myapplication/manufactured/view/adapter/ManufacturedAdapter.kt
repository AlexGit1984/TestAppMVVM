package me.mvvm.com.myapplication.manufactured.view.adapter

import android.arch.paging.PagedListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import me.mvvm.com.myapplication.R
import me.mvvm.com.myapplication.commonview.view.fullcarsview.adapter.AutoAdapter
import me.mvvm.com.myapplication.data.models.Car
import me.mvvm.com.myapplication.utils.State

/**
 * Created by Alexander Karpenko on 4/20/19.
 * java.karpenko@gmail.com
 */
class ManufacturedAdapter (private val retry: () -> Unit)
    : PagedListAdapter<Car, RecyclerView.ViewHolder>(NewsDiffCallback) {

    private var state = State.LOADING

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_auto_list, parent, false)
        return AutoAdapter.UserItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        (holder as UserItemViewHolder).bindTo(item)

    }

    internal class UserItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var userName: TextView

        init {
            userName = itemView.findViewById(R.id.item)
        }

        fun bindTo(car: Car?) {
            userName.text = car?.name
        }
    }

    fun setState(state: State) {
        this.state = state
        notifyItemChanged(super.getItemCount())
    }

    companion object {
        val NewsDiffCallback = object : DiffUtil.ItemCallback<Car>() {
            override fun areItemsTheSame(oldItem: Car, newItem: Car): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Car, newItem: Car): Boolean {
                return oldItem.name.equals(newItem.name)
            }
        }
    }
}
