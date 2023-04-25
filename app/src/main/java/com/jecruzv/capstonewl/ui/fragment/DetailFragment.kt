package com.jecruzv.capstonewl.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jecruzv.capstonewl.databinding.DetailFragmentBinding
import com.jecruzv.capstonewl.util.Annotations

@Annotations("Entregable 2")
class DetailFragment:Fragment() {
    lateinit var binding: DetailFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DetailFragmentBinding.inflate(inflater)
        return binding.root
    }
}