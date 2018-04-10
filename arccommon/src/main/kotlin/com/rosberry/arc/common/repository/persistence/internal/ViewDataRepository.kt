package com.rosberry.arc.common.repository.persistence.internal

import android.content.Context
import android.os.Bundle
import com.evernote.android.state.StateSaver
import com.rosberry.arc.common.repository.persistence.CommonRepository
import com.rosberry.arc.common.repository.persistence.prefs.InternalStorage

/**
 * Created by dmitry on 30.03.17.
 */

abstract class ViewDataRepository constructor(context: Context,
                                              storage: InternalStorage) : CommonRepository<InternalStorage>(context, storage) {


    fun saveInstanceState(bundle: Bundle?) {
       if (bundle != null) StateSaver.saveInstanceState(this, bundle)

    }

    fun restoreInstanceState(bundle: Bundle?) {
        StateSaver.restoreInstanceState(this, bundle)
    }

     fun getRepository() = this

}
