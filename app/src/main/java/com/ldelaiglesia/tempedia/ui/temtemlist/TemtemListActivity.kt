package com.ldelaiglesia.tempedia.ui.temtemlist

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ldelaiglesia.tempedia.R
import com.ldelaiglesia.tempedia.ui.temteminfo.TemtemInfoActivity
import kotlinx.android.synthetic.main.activity_temtemlist.*


class TemtemListActivity : AppCompatActivity(),
    androidx.appcompat.widget.SearchView.OnQueryTextListener {

    private lateinit var viewModel: TemtemListViewModel
    private lateinit var search: androidx.appcompat.widget.SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        Thread.sleep(2000)
        setTheme(R.style.AppTheme)
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_temtemlist)

        viewModel = ViewModelProvider(this).get(TemtemListViewModel::class.java)
        search = searchView

        initUI()
        search.setOnQueryTextListener(this)
    }

    private fun initUI() {
        temtemlistRecyclerView.layoutManager = LinearLayoutManager(this)
        temtemlistRecyclerView.adapter = TemtemListAdapter {
            val intent = Intent(this, TemtemInfoActivity::class.java)
            intent.putExtra("number", it)
            startActivity(intent)
        }

        viewModel.getTemtemList()

        viewModel.temtemList.observe(this, Observer { list ->
            (temtemlistRecyclerView.adapter as TemtemListAdapter).setData(list)
        })
    }


    override fun onQueryTextSubmit(query: String): Boolean {
        return false
    }

    override fun onQueryTextChange(query: String): Boolean {
        (temtemlistRecyclerView.adapter as TemtemListAdapter).searchInput(query)
        return false
    }
}
