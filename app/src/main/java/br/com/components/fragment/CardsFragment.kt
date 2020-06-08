package br.com.components.fragment

import android.net.Uri
import android.os.Bundle
import android.transition.Fade
import android.transition.TransitionSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import br.com.components.R
import br.com.components.activity.MainActivity
import kotlinx.android.synthetic.main.fragment_cards.*
import kotlinx.android.synthetic.main.fragment_cards.view.*

class CardsFragment : Fragment(), OnBackPress {

    private val MOVE_DEFAULT_TIME: Long = 1000
    private val FADE_DEFAULT_TIME: Long = 300

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

        val uri: Uri = Uri.parse("android.resource://br.com.components/drawable/geralt")
        card3.setOnClickListener {
            performTransition(uri)
        }
        fab_back.setOnClickListener {
            activity?.let {
                it.supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.container_root, MainFragment.newInstance())
                        .addToBackStack(MainActivity.FRAGMENT_TAG).commit()}
        }
    }

    private fun performTransition(uri: Uri) {
         val previousFragment = activity?.let{
             it.supportFragmentManager.findFragmentById(R.id.container_root)
         }

        val nextFragment = DetailCardFragment(uri)
        val fragmentTransaction = activity?.let { it.supportFragmentManager.beginTransaction() }

        // 1. Exit for Previous Fragment
        val exitFade = Fade()
        exitFade.duration = FADE_DEFAULT_TIME
        previousFragment!!.exitTransition = exitFade

        // 2. Shared Elements Transition
        val enterTransitionSet = TransitionSet()
        enterTransitionSet.addTransition(
            android.transition.TransitionInflater.from(activity).inflateTransition(android.R.transition.move)
        )
        enterTransitionSet.duration = MOVE_DEFAULT_TIME
        enterTransitionSet.startDelay = FADE_DEFAULT_TIME
        nextFragment.setSharedElementEnterTransition(enterTransitionSet)

        // 3. Enter Transition for New Fragment
        val enterFade = Fade()
        enterFade.startDelay = MOVE_DEFAULT_TIME + FADE_DEFAULT_TIME
        enterFade.duration = FADE_DEFAULT_TIME
        nextFragment.setEnterTransition(enterFade)

        val logo = card3.image3
        fragmentTransaction?.let{
            it.apply {
                this.addSharedElement(logo, logo.transitionName)
            }
            .replace(R.id.container_root, nextFragment)
            .commitAllowingStateLoss()
        }
    }

    private fun showToast(msg: String){
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

    override fun onBackPress() {
        activity?.let {
            it.supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.container_root, CardsFragment())
                    .addToBackStack(MainActivity.FRAGMENT_TAG).commit() }
    }
}