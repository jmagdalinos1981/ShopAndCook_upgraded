package com.johnmagdalinos.android.shopandcook2.ui.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.johnmagdalinos.android.shopandcook2.R
import com.johnmagdalinos.android.shopandcook2.ui.adapters.MainAdapter
import com.johnmagdalinos.android.shopandcook2.utils.Constants

class MainFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var callBack: MainFragmentCallback

    interface MainFragmentCallback {
        fun onMainFragmentCallback(title: String)
    }

    companion object {
        fun newInstance(): MainFragment {
            return MainFragment()
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        try {
            callBack = context as MainFragmentCallback
        } catch (e: ClassCastException) {
            throw ClassCastException("${context?.toString()} must implement MainFragmentCallback")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        recyclerView = view.findViewById<RecyclerView>(R.id.rv_main_list).apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this.context)
            adapter = MainAdapter(this.context) {title: String -> callBack
                    .onMainFragmentCallback(title)}

            if (savedInstanceState != null) {
                layoutManager.onRestoreInstanceState(savedInstanceState.getParcelable(Constants.MAIN_FRAGMENT_RECYCLER_STATE))
            }
        }
        return view
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelable(Constants.MAIN_FRAGMENT_RECYCLER_STATE, recyclerView.layoutManager.onSaveInstanceState())
        super.onSaveInstanceState(outState)
    }
}