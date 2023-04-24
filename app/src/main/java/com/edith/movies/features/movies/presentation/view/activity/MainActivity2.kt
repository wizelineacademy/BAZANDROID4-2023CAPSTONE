
package com.edith.movies.features.movies.presentation.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.edith.movies.R
import com.edith.movies.databinding.ActivityMain2Binding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity2 : AppCompatActivity() {
    private  val binding: ActivityMain2Binding by lazy { ActivityMain2Binding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)




    }
}