package com.example.tpexp1.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tpexp1.R
import com.example.tpexp1.data.model.Language
import com.example.tpexp1.databinding.SingleItemBinding

class RcvAdapter(
    private val context: Context,
    private val onClick: (Language) -> Unit,
) : RecyclerView.Adapter<RcvAdapter.ViewHolder>() {

    private lateinit var languageList: MutableList<Language>

    inner class ViewHolder(val binding: SingleItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = SingleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(languageList[position]) {
                Glide.with(context)
                    .load(this.img)
                    .centerCrop()
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_foreground)
                    .into(binding.imgLanguage)
                binding.tvLangName.text = this.name
                binding.tvExp.text = this.exp
                binding.layoutItems.setOnClickListener {
                    onClick(this)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return languageList.size
    }

    fun setList(list: MutableList<Language>) {
        this.languageList = list
        notifyDataSetChanged()
    }
}
