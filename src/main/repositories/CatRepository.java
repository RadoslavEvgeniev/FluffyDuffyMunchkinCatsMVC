package repositories;

import entities.Cat;

import javax.persistence.NoResultException;
import java.util.List;

public class CatRepository extends BaseRepository {

    public void saveCat(Cat cat) {
        super.executeAction(repositoryActionResult -> {
            super.entityManager.persist(cat);
        });
    }

    @SuppressWarnings("unchecked")
    public List<Cat> findAllCats() {
        List<Cat> cats = (List<Cat>) super.executeAction(repositoryActionResult -> {
            repositoryActionResult.setResult(
                    super.entityManager
                            .createNativeQuery("SELECT * FROM cats", Cat.class)
                            .getResultList()
            );
        }).getResult();

        return cats;
    }

    public Cat findCatByName(String catName) {
        Cat cat = (Cat) super.executeAction(repositoryActionResult -> {
            Cat catFromDb;

            try {

                catFromDb = (Cat) super.entityManager
                        .createNativeQuery("SELECT * FROM cats WHERE cat_name = '" + catName + "'", Cat.class).getSingleResult();
            } catch (NoResultException nre) {
                catFromDb = null;
            }

            repositoryActionResult.setResult(catFromDb);
        }).getResult();

        return cat;
    }
}
