package com.katyandleo.lifty.data

import android.os.Parcel
import android.os.Parcelable



data class Workout(
    val name: String,
    val lifts: List<Lift>?
): Parcelable {
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeTypedList(lifts)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Workout> {
        override fun createFromParcel(parcel: Parcel): Workout {
            return Workout(
                parcel.readString()!!,
                parcel.createTypedArrayList(Lift)
            )
        }

        override fun newArray(size: Int): Array<Workout?> {
            return arrayOfNulls(size)
        }
    }
}
