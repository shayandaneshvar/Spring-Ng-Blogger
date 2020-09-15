package ir.shayandaneshvar.blog.controller.v1;

import ir.shayandaneshvar.blog.api.v1.model.PostDtoRequest;
import ir.shayandaneshvar.blog.api.v1.model.PostDtoResponse;
import ir.shayandaneshvar.blog.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class PostController {
    private final PostService postService;
    @PostMapping()
    public ResponseEntity<?> createPost(@RequestBody PostDtoRequest dto) {
        postService.createPost(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PostDtoResponse>> showAllPosts() {
        return new ResponseEntity<>(postService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDtoResponse> getPost(@PathVariable Long id) {
        return new ResponseEntity<>(postService.getOne(id), HttpStatus.OK);
    }

}
