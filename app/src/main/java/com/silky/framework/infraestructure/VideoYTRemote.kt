package com.silky.framework.infraestructure

import com.silky.data.interfaces.IRemoteVideoYT
import com.silky.domain.VideoYT
import com.silky.framework.infraestructure.convert.Base64ToAudio
import com.silky.framework.infraestructure.service.IConvertService
import com.silky.framework.infraestructure.service.ServiceBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext

class VideoYTRemote(private val convertBase64: Base64ToAudio): IRemoteVideoYT {
    private val convertServiceBuilder: IConvertService by lazy { ServiceBuilder.buildService(
        IConvertService::class.java) }

    override suspend fun search(search: String): MutableList<VideoYT> {
        return convertServiceBuilder.getVideoList(search)

        //val videos = videosN.map { it.toDomainVideoYT() }

        //return videos
        //return coroutineScope {
          //  val request = convertServiceBuilder.getVideoList(search)
//            val response = withContext(Dispatchers.IO) {
//                request.execute().body()
//            }
            //request
        //}

        //request.execute().body()

//        request.enqueue(object : Callback<List<VideoYT>> {
//            override fun onResponse(call: Call<List<VideoYT>>, response: Response<List<VideoYT>>) {
//                if (response.isSuccessful) {
//                    return response.body() ?: null
//                }
//            }
//
//            override fun onFailure(call: Call<List<VideoYT>>, t: Throwable) {
//                TODO("Not yet implemented")
//            }
//        })
    }

    private fun validateBase64(base64: String, title: String): String {
        if (convertBase64.isBase64(base64)) {
            convertBase64.convert(base64, "${title}.mp3")
            return "ok"
        }

        return base64
    }

    override suspend fun download(link: String, videoId: String, title: String): String {
        return coroutineScope {
            val resultAsync = async {
                val base64 = convertServiceBuilder.download(VideoYT().apply {
                    VideoId = videoId
                    Title = title
                    Url = link
                })

                validateBase64(base64, title)
            }

            resultAsync.await()
            //val resultAsync =  validateBase64(base64, title) }
            //resultAsync.await()
        }
    }
}