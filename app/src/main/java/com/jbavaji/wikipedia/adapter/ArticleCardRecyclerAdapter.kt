package com.jbavaji.wikipedia.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.jbavaji.wikipedia.R
import com.jbavaji.wikipedia.holder.CardHolder
import com.jbavaji.wikipedia.model.WikiPage
import com.jbavaji.wikipedia.model.WikiResult


/**
 * Created by Jayeshgiri Bavaji on 4/12/2019.
 * Synechron Technologies
 * jayeshgiri.bavaji@synechron.com
 */

class ArticleCardRecyclerAdapter() : RecyclerView.Adapter<CardHolder>() {

    val currentResults : ArrayList<WikiPage> = ArrayList<WikiPage>()

    override fun getItemCount(): Int {
        return currentResults.size // temporary
    }

    override fun onBindViewHolder(holder: CardHolder?, position: Int) {
        // where update  our view
        var page = currentResults[position]
                //  update view with holder
        holder?.updateWithPage(page)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CardHolder {
        var cardItem = LayoutInflater.from(parent?.context).inflate(R.layout.artcile_cardview_item, parent, false)
        return CardHolder(cardItem)
    }

}