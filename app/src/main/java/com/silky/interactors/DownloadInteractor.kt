package com.silky.interactors

import com.silky.data.repository.VideoYTRepository

class DownloadInteractor(private val videoRepository: VideoYTRepository) {
    suspend fun Invoke(link: String, videoId:String, title: String): String {
        return videoRepository.download(link, videoId, title)
    }
}