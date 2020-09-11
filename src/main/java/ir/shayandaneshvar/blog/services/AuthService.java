package ir.shayandaneshvar.blog.services;

import ir.shayandaneshvar.blog.api.v1.mappers.UserMapper;
import ir.shayandaneshvar.blog.api.v1.model.RegisterRequest;
import ir.shayandaneshvar.blog.model.User;

public class AuthService {
    public void signUp(RegisterRequest request) {
        User user = UserMapper.INSTANCE.requestToUser(request);

    }
}
