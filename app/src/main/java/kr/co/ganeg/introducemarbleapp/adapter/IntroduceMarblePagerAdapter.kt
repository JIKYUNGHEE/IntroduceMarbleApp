package kr.co.ganeg.introducemarbleapp.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import kr.co.ganeg.introducemarbleapp.CharacterListFragment
import kr.co.ganeg.introducemarbleapp.MyFavoriteListFragment

const val CHARACTER_LIST_PAGE_INDEX = 0
const val MY_FAVORITE_IST_PAGE_INDEX = 1

class IntroduceMarblePagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    /**
     * Mapping of the ViewPager page indexes to their respective Fragments
     */
    private val tabFragmentsCreators: Map<Int, () -> Fragment> = mapOf(
        CHARACTER_LIST_PAGE_INDEX to { CharacterListFragment() },
        MY_FAVORITE_IST_PAGE_INDEX to { MyFavoriteListFragment() }
    )

    override fun getItemCount() = tabFragmentsCreators.size

    override fun createFragment(position: Int): Fragment {
        return tabFragmentsCreators[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }
}
