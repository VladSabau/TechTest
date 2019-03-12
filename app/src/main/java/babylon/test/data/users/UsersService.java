package babylon.test.data.users;

import java.util.List;

import babylon.test.data.users.model.User;
import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by Vlad Sabau on 10.03.19.
 */

public interface UsersService {

    @GET("/users")
    Observable<List<User>> getUsers();
}
