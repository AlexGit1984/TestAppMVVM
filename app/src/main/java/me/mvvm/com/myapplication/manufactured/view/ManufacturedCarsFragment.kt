package me.mvvm.com.myapplication.manufactured.view

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
import me.mvvm.com.myapplication.R
import me.mvvm.com.myapplication.builddate.view.BuildDateFragment
import me.mvvm.com.myapplication.manufactured.view.adapter.ManufactoredAdapter
import me.mvvm.com.myapplication.manufactured.viewmodel.ManufacturedVM
import me.mvvm.com.myapplication.ui.viewModels.base.BaseFragment
import me.mvvm.com.myapplication.utils.OnBackPressed
import me.mvvm.com.myapplication.utils.State


/**
 * Created by Alexander Karpenko on 4/20/19.
 * java.karpenko@gmail.com
 */
class ManufacturedCarsFragment : BaseFragment(), ManufactoredAdapter.OnItemClick, OnBackPressed {

    lateinit var viewModel: ManufacturedVM
    lateinit var mManufactoredAdapter: ManufactoredAdapter
    private var pos: Int = -1

    lateinit var viewMain: View

    private var key: String = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewMain = inflater.inflate(me.mvvm.com.myapplication.R.layout.full_cars_fragment, container,
                false)
        viewModel = ViewModelProviders.of(activity!!)
                .get(ManufacturedVM::class.java)
        val bundle = this.arguments
        if (bundle != null) {
            key = bundle.getString("KEY1", "")
        }
        viewModel.initDataSoutce(key?.toInt())
        initAdapter()
        initState()
        viewModel.getSelectedItem().observe(this, Observer<Int> {
            pos = it!!
            mManufactoredAdapter?.markItem(it)
        })

        return viewMain
    }

    private fun initAdapter() {
        mManufactoredAdapter = ManufactoredAdapter { viewModel.retry() }
        mManufactoredAdapter.setClick(this)
        viewMain.recycler_view.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)
        viewMain.recycler_view.adapter = mManufactoredAdapter
        val dividerItemDecoration = DividerItemDecoration(viewMain.recycler_view.getContext(),
                (viewMain.recycler_view.layoutManager as LinearLayoutManager).getOrientation())
        viewMain.recycler_view.addItemDecoration(dividerItemDecoration)
        viewModel.manufactoredList.observe(this, Observer {
            mManufactoredAdapter.submitList(it)
        })
    }

    private fun initState() {
        viewMain.text_error.setOnClickListener { viewModel.retry() }
        viewModel.getState().observe(this, Observer { state ->
            viewMain.progress_bar.visibility = if (viewModel.listIsEmpty() && state == State.LOADING) View.VISIBLE else View.GONE
          if (!viewModel.listIsEmpty()) {
                mManufactoredAdapter.setState(state ?: State.DONE)
              viewMain.text_error.visibility = View.GONE
            } else {
                viewMain.text_error.visibility = View.VISIBLE
            }
        })
    }

    override fun onBackPressed(): Boolean {
        viewModel.mutableData.postValue(-1)
        return true
    }

    private fun replaceFragment() {
        val bundle = Bundle()
        bundle.putInt("KEY1", key.toInt())
        bundle.putString("KEY2", mManufactoredAdapter.getKey(pos))
        replaceFragmentSafely(
                fragment = BuildDateFragment(),
                tag = "THIRD_FRAGMENT",
                bundle = bundle,
                exitAnimation = R.anim.go_out,
                enterAnimation = R.anim.exit_to_right,
                containerViewId = R.id.container,
                allowStateLoss = true
        )
    }

    override fun onClick(position: Int) {
        pos = position
        viewModel.mutableData.postValue(pos)
        viewMain.recycler_view.adapter.notifyItemChanged(position)
        replaceFragment()
    }
}