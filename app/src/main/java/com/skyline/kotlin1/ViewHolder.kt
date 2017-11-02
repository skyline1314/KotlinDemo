package com.skyline.kotlin1

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView

/**
 * Created by Administrator on 2017/11/1.
 */

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var num: TextView = itemView.findViewById(R.id.num)

}
