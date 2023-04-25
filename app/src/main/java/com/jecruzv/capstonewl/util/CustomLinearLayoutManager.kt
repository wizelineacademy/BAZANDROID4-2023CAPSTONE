package com.erick.juarez.tmdb.util

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager

class CustomLinearLayoutManager constructor(ctx: Context?): LinearLayoutManager(ctx, HORIZONTAL, false) {
    override fun supportsPredictiveItemAnimations(): Boolean {
        return false
    }

}