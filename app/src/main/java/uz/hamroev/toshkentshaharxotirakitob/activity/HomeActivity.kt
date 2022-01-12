package uz.hamroev.toshkentshaharxotirakitob.activity

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.GravityCompat
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.navigation.NavigationView
import uz.hamroev.toshkentshaharxotirakitob.R
import uz.hamroev.toshkentshaharxotirakitob.databinding.ActivityHomeBinding
import uz.hamroev.toshkentshaharxotirakitob.fragment.*

class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {


    lateinit var binding: ActivityHomeBinding

    lateinit var homeFragment: HomeFragment
    lateinit var eventFragment: EventFragment
    lateinit var searchFragment: SearchFragment
    lateinit var sendFragment: SendFragment
    lateinit var usersFragment: UsersFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setSupportActionBar(binding.includeAppBar.toolBar)
        val actionBar = supportActionBar
        actionBar?.title = "Toshkent - Xotira Kitob"

        val drawerToggle: ActionBarDrawerToggle = object : ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            binding.includeAppBar.toolBar,
            R.string.open,
            R.string.close
        ) {

        }

        drawerToggle.isDrawerIndicatorEnabled = true
        binding.drawerLayout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()

        //supportActionBar?.setHomeButtonEnabled(true)
        //supportActionBar?.setDisplayHomeAsUpEnabled(true)
        //supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)

        binding.navView.setNavigationItemSelectedListener(this)

        homeFragment = HomeFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame_layout, homeFragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()

        binding.includeAppBar.bottomNavigationViewLinear.setCurrentActiveItem(0)
        binding.includeAppBar.bottomNavigationViewLinear.setNavigationChangeListener { view, position ->
            when (position) {
                0 -> {
                    homeFragment = HomeFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frame_layout, homeFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }

                1 -> {
                    eventFragment = EventFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frame_layout, eventFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }

                2 -> {
                    searchFragment = SearchFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frame_layout, searchFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }

                3 -> {
                    sendFragment = SendFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frame_layout, sendFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }

                4 -> {
                    usersFragment = UsersFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frame_layout, usersFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }
            }
        }

    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {

        when (menuItem.itemId) {

            R.id.home -> {
                homeFragment = HomeFragment()
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_layout, homeFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()

                binding.includeAppBar.bottomNavigationViewLinear.setCurrentActiveItem(0)
            }

            R.id.event -> {
                eventFragment = EventFragment()
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_layout, eventFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()

                binding.includeAppBar.bottomNavigationViewLinear.setCurrentActiveItem(1)
            }

            R.id.search -> {
                searchFragment = SearchFragment()
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_layout, searchFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()

                binding.includeAppBar.bottomNavigationViewLinear.setCurrentActiveItem(2)
            }

            R.id.send -> {
                sendFragment = SendFragment()
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_layout, sendFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()

                binding.includeAppBar.bottomNavigationViewLinear.setCurrentActiveItem(3)
            }

            R.id.users -> {
                usersFragment = UsersFragment()
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_layout, usersFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()

                binding.includeAppBar.bottomNavigationViewLinear.setCurrentActiveItem(4)
            }

            R.id.evaluation -> {
                Toast.makeText(this, "Baholash", Toast.LENGTH_SHORT).show()
            }

            R.id.share -> {
                Toast.makeText(this, "Ulashish", Toast.LENGTH_SHORT).show()
            }

            R.id.exit -> {
                finish()
            }


        }

        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }


    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}