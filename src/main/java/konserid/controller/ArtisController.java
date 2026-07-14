package konserid.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;
import konserid.model.GuestStar;
import konserid.repository.GuestStarRepository;
import konserid.repository.KonserRepository;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;

@Controller
public class ArtisController {

    @Autowired
    private GuestStarRepository guestStarRepository;
    @Autowired
    private KonserRepository konserRepository;

    private boolean bukanAdmin(HttpSession session) {
        String role = (String) session.getAttribute("role");
        return role == null || !role.equals("ADMIN");
    }

    @GetMapping("/artis")
    public String listArtis(HttpSession session, Model model) {
        if (bukanAdmin(session)) return "redirect:/login";

        List<GuestStar> semuaArtis = guestStarRepository.findAll();

        Map<String, List<GuestStar>> grouped = semuaArtis.stream()
                .collect(Collectors.groupingBy(GuestStar::getNamaArtis, LinkedHashMap::new, Collectors.toList()));

        model.addAttribute("groupedArtis", grouped);
        return "daftar-artis";
    }

    @GetMapping("/artis/tambah")
    public String formTambah(HttpSession session, Model model) {
        if (bukanAdmin(session)) return "redirect:/login";
        model.addAttribute("artis", new GuestStar());
        model.addAttribute("listKonser", konserRepository.findAll());
        return "tambah-artis";
    }

    @PostMapping("/artis/tambah")
    public String simpanArtis(@ModelAttribute("artis") GuestStar artis, HttpSession session) {
        if (bukanAdmin(session)) return "redirect:/login";
        guestStarRepository.save(artis);
        return "redirect:/artis";
    }

    @GetMapping("/artis/edit/{id}")
    public String formEdit(@PathVariable("id") int id, HttpSession session, Model model) {
        if (bukanAdmin(session)) return "redirect:/login";
        GuestStar artis = guestStarRepository.findById(id).orElse(null);
        if (artis == null) return "redirect:/artis";
        model.addAttribute("artis", artis);
        model.addAttribute("listKonser", konserRepository.findAll());
        return "tambah-artis";
    }

    @GetMapping("/artis/hapus/{id}")
    public String hapusArtis(@PathVariable("id") int id, HttpSession session) {
        if (bukanAdmin(session)) return "redirect:/login";
        guestStarRepository.deleteById(id);
        return "redirect:/artis";
    }
}