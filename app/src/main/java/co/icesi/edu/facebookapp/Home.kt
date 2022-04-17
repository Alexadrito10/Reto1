package co.icesi.edu.facebookapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import co.icesi.edu.facebookapp.databinding.ActivityHomeBinding

class Home : AppCompatActivity() {
    private lateinit var binding : ActivityHomeBinding

    private lateinit var homeFragment : homePosts
    private lateinit var publishFragment : publish
    private lateinit var profileFragment: profile

    public lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)


        homeFragment = homePosts.newInstance()
        profileFragment= profile.newInstance()
        publishFragment= publish.newInstance()

        showFragment(homeFragment)

        /* binding.bottomNavigationView.setOnItemSelectedListener { menuItem ->
            if (menuItem.itemId == R.id.home) {
                showFragment(homeFragment)
            } else if (menuItem.itemId == R.id.publish) {
                showFragment(publishFragment)
            } else if (menuItem.itemId == R.id.profile) {
                showFragment(profileFragment)
            }
            true
        }*/

    }

    fun showFragment(fragment : Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.postColumn, fragment)
        transaction.commit()
    }
}