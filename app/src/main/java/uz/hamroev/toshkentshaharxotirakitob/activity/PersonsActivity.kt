package uz.hamroev.toshkentshaharxotirakitob.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import uz.hamroev.toshkentshaharxotirakitob.adapter.InsonAdapter
import uz.hamroev.toshkentshaharxotirakitob.cache.Cache
import uz.hamroev.toshkentshaharxotirakitob.databinding.ActivityPersonsBinding
import uz.hamroev.toshkentshaharxotirakitob.room.XotiraDatabase
import uz.hamroev.toshkentshaharxotirakitob.room.XotiraEntity

class PersonsActivity : AppCompatActivity() {
    lateinit var binding: ActivityPersonsBinding
    lateinit var insonAdapter: InsonAdapter
    private val TAG = "TTTT"
    var yearName = ""
    var message: String = ""
    var shareMessage: String =
        "https://play.google.com/store/apps/details?id="

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPersonsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Cache.init(this)
        XotiraDatabase.init(this)
        shareMessage = "https://play.google.com/store/apps/details?id=$packageName"

        supportActionBar?.hide()

        loadPerson()
        binding.backButton.setOnClickListener {
            finish()
        }


    }

    private fun loadPerson() {
        when (Cache.yearId) {
            "1" -> {
                binding.nameEvent.text = "Kirish"
                yearName = "Kirish"
                loadUI(1)
            }
            "2" -> {
                binding.nameEvent.text = "QISQARTMA SO‘ZLAR"
                yearName = "QISQARTMA SO‘ZLAR"
                loadUI(2)
            }
            "3" -> {
                binding.nameEvent.text = "XALQ QO‘ZG‘OLONLARIDA ISHTIROKI UCHUN JAZOLANGANLAR\n" +
                        "(1865-1916 yy.) \n" +
                        "“TOSHKENT ISTILOSIGA QARSHILIK UCHUN SURGUN QILINGANLAR“"
                yearName = "XALQ QO‘ZG‘OLONLARIDA ISHTIROKI UCHUN JAZOLANGANLAR\n" +
                        "(1865-1916 yy.) \n" +
                        "“TOSHKENT ISTILOSIGA QARSHILIK UCHUN SURGUN QILINGANLAR“"
                loadUI(3)
            }
            "4" -> {
                binding.nameEvent.text = "1892 YIL QO‘ZG‘OLONI ISHTIROKCHILARI"
                yearName = "1892 YIL QO‘ZG‘OLONI ISHTIROKCHILARI"
                loadUI(4)
            }
            "5" -> {
                binding.nameEvent.text = "1916 YIL QO‘ZG‘OLONI ISHTIROKCHILARI\n"
                yearName = "1916 YIL QO‘ZG‘OLONI ISHTIROKCHILARI\n"
                loadUI(5)
            }
            "6" -> {
                binding.nameEvent.text = "MARDIKORLIKKA OLINGANLAR\n"
                yearName = "MARDIKORLIKKA OLINGANLAR\n"
                loadUI(6)
            }
            "7" -> {
                binding.nameEvent.text =
                    "MUSTAMLAKACHILIKKA QARSHI  KAYFIYAT VA TASHVIQOT  UCHUN JAZOLANGANLAR"
                yearName = "MUSTAMLAKACHILIKKA QARSHI  KAYFIYAT VA TASHVIQOT  UCHUN JAZOLANGANLAR"
                loadUI(7)
            }
            "8" -> {
                binding.nameEvent.text = "SOVET HOKIMIYATIGA QARSHI KURASH QURBONLARI \n" +
                        "(1917-1924 yy.)"
                yearName = "SOVET HOKIMIYATIGA QARSHI KURASH QURBONLARI \n" +
                        "(1917-1924 yy.)"
                loadUI(8)
            }
            "9" -> {
                binding.nameEvent.text = "SIYOSIY AYBLOVLAR VA “QULOQ”LASHTIRISH QURBONLARI \n" +
                        "(1925-1936 yy.) \n" +
                        "“TURLI SIYOSIY AYBLOVLAR BILAN JAZOLANGANLAR“"
                yearName = "SIYOSIY AYBLOVLAR VA “QULOQ”LASHTIRISH QURBONLARI \n" +
                        "(1925-1936 yy.) \n" +
                        "“TURLI SIYOSIY AYBLOVLAR BILAN JAZOLANGANLAR“"
                loadUI(9)
            }
            "10" -> {
                binding.nameEvent.text = "“QULOQ”  QILINGANLAR"
                yearName = "“QULOQ”  QILINGANLAR"
                loadUI(10)
            }
            "11" -> {
                binding.nameEvent.text = "KATTA QIRG‘IN” QURBONLARI (1937-1938 y.y.)"
                yearName = "KATTA QIRG‘IN” QURBONLARI (1937-1938 y.y.)"
                loadUI(11)
            }
            "12" -> {
                binding.nameEvent.text = "1940-1950 YILLAR QATAG‘ONLARI"
                yearName = "1940-1950 YILLAR QATAG‘ONLARI"
                loadUI(12)
            }
            "13" -> {
                binding.nameEvent.text = "“PAXTA IShI” QURBONLARI"
                yearName = "“PAXTA IShI” QURBONLARI"
                loadUI(13)
            }
        }
    }

    fun loadUI(yearId: Int) {
        Toast.makeText(this, "$yearId", Toast.LENGTH_SHORT).show()
        val list =
            XotiraDatabase.GET.getXotiraDatabase().getXotiraDao().getPersonByYearId(yearId)
        insonAdapter = InsonAdapter(this, list, object : InsonAdapter.OnMyInsonClickListener {
            override fun onShareClick(xotiraEntity: XotiraEntity, position: Int) {
                val name = "Toshkent Shahar - Xotira Kitob"
                message = "$name\n\n" +
                        "* * * * * * *\n\n" +
                        "$yearName\n\n" +
                        "" +
                        "${list[position].person_name} -\n" +
                        "${list[position].person_info}\n" +
                        "\n* * * * * * *\n" +
                        "$shareMessage"

                val intent = Intent(Intent.ACTION_SEND)
                intent.type = "text/plain"
                intent.putExtra(Intent.EXTRA_TEXT, message)
                val chooser = Intent.createChooser(intent, "Share using...")
                startActivity(chooser)
            }
        })
        binding.rvInson.adapter = insonAdapter

    }
}