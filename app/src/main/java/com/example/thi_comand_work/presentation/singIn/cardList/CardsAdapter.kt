package com.example.thi_comand_work.presentation.singIn.cardList

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.thi_comand_work.R
import com.example.thi_comand_work.domain.objects.House
import com.example.thi_comand_work.presentation.singIn.FullHouseCardFragment
import com.example.thi_comand_work.presentation.singIn.MainSingInActivity

class CardsAdapter(val houseList: MutableList<House>, context: Context):
    RecyclerView.Adapter<CardsAdapter.ContactHolder>() {

    val _context: Context = context
    class ContactHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvImgPrev: ImageView
        var tvName: TextView
        var tvPrice: TextView
        var tvStatusPrice: ImageView
        var tvAdress:TextView

        init {
            tvName = itemView.findViewById(R.id.nameCard)
            tvPrice = itemView.findViewById(R.id.textPriceView)
            tvImgPrev = itemView.findViewById(R.id.imageView)
            tvStatusPrice = itemView.findViewById(R.id.recomImageView)
            tvAdress = itemView.findViewById(R.id.textArdessView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.card_home_shablon, parent, false)
        val holder = ContactHolder(view)

        view.setOnClickListener {
            val pos = holder.adapterPosition
            if (pos != RecyclerView.NO_POSITION) {
                val house = houseList[pos]
                val context = _context as MainSingInActivity
                context.openFragment(FullHouseCardFragment.newInstance(house))
                onItemClickListener?.invoke(pos)
            }
        }

        return holder
    }





    override fun onBindViewHolder(holder: ContactHolder, position: Int) {
        holder.tvName.text = houseList[position].name
        holder.tvAdress.text =houseList[position].address
        holder.tvStatusPrice.setImageResource(R.drawable.big_price)
        //holder.tvImgPrev.imageMatrix = todo подкачка изображений!!!
        holder.tvPrice.text = houseList[position].price.toString()
    }
    override fun getItemCount(): Int {
        return houseList.size
    }
    private var onItemClickListener: ((Int)->Unit)? = null
    fun setOnItemClickListener(f: (Int) -> Unit) { onItemClickListener = f }



}
interface OnItemClickListener {
    fun onItemClick(position: Int)
}