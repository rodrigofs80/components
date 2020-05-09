package br.com.components.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.components.R
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.reward.RewardItem
import com.google.android.gms.ads.reward.RewardedVideoAd
import com.google.android.gms.ads.reward.RewardedVideoAdListener
import kotlinx.android.synthetic.main.fab_back.*
import kotlinx.android.synthetic.main.fragment_banner.*
import java.util.*

class BannerFragment : Fragment(), OnBackPress, RewardedVideoAdListener {

    lateinit var intertitial : InterstitialAd
    lateinit var rewardedVideoAd : RewardedVideoAd

    companion object{
        const val BANNER = "ca-app-pub-3940256099942544/6300978111"
        const val INTERSTITIAL = "ca-app-pub-3940256099942544/1033173712"
        const val INTERSTITIAL_VIDEO = "ca-app-pub-3940256099942544/8691691433"
        const val REWARDED_VIDEO = "ca-app-pub-3940256099942544/5224354917"
        const val NATIVE_ADVANCED = "ca-app-pub-3940256099942544/2247696110"
        const val NATIVE_ADVANCED_VIDEO = "ca-app-pub-3940256099942544/1044960115"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_banner, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initItems()
    }

    private fun initItems(){
        // Add Devices
        //val testDeviceIds = Arrays.asList("33BE2250B43518CCDA7DE426D04EE231")
        //val configuration = RequestConfiguration.Builder().setTestDeviceIds(testDeviceIds).build()
        //MobileAds.setRequestConfiguration(configuration)

        MobileAds.initialize(context)

        // Simple Banner
        adView.loadAd(AdRequest.Builder().build())

        // INTERSTITIAL
        intertitial = InterstitialAd(context)
        intertitial.adUnitId = INTERSTITIAL
        intertitial.loadAd(AdRequest.Builder().build())
        bt_interstitial.setOnClickListener {
            if (intertitial.isLoaded) intertitial.show()
        }

        MobileAds.initialize(context, "ca-app-pub-3940256099942544~3347511713")
        rewardedVideoAd = MobileAds.getRewardedVideoAdInstance(context)
        rewardedVideoAd.rewardedVideoAdListener = this
        rewardedVideoAd.loadAd(REWARDED_VIDEO, AdRequest.Builder().build())
        bt_interstitial_video.isEnabled = false
        bt_interstitial_video.setOnClickListener {
            if (rewardedVideoAd.isLoaded) rewardedVideoAd.show()
        }

        fab_back.setOnClickListener {onBackPress()}
    }

    override fun onBackPress() {
        activity?.onBackPressed()
    }

    override fun onRewardedVideoAdClosed() {
        log.append("onRewardedVideoAdClosed.\n")
    }

    override fun onRewardedVideoAdLeftApplication() {
        log.append("onRewardedVideoAdLeftApplication.\n")
    }

    override fun onRewardedVideoAdLoaded() {
        log.append("onRewardedVideoAdLoaded.\n")
        bt_interstitial_video.isEnabled = true
    }

    override fun onRewardedVideoAdOpened() {
        log.append("onRewardedVideoAdOpened.\n")
    }

    override fun onRewardedVideoCompleted() {
        log.append("onRewardedVideoCompleted.\n")
    }

    override fun onRewarded(rewardItem: RewardItem) {
        log.append(String.format(Locale.getDefault(),"Vc recebeu %d %s !\n", rewardItem.amount, rewardItem.type))
    }

    override fun onRewardedVideoStarted() {
        log.append("onRewardedVideoStarted.\n")
    }

    override fun onRewardedVideoAdFailedToLoad(p0: Int) {
        log.append("onRewardedVideoAdFailedToLoad.\n")
    }
}