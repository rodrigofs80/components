package br.com.components.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import br.com.components.R
import kotlinx.android.synthetic.main.app_bar.*
import kotlinx.android.synthetic.main.fab_add.*
import kotlinx.android.synthetic.main.fab_back.fab_back
import kotlinx.android.synthetic.main.fragment_top_app_bar.*
import kotlinx.android.synthetic.main.fragment_top_bar.*
import kotlinx.android.synthetic.main.navigation_view.nav_view

class TopAppBarFragment : Fragment(), OnBackPress {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_top_app_bar, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initItems()
    }

    private fun initItems(){
        val toggle = ActionBarDrawerToggle(
            activity,
            drawer_layout,
            material_toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        material_toolbar.title = context?.getString(R.string.top_app_bar)
        material_toolbar.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.search -> {texto_top_bar.text = it.title.toString()}
                R.id._3d -> {texto_top_bar.text = it.title.toString()}
                R.id.accelerator -> {texto_top_bar.text = it.title.toString()}
                else -> {texto_top_bar.text = it.title.toString()}
            }
            true
        }

        nav_view.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.viagem -> {texto_top_bar.text = it.title.toString()}
                R.id.som -> {texto_top_bar.text = it.title.toString()}
                else -> {texto_top_bar.text = it.title.toString()}
            }
            drawer_layout.closeDrawer(GravityCompat.START)
            false
        }

        // Fabs
        fab_add.setOnClickListener {texto_top_bar.text = fab_add.contentDescription}
        fab_back.setOnClickListener {onBackPress()}
    }

    override fun onBackPress() {
        activity?.onBackPressed()
    }
}