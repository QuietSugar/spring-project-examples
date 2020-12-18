package name.xu.service;

import name.xu.entity.User;

import java.util.List;

public interface UserService {
    List<User> getUsersWithoutAnnotation(String name);

    List<User> getUsersWithAnnotation(String name);

    List<User> getUsersWithMap(String name);

    int insert(User user);

    int insertUsers(List<User> users);

    int countByBatchCode(String batchCode);
}
