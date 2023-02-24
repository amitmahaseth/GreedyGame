package com.example.greedygame.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.greedygame.AlbumsFragment
import com.example.greedygame.ArtistFragment
import com.example.greedygame.TrackFragment

class ViewPagerAdapter(fragmentActivity: FragmentActivity, private var totalCount: Int) :
    FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return totalCount
    }
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> AlbumsFragment()
            1 -> ArtistFragment()
            else -> TrackFragment()
        }


    }
}
