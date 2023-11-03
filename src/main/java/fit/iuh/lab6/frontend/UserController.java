package fit.iuh.lab6.frontend;

import fit.iuh.lab6.models.Users;
import fit.iuh.lab6.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public String showUserProfile(Model model, HttpServletRequest req){
        HttpSession session = req.getSession();
        Users user = (Users) session.getAttribute("userLogin");
        if(user==null)
            return "login";
        model.addAttribute("userLogin", user);
        return "profile";
    }
}
