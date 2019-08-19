package com.zcy.blog.service;

import com.zcy.blog.po.Blog;
import com.zcy.blog.vo.BlogQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface BlogService {

    //根据id查询blog
    Blog getBlog(Long id);

    //分页查询
    Page<Blog> listBlog(Pageable pageable, BlogQuery blog);

    //新增
    Blog saveBlog(Blog blog);

    //修改
    Blog updateBlog(Long id, Blog blog);

    //删除
    void deleteBlog(Long id);

    Page<Blog> listBlog(Pageable pageable);

    List<Blog> listRecommendBlogTop(Integer size);
}
