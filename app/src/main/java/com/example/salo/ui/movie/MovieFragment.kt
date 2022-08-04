package com.example.salo.ui.movie

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import com.example.salo.R
import com.example.salo.core.Resource
import com.example.salo.data.model.Movie
import com.example.salo.data.remote.MovieDataSource
import com.example.salo.databinding.FragmentMovieBinding
import com.example.salo.databinding.FragmentMovieDetaildBinding
import com.example.salo.presentation.MovieViewModel
import com.example.salo.presentation.MovieViewModelFactory
import com.example.salo.repository.MovieRepositoryImpl
import com.example.salo.repository.RetrofitClient
import com.example.salo.ui.movie.adapter.MovieAdapter
import com.example.salo.ui.movie.adapter.concat.PopularConcatAdapter
import com.example.salo.ui.movie.adapter.concat.ToprateConcatAdapter
import com.example.salo.ui.movie.adapter.concat.UpcomingConcatAdapter


class MovieFragment : Fragment(R.layout.fragment_movie), MovieAdapter.onMovieClickListener {

    //Primero se crea la vista con Fragment(R.layout.fragment_movie)


    private lateinit var binding: FragmentMovieBinding


    private val viewModel by viewModels<MovieViewModel> {

        //Inyeccion de depencias manual cuando creo la instancia de ViewModel
        MovieViewModelFactory(MovieRepositoryImpl(MovieDataSource(RetrofitClient.webservice)))

    }

    private lateinit var concatAdapter: ConcatAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovieBinding.bind(view)
        concatAdapter = ConcatAdapter()

        //El primer parametro del observe es clave porque es el lifecycleOwner, pasarle mal ese primer paramtro va a ahcer que se creen mucho observe.Con viewLifecycleOwner se da cuenta
        //de si tiene un observe sin necesidad de andar desctruyendo el mismo si se da vuelta la pantalla.
        viewModel.fetchUMainScreenMovies().observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    Log.d("Livedata upcoming", "Loading...")
                }
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE


                    //Como tenemos un recyclerView que cada item es un RecyclerView, lo trabajamos con un solo adapter que vive en el recycler view principal  y que a su vez pero con 3 Concat
                    concatAdapter.apply {
                        addAdapter(
                            0,
                            UpcomingConcatAdapter(
                                MovieAdapter(
                                    result.data.first.results,
                                    this@MovieFragment
                                )
                            )
                        )
                        addAdapter(
                            1,
                            ToprateConcatAdapter(
                                MovieAdapter(
                                    result.data.second.results,
                                    this@MovieFragment
                                )
                            )
                        )
                        addAdapter(
                            2,
                            PopularConcatAdapter(
                                MovieAdapter(
                                    result.data.third.results,
                                    this@MovieFragment
                                )
                            )
                        )
                    }

                    binding.rvMovies.adapter = concatAdapter

                    Log.d(
                        "Livedata ",
                        "Upcoming: ${result.data.first} " +
                                "\n\nPopular: ${result.data.second}  " +
                                "\n" + " \n" +
                                " Top rate: ${result.data.third} "
                    )
                }
                is Resource.Failure -> {
                    binding.progressBar.visibility = View.GONE
                    Log.d("Erorr", "${result.exception}")
                }

            }
        })

    }

    override fun onMovieClick(movie: Movie) {
        Log.d("movie", "onmovieclick $movie")

        val action = MovieFragmentDirections.actionMovieFragmentToMovieDetaildFragment(
            movie.poster_path,
            movie.backdrop_path,
            movie.vote_average.toFloat(),
            movie.vote_count,
            movie.overview,
            movie.title,
            movie.original_language,
            movie.release_date
        )
        findNavController().navigate(action)

    }
}