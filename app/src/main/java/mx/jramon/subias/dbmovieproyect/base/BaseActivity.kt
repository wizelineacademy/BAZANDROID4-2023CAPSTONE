package mx.jramon.subias.dbmovieproyect.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import mx.jramon.subias.dbmovieproyect.utils.LoadingLottie
import mx.jramon.subias.dbmovieproyect.GlobalViewModel

abstract class BaseActivity<A:ViewDataBinding> : AppCompatActivity() {

    lateinit var vBind: A
    var loadingDialog: LoadingLottie? = null

    @LayoutRes
    abstract fun getRootLayout(): Int

    abstract fun  initObserver()

    abstract fun initView()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vBind = DataBindingUtil.setContentView(this, getRootLayout())
        initView()

        lifecycleScope.launch {

        }
    }

    override fun onResume() {
        super.onResume()
        initObserver()

    }

    protected fun showHideView(view: DisplayView) {
        when (view) {
            DisplayView.HIDE_LOADING_VIEW -> if (loadingDialog != null) {
                loadingDialog?.dismissAllowingStateLoss()
            }
            DisplayView.SHOW_LOADING_VIEW -> {
                loadingDialog = LoadingLottie.display(supportFragmentManager)
                loadingDialog?.userVisibleHint = true
            }
        }
    }

    protected fun controlNetworkMonitor(vModel: GlobalViewModel){
        lifecycleScope.launch {
            /*vModel.isOnline.collect{ isOnline->
                if (isOnline)
                    Toast.makeText(this@BaseActivity, getString(R.string.connection_restored),Toast.LENGTH_SHORT).show()
                else
                    Toast.makeText(this@BaseActivity, getString(R.string.connection_lost),Toast.LENGTH_SHORT).show()
            }*/
        }
    }

    enum class DisplayView {
        SHOW_LOADING_VIEW, HIDE_LOADING_VIEW
    }
}