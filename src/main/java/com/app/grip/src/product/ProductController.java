package com.app.grip.src.product;

import com.app.grip.config.BaseException;
import com.app.grip.config.BaseResponse;
import com.app.grip.src.product.models.*;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.app.grip.config.BaseResponseStatus.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class ProductController {
    private final ProductService productService;
    private final ProductProvider productProvider;

    /**
     * 상품 등록 API
     * [POST] /api/products/:storeId
     * @RequestBody PostProductReq parameters
     * @PathVariable Long storeId
     * @return BaseResponse<PostProductRes>
     * @Auther shine
     */
    @ApiOperation(value = "상품 등록", notes = "상품 등록")
    @ResponseBody
    @PostMapping("/products/{storeId}")
    public BaseResponse<PostProductRes> postProduct(@RequestBody(required = false) PostProductReq parameters, @PathVariable Long storeId) {
        if(parameters.getName() == null || parameters.getName().length() == 0) {
            return new BaseResponse<>(EMPTY_NAME);
        }
        if(parameters.getContent() == null || parameters.getContent().length() == 0) {
            return new BaseResponse<>(EMPTY_CONTENT);
        }
        if(parameters.getPrice() == null) {
            return new BaseResponse<>(EMPTY_PRICE);
        }
        if(parameters.getName() == null || parameters.getName().length() == 0) {
            return new BaseResponse<>(EMPTY_CATEGORY);
        }

        try {
            PostProductRes product = productService.createProduct(parameters, storeId);
            return new BaseResponse<>(SUCCESS, product);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }



    /**
     * 상품카테고리 등록 API
     * [POST] /api/products-category
     * @RequestBody
     * @return
     * @Auther shine
     */
    @ApiOperation(value = "상품카테고리 등록", notes = "상품카테고리 등록")
    @ResponseBody
    @PostMapping("/products-category")
    public BaseResponse<PostProductCategoryRes> postProductCategory(@RequestBody(required = false) PostProductCategoryReq parameters) {
        if(parameters.getName() == null || parameters.getName().length() == 0) {
            return new BaseResponse<>(EMPTY_NAME);
        }

        try {
            PostProductCategoryRes productCategory = productService.createProductCategory(parameters);
            return new BaseResponse<>(SUCCESS, productCategory);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }
}
