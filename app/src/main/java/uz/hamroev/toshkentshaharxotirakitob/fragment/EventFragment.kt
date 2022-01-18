package uz.hamroev.toshkentshaharxotirakitob.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import uz.hamroev.toshkentshaharxotirakitob.activity.PersonsActivity
import uz.hamroev.toshkentshaharxotirakitob.adapter.EventAdapter
import uz.hamroev.toshkentshaharxotirakitob.cache.Cache
import uz.hamroev.toshkentshaharxotirakitob.databinding.FragmentEventBinding
import uz.hamroev.toshkentshaharxotirakitob.model.Event

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [EventFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EventFragment : Fragment() {
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

    lateinit var binding: FragmentEventBinding
    lateinit var list: ArrayList<Event>
    lateinit var eventAdapter: EventAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEventBinding.inflate(layoutInflater, container, false)

        Cache.init(binding.root.context)

        loadData()
        eventAdapter =
            EventAdapter(binding.root.context, list, object : EventAdapter.OnMyEventClickListener {
                override fun onEventClick(event: Event, position: Int) {
                    var year_id = (position+1).toString()
                    Cache.yearId = "${year_id.toString()}"
                    val intent = Intent(binding.root.context, PersonsActivity::class.java)
                    startActivity(intent)

                }
            })
        binding.rvAllEvent.adapter = eventAdapter


        return binding.root
    }

    private fun loadData() {
        list = ArrayList()
        list.add(Event("Kirish"))
        list.add(Event("QISQARTMA SO‘ZLAR"))
        list.add(
            Event(
                "XALQ QO‘ZG‘OLONLARIDA ISHTIROKI UCHUN JAZOLANGANLAR\n" +
                        "(1865-1916 yy.)\n" +
                        "\n" +
                        "TOSHKENT ISTILOSIGA QARSHILIK UCHUN SURGUN QILINGANLAR"
            )
        )
        list.add(Event("1892 YIL QO‘ZG‘OLONI ISHTIROKCHILARI"))
        list.add(Event("1916 YIL QO‘ZG‘OLONI ISHTIROKCHILARI\n"))
        list.add(Event("MARDIKORLIKKA OLINGANLAR\n"))
        list.add(Event("MUSTAMLAKACHILIKKA QARSHI  KAYFIYAT VA TASHVIQOT  UCHUN JAZOLANGANLAR"))
        list.add(
            Event(
                "SOVET HOKIMIYATIGA QARSHI KURASH QURBONLARI \n" +
                        "(1917-1924 yy.)"
            )
        )
        list.add(
            Event(
                "SIYOSIY AYBLOVLAR VA “QULOQ”LASHTIRISH QURBONLARI \n" +
                        "(1925-1936 yy.)\n" +
                        "\n" +
                        "TURLI SIYOSIY AYBLOVLAR BILAN JAZOLANGANLAR"
            )
        )
        list.add(Event("“QULOQ”  QILINGANLAR"))
        list.add(Event("“KATTA QIRG‘IN” QURBONLARI (1937-1938 y.y.)"))
        list.add(Event("1940-1950 YILLAR QATAG‘ONLARI"))
        list.add(Event("“PAXTA IShI” QURBONLARI"))
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment EventFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            EventFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}