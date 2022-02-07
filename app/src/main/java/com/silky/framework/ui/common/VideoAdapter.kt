package com.silky.framework.ui.common

import android.content.Intent
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.silky.framework.R
import com.silky.domain.VideoYT
import com.silky.framework.ui.main.MainPresenter

class VideoAdapter(private val videos: MutableList<VideoYT>,
                   private val mainPresenter: MainPresenter
): RecyclerView.Adapter<VideoAdapter.MyViewHolder>()  {
    fun getListVideo():MutableList<VideoYT> = videos

    //se llama cuando el recycler necesita una vista
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        //inflamos la vista
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_video, parent, false)

        return MyViewHolder(view)
    }

    //se llama cuando actualizamos una vista
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        if (this.getItemCount() > 0) {
            holder.bind(videos[position])
        }
    }

    override fun getItemCount(): Int {
        return videos.size
    }

    inner class MyViewHolder(private val view: View): RecyclerView.ViewHolder(view) {
        fun bind(video: VideoYT) {
            video.Thumbnail?.let { thumbnail ->
                view.findViewById<ImageView>(R.id.imgVideo)?.let {
                    Glide.with(view.context).load(thumbnail).into(it)
                }
            }

            video.Title?.let { title ->
                view.findViewById<TextView>(R.id.tvTitle)?.let {
                    it.text = mainPresenter.shortenTitle(title)
                }
            }

            video.Channel?.let { channel ->
                view.findViewById<TextView>(R.id.tvAuthor)?.let {
                    it.text = channel
                }
            }

            video.Duration?.let { duration ->
                view.findViewById<TextView>(R.id.tvDuration)?.let {
                    it.text = duration
                }
            }

            view.findViewById<Button>(R.id.btnDownload)?.let {
                it.setOnClickListener {
                    video.also {
                        //mainPresenter.messageToast("iniciando descarga")
                        //mainPresenter.loading(true)

                        //val pendingIntent = PendingIntent.getActivity(view.context, 0,
                            //Intent(view.context, DownloadListActivity::class.java), 0)

//                        val notificationApp = NotificationApp(view.context)
//                        notificationApp.notify(it.Id!!, notificationApp.prepare(it.Title ?: "notification", "Descargando...",
//                            null,null))

                        val intentService = Intent(view.context, DownloadService::class.java).apply {
                            putExtra("video", it)
                        }

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            view.context.startService(intentService)
                        } else {
                            view.context.startService(intentService)
                            Log.i("message-2", "here")
                        }

//                        val scope: CoroutineScope = CoroutineScope(context = Dispatchers.Default)
//                        scope.launch {
//                            val response = mainPresenter.download(it.Url ?: "",it.Id ?: "", it.Title ?: "")
//
//                            withContext(Dispatchers.Main) {
//                                if (response == "ok") {
//                                    //mainPresenter.loading(false)
//                                    mainPresenter.messageToast("descarga finalizada")
//                                } else {
//                                    mainPresenter.messageToast(response)
//                                }
//
//                                scope.cancel()
//                            }
//                        }
                    }
                }
            }
        }
    }
}