package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.myapplication.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val tabConfigurationStrategy =
        TabLayoutMediator.TabConfigurationStrategy { tab, position ->
            tab.text = Companion.title[position]
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initViewPager()

    }


    /**
     * 첫 화면 구성 (ViewPager/TabLayout)
     */
    private fun initViewPager() {

        with(binding) {
            viewPager.adapter = FragmentPagerAdapter(list, this@MainActivity)

            TabLayoutMediator(tabLayout, viewPager, tabConfigurationStrategy).attach()
        }
    }


    companion object {
        private val title = listOf("G", "T", "CA", "D")
        private val list = listOf(FragmentA(), FragmentB(), FragmentC(), FragmentD())
    }

}


class FragmentPagerAdapter(
    private val fragmentList: List<Fragment>,
    fragmentActivity: FragmentActivity
) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount() = fragmentList.size
    override fun createFragment(position: Int) = fragmentList[position]
}