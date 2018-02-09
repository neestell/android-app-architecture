package com.rosberry.arc.common.repository.extensions

import android.widget.TextView
import com.rosberry.arc.common.repository.text.KTextWatcher

/**
 * Created by dmitry on 19.06.17.
 */

inline fun TextView.textWatcher(init: KTextWatcher.() -> Unit) = addTextChangedListener(KTextWatcher().apply(init))
