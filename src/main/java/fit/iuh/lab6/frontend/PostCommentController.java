package fit.iuh.lab6.frontend;

import fit.iuh.lab6.models.Post;
import fit.iuh.lab6.models.PostComment;
import fit.iuh.lab6.models.Users;
import fit.iuh.lab6.services.PostCommentService;
import fit.iuh.lab6.services.PostService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.Optional;

@Controller
public class PostCommentController {
    @Autowired
    private PostCommentService commentService;
    @Autowired
    private PostService postService;

    @PostMapping("/create-comment")
    public String createComment(HttpServletRequest req,
                                @RequestParam("post") Optional<Long> postId,
                                @RequestParam("title") Optional<String> title,
                                @RequestParam("content") Optional<String> content){
        PostComment postComment = new PostComment();
        postComment.setTitle(title.orElse("comment"));
        postComment.setContent(content.orElse("comment"));
        postComment.setCreatedAt(LocalDateTime.now());
        postComment.setPublishedAt(LocalDateTime.now());
        postComment.setPublished(true);
        postComment.setUser((Users) req.getSession().getAttribute("userLogin"));
        Post post = postService.findByID(postId.orElse(0L)).get();
        postComment.setPost(post);
        commentService.save(postComment);
        return "redirect:/post";
    }
}
