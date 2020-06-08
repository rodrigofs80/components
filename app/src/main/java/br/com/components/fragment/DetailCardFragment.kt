package br.com.components.fragment

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import br.com.components.R
import br.com.components.activity.MainActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fab_back.fab_back
import kotlinx.android.synthetic.main.fragment_detail_card.view.*

class DetailCardFragment() : Fragment() {

    var uri: Uri? = null

    constructor(uri: Uri): this() {
        this.uri = uri
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_detail_card, container, false)
        view.setTransitionName(view.transitionName)
        Glide.with(this)
            .load(uri)
            .into(view.image as ImageView)
        return  view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fab_back.setOnClickListener {
            activity?.let {
                it.supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.container_root, CardsFragment())
                        .addToBackStack(MainActivity.FRAGMENT_TAG).commit() }
        }
    }
}