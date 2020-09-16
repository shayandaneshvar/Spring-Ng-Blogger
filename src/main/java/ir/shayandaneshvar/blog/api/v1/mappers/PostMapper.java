package ir.shayandaneshvar.blog.api.v1.mappers;

import ir.shayandaneshvar.blog.api.v1.model.PostDtoRequest;
import ir.shayandaneshvar.blog.api.v1.model.PostDtoResponse;
import ir.shayandaneshvar.blog.model.Post;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PostMapper {
    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

    Post convertToPost(PostDtoRequest postDtoRequest);

    PostDtoResponse convertToPostDto(Post post);
}
