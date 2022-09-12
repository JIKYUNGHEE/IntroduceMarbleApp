package kr.co.ganeg.introducemarvelapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kr.co.ganeg.introducemarvelapp.adapter.CHARACTER_LIST_PAGE_INDEX
import kr.co.ganeg.introducemarvelapp.adapter.IntroduceMarvelPagerAdapter
import kr.co.ganeg.introducemarvelapp.adapter.MY_FAVORITE_IST_PAGE_INDEX
import kr.co.ganeg.introducemarvelapp.databinding.FragmentViewPagerBinding

@AndroidEntryPoint
class HomeViewPagerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentViewPagerBinding.inflate(inflater, container, false)
        val tabLayout = binding.tabs
        val viewPager = binding.viewPager

        viewPager.adapter = IntroduceMarvelPagerAdapter(this)

        // Set the icon and text for each tab
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = getTabTitle(position)
        }.attach()

        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)

        return binding.root
    }

    private fun getTabTitle(position: Int): String? {
        return when (position) {
            CHARACTER_LIST_PAGE_INDEX -> getString(R.string.character_list_title)
            MY_FAVORITE_IST_PAGE_INDEX -> getString(R.string.my_favorite_list_title)
            else -> null
        }
    }
}
