package com.example.salo.core

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseConcatHolder<T>(itemView: View): RecyclerView.ViewHolder(itemView) {
    //Aca bindeamos un adapter y no un item.
    abstract fun bind(adapter:T)

}