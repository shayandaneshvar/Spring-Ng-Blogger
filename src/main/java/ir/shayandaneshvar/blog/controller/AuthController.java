package ir.shayandaneshvar.blog.controller;

import ir.shayandaneshvar.blog.dto.RegisterRequest;
import ir.shayandaneshvar.blog.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class AuthController {
    private final AuthService authService;

    @PostMapping("/sign-up")
    public void signUp(@RequestBody RegisterRequest request) {
        authService.signUp(request);
    }
}
