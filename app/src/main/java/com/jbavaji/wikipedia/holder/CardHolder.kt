package com.jbavaji.wikipedia.holder

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.google.gson.Gson
import com.jbavaji.wikipedia.R
import com.jbavaji.wikipedia.activities.ArticleDetailsActivity
import com.jbavaji.wikipedia.model.WikiPage
import com.squareup.picasso.Picasso


/**
 * Created by Jayeshgiri Bavaji on 4/12/2019.
 * Synechron Technologies
 * jayeshgiri.bavaji@synechron.com
 */
class CardHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val articleImageView: ImageView = itemView.findViewById<ImageView>(R.id.article_image)
    private val titleTextView: TextView = itemView.findViewById<TextView>(R.id.article_title)

    private var currentPage: WikiPage? = null

    init {
        itemView.setOnClickListener { view: View? ->
            var detailsIntent = Intent(itemView.context, ArticleDetailsActivity::class.java)
            var pageJson = Gson().toJson(currentPage)
            detailsIntent.putExtra("page", pageJson)
            itemView.context.startActivity(detailsIntent)
        }
    }

    fun updateWithPage(page: WikiPage) {
        currentPage = page
        titleTextView.text = page.title

        if (page.thumbnail != null) {
            Picasso.with(itemView.context)
                    .load(page!!.thumbnail!!.source)
                    .into(articleImageView)
        }
    }
}