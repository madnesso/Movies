package com.example.movies.ui.movies.current

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.R
import com.example.movies.data.db.entity.MoviesEntry
import com.example.movies.data.db.unitlocalised.CleanerMovieEntry
import kotlinx.android.synthetic.main.item_movie.*
import com.example.movies.ui.base.ScopedFragment
import com.xwray.groupie.GroupAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import kotlinx.android.synthetic.main.current_movies_fragment.*


class CurrentMoviesFragment : ScopedFragment(), KodeinAware {


    companion object {
        fun newInstance() =
            CurrentMoviesFragment()
    }

    override val kodein by closestKodein()
    private val viewModelFactory: MovieFactory by instance()

    private lateinit var viewModel: CurrentMoviesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.current_movies_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(CurrentMoviesViewModel::class.java)
        bindUI()
    }

    private fun bindUI() = launch(Dispatchers.Main) {
        val moviesEntries = viewModel.moviesEntries.await()

        moviesEntries.observe(viewLifecycleOwner, Observer { movieEntries ->
            if (movieEntries == null) return@Observer
            initRecyclerView(movieEntries.movieItems())
        })
    }

    private fun List<CleanerMovieEntry>.movieItems(): List<MovieItem> {
        return this.map {
            MovieItem(it)
        }
    }

    private fun initRecyclerView(items: List<MovieItem>) {
        val groupAdapter = GroupAdapter<GroupieViewHolder>().apply {
            addAll(items)
        }

        moviesrecycler.apply {
            layoutManager = LinearLayoutManager(this@CurrentMoviesFragment.context)
            adapter = groupAdapter
        }

        groupAdapter.setOnItemClickListener { item, view ->
            (item as? MovieItem)?.let {
                //it.moviesEntry.id.let { it1 -> showWeatherDetail(it1, view) }
            }
        }
    }

//    private fun showWeatherDetail(id: Int, view: View) {
//        val actionDetail = FutureListWeatherFragmentDirections.actionDetail
//    }
}