package com.example.greedygame

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.greedygame.adapter.ViewPagerAdapter
import com.example.greedygame.databinding.FragmentDetailsAlbumBinding
import com.example.greedygame.pojo.Album
import com.google.android.material.tabs.TabLayoutMediator


class DetailsAlbumFragment : Fragment() {
    private lateinit var binding: FragmentDetailsAlbumBinding
    private lateinit var viewPagerAdapter: ViewPagerAdapter
    private val animalsArray = arrayOf("AlbumsFragment", "ArtistFragment","TrackFragment")
    private var albumDetail: Album? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentDetailsAlbumBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
        setupTabLayout()
        init()
        albumDetail= requireArguments().getSerializable("binding")as Album
    }

    private fun init() {
        binding.tvRock.text=albumDetail!!.name
    }

    fun setAdapter() {
        viewPagerAdapter = ViewPagerAdapter(requireActivity(), 3)
        binding.Vp.adapter = viewPagerAdapter

    }

    private fun setupTabLayout() {
        TabLayoutMediator(
            binding.tabLayout, binding.Vp
        ) { tab, position -> tab.text = animalsArray[position] }.attach()
    }

 /*   override fun onBackPressed() {
        val viewPager = binding.Vp
        if (viewPager.currentItem == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed()
        } else {
            // Otherwise, select the previous step.
            viewPager.currentItem = viewPager.currentItem - 1
        }
    }*/



}