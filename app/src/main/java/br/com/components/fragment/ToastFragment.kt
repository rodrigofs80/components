package br.com.components.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import br.com.components.R
import kotlinx.android.synthetic.main.custom_toast.view.*
import kotlinx.android.synthetic.main.fab_back.*
import kotlinx.android.synthetic.main.fragment_toast.*

class ToastFragment : Fragment(), OnBackPress {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_toast, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initItems()
    }

    private fun initItems(){
        sucesso.setOnClickListener { showToast(1, "Sucesso!") }
        alerta.setOnClickListener { showToast(2, "Alerta!") }
        erro.setOnClickListener { showToast(3, "Erro!") }
        fab_back.setOnClickListener {onBackPress()}
    }

    private fun showToast(tipo: Int, txt: String){
        val view: ViewGroup? = activity?.let {
            it.findViewById(R.id.container_toast)
        }
        val v =  layoutInflater.inflate(R.layout.custom_toast, view)
        val image = v.img
        v.msg.text = txt

        when (tipo) {
            1 -> {
                v.background = resources.getDrawable(R.drawable.sucess_toast_cutom)
                image.setImageResource(R.drawable.ic_check_black_24dp)
            }
            2 -> {
                v.background = resources.getDrawable(R.drawable.alert_toast_cutom)
                image.setImageResource(R.drawable.ic_add_alert_black_24dp)
            }
            else -> {
                v.background = resources.getDrawable(R.drawable.erro_toast_cutom)
                image.setImageResource(R.drawable.ic_error_black_24dp)
            }
        }

        val toast = Toast(context)
        toast.view = v
        toast.duration = Toast.LENGTH_SHORT
        toast.show()
    }

    override fun onBackPress() {
        activity?.onBackPressed()
    }
}