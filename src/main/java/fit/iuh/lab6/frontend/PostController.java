package fit.iuh.lab6.frontend;

import fit.iuh.lab6.models.Post;
import fit.iuh.lab6.models.Users;
import fit.iuh.lab6.services.PostService;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class PostController {
    @Autowired
    private final PostService postService;


    public PostController(PostService postService) {
        this.postService = postService;

    }

    @PostMapping("/create-post")
    public String createPost(HttpServletRequest req,
                             @RequestParam("title") Optional<String> title,
                             @RequestParam("content") Optional<String> content){
        Post post = new Post();
        post.setCreateAt(LocalDateTime.now());
        post.setTitle(title.orElse("default title"));
        post.setContent(content.orElse("nothing in here"));
        post.setPublished(true);
        post.setPublishedAt(LocalDateTime.now());
        post.setCreateAt(LocalDateTime.now());
        HttpSession session =req.getSession();
        Users user = (Users) session.getAttribute("userLogin");
        post.setAuthor(user);
        postService.save(post);
        return "redirect:/post";
    }
    @GetMapping("/post")
    public  String showPostPaging(Model model,HttpServletRequest req,
                                  @RequestParam("page") Optional<Integer> page,
                                  @RequestParam("size") Optional<Integer> size){
        Users userLogin = (Users) req.getSession().getAttribute("userLogin");
        if(userLogin==null)
            return "redirect:/login";
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(3);
        Page<Post> postPage = postService.findAll(currentPage, pageSize, "id", "desc");
        model.addAttribute("postPage", postPage);
        int totalPage = postPage.getTotalPages();
        if(totalPage>0){
            List<Integer> pageNumbers = IntStream.rangeClosed(1,totalPage)
                    .boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "post";
    }
    @GetMapping("/post/my-posts")
    public  String showPostUserPaging(Model model,
                                  @RequestParam("page") Optional<Integer> page,
                                  @RequestParam("size") Optional<Integer> size){
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(3);
        Users user = new Users();
        Page<Post> postPage = postService.findByAuthor(user,currentPage, pageSize, "id", "desc");
        model.addAttribute("postPage", postPage);
        int totalPage = postPage.getTotalPages();
        if(totalPage>0){
            List<Integer> pageNumbers = IntStream.rangeClosed(1,totalPage)
                    .boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "post";
    }
}
