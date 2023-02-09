package com.example.sumatifroom_eko_faizin_genap

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sumatifroom_eko_faizin_genap.room.codepelita
import com.example.sumatifroom_eko_faizin_genap.room.tb_barang
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    val db by lazy { codepelita(this) }
    private lateinit var barangAdapter: BarangAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        haledit()
        setupRecyclerView()
    }

    override fun onStart() {
        super.onStart()
        loadData()
    }
    fun loadData() {
        CoroutineScope(Dispatchers.IO).launch {
            val Barang = db.brgDAO().tampilAll()
            Log.d("Mainactivity","dbResponse:$Barang")
            withContext(Dispatchers.Main) {
                barangAdapter.setData(Barang)
            }
        }
    }
    fun intentEdit(tbBrngId: Int, intentType: Int)  {
        startActivity(
            Intent(applicationContext, EditActivity::class.java)
                .putExtra("intent_id",tbBrngId)
                .putExtra("intent_type",intentType)

        )
    }
    fun setupRecyclerView(){
        barangAdapter = BarangAdapter(arrayListOf(), object :
            BarangAdapter.OnAdapterListener {
            override fun OnClick(tbBarang: tb_barang) {
                intentEdit(tbBarang.Id, Constant.TYPE_READ)
            }

            override fun OnUpdate(tbBarang: tb_barang) {
                intentEdit(tbBarang.Id, Constant.TYPE_UPDATE)
            }

            override fun OnDelete(tbBarang: tb_barang) {
                deleteAlert(tbBarang)
            }
        })
        //id recyclerview
        ListDataBarang.apply {
            layoutManager = LinearLayoutManager (applicationContext)
            adapter = barangAdapter
        }
    }
    fun haledit(){
        btnTambahData.setOnClickListener{
            intentEdit(0,Constant.TYPE_CREATE)
        }
    }

    private fun deleteAlert(tbBarang: tb_barang) {
        val dialog = AlertDialog.Builder(this)
        dialog.apply {
            setTitle("Konfirmasi Hapus")
            setMessage("Yakin Mau Hapus ${tbBarang.NamaBarang}")
            setNegativeButton("Batal") {dialogInterface, i ->
                dialogInterface.dismiss()
            }
            setPositiveButton("Hapus") { dialogInterface, i ->
                CoroutineScope(Dispatchers.IO).launch {
                    db.brgDAO().deleteBarang(tbBarang)
                    dialogInterface.dismiss()
                    loadData()
                }
            }
        }
        dialog.show()
    }
}