package com.example.thi_comand_work.presentation.singIn.cardList

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import androidx.recyclerview.widget.RecyclerView
import com.example.thi_comand_work.R
import com.example.thi_comand_work.domain.objects.House
import com.example.thi_comand_work.presentation.singIn.FullHouseCardFragment
import com.example.thi_comand_work.presentation.singIn.MainSingInActivity

class CardsAdapter(private val houseList: MutableList<House>, private val context: Context) :
    RecyclerView.Adapter<CardsAdapter.ContactHolder>() {

    class ContactHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvImgPrev: ImageView = itemView.findViewById(R.id.imageView)
        val tvName: TextView = itemView.findViewById(R.id.nameCard)
        val tvPrice: TextView = itemView.findViewById(R.id.textPriceView)
        val tvStatusPrice: ImageView = itemView.findViewById(R.id.recomImageView)
        val tvAdress: TextView = itemView.findViewById(R.id.textArdessView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.card_home_shablon, parent, false)
        return ContactHolder(view).apply {
            itemView.setOnClickListener {
                val pos = adapterPosition
                if (pos != RecyclerView.NO_POSITION) {
                    val scaleXUp = ObjectAnimator.ofFloat(itemView, "scaleX", 1.2f)
                    val scaleYUp = ObjectAnimator.ofFloat(itemView, "scaleY", 1.2f)
                    val scaleXDown = ObjectAnimator.ofFloat(itemView, "scaleX", 1.0f)
                    val scaleYDown = ObjectAnimator.ofFloat(itemView, "scaleY", 1.0f)

                    val animatorSet = AnimatorSet().apply {
                        play(scaleXUp).with(scaleYUp)
                        play(scaleXDown).with(scaleYDown).after(scaleXUp)
                        duration = 300
                        interpolator = FastOutSlowInInterpolator()
                        addListener(object : AnimatorListenerAdapter() {
                            override fun onAnimationEnd(animation: Animator) {
                                val house = houseList[pos]
                                val context = itemView.context as MainSingInActivity
                                context.openCardFragment(FullHouseCardFragment.newInstance(house))
                                onItemClickListener?.invoke(pos)
                            }
                        })
                    }

                    animatorSet.start()
                }
            }
        }
    }

    override fun onBindViewHolder(holder: ContactHolder, position: Int) {
        val house = houseList[position]
        holder.tvName.text = house.name
        holder.tvAdress.text = house.address
        holder.tvStatusPrice.setImageResource(R.drawable.big_price)
        holder.tvPrice.text = house.price.toString()
        //holder.tvImgPrev.imageMatrix = todo подкачка изображений!!!
    }

    override fun getItemCount(): Int = houseList.size

    private var onItemClickListener: ((Int) -> Unit)? = null

    fun setOnItemClickListener(listener: (Int) -> Unit) {
        onItemClickListener = listener
    }
}

interface OnItemClickListener {
    fun onItemClick(position: Int)
}
