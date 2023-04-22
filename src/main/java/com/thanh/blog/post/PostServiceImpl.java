package com.thanh.blog.post;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.thanh.blog.exception.AccessDeniedException;
import com.thanh.blog.exception.BadRequestException;
import com.thanh.blog.exception.ResourceNotFoundException;
import com.thanh.blog.model.Category;
import com.thanh.blog.model.Post;
import com.thanh.blog.model.Role;
import com.thanh.blog.model.Tag;
import com.thanh.blog.model.User;
import com.thanh.blog.payload.response.ApiResponse;
import com.thanh.blog.payload.response.PageResponse;
import com.thanh.blog.repository.CategoryRepository;
import com.thanh.blog.repository.TagRepository;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;
    private final TagRepository tagRepository;
    private final PostListDtoMapper PostListDtoMapper;
    private final PostDtoMapper postDtoMapper;

    @Override
    public PageResponse<PostListDTO> findByCategory(String slug, Integer pageNumber, Integer pageSize) {
        Long totalPosts = postRepository.countByCategories_Slug(slug);
        if (totalPosts == 0) {
            throw new ResourceNotFoundException("Post", "category", totalPosts);
        }
        Integer totalPages = Math.round((float) totalPosts / pageSize);
        if (totalPages < pageNumber)
            pageNumber = totalPages;
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.Direction.DESC, "id");
        List<PostListDTO> posts = postRepository.findByCategories_Slug(slug, pageable)
                .getContent()
                .stream()
                .map(PostListDtoMapper)
                .collect(Collectors.toList());
        PageResponse<PostListDTO> pageResponse = new PageResponse<>(
                pageNumber,
                pageSize,
                totalPages,
                totalPosts,
                posts);
        return pageResponse;
    }

    @Override
    public PageResponse<PostListDTO> findByTags(List<String> tags, Integer pageNumber, Integer pageSize) {
        String[] tagArray = tags.toArray(new String[tags.size()]);
        List<Post> postList = postRepository.findByTags_SlugIn(tagArray);
        Long totalPosts = Long.valueOf(postList.size());
        if (totalPosts == 0) {
            throw new ResourceNotFoundException("Post", "tags", tags);
        }
        Integer totalPages = Math.round((float) totalPosts / pageSize);
        if (totalPages < pageNumber)
            pageNumber = totalPages;
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.Direction.DESC, "id");
        List<PostListDTO> posts = postRepository.findByTags_SlugIn(tagArray, pageable)
                .getContent()
                .stream()
                .map(PostListDtoMapper)
                .collect(Collectors.toList());
        PageResponse<PostListDTO> pageResponse = new PageResponse<>(
                pageNumber,
                pageSize,
                totalPages,
                totalPosts,
                posts);
        return pageResponse;
    }

    @Override
    public PostListDTO createPost(User user, CreatePostRequest createPostRequest) {
        if (Boolean.TRUE.equals(postRepository.existsBySlug(createPostRequest.getSlug()))) {
            throw new BadRequestException("Slug already taken.");
        }
        Post post = Post.builder()
                .user(user)
                .title(createPostRequest.getTitle())
                .slug(createPostRequest.getSlug())
                .summary(createPostRequest.getSummary())
                .metaTitle(createPostRequest.getMetaTitle())
                .content(createPostRequest.getContent())
                .published(true)
                .createDate(new Date())
                .categories(categoryRepository.findAllById(createPostRequest.getCategories()))
                .tags(tagRepository.findAllById(createPostRequest.getTags()))
                .build();
        postRepository.save(post);
        return PostListDtoMapper.apply(post);
    }

    @Override
    public PostListDTO updatePost(User user, UpdatePostRequest data) {
        Optional<Post> post = postRepository.findById(data.getId());
        if (post.isEmpty()) {
            throw new BadRequestException("The post not exists on the system.");
        }
        if (post.get().getUser().getId() != user.getId()) {
            throw new AccessDeniedException("You do not have access to this method.");
        }
        List<Category> categories = categoryRepository.findAllById(data.getCategories());
        List<Tag> tags = tagRepository.findAllById(data.getTags());
        post.get().setCategories(categories);
        post.get().setContent(data.getContent());
        post.get().setSlug(data.getSlug());
        post.get().setMetaTitle(data.getMetaTitle());
        post.get().setTitle(data.getTitle());
        post.get().setTags(tags);
        postRepository.save(post.get());
        return PostListDtoMapper.apply(post.get());
    }

    @Override
    public ApiResponse deletePost(User user, Long postId) {
        Optional<Post> post = postRepository.findById(postId);
        if (post.isEmpty()) {
            throw new BadRequestException("The post not exists on the system.");
        }
        if (post.get().getUser().getId() != user.getId() && !user.getRole().equals(Role.ROLE_ADMIN)) {
            throw new AccessDeniedException("You do not have access to this method.");
        }
        postRepository.delete(post.get());
        return new ApiResponse(true, "Delete successfully");
    }

    @Override
    public PostListDTO updatePostPublished(User user, UpdatePublishedRequest data) {
        Optional<Post> post = postRepository.findById(data.getId());
        if (post.isEmpty()) {
            throw new BadRequestException("The post not exists on the system.");
        }
        if (post.get().getUser().getId() != user.getId()) {
            throw new AccessDeniedException("You do not have access to this method.");
        }
        if (post.get().getPublished().equals(data.getNewPublished())) {
            throw new BadRequestException("The new value must be different from the original value.");
        }
        post.get().setPublished(data.getNewPublished());
        postRepository.save(post.get());
        return PostListDtoMapper.apply(post.get());
    }

    @Override
    public PageResponse<PostListDTO> findAllPost(Integer pageNumber, Integer pageSize) {
        Long totalPosts = postRepository.count();
        Integer totalPages = Math.round((float) totalPosts / pageSize);
        if (totalPages < pageNumber)
            pageNumber = totalPages;
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.Direction.DESC, "id");
        List<PostListDTO> posts = postRepository.findAll(pageable)
                .getContent()
                .stream()
                .map(PostListDtoMapper)
                .collect(Collectors.toList());
        PageResponse<PostListDTO> pageResponse = new PageResponse<>(
            pageNumber,
            pageSize,
            totalPages,
            totalPosts,
            posts
        );
        return pageResponse;
    }

    public PostDTO getPostById(Long postId) {
        var post = postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("post", "id", postId));
        return postDtoMapper.apply(post);
    }
}
