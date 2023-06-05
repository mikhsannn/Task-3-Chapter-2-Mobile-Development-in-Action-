import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.dbtaplikasiflutter.R
import com.example.dbtaplikasiflutter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Mendapatkan NavController dari nav_host_fragment
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)

        // Mengatur AppBarConfiguration dengan menggunakan set dari id menu yang memiliki tautan langsung ke fragment root
        val appBarConfiguration = AppBarConfiguration.Builder(navController.graph).build()

        // Mengatur Action Bar dengan NavController dan AppBarConfiguration
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        // Mengatur bahwa NavigationUp berfungsi seperti back button, akan menggantikan Activity yang terbuka dengan fragment sebelumnya
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
