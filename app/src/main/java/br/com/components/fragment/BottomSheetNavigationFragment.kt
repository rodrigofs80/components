package br.com.components.fragment

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import br.com.components.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.navigation.NavigationView

class BottomSheetNavigationFragment : BottomSheetDialogFragment() {

    //lateinit var closeButton: ImageView;

    companion object {
        fun newInstance() : BottomSheetNavigationFragment {
            val bundle = Bundle()
            val fragment = BottomSheetNavigationFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    //Bottom Sheet Callback
    private val mBottomSheetBehaviorCallback: BottomSheetCallback = object : BottomSheetCallback() {
        override fun onStateChanged(bottomSheet: View, newState: Int) {
            if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                dismiss()
            }
        }

        override fun onSlide(bottomSheet: View, slideOffset: Float) {
            //check the slide offset and change the visibility of close button
            if (slideOffset > 0) {
                //closeButton.setVisibility(View.VISIBLE)
            } else {
                //closeButton.setVisibility(View.GONE)
            }
        }
    }

    @SuppressLint("RestrictedApi")
    override fun setupDialog(dialog: Dialog, style: Int) {
        super.setupDialog(dialog, style)

        val contentView = View.inflate(context, R.layout.bottom_navigation_drawer, null)
        dialog.setContentView(contentView)

        val nav_view = contentView.findViewById(R.id.nav_view) as NavigationView
        var texto_bottom_bar = activity!!.findViewById(R.id.texto_bottom_bar) as TextView

        nav_view.setNavigationItemSelectedListener{
            when (it.itemId) {
                R.id.viagem -> {texto_bottom_bar.text = it.title.toString()}
                R.id.som -> {texto_bottom_bar.text = it.title.toString()}
                else -> {texto_bottom_bar.text = it.title.toString()}
            }
            dismiss()
            true
        }
//        closeButton = contentView.findViewById(R.id.close_image_view)
//        closeButton.setOnClickListener {
//            dismiss()
//        }

        val params = (contentView.parent as View).layoutParams as CoordinatorLayout.LayoutParams
        val behavior = params.behavior

        if (behavior is BottomSheetBehavior<*>) {
            behavior.setBottomSheetCallback(mBottomSheetBehaviorCallback)
        }
    }
}