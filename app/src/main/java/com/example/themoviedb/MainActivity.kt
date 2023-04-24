package com.example.themoviedbimport androidx.appcompat.app.AppCompatActivityimport android.os.Bundleimport androidx.activity.viewModelsimport androidx.navigation.fragment.NavHostFragmentimport androidx.navigation.ui.setupWithNavControllerimport com.example.themoviedb.presentation.viewmodel.MovieViewModelimport com.google.android.material.bottomnavigation.BottomNavigationViewimport dagger.hilt.android.AndroidEntryPoint@AndroidEntryPointclass MainActivity : AppCompatActivity() {    private val movieViewModel: MovieViewModel by viewModels()    override fun onCreate(savedInstanceState: Bundle?) {        super.onCreate(savedInstanceState)        setContentView(R.layout.activity_main)        movieViewModel.callGenresMovies()        val host = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment? ?: return        val navController = host.navController        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_nav_view)        bottomNavigationView?.setupWithNavController(navController)    }}