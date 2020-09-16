package ir.shayandaneshvar.blog.api.v1.mappers;

import ir.shayandaneshvar.blog.api.v1.model.PostDtoRequest;
import ir.shayandaneshvar.blog.api.v1.model.PostDtoResponse;
import ir.shayandaneshvar.blog.model.Post;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PostMapperTest {
    private PostMapper mapper = PostMapper.INSTANCE;
    private final String USER = "USER", TITLE = "Title", CONTENT = "CNT",
            ID = "1";

    @Test
    public void testToPost() {
        PostDtoRequest postDtoRequest = new PostDtoRequest().setContent(CONTENT)
                .setTitle(TITLE);
        Post post = mapper.convertToPost(postDtoRequest);
        Assertions.assertEquals(post.getContent(), CONTENT);
        Assertions.assertEquals(post.getTitle(), TITLE);
    }

    @Test
    public void testToDto() {
        Post post = (Post) new Post().setContent(CONTENT).setTitle(TITLE)
                .setUsername(USER).setId(Long.parseLong(ID));
        PostDtoResponse postDtoRequest = mapper.convertToPostDto(post);
        Assertions.assertEquals(postDtoRequest.getContent(), CONTENT);
        Assertions.assertEquals(postDtoRequest.getTitle(), TITLE);
        Assertions.assertEquals(postDtoRequest.getUsername(), USER);
        Assertions.assertEquals(postDtoRequest.getId(), ID);
    }
}
