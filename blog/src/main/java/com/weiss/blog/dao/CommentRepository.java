package com.weiss.blog.dao;

import com.weiss.blog.entity.Comment;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

//    Sort排序对象
//    查询父级评论id为null  即为自己就是父级评论的blog
    List<Comment> findByBlogIdAndParentCommentNull(Long blogId, Sort sort);


}
