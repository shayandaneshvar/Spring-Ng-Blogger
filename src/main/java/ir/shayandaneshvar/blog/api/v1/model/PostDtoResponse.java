package ir.shayandaneshvar.blog.api.v1.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PostDtoResponse {
    private String id;
    private String title;
    private String content;
    private String username;
    private String createdOn;
    private String updatedOn;
}
