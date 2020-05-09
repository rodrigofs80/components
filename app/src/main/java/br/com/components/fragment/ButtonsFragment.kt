package br.com.components.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import br.com.components.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fab_back.*
import kotlinx.android.synthetic.main.fragment_buttons.*
import kotlinx.android.synthetic.main.fragment_buttons.view.*

class ButtonsFragment : Fragment(), OnBackPress {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_buttons, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initItems()
    }

    private fun initItems(){
        text_button.setOnClickListener { text_text_button.text = "Text button" }
        outline_button.setOnClickListener { text_outline_button.text = "Outlined button" }
        contained_button.setOnClickListener { text_contained_button.text = "Contained button" }
        toggleButton.addOnButtonCheckedListener { toggleButton, checkedId, isChecked ->
            if(isChecked){
                when(checkedId){
                    R.id.button1 -> text_toggle_button.text = "MaterialButtonToggleGroup: "+ toggleButton.button1.text
                    R.id.button2 -> text_toggle_button.text = "MaterialButtonToggleGroup: "+ toggleButton.button2.text
                    else -> text_toggle_button.text = "MaterialButtonToggleGroup: "+ toggleButton.button3.text
                }
            }
        }
        ext_fab.setOnClickListener { text_extended_fab.text = ext_fab.text }

        fabdial.setOnClickListener {
            fabdial.isExpanded = !fabdial.isExpanded
        }
        fab1.setOnClickListener {
            showToast(fab1.contentDescription.toString())
            fabdial.isExpanded = false
        }
        fab2.setOnClickListener {
            showToast(fab2.contentDescription.toString())
            fabdial.isExpanded = false
        }
        fab3.setOnClickListener {
            showToast(fab3.contentDescription.toString())
            fabdial.isExpanded = false
        }

        fab_back.setOnClickListener {onBackPress()}
    }

    private fun showToast(texto: String){
        Toast.makeText(context, texto, Toast.LENGTH_SHORT).show()
    }

    override fun onBackPress() {
        activity?.onBackPressed()
    }
}