package konserid.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import konserid.model.User;
import konserid.repository.UserRepository;

@Controller
public class LoginController {
    
    @Autowired
    private UserRepository userRepository;
    
    @GetMapping("/login")
    public String showLogin() { return "login"; }

    @PostMapping("/login")
    public String doLogin(@RequestParam String username, @RequestParam String password, HttpSession session) {
        User user = userRepository.findByUsernameAndPassword(username, password);
        if (user != null) {
            session.setAttribute("user", user);
            session.setAttribute("userLoggedIn", true);
            session.setAttribute("role", user.getRole());
            if ("ADMIN".equals(user.getRole())) {
                return "redirect:/admin/dashboard";
            }
            return "redirect:/daftar-konser";
        }
        return "redirect:/login?error";
    }
    
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/daftar-konser";
    }
    
    @GetMapping("/daftar")
    public String showRegister() { 
    	return "register"; }

    @PostMapping("/daftar")
    public String doRegister(@RequestParam String username, @RequestParam String password) {
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password);
        newUser.setRole("USER");
  
        userRepository.save(newUser);
        return "redirect:/login"; 
    }
}