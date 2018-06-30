package repositories;

import entities.User;

import javax.persistence.NoResultException;

public class UserRepository extends BaseRepository {

    public void saveUser(User user) {
        super.executeAction(repositoryActionResult -> {
            super.entityManager.persist(user);
        });
    }

    public User findByUsername(String username) {
        User user = (User) super.executeAction(repositoryActionResult -> {
            User userFromDb;

            try {
                userFromDb = (User) super.entityManager
                        .createNativeQuery("SELECT * FROM users WHERE username = '" + username + "'", User.class).getSingleResult();

            } catch (NoResultException nre) {
                userFromDb = null;
            }

            repositoryActionResult.setResult(userFromDb);
        }).getResult();

        return user;
    }
}
