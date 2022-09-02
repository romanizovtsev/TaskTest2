package com.example.tasktest.ui.main

import android.graphics.Color.BLUE
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tasktest.R
import com.example.tasktest.databinding.FragmentMainBinding
import com.example.tasktest.ui.recycler.DataModel
import com.example.tasktest.ui.recycler.OnScrollListener
import com.example.tasktest.ui.recycler.RecyclerAdapter

/**
 * A placeholder fragment containing a simple view.
 */
class PlaceholderFragment : Fragment() {

    private lateinit var pageViewModel: PageViewModel
    private var _binding: FragmentMainBinding? = null
    private lateinit var  recyclerAdapter: RecyclerAdapter
    private var dataList = mutableListOf<DataModel>()
    var startNumber=3
    private val PagesViewModel by viewModels<PageViewModel> {
        ViewModelFactory(requireContext())
    }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageViewModel = ViewModelProvider(this).get(PageViewModel::class.java).apply {
            setIndex(arguments?.getInt(ARG_SECTION_NUMBER) ?: 1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val root = binding.root
        var gridLayoutManager = GridLayoutManager(requireActivity(),2)
        binding.recyclerView.layoutManager=gridLayoutManager
        recyclerAdapter = RecyclerAdapter()//aplicationContext
        binding.recyclerView.adapter = recyclerAdapter

        Log.e("Загружаю","${startNumber}-${startNumber}")
        //pageViewModel.updateDataList(startNumber+100)
        //startNumber+=100

        dataList.add(DataModel(2,BLUE))
        recyclerAdapter.submitList(dataList)
       // binding.recyclerView.addOnScrollListener(OnScrollListener(gridLayoutManager, recyclerAdapter, dataList))
        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            var previousTotal = 0
            var loading = true
            val visibleThreshold = 10
            var firstVisibleItem = 0
            var visibleItemCount = 0
            var totalItemCount = 0

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                visibleItemCount = recyclerView.childCount
                totalItemCount = gridLayoutManager.itemCount
                firstVisibleItem = gridLayoutManager.findFirstVisibleItemPosition()

                if (loading) {
                    if (totalItemCount > previousTotal) {
                        loading = false
                        previousTotal = totalItemCount
                    }
                }

                if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {
                    val initialSize = dataList.size
                    Log.e("Загружаю","${startNumber}-${startNumber}")
                    pageViewModel.updateDataList(startNumber+100)
                    startNumber+=100
                    //val updatedSize = dataList.size
                    //recyclerView.post { recyclerAdapter.notifyItemRangeInserted(initialSize, updatedSize) }
                    loading = true
                }
            }
        })


        pageViewModel.results.observe(viewLifecycleOwner) {
            it?.let {
                dataList= dataList.plus(it) as MutableList<DataModel>
                recyclerAdapter.submitList(dataList)

            }
        }


        return root
    }

    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val ARG_SECTION_NUMBER = "section_number"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(sectionNumber: Int): PlaceholderFragment {
            return PlaceholderFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}