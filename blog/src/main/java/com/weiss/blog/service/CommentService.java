package com.weiss.blog.service;

import com.weiss.blog.entity.Comment;

import java.util.List;


/**
 * 评论区的Service
 */
public interface CommentService {

    List<Comment> listCommentByBlogId(Long blogId);

    Comment saveComment(Comment comment);
}
