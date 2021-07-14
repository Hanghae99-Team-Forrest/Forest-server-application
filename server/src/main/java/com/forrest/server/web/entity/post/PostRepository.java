package com.forrest.server.web.entity.post;

import com.forrest.server.web.entity.category.Category;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Created by Bloo
 * @Date: 2021/07/10
 */

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByCategory( Category category );
}
