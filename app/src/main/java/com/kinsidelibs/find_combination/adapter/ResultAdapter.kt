package com.kinsidelibs.find_combination.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kinsidelibs.find_combination.databinding.ResultCardBinding

class ResultAdapter(val activity: Activity, val data: List<List<Int>>) :
    RecyclerView.Adapter<ResultAdapter.VH>() {
    class VH(val binding: ResultCardBinding) : RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ResultCardBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount() = data.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: VH, position: Int) {
        val result = data[position].toString()
        holder.binding.valueTextView.text = result

        //split list with + delimiter , then convert it to string
        //set to UI
    }
}