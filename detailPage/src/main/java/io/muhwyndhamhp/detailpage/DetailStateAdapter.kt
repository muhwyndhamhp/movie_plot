package io.muhwyndhamhp.detailpage

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import io.muhwyndhamhp.detailpage.fragment.InformationFragment
import io.muhwyndhamhp.detailpage.fragment.ReviewFragment

class DetailStateAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {
    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> InformationFragment()
            else -> ReviewFragment()
        }
    }
}