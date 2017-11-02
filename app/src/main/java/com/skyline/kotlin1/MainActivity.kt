package com.skyline.kotlin1

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    private var data = ArrayList<String>()
    private var mAdapter: RecyclerAdapter? = null
    private var context: Context? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        context = this
        initView()
    }

    private fun initView() {
        swipe_view.setOnRefreshListener {
            swipe_view.isRefreshing = true
            swipe_view.postDelayed(Runnable {
                initData()
                swipe_view.isRefreshing = false
            }, 1000)
        }
        recycler_view.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        mAdapter = RecyclerAdapter()
        recycler_view.adapter = mAdapter
        initData()
    }

    private fun initData() {
        var size=data.size
        for (i in size..(size + 49)) {
            data.add("position $i   kotlin activity ")
        }
        mAdapter!!.notifyDataSetChanged()
    }


    private inner class RecyclerAdapter : RecyclerView.Adapter<ViewHolder>() {

        override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
            val item = data[position]
            holder!!.num.setText(item)
            holder.num.setOnClickListener({
                var intent = Intent(context, SecondActivity::class.java)
                startActivity(intent)
            })
        }

        override fun getItemCount(): Int {
            return data.size
        }

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
            val view = layoutInflater.inflate(R.layout.listarray_item, null)
            return ViewHolder(view)
        }
    }

}
