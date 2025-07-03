package com.mlhero.mobile_legends_hero.repository;

import com.mlhero.mobile_legends_hero.model.Hero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeroRepository extends JpaRepository<Hero, Long> {
    // Spring Data JPA secara otomatis menyediakan method CRUD dasar
    // Anda bisa menambahkan method kustom di sini jika diperlukan
}
