package com.rosberry.arc.common.repository.persistence.file.repository

import android.content.Context
import com.rosberry.arc.common.repository.persistence.CommonRepository

/**
 * Created by dmitry on 07.08.17.
 */

abstract class ExternalRepository<out S>(context: Context, service: S) : CommonRepository<S>(context, service)