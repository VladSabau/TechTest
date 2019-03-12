package babylon.test.ui.posts.details

import babylon.test.RxImmediateSchedulerRule
import babylon.test.data.comments.model.Comment
import babylon.test.data.comments.usecase.CommentsUseCase
import babylon.test.data.posts.model.Post
import babylon.test.data.users.model.User
import babylon.test.data.users.usecase.UsersUseCase
import babylon.test.ui.posts.CommentBuilder
import babylon.test.ui.posts.PostBuilder
import babylon.test.ui.posts.UserBuilder
import io.mockk.*
import io.reactivex.Observable
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Created by Vlad Sabau on 12.03.19.
 */
class PostDetailsPresenterTest {

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

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
        val post = getMockPost()

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
        val post = getMockPost()
        val user = getMockUser()
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

    @After
    fun afterTests() {
        unmockkAll()
    }

    private fun getMockPost(): Post {
        return PostBuilder()
                .withId(1)
                .withUserId(1)
                .withTitle("Title")
                .withBody("Body")
                .build()
    }

    private fun getMockUser(): User {
        return UserBuilder()
                .withId(1)
                .withName("Name")
                .withUsername("Username")
                .withEmail("Email")
                .build()
    }

    private fun getMockComments(): List<Comment> {
        val comments = ArrayList<Comment>()
        comments.add(getMockComment())

        return comments
    }

    private fun getMockComment(): Comment {
        return CommentBuilder()
                .withId(1)
                .withPostId(1)
                .withName("Comment name")
                .withEmail("Comment email")
                .build()
    }
}

