package com.example.mypos.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.mypos.R
import com.example.mypos.databinding.FragmentHomeBinding
import com.example.mypos.ui.Operation
import com.example.mypos.ui.OperationAdapter

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        // 修改這裡，使用 binding 對象找到 ListView
        val gridView: GridView = binding.listOperationSet

        //操作內容物件: 包含名稱和圖片
        val item = ArrayList<Operation>()
        // 操作名稱
        val operationNameArray = resources.getStringArray(R.array.operation_set)
        //操作項目圖片
        val operationImgArray = resources.obtainTypedArray(R.array.operation_image_set)

        // 將操作名稱和圖片加入 operation_item
        for (i in operationNameArray.indices) {
            item.add(Operation(operationImgArray.getResourceId(i,0),operationNameArray[i]))
        }

        operationImgArray.recycle()
        gridView.numColumns = 3
        gridView.adapter = OperationAdapter(R.layout.operator_vertical,item)

        // 單一資料顯示寫法
//        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, operationNameArray)
        // 設置 ListView 的 adapter
        return root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}