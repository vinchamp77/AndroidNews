package vtsen.hashnode.dev.androidnews

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.core.app.ApplicationProvider
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import vtsen.hashnode.dev.androidnews.ui.MainActivity
import vtsen.hashnode.dev.androidnews.ui.screens.MainScreen
import vtsen.hashnode.dev.androidnews.viewmodel.MainViewModel

class ComposeTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun bottomNavigationNames_areValid() {
        var text = composeTestRule.activity.getString(R.string.home)
        composeTestRule.onNodeWithText(text).assertExists()

        text = composeTestRule.activity.getString(R.string.unread_articles)
        composeTestRule.onNodeWithText(text).assertExists()

        text = composeTestRule.activity.getString(R.string.bookmarks)
        composeTestRule.onNodeWithText(text).assertExists()
    }

    @Test
    fun clickBookmarks_clearBookmarks_showsNoArticles() {
        var text = composeTestRule.activity.getString(R.string.bookmarks)
        composeTestRule.onNodeWithText(text).performClick()
        // This test is not done yet, work in progress
    }
}