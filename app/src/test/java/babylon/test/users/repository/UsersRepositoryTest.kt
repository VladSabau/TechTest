package babylon.test.users.repository

import babylon.test.BaseTest
import babylon.test.data.users.UsersService
import babylon.test.data.users.model.User
import babylon.test.data.users.repository.UsersRepository
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
class UsersRepositoryTest: BaseTest() {

    private val service = mockk<UsersService>(relaxed = true)

    var repository: UsersRepository = UsersRepository(service)

    @Before
    fun before() {
        clearMocks(service)
        repository = UsersRepository(service)
    }

    @Test
    fun `verify that server call is made`() {
        val users: List<User> = getMockUsers()
        every { service.users } returns Observable.just(users)

        repository.fetchUsers().test().assertComplete().assertValues(users)
        verify(exactly = 1) { service.users }
    }

    @Test
    fun `verify fetch is throwing an error`() {
        val throwable = Throwable()
        every { service.users } returns Observable.error(throwable)

        repository.fetchUsers().test().assertError(throwable)
    }

    @Test
    fun `verify getting user by id`() {
        val user = getMockUser(3, "Vlad", "VladSabau", "vlad@vlad.com")

        val users: ArrayList<User> = getMockUsers()
        users.add(user)
        every { service.users } returns Observable.just(users)

        repository.getUserById(3).test().assertComplete().assertValue(user)
    }
}