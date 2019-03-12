package babylon.test.ui.posts

import babylon.test.data.comments.model.Comment

/**
 * Created by Vlad Sabau on 12.03.19.
 */
class CommentBuilder {

    private var id: Int? = 0
    private var postId: Int? = 0
    private var name: String? = null
    private var email: String? = null

    fun withId(id: Int): CommentBuilder {
        this.id = id
        return this
    }

    fun withPostId(postId: Int): CommentBuilder {
        this.postId = postId
        return this
    }

    fun withName(name: String): CommentBuilder {
        this.name = name
        return this
    }

    fun withEmail(email: String): CommentBuilder {
        this.email = email
        return this
    }

    fun build(): Comment {
        val comment = Comment()
        comment.id = this.id!!
        comment.postId = this.postId!!
        comment.name = this.name!!
        comment.email = this.email!!

        return comment
    }
}