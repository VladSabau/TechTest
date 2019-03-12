package babylon.test.data.users.usecase;

import java.util.List;

import javax.inject.Inject;

import babylon.test.data.users.model.User;
import babylon.test.data.users.repository.UsersRepository;
import io.reactivex.Observable;

/**
 * Created by Vlad Sabau on 10.03.19.
 */

public class UsersUseCase {

    private final UsersRepository repository;

    @Inject
    public UsersUseCase(final UsersRepository repository) {
        this.repository = repository;
    }

    public Observable<List<User>> getUsers() {
        return repository.fetchUsers();
    }

    public Observable<User> getUserById(int userId) {
        return repository.getUserById(userId);
    }
}
