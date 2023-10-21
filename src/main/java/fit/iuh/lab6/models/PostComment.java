package fit.iuh.lab6.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.apache.catalina.User;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "post_comment")
public class PostComment {
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;
    @Column(name = "title",columnDefinition = "varchar(100)")
    private String title;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",columnDefinition = "bigint(20)")
    private Long id;
    @OneToMany(mappedBy = "parent")
    private Set<PostComment> postComments =new LinkedHashSet<>();
    @Column(name = "published",columnDefinition = "bit(1)")
    private boolean published;
    @Column(name = "content",columnDefinition = "tinytext")
    private String content;
    @Column(name = "published_at",columnDefinition = "datetime(6)")
    private LocalDateTime publishedAt;
    @Column(name = "created_at",columnDefinition = "datetime(6)")
    private LocalDateTime createdAt;
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private PostComment parent;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;
}
