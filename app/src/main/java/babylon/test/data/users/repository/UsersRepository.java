package babylon.test.data.users.repository;

import java.util.List;

import javax.inject.Inject;

import babylon.test.data.users.UsersService;
import babylon.test.data.users.model.User;
import io.reactivex.Observable;

/**
 * Created by Vlad Sabau on 10.03.19.
 */
public class UsersRepository {

    private final UsersService usersService;

    @Inject
    public UsersRepository(UsersService usersService) {
        this.usersService = usersService;
    }

    public Observable<List<User>> fetchUsers() {
        return usersService.getUsers();
    }

    public Observable<User> getUserById(int userId) {
        return usersService.getUsers()
                .flatMap(Observable::fromIterable)
                .filter(user -> user.getId() == userId);
    }
}