package com.example.greedygame.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.greedygame.R
import com.example.greedygame.pojo.Album
import java.util.*

class AlbumAdapter(
    var mContext: Context,
    var albumList: ArrayList<Album>,
    var MAX_ITEMS_TO_SHOW: Int,
    var onItemClicked:ItemClickedListener
) : RecyclerView.Adapter<AlbumListHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumListHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.album_list, parent, false)
        return AlbumListHolder(view)
    }


    override fun onBindViewHolder(holder: AlbumListHolder, position: Int) {
        var dataList = albumList[position]
        holder.tvAlbumName.text = dataList.name
        holder.itemView.setOnClickListener {
            onItemClicked.onClickItem(position)
        }

    }

    override fun getItemCount(): Int {
        return if (albumList.size > MAX_ITEMS_TO_SHOW) MAX_ITEMS_TO_SHOW else albumList.size
    }

}

interface ItemClickedListener {
    fun onClickItem(position: Int)

}


class AlbumListHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var tvAlbumName: AppCompatTextView = itemView.findViewById(R.id.tvAlbumName)

}