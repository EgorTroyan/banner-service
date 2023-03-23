package com.troian.bannerservice.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    @NotBlank(message = "name is required")
    private String name;

    @Column(name = "name_id",
            unique = true)
    @NotBlank(message = "name id is required")
    private String nameId;

    @ManyToMany(mappedBy = "categories", fetch = FetchType.LAZY)
    @ToString.Exclude
    @JsonIgnore
    private Set<Banner> banners;

    private boolean isActive;

    public Category(String name, String nameId, boolean isActive) {
        this.name = name;
        this.nameId = nameId;
        this.isActive = isActive;
    }

    public Set<Banner> getBanners() {
        if(banners == null) {
            banners = new HashSet<>();
        }
        return banners;
    }

    public void addBanner(Banner banner) {
        if(banners == null) {
            banners = new HashSet<>();
        }
        banners.add(banner);
    }
}