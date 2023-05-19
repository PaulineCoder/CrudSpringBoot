package ru.petrush.CrudSpringBoot.dao;


import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Component;
import ru.petrush.CrudSpringBoot.model.User;

import java.util.List;


@Component
public class UserDaoImp implements UserDao {

    private final EntityManager entityManager;


    public UserDaoImp(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<User> listUsers() {
        TypedQuery<User> query = entityManager.createQuery("select u from User u", User.class);
        return query.getResultList();
    }


    public User showUser(long id) {
        TypedQuery<User> query = entityManager.createQuery("select u from User u where u.id = :id", User.class);
        query.setParameter("id", id);
        return query.getResultList().stream().findAny().orElse(null);
    }

    @Override
    public void add(User user) {
        entityManager.persist(user);
    }


    @Override
    public void update(long id, User user) {
        entityManager.merge(user);
    }

    @Override
    public void delete(long id) {
        entityManager.remove(entityManager.find(User.class, id));
    }
}
