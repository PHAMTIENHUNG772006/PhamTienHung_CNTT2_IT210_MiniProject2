package com.practice.equipmentborrowingmanagement1.service;

import com.practice.equipmentborrowingmanagement1.customException.EmailException;
import com.practice.equipmentborrowingmanagement1.customException.InvalidCredentialsException;
import com.practice.equipmentborrowingmanagement1.model.dto.UserRequest;
import com.practice.equipmentborrowingmanagement1.model.entity.Role;
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
    public void registerUser(UserRequest user) {

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new EmailException("Email đã tồn tại");
        }
        User newUser = new User(
            1,
                user.getFullName(),
                user.getEmail(),
                user.getPassword(),
                Role.STUDENT
        );
        userRepository.save(newUser);
    }

    @Override
    public User login(String email, String password) {

        User user = userRepository.findByEmail(email);

        // check user tồn tại
        if (user == null) {
            throw new InvalidCredentialsException("Email hoặc mật khẩu không đúng");
        }

        // check password
        if (!user.getPassword().equals(password)) {
            throw new InvalidCredentialsException("Email hoặc mật khẩu không đúng");
        }

        return user;
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return Optional.ofNullable(userRepository.findByEmail(email));
    }
}
