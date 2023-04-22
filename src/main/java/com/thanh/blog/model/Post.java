package com.thanh.blog.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "post", uniqueConstraints = { @UniqueConstraint(columnNames = { "slug" }) })
public class Post {
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE)
        private Long id;
        @Column(nullable = false)
        private String title;
        private String metaTitle;
        @Column(nullable = false)
        private String slug;
        private String summary;
        @Builder.Default
        private Boolean published = true;
        @Builder.Default
        private Date createDate = new Date();
        @Column(columnDefinition = "text not null")
        private String content;

        @JsonIgnore
        @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "author_id")
        private User user;

        @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
        @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
        private List<PostComment> postComments;

        @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
        @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        @JoinTable(name = "post_category", joinColumns = @JoinColumn(name = "post_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
        private List<Category> categories;

        @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
        @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        @JoinTable(name = "post_tag", joinColumns = @JoinColumn(name = "post_id"), inverseJoinColumns = @JoinColumn(name = "tag_id"))
        private List<Tag> tags;
}
