package com.mlhero.mobile_legends_hero.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tm_hero")
public class Hero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_m_hero")
    private Long idMHero; // Menggunakan Long untuk auto-increment ID

    @Column(name = "id_hero", unique = true, nullable = false)
    private Integer idHero;

    @Column(name = "nama_hero", nullable = false, length = 100)
    private String namaHero;

    @Enumerated(EnumType.STRING)
    @Column(name = "kategori", nullable = false, length = 26)
    private KategoriHero kategori;

    @Column(name = "gender", nullable = false, length = 10)
    private String gender;

    // Enum untuk KategoriHero
    public enum KategoriHero {
        MAGE, ASSASSIN, FIGHTER, TANK, MARKSMAN, SUPPORT
    }

    // Constructors
    public Hero() {
    }

    public Hero(Integer idHero, String namaHero, KategoriHero kategori, String gender) {
        this.idHero = idHero;
        this.namaHero = namaHero;
        this.kategori = kategori;
        this.gender = gender;
    }

    // Getters and Setters
    public Long getIdMHero() {
        return idMHero;
    }

    public void setIdMHero(Long idMHero) {
        this.idMHero = idMHero;
    }

    public Integer getIdHero() {
        return idHero;
    }

    public void setIdHero(Integer idHero) {
        this.idHero = idHero;
    }

    public String getNamaHero() {
        return namaHero;
    }

    public void setNamaHero(String namaHero) {
        this.namaHero = namaHero;
    }

    public KategoriHero getKategori() {
        return kategori;
    }

    public void setKategori(KategoriHero kategori) {
        this.kategori = kategori;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
