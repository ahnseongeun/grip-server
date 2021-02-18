package com.app.grip.src.product;

import com.app.grip.config.BaseException;
import com.app.grip.src.product.models.*;
import com.app.grip.src.review.ReviewProvider;
import com.app.grip.src.review.models.Review;
import com.app.grip.src.store.models.GetStoresRes;
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
    private final ReviewProvider reviewProvider;

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);

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
     * 전체 상품 조회
     * @return List<GetProductsRes>
     * @throws BaseException
     * @Auther shine
     */
    public List<GetProductsRes> retrieveProducts() throws BaseException {
        List<Product> productList;

        try {
            productList = productRepository.findAllByOrderByIdDesc();
        } catch (Exception exception) {
            throw new BaseException(FAILED_TO_GET_PRODUCTCATEGORY);
        }

        return productList.stream().map(product -> {
            return new GetProductsRes(product.getId(), product.getName(),
                    product.getContent(), product.getPrice(), product.getPictureURL(),
                    new GetStoresRes(
                            product.getStore().getId(),
                            product.getStore().getName(),
                            product.getStore().getIntroduction(),
                            product.getStore().getPictureURL(),
                            product.getStore().getUser().getNo(),
                            dateFormat.format(product.getStore().getCreateDate()),
                            dateFormat.format(product.getStore().getUpdateDate()),
                            product.getStore().getStatus()),
                    product.getProductCategory().getName(),
                    dateFormat.format(product.getCreateDate()),
                    product.getStatus());
        }).collect(Collectors.toList());
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
            product = productRepository.findByIdOrderByReviewList(productId).get(0);
        } catch (Exception exception) {
            throw new BaseException(FAILED_TO_GET_PRODUCT);
        }

        return product;
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
     * Product -> GetProductRes 변경
     * @Param Product product
     * @return GetProductRes
     * @Auther shine
     */
    public GetProductRes retrieveGetProductRes(Product product) {
        int i = 0;
        double sum = 0;
        for (Review review : product.getReviewList()) {
            sum += product.getReviewList().get(i).getStar();
            i++;
        }

        return new GetProductRes(
                product.getId(),
                product.getName(),
                product.getContent(),
                product.getPrice(),
                product.getPictureURL(),
                product.getStore().getName(),
                product.getProductCategory().getName(),
                dateFormat.format(product.getCreateDate()),
                product.getStatus(),
                new Double(sum / product.getReviewList().size()),
                product.getReviewList().stream().map(review -> {
                    return reviewProvider.retrieveGetReviewRes(review);
                }).collect(Collectors.toList()));
    }

}