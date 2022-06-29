package androidx.appcompat.widget

import android.content.Context
import android.view.View

class CustomPopupMenu constructor(context: Context, view: View): PopupMenu(context, view) {
    fun setOnMenuItemClickListener(onMenuItemClickListener: android.widget.PopupMenu.OnMenuItemClickListener) {
        TODO("Not yet implemented")
    }

    init {
        mPopup.setForceShowIcon(true)
    }
}