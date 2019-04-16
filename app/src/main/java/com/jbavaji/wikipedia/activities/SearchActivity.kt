package com.jbavaji.wikipedia.activities

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem

import com.jbavaji.wikipedia.R
import com.jbavaji.wikipedia.WikiApplication
import com.jbavaji.wikipedia.adapter.ArticleListItemRecyclerAdapter
import com.jbavaji.wikipedia.managers.WikiManager
import com.jbavaji.wikipedia.model.WikiResult
import com.jbavaji.wikipedia.provider.ArticleDataProvider
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity() {
    private var wikiManager : WikiManager? = null
    private var adapter: ArticleListItemRecyclerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        wikiManager = (applicationContext as WikiApplication).wikiManager

        setSupportActionBar(toolBar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        search_result_recycler.layoutManager = LinearLayoutManager(this)
        adapter = ArticleListItemRecyclerAdapter(this)
        search_result_recycler.adapter = adapter
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == android.R.id.home) {
            finish()
        }
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)
        val searchItem = menu!!.findItem(R.id.action_search)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = searchItem!!.actionView as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.setIconifiedByDefault(false)
        searchView.requestFocus()
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                wikiManager?.search(query, 0, 20) { wikiResult ->
                    adapter!!.currentResults.clear()
                    adapter!!.currentResults.addAll(wikiResult.query!!.pages)
                    runOnUiThread { adapter!!.notifyDataSetChanged() }
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })

        return super.onCreateOptionsMenu(menu)
    }
}
