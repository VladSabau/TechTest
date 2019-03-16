package babylon.test.comments.repository

import babylon.test.BaseTest
import babylon.test.data.comments.CommentsService
import babylon.test.data.comments.model.Comment
import babylon.test.data.comments.repository.CommentsRepository
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
class CommentsRepositoryTest: BaseTest() {

    private val service = mockk<CommentsService>(relaxed = true)

    var repository: CommentsRepository = CommentsRepository(service)

    @Before
    fun before() {
        clearMocks(service)
        repository = CommentsRepository(service)
    }

    @Test
    fun verifyServerCallReturnIsCalled() {
        val comments: List<Comment> = getMockComments()
        every { service.comments } returns Observable.just(comments)

        repository.fetchComments().test().assertComplete().assertValues(comments)
        verify(exactly = 1) { service.comments }
    }

    @Test
    fun makeRetrofitCallThrowError() {
        val throwable = Throwable()
        every { service.comments } returns Observable.error(throwable)

        repository.fetchComments().test().assertError(throwable)
    }

    @Test
    fun verifyRetrieveCommentByPostId() {
        val commentsResult = ArrayList<Comment>()
        val comment: Comment = getMockComment(4, 5, "Comment test", "email@email.com")
        commentsResult.add(comment)

        val comments: ArrayList<Comment> = getMockComments()
        comments.addAll(commentsResult)
        every { service.comments } returns Observable.just(comments)

        repository.getCommentsByPostId(5).test().assertComplete().assertValues(commentsResult)
    }
}