package com.repository.jpa;

import com.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaUserRepository implements UserRepository{

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public User save(User user) {

        if (user.isNew())
        {
            em.persist(user);
            return user;
        }
        else return em.merge(user);
    }

    @Override
    public User get(int id) {

        return em.find(User.class, id);
    }

    @Override
    public List<User> getAll() {
        return em.createNamedQuery(User.ALL_SORTED, User.class).getResultList();
    }

    @Override
    public boolean delete(int id) {

        Query query = em.createQuery("DELETE FROM User u WHERE u.id=:id");
        return query.setParameter("id", id).executeUpdate() != 0;
    }
}
