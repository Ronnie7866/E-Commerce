
package com.backend.ecommerce.controllers;

import com.backend.ecommerce.authentication.AuthenticationResponse;
import com.backend.ecommerce.authentication.RegisterRequest;
import com.backend.ecommerce.dto.UserDTO;
import com.backend.ecommerce.entity.User;
import com.backend.ecommerce.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {


    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        UserDTO user = userService.createUser(userDTO);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        UserDTO updatedUser = userService.updateUser(id, userDTO);
        return ResponseEntity.ok(updatedUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        UserDTO userDTO = userService.getUserById(id);
        return ResponseEntity.ok(userDTO);
    }

    //TODO Late | Pagination | comment
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    //TODO comment
    @GetMapping("/email")
    public ResponseEntity<UserDTO> getUserByEmail(@RequestParam String email) {
        UserDTO userDTO = userService.getUserByEmail(email);
        return ResponseEntity.ok(userDTO);
    }


//    @PostMapping("/signup")
//    public ResponseEntity<UserDTO> signup(@RequestBody UserDTO userDTO) {
//        UserDTO savedUser = userService.register(userDTO);
//        return ResponseEntity.ok(savedUser);
//    }

//    @PostMapping("/login")
//    public ResponseEntity<UserDTO> login(@RequestParam String email, @RequestParam String password) {
//        userService.login(email, password);
//        return ResponseEntity.ok(userService.getUserByEmail(email));
//    }
}
