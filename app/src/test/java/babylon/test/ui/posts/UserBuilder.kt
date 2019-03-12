package babylon.test.ui.posts

import babylon.test.data.users.model.User

/**
 * Created by Vlad Sabau on 12.03.19.
 */
class UserBuilder {

    private var id: Int? = 0
    private var name: String? = null
    private var username: String? = null
    private var email: String? = null

    fun withId(id: Int): UserBuilder {
        this.id = id
        return this
    }

    fun withName(name: String): UserBuilder {
        this.name = name
        return this
    }

    fun withUsername(username: String): UserBuilder {
        this.username = username
        return this
    }

    fun withEmail(email: String): UserBuilder {
        this.email = email
        return this
    }

    fun build(): User {
        val user = User()
        user.id = this.id!!
        user.name = this.name!!
        user.username = this.username!!
        user.email = this.email!!

        return user
    }
}