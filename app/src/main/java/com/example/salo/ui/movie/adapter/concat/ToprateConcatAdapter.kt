package com.example.salo.ui.movie.adapter.concat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.salo.core.BaseConcatHolder
import com.example.salo.databinding.TopratedMoviesRowBinding
import com.example.salo.databinding.UpcommingMovieRowBinding
import com.example.salo.ui.movie.adapter.MovieAdapter

class ToprateConcatAdapter  (private val moviesAdapter: MovieAdapter): RecyclerView.Adapter<BaseConcatHolder<*>>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseConcatHolder<*> {
        val itemBinding=
            TopratedMoviesRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ConcatViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: BaseConcatHolder<*>, position: Int) {
        when(holder){
            is ConcatViewHolder-> holder.bind(moviesAdapter)
        }
    }

    override fun getItemCount(): Int = 1


    private inner class ConcatViewHolder(val binding: TopratedMoviesRowBinding) : BaseConcatHolder<MovieAdapter>(binding.root) {
        override fun bind(adapter: MovieAdapter) {
            binding.rvTopRatedMovies.adapter=adapter
        }

    }
}