package fit.iuh.lab6.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user")
public class Users {
    @Column(name = "mobile",columnDefinition = "varchar(15)")
    private String mobile;
    @Column(name = "last_login",columnDefinition = "datetime(6)")
    private LocalDateTime lastLogin;
    @Column(name = "last_mame",columnDefinition = "varchar(50)")
    private String lastName;
    @Column(name = "intro",columnDefinition = "tinytext")
    private String intro;
    @Column(name = "profile",columnDefinition = "tinytext")
    private String profile;
    @Column(name = "registered_at",columnDefinition = "datetime(6)")
    private LocalDateTime registeredAt;
    @OneToMany(mappedBy = "user")
    private Set<PostComment> postComments = new LinkedHashSet<>();
    @Column(name = "password_hash",columnDefinition = "varchar(32)")
    private String passwordHash;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",columnDefinition = "bigint(20)")
    private Long id;
    @Column(name = "middle_name",columnDefinition = "varchar(50)")
    private String middleName;
    @OneToMany(mappedBy = "author")
    private Set<Post> posts = new LinkedHashSet<>();
    @Column(name = "first_name",columnDefinition = "varchar(50)")
    private String firstName;
    @Column(name = "email",columnDefinition = "varchar(50)")
    private String email;

    public String getName(){
        return this.firstName+" "+this.lastName+" "+this.middleName;
    }
}
