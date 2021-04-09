package com.jamp.bt.rest.api.service.impl;

import com.jamp.bt.rest.api.dto.user.User;
import com.jamp.bt.rest.api.repositories.UserRepository;
import com.jamp.bt.rest.api.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired()
    private UserRepository repository;

    @Override
    public User createUser(User user) {
        repository.save(user);
        return user;
    }

    @Override
    public User updateUser(User targetUser) {
        Optional<User> optUser = repository.findById(targetUser.getId());
        if (optUser.isPresent()) {
            User user = optUser.get();
            user.setName(targetUser.getName());
            user.setSurname(targetUser.getSurname());
            user.setBirthday(targetUser.getBirthday());
            repository.save(user);
            return user;
        } else {
            throw new NoSuchElementException("No user with ID " + targetUser.getId());
        }
    }

    @Override
    public void deleteUser(long id) {
        repository.deleteById(id);
    }

    @Override
    public User findById(long id) {
        return repository.findById(id).orElseThrow(()->new NoSuchElementException("No user with ID " + id));
    }

    @Override
    public List<User> getAllUsers() {
        return repository.findAll();
    }
}
