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
        fun onAwesomeClicked(item: AwesomeModel, sharedView: View, position: Int)
        fun onAwesomeLongClicked(item: AwesomeModel, sharedView: View, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): AwesomeViewHolder {
        return AwesomeViewHolder(parent?.inflate(R.layout.i_awesome, false))
    }

    override fun onBindViewHolder(holder: AwesomeViewHolder?, position: Int) {
        val item = getItem(position)
        holder?.bind(item)
        holder?.itemView?.setOnClickListener({ itemClickListener?.onAwesomeClicked(item, holder.itemView, position) })
        holder?.itemView?.setOnLongClickListener {
            itemClickListener?.onAwesomeLongClicked(item, holder.itemView, position)
            true
        }

    }
}