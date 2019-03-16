package babylon.test.ui.posts.details

import babylon.test.BaseTest
import babylon.test.data.comments.usecase.CommentsUseCase
import babylon.test.data.users.usecase.UsersUseCase
import io.mockk.clearMocks
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test

/**
 * Created by Vlad Sabau on 12.03.19.
 */
class PostDetailsPresenterTest: BaseTest() {

    private val usersUseCase = mockk<UsersUseCase>(relaxed = true)
    private val commentsUseCase = mockk<CommentsUseCase>(relaxed = true)
    private val view = mockk<PostDetailsView>(relaxed = true)

    var presenter: PostDetailsPresenter = PostDetailsPresenter(usersUseCase, commentsUseCase)

    @Before
    fun before() {
        clearMocks(usersUseCase, commentsUseCase, view)
        presenter = PostDetailsPresenter(usersUseCase, commentsUseCase)
    }

    @Test
    fun `when screen loads and Post is null show proper error`() {
        every { view.post } returns PostDetailsPresenter.POST_NOT_SET

        presenter.attachView(view)
        presenter.onScreenLoad()

        verify(exactly = 1) { view.showUnknownPostDialog() }
    }

    @Test
    fun `when screen loads and Post and rx call throws error show error message`() {
        val post = getMockPost(1, 1, "Title 1", "Body 1")

        every { view.post } returns post
        every { usersUseCase.getUserById(post.userId) } returns Observable.error(Throwable("Error"))
        every { commentsUseCase.getCommentsByPostId(post.id) } returns Observable.empty()

        presenter.attachView(view)
        presenter.onScreenLoad()

        verify(exactly = 1) { view.startProgressIndicator() }
        verify(exactly = 1) { view.showErrorLoadingPost() }
        verify(exactly = 1) { view.stopProgressIndicator() }
    }

    @Test
    fun `when screen loads and Post with User and Comment load details`() {
        val post = getMockPost(1, 1, "Title 1", "Body 1")
        val user = getMockUser(1, "Vlad", "VladSabau", "vlad@email.com")
        val comments = getMockComments()

        every { view.post } returns post
        every { usersUseCase.getUserById(post.userId) } returns Observable.just(user)
        every { commentsUseCase.getCommentsByPostId(post.id) } returns Observable.just(comments)

        presenter.attachView(view)
        presenter.onScreenLoad()

        verify(exactly = 1) { view.startProgressIndicator() }
        verify(exactly = 1) { view.loadPostDetail(any()) }
        verify(exactly = 1) { view.stopProgressIndicator() }
    }
}

