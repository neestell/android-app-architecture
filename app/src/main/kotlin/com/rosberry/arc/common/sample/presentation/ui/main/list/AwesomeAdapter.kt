package com.rosberry.arc.common.sample.presentation.ui.main.list

import android.view.View
import android.view.ViewGroup
import com.rosberry.arc.common.presentation.ui.base.BaseAdapter
import com.rosberry.arc.common.repository.extensions.inflate
import com.rosberry.arc.common.sample.R

/**
 * Created by dmitry on 09.02.2018.
 */
class AwesomeAdapter : BaseAdapter<AwesomeViewHolder, AwesomeModel, AwesomeAdapter.OnItemClickListener>() {

    interface OnItemClickListener {
        fun onItemClicked(item: AwesomeModel, sharedView: View, position: Int)
        fun onItemLongClicked(item: AwesomeModel, sharedView: View, position: Int)

    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): AwesomeViewHolder {
        return AwesomeViewHolder(parent?.inflate(R.layout.i_awesome, false))
    }

    override fun onBindViewHolder(holder: AwesomeViewHolder?, position: Int) {
        val item = getItem(position)
        holder?.bind(item)
        holder?.itemView?.setOnClickListener({ itemClickListener?.onItemClicked(item, holder.itemView, position) })
        holder?.itemView?.setOnLongClickListener {
            itemClickListener?.onItemLongClicked(item, holder.itemView, position)
            true
        }

    }
}