package com.example.movies.data.recyc

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.R
import com.example.movies.data.db.entity.MoviesEntry
import com.example.movies.ui.ResultActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_movie.view.*

class MoviesAdapter(private val context: Context, private val moviesList: List<MoviesEntry>) :
    RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val movieViewLayout = layoutInflater.inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(movieViewLayout)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindMovieData(moviesList[position])

    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val image = itemView.item_move_img
        private val title = itemView.item_move_name

        fun bindMovieData(movie: MoviesEntry) {
            Picasso.get().load("https://image.tmdb.org/t/p/w500/${movie.poster_path}").into(image)
            title.text = movie.title
            image.setOnClickListener {
                val I = Intent(context, ResultActivity::class.java)
                I.putExtra("overview", movie.overview) //string
                I.putExtra("title", movie.title) //string
                I.putExtra("release date", movie.release_date) //string
                I.putExtra("image", movie.poster_path)//string
                context.startActivity(I)
            }

        }
    }

}