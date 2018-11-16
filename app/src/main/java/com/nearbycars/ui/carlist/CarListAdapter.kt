package com.nearbycars.ui.adapter

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nearbycars.R
import com.nearbycars.data.model.PlaceMark
import com.nearbycars.ui.MainActivity
import com.nearbycars.ui.carmap.CarMapFragment
import kotlinx.android.synthetic.main.item_car.view.*
import java.util.ArrayList

/**
 * Adapter class for Car list
 *
 * @author Srisailam
 * @version 1.0
 * @since   2018-11-16
 */
class CarListAdapter(placeMarks: MutableList<PlaceMark>, mainActivity: MainActivity) : RecyclerView.Adapter<CarListAdapter.ViewHolder>() {

    private val placeMarksList: MutableList<PlaceMark>
    private val activity: MainActivity

    init {
        placeMarksList = placeMarks
        activity = mainActivity
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_car, parent, false))
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = placeMarksList.get(position)
        holder.name.text = item.name
        holder.vin.text = item.vin
        holder.interior.text = item.interior
        holder.exterior.text = item.exterior
        holder.engineType.text = item.engineType
        holder.address.text = item.address
        holder.fuel.text = item.fuel.toString()
        if (item.coordinates.size > 1) {
            holder.coordinates.text = item.coordinates.get(1).toString() + "," + item.coordinates.get(0).toString()
        }

        //Sub layout
        holder.container.setOnClickListener(View.OnClickListener { v ->
            val bundle = Bundle()
            bundle.putParcelableArrayList(CarMapFragment::class.java.name, ArrayList(placeMarksList))
            val carMapFragment = CarMapFragment.newInstance()
            carMapFragment.arguments = bundle
            activity.replaceTheFragment(carMapFragment, CarMapFragment::class.java.name)
        })

    }

    override fun getItemCount(): Int {
        return placeMarksList.size
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var container = view.container
        var name = view.name
        var vin = view.vin
        var interior = view.interior
        var fuel = view.fuel
        var exterior = view.exterior
        var engineType = view.engineType
        var coordinates = view.coordinates
        var address = view.address

    }

}