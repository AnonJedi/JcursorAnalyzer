package repository;

import java.util.ArrayList;

public class UserRepository {
    private final String DBPath;
    private ArrayList<String> usernames;

    public UserRepository(String DBPath) {
        usernames = new ArrayList<>();
        this.DBPath = DBPath;
    }

    public ArrayList<String> fetchAllUsers() {
        return usernames;
    }

    public void create(String username) {
        usernames.add(username);
    }
}
