package konserid.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import jakarta.servlet.http.HttpSession;
import konserid.repository.KonserRepository;
import konserid.repository.GuestStarRepository;

@Controller
public class AdminDashboardController {

    @Autowired
    private KonserRepository konserRepository;
    @Autowired
    private GuestStarRepository guestStarRepository;

    @GetMapping("/admin/dashboard")
    public String dashboard(HttpSession session, Model model) {
        String role = (String) session.getAttribute("role");
        if (role == null || !role.equals("ADMIN")) return "redirect:/login";

        model.addAttribute("totalKonser", konserRepository.count());
        model.addAttribute("totalArtis", guestStarRepository.count());
        return "admin-dashboard";
    }
}
