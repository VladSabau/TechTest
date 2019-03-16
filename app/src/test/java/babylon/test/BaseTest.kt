package babylon.test

import babylon.test.data.comments.model.Comment
import babylon.test.data.posts.model.Post
import babylon.test.data.users.model.User
import babylon.test.ui.posts.CommentBuilder
import babylon.test.ui.posts.PostBuilder
import babylon.test.ui.posts.UserBuilder
import io.mockk.unmockkAll
import org.junit.After
import org.junit.Rule

/**
 * Created by Vlad Sabau on 16.03.19.
 */
open class BaseTest {

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    @After
    fun afterTests() {
        unmockkAll()
    }

    protected fun getMockPostList(): ArrayList<Post> {
        val posts = ArrayList<Post>()
        posts.add(getMockPost(1, 1, "Title 1", "Body 1"))
        posts.add(getMockPost(2, 1, "Title 2", "Body 2"))
        posts.add(getMockPost(3, 1, "Title 3", "Body 3"))
        posts.add(getMockPost(1, 2, "Title a", "Body a"))

        return posts
    }

    protected fun getMockPost(id: Int, userId: Int, title: String, body: String): Post {
        return PostBuilder()
                .withId(id)
                .withUserId(userId)
                .withTitle(title)
                .withBody(body)
                .build()
    }

    protected fun getMockUsers(): ArrayList<User> {
        val users = ArrayList<User>()
        users.add(getMockUser(1, "Vlad", "VladSabau", "vlad@email.com"))
        users.add(getMockUser(2, "User", "UserTest", "test@email.com"))

        return users
    }

    protected fun getMockUser(id: Int, name: String, username: String, email: String): User {
        return UserBuilder()
                .withId(id)
                .withName(name)
                .withUsername(username)
                .withEmail(email)
                .build()
    }

    protected fun getMockComments(): ArrayList<Comment> {
        val comments = ArrayList<Comment>()
        comments.add(getMockComment(1, 1, "Comment 1", "email1"))
        comments.add(getMockComment(2, 2, "Comment a", "email2"))
        comments.add(getMockComment(3, 2, "Comment b", "email2"))

        return comments
    }

    protected fun getMockComment(id: Int, postId: Int, name: String, email: String): Comment {
        return CommentBuilder()
                .withId(id)
                .withPostId(postId)
                .withName(name)
                .withEmail(email)
                .build()
    }
}