package op.mobile.app.dev.chalbr1.travelling

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.asLiveData
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import op.mobile.app.dev.chalbr1.travelling.helpers.settings.SettingsManager

/**
 * Main activity
 *
 * @constructor Create empty Main activity
 */
class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    /**
     * On create
     *
     * @param savedInstanceState
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar) // Find the View in activity_main.xml with the id toolbar
        setSupportActionBar(toolbar) // Set toolbar as the entire application's action bar

        val btmNavView: BottomNavigationView = findViewById(R.id.btm_nav_view)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        btmNavView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.splash_screen || destination.id == R.id.navigation_login || destination.id == R.id.navigation_register) {
                btmNavView.visibility = View.GONE
                toolbar.visibility = View.GONE
            }
            else {
                btmNavView.visibility = View.VISIBLE
                toolbar.visibility = View.GONE
            }
        }

        appBarConfiguration = AppBarConfiguration(setOf(R.id.navigation_home, R.id.navigation_settings))
        setupActionBarWithNavController(navController, appBarConfiguration)

        val settings = SettingsManager(this)
        settings.uiModeFlow.asLiveData().observe(this) {
            settings.setCheckedUiMode(it,true, null)
        }
    }

    /**
     * On support navigate up
     *
     * @return
     */
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}