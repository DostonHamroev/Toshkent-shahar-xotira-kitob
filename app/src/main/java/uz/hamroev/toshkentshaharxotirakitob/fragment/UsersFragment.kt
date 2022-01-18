package uz.hamroev.toshkentshaharxotirakitob.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import uz.hamroev.toshkentshaharxotirakitob.R
import uz.hamroev.toshkentshaharxotirakitob.adapter.UserAdapter
import uz.hamroev.toshkentshaharxotirakitob.databinding.FragmentUsersBinding
import uz.hamroev.toshkentshaharxotirakitob.model.MyUser

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [UsersFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class UsersFragment : Fragment() {
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


    lateinit var binding: FragmentUsersBinding
    lateinit var list: ArrayList<MyUser>
    lateinit var userAdapter: UserAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUsersBinding.inflate(layoutInflater, container, false)

        loadData()
        userAdapter = UserAdapter(binding.root.context, list)
        binding.rvUsers.adapter = userAdapter

        return binding.root
    }

    private fun loadData() {
        list = ArrayList()
        list.add(MyUser("Zamira\nRaimovna\nIshonxodjayeva","Professor O'zMU",R.drawable.ic_zamira))
        list.add(MyUser("Saida\nBeknazarova\nSafibullayevna","Professor\nt.f.d.\nMuhammad al-Xorazmiy nomidagi Toshkent Аxborot texnologiyalari universiteti Аudiovizual texnologiyalar kafedrasi professori",R.drawable.ic_saida))
        list.add(MyUser("Doston\nHamroyev\nDavron o'g'li","Android Dasturchi\n" + "\n" + "Muhammad al-Xorazmiy nomidagi Toshkent Аxborot texnologiyalari universiteti Televizion Texnologiyalari fakulteti talabasi",R.drawable.ic_doston))
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment UsersFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            UsersFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}