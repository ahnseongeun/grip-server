package com.app.grip.src.product;

import com.app.grip.config.BaseException;
import com.app.grip.src.product.models.*;
import com.app.grip.src.review.models.GetReviewRes;
import com.app.grip.src.review.models.PictureRes;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import static com.app.grip.config.BaseResponseStatus.*;

@RequiredArgsConstructor
@Service
public class ProductProvider {
    private final ProductCategoryRepository productCategoryRepository;
    private final ProductRepository productRepository;

    SimpleDateFormat outputDateFormat = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초", Locale.KOREA);

    /**
     * 전체 카테고리 조회
     * @return List<GetProductCategoryRes>
     * @throws BaseException
     * @Auther shine
     */
    public List<GetProductCategoryRes> retrieveProductsCategory() throws BaseException {
        List<ProductCategory> productCategoryList;

        try {
            productCategoryList = productCategoryRepository.findAll(Sort.by("id"));
        } catch (Exception exception) {
            throw new BaseException(FAILED_TO_GET_PRODUCTCATEGORY);
        }

        return productCategoryList.stream().map(productCategory -> {
            return new GetProductCategoryRes(productCategory.getName(), productCategory.getStatus());
        }).collect(Collectors.toList());
    }

    /**
     * 카테고리 조회
     * @Param String categoryName
     * @return ProductCategory
     * @throws BaseException
     * @Auther shine
     */
    public ProductCategory retrieveProductCategoryByNameAndStatusY(String categoryName) throws BaseException {
        ProductCategory productCategory;

        try {
            productCategory = productCategoryRepository.findByStatusAndName("Y", categoryName).get(0);
        } catch (Exception exception) {
            throw new BaseException(FAILED_TO_GET_PRODUCTCATEGORY);
        }

        return productCategory;
    }

    /**
     * ProductCategory -> GetProductCategoryRes 변경
     * @Param ProductCategory productCategory
     * @return GetProductCategoryRes
     * @Auther shine
     */
    public GetProductCategoryRes retrieveGetProductCategoryRes(ProductCategory productCategory) {
        return new GetProductCategoryRes(productCategory.getName(), productCategory.getStatus());
    }


    /**
     * 전체 상품 조회
     * @return List<GetProductRes>
     * @throws BaseException
     * @Auther shine
     */
    public List<GetProductsRes> retrieveProducts() throws BaseException {
        List<Product> productList;

        try {
            productList = productRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        } catch (Exception exception) {
            throw new BaseException(FAILED_TO_GET_PRODUCTCATEGORY);
        }

        return productList.stream().map(product -> {
            return new GetProductsRes(product.getId(), product.getName(),
                    product.getContent(), product.getPrice(), product.getPictureURL(),
                    product.getStore().getName(), product.getProductCategory().getName(),
                    outputDateFormat.format(product.getCreateDate()), product.getStatus());
        }).collect(Collectors.toList());
    }

    /**
     * 상품 상세조회
     * @return
     * @throws
     * @Auther shine
     */
    public GetProductRes retrieveProduct(Long productId) throws BaseException {
        Product product;

        try {
            product = productRepository.findById(productId).orElseThrow(() -> new BaseException(FAILED_TO_GET_PRODUCT));
        } catch (Exception exception) {
            throw new BaseException(FAILED_TO_GET_PRODUCTCATEGORY);
        }

        return null;
    }

    /**
     * 상품 조회
     * @Param Long productId
     * @return Product
     * @throws BaseException
     * @Auther shine
     */
    public Product retrieveProductsById(Long productId) throws BaseException {
        Product product;

        try {
            product = productRepository.findById(productId).orElseThrow(() -> new BaseException(NOT_FOUND_PRODUCT));
        } catch (Exception exception) {
            throw new BaseException(FAILED_TO_GET_PRODUCT);
        }

        return product;
    }


    
}