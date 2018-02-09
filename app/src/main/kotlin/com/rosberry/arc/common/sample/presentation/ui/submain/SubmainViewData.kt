package com.rosberry.arc.common.sample.presentation.ui.submain

import android.content.Context
import com.rosberry.arc.common.repository.persistence.internal.ViewDataRepository
import com.rosberry.arc.common.repository.persistence.prefs.InternalStorage
import javax.inject.Inject

/**
 * Created by dmitry on 30.03.17.
 */

class SubmainViewData
@Inject constructor(context: Context, storage: InternalStorage) : ViewDataRepository(context, storage) {

}