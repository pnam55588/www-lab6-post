package fit.iuh.lab6;

import fit.iuh.lab6.models.Post;
import fit.iuh.lab6.models.PostComment;
import fit.iuh.lab6.models.Users;
import fit.iuh.lab6.repositories.PostCommentRepository;
import fit.iuh.lab6.repositories.PostRepository;
import fit.iuh.lab6.repositories.UsersRepository;
import fit.iuh.lab6.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

@SpringBootApplication
@RequiredArgsConstructor
public class Lab6Application {
    private String LOREM = "Lorem ipsum dosemperd tortor.";

    public static void main(String[] args) {
        SpringApplication.run(Lab6Application.class, args);
    }
    private final PostRepository postRepository;
    private final UsersRepository usersRepository;
    private final PostCommentRepository postCommentRepository;
    @Autowired
    private PostService postService;
//    @Bean
    CommandLineRunner initPosts(){
        return args -> {
            for(int i=1;i<1000;i++){
                Random ran = new Random();
                Users user = new Users();
                user.setEmail("user_"+i+"@gmail.com");
                user.setIntro("intro");
                user.setFirstName("user"+i);
                user.setLastName("nguyen");
                user.setLastLogin(LocalDateTime.now());
                user.setMiddleName("van");
                user.setMobile(ran.nextInt(111111111,999999999)+"");
                user.setPasswordHash("123");
                user.setProfile("nothing");
                user.setRegisteredAt(LocalDateTime.now());
                usersRepository.save(user);

                Post post = new Post();
                post.setPublished(true);
                post.setTitle("title"+i);
                post.setContent(LOREM);
                post.setAuthor(user);
                post.setCreateAt(LocalDateTime.now());
                post.setMetaTitle("meta title");
                post.setSummary("sumary");
                post.setUpdatedAt(LocalDateTime.now());
                post.setPublishedAt(LocalDateTime.now());
                postRepository.save(post);
                for(int j=0;j<2;j++){
                    PostComment postComment = new PostComment();
                    postComment.setContent("init post comment");
                    postComment.setTitle("title");
                    postComment.setUser(user);
                    postComment.setCreatedAt(LocalDateTime.now());
                    postComment.setPublishedAt(LocalDateTime.now());
                    postComment.setPublished(true);
                    postComment.setPost(post);
                    postCommentRepository.save(postComment);

                    PostComment child = new PostComment();
                    child.setPublished(true);
                    child.setContent("child");
                    child.setCreatedAt(LocalDateTime.now());
                    child.setTitle("test child");
                    child.setPublishedAt(LocalDateTime.now());
                    child.setUser(user);
                    child.setParent(postComment);
                    postCommentRepository.save(child);
                }
            }
        };
    }


}
