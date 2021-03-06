package com.nearbycars.ui.carlist

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.nearbycars.R
import com.nearbycars.data.model.PlaceMark
import com.nearbycars.data.model.PlaceMarkList
import com.nearbycars.ui.MainActivity
import com.nearbycars.ui.carlist.CarListAdapter
import com.nearbycars.utils.CommonUtil
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.cars_list_fragment.*
import javax.inject.Inject

val TAG = CarListFragment::class.java.name

/**
 * Class to show the list of the cars
 *
 * @author Srisailam
 * @version 1.0
 * @since   2018-11-16
 */
class CarListFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: CarListViewModel


    companion object {
        fun newInstance() = CarListFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.cars_list_fragment, container, false)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(CarListViewModel::class.java)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).setBackButton(false)
        (activity as MainActivity).setTitle(getString(R.string.carlist))
        observerViewModel()
        if (CommonUtil.isNetworkStatusAvailable(this!!.context!!)) {
            (activity as MainActivity).showProgressDialog()
            (activity as MainActivity).espressoTestIdlingResource.increment()
            viewModel.getCarList()
        } else {
            Toast.makeText(context, getText(R.string.network_error), Toast.LENGTH_LONG).show()
        }
    }

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    /**
     * Observer for success response
     */
    private val stateObserver = Observer<PlaceMarkList> {
        Log.d(TAG, "data -> ${it!!.placemarks.size} ")
        val carListAdapter = CarListAdapter(it.placemarks as MutableList<PlaceMark>, activity as MainActivity)
        setAdapter(carListAdapter)
        (activity as MainActivity).dismissProgressDialog()
        (activity as MainActivity).espressoTestIdlingResource.decrement()


    }

    /**
     * Observer for error or failure response
     */
    private val errorStateObserver = Observer<String> {
        (activity as MainActivity).dismissProgressDialog()
        Toast.makeText(context, it, Toast.LENGTH_LONG).show()
    }


    /**
     * set the adapter to recyclerview
     */
    private fun setAdapter(carListAdapter: CarListAdapter) {
        recyclerview.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = carListAdapter
        }
    }


    private fun observerViewModel() {
        viewModel.originalData.observe(this, stateObserver)
        viewModel.errorData.observe(this, errorStateObserver)
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.originalData.removeObserver(stateObserver)
    }

}