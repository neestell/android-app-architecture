package com.rosberry.arc.common.presentation.ui.base.mvp

import com.rosberry.arc.common.repository.persistence.internal.ViewDataRepository

interface InteractorDataProvider {
    fun getRepository(): ViewDataRepository
}