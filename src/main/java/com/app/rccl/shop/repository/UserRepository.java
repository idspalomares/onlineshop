package com.app.rccl.shop.repository;

import com.app.rccl.shop.model.User;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends ShopRepository<User> {

    @Query("select u from User u where u.username = ?1 and u.password = ?2")
    User findByUsernameAndPassword(String username, String password);

}
