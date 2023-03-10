package com.poly.datn.service.impl;

import com.poly.datn.dao.*;
import com.poly.datn.entity.*;
import com.poly.datn.service.*;
import com.poly.datn.service.AutoTask.AutoTaskService;
import com.poly.datn.utils.CheckRole;
import com.poly.datn.utils.PriceUtils;
import com.poly.datn.utils.ProductUtils;
import com.poly.datn.utils.StringFind;
import com.poly.datn.vo.*;
import com.poly.datn.vo.VoBoSung.ProductDTO.UpdateProductDTO;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss aa");
    @Autowired
    ProductUtils productUtils;

    @Autowired
    ProductDAO productDAO;

    @Autowired
    ProductCategoryDAO productCategoryDAO;

    @Autowired
    ProductColorDAO productColorDAO;

    @Autowired
    ProductDetailsDAO productDetailsDAO;

    @Autowired
    BlogDAO blogDAO;

    @Autowired
    BlogDetailsDAO blogDetailsDAO;

    @Autowired
    StringFind stringFind;

    @Autowired
    CommentService commentService;

    @Autowired
    SaleService saleService;

    @Autowired
    CheckRole checkRole;

    @Autowired
    SaleDAO saleDAO;

    @Autowired
    ProductSaleDAO productSaleDAO;

    @Autowired
    PriceUtils priceUtils;
    // Begin code of MA
    @Autowired
    ProductDetailService productDetailService;
    @Autowired
    ProductColorService productColorService;
    @Autowired
    ProductCategoryService productCategoryService;

    @Autowired
    CategoryDAO categoryDAO;

    public List<ProductVO> getListP(Optional<Integer> cate, Optional<String> find) {
        List<Product> products;
        if (cate.isPresent() && find.isPresent()) {
            products = getListByCate(cate.get());
            if (products.size() > 0) products = stringFind.getMatchProduct(products, find.get());
            else products = new ArrayList<>();
        } else if (cate.isPresent()) {
            products = getListByCate(cate.get());
        } else if (find.isPresent()) {
            products = productDAO.findAll();
            if (products.size() > 0) products = stringFind.getMatchProduct(products, find.get());
            else products = new ArrayList<>();
        } else {
            products = productDAO.findAll();
        }

        List<ProductVO> productVOS = new ArrayList<>();
        for (Product product : products) {

            ProductVO productVO = productUtils.convertToVO(product);
            productVOS.add(productVO);
        }
        return productVOS;
    }

    @Override
    public List<ProductVO> getList(Optional<Integer> cate, Optional<String> find) {
        List<ProductVO> productVOS = this.getListP(cate, find);
        List<ProductVO> productVO = new ArrayList<>();
        for (ProductVO productVO1 : productVOS) {
            if (productVO1.getStatus().equals("??ang b??n")) {
                productVO.add(productVO1);
            } else {
                continue;
            }
        }
        return productVO;
    }

    @Override
    public List<ProductVO> getListAdmin(Optional<Integer> cate, Optional<String> find, Principal principal) {
        checkPrincipal(principal);
        List<ProductVO> productVOS = this.getListP(cate, find);
        List<ProductVO> productVO = new ArrayList<>();
        for (ProductVO productVO1 : productVOS) {
            if (productVO1.getStatus().equals("???? x??a")) {
                continue;
            } else {
                productVO.add(productVO1);
            }
        }
        return productVO;
    }

    @Override
    public List<ProductVO> getListDeleteAdmin(Optional<Integer> cate, Optional<String> find, Principal principal) {
        checkPrincipal(principal);
        List<ProductVO> productVOS = this.getListP(cate, find);
        List<ProductVO> productVO = new ArrayList<>();
        for (ProductVO productVO1 : productVOS) {
            if (productVO1.getStatus().equals("???? x??a")) {
                productVO.add(productVO1);
            } else {
                continue;
            }
        }
        return productVO;
    }

    @Override
    public List<ProductVO> getByPrice(Optional<Long> start, Optional<Long> end) {
        List<ProductVO> productVOS = new ArrayList<>();
        List<Product> products;
        if (start.isPresent() && end.isPresent()) {
            products = productDAO.findAllByPriceBetween(start.get(), end.get());
        } else if (start.isPresent() || end.isPresent()) {
            if (start.isPresent()) {
                products = productDAO.findAllByPriceGreaterThanEqual(start.get());
            } else {
                products = productDAO.findAllByPriceLessThanEqual(end.get());
            }
        } else {
            products = productDAO.findAll();
        }
        for (Product product : products) {
            ProductVO productVO = productUtils.convertToVO(product);
            productVOS.add(productVO);
        }
        return productVOS;
    }

    @Override
    public ProductCategoryVO selectCate(Integer pid, Integer cid, Principal principal) {
        checkPrincipal(principal);
        ProductCategory productCategory = new ProductCategory();
        productCategory.setProductId(pid);
        productCategory.setCategoryId(cid);
        productCategory = productCategoryDAO.save(productCategory);
        ProductCategoryVO productCategoryVO = new ProductCategoryVO();
        BeanUtils.copyProperties(productCategory, productCategoryVO);
        return productCategoryVO;
    }

    @Override
    public Boolean unSelectCate(Integer pid, Integer cid, Principal principal) {
        checkPrincipal(principal);
        productCategoryDAO.unSelect(pid, cid);
        return true;
    }

    @Override
    public Object getBlogLess(Principal principal) {
        checkPrincipal(principal);
        List<ProductVO> vos = new ArrayList<>();
        for (Product product : productDAO.getNotContainBlog()) {
            ProductVO vo = productUtils.convertToVO(product);
            vos.add(vo);
        }
        return vos;
    }

    @Override
    public List<ProductVO> getDiscount() {
        List<ProductVO> productVOS = new ArrayList<>();
        for (Product product : productDAO.findAll()) {
            ProductVO vo = productUtils.convertToVO(product);
            if (vo.getDiscount() > 0) {
                productVOS.add(vo);
            }
        }
        Collections.sort(productVOS, Comparator.comparingLong(ProductVO::getDiscount).reversed());
        return productVOS;
    }

    @Override
    public Integer getByIdAndColor(Integer id, Integer cid) {
        ProductColor productColor = productColorDAO.findByProductIdAndColorId(id, cid);
        if (productColor == null)
            return 0;
        return productColor.getQuantity();
    }

    @Override
    public List<ProductVO> getTrending() {
        List<ProductVO> productVOS = new ArrayList<>();
        int i = 0;
        int j = 0;
        while (i < 8 && j < AutoTaskService.trending.size()) {
            int sum = AutoTaskService.trending.get(j).getProductVO().getProductColors()
                    .stream().reduce(0, (sub, pro) -> sub + pro.getQuantity(), Integer::sum);
            System.out.println(AutoTaskService.trending.get(j).toString());
            System.out.println(sum);
            System.out.println("\n\n\n");
            if (sum > 0) {
                productVOS.add(AutoTaskService.trending.get(j).getProductVO());
                i++;
            }
            j++;
        }

        return productVOS;
    }

    @Override
    public List<ProductVO> getMostNew() {
        List<ProductVO> productVOS = new ArrayList<>();
        for (Product product : productDAO.findNewTopProduct()) {
            ProductVO productVO = productUtils.convertToVO(product);
            productVOS.add(productVO);
        }
        return productVOS;
    }

    @Override
    public List<Product> getListByCate(Integer cateId) {
        List<Product> products = new ArrayList<>();
        List<ProductCategory> productCategories = productCategoryDAO.findAllByCategoryIdEquals(cateId);
        for (ProductCategory productCategory : productCategories) {
            Optional<Product> optionalProduct = productDAO.findById(productCategory.getProductId());

            List<ProductVO> productVOS = new ArrayList<>();

            if (optionalProduct.isPresent()) {
                products.add(optionalProduct.get());
            }
        }
        return products;
    }

    @Override
    public ProductVO getById(Integer id) throws NullPointerException {
        Product product = productDAO.findById(id).orElseThrow(() -> new NotImplementedException("Product not found with id: " + id));
        ProductVO productVO = productUtils.convertToVO(product);
        List<ProductCategoryVO> productCategoryVOS = new ArrayList<>();
        for (ProductCategory category : productCategoryDAO.findAllByProductIdEquals(productVO.getId())
        ) {
            ProductCategoryVO productCategoryVO = new ProductCategoryVO();
            BeanUtils.copyProperties(category, productCategoryVO);
            productCategoryVOS.add(productCategoryVO);
        }
        productVO.setProductCategories(productCategoryVOS);
        try {
            productVO.setBlogs(getBlogByProductIdAndType(productVO.getId(), 1));
        } catch (NullPointerException e) {
            productVO.setBlogs(null);
        }
        return productVO;
    }


