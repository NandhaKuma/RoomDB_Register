package com.room_db_nandhakumar.taskroomdb.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.room_db_nandhakumar.taskroomdb.databinding.ParentLayoutBinding
import com.room_db_nandhakumar.taskroomdb.response.ProfileResponse

class ParentAdapter(var context: Context,var profileArray: ArrayList<ProfileResponse>) : RecyclerView.Adapter<ParentAdapter.ViewHolder>() {
    private lateinit var recyclerListener: RecyclerListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ParentLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.binding.title.text = profileArray[position].title




        holder.binding.childImageRecycler.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        holder.binding.childImageRecycler.setHasFixedSize(true)

        var childAdapter = ChildAdapter(profileArray[position].subTitle)
        holder.binding.childImageRecycler.adapter = childAdapter


    }

    override fun getItemCount(): Int {
        return profileArray.size
    }

    class ViewHolder(val binding: ParentLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    interface RecyclerListener {
        fun onclick(position: Int, title: String)
    }

    fun setRecyclerListener(recyclerListener: RecyclerListener) {
        this.recyclerListener = recyclerListener
    }

}