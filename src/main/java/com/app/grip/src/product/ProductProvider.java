package com.app.grip.src.product;

import com.app.grip.config.BaseException;
import com.app.grip.src.product.models.GetProductCategoryRes;
import com.app.grip.src.product.models.ProductCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.app.grip.config.BaseResponseStatus.*;

@RequiredArgsConstructor
@Service
public class ProductProvider {
    private final ProductCategoryRepository productCategoryRepository;

    /**
     * 카테고리 전체 조회
     * @return List<GetProductCategoryRes>
     * @throws BaseException
     * @Auther shine
     */
    public List<GetProductCategoryRes> retrieveProductsCategory() throws BaseException {
        List<ProductCategory> productCategoryList;

        try {
            productCategoryList = productCategoryRepository.findByStatusOrderByCreateDateDesc("Y");
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
    public ProductCategory retrieveProductCategoryByName(String categoryName) throws BaseException {
        ProductCategory productCategory;

        try {
            productCategory = productCategoryRepository.findByStatusAndName("Y", categoryName).get(0);
        } catch (Exception exception) {
            throw new BaseException(FAILED_TO_GET_PRODUCTCATEGORY);
        }

        return productCategory;
    }
    
}
