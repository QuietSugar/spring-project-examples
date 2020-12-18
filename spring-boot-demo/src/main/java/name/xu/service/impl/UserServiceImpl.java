package name.xu.service.impl;

import name.xu.entity.User;
import name.xu.mapper.UserMapper;
import name.xu.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {
    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    UserMapper userMapper;

    @Override
    public List<User> getUsersWithoutAnnotation(String name) {
        return userMapper.getUsersWithoutAnnotation(name, "name");
    }

    @Override
    public List<User> getUsersWithAnnotation(String name) {
        return userMapper.getUsersWithoutAnnotation(name, "name");
    }

    @Override
    public List<User> getUsersWithMap(String name) {
        return userMapper.getGetUsersWithMap(name, "name");
    }

    @Override
    public int insert(User user) {
        return userMapper.insert(user);
    }
    @Override
    public int insertUsers(List<User> users) {
        return userMapper.insertUsers(users);
    }

    @Override
    public int countByBatchCode(String batchCode) {
        return userMapper.countByBatchCode(batchCode);
    }
}
