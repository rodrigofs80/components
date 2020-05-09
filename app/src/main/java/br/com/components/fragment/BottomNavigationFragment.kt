package br.com.components.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.components.R
import kotlinx.android.synthetic.main.fab_back.*
import kotlinx.android.synthetic.main.fragment_bottom_navigation.*

class BottomNavigationFragment() : Fragment(), OnBackPress {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_bottom_navigation, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initItems()
    }

    private fun initItems(){
        bottom_navigation.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.dinheiro -> { texto_bottom_navigation.text = it.title }
                R.id.pets -> { texto_bottom_navigation.text = it.title }
            }
            true
        }
        fab_back.setOnClickListener {onBackPress()}
    }

    override fun onBackPress() {
        activity?.onBackPressed()
    }
}