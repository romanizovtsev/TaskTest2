package com.example.tasktest.ui.simple

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.tasktest.databinding.FragmentMainBinding
import com.example.tasktest.ui.recycler.OnScrollListener
import com.example.tasktest.ui.recycler.RecyclerAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class SimpleNumbersFragment : Fragment() {

    private val vm by viewModel<SimpleNumbersViewModel>()
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val recyclerAdapter: RecyclerAdapter = RecyclerAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val gridLayoutManager = GridLayoutManager(context, NUMBER_OF_COLUMNS)
        binding.recyclerView.layoutManager = gridLayoutManager
        binding.recyclerView.adapter = recyclerAdapter

        if ((savedInstanceState == null)||(!savedInstanceState.getBoolean(BUNDLE_TAG, false)))
            vm.setFirstData()

        binding.recyclerView.addOnScrollListener(
            OnScrollListener(gridLayoutManager) {
                vm.launchDataLoad()
            }
        )
        //vm.results
        vm.results.observe(viewLifecycleOwner) {
            it?.let {
                recyclerAdapter.submitList(it)
            }
        }

        return binding.root
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(BUNDLE_TAG, true)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private companion object {

        private const val BUNDLE_TAG = "isOpenedBefore"
        private const val NUMBER_OF_COLUMNS = 3
    }
}