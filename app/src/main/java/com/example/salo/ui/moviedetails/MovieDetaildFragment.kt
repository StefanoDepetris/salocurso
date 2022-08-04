package com.example.salo.ui.moviedetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.salo.R
import com.example.salo.databinding.FragmentMovieDetaildBinding

// TODO: Rename parameter arguments, choose names that match
class MovieDetaildFragment : Fragment(R.layout.fragment_movie_detaild) {

    private lateinit var binding: FragmentMovieDetaildBinding

    private val args by navArgs<MovieDetaildFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding= FragmentMovieDetaildBinding.bind(view)
        Glide.with(requireContext()).load("https://image.tmdb.org/t/p/w500/${args.posterImageUrl}").centerCrop().into(binding.imbMovie)

        Glide.with(requireContext()).load("https://image.tmdb.org/t/p/w500/${args.backGroundImageUrl}").centerCrop().into(binding.imgBackground)

        binding.txtDescription.text=args.overview
        binding.tvTitleMovie.text=args.tittle
        binding.tvLanguage.text="Language ${args.language}"
        binding.tvRating.text= "${args.voteAverage} (${args.voteCount} Reviews)"
        binding.tvCalendar.text= "Released ${args.releaseDate}"


        super.onViewCreated(view, savedInstanceState)
    }


}