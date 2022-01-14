package uz.hamroev.toshkentshaharxotirakitob.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.hamroev.toshkentshaharxotirakitob.databinding.ItemPersonBinding
import uz.hamroev.toshkentshaharxotirakitob.model.Person

class PersonAdapter(
    var context: Context,
    var list: ArrayList<Person>,
    var onMyPersonClickListener: OnMyPersonClickListener
) :
    RecyclerView.Adapter<PersonAdapter.VhPerson>() {

    inner class VhPerson(var itemPersonBinding: ItemPersonBinding) :
        RecyclerView.ViewHolder(itemPersonBinding.root) {

        fun onBind(person: Person, position: Int) {

            itemPersonBinding.userInfo.text = person.person

            itemPersonBinding.shareBtn.setOnClickListener {
                onMyPersonClickListener.onShare(person, position)
            }

            itemPersonBinding.root.setOnClickListener {
                onMyPersonClickListener.onPersionClick(person, position)

            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VhPerson {
        return VhPerson(
            ItemPersonBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: VhPerson, position: Int) {
        return holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface OnMyPersonClickListener {

        fun onPersionClick(person: Person, position: Int)

        fun onShare(person: Person, position: Int)
    }
}