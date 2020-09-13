package kpk.dev.presentation.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kpk.dev.presentation.R

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val navController: NavController by lazy {
        findNavController(R.id.nav_host_fragment)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
    }

    override fun onSupportNavigateUp() = navController.navigateUp()
}