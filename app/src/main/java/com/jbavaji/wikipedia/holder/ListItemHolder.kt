package com.jbavaji.wikipedia.holder

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.gson.Gson
import com.jbavaji.wikipedia.R
import com.jbavaji.wikipedia.activities.ArticleDetailsActivity
import com.jbavaji.wikipedia.activities.SearchActivity
import com.jbavaji.wikipedia.model.WikiPage
import com.squareup.picasso.Picasso


/**
 * Created by Jayeshgiri Bavaji on 4/12/2019.
 * Synechron Technologies
 * jayeshgiri.bavaji@synechron.com
 */
class ListItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val resultImageView: ImageView = itemView.findViewById<ImageView>(R.id.result_icon)
    private val titleTextView: TextView = itemView.findViewById<TextView>(R.id.result_title)

    private var currentPage: WikiPage? = null

    init {
        itemView.setOnClickListener { view: View? ->
            var detailsIntent = Intent(itemView.context, ArticleDetailsActivity::class.java)
            var pageJson = Gson().toJson(currentPage)
            detailsIntent.putExtra("page", pageJson)
            itemView.context.startActivity(detailsIntent)
        }
    }

    fun updateWithPage(page: WikiPage, context: Context) {
        currentPage = page
        if (page.thumbnail != null) {
            Picasso.with(itemView.context).load(page.thumbnail!!.source).into(resultImageView)
        }

        titleTextView.text = page.title

        itemView.setOnClickListener { _: View? ->
            var detailsIntent = Intent(context, ArticleDetailsActivity::class.java)
            var pageJson = Gson().toJson(currentPage)
            detailsIntent.putExtra("page", pageJson)
            context.startActivity(detailsIntent)
        }
    }
}