package com.enyeinteractive.demo.marvelapi

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.test.filters.LargeTest
import org.junit.Rule
import org.junit.Test

@LargeTest
class MainScreenUITest {


    @Rule
    @JvmField
    val activityRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun testMainListUi() {

        activityRule.onNodeWithText("Sample Marvel Api").assertExists()

        //TC: alternatively could rig an idling resource here
        activityRule.waitUntil(timeoutMillis = 5_000) {
            val exists = runCatching {
                activityRule.onNodeWithText("Loading...").fetchSemanticsNode()
                true
            }.getOrDefault(false)
            !exists
        }

        //TC: this is screen dependent but is just an example
        activityRule.onAllNodesWithContentDescription("thumbnail")
            .assertCountEquals(5)

    }
}