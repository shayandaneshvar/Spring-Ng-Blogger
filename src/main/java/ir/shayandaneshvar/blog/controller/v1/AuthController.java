package ir.shayandaneshvar.blog.controller.v1;

import ir.shayandaneshvar.blog.api.v1.model.LoginRequest;
import ir.shayandaneshvar.blog.api.v1.model.RegisterRequest;
import ir.shayandaneshvar.blog.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class AuthController {
    private final AuthService authService;

    @PostMapping(value = "/sign-up")
    public ResponseEntity<?> signUp(@RequestBody RegisterRequest request) {
        authService.signUp(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/login")
    public String login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }
}
