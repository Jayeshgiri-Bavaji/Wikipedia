package com.jbavaji.wikipedia.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jbavaji.wikipedia.R
import com.jbavaji.wikipedia.adapter.ArticleListItemRecyclerAdapter
import kotlinx.android.synthetic.main.fragment_favourite.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class FavouriteFragment : Fragment() {

      var favorite_article_recycler : RecyclerView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_favourite, container, false)

        favorite_article_recycler = view.findViewById<RecyclerView>(R.id.favorite_article_recycler)

        favorite_article_recycler!!.layoutManager = LinearLayoutManager(context)
        favorite_article_recycler!!.adapter = ArticleListItemRecyclerAdapter()

        return view
    }


}
