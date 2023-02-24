package com.example.greedygame.pojo

import java.io.Serializable

data class AlbumResponse(
    val albums: Albums
)

data class Albums(
    val attr: Attr,
    val album: ArrayList<Album>
)

data class Attr(
    val page: String,
    val perPage: String,
    val tag: String,
    val total: String,
    val totalPages: String
)

data class Album(
    val attr: AttrX,
    val artist: Artist,
    val image: List<Image>,
    val mbid: String,
    val name: String,
    val url: String
) : Serializable

data class AttrX(
    val rank: String
): Serializable

data class Artist(
    val mbid: String,
    val name: String,
    val url: String
): Serializable

data class Image(
    val text: String,
    val size: String
): Serializable