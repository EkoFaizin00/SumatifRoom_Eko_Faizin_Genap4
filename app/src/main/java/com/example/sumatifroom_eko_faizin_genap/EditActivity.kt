package com.example.sumatifroom_eko_faizin_genap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.sumatifroom_eko_faizin_genap.room.codepelita
import com.example.sumatifroom_eko_faizin_genap.room.tb_barang
import kotlinx.android.synthetic.main.activity_edit.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
class EditActivity : AppCompatActivity() {
    val db by lazy { codepelita(this) }
    private var tbBarangId: Int =0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        tombolperintah()
        setupView()
        tbBarangId = intent.getIntExtra("intent_id",0)
        Toast.makeText(this, tbBarangId.toString(), Toast.LENGTH_SHORT).show()
    }

        fun tombolperintah(){
            btnSimpanData.setOnClickListener{
                CoroutineScope(Dispatchers.IO).launch {
                    db.brgDAO().addBarang(
                        tb_barang(
                            tvID.text.toString().toInt(),
                            tvNamaBarang.text.toString(),
                            tvHarga.text.toString(),
                            tvQTY.text.toString()
                        )
                    )
                    finish()
                }
            }
            btnUpdate.setOnClickListener {
                CoroutineScope(Dispatchers.IO).launch {
                    db.brgDAO().updateBarang(
                        tb_barang(
                            tbBarangId,
                            tvNamaBarang.text.toString(),
                            tvHarga.text.toString(),
                            tvQTY.text.toString()
                        )
                    )
                    finish()
                }
            }
        }
    fun tampilData(){
        tbBarangId = intent.getIntExtra("intent_id",0)
        CoroutineScope(Dispatchers.IO).launch {
            val Brg = db.brgDAO().tampilIdBarang(tbBarangId)[0]
            tvID.setText(Brg.Id)
            tvNamaBarang.setText(Brg.NamaBarang)
            tvHarga.setText(Brg.Harga)
            tvQTY.setText(Brg.Qty)

        }
    }
    fun setupView(){
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        val intentType = intent.getIntExtra("intent_type", 0)
        when (intentType) {
            Constant.TYPE_CREATE -> {
                btnUpdate.visibility = View.GONE
            }
            Constant.TYPE_READ -> {
                btnSimpanData.visibility = View.GONE
                btnUpdate.visibility = View.GONE
                tvID.visibility = View.GONE
                tampilData()
            }
            Constant.TYPE_UPDATE -> {
                btnSimpanData.visibility = View.GONE
                tvID.visibility = View.GONE
                tampilData()
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}