package com.silky.interactors

import com.silky.data.repository.VideoYTRepository
import com.silky.domain.VideoYT

class SearchVideoInteractor(private val videoRepository: VideoYTRepository) {
    suspend fun Invoke(search:String): MutableList<VideoYT> {
        return videoRepository.search(search)
    }
}