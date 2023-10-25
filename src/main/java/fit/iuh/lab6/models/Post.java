package fit.iuh.lab6.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter @Setter
@Entity
@Table(name = "post")
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",columnDefinition = "bigint(20)")
    private Long id;
    @Column(name = "published",columnDefinition = "bit(1)")
    private boolean published;
    @Column(name = "content",columnDefinition = "tinytext")
    private String content;
    @OneToMany(mappedBy = "post")
    private Set<PostComment> postComments = new LinkedHashSet<>();
    @ManyToOne
    @JoinColumn(name = "parent_id")
    @JsonBackReference
    private Post parent;
    @Column(name = "meta_title",columnDefinition = "varchar(100)")
    private String metaTitle;
    @Column(name = "summary",columnDefinition = "tinytext")
    private String summary;
    @Column(name = "create_at",columnDefinition = "datetime(6)")
    private LocalDateTime createAt;
    @OneToMany(mappedBy = "parent")
    private Set<Post> posts = new LinkedHashSet<>();
    @Column(name = "title",columnDefinition = "varchar(75)")
    private String title;
    @ManyToOne
    @JoinColumn(name = "author_id")
    @JsonBackReference
    private Users author;
    @Column(name = "updated_at",columnDefinition = "datetime(6)")
    private LocalDateTime updatedAt;
    @Column(name = "published_at",columnDefinition = "datetime(6)")
    private LocalDateTime publishedAt;

}
