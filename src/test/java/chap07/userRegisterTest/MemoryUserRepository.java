package chap07.userRegisterTest;

import java.util.HashMap;
import java.util.Map;

public class MemoryUserRepository implements UserRepository{
    private Map<String, User> users = new HashMap<>();
    public void save(User user) {
        users.put(user.getId(), user);
    }

    @Override
    public User findById(String id) {
        return users.get(id);
    }
}
