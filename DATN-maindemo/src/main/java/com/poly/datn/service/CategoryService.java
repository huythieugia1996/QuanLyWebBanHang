package com.poly.datn.service;

import com.poly.datn.vo.CategoryVO;
import com.poly.datn.vo.ProductVO;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<CategoryVO> getCategories();

    CategoryVO createCategory(CategoryVO categoryVO, Principal principal);

    CategoryVO deleteCategory(Integer id, Principal principal);

    CategoryVO updateCategory(CategoryVO categoryVO, Principal principal);
}
