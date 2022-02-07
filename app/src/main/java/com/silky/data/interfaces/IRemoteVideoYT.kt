package com.silky.data.interfaces

import com.silky.domain.VideoYT

interface IRemoteVideoYT {
    suspend fun search(search: String): MutableList<VideoYT>
    suspend fun download(link: String, videoId: String, title: String): String
}