package com.example.salo.ui.movie.adapter.concat

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.salo.core.BaseConcatHolder
import com.example.salo.core.BaseViewHolder
import com.example.salo.data.model.Movie
import com.example.salo.databinding.MovieItemBinding
import com.example.salo.databinding.PopularMoviesRowBinding
import com.example.salo.ui.movie.adapter.MovieAdapter

//Esto es para cada seccion y en vez de pasarle una lista le pasamos un adapter
class PopularConcatAdapter(private val moviesAdapter:MovieAdapter): RecyclerView.Adapter<BaseConcatHolder<*>>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseConcatHolder<*> {
        val itemBinding=PopularMoviesRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ConcatViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: BaseConcatHolder<*>, position: Int) {
        when(holder){
            is ConcatViewHolder-> holder.bind(moviesAdapter)
        }
    }

    //Porque hay un solo adapter que hacer el concat
    override fun getItemCount(): Int = 1


    private inner class ConcatViewHolder(val binding: PopularMoviesRowBinding) : BaseConcatHolder<MovieAdapter>(binding.root) {
        override fun bind(adapter: MovieAdapter) {
            binding.rvPopularMovies.adapter=adapter
        }

    }
}