package konserid.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;


import jakarta.servlet.http.HttpSession;
import konserid.model.Konser;
import konserid.model.User;
import konserid.model.Wishlist;
import konserid.repository.KonserRepository;
import konserid.repository.WishlistRepository;

@Controller
public class WishlistController {

    @Autowired
    private KonserRepository konserRepository;

    @Autowired
    private WishlistRepository wishlistRepository;

    @PostMapping("/wishlist/add")
    public String addWishlist(@RequestParam Integer konserId, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) return "redirect:/login";

        Konser konser = konserRepository.findById(konserId).orElse(null);
        
        if (konser != null) {
            Wishlist wishlist = new Wishlist();
            wishlist.setUser(user);
            wishlist.setKonser(konser);
            
            wishlistRepository.save(wishlist);
        }
        
        return "redirect:/wishlist";
    }
    
    @GetMapping("/wishlist")
    public String showWishlist(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) return "redirect:/login";

        List<Wishlist> wishlist = wishlistRepository.findByUser(user); 
        model.addAttribute("listWishlist", wishlist);
        
        return "wishlist"; // 
    } 
    
    @GetMapping("/wishlist/delete/{id}")
    public String deleteWishlist(@PathVariable Integer id) {
        wishlistRepository.deleteById(id);
        return "redirect:/wishlist";
    }
}
