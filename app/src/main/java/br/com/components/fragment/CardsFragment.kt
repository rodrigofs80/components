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
        seta.setOnClickListener {
            if(ll_detail.visibility == View.GONE){
                ll_detail.visibility = View.VISIBLE
                seta.rotation = 180f
            } else {
                ll_detail.visibility = View.GONE
                seta.rotation = 360f
            }
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