package br.com.components.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.components.R
import kotlinx.android.synthetic.main.fragment_cards.*

class DetailCardFragment : Fragment(), OnBackPress {

    companion object{
        fun newInstance() = DetailCardFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_detail_card, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initItems()
    }

    private fun initItems(){
        fab_back.setOnClickListener {onBackPress()}
    }

    override fun onBackPress() {
        activity?.onBackPressed()
    }
}