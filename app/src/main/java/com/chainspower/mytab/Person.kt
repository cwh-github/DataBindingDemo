package com.chainspower.mytab

import android.os.Parcel
import android.os.Parcelable

/**
 * Description:
 * Date：2019/5/31-11:15
 * Author: cwh
 */
class Person() :Parcelable {

    lateinit var name: Person

    var age:Int=0

    constructor(parcel: Parcel) : this() {
        name = parcel.readParcelable(Person::class.java.classLoader)
        age = parcel.readInt()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(name, flags)
        parcel.writeInt(age)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Person> {
        override fun createFromParcel(parcel: Parcel): Person {
            return Person(parcel)
        }

        override fun newArray(size: Int): Array<Person?> {
            return arrayOfNulls(size)
        }
    }
}