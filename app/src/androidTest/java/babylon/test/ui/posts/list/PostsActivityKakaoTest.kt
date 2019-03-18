package babylon.test.ui.posts.list

import androidx.test.espresso.IdlingRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import babylon.test.PostDetailsKakaoScreen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Created by Vlad Sabau on 16.03.19.
 */
@RunWith(AndroidJUnit4::class)
class PostsActivityKakaoTest {

    @JvmField
    @Rule
    val testRule = ActivityTestRule<PostsActivity>(PostsActivity::class.java)

    private val kakaoPostsScreen = PostsKakaoScreen()

    private val kakaoDetailsScreen = PostDetailsKakaoScreen()

    @Test
    fun initialViewsDisplayedProperly() {
        val postsActivityIdlingResource = testRule.activity.espressoIdlingResourceForPostsActivity
        IdlingRegistry.getInstance().register(postsActivityIdlingResource)

        kakaoPostsScreen {
            noPostsTextView {
                isNotDisplayed()
            }
            postsRecycler {
                isVisible()
                hasSize(100)
            }
        }

        IdlingRegistry.getInstance().unregister(postsActivityIdlingResource)
    }

    @Test
    fun testContentItemsRecyclerView() {
        val postsActivityIdlingResource = testRule.activity.espressoIdlingResourceForPostsActivity
        IdlingRegistry.getInstance().register(postsActivityIdlingResource)

        kakaoPostsScreen {
            postsRecycler {
                isVisible()
                hasSize(100)

                firstChild<PostsKakaoScreen.Item> {
                    isVisible()
                    title { hasText("sunt aut facere repellat provident occaecati excepturi optio reprehenderit") }
                }

                childWith<PostsKakaoScreen.Item> { withDescendant { withText("eum et est occaecati") } } perform {
                    title {
                        isDisplayed()
                        hasText("eum et est occaecati")
                    }
                }
            }
        }

        IdlingRegistry.getInstance().unregister(postsActivityIdlingResource)
    }

    @Test
    fun checkIfDetailsActivityIsOpened() {
        val postsActivityIdlingResource = testRule.activity.espressoIdlingResourceForPostsActivity
        IdlingRegistry.getInstance().register(postsActivityIdlingResource)

        kakaoPostsScreen {
            postsRecycler {
                firstChild<PostsKakaoScreen.Item> {
                    isVisible()
                    perform {
                        click()

                        // details screen
                        kakaoDetailsScreen {
                            titleTextView {
                                isDisplayed()
                            }
                            bodyTextView {
                                isDisplayed()
                            }
                            usernameTextView {
                                isDisplayed()
                            }
                            nbCommentsTextView {
                                isDisplayed()
                            }
                        }
                    }
                }
            }
        }

        IdlingRegistry.getInstance().unregister(postsActivityIdlingResource)
    }
}