package uz.hamroev.toshkentshaharxotirakitob.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.realpacific.clickshrinkeffect.applyClickShrink
import render.animations.Attention
import render.animations.Render
import uz.hamroev.toshkentshaharxotirakitob.R
import uz.hamroev.toshkentshaharxotirakitob.databinding.ItemEventBinding
import uz.hamroev.toshkentshaharxotirakitob.databinding.ItemEventHomeBinding
import uz.hamroev.toshkentshaharxotirakitob.model.Event

class EventHomeAdapter(
    var context: Context,
    var list: ArrayList<Event>,
    var onMyEventClickListener: OnMyEventClickListener
) : RecyclerView.Adapter<EventHomeAdapter.VhEvent>() {

    inner class VhEvent(var itemEventHomeBinding: ItemEventHomeBinding) :
        RecyclerView.ViewHolder(itemEventHomeBinding.root) {


        fun onBind(event: Event, position: Int) {
            itemEventHomeBinding.yearNameTv.text = event.year_name

            val anim = Render(context)
            anim.setDuration(1000)
            anim.setAnimation(Attention.Bounce(itemEventHomeBinding.root))
            anim.start()

            if (position == 0 || position == 4 || position == 8 || position == 12 || position == 16) {
                itemEventHomeBinding.dotLinear.setBackgroundResource(R.color.green)
                itemEventHomeBinding.mainLinear.setBackgroundResource(R.drawable.gradient_card1)
            }
            if (position == 1 || position == 5 || position == 9 || position == 13 || position == 17) {
                itemEventHomeBinding.dotLinear.setBackgroundResource(R.color.red)
                itemEventHomeBinding.mainLinear.setBackgroundResource(R.drawable.gradient_card2)
            }
            if (position == 2 || position == 6 || position == 10 || position == 14 || position == 18) {
                itemEventHomeBinding.dotLinear.setBackgroundResource(R.color.yellow)
                itemEventHomeBinding.mainLinear.setBackgroundResource(R.drawable.gradient_card3)
            }
            if (position == 3 || position == 7 || position == 11 || position == 15 || position == 19) {
                itemEventHomeBinding.dotLinear.setBackgroundResource(R.color.blue)
                itemEventHomeBinding.mainLinear.setBackgroundResource(R.drawable.gradient_card4)
            }


            itemEventHomeBinding.root.setOnClickListener {
                itemEventHomeBinding.root.applyClickShrink()
                onMyEventClickListener.onEventClick(event, position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VhEvent {
        return VhEvent(ItemEventHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: VhEvent, position: Int) {
        return holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface OnMyEventClickListener {
        fun onEventClick(event: Event, position: Int)
    }
}