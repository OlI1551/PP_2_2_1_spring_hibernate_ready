package hiber.dao;

import hiber.model.User;

import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import javax.persistence.Query;
import java.util.List;


@Component
@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public User getUserByCarModelAndSeries(String model, int series) {
      Query jpqlQuery = sessionFactory.getCurrentSession().createQuery("from User as user where  user.car.model=:model and user.car.series=:series", User.class);
      jpqlQuery.setParameter("model", model);
      jpqlQuery.setParameter("series", series);
      return (User) jpqlQuery.getSingleResult();
   }

   @Transactional
   @Override
   public List<User> getUsersByCarModelAndSeries(String model, int series) {
      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User as user where  user.car.model=:model and user.car.series=:series", User.class).
              setParameter("model", model).
              setParameter("series", series);
      return query.getResultList();
   }
}
