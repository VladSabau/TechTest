package babylon.test.ui.posts

import babylon.test.data.posts.model.Post

/**
 * Created by Vlad Sabau on 12.03.19.
 */
class PostBuilder {

    private var userId: Int? = 0
    private var id: Int? = 0
    private var title: String? = null
    private var body: String? = null

    fun withUserId(userId: Int): PostBuilder {
        this.userId = userId
        return this
    }

    fun withId(id: Int): PostBuilder {
        this.id = id
        return this
    }

    fun withTitle(title: String): PostBuilder {
        this.title = title
        return this
    }

    fun withBody(body: String): PostBuilder {
        this.body = body
        return this
    }

    fun build(): Post {
        val post = Post()
        post.id = this.id!!
        post.userId = this.userId!!
        post.title = this.title!!
        post.body = this.body!!

        return post
    }
}