package ir.shayandaneshvar.blog.services;

import ir.shayandaneshvar.blog.api.v1.mappers.UserMapper;
import ir.shayandaneshvar.blog.api.v1.model.LoginRequest;
import ir.shayandaneshvar.blog.api.v1.model.RegisterRequest;
import ir.shayandaneshvar.blog.model.User;
import ir.shayandaneshvar.blog.repo.UserRepository;
import ir.shayandaneshvar.blog.security.JwtProvider;
import ir.shayandaneshvar.blog.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    public void signUp(RegisterRequest request) {
        User user = UserMapper.INSTANCE.requestToUser(request);
        user.setPassword(encodePassword(user.getPassword()));
        userRepository.save(user);
    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    public String login(LoginRequest request) {
        Authentication auth = authenticationManager.authenticate(new
                UsernamePasswordAuthenticationToken(request.getUsername(),
                request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(auth);
        return jwtProvider.generateToken(auth);
    }


    public Optional<org.springframework.security.core.userdetails.User>
    getCurrentUser() {
        return Optional.ofNullable(
                (org.springframework.security.core.userdetails.User)
                        SecurityContextHolder.getContext().getAuthentication()
                                .getPrincipal());
    }
}
