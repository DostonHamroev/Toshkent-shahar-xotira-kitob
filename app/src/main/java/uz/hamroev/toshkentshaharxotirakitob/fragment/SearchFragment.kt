package uz.hamroev.toshkentshaharxotirakitob.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import uz.hamroev.toshkentshaharxotirakitob.adapter.PersonAdapter
import uz.hamroev.toshkentshaharxotirakitob.databinding.FragmentSearchBinding
import uz.hamroev.toshkentshaharxotirakitob.model.Person

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SearchFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SearchFragment : Fragment() {
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

    lateinit var binding: FragmentSearchBinding
    lateinit var list: ArrayList<Person>
    lateinit var personAdapter: PersonAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(layoutInflater, container, false)

        loadData()
        personAdapter = PersonAdapter(
            binding.root.context,
            list,
            object : PersonAdapter.OnMyPersonClickListener {
                override fun onPersionClick(person: Person, position: Int) {
                    Toast.makeText(binding.root.context, "$position", Toast.LENGTH_SHORT).show()
                }

                override fun onShare(person: Person, position: Int) {
                    Toast.makeText(binding.root.context, "Share", Toast.LENGTH_SHORT).show()

                }
            })
        binding.rvSearch.adapter = personAdapter

        //searchItems()
        searchPerson()
        return binding.root
    }

    private fun searchPerson() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                if (p0!!.isEmpty()) {

                } else
                    filterPerson(p0.toString())
                return true

            }

            override fun onQueryTextChange(p0: String?): Boolean {
                if (p0!!.isEmpty()) {

                } else
                    filterPerson(p0.toString())
                return true

            }
        })
    }

    private fun loadData() {
        list = ArrayList()
        list.add(Person("Sardor"))
        list.add(Person("Sanjar"))
        list.add(Person("Sarvar"))
        list.add(Person("Sevinch"))
        list.add(Person("Zarina"))
        list.add(Person("Nigora"))
        list.add(Person("Ozod"))
        list.add(Person("Ozoda"))
        list.add(Person("Maftuna"))
        list.add(Person("Sardor"))
        list.add(Person("Sanjar"))
        list.add(Person("Sarvar"))
        list.add(Person("Sevinch"))
        list.add(Person("Zarina"))
        list.add(Person("Nigora"))
        list.add(Person("Ozod"))
        list.add(Person("Ozoda"))
        list.add(Person("Maftuna"))
        list.add(Person("Sardor"))
        list.add(Person("Sanjar"))
        list.add(Person("Sarvar"))
        list.add(Person("Sevinch"))
        list.add(Person("Zarina"))
        list.add(Person("Nigora"))
        list.add(Person("Ozod"))
        list.add(Person("Ozoda"))
        list.add(Person("Maftuna"))
        list.add(Person("Sardor"))
        list.add(Person("Sanjar"))
        list.add(Person("Sarvar"))
        list.add(Person("Sevinch"))
        list.add(Person("Zarina"))
        list.add(Person("Nigora"))
        list.add(Person("Ozod"))
        list.add(Person("Ozoda"))
        list.add(Person("Maftuna"))
    }

    private fun filterPerson(name: String) {


    }

    private fun filter(string: String) {
        // val list = ArrayList<Mavzu>()
        // val list2 = ArrayList<Main3Mavzu>()
//        for (datum in data) {
//            if (
//                datum.info1Krill!!.contains(string) || datum.info1Ru!!.contains(string)
//                || datum.info1Uz!!.contains(string) || datum.title1Krill!!.contains(string)
//                || datum.title1Ru!!.contains(string) || datum.title1Uz!!.contains(string)
//            ) {
//                list.add(datum)
//                var searchAdapter = SearchAdapter(list,Cache.til!!)
//                binding.rvHome.adapter = searchAdapter
//            }
//        }
//        for (datum in data2) {
//            if (datum.infoKrill!!.contains(string) || datum.titleKrill!!.contains(string)
//                || datum.inforu!!.contains(string) || datum.titleRu!!.contains(string)
//                || datum.infoUz!!.contains(string) || datum.titleUz!!.contains(string)){
//                list2.add(datum)
//                var search2Adapter = Search2Adapter(list2, Cache.til!!)
//                binding.rvHome.adapter = search2Adapter
//            }
//
//
//        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SearchFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SearchFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}