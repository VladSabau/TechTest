package babylon.test.posts.repository

import babylon.test.BaseTest
import babylon.test.data.posts.PostsService
import babylon.test.data.posts.model.Post
import babylon.test.data.posts.repository.PostsRepository
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
class PostsRepositoryTest: BaseTest() {

    private val service = mockk<PostsService>(relaxed = true)

    var repository: PostsRepository = PostsRepository(service)

    @Before
    fun before() {
        clearMocks(service)
        repository = PostsRepository(service)
    }

    @Test
    fun `verify that server call is made`() {
        val posts: List<Post> = getMockPostList()
        every { service.posts } returns Observable.just(posts)

        repository.fetchPosts().test().assertComplete().assertValues(posts)
        verify(exactly = 1) { service.posts }
    }

    @Test
    fun `verify fetch is throwing an error`() {
        val throwable = Throwable()
        every { service.posts } returns Observable.error(throwable)

        repository.fetchPosts().test().assertError(throwable)
    }
}