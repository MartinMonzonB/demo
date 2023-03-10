package lp3.backend.repository;

import lp3.backend.dao.UserDao;
import lp3.backend.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;
import java.util.List;

@Repository("userDao")
public class UserRepo implements UserDao {

    private static List<User> DB = new ArrayList<>();

    @Override
    public int insertUser(UUID id, User User){
        DB.add(new User(id, User.getUsername(),User.getEmail(),User.getOrganization()));
        return 0;
    }

    @Override
    public List<User> selectAllPeople() {
        return DB;
    }

    @Override
    public Optional<User> selectUserById(UUID id) {
        return DB.stream()
                .filter(User -> User.getId().equals(id))
                .findFirst();
    }

    @Override
    public int deleteUserById(UUID id) {
        Optional<User> UserMaybe = selectUserById(id);
        if (UserMaybe.isEmpty()) {
            return 0;
        }
        DB.remove(UserMaybe.get());
        return 1;
    }


    @Override
    public int updateUserById(UUID id, User update) {
        return selectUserById(id)
                .map(User -> {
                    int indexOfUserToUpdate = DB.indexOf(User);
                    if (indexOfUserToUpdate >= 0){
                        DB.set(indexOfUserToUpdate, new User(id, update.getUsername(), update.getEmail(),update.getOrganization()));
                        return 1;
                    }
                    return 0;
                } )
                .orElse(0);
    }
}
