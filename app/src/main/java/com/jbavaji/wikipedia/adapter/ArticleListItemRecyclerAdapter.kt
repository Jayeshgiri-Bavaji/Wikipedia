package com.jbavaji.wikipedia.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.jbavaji.wikipedia.R
import com.jbavaji.wikipedia.holder.CardHolder
import com.jbavaji.wikipedia.holder.ListItemHolder


/**
 * Created by Jayeshgiri Bavaji on 4/12/2019.
 * Synechron Technologies
 * jayeshgiri.bavaji@synechron.com
 */

class ArticleListItemRecyclerAdapter() : RecyclerView.Adapter<ListItemHolder>() {

    override fun getItemCount(): Int {
        return 15 // temporary
    }

    override fun onBindViewHolder(holder: ListItemHolder?, position: Int) {
        // where update  our view
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ListItemHolder {
        var cardItem = LayoutInflater.from(parent?.context).inflate(R.layout.article_list_item, parent, false)
        return ListItemHolder(cardItem)
    }

}