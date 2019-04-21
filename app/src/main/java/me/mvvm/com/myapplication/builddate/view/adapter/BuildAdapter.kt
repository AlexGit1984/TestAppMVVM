package me.mvvm.com.myapplication.builddate.view.adapter

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import me.mvvm.com.myapplication.R
import me.mvvm.com.myapplication.data.models.BuildDate

/**
 * Created by Alexander Karpenko on 4/20/19.
 * java.karpenko@gmail.com
 */
class BuildAdapter : RecyclerView.Adapter<BuildAdapter.BuildItemViewHolder>() {

    private lateinit var mOnClick: BuildAdapter.OnItemClick
    var mMarkPosition = -1
    private var list = ArrayList<BuildDate>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BuildItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_auto_list, parent, false)
        return BuildItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: BuildItemViewHolder, position: Int) {
        val item = list?.get(position)
        (holder as BuildItemViewHolder).bindTo(item, mOnClick, mMarkPosition, position)

    }

    fun setClick(onClick: BuildAdapter.OnItemClick) {
        this.mOnClick = onClick
    }

    class BuildItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var userName: TextView
        var item: View

        init {
            item = itemView.findViewById(R.id.item_layout)
            userName = itemView.findViewById(R.id.item)
        }

        fun bindTo(car: BuildDate?, click: OnItemClick, mMarkPosition: Int, position: Int) {
            userName.text = car?.date
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

    override fun getItemCount(): Int = list.size



    fun updateList(it: List<BuildDate>?) {
        if(it!=null) {
            list = it as ArrayList<BuildDate>
            notifyDataSetChanged()
        }
    }

    interface OnItemClick {
        fun onClick(position: Int)
    }
}

