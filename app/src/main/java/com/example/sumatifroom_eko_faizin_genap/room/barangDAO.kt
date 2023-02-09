package com.example.sumatifroom_eko_faizin_genap.room

import androidx.room.*

@Dao
interface barangDAO {
    @Insert
    fun addBarang (tbBarang: tb_barang)

    @Update
    fun updateBarang (tbBarang: tb_barang)

    @Delete
    fun deleteBarang (tbBarang: tb_barang)

    @Query("SELECT*FROM tb_barang")
    fun tampilAll(): List<tb_barang>

    @Query("SELECT * FROM tb_barang WHERE id=:Barang_id")
    fun tampilIdBarang(Barang_id: Int): List<tb_barang>

}