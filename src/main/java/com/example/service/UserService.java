package com.example.service;

import com.example.model.User;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void registerUser(String username, String password, String role) {
        // ユーザー名の重複チェック
        if (userRepository.findByUsername(username).isPresent()) {
            throw new RuntimeException("ユーザー名は既に使用されています");
        }

        // パスワードをハッシュ化
        String encodedPassword = passwordEncoder.encode(password);

        // ユーザーを作成して保存
        User user = new User(username, encodedPassword, role);
        userRepository.save(user);
    }
}