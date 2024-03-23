package com.example.sipembawaberita

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class News(
    var name: String,
    var description: String,
    var photo: String,
    var link: String
) : Parcelable