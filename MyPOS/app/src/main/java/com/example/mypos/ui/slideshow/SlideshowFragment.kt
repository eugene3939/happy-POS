package com.example.mypos.ui.slideshow

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.mypos.SqlDBHelper
import com.example.mypos.databinding.FragmentSlideshowBinding

class SlideshowFragment : Fragment() {

    private var _binding: FragmentSlideshowBinding? = null
    private val binding get() = _binding!!

    private lateinit var dbrw: SQLiteDatabase
    private var items: ArrayList<String> = ArrayList()
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val slideshowViewModel =
            ViewModelProvider(this).get(SlideshowViewModel::class.java)

        _binding = FragmentSlideshowBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val btnQuery: Button = binding.btnQuery
        val btnInsert: Button = binding.btnInsert
        val btnUpdate: Button = binding.btnUpdate
        val btnDelete: Button = binding.btnDelete

        val edItem: EditText = binding.edtItem
        val edPrice: EditText = binding.edtPrice

        val listView: ListView = binding.listSqlShow

        val textView: TextView = binding.textSlideshow
        slideshowViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        val dbHelper = SqlDBHelper(requireContext())
        dbrw = dbHelper.writableDatabase

        adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, items)
        listView.adapter = adapter

        performQuery(edItem)

        //點擊Listview項目
        // 點擊ListView項目
        listView.setOnItemClickListener { parent, view, position, id ->
            // 獲取被點擊的項目資訊
            val clickedItem = parent.getItemAtPosition(position).toString()

            // 將品名和價格分割並設置到對應的EditText
            val itemParts = clickedItem.split("\t\t\t\t")
            val itemName = itemParts[0].substring(3) // 截取品名部分
            val itemPrice = itemParts[1].substring(3) // 截取價格部分

            // 設置到EditText
            edItem.setText(itemName)
            edPrice.setText(itemPrice)
        }

        //查詢
        btnQuery.setOnClickListener {
            val c = dbrw.rawQuery(
                if (edItem.length() < 1) "SELECT * FROM myTable" else
                    "SELECT * FROM myTable WHERE merchandise LIKE '${edItem.text}'", null
            )

            c.moveToFirst()
            items.clear()
            Toast.makeText(requireContext(), "共有${c.count}筆資料", Toast.LENGTH_SHORT).show()
            for (i in 0 until c.count) {
                items.add("品名:${c.getString(0)}\t\t\t\t價格:${c.getString(1)}")
                c.moveToNext()
            }
            adapter.notifyDataSetChanged()
            c.close()
        }

        btnInsert.setOnClickListener {
            if (edItem.length() < 1 || edPrice.length() < 1)
                Toast.makeText(requireContext(), "欄位請勿流空", Toast.LENGTH_SHORT).show()
            else
                try {
                    dbrw.execSQL(
                        "INSERT INTO myTable(merchandise, price) VALUES(?,?)",
                        arrayOf<Any>(edItem.text.toString(), edPrice.text.toString())
                    )

                    Toast.makeText(requireContext(), "新增成功", Toast.LENGTH_SHORT).show()

                    // 清空輸入框
                    edItem.setText("")
                    edPrice.setText("")

                    // 通知 Adapter 更新 UI
                    adapter.notifyDataSetChanged()

                    performQuery(edItem)
                } catch (e: Exception) {
                    Toast.makeText(requireContext(), "新增失敗", Toast.LENGTH_SHORT).show()
                }
        }

        btnUpdate.setOnClickListener {
            if (edItem.length() < 1 || edPrice.length() < 1)
                Toast.makeText(requireContext(), "欄位請勿流空", Toast.LENGTH_SHORT).show()
            else
                try {
                    dbrw.execSQL(
                        "UPDATE myTable SET price = ${edPrice.text} WHERE merchandise LIKE '${edItem.text}'"
                    )

                    Toast.makeText(
                        requireContext(),
                        "更新商品名${edPrice.text} 價格${edItem.text}",
                        Toast.LENGTH_SHORT
                    ).show()

                    edItem.setText("")
                    edPrice.setText("")

                    performQuery(edItem)

                    // 通知 Adapter 更新 UI
                    adapter.notifyDataSetChanged()
                } catch (e: Exception) {
                    Toast.makeText(requireContext(), "更新失敗", Toast.LENGTH_SHORT).show()
                }
        }

        btnDelete.setOnClickListener {
            if (edItem.length() < 1)
                Toast.makeText(requireContext(), "商品名請勿流空", Toast.LENGTH_SHORT).show()
            else
                try {
                    dbrw.execSQL("DELETE FROM myTable WHERE merchandise LIKE '${edItem.text}'")

                    Toast.makeText(
                        requireContext(),
                        "刪除商品名${edItem.text} 價格${edItem.text}",
                        Toast.LENGTH_SHORT
                    ).show()

                    edItem.setText("")
                    edPrice.setText("")

                    // 通知 Adapter 更新 UI
                    adapter.notifyDataSetChanged()

                    performQuery(edItem)
                } catch (e: Exception) {
                    Toast.makeText(requireContext(), "刪除失敗", Toast.LENGTH_SHORT).show()
                }
        }

        return root
    }

    // 執行查詢的方法
    // 在 performQuery 方法中添加參數
    private fun performQuery(edItem: EditText) {
        val c = dbrw.rawQuery(
            if (edItem.length() < 1) "SELECT * FROM myTable" else
                "SELECT * FROM myTable WHERE merchandise LIKE '${edItem.text}'", null
        )

        c.moveToFirst()
        items.clear()
//        Toast.makeText(requireContext(), "共有${c.count}筆資料", Toast.LENGTH_SHORT).show()
        for (i in 0 until c.count) {
            items.add("品名:${c.getString(0)}\t\t\t\t價格:${c.getString(1)}")
            c.moveToNext()
        }

        adapter.notifyDataSetChanged()
        c.close()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        dbrw.close()
        _binding = null
    }
}
