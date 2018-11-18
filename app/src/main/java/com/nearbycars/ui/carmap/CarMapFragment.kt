package com.nearbycars.ui.carmap

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.nearbycars.R
import com.nearbycars.data.model.PlaceMark
import com.nearbycars.ui.MainActivity
import dagger.android.support.AndroidSupportInjection

/**
 * Class for implement Google map for car location
 *
 * @author Srisailam
 * @version 1.0
 * @since   2018-11-16
 */

class CarMapFragment : Fragment(), OnMapReadyCallback {

    private lateinit var placeMarks: ArrayList<PlaceMark>
    private var mMap: GoogleMap? = null
    private var mapFragment: SupportMapFragment? = null

    companion object {
        fun newInstance() = CarMapFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.maps_fragment, container, false)
        (activity as MainActivity).showProgressDialog()
        mapFragment = getChildFragmentManager().findFragmentById(R.id.map) as SupportMapFragment?
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).setBackButton(true)
        (activity as MainActivity).setTitle(getString(R.string.carmap))
        placeMarks = arguments!!.getParcelableArrayList<PlaceMark>(CarMapFragment::class.java.name) as ArrayList<PlaceMark>
        mapFragment!!.getMapAsync(this)

    }

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        (activity as MainActivity).dismissProgressDialog()
        mMap = googleMap
        mMap?.let {
            for (i in 0 until placeMarks.size) {
                val latLng = LatLng(placeMarks.get(i).coordinates.get(1), placeMarks.get(i).coordinates.get(0))
                // Add a marker, and move the camera.
                it.addMarker(MarkerOptions().position(latLng).title(placeMarks.get(i).name))
                if (i == placeMarks.size - 1) {
                    it.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12.0f))
                }

            }
        }
    }
}