package com.mlhero.mobile_legends_hero.controller;

import com.mlhero.mobile_legends_hero.model.Hero;
import com.mlhero.mobile_legends_hero.repository.HeroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/heroes")
public class HeroController {

    @Autowired
    private HeroRepository heroRepository;

    // Menampilkan daftar hero
    @GetMapping
    public String listHeroes(Model model) {
        List<Hero> heroes = heroRepository.findAll();
        model.addAttribute("heroes", heroes);
        return "hero-list"; // Akan me-resolve ke src/main/resources/templates/hero-list.html
    }

    // Menampilkan form untuk menambah hero baru
    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("hero", new Hero());
        model.addAttribute("kategoriList", Hero.KategoriHero.values()); // Untuk dropdown kategori
        return "hero-form"; // Akan me-resolve ke src/main/resources/templates/hero-form.html
    }

    // Memproses penambahan hero baru
    @PostMapping("/save")
    public String saveHero(@ModelAttribute("hero") Hero hero, RedirectAttributes redirectAttributes) {
        try {
            heroRepository.save(hero);
            redirectAttributes.addFlashAttribute("message", "Hero berhasil ditambahkan!");
            redirectAttributes.addFlashAttribute("alertClass", "alert-success");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "Gagal menambahkan hero: ID Hero sudah ada atau data tidak valid.");
            redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
        }
        return "redirect:/heroes";
    }

    // Menampilkan form untuk mengedit hero yang sudah ada
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Hero> hero = heroRepository.findById(id);
        if (hero.isPresent()) {
            model.addAttribute("hero", hero.get());
            model.addAttribute("kategoriList", Hero.KategoriHero.values());
            return "hero-form";
        } else {
            redirectAttributes.addFlashAttribute("message", "Hero tidak ditemukan!");
            redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
            return "redirect:/heroes";
        }
    }

    // Memproses update hero
    @PostMapping("/update/{id}")
    public String updateHero(@PathVariable("id") Long id, @ModelAttribute("hero") Hero hero, RedirectAttributes redirectAttributes) {
        hero.setIdMHero(id); // Pastikan ID yang diupdate benar
        try {
            heroRepository.save(hero);
            redirectAttributes.addFlashAttribute("message", "Hero berhasil diupdate!");
            redirectAttributes.addFlashAttribute("alertClass", "alert-success");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "Gagal mengupdate hero: ID Hero sudah ada atau data tidak valid.");
            redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
        }
        return "redirect:/heroes";
    }


    // Menghapus hero
    @GetMapping("/delete/{id}")
    public String deleteHero(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        try {
            heroRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("message", "Hero berhasil dihapus!");
            redirectAttributes.addFlashAttribute("alertClass", "alert-success");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "Gagal menghapus hero.");
            redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
        }
        return "redirect:/heroes";
    }
}
