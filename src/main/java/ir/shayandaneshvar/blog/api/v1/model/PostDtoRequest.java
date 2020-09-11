package ir.shayandaneshvar.blog.api.v1.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PostDtoRequest {
    private String id;
    private String title;
    private String content;
}
