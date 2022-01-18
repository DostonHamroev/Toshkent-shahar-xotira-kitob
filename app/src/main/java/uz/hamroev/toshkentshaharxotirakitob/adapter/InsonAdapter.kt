package uz.hamroev.toshkentshaharxotirakitob.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.realpacific.clickshrinkeffect.applyClickShrink
import render.animations.Attention
import render.animations.Render
import uz.hamroev.toshkentshaharxotirakitob.databinding.ItemInsonBinding
import uz.hamroev.toshkentshaharxotirakitob.room.XotiraEntity

class InsonAdapter(
    var context: Context,
    var list: List<XotiraEntity>,
    var onMyInsonClickListener: OnMyInsonClickListener
) :
    RecyclerView.Adapter<InsonAdapter.VhInson>() {

    inner class VhInson(var itemInsonBinding: ItemInsonBinding) :
        RecyclerView.ViewHolder(itemInsonBinding.root) {


        fun onBind(xotiraEntity: XotiraEntity, position: Int) {

            val anim = Render(context)
            anim.setDuration(900)
            anim.setAnimation(Attention.Bounce(itemInsonBinding.root))
            anim.start()

            itemInsonBinding.insonName.text = xotiraEntity.person_name.trim().toUpperCase()
            itemInsonBinding.insonInfo.text = xotiraEntity.person_info.trim()

            itemInsonBinding.shareIV.setOnClickListener {
                onMyInsonClickListener.onShareClick(xotiraEntity, position)
            }

            itemInsonBinding.shareLinear.setOnClickListener {
                onMyInsonClickListener.onShareClick(xotiraEntity, position)
            }

            itemInsonBinding.root.applyClickShrink()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VhInson {
        return VhInson(ItemInsonBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: VhInson, position: Int) {
        return holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface OnMyInsonClickListener {
        fun onShareClick(xotiraEntity: XotiraEntity, position: Int)
    }
}