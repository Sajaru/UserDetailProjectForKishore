package com.Pulak.userDOB.Dao;

import org.springframework.data.repository.CrudRepository;
import com.Pulak.userDOB.Model.UserDaoModel;

public interface UserDao extends CrudRepository<UserDaoModel, String> {

}
