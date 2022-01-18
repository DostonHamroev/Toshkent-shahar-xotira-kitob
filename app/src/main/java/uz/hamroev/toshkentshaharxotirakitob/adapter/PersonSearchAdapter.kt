package uz.hamroev.toshkentshaharxotirakitob.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.hamroev.toshkentshaharxotirakitob.database.PersonEntity
import uz.hamroev.toshkentshaharxotirakitob.databinding.ItemPersonSearchBinding

class PersonSearchAdapter(
    var context: Context,
    var list: List<PersonEntity>,
    var onMyPersonSearchClickListener: OnMyPersonSearchClickListener
) :
    RecyclerView.Adapter<PersonSearchAdapter.VhPersonSearch>() {

    inner class VhPersonSearch(var itemPersonSearchBinding: ItemPersonSearchBinding) :
        RecyclerView.ViewHolder(itemPersonSearchBinding.root) {

        fun onBind(personEntity: PersonEntity, position: Int) {
            itemPersonSearchBinding.personName.text = personEntity.person_name
            itemPersonSearchBinding.personInfo.text = personEntity.person_info

            itemPersonSearchBinding.shareBtn.setOnClickListener {
                onMyPersonSearchClickListener.onShare(personEntity, position)

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VhPersonSearch {
        return VhPersonSearch(
            ItemPersonSearchBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: VhPersonSearch, position: Int) {
        return holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface OnMyPersonSearchClickListener {

        fun onShare(personEntity: PersonEntity, position: Int)
    }


}