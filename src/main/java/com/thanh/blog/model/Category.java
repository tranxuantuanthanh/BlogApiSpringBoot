package com.thanh.blog.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "category")
public class Category{
    @Id
    @GeneratedValue
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Category category;

    @OneToMany(mappedBy = "category")
    private List<Category> childrenCategories;

    @Column(nullable = false)
    private String title;
    private String content;
    @Column(nullable = false, unique = true)
    private String slug;

    @JsonIgnore
    @ManyToMany(mappedBy = "categories")
    private List<Post> posts;
}
