package com.example.mypos.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.mypos.R
import com.example.mypos.databinding.FragmentHomeBinding

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
        val listView: ListView = binding.listOperationSet
        // 從字符串數組獲取操作列表
        val operationArray = resources.getStringArray(R.array.operation_set)
        // 使用 ArrayAdapter 來設置 ListView 的數據
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, operationArray)
        // 設置 ListView 的 adapter
        listView.adapter = adapter
        return root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}