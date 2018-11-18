package com.nearbycars.data.model

import android.os.Parcel
import android.os.Parcelable
/**
 * Model class for Car details
 *
 * @author Srisailam
 * @version 1.0
 * @since   2018-11-16
 */

data class PlaceMark(
        val address: String,
        val coordinates: List<Double>,
        val engineType: String,
        var exterior: String,
        val fuel: Int,
        val interior: String,
        val name: String,
        val vin: String

):Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            TODO("coordinates"),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(address)
        parcel.writeString(engineType)
        parcel.writeString(exterior)
        parcel.writeInt(fuel)
        parcel.writeString(interior)
        parcel.writeString(name)
        parcel.writeString(vin)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PlaceMark> {
        override fun createFromParcel(parcel: Parcel): PlaceMark {
            return PlaceMark(parcel)
        }

        override fun newArray(size: Int): Array<PlaceMark?> {
            return arrayOfNulls(size)
        }
    }
}