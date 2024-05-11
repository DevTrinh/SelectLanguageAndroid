package com.fftools.selectlanguage.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.fftools.selectlanguage.databinding.ItemLanguageBinding
import com.fftools.selectlanguage.model.language.ItemLanguage

class LanguageAdapter(
    private var list: MutableList<ItemLanguage>,
    private var onLanguageAdapterListener: OnLanguageAdapterListener
) :
    RecyclerView.Adapter<LanguageAdapter.ItemLanguageViewHolder>() {
    private var itemLanguage: ItemLanguage? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemLanguageViewHolder {
        val binding =
            ItemLanguageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemLanguageViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(
        holder: ItemLanguageViewHolder,
        @SuppressLint("RecyclerView") position: Int
    ) {
        if (itemLanguage == null) {
            itemLanguage = list[0]
        }
        val language = list[position]
        with(holder.binding) {
            ivFlag.setImageResource(language.flag)
            ivFlag.clipToOutline = true
            tvLanguage.text = language.locale

            clContainsItem.setOnClickListener {
                itemLanguage = language
                notifyDataSetChanged()
                onLanguageAdapterListener.onClickLanguageListener(language.code)
            }

            rbSelected.isChecked = itemLanguage == language
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setCountries(newCountries: MutableList<ItemLanguage>) {
        list = newCountries
        notifyDataSetChanged()
    }

    class ItemLanguageViewHolder(val binding: ItemLanguageBinding) :
        ViewHolder(binding.root)

    interface OnLanguageAdapterListener {
        fun onClickLanguageListener(language: String)
    }
}