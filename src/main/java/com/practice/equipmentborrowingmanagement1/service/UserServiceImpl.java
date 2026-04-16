package com.practice.equipmentborrowingmanagement1.service;

import com.practice.equipmentborrowingmanagement1.customException.EmailException;
import com.practice.equipmentborrowingmanagement1.model.entity.User;
import com.practice.equipmentborrowingmanagement1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("UserService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void registerUser(User user) {

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new EmailException("Email đã tồn tại");
        }

        userRepository.save(user);
    }

    @Override
    public User login(String email, String password) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new InvalidCredentialsException("Email không tồn tại"));

        if (!user.getPassword().equals(password)) {
            throw new InvalidCredentialsException("Sai mật khẩu");
        }

        return user;
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return null;
    }
}
