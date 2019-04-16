package com.jbavaji.wikipedia.activities

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import com.jbavaji.wikipedia.R
import com.jbavaji.wikipedia.fragments.ExploreFragment
import com.jbavaji.wikipedia.fragments.FavouriteFragment
import com.jbavaji.wikipedia.fragments.HistoryFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val exploreFragment: ExploreFragment
    private val historyFragment: HistoryFragment
    private val favouriteFragment: FavouriteFragment

    init {
        exploreFragment = ExploreFragment()
        historyFragment = HistoryFragment()
        favouriteFragment = FavouriteFragment()
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->

        val transcation = supportFragmentManager.beginTransaction()
        transcation.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)

        when (item!!.itemId) {
            R.id.navigation_explore -> {
                transcation.replace(R.id.fragment_container, exploreFragment)
            }
            R.id.navigation_favourite -> {
                transcation.replace(R.id.fragment_container, favouriteFragment)
            }
            R.id.navigation_history -> {
                transcation.replace(R.id.fragment_container, historyFragment)
            }
        }

        transcation.commit()
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolBar)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        val transcation = supportFragmentManager.beginTransaction()
        transcation.add(R.id.fragment_container, exploreFragment)
        transcation.commit()
    }
}
