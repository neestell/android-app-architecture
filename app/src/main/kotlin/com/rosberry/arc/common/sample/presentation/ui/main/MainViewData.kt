package com.rosberry.arc.common.sample.presentation.ui.main

import android.content.Context
import com.evernote.android.state.State
import com.rosberry.arc.common.repository.persistence.internal.ViewDataRepository
import com.rosberry.arc.common.repository.persistence.prefs.InternalStorage

import javax.inject.Inject

/**
 * Created by dmitry on 30.03.17.
 */

class MainViewData
@Inject constructor(context: Context, storage: InternalStorage) : ViewDataRepository(context, storage), MainInteractor.CameraDataProvider, PleasureInteractor.PleasureDataProvider {

    @State
    var cached = true

    override fun isCached() = cached
}
