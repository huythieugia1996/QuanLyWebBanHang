package com.poly.datn.dao;

import com.poly.datn.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryDAO extends JpaRepository<Category, Integer> {
    @Query("select  c from Category  c where  c.type =:id and c.status=:status")
    List<Category> findChildCategories(@Param("id") Integer id, @Param("status") Boolean status);

    @Query(nativeQuery = true, value = "select * from Category")
    List<Category> findRootCategories();

    @Query("select  c from Category  c where  c.type =:id")
    List<Category> findChildCategories(@Param("id") Integer id);

    Category findOneById(Integer id);


}
