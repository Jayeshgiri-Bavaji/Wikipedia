package com.jbavaji.wikipedia.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.jbavaji.wikipedia.R
import com.jbavaji.wikipedia.holder.CardHolder


/**
 * Created by Jayeshgiri Bavaji on 4/12/2019.
 * Synechron Technologies
 * jayeshgiri.bavaji@synechron.com
 */

class ArticleCardRecyclerAdapter() : RecyclerView.Adapter<CardHolder>() {

    override fun getItemCount(): Int {
        return 15 // temporary
    }

    override fun onBindViewHolder(holder: CardHolder?, position: Int) {
        // where update  our view
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CardHolder {
        var cardItem = LayoutInflater.from(parent?.context).inflate(R.layout.artcile_cardview_item, parent, false)
        return CardHolder(cardItem)
    }

}