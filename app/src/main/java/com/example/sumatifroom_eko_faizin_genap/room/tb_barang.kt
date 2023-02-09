package com.example.sumatifroom_eko_faizin_genap.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class tb_barang (
    @PrimaryKey
    val Id : Int,
    val NamaBarang : String,
    val Harga : String,
    val Qty : String
)