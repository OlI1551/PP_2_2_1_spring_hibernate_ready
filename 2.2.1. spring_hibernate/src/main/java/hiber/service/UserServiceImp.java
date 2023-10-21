package hiber.service;

import hiber.dao.UserDao;
import hiber.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class UserServiceImp implements UserService {

   private final UserDao userDao;

   @Autowired
   public UserServiceImp(UserDao userDao) {
      this.userDao = userDao;
   }

   @Transactional
   @Override
   public void add(User user) {
      userDao.add(user);
   }

   @Transactional(readOnly = true)
   @Override
   public List<User> getUsersList() {
      return userDao.getUsersList();
   }

   @Transactional
   @Override
   public User getUserByCarModelAndSeries(String model, int series) {
      return userDao.getUserByCarModelAndSeries(model, series);
   }

   @Transactional
   @Override
   public List<User> getUsersByCarModelAndSeries(String model, int series) {
      return userDao.getUsersByCarModelAndSeries(model, series);
   }
}
