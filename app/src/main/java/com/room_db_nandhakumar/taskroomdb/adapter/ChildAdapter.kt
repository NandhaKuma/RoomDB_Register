package com.room_db_nandhakumar.taskroomdb.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.room_db_nandhakumar.taskroomdb.databinding.ChildLayoutBinding
import com.room_db_nandhakumar.taskroomdb.databinding.ParentLayoutBinding

class ChildAdapter(var subTitle: ArrayList<String>) : RecyclerView.Adapter<ChildAdapter.ViewHolder>() {
    private lateinit var recyclerListener: RecyclerListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ChildLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.title.text = subTitle[position]
    }

    override fun getItemCount(): Int {
        return subTitle.size
    }

    class ViewHolder(val binding: ChildLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    interface RecyclerListener {
        fun onclick(position: Int, title: String)
    }

    fun setRecyclerListener(recyclerListener: RecyclerListener) {
        this.recyclerListener = recyclerListener
    }

}