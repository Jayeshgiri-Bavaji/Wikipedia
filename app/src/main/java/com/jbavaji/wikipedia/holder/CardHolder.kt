package com.jbavaji.wikipedia.holder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.jbavaji.wikipedia.R
import kotlinx.android.synthetic.main.artcile_cardview_item.view.*


/**
 * Created by Jayeshgiri Bavaji on 4/12/2019.
 * Synechron Technologies
 * jayeshgiri.bavaji@synechron.com
 */
class CardHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val articleImageView : ImageView = itemView.findViewById<ImageView>(R.id.article_image)
    private val titleTextView : TextView = itemView.findViewById<TextView>(R.id.article_title)
}