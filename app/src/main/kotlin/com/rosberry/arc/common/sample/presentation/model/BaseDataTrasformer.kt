package com.rosberry.arc.common.sample.presentation.model

import com.rosberry.arc.common.sample.presentation.ui.main.list.AwesomeModel

object BaseDataTrasformer {

    fun toAwesomeList(src: ArrayList<AwesomeModel>) : ArrayList<AwesomeModel>{
        val target = ArrayList<AwesomeModel>().apply {
             addAll(src.map { it })
        }
        return target
    }
}