package com.enyeinteractive.demo.marvelapi.viewmodel

import com.enyeinteractive.demo.marvelapi.network.Md5Helper
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class Md5HashTest {

    @Test
    fun generateHash() {
        val hash = Md5Helper.md5(100)
        assertThat(hash).isEqualTo("1a7bd681dd3d66ce26d9e28dc690d495")
    }
}