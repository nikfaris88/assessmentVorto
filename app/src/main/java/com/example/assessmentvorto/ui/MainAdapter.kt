package com.example.assessmentvorto.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.assessmentvorto.R
import com.example.assessmentvorto.databinding.ItemSearchListBinding
import com.example.assessmentvorto.model.Business


class MainAdapter(
    private val business: MutableList<Business>
) : RecyclerView.Adapter<BaseViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        return SearchListViewHolder(
            DataBindingUtil.inflate(inflater, R.layout.item_search_list, parent, false)
        )

    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val data = business[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = business.size

}

abstract class BaseViewHolder(mBinding: View) : RecyclerView.ViewHolder(mBinding) {
    abstract fun bind(item: Business)
}

class SearchListViewHolder(mBinding: ItemSearchListBinding) :
    BaseViewHolder(mBinding.root) {
    val name: TextView = mBinding.txtPlaceName
    val location: TextView = mBinding.txtLocation

    override fun bind(item: Business) {
        name.text = item.name
        location.text = item.location.displayAddress[0]

    }
}