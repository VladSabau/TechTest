package babylon.test.ui.posts.list

import babylon.test.BaseTest
import babylon.test.data.posts.model.Post
import babylon.test.data.posts.usecase.PostsUseCase
import io.mockk.clearMocks
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test

/**
 * Created by Vlad Sabau on 16.03.19.
 */
class PostsPresenterTest: BaseTest() {

    private val postsUseCase = mockk<PostsUseCase>(relaxed = true)
    private val view = mockk<PostsView>(relaxed = true)

    var presenter: PostsPresenter = PostsPresenter(postsUseCase)

    @Before
    fun before() {
        clearMocks(postsUseCase, view)
        presenter = PostsPresenter(postsUseCase)
    }

    @Test
    fun `when screen loads and rx call throws error show error message`() {
        every { postsUseCase.posts } returns Observable.error(Throwable("Error"))

        presenter.attachView(view)
        presenter.onScreenLoad()

        verify(exactly = 1) { view.startProgressIndicator() }
        verify(exactly = 1) { view.showNoPostsAvailable() }
        verify(exactly = 1) { view.stopProgressIndicator() }
    }

    @Test
    fun `when screen loads and PostsList valid update view`() {
        val posts = getMockPostList()
        every { postsUseCase.posts } returns Observable.just(posts)

        presenter.attachView(view)
        presenter.onScreenLoad()

        verify(exactly = 1) { view.startProgressIndicator() }
        verify(exactly = 1) { view.updatePosts(posts) }
        verify(exactly = 1) { view.stopProgressIndicator() }
    }

    @Test
    fun `when screen loads and PostsList empty show error`() {
        val posts = ArrayList<Post>()
        every { postsUseCase.posts } returns Observable.just(posts)

        presenter.attachView(view)
        presenter.onScreenLoad()

        verify(exactly = 1) { view.startProgressIndicator() }
        verify(exactly = 1) { view.showNoPostsAvailable() }
        verify(exactly = 1) { view.stopProgressIndicator() }
    }

    @Test
    fun `when click on post load the view`() {
        val post = getMockPost(1, 1, "Title 1", "Body 1")

        presenter.attachView(view)
        presenter.onPostClick(post)

        verify(exactly = 1) { view.openPostDetail(post) }
    }
}

