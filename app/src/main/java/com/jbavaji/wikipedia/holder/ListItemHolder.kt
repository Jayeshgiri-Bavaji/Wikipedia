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
class ListItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val resultImageView : ImageView = itemView.findViewById<ImageView>(R.id.result_icon)
    private val titleTextView : TextView = itemView.findViewById<TextView>(R.id.result_title)
}