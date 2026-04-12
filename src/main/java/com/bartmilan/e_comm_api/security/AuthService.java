package com.bartmilan.e_comm_api.security;

import com.bartmilan.e_comm_api.model.Role;
import com.bartmilan.e_comm_api.model.User;
import com.bartmilan.e_comm_api.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, JwtService jwtService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    public String register(String email, String password) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new IllegalStateException("User with this email already exists");
        }

        User user = new User(email, passwordEncoder.encode(password), Role.USER);
        userRepository.save(user);

        return jwtService.generateToken(email, Role.USER.name());
    }

    public String login(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalStateException("Invalid email or password"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalStateException("Invalid email or password");
        }

        return jwtService.generateToken(email, user.getRole().name());
    }

}
