package com.silky.framework.ui.main

import com.silky.domain.VideoYT
import com.silky.framework.ui.common.VideoAdapter
import com.silky.interactors.DownloadInteractor
import com.silky.interactors.SearchVideoInteractor

class MainPresenter(private val _view: MainActivity,
                    private val _searchInteractor: SearchVideoInteractor,
                    private val _download: DownloadInteractor
) {

    suspend fun search(search: String): MutableList<VideoYT>? {
        if (!search.isNullOrEmpty()) {
            val videos = _searchInteractor.Invoke(search)

            if (videos.isNullOrEmpty()) {
                //_view.MessageToast("no se encontraron resultados")
                return null
            }

            //val recyclerVideo = _view.findViewById<RecyclerView>(R.id.recyclerVideo)
            return videos
        }

        return null
    }

    fun cleanAdapter(videoAdapter: VideoAdapter, videos: MutableList<VideoYT>) {
        //videoAdapter.getListVideo().fore`
        if ( !videos.isNullOrEmpty()) {
            for ((index) in videos.withIndex()) {
                videos.removeAt(index)
            }

            videoAdapter.notifyDataSetChanged()
        }
    }

    fun shortenTitle(title: String) : String {
        if (title.length > 50) {
            return "${title.substring(0, 49)}..."
        }

        return title
    }

    fun messageToast(message: String) {
        _view. messageToast(message)
    }

    fun loading(visible: Boolean) {
        _view.loading(visible)
    }

    suspend fun download(link: String, videoId: String, title: String): String {
        return _download.Invoke(link, videoId, title)
    }
}