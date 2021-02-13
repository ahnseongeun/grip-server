package com.app.grip.src.product;

import com.app.grip.config.BaseException;
import com.app.grip.src.product.models.*;
import com.app.grip.src.store.StoreRepository;
import com.app.grip.src.store.models.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.app.grip.config.BaseResponseStatus.*;

@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductCategoryRepository productCategoryRepository;
    private final StoreRepository storeRepository;

    /**
     * 상품 생성
     * @param PostProductReq parameters, Long storeId
     * @return PostProductRes
     * @throws BaseException
     * @Auther shine
     */
    public PostProductRes createProduct(PostProductReq parameters, Long storeId) throws BaseException {
        Store store = storeRepository.findByStatusAndId("Y", storeId).get(0);
        ProductCategory productCategory = productCategoryRepository.findByStatusAndId("Y", parameters.getProductCategoryId()).get(0);

        Product newProduct = Product.builder()
                .name(parameters.getName())
                .content(parameters.getContent())
                .price(parameters.getPrice())
                .pictureURL(parameters.getPictureURL())
                .store(store)
                .productCategory(productCategory)
                .build();

        try {
            newProduct = productRepository.save(newProduct);
        } catch (Exception exception) {
            throw new BaseException(FAILED_TO_POST_PRODUCT);
        }

        return new PostProductRes(newProduct.getId(), newProduct.getName(),
                newProduct.getContent(), newProduct.getPrice(), newProduct.getPictureURL(),
                newProduct.getStore().getId(), newProduct.getProductCategory().getId());
    }

    /**
     * 상품카테고리 생성
     * @param PostProductsCategoryReq parameters
     * @return PostProductsCategoryRes
     * @throws BaseException
     * @Auther shine
     */
    public PostProductCategoryRes createProductCategory(PostProductCategoryReq parameters) throws BaseException {
        ProductCategory newProductCategory = ProductCategory.builder()
                .name(parameters.getName())
                .build();

        try {
            newProductCategory = productCategoryRepository.save(newProductCategory);
        } catch (Exception exception) {
            throw new BaseException(FAILED_TO_POST_PRODUCTCATEGORY);
        }

        return new PostProductCategoryRes(newProductCategory.getId(), newProductCategory.getName());

    }

}