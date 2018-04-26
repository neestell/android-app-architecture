package com.rosberry.arc.common.sample.presentation.ui.list

import android.content.Context
import com.rosberry.arc.common.repository.persistence.internal.ViewDataRepository
import com.rosberry.arc.common.repository.persistence.prefs.InternalStorage
import com.rosberry.arc.common.sample.presentation.ui.debug.DebugInteractor
import com.rosberry.arc.common.sample.presentation.ui.main.list.AwesomeModel
import javax.inject.Inject

/**
 * Created by dmitry on 30.03.17.
 */

class SwipeRefreshViewData
@Inject constructor(context: Context, storage: InternalStorage) : ViewDataRepository(context, storage),
        SwipeRefreshInteractor.SubmainDataProvider, DebugInteractor.DebugDataProvider {
    private val awesome = ArrayList<AwesomeModel>()

    override fun getAwesome(): ArrayList<AwesomeModel> = awesome

}