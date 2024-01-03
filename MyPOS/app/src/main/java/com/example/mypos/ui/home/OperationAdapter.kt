// OperationAdapter.kt
package com.example.mypos.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.ImageView
import android.widget.TextView
import com.example.mypos.R

class OperationAdapter constructor(private val layout: Int, private val data: ArrayList<Operation>) : BaseAdapter() {
    override fun getCount(): Int {
        return data.size
    }

    override fun getItem(position: Int): Any {
        return data[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = View.inflate(parent?.context, layout, null)

        // 找到 ImageView 和 TextView
        val operationImageView: ImageView = view.findViewById(R.id.img_opr_photo)
        val operationNameTextView: TextView = view.findViewById(R.id.txt_opr_names)

        // 根據 position 取得對應的 Operation
        val item = getItem(position) as Operation

        // 將 Operation 的資料顯示在 UI 元件上
        operationImageView.setImageResource(item.photo)
        operationNameTextView.text = item.name

        return view
    }
}