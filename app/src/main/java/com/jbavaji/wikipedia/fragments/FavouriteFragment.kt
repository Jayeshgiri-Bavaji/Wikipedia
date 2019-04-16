package com.jbavaji.wikipedia.fragments


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jbavaji.wikipedia.R
import com.jbavaji.wikipedia.WikiApplication
import com.jbavaji.wikipedia.adapter.ArticleCardRecyclerAdapter
import com.jbavaji.wikipedia.adapter.ArticleListItemRecyclerAdapter
import com.jbavaji.wikipedia.managers.WikiManager
import org.jetbrains.anko.doAsync


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class FavouriteFragment : Fragment() {
    private var wikiManager: WikiManager? = null
    var favorite_article_recycler: RecyclerView? = null
    private var adapter : ArticleCardRecyclerAdapter = ArticleCardRecyclerAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_favourite, container, false)

        favorite_article_recycler = view.findViewById<RecyclerView>(R.id.favorite_article_recycler)

        favorite_article_recycler!!.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        favorite_article_recycler!!.adapter = adapter

        return view
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        wikiManager = (activity.applicationContext as WikiApplication).wikiManager
    }

    override fun onResume() {
        super.onResume()

        doAsync {
            val favouriteArticles = wikiManager!!.getFavourite()
            adapter.currentResults.clear()
            adapter.currentResults.addAll(favouriteArticles!!)

            activity.runOnUiThread{
                adapter.notifyDataSetChanged()
            }
        }
    }
}
