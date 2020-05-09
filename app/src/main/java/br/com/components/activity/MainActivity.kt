package br.com.components.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import br.com.components.model.Botao
import br.com.components.R
import br.com.components.fragment.*
import br.com.components.fragment.MainFragment.OnItemListSelected
import com.google.android.gms.ads.MobileAds

class MainActivity() : AppCompatActivity(), OnItemListSelected {

    companion object {
        const val FRAGMENT_TAG = "frag-tag"
        const val FRAGMENT_ID = "frag-id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MobileAds.initialize(this)
        if(savedInstanceState == null){
            supportFragmentManager.beginTransaction().add(R.id.container_root, MainFragment.newInstance(), FRAGMENT_TAG).commit()
        }
    }

    private fun replaceFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.container_root, fragment).addToBackStack(FRAGMENT_TAG).commit()
    }

    override fun onSelected(botao: Botao) {
        val args = Bundle()
        args.putParcelable(FRAGMENT_ID, botao)
        val fragment = getFragment(botao.id)
        fragment.arguments = args
        replaceFragment(fragment)
    }

    private fun getFragment(fragId: Int): Fragment {
        return when(fragId){
            1 -> TopAppBarFragment()
            2 -> BottomAppBarsFragment()
            3 -> BannerFragment()
            4 -> BottomNavigationFragment()
            5 -> ButtonsFragment()
            else -> TopAppBarFragment()
        }
    }
}