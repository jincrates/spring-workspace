package me.jincrates.todo.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import me.jincrates.todo.service.UserService;
import me.jincrates.todo.web.dto.UserDTO;
import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody UserDTO userDTO) {
        try {
            //요청을 이용해 저장할 사용자 만들기
        } catch(Exception e) {

        }
        return null;
    }
}
