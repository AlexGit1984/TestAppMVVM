package me.mvvm.com.myapplication.commonview.view.fullcarsview

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
import me.mvvm.com.myapplication.commonview.view.fullcarsview.adapter.AutoAdapter
import me.mvvm.com.myapplication.commonview.viewmodel.FullCarsVM
import me.mvvm.com.myapplication.manufactured.view.ManufacturedCarsFragment
import me.mvvm.com.myapplication.ui.viewModels.base.BaseFragment
import me.mvvm.com.myapplication.utils.OnBackPressed
import me.mvvm.com.myapplication.utils.State

/**
 * Created by Alexander Karpenko on 4/20/19.
 * java.karpenko@gmail.com
 */
class FullCarsFragment : BaseFragment(), AutoAdapter.OnItemClick, OnBackPressed {

    val SECOND_FRAGMENT = "SECOND_FRAGMENT"

    lateinit var viewModel: FullCarsVM

    lateinit var mAutoAdapter: AutoAdapter

    lateinit var viewMain: View

    private var pos: Int = -1

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewMain = inflater.inflate(R.layout.full_cars_fragment, container,
                false)
        viewModel = ViewModelProviders.of(activity!!)
                .get(FullCarsVM::class.java)
        viewModel.getSelectedItem().observe(this, Observer<Int> {
            pos = it!!
            mAutoAdapter?.markItem(pos)
        })

        initAdapter()
        initState()

        return viewMain
    }

    private fun initAdapter() {
        mAutoAdapter = AutoAdapter { viewModel.retry() }
        mAutoAdapter.setClick(this)
        viewMain.recycler_view.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)
        viewMain.recycler_view.adapter = mAutoAdapter
        val dividerItemDecoration = DividerItemDecoration(viewMain.recycler_view.getContext(),
                (viewMain.recycler_view.layoutManager as LinearLayoutManager).getOrientation())
        viewMain.recycler_view.addItemDecoration(dividerItemDecoration)
        viewModel.carList.observe(this, Observer {
            mAutoAdapter.submitList(it)
        })
        mAutoAdapter.markItem(pos)
    }

    private fun initState() {
        viewMain.text_error.setOnClickListener { viewModel.retry() }
        viewModel.getState().observe(this, Observer { state ->
            viewMain.progress_bar.visibility = if (viewModel.listIsEmpty() && state == State.LOADING) View.VISIBLE else View.GONE
            viewMain.text_error.visibility = if (viewModel.listIsEmpty() && state == State.ERROR) View.VISIBLE else View.GONE
            if (!viewModel.listIsEmpty()) {
                mAutoAdapter.setState(state ?: State.DONE)
            }
        })
    }

    private fun replaceFragment() {
        val bundle = Bundle()
        bundle.putString("KEY1", mAutoAdapter.getKey(pos))
        bundle.putString("KEY2", "")
        replaceFragmentSafely(
                fragment = ManufacturedCarsFragment(),
                tag = SECOND_FRAGMENT,
                bundle = bundle,
                exitAnimation=R.anim.go_out,
                enterAnimation = R.anim.exit_to_right,
                containerViewId = R.id.container,
                allowStateLoss = true
        )
    }

    override fun onBackPressed(): Boolean {
        return true
    }

    override fun onClick(position: Int) {
        pos = position
//        viewModel.mutableData.postValue(pos)
        mAutoAdapter.mMarkPosition = position
        viewMain.recycler_view.adapter.notifyDataSetChanged()
        replaceFragment()
    }
}