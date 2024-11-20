package com.kinsidelibs.find_combination.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kinsidelibs.find_combination.databinding.ValueCardBinding
import com.kinsidelibs.find_combination.listener.OnDeleteValueListener

class ValuesAdapter(
    val activity: Activity,
    val data: List<Int>,
) :
    RecyclerView.Adapter<ValuesAdapter.VH>() {
    private var listener: OnDeleteValueListener? = null

    class VH(val binding: ValueCardBinding) : RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ValueCardBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount() = data.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.binding.valueTextView.text = data[position].toString()
        holder.binding.root.setOnClickListener{
            listener?.onDeleteValue(position , data.size)
        }
    }

    fun onDeleteItem(onDeleteValueListener: OnDeleteValueListener){
        listener = onDeleteValueListener
    }
}

