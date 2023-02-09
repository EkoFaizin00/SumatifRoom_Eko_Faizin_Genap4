package com.example.sumatifroom_eko_faizin_genap

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sumatifroom_eko_faizin_genap.room.tb_barang
import kotlinx.android.synthetic.main.activity_barang_adapter.view.*

class BarangAdapter (private val Barang : ArrayList<tb_barang>,private val listener: OnAdapterListener) :
        RecyclerView.Adapter<BarangAdapter.BarangViewHoler>() {
    class BarangViewHoler (val view: View): RecyclerView.ViewHolder(view){
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
    BarangViewHoler{
        return BarangViewHoler(
            LayoutInflater.from(parent.context).inflate(R.layout.activity_barang_adapter,parent,false)
        )
    }

    override fun onBindViewHolder(holder: BarangViewHoler, position: Int) {
        val Brg = Barang[position]
        holder.view.tvData.text = Brg.Id.toString()
        holder.view.tvData2.text = Brg.NamaBarang

        holder.view.Adapter.setOnClickListener {
            listener.OnClick(Brg)
        }
        holder.view.imageViewEdit.setOnClickListener {
            listener.OnUpdate(Brg)
        }
        holder.view.imageViewDelete.setOnClickListener{
            listener.OnDelete(Brg)
        }
    }

    override fun getItemCount() = Barang.size

    fun setData(list: List<tb_barang>){
        Barang.clear()
        Barang.addAll(list)
        notifyDataSetChanged()
    }
    interface  OnAdapterListener{
        fun OnClick(tbBarang: tb_barang)
        fun OnUpdate(tbBarang: tb_barang)
        fun OnDelete(tbBarang: tb_barang)

    }
}
