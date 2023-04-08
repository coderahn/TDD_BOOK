package chap07.userRegisterTest;

public interface UserRepository {
    void save(User user);

    User findById(String id);
}
