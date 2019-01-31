package com.app.rccl.shop.service;

import com.app.rccl.shop.exception.UserNotFoundException;
import com.app.rccl.shop.model.User;
import com.app.rccl.shop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public User findUser(String username, String password) throws UserNotFoundException {
        User user = repository.findByUsernameAndPassword(username, password);

        if (user == null) {
            throw new UserNotFoundException();
        }

        return user;
    }
}
