package com.example.thi_comand_work.domain.objects

import android.os.Parcel
import android.os.Parcelable

data class House(
    val name: String,
    val address: String,
    val description: String,
    val price: Int,
    val owner: String,
    val img_url: String,
    val id: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(address)
        parcel.writeString(description)
        parcel.writeInt(price)
        parcel.writeString(owner)
        parcel.writeString(img_url)
        parcel.writeInt(id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<House> {
        override fun createFromParcel(parcel: Parcel): House {
            return House(parcel)
        }

        override fun newArray(size: Int): Array<House?> {
            return arrayOfNulls(size)
        }
    }
}
