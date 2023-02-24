package com.example.greedygame

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.greedygame.adapter.AlbumAdapter
import com.example.greedygame.adapter.ItemClickedListener
import com.example.greedygame.databinding.FragmentAlbumBinding
import com.example.greedygame.pojo.Album
import com.example.greedygame.pojo.AlbumResponse
import com.example.greedygame.utils.Extension
import com.example.greedygame.viewmodel.AlbumVM
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class AlbumFragment : Fragment(), ItemClickedListener {

    private var MAX_ITEMS_TO_SHOW=12
    private lateinit var binding: FragmentAlbumBinding
    private lateinit var albumVm: AlbumVM

    private lateinit var albumAdapter: AlbumAdapter
    lateinit var dataList: ArrayList<Album>
   // Show only 12 items initially

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAlbumBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        albumVm = AlbumVM()
        init()
        albumApiHit()
    }

    private fun init() {
        binding.ivLess.setImageResource(R.drawable.keyboard_arrow_down)
        binding.ivLess.setOnClickListener {
            binding.ivLess.setImageResource(R.drawable.keyboard_arrow_up)
            MAX_ITEMS_TO_SHOW = dataList.size
            // Refresh RecyclerView
            setAdapter()
            albumAdapter.notifyDataSetChanged()
        }
    }

    private fun albumApiHit() {
        var method = "tag.gettopalbums"
        var tag = "disco"
        var api_key = "7268f95cf528622b0a0e05ff241ca145"
        var format = "json"
        CoroutineScope(Dispatchers.Main).launch {
            Extension.showProgess(requireActivity())
            albumVm.getAlbumList(method, tag, api_key, format)
                .observe(this@AlbumFragment, Observer {
                    val response = it as AlbumResponse
                    Extension.stopProgress()
                    dataList=response.albums.album
                    setAdapter()
                })
        }
    }

    private fun setAdapter() {
        albumAdapter = AlbumAdapter(requireActivity(), dataList,MAX_ITEMS_TO_SHOW)
        binding.rvListAlbum.adapter = albumAdapter
    }

    override fun onClickItem(position: Int) {
        var bundle=Bundle()
        bundle.putSerializable("albumList",dataList[position])
        findNavController().navigate(R.id.action_albumFragment_to_detailsAlbumFragment,bundle)
    }

}
