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
import uz.hamroev.toshkentshaharxotirakitob.model.Event

class EventAdapter(
    var context: Context,
    var list: ArrayList<Event>,
    var onMyEventClickListener: OnMyEventClickListener
) : RecyclerView.Adapter<EventAdapter.VhEvent>() {

    inner class VhEvent(var itemEventBinding: ItemEventBinding) :
        RecyclerView.ViewHolder(itemEventBinding.root) {


        fun onBind(event: Event, position: Int) {
            itemEventBinding.yearNameTv.text = event.year_name

            val anim = Render(context)
            anim.setDuration(1000)
            anim.setAnimation(Attention.Bounce(itemEventBinding.root))
            anim.start()

            if (position == 0 || position == 4 || position == 8 || position == 12 || position == 16) {
                itemEventBinding.dotLinear.setBackgroundResource(R.color.green)
                itemEventBinding.mainLinear.setBackgroundResource(R.drawable.gradient_card1)
            }
            if (position == 1 || position == 5 || position == 9 || position == 13 || position == 17) {
                itemEventBinding.dotLinear.setBackgroundResource(R.color.red)
                itemEventBinding.mainLinear.setBackgroundResource(R.drawable.gradient_card2)
            }
            if (position == 2 || position == 6 || position == 10 || position == 14 || position == 18) {
                itemEventBinding.dotLinear.setBackgroundResource(R.color.yellow)
                itemEventBinding.mainLinear.setBackgroundResource(R.drawable.gradient_card3)
            }
            if (position == 3 || position == 7 || position == 11 || position == 15 || position == 19) {
                itemEventBinding.dotLinear.setBackgroundResource(R.color.blue)
                itemEventBinding.mainLinear.setBackgroundResource(R.drawable.gradient_card4)
            }


            itemEventBinding.root.setOnClickListener {
                itemEventBinding.root.applyClickShrink()
                onMyEventClickListener.onEventClick(event, position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VhEvent {
        return VhEvent(ItemEventBinding.inflate(LayoutInflater.from(parent.context), parent, false))
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