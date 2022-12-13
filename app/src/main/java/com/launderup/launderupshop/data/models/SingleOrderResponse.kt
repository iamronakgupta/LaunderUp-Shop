package com.launderup.launderupshop.data.models

import android.os.Parcel
import android.os.Parcelable

data class SingleOrderResponse(
    val address: String?,
    val clothes_types: String?,
    val created_at: String?,
    val delivery_dt: String?,
    val express: Boolean,
    val geolocation: String?,
    val id: Int,
    val order_id: String?,
    val payment_id: String?,
    val pickup_dt: String?,
    val service_type: String?,
    val shid: String?,
    val status: String?,
    val total_cost: String?,
    val uid: String?,
    val updated_at: String?
):Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readByte() != 0.toByte(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(address)
        parcel.writeString(clothes_types)
        parcel.writeString(created_at)
        parcel.writeString(delivery_dt)
        parcel.writeByte(if (express) 1 else 0)
        parcel.writeString(geolocation)
        parcel.writeInt(id)
        parcel.writeString(order_id)
        parcel.writeString(payment_id)
        parcel.writeString(pickup_dt)
        parcel.writeString(service_type)
        parcel.writeString(shid)
        parcel.writeString(status)
        parcel.writeString(total_cost)
        parcel.writeString(uid)
        parcel.writeString(updated_at)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SingleOrderResponse> {
        override fun createFromParcel(parcel: Parcel): SingleOrderResponse {
            return SingleOrderResponse(parcel)
        }

        override fun newArray(size: Int): Array<SingleOrderResponse?> {
            return arrayOfNulls(size)
        }
    }

}