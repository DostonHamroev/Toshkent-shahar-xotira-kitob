package uz.hamroev.toshkentshaharxotirakitob

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import render.animations.Attention
import render.animations.Render
import uz.hamroev.toshkentshaharxotirakitob.activity.HomeActivity
import uz.hamroev.toshkentshaharxotirakitob.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var render: Render
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window?.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        supportActionBar?.hide()
        startIntroUI()

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }, 3000)


    }

    private fun startIntroUI() {
        render = Render(this)

        val animVersion = AnimationUtils.loadAnimation(this, R.anim.anim_intro_app_name)
        val animTeam = AnimationUtils.loadAnimation(this, R.anim.anim_intro_team)

        render.setAnimation(Attention.Bounce(binding.introAppTv))
        render.setDuration(2000)
        render.start()

        binding.versionTv.startAnimation(animVersion)
        binding.teamTv.startAnimation(animTeam)

    }
}