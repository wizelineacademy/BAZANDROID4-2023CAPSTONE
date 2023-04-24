package mx.jramon.subias.dbmovieproyect.utils

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.airbnb.lottie.LottieDrawable
import mx.jramon.subias.dbmovieproyect.R
import mx.jramon.subias.dbmovieproyect.databinding.AnimationLottieBinding

class LoadingLottie : DialogFragment() {

    var fragmentMG : FragmentManager? = null
    val LOTTIE_ASSET = "movie_loader.json"

    private lateinit var vBind: AnimationLottieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.AppTheme_FullScreenDialog_Transparent)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        vBind = AnimationLottieBinding.inflate(inflater, container, true)
        vBind.lifecycleOwner = viewLifecycleOwner
        return vBind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isCancelable = false

        vBind.animationView.imageAssetsFolder = "assets"
        vBind.animationView.setAnimation(LOTTIE_ASSET)
        vBind.animationView.repeatCount = LottieDrawable.INFINITE

        playAnimation()
    }

    fun playAnimation() {
        vBind.animationView.playAnimation()
    }

    override fun onDismiss(dialog: DialogInterface) {
        vBind.animationView.cancelAnimation()
        super.onDismiss(dialog)
    }

    override fun onStart() {
        super.onStart()
        val dialog = dialog ?: requireDialog()
        dialog.window?.apply {
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.MATCH_PARENT
            dialog.window?.setLayout(width, height)
        }
    }

    companion object
    {
        const val TAG = "GSVULoading"

        fun display(fragmentManager: FragmentManager): LoadingLottie
        {
            val dialogFragment = LoadingLottie()
            dialogFragment.show(fragmentManager, TAG)
            dialogFragment.fragmentMG = fragmentManager
            return dialogFragment
        }
    }


}