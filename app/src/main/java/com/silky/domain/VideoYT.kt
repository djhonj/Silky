package com.silky.domain

import java.io.Serializable

data class VideoYT(var Id: Int? = null, var VideoId: String? = null, var Title: String? = null,
                   var Url: String? = null, var Channel: String? = null, var Thumbnail: String? = null,
                   var Duration: String? = null): Serializable