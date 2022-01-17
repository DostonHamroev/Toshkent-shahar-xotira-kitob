package uz.hamroev.toshkentshaharxotirakitob.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.hamroev.toshkentshaharxotirakitob.databinding.ItemPersonBinding
import uz.hamroev.toshkentshaharxotirakitob.model.Person
import uz.hamroev.toshkentshaharxotirakitob.room.XotiraEntity

class PersonAdapter(
    var context: Context,
    var list: List<XotiraEntity>,
    var onMyPersonClickListener: OnMyPersonClickListener?=null
) :
    RecyclerView.Adapter<PersonAdapter.VhPerson>() {

    inner class VhPerson(var itemPersonBinding: ItemPersonBinding) :
        RecyclerView.ViewHolder(itemPersonBinding.root) {

        fun onBind(xotiraEntity: XotiraEntity, position: Int) {

            itemPersonBinding.userInfo.text = xotiraEntity.person_name

//            itemPersonBinding.shareBtn.setOnClickListener {
//                onMyPersonClickListener?.onShare(xotiraEntity, position)
//            }
//
//            itemPersonBinding.root.setOnClickListener {
//                onMyPersonClickListener?.onPersionClick(xotiraEntity, position)
//
//            }
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

        fun onPersionClick(xotiraEntity: XotiraEntity, position: Int)

        fun onShare(xotiraEntity: XotiraEntity, position: Int)
    }
}