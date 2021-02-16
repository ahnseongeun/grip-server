package com.app.grip.src.product;

import com.app.grip.config.BaseException;
import com.app.grip.config.BaseResponse;
import com.app.grip.src.product.models.*;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

import static com.app.grip.config.BaseResponseStatus.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class ProductController {
    private final ProductService productService;
    private final ProductProvider productProvider;

    /**
     * 전체 상품카테고리 조회 API
     * [GET] /api/admin/products-category
     * @return BaseResponse<List<GetProductsCategoryRes>
     * @Auther shine
     */
    @ApiOperation(value = "전체 상품카테고리 조회(관리자용)", notes = "전체 상품카테고리 조회")
    @ResponseBody
    @GetMapping("/admin/products-category")
    public BaseResponse<List<GetProductCategoryRes>> getProductsCategory() {
        try {
            List<GetProductCategoryRes> productCategoryList = productProvider.retrieveProductsCategory();
            return new BaseResponse<>(SUCCESS, productCategoryList);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    /**
     * 상품카테고리 조회 API
     * [GET] /api/products-category/:categoryName
     * @RequestBody String categoryName
     * @return BaseResponse<GetProductCategoryRes>
     * @Auther shine
     */
    @ApiOperation(value = "상품카테고리 조회", notes = "상품카테고리 조회")
    @ResponseBody
    @GetMapping("/products-category")
    public BaseResponse<GetProductCategoryRes> getProductCategory(@RequestBody(required = false) HashMap<String, String> parameter) {
        String categoryName = parameter.get("categoryName");

        if(categoryName == null || categoryName.length() == 0) {
            return new BaseResponse<>(EMPTY_CATEGORY);
        }

        try {
            ProductCategory productCategory = productProvider.retrieveProductCategoryByNameAndStatusY(categoryName);
            return new BaseResponse<>(SUCCESS, productProvider.retrieveGetProductCategoryRes(productCategory));
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }


    /**
     * 전체 상품 조회 API
     * [GET] /api/admin/products
     * @return BaseResponse<List<GetProductRes>>
     * @Auther shine
     */
    @ApiOperation(value = "전체 상품 조회(관리자용)", notes = "전체 상품 조회")
    @ResponseBody
    @GetMapping("/admin/products")
    public BaseResponse<List<GetProductsRes>> getProducts() {
        try {
            List<GetProductsRes> productList = productProvider.retrieveProducts();
            return new BaseResponse<>(SUCCESS, productList);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    /**
     * 상품 상세조회 API
     * [GET] /api/products/:productId
     * @PathVariable Long productId
     * @return BaseResponse<GetProductRes>
     * @Auther shine
     */
    @ApiOperation(value = "상품 상세조회", notes = "상품 상세조회")
    @ResponseBody
    @GetMapping("/products/{productId}")
    public BaseResponse<GetProductRes> getProduct(@PathVariable Long productId) {
        try {
            Product productList = productProvider.retrieveProductsById(productId);
            return new BaseResponse<>(SUCCESS, productProvider.retrieveGetProductRes(productList));
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
        if(parameters.getProductCategoryName() == null || parameters.getProductCategoryName().length() == 0) {
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
     * 판매 종료된 상품 처리 API
     * [PATCH] /api/products/:productId/completed
     * @PathVariable Long productId
     * @return BaseResponse<Void>
     * @Auther shine
     */
    @ApiOperation(value = "판매 종료된 상품 처리", notes = "판매 종료된 상품 처리")
    @ResponseBody
    @PatchMapping("/products/{productId}/completed")
    public BaseResponse<Void> patchProductCompleted(@PathVariable Long productId) {
        try {
            productService.patchProductCompleted(productId);
            return new BaseResponse<>(SUCCESS);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

}