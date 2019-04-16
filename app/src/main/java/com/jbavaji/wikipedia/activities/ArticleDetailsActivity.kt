package com.jbavaji.wikipedia.activities

import android.graphics.Bitmap
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import com.google.gson.Gson
import com.jbavaji.wikipedia.R
import com.jbavaji.wikipedia.WikiApplication
import com.jbavaji.wikipedia.managers.WikiManager
import com.jbavaji.wikipedia.model.WikiPage
import kotlinx.android.synthetic.main.activity_article_detail.*
import org.jetbrains.anko.toast


/**
 * Created by Jayeshgiri Bavaji on 4/12/2019.
 * Synechron Technologies
 * jayeshgiri.bavaji@synechron.com
 */

class ArticleDetailsActivity : AppCompatActivity() {
    private var wikiManager: WikiManager? = null
    private var currentPage: WikiPage? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_detail)

        setSupportActionBar(toolBar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        wikiManager = (applicationContext as WikiApplication).wikiManager

        val wikiPageJson = intent.getStringExtra("page")
        currentPage = Gson().fromJson<WikiPage>(wikiPageJson, WikiPage::class.java)

        toolBar?.title = currentPage?.title

        article_detail_webview?.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view!!.loadUrl(url)
                return true
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                page_progressbar.visibility = View.GONE
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                page_progressbar.visibility = View.VISIBLE
            }
        }

        article_detail_webview.loadUrl(currentPage!!.fullurl)

        wikiManager?.addHistory(currentPage!!)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.article_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == android.R.id.home) {
            finish()
        }else if(item!!.itemId == R.id.action_favourite){
            try {
                if(wikiManager!!.getIsFavourite(currentPage!!.pageid!!)){
                    wikiManager!!.removeFavouriteById(currentPage!!.pageid!!)
                    toast("Article removed from favourite")
                }else{
                    wikiManager!!.addFavourite(currentPage!!)
                    toast("Article added to favourite")
                }
            }catch (ex: Exception){
                toast("Uable to perform option")
            }
        }
        return true
    }
}