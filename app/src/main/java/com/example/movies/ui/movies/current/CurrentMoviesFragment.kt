package com.example.movies.ui.movies.current

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.movies.R

class CurrentMoviesFragment : Fragment() {

    companion object {
        fun newInstance() =
            CurrentMoviesFragment()
    }

    private lateinit var viewModel: CurrentMoviesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.current_movies_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CurrentMoviesViewModel::class.java)
        // TODO: Use the ViewModel
    }

}