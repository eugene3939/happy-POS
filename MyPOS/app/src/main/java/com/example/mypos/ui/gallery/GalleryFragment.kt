package com.example.mypos.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.GridView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.mypos.R
import com.example.mypos.databinding.FragmentGalleryBinding
import com.example.mypos.ui.Operation
import com.example.mypos.ui.OperationAdapter

class GalleryFragment : Fragment() {

    private var _binding: FragmentGalleryBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val galleryViewModel =
            ViewModelProvider(this).get(GalleryViewModel::class.java)

        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textGallery
        galleryViewModel.text.observe(viewLifecycleOwner) {
            textView.text = "營業選項"
        }1

        // custom list會顯示之後可以新增的選項
        val gridView: GridView = binding.listCustomItems
        val customArray = resources.getStringArray(R.array.customized_set)

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1,customArray)
        gridView.numColumns = 3
        gridView.adapter = adapter

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}