package com.jbavaji.wikipedia.fragments


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AlertDialog
import android.support.v7.widget.CardView
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jbavaji.wikipedia.R
import com.jbavaji.wikipedia.WikiApplication
import com.jbavaji.wikipedia.activities.SearchActivity
import com.jbavaji.wikipedia.adapter.ArticleCardRecyclerAdapter
import com.jbavaji.wikipedia.managers.WikiManager
import com.jbavaji.wikipedia.provider.ArticleDataProvider

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class ExploreFragment : Fragment() {
    private var wikiManager: WikiManager? = null
    var search_card_view: CardView? = null
    var explore_article_recycler: RecyclerView? = null
    var adapter: ArticleCardRecyclerAdapter = ArticleCardRecyclerAdapter()
    var refresher: SwipeRefreshLayout? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        wikiManager = (activity.applicationContext as WikiApplication).wikiManager
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_explore, container, false)

        search_card_view = view.findViewById<CardView>(R.id.search_card_view)
        explore_article_recycler = view.findViewById<RecyclerView>(R.id.explore_article_recycler)
        refresher = view.findViewById<SwipeRefreshLayout>(R.id.refresher)

        search_card_view!!.setOnClickListener {
            val searchIntent = Intent(context, SearchActivity::class.java)
            context.startActivity(searchIntent)
        }

        explore_article_recycler!!.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        explore_article_recycler!!.adapter = adapter

        refresher?.setOnRefreshListener {
            getRandomArticle()
        }

        getRandomArticle()
        return view
    }

    private fun getRandomArticle() {
        refresher?.isRefreshing = true

        try {
            wikiManager?.getRandom(55) { wikiResult ->
                adapter.currentResults.clear()
                adapter.currentResults.addAll(wikiResult.query!!.pages)
                activity.runOnUiThread {
                    adapter.notifyDataSetChanged()
                    refresher?.isRefreshing = false
                }
            }
        } catch (ex: Exception) {
            val builder = AlertDialog.Builder(activity)
            builder.setMessage(ex.message).setTitle("Oops!!!")
            var dialog = builder.create()
            dialog.show()
        }
    }
}
