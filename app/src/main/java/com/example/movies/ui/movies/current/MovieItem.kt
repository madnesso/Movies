package com.example.movies.ui.movies.current

import com.example.movies.R
import com.example.movies.data.db.entity.MoviesEntry
import com.example.movies.data.db.unitlocalised.CleanerMovieEntry
import com.example.movies.databinding.ActivityMainBinding
import com.squareup.picasso.Picasso
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_movie.*

class MovieItem(
    private val moviesEntry: CleanerMovieEntry
) : Item() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.apply {
            item_move_name.text = moviesEntry.title
            Picasso.get().load("https://image.tmdb.org/t/p/w500/${moviesEntry.posterPath}")
                .into(item_move_img)
        }
    }

    override fun getLayout(): Int {
        return R.layout.item_movie
    }


}