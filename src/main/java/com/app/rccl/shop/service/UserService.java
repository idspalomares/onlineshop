package com.app.rccl.shop.service;

import com.app.rccl.shop.exception.UserNotFoundException;
import com.app.rccl.shop.model.User;

public interface UserService {

    User findUser(String username, String password) throws UserNotFoundException;

}
