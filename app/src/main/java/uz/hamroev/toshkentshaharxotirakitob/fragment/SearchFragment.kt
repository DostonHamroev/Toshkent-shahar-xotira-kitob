package uz.hamroev.toshkentshaharxotirakitob.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import uz.hamroev.toshkentshaharxotirakitob.R
import uz.hamroev.toshkentshaharxotirakitob.adapter.InsonAdapter
import uz.hamroev.toshkentshaharxotirakitob.adapter.PersonSearchAdapter
import uz.hamroev.toshkentshaharxotirakitob.database.PersonDatabase
import uz.hamroev.toshkentshaharxotirakitob.database.PersonEntity
import uz.hamroev.toshkentshaharxotirakitob.databinding.FragmentSearchBinding
import uz.hamroev.toshkentshaharxotirakitob.room.XotiraDao
import uz.hamroev.toshkentshaharxotirakitob.room.XotiraDatabase
import uz.hamroev.toshkentshaharxotirakitob.room.XotiraEntity

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
    lateinit var insonAdapter: InsonAdapter

    lateinit var personSearchAdapter: PersonSearchAdapter
    lateinit var listSearchPerson: List<PersonEntity>
    lateinit var personSearchDatabase: PersonDatabase

    var message: String = ""
    var shareMessage: String =
        "https://play.google.com/store/apps/details?id="
    lateinit var xotiraDao: XotiraDao
    private val TAG = "SearchFragment"

    @SuppressLint("ResourceAsColor")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(layoutInflater, container, false)

        //  loadData()
        xotiraDao = XotiraDatabase.GET.getXotiraDatabase().getXotiraDao()
        shareMessage = "https://play.google.com/store/apps/details?id=${activity?.packageName}"

        personSearchDatabase = PersonDatabase.getInstance(binding.root.context)
        listSearchPerson = personSearchDatabase.personDao().getAllPerson()
//        val search = XotiraDatabase.GET.getXotiraDatabase().getXotiraDao().search("Ahmadjon")
//        search.forEach {
//            Log.d(TAG, "onCreateView: ${it.id} ${it.person_name} ${it.person_info} ${it.year_id}")
//
//        }
//        var emphasisTextView = binding.ism as EmphasisTextView
//        emphasisTextView.setText("Abbosjon")
//        emphasisTextView.setTextToHighlight("jo")
//        emphasisTextView.setHighlightColor(R.color.sariq)
//        emphasisTextView.setCaseInsensitive(true)
//        emphasisTextView.highlight()

        searchItems()
        isHaveSearchPerson()

        return binding.root
    }

    private fun searchItems() {

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                if (p0!!.isEmpty()) {
                    // binding.rvHome.adapter = homeAdapter
                    // Toast.makeText(binding.root.context, "ok", Toast.LENGTH_SHORT).show()
                    isHaveSearchPerson()
                } else {
                    if (p0.trim().length >= 4) {
                        filter(p0.toString())
                    } else {
                        Toast.makeText(
                            binding.root.context,
                            "4 ta belgidan kam bo'lmagan so'z kiriting !",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                if (p0!!.isEmpty()) {
                    // binding.rvHome.adapter = homeAdapter
                    isHaveSearchPerson()
                    // Toast.makeText(binding.root.context, "", Toast.LENGTH_SHORT).show()
                } else {
                    //filter(p0.toString())
                }
                return true
            }

        })
    }

    private fun filter(name: String) {
        try {
            val searchList = XotiraDatabase.GET.getXotiraDatabase().getXotiraDao().search(name)

            if (searchList.isEmpty()) {
                Toast.makeText(binding.root.context, "Topilmadi", Toast.LENGTH_SHORT).show()
                isHaveSearchPerson()
            } else {
                binding.notSearchYetLinear.visibility = View.GONE
                insonAdapter = InsonAdapter(
                    binding.root.context,
                    searchList,
                    object : InsonAdapter.OnMyInsonClickListener {
                        override fun onShareClick(xotiraEntity: XotiraEntity, position: Int) {
                            val name = "Toshkent Shahar - Xotira Kitob"
                            message = "$name\n\n" +
                                    "* * * * * * *\n\n" +
                                    "" +
                                    "${searchList[position].person_name} -\n\n" +
                                    "${searchList[position].person_info}\n" +
                                    "\n* * * * * * *\n" +
                                    "$shareMessage"

                            val intent = Intent(Intent.ACTION_SEND)
                            intent.type = "text/plain"
                            intent.putExtra(Intent.EXTRA_TEXT, message)
                            val chooser = Intent.createChooser(intent, "Share using...")
                            startActivity(chooser)
                        }

                        override fun onItemClick(
                            xotiraEntity: XotiraEntity,
                            position: Int,
                            view: View
                        ) {
                            view.setBackgroundResource(R.drawable.gradient_card2)
                            val personName = searchList[position].person_name
                            val personInfo = searchList[position].person_info
                            val personEntity = PersonEntity()
                            personEntity.person_name = personName
                            personEntity.person_info = personInfo
                            personSearchDatabase.personDao().addPerson(personEntity)
                            binding.notSearchYetLinear.visibility = View.GONE
                        }
                    })
                binding.rvSearch.adapter = insonAdapter


            }


        } catch (e: Exception) {

        }

    }


    private fun isHaveSearchPerson() {
        if (listSearchPerson.isEmpty()) {

        } else {
            binding.notSearchYetLinear.visibility = View.GONE
            personSearchAdapter = PersonSearchAdapter(
                binding.root.context,
                listSearchPerson,
                object : PersonSearchAdapter.OnMyPersonSearchClickListener {
                    override fun onShare(personEntity: PersonEntity, position: Int) {
                        val name = "Toshkent Shahar - Xotira Kitob"
                        message = "$name\n\n" +
                                "* * * * * * *\n\n" +
                                "" +
                                "${listSearchPerson[position].person_name} -\n\n" +
                                "${listSearchPerson[position].person_info}\n" +
                                "\n* * * * * * *\n" +
                                "$shareMessage"

                        val intent = Intent(Intent.ACTION_SEND)
                        intent.type = "text/plain"
                        intent.putExtra(Intent.EXTRA_TEXT, message)
                        val chooser = Intent.createChooser(intent, "Share using...")
                        startActivity(chooser)
                    }
                })
            binding.rvSearch.adapter = personSearchAdapter
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

