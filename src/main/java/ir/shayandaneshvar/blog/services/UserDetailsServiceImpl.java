package ir.shayandaneshvar.blog.services;

import ir.shayandaneshvar.blog.model.User;
import ir.shayandaneshvar.blog.repo.UserRepository;
import ir.shayandaneshvar.blog.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(s).orElseThrow(
                () -> new UsernameNotFoundException(s + " not found!"));
        return new UserPrincipal(user);
    }
}
