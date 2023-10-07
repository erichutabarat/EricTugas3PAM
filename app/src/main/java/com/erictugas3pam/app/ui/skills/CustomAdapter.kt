package com.erictugas3pam.app.ui.skills

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.erictugas3pam.app.R

class CustomAdapter(private var mlist: List<itemsViewModel>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_skill_box, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemViewModel = mlist[position]

        // sets the text to the textview from our ViewHolder class
        holder.skillnamaTextView.text = itemViewModel.skillnama
        holder.skillpengalamanTextView.text = itemViewModel.skillpengalaman
    }

    override fun getItemCount(): Int {
        return mlist.size
    }
    @SuppressLint("NotifyDataSetChanged")
    fun filterList(filterlist: ArrayList<itemsViewModel>){
        mlist = filterlist
        notifyDataSetChanged()
    }

    // Holds the views for adding it to image and text
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val skillnamaTextView: TextView = itemView.findViewById(R.id.skillnama)
        val skillpengalamanTextView: TextView = itemView.findViewById(R.id.skillpengalaman)
    }
}
