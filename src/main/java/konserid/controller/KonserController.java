package konserid.controller;

import konserid.model.Konser;

import konserid.repository.KonserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class KonserController {
    
    @Autowired
    private KonserRepository konserRepository;
    
    @GetMapping("/")
    public String home(@RequestParam(name = "keyword", required = false) String keyword, Model model) {
        List<Konser> list;
        if (keyword != null && !keyword.trim().isEmpty()) {
            list = konserRepository.findByNamaEventContainingIgnoreCase(keyword.trim());
        } else {
            list = konserRepository.findAll();
        }
        model.addAttribute("listKonser", list);
        model.addAttribute("trending", konserRepository.findTop5ByOrderByWishlist());
        return "daftar-konser";
    }
    
    @GetMapping("/daftar-konser")
    public String listKonser(@RequestParam(name = "keyword", required = false) String keyword, Model model) {
        List<Konser> list;
        if (keyword != null && !keyword.trim().isEmpty()) {
            list = konserRepository.findByNamaEventContainingIgnoreCase(keyword.trim());
        } else {
            list = konserRepository.findAll();
        }
        model.addAttribute("listKonser", list);
        model.addAttribute("trending", konserRepository.findTop5ByOrderByWishlist());
        return "daftar-konser"; 
    }
    
    @GetMapping("/tambah")
    public String tampilkanFormTambah(HttpSession session, Model model) {
        String role = (String) session.getAttribute("role");
        if (role == null || !role.equals("ADMIN")) {
            return "redirect:/login"; 
        }
        model.addAttribute("konser", new Konser());
        return "tambah-konser"; 
    }
    
    @PostMapping("/tambah")
    public String simpanKonser(@ModelAttribute("konser") Konser konser, 
    		HttpSession session) {
        String role = (String) session.getAttribute("role");
        if (role == null || !role.equals("ADMIN")) return "redirect:/login";
        
        konserRepository.save(konser); 
        return "redirect:/admin/konser"; 
    }
    
    @GetMapping("/edit/{id}")
    public String tampilFromEdit(@PathVariable("id") int id, HttpSession session, Model model) {
        // Cek Admin
        String role = (String) session.getAttribute("role");
        if (role == null || !role.equals("ADMIN")) return "redirect:/login";

        Konser konser = konserRepository.findById(id).orElse(null);
        if (konser == null) return "redirect:/daftar-konser";
        
        model.addAttribute("konser", konser);
        return "tambah-konser";
    }

    @GetMapping("/hapus/{id}")
    public String hapusKonser(@PathVariable("id") int id, HttpSession session) {
        // Cek Admin
        String role = (String) session.getAttribute("role");
        if (role == null || !role.equals("ADMIN")) return "redirect:/login";

        konserRepository.deleteById(id);
        return "redirect:/admin/konser";
    }
    
    @GetMapping("/admin/konser")
    public String listKonserAdmin(HttpSession session, Model model) {
        String role = (String) session.getAttribute("role");
        if (role == null || !role.equals("ADMIN")) return "redirect:/login";

        model.addAttribute("listKonser", konserRepository.findAll());
        return "kelola-konser";
    }
     
    @GetMapping("/konser/detail/{id}")
    public String tampilDetailKonser(@PathVariable("id") int id, Model model) {
        Konser konser = konserRepository.findById(id).orElse(null);
        if (konser == null) return "redirect:/daftar-konser";
        
        model.addAttribute("konser", konser);
        return "detail-konser"; 
    }
}