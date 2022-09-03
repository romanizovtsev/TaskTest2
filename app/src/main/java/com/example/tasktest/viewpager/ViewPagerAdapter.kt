package com.example.tasktest.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.tasktest.ui.fibonacci.FibonacciNumbersFragment
import com.example.tasktest.ui.simple.SimpleNumbersFragment

class ViewPagerAdapter(fragment: FragmentActivity) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                SimpleNumbersFragment()
            }
            else -> {
                FibonacciNumbersFragment()
            }
        }
    }

}