package fit.iuh.lab6.frontend;

import fit.iuh.lab6.models.Users;
import fit.iuh.lab6.repositories.UsersRepository;
import fit.iuh.lab6.services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.Optional;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private PostController postController;

    @GetMapping("/login")
    public String showLoginForm(){ return "login";}

    @PostMapping("/auth")
    public String login(HttpServletRequest request, @RequestParam String email, @RequestParam String password){
        Users user =  userService.getUserLogin(email, password);
        if(user!=null){
            user.setLastLogin(LocalDateTime.now());
            HttpSession session = request.getSession();
            session.setAttribute("userLogin", user);
            return "redirect:/post";
        }
        return  "redirect:/login";
    }
}
