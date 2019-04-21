package me.mvvm.com.myapplication.commonview.view.fullcarsview.adapter

import android.arch.paging.PagedListAdapter
import android.graphics.Color
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import me.mvvm.com.myapplication.R
import me.mvvm.com.myapplication.data.models.Car
import me.mvvm.com.myapplication.utils.State

/**
 * Created by Alexander Karpenko on 4/20/19
 * java.karpenko@gmail.com
 */
class AutoAdapter(private val retry: () -> Unit
) : PagedListAdapter<Car, RecyclerView.ViewHolder>(NewsDiffCallback) {

    private lateinit var mOnClick: AutoAdapter.OnItemClick
    private var state = State.LOADING
    var mMarkPosition = -1
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_auto_list, parent, false)
        return UserItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        (holder as UserItemViewHolder).bindTo(item, mOnClick, mMarkPosition, position)

    }

    fun setClick(onClick: AutoAdapter.OnItemClick) {
        this.mOnClick = onClick
    }

    internal class UserItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var userName: TextView
        var item: View

        init {
            item = itemView.findViewById(R.id.item_layout)
            userName = itemView.findViewById(R.id.item)
        }

        fun bindTo(car: Car?, click: OnItemClick, mMarkPosition: Int, position: Int) {
            userName.text = car?.name
            item.setOnClickListener({
                click.onClick(adapterPosition)
                item.setBackgroundColor(Color.GREEN)
            })
            if (mMarkPosition == position) {
                item.setBackgroundColor(Color.GREEN)
            } else {
                if (position % 2 == 0) {
                    item.setBackgroundColor(Color.WHITE)
                } else {
                    item.setBackgroundColor(Color.LTGRAY)
                }
            }
        }
    }

    fun setState(state: State) {
        this.state = state
        notifyItemChanged(super.getItemCount())
    }

    fun markItem(pos: Int) {
        if (pos != -1) {
            mMarkPosition = pos
            notifyDataSetChanged()
        }
    }

    fun getKey(pos: Int): String? = getItem(pos)?.id

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

    interface OnItemClick {
        fun onClick(position: Int)
    }
}