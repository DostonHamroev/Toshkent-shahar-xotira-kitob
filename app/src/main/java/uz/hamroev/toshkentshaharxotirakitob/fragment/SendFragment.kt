package uz.hamroev.toshkentshaharxotirakitob.fragment

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.realpacific.clickshrinkeffect.applyClickShrink
import render.animations.Attention
import render.animations.Render
import uz.hamroev.toshkentshaharxotirakitob.databinding.FragmentSendBinding
import xyz.teamgravity.checkinternet.CheckInternet

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SendFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SendFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    lateinit var binding: FragmentSendBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSendBinding.inflate(layoutInflater, container, false)

        binding.sendBtn.setOnClickListener {
            binding.sendBtn.applyClickShrink()
            val name = binding.themeNameEt.text.toString().trim()
            val info = binding.themeInfoEt.text.toString().trim()

            if (name == "" || info == "") {
                vibratePhone()
                Toast.makeText(binding.root.context, "Maydonlarni to'ldiring", Toast.LENGTH_SHORT)
                    .show()
                val animError = Render(binding.root.context)
                animError.setAnimation(Attention.Bounce(binding.linearEtBlock))
                animError.setDuration(1000)
                animError.start()
            } else {
                CheckInternet().check { connected ->
                    if (connected) {
                        try {
                            val myGmail = "dos400dos400@gmail.com"
                            val intent = Intent(Intent.ACTION_SEND)
                            intent.setData(Uri.parse("mailto:"))
                            intent.setType("text/plain")
                            intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("${myGmail}"))
                            intent.putExtra(Intent.EXTRA_SUBJECT, name)
                            intent.putExtra(Intent.EXTRA_TEXT, info)
                            startActivity(
                                Intent.createChooser(
                                    intent,
                                    "Email Pochtani Tanlang"
                                )
                            )
                        } catch (e: Exception) {

                        }
                    } else {
                        Toast.makeText(binding.root.context, "Internet yo'q", Toast.LENGTH_SHORT)
                            .show()
                    }

                }
            }

        }

        return binding.root
    }

    fun Fragment.vibratePhone() {
        val vibrator = context?.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (Build.VERSION.SDK_INT >= 26) {
            vibrator.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            vibrator.vibrate(200)
        }
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SendFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SendFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}