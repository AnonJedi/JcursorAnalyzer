package service;

import common.Common;
import repository.UserRepository;

import java.util.ArrayList;

public class UserService {

    private UserRepository repository;

    public UserService() {
        repository = new UserRepository(Common.DB_PATH);
    }

    public boolean registrateUser(final String username) {
        ArrayList<String> usernames = repository.fetchAllUsers();
        boolean result = !usernames.contains(username);
        if (result) {
            try {
                repository.create(username);
            } catch (Exception e) {
                result = false;
            }
        }
        return result;
    }
}
