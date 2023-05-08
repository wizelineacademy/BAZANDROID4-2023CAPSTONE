package com.jecruzv.capstonewl.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.jecruzv.capstonewl.ui.fragment.ui.theme.CapstoneWLTheme
import com.jecruzv.capstonewl.ui.viewmodels.HomeViewModel
import com.jecruzv.capstonewl.ui.viewmodels.MovieViewModel
import com.jecruzv.capstonewl.util.Annotations
import dagger.hilt.android.AndroidEntryPoint

@Annotations("Entregable 2")
@AndroidEntryPoint
class DetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return ComposeView(requireContext()).apply {
            setContent {
                CapstoneWLTheme {
                    MovieScreen(navController = findNavController())
                }
            }
        }
    }
}