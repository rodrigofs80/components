package br.com.components.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import br.com.components.R
import kotlinx.android.synthetic.main.fragment_cards.*

class CardsFragment : Fragment(), OnBackPress {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_cards, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initItems()
    }

    private fun initItems(){
        acao1.setOnClickListener {
            showToast(it.contentDescription.toString())
        }
        acao2.setOnClickListener {
            showToast(it.contentDescription.toString())
        }
        card2.setOnClickListener {
            if(ll_detail2.visibility == View.GONE){
                ll_detail2.visibility = View.VISIBLE
            } else {
                ll_detail2.visibility = View.GONE
            }
        }
        card3.setOnClickListener {
            activity?.let {
                it.supportFragmentManager.beginTransaction()
                    .addSharedElement(image3,"myImage")
                    .addSharedElement(titulo3,"myTitulo")
                    .addSharedElement(sub_titulo3,"mySubTitulo")
                    .addSharedElement(descricao3,"myDescricao")
                    .addSharedElement(detalhe3,"myDetalhe")
                    .addSharedElement(profissao3,"myProfissao")
                    .replace(R.id.container_root, DetailCardFragment.newInstance()).addToBackStack("frag-tag").commit() }
        }
        fab_back.setOnClickListener {onBackPress()}
    }

    private fun showToast(msg: String){
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

    override fun onBackPress() {
        activity?.onBackPressed()
    }
}