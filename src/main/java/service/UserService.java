package service;

import model.User;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class UserService {

    /* хранилище данных */
    private Map<Long, User> dataBase = Collections.synchronizedMap(new HashMap<>());
    /* счетчик id */
    private AtomicLong maxId = new AtomicLong(0);
    /* список авторизованных пользователей */
    private Map<Long, User> authMap = Collections.synchronizedMap(new HashMap<>());


    public List<User> getAllUsers() {
        List<User> user = new ArrayList(dataBase.keySet());
        return user;
    }

    public User getUserById(Long id) {
        return dataBase.get(id);

    }

    public boolean addUser(User user) {
        if (dataBase.containsValue(user)) {
            return false;
        } else {

            dataBase.put(maxId.addAndGet(1), user);
            return true;
        }
    }

    public void deleteAllUser() {
        dataBase.clear();
    }

    public boolean isExistsThisUser(User user) {
        if (dataBase.containsValue(user)) {
            return true;
        } else {
            return false;
        }
    }

    public List<User> getAllAuth() {
        List<User> userAuth = new ArrayList(authMap.keySet());
        return userAuth;
    }

    public boolean authUser(User user) {
        if (authMap.containsValue(user)) {
            return true;
        } else {
            return false;
        }
    }

    public void logoutAllUsers() {
        authMap.clear();
    }

    public boolean isUserAuthById(Long id) {
        if (authMap.containsKey(id)) {
            return true;
        } else {
            return false;
        }
    }

}
