package com.katyandleo.lifty.data

import android.os.Parcel
import android.os.Parcelable

data class Lift(
    val name: String,
    val reps: Int,
    val sets: Int,
    val rpe: Int,
    val weight: Float,
    var actualRpe: Int?,
    var actualWeight: Float?,
    val notes: String? = null,
    var isDone: Boolean = false
): Parcelable {
    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(name)
        dest.writeInt(reps)
        dest.writeInt(sets)
        dest.writeInt(rpe)
        dest.writeFloat(weight)
        dest.writeValue(actualRpe)
        dest.writeValue(actualWeight)
        dest.writeValue(notes)
        dest.writeInt(if(isDone) 1 else 0)
    }

    companion object CREATOR : Parcelable.Creator<Lift> {
        override fun createFromParcel(parcel: Parcel): Lift {
            return Lift(
                parcel.readString()!!,
                parcel.readInt(),
                parcel.readInt(),
                parcel.readInt(),
                parcel.readFloat(),
                parcel.readInt(),
                parcel.readFloat(),
                parcel.readString(),
                parcel.readInt() == 1,
            )
        }

        override fun newArray(size: Int): Array<Lift?> {
            return arrayOfNulls(size)
        }
    }
}