//    private ProductVO convertToVO(Product product) {
//        ProductVO productVO = new ProductVO();
//        BeanUtils.copyProperties(product, productVO);
//        List<ProductColorVO> productColorVOS = new ArrayList<>();
//        for (ProductColor productColor : productColorDAO.findAllByProductIdEquals(productVO.getId())) {
//            ProductColorVO productColorVO = new ProductColorVO();
//            BeanUtils.copyProperties(productColor, productColorVO);
//            productColorVOS.add(productColorVO);
//        }
//        productVO.setProductColors(productColorVOS);
//        List<ProductDetailsVO> productDetailsVOS = new ArrayList<>();
//        List<String> photos = new ArrayList<>();
//        for (ProductDetails productDetails : productDetailsDAO.findAllByProductIdEquals(productVO.getId())) {
//            ProductDetailsVO productDetailsVO = new ProductDetailsVO();
//            if (productDetails.getPropertyName().equalsIgnoreCase("photo")) {
//                for (String photo : productDetails.getPropertyValue().split(",")) {
//                    photos.add(photo.trim());
//                }
//            } else {
//                BeanUtils.copyProperties(productDetails, productDetailsVO);
//                productDetailsVOS.add(productDetailsVO);
//            }
//        }
//        productVO.setProductDetails(productDetailsVOS);
//        productVO.setPhotos(photos);
//        productVO.setDiscount(priceUtils.maxDiscountAtPresentOf(productVO.getId()));
//
//        return productVO;
//    }


    private BlogVO getBlogByProductIdAndType(Integer productId, Integer type) throws NullPointerException {
        Blog blog = blogDAO.getByProductIdAndType(productId, type).orElseThrow(() -> new NullPointerException("Blog not Fount for product"));
        BlogVO blogVO = new BlogVO();
        List<BlogDetailsVO> blogDetailsVOS = new ArrayList<>();
        BeanUtils.copyProperties(blog, blogVO);
        blogVO.setTimeCreated(sdf.format(blog.getTimeCreated()));
        for (BlogDetails blogDetails : blogDetailsDAO.findByBlogId(blog.getId())) {
            BlogDetailsVO blogDetailsVO = new BlogDetailsVO();
            BeanUtils.copyProperties(blogDetails, blogDetailsVO);
            blogDetailsVOS.add(blogDetailsVO);
        }
        blogVO.setComments(commentService.getListByBlogId(blogVO.getId()));
        blogVO.setBlogDetails(blogDetailsVOS);
        return blogVO;
    }
    // End code of MA

    //admin

    @Override
    @Transactional
    public Object delete(Integer id, Principal principal) {
        checkPrincipal(principal);
        try {
            Product product = productDAO.getById(id);
            if (product == null) {
                throw new NotFoundException("api.error.API-003");
            }
            product.setStatus("???? x??a");
            productDAO.save(product);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    @Transactional
    public Object dontSell(Integer id, Principal principal) {
        checkPrincipal(principal);
        return changeStatus("Ng???ng kinh doanh", id);
    }

    @Override
    @Transactional
    public Object storageEmpty(Integer id, Principal principal) {
        checkPrincipal(principal);
        return changeStatus("H???t h??ng", id);
    }

    @Override
    @Transactional
    public Object comingsoon(Integer id, Principal principal) {
        checkPrincipal(principal);
        return changeStatus("H??ng s???p v???", id);
    }

    @Override
    @Transactional
    public Object productReady(Integer id, Principal principal) {
        checkPrincipal(principal);
        return changeStatus("??ang b??n", id);
    }

    @Override
    @Transactional
    public Object noSell(Integer id, Principal principal) {
        checkPrincipal(principal);
        return changeStatus("Kh??ng kinh doanh", id);
    }


    @Override
    public ProductVO newProduct(ProductVO productVO, Principal principal) {
        checkPrincipal(principal);
        Product product = new Product();
        BeanUtils.copyProperties(productVO, product);
        product.setStatus("Th??ng Tin Ch??a ?????");
        product = productDAO.save(product);

        List<ProductCategoryVO> productCategoryVO = productVO.getProductCategories();
        if (productCategoryVO == null) {
            throw new NotImplementedException("Ch??a th??m category");
        }
        List<ProductCategory> productCategories = new ArrayList<>();

        for (ProductCategoryVO productCategoryVO1 : productCategoryVO) {

            Category category = categoryDAO.findOneById(productCategoryVO1.getCategoryId());
            if (category == null) {
                throw new NotImplementedException("Ch??a th??m category");
            }
            ProductCategory productCategory = new ProductCategory();
            productCategoryVO1.setProductId(product.getId());
            productCategoryVO1.setCategoryId(category.getId());
            BeanUtils.copyProperties(productCategoryVO1, productCategory);
            productCategories.add(productCategory);
        }
        productCategoryDAO.saveAll(productCategories);
        productVO.setId(product.getId());
        return productVO;
    }

    @Override
    public ProductVO update(UpdateProductDTO updateProductDTO, Principal principal) {
        checkPrincipal(principal);
        if (updateProductDTO.getProductCategories().size() < 1) {
            throw new NotImplementedException("th??m t???i thi??u 1 danh m???c");
        }
        if (updateProductDTO.getProductDetails().size() < 1) {
            throw new NotImplementedException("th??m t???i thi??u 1 th??ng tin");
        }
        if (updateProductDTO.getProductColors().size() < 1) {
            throw new NotImplementedException("th??m t???i thi??u 1 m??u");
        }
        Product product = productDAO.getOneProductById(updateProductDTO.getId());
        if (product == null) {
            throw new NotImplementedException("Kh??ng t???n t???i s???n ph???m n??y, vui l??ng th??m m???i");
        }
        BeanUtils.copyProperties(updateProductDTO, product);
        if (updateProductDTO.getStatus().equals("Kh??ng kinh doanh") || updateProductDTO.getStatus().equals("??ang b??n") || updateProductDTO.getStatus().equals("H???t h??ng") || updateProductDTO.getStatus().equals("H??ng s???p v???")) {
            product.setStatus(updateProductDTO.getStatus());
        } else {
            product.setStatus(product.getStatus());
        }
        product = productDAO.save(product);

        productCategoryService.updateProductCategory(product.getId(), updateProductDTO.getProductCategories());
        productDetailService.updateProductDetail(product.getId(), updateProductDTO.getProductDetails());
        productColorService.updateProductColor(product.getId(), updateProductDTO.getProductColors());
        return getById(product.getId());
    }

    public void checkPrincipal(Principal principal) {
        if (principal == null) {
            throw new NotImplementedException("Ch??a ????ng nh???p");
        }
        if (!(checkRole.isHavePermition(principal.getName(), "Director") || checkRole.isHavePermition(principal.getName(), "Staff"))) {
            throw new NotImplementedException("User n??y kh??ng c?? quy???n");
        }
    }

    private boolean changeStatus(String status, Integer id) {
        try {
            Product product = productDAO.getById(id);
            if (product == null) {
                throw new NotFoundException("api.error.API-003");
            }
            product.setStatus(status);
            productDAO.save(product);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}