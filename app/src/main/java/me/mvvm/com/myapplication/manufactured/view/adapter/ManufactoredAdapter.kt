package me.mvvm.com.myapplication.manufactured.view.adapter

import android.arch.paging.PagedListAdapter
import android.graphics.Color
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import me.mvvm.com.myapplication.R
import me.mvvm.com.myapplication.data.models.Manufactured
import me.mvvm.com.myapplication.utils.State

/**
 * Created by Alexander Karpenko on 4/21/19.
 * java.karpenko@gmail.com
 */
class ManufactoredAdapter(private val retry: () -> Unit
) : PagedListAdapter<Manufactured, RecyclerView.ViewHolder>(NewsDiffCallback) {

    private lateinit var mOnClick: ManufactoredAdapter.OnItemClick
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

    fun setClick(onClick: ManufactoredAdapter.OnItemClick) {
        this.mOnClick = onClick
    }

    fun markItem(pos: Int) {
        if (pos != -1) {
            mMarkPosition = pos
            notifyDataSetChanged()
        }
    }

    internal class UserItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var userName: TextView
        var item: View

        init {
            item = itemView.findViewById(R.id.item_layout)
            userName = itemView.findViewById(R.id.item)
        }

        fun bindTo(man: Manufactured?, click: OnItemClick, mMarkPosition: Int, position: Int) {
            userName.text = man?.brand
            item.setOnClickListener(View.OnClickListener {
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

    companion object {
        val NewsDiffCallback = object : DiffUtil.ItemCallback<Manufactured>() {
            override fun areItemsTheSame(oldItem: Manufactured, newItem: Manufactured): Boolean {
                return oldItem.producer == newItem.producer
            }

            override fun areContentsTheSame(oldItem: Manufactured, newItem: Manufactured): Boolean {
                return oldItem.brand.equals(newItem.brand)
            }
        }
    }

    fun getKey(pos: Int): String? = getItem(pos)?.producer

    interface OnItemClick {
        fun onClick(position: Int)
    }
}

