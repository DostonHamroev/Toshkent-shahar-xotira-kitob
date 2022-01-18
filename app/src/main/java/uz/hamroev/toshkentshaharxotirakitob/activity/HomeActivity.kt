package uz.hamroev.toshkentshaharxotirakitob.activity

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.GravityCompat
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.navigation.NavigationView
import uz.hamroev.toshkentshaharxotirakitob.R
import uz.hamroev.toshkentshaharxotirakitob.cache.Cache
import uz.hamroev.toshkentshaharxotirakitob.databinding.ActivityHomeBinding
import uz.hamroev.toshkentshaharxotirakitob.fragment.*

class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {


    lateinit var binding: ActivityHomeBinding

    lateinit var homeFragment: HomeFragment
    lateinit var eventFragment: EventFragment
    lateinit var searchFragment: SearchFragment
    lateinit var sendFragment: SendFragment
    lateinit var usersFragment: UsersFragment

    var isBack = false
    lateinit var handler: Handler
    var doubleToast = "Chiqish uchun 2 marta bosing"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Cache.init(this)

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
                try {
                    val uri: Uri = Uri.parse("market://details?id=$packageName")
                    val intent = Intent(Intent.ACTION_VIEW, uri)
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                } catch (e: ActivityNotFoundException) {
                    val uri: Uri =
                        Uri.parse("http://play.google.com/store/apps/details?id=$packageName")
                    val intent = Intent(Intent.ACTION_VIEW, uri)
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                }

                binding.drawerLayout.closeDrawers()
            }

            R.id.share -> {
                try {
                    val intent = Intent(Intent.ACTION_SEND)
                    intent.setType("text/plain")
                    intent.putExtra(Intent.EXTRA_SUBJECT, "Toshkent Shahar - Xotira Kitob")
                    val shareMessage: String =
                        "https://play.google.com/store/apps/details?id=" + packageName
                    intent.putExtra(Intent.EXTRA_TEXT, shareMessage)
                    startActivity(Intent.createChooser(intent, "share by"))
                } catch (e: Exception) {
                    Toast.makeText(this, "", Toast.LENGTH_SHORT).show()
                }
                binding.drawerLayout.closeDrawers()
            }

            R.id.exit -> {
                // apple alert  we create maybe !
                finish()
            }


        }

        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }


    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else

        if(isBack) {
            super.onBackPressed()
            return
        }

        this.isBack = true
        handler = Handler(Looper.getMainLooper())
        Toast.makeText(this, "$doubleToast", Toast.LENGTH_SHORT).show()
        handler.postDelayed({
            isBack = false
        }, 1000)
    }
}