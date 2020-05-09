package br.com.components.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import br.com.components.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_bottom_app_bar.*

class BottomAppBarsFragment : Fragment(), OnBackPress {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_bottom_app_bar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //(activity as AppCompatActivity).setSupportActionBar(bottom_app_bar)
        initItems()
    }

    private fun initItems(){
        // Funciona para o menu: R.menu.menu sem o setHasOptionsMenu(true) e o setSupportActionBar(bottom_app_bar)
        bottom_app_bar.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.search -> {texto_bottom_bar.text = it.title.toString()}
                R.id._3d -> {texto_bottom_bar.text = it.title.toString()}
                R.id.accelerator -> {texto_bottom_bar.text = it.title.toString()}
                else -> {texto_bottom_bar.text = it.title.toString()}
            }
            true
        }

        // Menu: Navigation_menu
//        bottom_app_bar.setNavigationOnClickListener {
//            nav_view.visibility = View.VISIBLE
//            nav_view.setNavigationItemSelectedListener {
//                when (it.itemId) {
//                    R.id.viagem -> {texto_bottom_bar.text = it.title.toString()}
//                    R.id.som -> {texto_bottom_bar.text = it.title.toString()}
//                    else -> {texto_bottom_bar.text = it.title.toString()}
//                }
//                nav_view.visibility = View.GONE
//                true
//            }
//        }

        // Fabs
        fab.setOnClickListener {texto_bottom_bar.text = fab.contentDescription}
        fab_back.setOnClickListener {onBackPress()}

        // Menu: Navigation_menu usando BottomSheetDialogFragment
        bottom_app_bar.setNavigationOnClickListener {
            val bottomSheetDialogFragment: BottomSheetDialogFragment = BottomSheetNavigationFragment.newInstance()
            bottomSheetDialogFragment.show(activity!!.supportFragmentManager, "Bottom Sheet Dialog Fragment")
        }
    }

    // -- Criar o menu: R.menu.menu. É necessário o setHasOptionsMenu(true) no onCreateView e o setSupportActionBar(bottom_app_bar) no onViewCreated
//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        inflater.inflate(R.menu.menu, menu)
//        super.onCreateOptionsMenu(menu, inflater)
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when(item.itemId){
//            R.id.search -> {texto_bottom_bar.text = item.title.toString()}
//            R.id._3d -> {texto_bottom_bar.text = item.title.toString()}
//            R.id.accelerator -> {texto_bottom_bar.text = item.title.toString()}
//            else -> {texto_bottom_bar.text = item.title.toString()}
//        }
//        return super.onOptionsItemSelected(item)
//    }
    // -- Criar Menu --//

    override fun onBackPress() {
        activity?.onBackPressed()
    }
}