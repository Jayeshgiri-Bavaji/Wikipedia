package com.jbavaji.wikipedia.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.jbavaji.wikipedia.R
import com.jbavaji.wikipedia.holder.ListItemHolder
import com.jbavaji.wikipedia.model.WikiPage


/**
 * Created by Jayeshgiri Bavaji on 4/12/2019.
 * Synechron Technologies
 * jayeshgiri.bavaji@synechron.com
 */

class ArticleListItemRecyclerAdapter(var context: Context) : RecyclerView.Adapter<ListItemHolder>() {

    var currentResults: ArrayList<WikiPage> = ArrayList<WikiPage>()

    override fun getItemCount(): Int {
        return currentResults.size // temporary
    }

    override fun onBindViewHolder(holder: ListItemHolder?, position: Int) {
        // where update  our view
        var page = currentResults[position]
        holder?.updateWithPage(page, this.context)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ListItemHolder {
        var cardItem = LayoutInflater.from(parent?.context).inflate(R.layout.article_list_item, parent, false)
        return ListItemHolder(cardItem)
    }

}