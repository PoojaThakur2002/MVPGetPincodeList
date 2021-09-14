package com.example.androidtask.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidtask.Models.PostOffice
import com.example.androidtask.R
import com.example.androidtask.handlers.PincodeClick

class PincodeAdapter(val pincodeList: ArrayList<PostOffice>?,val mListener: PincodeClick) : RecyclerView.Adapter<PincodeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PincodeAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.adapter_pincode, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: PincodeAdapter.ViewHolder, position: Int) {
        pincodeList!!.get(position).let {
            holder.bindItems(it)
        }
        holder.itemView.setOnClickListener(View.OnClickListener {
            mListener.onClickBrand(pincodeList[position].District,pincodeList[position].State,pincodeList[position].Country,pincodeList[position].Pincode,pincodeList[position].Circle,pincodeList[position].Division,pincodeList[position].Region,pincodeList[position].Block)
            notifyDataSetChanged()
        })
    }


    override fun getItemCount(): Int {
        if (pincodeList!=null){
            return pincodeList!!.size
        }else{
            return 0
        }

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(pincodeList: PostOffice) {
            val name = itemView.findViewById(R.id.tv_name) as TextView
            val branch = itemView.findViewById(R.id.tv_branch) as TextView
            name.text="Name:  "+pincodeList.Name
            branch.text= "Branch:  "+pincodeList.BranchType
        }
    }

}