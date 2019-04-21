package me.mvvm.com.myapplication.builddate.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.full_cars_fragment.view.*
import me.mvvm.com.myapplication.builddate.view.adapter.BuildAdapter
import me.mvvm.com.myapplication.builddate.viewmodel.BuildDateVM
import me.mvvm.com.myapplication.ui.viewModels.base.BaseFragment
import me.mvvm.com.myapplication.utils.OnBackPressed

/**
 * Created by Alexander Karpenko on 4/20/19.
 * java.karpenko@gmail.com
 */
class BuildDateFragment : BaseFragment(), BuildAdapter.OnItemClick, OnBackPressed {

    lateinit var viewModel: BuildDateVM
    lateinit var mBuildAdapter: BuildAdapter
    private var pos: Int = -1
    lateinit var viewMain: View
    private var key: Int = -1
    private var key2: String = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewMain = inflater.inflate(me.mvvm.com.myapplication.R.layout.full_cars_fragment, container,
                false)
        viewModel = ViewModelProviders.of(activity!!)
                .get(BuildDateVM::class.java)
        val bundle = this.arguments
        if (bundle != null) {
            key = bundle.getInt("KEY1", -1)
            key2 = bundle.getString("KEY2", "")
        }
        viewModel.initDataSoutce(key2,key)
        initAdapter()
        return viewMain
    }

    private fun initAdapter() {
        mBuildAdapter = BuildAdapter ()
        mBuildAdapter.setClick(this)
        viewMain.recycler_view.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)
        viewMain.recycler_view.adapter = mBuildAdapter
        val dividerItemDecoration = DividerItemDecoration(viewMain.recycler_view.getContext(),
                (viewMain.recycler_view.layoutManager as LinearLayoutManager).getOrientation())
        viewMain.recycler_view.addItemDecoration(dividerItemDecoration)
        viewModel.getListItems().observe(this, Observer {
            if (it?.isNotEmpty()!!) {
                viewMain.text_error.visibility = View.GONE
            } else {
                viewMain.text_error.visibility = View.VISIBLE
            }
            mBuildAdapter.updateList(it)
        })
    }

    override fun onBackPressed(): Boolean {
        return true
    }

    override fun onClick(position: Int) {
        pos = position
        mBuildAdapter.mMarkPosition = position
        viewMain.recycler_view.adapter.notifyDataSetChanged()
    }
}