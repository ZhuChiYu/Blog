package com.zcy.blog.service;

import com.zcy.blog.NotFoundException;
import com.zcy.blog.dao.BlogRepository;
import com.zcy.blog.po.Blog;
import com.zcy.blog.po.Type;
import com.zcy.blog.util.MyBeanUtils;
import com.zcy.blog.vo.BlogQuery;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class BlogServiceImpl implements BlogService {


    @Autowired
    private BlogRepository blogRepository;

    //根据主键查询
    @Override
    public Blog getBlog(Long id) {
        return blogRepository.getOne(id);
    }


    //分页查询
    //jpa封装了组合查询的方法
    @Override
    public Page<Blog> listBlog(Pageable pageable, BlogQuery blog) {
        return blogRepository.findAll(new Specification<Blog>() {
            @Override
            //
            public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                //组合条件放在list中
                List<Predicate> predicates = new ArrayList<>();
                if (!"".equals(blog.getTitle()) && blog.getTitle() != null) {
                    //模糊查询条件
                    predicates.add(cb.like(root.<String>get("title"), "%"+blog.getTitle()+"%"));
                }
                if (blog.getTypeId() != null) {
                    //类型判断
                    predicates.add(cb.equal(root.<Type>get("type").get("id"), blog.getTypeId()));
                }
                if (blog.isRecommend()) {
                    //是否推荐判断
                    predicates.add(cb.equal(root.<Boolean>get("recommend"), blog.isRecommend()));
                }

                //将上述组合自动拼接成sql语句
                cq.where(predicates.toArray(new Predicate[predicates.size()]));
                return null;
            }
        },pageable);
    }

    @Override
    public Page<Blog> listBlog(Pageable pageable) {
        return blogRepository.findAll(pageable);
    }

    @Override
    public Page<Blog> listBlog(String query, Pageable pageable) {
        return blogRepository.findByQuery(query,pageable);
    }

    @Override
    public List<Blog> listRecommendBlogTop(Integer size) {
        Sort sort = new Sort(Sort.Direction.DESC,"updateTime");
        Pageable pageable = new PageRequest(0, size, sort);
        return blogRepository.findTop(pageable);
    }

    //新增blog
    @Transactional
    @Override
    public Blog saveBlog(Blog blog) {
        if (blog.getId() == null) {
            blog.setCreateTime(new Date());
            blog.setUpdateTime(new Date());
            blog.setViews(0);
        } else {
            blog.setUpdateTime(new Date());
        }
        return blogRepository.save(blog);
    }

    //更新
    //数据库中根据id获取blog对象，若对象为空抛出自定义异常，若对象不为空，将要更新的博客拷贝给对象，
    @Transactional
    @Override
    public Blog updateBlog(Long id, Blog blog) {
        Blog b = blogRepository.getOne(id);
        if (b == null) {
            throw new NotFoundException("该博客不存在");
        }
        //过滤掉属性值为空的属性，只拷贝不为空的属性
        BeanUtils.copyProperties(blog,b, MyBeanUtils.getNullPropertyNames(blog));
        b.setUpdateTime(new Date());
        return blogRepository.save(b);
    }

    @Transactional
    @Override
    public void deleteBlog(Long id) {
        blogRepository.deleteById(id);
    }
}
