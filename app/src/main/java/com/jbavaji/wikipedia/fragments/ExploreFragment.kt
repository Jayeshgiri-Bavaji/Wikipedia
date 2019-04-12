package com.jbavaji.wikipedia.fragments


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.CardView
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView

import com.jbavaji.wikipedia.R
import com.jbavaji.wikipedia.activities.SearchActivity
import com.jbavaji.wikipedia.adapter.ArticleCardRecyclerAdapter
import kotlinx.android.synthetic.main.fragment_explore.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class ExploreFragment : Fragment() {

    var search_card_view : CardView? = null
    var explore_article_recycler : RecyclerView? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_explore, container, false)

        search_card_view = view.findViewById<CardView>(R.id.search_card_view)
        explore_article_recycler = view.findViewById<RecyclerView>(R.id.explore_article_recycler)

        search_card_view!!.setOnClickListener {
            val searchIntent = Intent(context, SearchActivity::class.java)
            context.startActivity(searchIntent)
        }

        explore_article_recycler!!.layoutManager = LinearLayoutManager(context)
        explore_article_recycler!!.adapter = ArticleCardRecyclerAdapter()

        return view
    }


}
