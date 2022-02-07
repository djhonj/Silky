package com.silky.data.repository

import com.silky.data.interfaces.IRemoteVideoYT
import com.silky.domain.VideoYT

class VideoYTRepository(private val remoteVideoYT: IRemoteVideoYT) {
    suspend fun search(search: String): MutableList<VideoYT> {
        return remoteVideoYT.search(search)
    }

    suspend fun download(link: String, videoId: String, title: String): String {
        return remoteVideoYT.download(link, videoId, title)
    }
}