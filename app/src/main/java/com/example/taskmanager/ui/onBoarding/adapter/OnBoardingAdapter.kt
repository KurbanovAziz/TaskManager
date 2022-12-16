package com.example.taskmanager.ui.onBoarding.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.taskmanager.R
import com.example.taskmanager.databinding.ItemBoardingBinding
import com.example.taskmanager.ext.loadImage
import com.example.taskmanager.model.OnBoard

class OnBoardingAdapter(
    private val context: Context,
    private val onClick:() -> Unit
) : RecyclerView.Adapter<OnBoardingAdapter.OnBoardingViewHolder>() {


    private val data = arrayListOf<OnBoard>(
        OnBoard(R.drawable.ic_juice_3, "It's available in your favorite cities nowand thy wait! go and orderfour favorite juices"),
        OnBoard(R.drawable.ic_juice_2, "Juice is a beverage made from theextraction or pressing out of natural liquidcontained good quality fruitsfour favorite juices"),
        OnBoard(R.drawable.ic_juice_1, "User can look for their favorite juicesand buy it through the online gatewayprocess or through cash on delivery")
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingViewHolder {
        return OnBoardingViewHolder(ItemBoardingBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false))
    }

    override fun onBindViewHolder(holder: OnBoardingViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class OnBoardingViewHolder(private val binding: ItemBoardingBinding) :
        ViewHolder(binding.root) {
        fun bind(onBoard: OnBoard) {
            binding.tvTitle.text = onBoard.title
            binding.ivBoarding.setImageResource(onBoard.image!!)

            if (adapterPosition == data.lastIndex) {
                binding.tvSkip.text = context.getString(R.string.next)
            } else binding.tvSkip.text = context.getString(R.string.skip)
            binding.tvSkip.setOnClickListener {
                onClick()
            }
        }


    }
}