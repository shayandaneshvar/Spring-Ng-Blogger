package ir.shayandaneshvar.blog.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.Instant;

@Data
@Table
@Entity
@NoArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class Post extends BaseEntity<Long> {
    @Column
    @NotBlank
    private String title;
    @Lob
    @Column
    @NotEmpty
    private String content;
    @NotBlank
    private String username;
    @Column
    private Instant createdOn;
    @Column
    private Instant updatedOn;
}
