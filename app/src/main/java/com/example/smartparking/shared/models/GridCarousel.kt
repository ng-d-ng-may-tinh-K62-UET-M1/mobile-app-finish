package com.example.smartparking.shared.models

import android.content.Context
import android.view.animation.LayoutAnimationController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.epoxy.Carousel
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView

@ModelView(saveViewState = true, autoLayout = ModelView.Size.MATCH_WIDTH_MATCH_HEIGHT)
class GridCarousel(context: Context) : Carousel(context) {
    companion object {
        const val DEFAULT_SPAN = 2
        const val DEFAULT_ORIENTATION = RecyclerView.VERTICAL
    }

    private var span: Int = DEFAULT_SPAN
    private var orientation: Int = DEFAULT_ORIENTATION

    @ModelProp(ModelProp.Option.DoNotHash)
    fun itemAnimator(itemAnimator: ItemAnimator?) {
        setItemAnimator(itemAnimator)
    }

    @ModelProp(ModelProp.Option.DoNotHash)
    fun layoutAnimator(layoutAnimator: LayoutAnimationController?) {
        layoutAnimation = layoutAnimator
    }

    @JvmOverloads
    @ModelProp
    fun setSpanCount(span: Int = DEFAULT_SPAN) {
        this.span = span
        layoutManager = GridLayoutManager(context, span, orientation, false)
    }

    @JvmOverloads
    @ModelProp
    fun setOrientation(orientation: Int = DEFAULT_ORIENTATION) {
        this.orientation = orientation
        layoutManager = GridLayoutManager(context, span, orientation, false)
    }

    override fun getSnapHelperFactory(): SnapHelperFactory? {
        return null
    }


}