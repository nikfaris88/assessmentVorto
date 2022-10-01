package com.example.assessmentvorto.ui

import android.util.Log
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
) : RecyclerView.Adapter<BaseViewHolder>() {
    var itemViewModels: List<Business> = emptyList()

    class SearchListViewHolder(mBinding: ItemSearchListBinding) :
        BaseViewHolder(mBinding.root) {
        val name: TextView = mBinding.txtPlaceName
        private val location: TextView = mBinding.txtLocation

        override fun bind(item: Business) {
            name.text = item.name
            location.text = item.location.display_address[0] + "\n" + item.location.display_address[1]

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        return SearchListViewHolder(
            DataBindingUtil.inflate(inflater, R.layout.item_search_list, parent, false)
        )

    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val data = itemViewModels[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int{
        Log.d("Adapter SIZE", "${itemViewModels.size}")
        return itemViewModels.size
    }

    fun updateItems(items: List<Business>?) {
        itemViewModels = items ?: emptyList()
        notifyDataSetChanged()
    }
}

abstract class BaseViewHolder(mBinding: View) : RecyclerView.ViewHolder(mBinding) {
    abstract fun bind(item: Business)
}

