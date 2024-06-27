package com.backend.ecommerce.implementation;

import com.backend.ecommerce.authentication.AuthenticationRequest;
import com.backend.ecommerce.authentication.AuthenticationResponse;
import com.backend.ecommerce.authentication.RegisterRequest;
import com.backend.ecommerce.dto.CustomModelMapper;
import com.backend.ecommerce.records.UserDTO;
import com.backend.ecommerce.enums.Role;
import com.backend.ecommerce.entity.User;
import com.backend.ecommerce.repository.UserRepository;
import com.backend.ecommerce.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final CustomModelMapper customModelMapper;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ADMIN)
                .build();
        User savedUser = userRepository.save(user);
        UserDTO userDTO = customModelMapper.apply(savedUser);
        return new AuthenticationResponse(jwtService.generateToken(savedUser), userDTO);
        //[TODO] return user
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        doAuthenticate(request.getEmail(), request.getPassword());
        var user = userRepository.findByEmail(request.getEmail()).get();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    private void doAuthenticate(String email, String password) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            authenticationManager.authenticate(authentication);
        } catch (org.springframework.security.authentication.BadCredentialsException e) {
            throw new BadCredentialsException("Invalid Password");
        }
    }
}
