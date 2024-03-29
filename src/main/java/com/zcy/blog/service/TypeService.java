package com.zcy.blog.service;

import com.zcy.blog.po.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TypeService {

//    Type saveType(Type type);
//
//    Type getType(Long id);
//
//    Type getTypeByName(String name);
//
//    Page<Type> listType(Pageable pageable);
//
//    Type updateType(Long id,Type type);
//
//    void deleteType(Long id);
    Type saveType(Type type);

    Type getType(Long id);

    Type getTypeByName(String name);

    Page<Type> listType(Pageable pageable);

    List<Type> listType();

    List<Type> listTypeTop(Integer size);

    Type updateType(Long id,Type type);

    void deleteType(Long id);
}
