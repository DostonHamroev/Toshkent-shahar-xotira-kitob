package uz.hamroev.toshkentshaharxotirakitob.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import uz.hamroev.toshkentshaharxotirakitob.adapter.PersonAdapter
import uz.hamroev.toshkentshaharxotirakitob.databinding.FragmentSearchBinding
import uz.hamroev.toshkentshaharxotirakitob.model.Person
import uz.hamroev.toshkentshaharxotirakitob.room.XotiraDao
import uz.hamroev.toshkentshaharxotirakitob.room.XotiraDatabase
import uz.hamroev.toshkentshaharxotirakitob.room.XotiraEntity
import uz.hamroev.toshkentshaharxotirakitob.utils.Status
import uz.hamroev.toshkentshaharxotirakitob.viewmodel.MyViewModel

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

    lateinit var vm : MyViewModel
    lateinit var binding: FragmentSearchBinding
    lateinit var list: ArrayList<Person>
    var personAdapter: PersonAdapter?=null

    lateinit var xotiraDao: XotiraDao
    private  val TAG = "SearchFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(layoutInflater, container, false)

      //  loadData()
        xotiraDao = XotiraDatabase.GET.getXotiraDatabase().getXotiraDao()
        vm = ViewModelProvider(this).get(MyViewModel::class.java)
        vm.getList().observe(viewLifecycleOwner, {
            when (it.status) {
                Status.ERROR -> Toast.makeText(binding.root.context, it.message, Toast.LENGTH_SHORT)
                    .show()
                Status.LOADING->{
                    Toast.makeText(
                        binding.root.context,
                        "Yuklayapman. Qisib tur",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                Status.SUCCESS->{
                    personAdapter =
                        it.data?.let { it1 -> PersonAdapter(binding.root.context, it1, null) }
                    binding.rvSearch.adapter = personAdapter
                }
            }

        })
//        allPerson.forEach{
//            Log.d(TAG, "${it.id}   ${it.person_name}   ${it.person_info}")
//
//        }

//        val personByYearId =
//            XotiraDatabase.GET.getXotiraDatabase().getXotiraDao().getPersonByYearId(12)
//        
//        personByYearId.forEach {
//            Log.d(TAG, "onCreateView: ${it.id} ${it.person_name} - ${it.person_info}")
//        
//        }
//
//        val search = XotiraDatabase.GET.getXotiraDatabase().getXotiraDao().search("Ahmadjon")
//        search.forEach {
//            Log.d(TAG, "onCreateView: ${it.id} ${it.person_name} ${it.person_info} ${it.year_id}")
//
//        }




        //searchItems()
        searchPerson()
        return binding.root
    }

    private fun searchPerson() {
        binding.searchView.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }
            val handler = Handler(Looper.getMainLooper() /*UI thread*/);
            val workRunnable = Runnable {
                Toast.makeText(binding.root.context, "Ishladi", Toast.LENGTH_SHORT).show()
            }
            override fun afterTextChanged(p0: Editable?) {
                    handler.removeCallbacks(workRunnable);
                    handler.postDelayed(workRunnable, 2000 /*delay*/);
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