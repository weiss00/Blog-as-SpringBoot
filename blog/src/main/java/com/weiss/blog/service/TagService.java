package com.weiss.blog.service;

import com.weiss.blog.entity.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TagService {

    Tag saveTag(Tag tag);

    Tag getTag(Long id);

    Tag getTagByName(String name);

    Tag updateTag(Long id, Tag tag);

    List<Tag> listTag();

    List<Tag> listTag(String ids);

    Page<Tag> listTag(Pageable pageable);

    List<Tag> listTagTop(Integer size);

    void deleteTag(Long id);



}
