package ir.shayandaneshvar.blog.api.v1.mappers;

import ir.shayandaneshvar.blog.api.v1.model.RegisterRequest;
import ir.shayandaneshvar.blog.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User requestToUser(RegisterRequest request);
}
