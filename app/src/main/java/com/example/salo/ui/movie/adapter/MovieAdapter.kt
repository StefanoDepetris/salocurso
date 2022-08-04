package com.example.salo.ui.movie.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.salo.core.BaseViewHolder
import com.example.salo.data.model.Movie
import com.example.salo.databinding.MovieItemBinding
import com.example.salo.presentation.MovieViewModel

//Creamos un ViewHolder
//Este adaptador es para cada uno de los elementos dentro de la lista de cada categoria..
class MovieAdapter(
    private val movieList: List<Movie>,
    private val itemClickListener: onMovieClickListener
) : RecyclerView.Adapter<BaseViewHolder<*>>() {


    interface onMovieClickListener {
        fun onMovieClick(movie: Movie)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {

        //Buscamos como se va a ver cada unos de los elementos en la lista de peliculas
        val itemBinding = MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        //
        val holder = MoviesViewHolder(itemBinding, parent.context)

        //attach click para eso tengo que crear un interfaz ACA ESTA EL CLICK DE CADA ELEMENTO

        itemBinding.root.setOnClickListener {
            val position = holder.bindingAdapterPosition.takeIf { it != DiffUtil.DiffResult.NO_POSITION }
                    ?: return@setOnClickListener//Si apreto algun elemento  si es -1 no devuelve nada

            itemClickListener.onMovieClick(movieList[position])
        }

        return holder
    }

    //a cada elemento de la lista neceismos poner este objeto
    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when(holder){
            is MoviesViewHolder-> holder.bind(movieList[position])
        }
    }

    override fun getItemCount(): Int=movieList.size




    //Al se inner forma parte de movieAdapter cuando instance MovieAdapter si instancia esta por eso el inner.
    // El inner lo que hace es que cuando muera MovieAapter(el objeto muere este tambien)

    //Binding.root es toda layout completa es decir todo  Movieitem

    //Viewholder se crea apartior de la vista itemBinding, este BaseViewHolder tiene una movie.
    // Y le pasamos una vista para poder bindearle la pelicula.
    private inner class MoviesViewHolder(val binding: MovieItemBinding, val context: Context) :
        BaseViewHolder<Movie>(binding.root) {
        override fun bind(item: Movie) {

            Log.d("Poster Image ","poster Image  ${item.poster_path}")
            Glide.with(context).load("https://image.tmdb.org/t/p/w500/${item.poster_path}")
                .centerCrop().into(binding.imageMovie)
        }
    }

}