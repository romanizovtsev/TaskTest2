package com.example.tasktest

import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.tasktest.databinding.ActivityMainBinding
import com.example.tasktest.viewpager.ViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : FragmentActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val tabNames: Array<String> = arrayOf(
            getString(R.string.simple_numbers_text),
            getString(R.string.fibonacci_numbers_text)
        )
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val viewPager: ViewPager2 = binding.viewPager
        viewPager.adapter = ViewPagerAdapter(this)
        val tabLayout: TabLayout = binding.tabs

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = tabNames[position]
        }.attach()

    }
}