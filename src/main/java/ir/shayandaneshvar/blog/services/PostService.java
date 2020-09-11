package ir.shayandaneshvar.blog.services;

import ir.shayandaneshvar.blog.api.v1.mappers.PostMapper;
import ir.shayandaneshvar.blog.api.v1.model.PostDtoRequest;
import ir.shayandaneshvar.blog.api.v1.model.PostDtoResponse;
import ir.shayandaneshvar.blog.exceptions.PostNotFoundException;
import ir.shayandaneshvar.blog.model.Post;
import ir.shayandaneshvar.blog.repo.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class PostService {
    private final AuthService authService;
    private final PostRepository postRepository;

    public void createPost(PostDtoRequest postDtoRequest) {
        Post post = mapToPost(postDtoRequest);
        postRepository.save(post);
    }

    private PostDtoResponse mapToDto(Post post) {
        return PostMapper.INSTANCE.convertToPostDto(post);
    }

    private Post mapToPost(PostDtoRequest request) {
        Post post = PostMapper.INSTANCE.convertToPost(request);
        User user = authService.getCurrentUser().orElseThrow(() ->
                new IllegalArgumentException("No User Logged in!"));
        post.setUsername(user.getUsername())
                .setCreatedOn(Instant.now())
                .setUpdatedOn(Instant.now());
        return post;
    }

    public List<PostDtoResponse> getAll() {
        return postRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public PostDtoResponse getOne(Long id) {
        return mapToDto(postRepository.
                findById(id).orElseThrow(() ->
                new PostNotFoundException("No post with id=" + id)));
    }
}
