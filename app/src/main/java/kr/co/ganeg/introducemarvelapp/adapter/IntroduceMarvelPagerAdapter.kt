package kr.co.ganeg.introducemarvelapp.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import kr.co.ganeg.introducemarvelapp.CharacterListFragment
import kr.co.ganeg.introducemarvelapp.MyFavoriteListFragment

const val CHARACTER_LIST_PAGE_INDEX = 0
const val MY_FAVORITE_IST_PAGE_INDEX = 1

class IntroduceMarvelPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val tabFragmentsCreators: Map<Int, () -> Fragment> = mapOf(
        CHARACTER_LIST_PAGE_INDEX to { CharacterListFragment() },
        MY_FAVORITE_IST_PAGE_INDEX to { MyFavoriteListFragment() }
    )

    override fun getItemCount() = tabFragmentsCreators.size

    override fun createFragment(position: Int): Fragment {
        return tabFragmentsCreators[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }
}
