package com.app.grip.src.product;

import com.app.grip.config.BaseException;
import com.app.grip.src.product.models.*;
import com.app.grip.src.store.StoreProvider;
import com.app.grip.src.store.models.Store;
import com.app.grip.src.user.UserRepository;
import com.app.grip.src.user.models.User;
import com.app.grip.utils.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.app.grip.config.BaseResponseStatus.*;

@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductCategoryRepository productCategoryRepository;
    private final ProductProvider productProvider;
    private final StoreProvider storeProvider;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    /**
     * 상품카테고리 생성
     * @param PostProductCategoryReq parameters
     * @return PostProductCategoryRes
     * @throws BaseException
     * @Auther shine
     */
    public PostProductCategoryRes createProductCategory(PostProductCategoryReq parameters) throws BaseException {
        User user = userRepository.findById(jwtService.getUserNo()).orElseThrow(() -> new BaseException(FAILED_TO_GET_USER));

        if(user.getRole() != 100) {
            throw new BaseException(DO_NOT_AUTH_USER);
        }

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

    /**
     * 상품 생성
     * @param PostProductReq parameters, Long storeId
     * @return PostProductRes
     * @throws BaseException
     * @Auther shine
     */
    public PostProductRes createProduct(PostProductReq parameters, Long storeId) throws BaseException {
        Store store = storeProvider.retrieveStoreById(storeId);
        ProductCategory productCategory = productProvider.retrieveProductCategoryByNameAndStatusY(parameters.getProductCategoryName());

        if(store.getUser().getNo() != jwtService.getUserNo()) {
            throw new BaseException(DO_NOT_AUTH_USER);
        }

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
     * 판매 종료된 상품 처리
     * @param Long productId
     * @return void
     * @throws BaseException
     * @Auther shine
     */
    public void patchProductCompleted(Long productId) throws BaseException {
        Product product = productProvider.retrieveProductsById(productId);

        if(product.getStatus().equals("Y")) {
            if(product.getStore().getUser().getNo() != jwtService.getUserNo()) {
                throw new BaseException(DO_NOT_AUTH_USER);
            }
            product.setStatus("C");
        } else if(product.getStatus().equals("C")) {
            throw new BaseException(ALREADY_COMPLETED);
        } else if(product.getStatus().equals("N")) {
            throw new BaseException(ALREADY_DELETE_PRODUCT);
        }

        try {
            productRepository.save(product);
        } catch (Exception exception) {
            throw new BaseException(FAILED_TO_PATCH_PRODUCT);
        }
    }

}