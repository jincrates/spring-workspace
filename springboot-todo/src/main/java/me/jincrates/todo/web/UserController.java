package me.jincrates.todo.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import me.jincrates.todo.config.security.TokenProvider;
import me.jincrates.todo.domain.users.User;
import me.jincrates.todo.service.UserService;
import me.jincrates.todo.web.dto.ResponseDTO;
import me.jincrates.todo.web.dto.UserDTO;
import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class UserController {
    private final UserService userService;
    private final TokenProvider tokenProvider;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody UserDTO userDTO) {
        try {
            //요청을 이용해 저장할 사용자 만들기
            User user = User.builder()
                    .email(userDTO.getEmail())
                    .username(userDTO.getUsername())
                    .password(passwordEncoder.encode(userDTO.getPassword()))
                    .build();
            //서비스를 이용해 리포지터리에 사용자 저장
            User registeredUser = userService.create(user);

            //사용자 정보는 항상 하나이므로 리스트로 만들어야 하는 ResponseDTO를 사용하지 않고 그냥 UserDTO 리턴
            UserDTO response = UserDTO.builder()
                    .email(registeredUser.getEmail())
                    .username(registeredUser.getUsername())
                    .id(registeredUser.getId())
                    .build();

            return ResponseEntity.ok().body(response);
        } catch(Exception e) {
            ResponseDTO response = ResponseDTO.builder().error(e.getMessage()).build();

            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticate(@RequestBody UserDTO userDTO) {
        User user = userService.getByCredentials(
                userDTO.getEmail(),
                userDTO.getPassword(),
                passwordEncoder
        );

        if (user != null) {
            //토큰 생성
            final String token = tokenProvider.create(user);
            final UserDTO responseUser = UserDTO.builder()
                    .email(user.getEmail())
                    .id(user.getId())
                    .token(token)
                    .build();
            return ResponseEntity.ok().body(responseUser);
        } else {
            ResponseDTO response = ResponseDTO.builder().error("Login failed.").build();

            return ResponseEntity.badRequest().body(response);
        }
    }
}
