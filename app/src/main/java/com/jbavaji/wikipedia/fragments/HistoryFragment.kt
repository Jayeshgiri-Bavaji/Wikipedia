package com.jbavaji.wikipedia.fragments


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import com.jbavaji.wikipedia.R
import com.jbavaji.wikipedia.WikiApplication
import com.jbavaji.wikipedia.adapter.ArticleListItemRecyclerAdapter
import com.jbavaji.wikipedia.managers.WikiManager
import org.jetbrains.anko.alert
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.noButton
import org.jetbrains.anko.yesButton

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class HistoryFragment : Fragment() {
    private var wikiManager: WikiManager? = null
    var history_article_recycler: RecyclerView? = null
    private var adapter: ArticleListItemRecyclerAdapter? = null

    init {
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        var view = inflater.inflate(R.layout.fragment_history, container, false)

        history_article_recycler = view.findViewById<RecyclerView>(R.id.history_article_recycler)

        history_article_recycler!!.layoutManager = LinearLayoutManager(context)
        adapter = ArticleListItemRecyclerAdapter(context)
        history_article_recycler!!.adapter = adapter

        return view
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        wikiManager = (activity.applicationContext as WikiApplication).wikiManager
    }


    override fun onResume() {
        super.onResume()

        doAsync {
            val historyArticles = wikiManager!!.getHistory()
            adapter!!.currentResults.clear()
            adapter!!.currentResults.addAll(historyArticles!!)

            activity.runOnUiThread {
                adapter!!.notifyDataSetChanged()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater!!.inflate(R.menu.history_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == R.id.action_clear_history) {
            activity.alert("Are you sure want to clear your History?", "Confirm") {
                yesButton {
                    adapter!!.currentResults.clear()
                    doAsync {
                        wikiManager!!.clearHistory()
                    }
                    activity.runOnUiThread {
                        adapter!!.notifyDataSetChanged()
                    }

                }
                noButton { }
            }.show()
        }
        return super.onOptionsItemSelected(item)
    }
}

