package com.enyeinteractive.demo.marvelapi.network

import com.enyeinteractive.demo.marvelapi.BuildConfig
import java.security.MessageDigest

object Md5Helper {

    fun md5(requestId: Long) = MessageDigest.getInstance("md5")
        .digest("$requestId${BuildConfig.MARVEL_PRIVATE_KEY}${BuildConfig.MARVEL_PUBLIC_KEY}".toByteArray())
        .toHex()

    //turn each byte into a character sequence and concat
    private fun ByteArray.toHex() = joinToString(separator = "") { byte -> "%02x".format(byte) }

}