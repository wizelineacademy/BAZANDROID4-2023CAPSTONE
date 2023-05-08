package mx.jramon.subias.dbmovieproyect.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<B : ViewDataBinding> : Fragment(){

    lateinit var vBind: B

    @LayoutRes
    abstract fun getLayout(): Int

    abstract fun initView()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = DataBindingUtil.inflate<B>(inflater, getLayout(), container, false).apply {
        vBind = this
        initView()
    }.root
}