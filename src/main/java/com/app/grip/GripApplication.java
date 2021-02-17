package com.app.grip;

import com.app.grip.src.advertisement.AdvertisementRepository;
import com.app.grip.src.advertisement.models.Advertisement;
import com.app.grip.src.coupon.CouponRepository;
import com.app.grip.src.coupon.models.Coupon;
import com.app.grip.src.product.ProductCategoryRepository;
import com.app.grip.src.product.ProductRepository;
import com.app.grip.src.product.models.Product;
import com.app.grip.src.product.models.ProductCategory;
import com.app.grip.src.review.ReviewRepository;
import com.app.grip.src.review.models.Review;
import com.app.grip.src.review.models.ReviewPicture;
import com.app.grip.src.store.StoreRepository;
import com.app.grip.src.store.models.Store;
import com.app.grip.src.user.UserRepository;
import com.app.grip.src.user.models.User;
import com.app.grip.src.video.VideoRepository;
import com.app.grip.src.video.models.Video;
import com.app.grip.src.videoCategory.VideoCategory;
import com.app.grip.src.videoCategory.VideoCategoryRepository;
import com.app.grip.utils.GetFileMetaData;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@EnableJpaAuditing
@SpringBootApplication
public class GripApplication implements CommandLineRunner {

    private final VideoCategoryRepository videoCategoryRepository;
    private final AdvertisementRepository advertisementRepository;
    private final UserRepository userRepository;
    private final VideoRepository videoRepository;
    private final StoreRepository storeRepository;
    private final ProductCategoryRepository productCategoryRepository;
    private final ProductRepository productRepository;
    private final ReviewRepository reviewRepository;
    private final CouponRepository couponRepository;
    private final HashMap<String, Integer> StreamingRepository;
    private final HashMap<String, Integer> StreamingSizeRepository;

    public GripApplication(VideoCategoryRepository videoCategoryRepository,
                           AdvertisementRepository advertisementRepository,
                           UserRepository userRepository, VideoRepository videoRepository,
                           StoreRepository storeRepository,
                           ProductCategoryRepository productCategoryRepository,
                           ProductRepository productRepository, ReviewRepository reviewRepository,
                           CouponRepository couponRepository,
                           @Qualifier("streaming") HashMap<String, Integer> streamingRepository,
                           @Qualifier("streamingSize")HashMap<String, Integer> streamingSizeRepository,
                           GetFileMetaData getFileMetaData) {
        this.videoCategoryRepository = videoCategoryRepository;
        this.advertisementRepository = advertisementRepository;
        this.userRepository = userRepository;
        this.videoRepository = videoRepository;
        this.storeRepository = storeRepository;
        this.productCategoryRepository = productCategoryRepository;
        this.productRepository = productRepository;
        this.reviewRepository = reviewRepository;
        this.couponRepository = couponRepository;
        this.StreamingRepository = streamingRepository;
        this.StreamingSizeRepository = streamingSizeRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(GripApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        /**
         * 유저 더미 데이터 삽입
         */
        User user1 = new User("김철수","철수입니다"
                ,"https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/user.png",
                "010-1111-1111", "1995-03-10", "test1@naver.com", "M",
                "N", 1, "Y", "111111");

        User user2 = new User("안철수","철수입니다"
                        ,"https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/user.png",
                        "010-1111-1112", "1995-03-12", "test2@naver.com", "M",
                        "N", 1, "Y", "111112");

        User user3 = new User("홍철수","철수입니다"
                ,"https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/user.png",
                "010-1111-1113", "1995-03-13", "test3@naver.com", "W",
                "N", 1, "Y", "111113");

        User grapher1 = new User("이철수","철수입니다"
                ,"https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/user.png",
                "010-1111-1114", "1995-03-14", "test4@naver.com", "M",
                "N", 50, "Y", "111114");

        User grapher2 = new User("이철박","철수입니다"
                ,"https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/user.png",
                "010-1111-1115", "1995-03-15", "test5@naver.com", "M",
                "N", 50, "Y", "111115");

        User grapher3 = new User("박철이","철수입니다"
                ,"https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/user.png",
                "010-1111-1116", "1995-03-16", "test6@naver.com", "M",
                "N", 50, "Y", "111116");

        User grapher4 = new User("김수철","철수입니다"
                ,"https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/user.png",
                "010-1111-1117", "1995-03-17", "test7@naver.com", "M",
                "N", 50, "Y", "111117");

        User admin = new User("박철수","철수입니다"
                ,"https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/user.png",
                "010-1111-1118", "1995-03-18", "test8@naver.com", "M",
                "N", 100, "Y", "111118");

        User userFace1 = new User("김도윤","도윤입니다"
                ,"https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/user.png",
                "010-1111-1100", "1990-03-10", "test1@gmail.com", "M",
                "F", 1, "Y", "222221");

        User userFace2 = new User("윤서윤","서윤입니다"
                ,"https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/user.png",
                "010-1111-1122", "1996-03-13", "test2@gmail.com", "W",
                "F", 1, "Y", "222222");

        User grapherFace1 = new User("김하준","하준입니다"
                ,"https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/user.png",
                "010-1111-1133", "1993-03-12", "test3@gmail.com", "M",
                "F", 50, "Y", "222223");

        User grapherFace2 = new User("정수아","수아입니다"
                ,"https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/user.png",
                "010-1111-1144", "1997-03-12", "test4@gmail.com", "W",
                "F", 50, "Y", "222224");


        final List<User> userList =
                Arrays.asList(user1,user2,user3,grapher1,grapher2,grapher3,grapher4,admin,
                        userFace1,userFace2,grapherFace1,grapherFace2);

        List<User> savedUser = (List<User>) userRepository.saveAll(userList);


        /**
         * 상점 더미 데이터 삽입
         */
        Store store1 = new Store("블링 블링 액세서리",
                "아름다운 액세서리들이 모여 있습니다.\n구경하러 오세요~",
                "https://cdn.icon-icons.com/icons2/1993/PNG/512/ecommerce_home_market_mart_shop_shopping_store_icon_123207.png");
        store1.setUser(grapher1);

        Store store2 = new Store("머리부터 발끝까지 핫이슈~",
                "핫하고 요즘 인기있는 옷과 아이템들이 구비되어 있습니다~\n구경하러 오세요~",
                "https://cdn.icon-icons.com/icons2/1993/PNG/512/ecommerce_home_market_mart_shop_shopping_store_icon_123207.png");
        store2.setUser(grapher2);

        Store store3 = new Store("핸드메이드 주스",
                "직접 재배한 유기농 채소와 과일로 만든 핸드메이드 주스!\n구경하러 오세요~",
                "https://cdn.icon-icons.com/icons2/1993/PNG/512/ecommerce_home_market_mart_shop_shopping_store_icon_123207.png");
        store3.setUser(grapher3);

        Store store4 = new Store("최신 전자기기 여기 다있다!",
                "세상에 있는 모든 최신 전자기기 모여있는 곳!\n세상에서 가장 핫한 기기! 구경하러 오세요~",
                "https://cdn.icon-icons.com/icons2/1993/PNG/512/ecommerce_home_market_mart_shop_shopping_store_icon_123207.png");
        store4.setUser(grapher4);

        Store store5 = new Store("하태 하태",
                "세상 모든 핫한 제품만 모아 모아 팝니다!\n구경하러 오세요~",
                "https://cdn.icon-icons.com/icons2/1993/PNG/512/ecommerce_home_market_mart_shop_shopping_store_icon_123207.png");
        store5.setUser(grapherFace1);


        final List<Store> storeList = Arrays.asList(store1,store2,store3,store4,store5);
        List<Store> savedStore = (List<Store>) storeRepository.saveAll(storeList);


        /**
         * 상품 카테고리 더미 데이터 삽입
         */
        ProductCategory productCategory1 = new ProductCategory("패션");
        ProductCategory productCategory2 = new ProductCategory("화장품/미용");
        ProductCategory productCategory3 = new ProductCategory("디지털/가전");
        ProductCategory productCategory4 = new ProductCategory("가구/인테리어");
        ProductCategory productCategory5 = new ProductCategory("출산/육아");
        ProductCategory productCategory6 = new ProductCategory("식품");
        ProductCategory productCategory7 = new ProductCategory("스포츠/레저");
        ProductCategory productCategory8 = new ProductCategory("생활/건강");
        ProductCategory productCategory9 = new ProductCategory("여가/생활편의");

        final List<ProductCategory> productCategoryList = Arrays.asList(productCategory1,
                productCategory2, productCategory3, productCategory4, productCategory5, productCategory6,
                productCategory7, productCategory8, productCategory9);
        List<ProductCategory> savedProductCategory = (List<ProductCategory>) productCategoryRepository.saveAll(productCategoryList);


        /**
         * 상품 더미 데이터 삽입
         */
        Product product1 = new Product("블링 블링 금목걸이 팔아요~", "멀리서봐도 아름답고 반짝 반짝 빛나는 24K 금목걸이 팝니다~", 200000,
                "https://spng.subpng.com/20180906/vqw/kisspng-earring-jewellery-necklace-kalyan-jewellers-gold-jewellery-transparent-png-arts-5b91ec7d692563.1314281415362899174307.jpg");
        product1.setStore(store1);
        product1.setProductCategory(productCategory1);

        Product product2 = new Product("핫이슈~ 땡땡이 원피스~", "요즘 핫한 그 원피스! 품질 좋고 싸게 팝니다~", 88000,
                "https://img1.daumcdn.net/thumb/R720x0/?fname=http%3A%2F%2Ft1.daumcdn.net%2Fliveboard%2Ftag%2Fa9d8ddfca41d4139ad073089a4d6958e.jpg");
        product2.setStore(store2);
        product2.setProductCategory(productCategory1);

        Product product3 = new Product("품절 임박! 핸드메이드 주스~", "직접 만든 핸드메이드 주스~ 품절되기 전에 빨리와서 구매해주세요!", 50000,
                "http://bompack.com/web/product/extra/small/201809/dd9b17fb93e8ee27b0875fef7768fb0e.jpg");
        product3.setStore(store3);
        product3.setProductCategory(productCategory6);

        Product product4 = new Product("신제품! 노트북 팝니다", "출시 기념으로 싸게 팝니다~ 많이들 사러 와주세용~~", 1000000,
                "https://byline.network/wp-content/uploads/2020/05/ASUS-ExpertBook-B9-side.jpg");
        product4.setStore(store4);
        product4.setProductCategory(productCategory3);

        Product product5 = new Product("올겨울 인싸템! 양털 팝니다", "요즘 인싸라면 없어서는 안되는 아이템! 양털 겉옷! 싸게 판매할때 장만하세요~", 59999,
                "https://i.ytimg.com/vi/kUmHOpg_UZY/hqdefault.jpg");
        product5.setStore(store5);
        product5.setProductCategory(productCategory1);

        final List<Product> productList = Arrays.asList(product1,product2,product3,product4,product5);
        List<Product> savedProduct = (List<Product>) productRepository.saveAll(productList);


        /**
         * 리뷰 더미 데이터 삽입
         */
        List<ReviewPicture> reviewPictureList;
        Review review1 = new Review(5, "빠른 배송과 실제로 받아보니 정말 정말 예쁘네요!");
        reviewPictureList = Arrays.asList(
                new ReviewPicture("https://previews.123rf.com/images/muralinathypr/muralinathypr1209/muralinathypr120900097/15123481-%EC%9D%B8%EB%8F%84%EC%9D%98-%EC%A0%84%ED%86%B5%EC%A0%81%EC%9D%B8-%EA%B8%88-%EB%AA%A9%EA%B1%B8%EC%9D%B4.jpg"),
                new ReviewPicture("https://image.winudf.com/v2/image1/bGl0dGxlYXBwYXMuZ29sZGpld2VscnlkZXNpZ25fc2NyZWVuXzBfMTU3MzQ2ODAxMF8wMTA/screen-3.jpg?fakeurl=1&type=.jpg"));
        review1.setUser(user1);
        review1.setProduct(product1);
        review1.setReviewPictureList(reviewPictureList);

        Review review2 = new Review(4, "제가 원하던 원피스였으나 배송이 느린점이 아쉽습니다! 배송 부분만 감안하면 가격도 저렴하고 품질도 훌륭합니다!");
        reviewPictureList = Arrays.asList(new ReviewPicture("http://m.momoring.kr/web/product/big/201905/f19e479bc7827024257bb3723bea4611.gif"));
        review2.setUser(user2);
        review2.setProduct(product2);
        review2.setReviewPictureList(reviewPictureList);

        Review review3 = new Review(5, "식품같은 경우는 신선도가 생명인데 빠른 배송으로 하루만에 배송을 받았습니다! 유기농 재료로 직접 만들어서 먹는 순간 건강해지는 느낌! 정말 강추합니다.");
        reviewPictureList = Arrays.asList(new ReviewPicture("https://dimg.donga.com/wps/NEWS/IMAGE/2015/03/07/69995569.1.jpg"),
                new ReviewPicture("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTEosFKYRqlMvldH3Vkj2yIxKhYnBFMJ7vgGQ&usqp=CAU"));
        review3.setUser(user3);
        review3.setProduct(product3);
        review3.setReviewPictureList(reviewPictureList);

        Review review4 = new Review(5, "전에 쓰던 노트북과 차원이 다릅니다! 속도가 완전.... 이 노트북을 쓰면 다른 노트북을 못쓸 정도로 매우 쾌적하고 빠른 속도에 감탄했습니다!");
        reviewPictureList = Arrays.asList(new ReviewPicture("http://img.newspim.com/news/2019/11/20/1911201521166450.jpg"));
        review4.setUser(userFace1);
        review4.setProduct(product4);
        review4.setReviewPictureList(reviewPictureList);

        Review review5 = new Review(1, "배송도 느리고 생각보다 가격대비 품질이 그저 그러네요.");
        review5.setUser(userFace2);
        review5.setProduct(product5);

        Review review6 = new Review(2, "배송은 빨랐으나 생각보다 상품이 별로네요. 비추합니다.");
        review6.setUser(userFace1);
        review6.setProduct(product1);

        final List<Review> reviewList = Arrays.asList(review1,review2,review3,review4,review5,review6);
        List<Review> savedReview = (List<Review>) reviewRepository.saveAll(reviewList);


        /**
         * 쿠폰 더미 데이터 삽입
         */
        SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);
        Date effectiveDate = new Date();
        try {
            effectiveDate = inputDateFormat.parse("2021-02-28 23:59:59");
        } catch (ParseException exception) {
            exception.printStackTrace();
        }
        Coupon coupon1 = new Coupon("회원가입 감사 할인 쿠폰", 1000, 10000, effectiveDate);
        coupon1.setUser(grapher1);
        Coupon coupon2 = new Coupon("2월 할인 쿠폰", 2000, 20000, effectiveDate);
        coupon2.setUser(grapher2);
        Coupon coupon3 = new Coupon("2월 할인쿠폰", 3000, 30000, effectiveDate);
        coupon3.setUser(grapher3);
        Coupon coupon4 = new Coupon("선착순 100명 할인 쿠폰", 4000, 40000, effectiveDate);
        coupon4.setUser(grapher4);
        Coupon coupon5 = new Coupon("첫 구매 감사 할인 쿠폰", 5000, 50000, effectiveDate);
        coupon5.setUser(grapher1);
        Coupon coupon6 = new Coupon("발렌타인 기념 할인 쿠폰", 6000, 60000, effectiveDate);
        coupon6.setUser(grapher2);
        Coupon coupon7 = new Coupon("이벤트 적용 할인 쿠폰", 7000, 70000, effectiveDate);
        coupon7.setUser(grapher3);
        Coupon coupon8 = new Coupon("5명 추천 감사 쿠폰", 8000, 80000, effectiveDate);
        coupon8.setUser(grapher4);
        Coupon coupon9 = new Coupon("10번 구매 감사 쿠폰", 9000, 90000, effectiveDate);
        coupon9.setUser(grapher1);
        Coupon coupon10 = new Coupon("VIP 전용 쿠폰", 10000, 1000000, effectiveDate);
        coupon10.setUser(grapher2);

        final List<Coupon> couponList = Arrays.asList(coupon1,coupon2,coupon3,coupon4,coupon5,coupon6,coupon7,coupon8,coupon9,coupon10);
        List<Coupon> savedCoupon = (List<Coupon>) couponRepository.saveAll(couponList);


        /**
         * 영상 카테고리 더미 데이터 삽입
         */
        VideoCategory videoCategory1 = new VideoCategory("라이브예고","https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/pizza.jpg");
        VideoCategory videoCategory2 = new VideoCategory("내팔로잉","https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/pizza.jpg");
        VideoCategory videoCategory3 = new VideoCategory("전체LIVE","https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/pizza.jpg");
        VideoCategory videoCategory4 = new VideoCategory("소호몰언니","https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/pizza.jpg");
        VideoCategory videoCategory5 = new VideoCategory("스타일링","https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/pizza.jpg");
        VideoCategory videoCategory6 = new VideoCategory("신인그리퍼","https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/pizza.jpg");
        VideoCategory videoCategory7 = new VideoCategory("뷰티꿀팁","https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/pizza.jpg");
        VideoCategory videoCategory8 = new VideoCategory("먹방쿡방","https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/pizza.jpg");
        VideoCategory videoCategory9 = new VideoCategory("알쓸신템","https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/pizza.jpg");

            final List<VideoCategory> videoCategoryList =
                Arrays.asList(
                        videoCategory1,videoCategory2,videoCategory3,
                        videoCategory4,videoCategory5,videoCategory6,
                        videoCategory7,videoCategory8,videoCategory9);


        List<VideoCategory> savedVideoCategory = (List<VideoCategory>) videoCategoryRepository.saveAll(videoCategoryList);


        /**
         * 광고 더미 데이터 삽입
         */
        final List<Advertisement> advertisementList =
                Arrays.asList(
                        new Advertisement("광고1번","https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/pizza.jpg",1L),
                        new Advertisement("광고2번","https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/pizza.jpg",1L),
                        new Advertisement("광고3번","https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/pizza.jpg",1L),
                        new Advertisement("광고4번","https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/pizza.jpg",1L),
                        new Advertisement("광고5번","https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/pizza.jpg",1L),
                        new Advertisement("광고6번","https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/pizza.jpg",1L),
                        new Advertisement("광고7번","https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/pizza.jpg",1L),
                        new Advertisement("광고8번","https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/pizza.jpg",1L));


        List<Advertisement> savedAdvertisement = (List<Advertisement>) advertisementRepository.saveAll(advertisementList);

        /**
         * 영상 더미 데이터 삽입
         */
        //yyyy년 MM월 dd일 HH시 mm분 ss초
        final List<Video> videoList =
                Arrays.asList(
                        //라이브 예고
                        new Video("곽스타","N","2021년 02월 12일 20시 00분","N","https://subdomain.ahnbat.kr/video/video1/videotest1.m3u8"
                                ,"https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/video1.png",0,videoCategory1,grapher1),
                        new Video("네이플 본사 공식몰","N","2021년 02월 12일 20시 00분","N","https://subdomain.ahnbat.kr/video/video2/videotest2.m3u8"
                                ,"https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/video3.png",0,videoCategory1,grapher2),
                        new Video("고랭고랭","N","2021년 02월 13일 20시 00분","N","https://subdomain.ahnbat.kr/video/video1/videotest1.m3u8"
                                ,"https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/video2.png",0,videoCategory1,grapher3),

                        //전체 라이브
                        new Video("2부시작5초전","Y","2021년 02월 12일 17시 00분","N","https://subdomain.ahnbat.kr/video/video1/videotest1.m3u8"
                                ,"https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/video1.png",0,videoCategory3,grapher1),
                        new Video("오버티 55-88까지 맨투맨","Y","2021년 02월 12일 15시 00분","N","https://subdomain.ahnbat.kr/video/video2/videotest2.m3u8"
                                ,"https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/video2.png",0,videoCategory3,grapher2),
                        new Video("짧은라방, 구두가방","Y","2021년 02월 12일 16시 00분","N","https://subdomain.ahnbat.kr/video/video1/videotest1.m3u8"
                                ,"https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/test.png",0,videoCategory3,grapher3),

                        //소호몰 언니
                        new Video("째즈언니","Y","2021년 02월 12일 16시 00분","N","https://subdomain.ahnbat.kr/video/video2/videotest2.m3u8"
                                ,"https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/video1.png",0,videoCategory4,grapher1),
                        new Video("신사%세일해용, 10만원이상 선물","Y","2021년 02월 12일 16시 00분","N","https://subdomain.ahnbat.kr/video/video1/videotest1.m3u8"
                                ,"https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/test.png",0,videoCategory4,grapher2),
                        new Video("반지할인, 새해복_언니들","Y","2021년 02월 12일 16시 00분","N","https://subdomain.ahnbat.kr/video/video1/videotest1.m3u8"
                                ,"https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/video3.png",0,videoCategory4,grapher4),

                        //스타일링
                       new Video("2부시작5초전","Y","2021년 02월 12일 16시 00분","N","https://subdomain.ahnbat.kr/video/video2/videotest2.m3u8"
                                ,"https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/video2.png",0,videoCategory5,grapher2),
                        new Video("짧은라방, 구두가방","Y","2021년 02월 12일 16시 00분","N","https://subdomain.ahnbat.kr/video/video1/videotest1.m3u8"
                                ,"https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/test.png",0,videoCategory5,grapher3),
                        new Video("올해는 나 장가갈꺼래...","Y","2021년 02월 12일 16시 00분","N","https://subdomain.ahnbat.kr/video/video1/videotest1.m3u8"
                                ,"https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/video1.png",0,videoCategory5,grapher4),

                        //신인
                       new Video("오늘도 수고했어~","Y","2021년 02월 12일 16시 00분","N","https://subdomain.ahnbat.kr/video/video2/videotest2.m3u8"
                                ,"https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/video3.png",0,videoCategory6,grapher1),
                        new Video("다시 소통 방송 들어와요ㅠㅠ","Y","2021년 02월 12일 16시 00분","N","https://subdomain.ahnbat.kr/video/video1/videotest1.m3u8"
                                ,"https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/video2.png",0,videoCategory6,grapher3),
                        new Video("소통과 판매 논녀와용...","Y","2021년 02월 12일 16시 00분","N","https://subdomain.ahnbat.kr/video/video2/videotest2.m3u8"
                                ,"https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/test.png",0,videoCategory6,grapher4),

                        //뷰티 꿀팁
                        new Video("복주머니!!!","Y","2021년 02월 12일 16시 00분","N","https://subdomain.ahnbat.kr/video/video2/videotest2.m3u8"
                                ,"https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/test.png",0,videoCategory7,grapher4),
                        new Video("셀로니아 줄기세포 샴푸!!","Y","2021년 02월 12일 16시 00분","N","https://subdomain.ahnbat.kr/video/video1/videotest1.m3u8"
                                ,"https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/video3.png",0,videoCategory7,grapher3),
                        new Video("피부에 양보하세요","Y","2021년 02월 12일 16시 00분","N","https://subdomain.ahnbat.kr/video/video2/videotest2.m3u8"
                                ,"https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/video2.png",0,videoCategory7,grapher2),

                        //먹방쿡방
                       new Video("크로플+누텔라","Y","2021년 02월 12일 16시 00분","N","https://subdomain.ahnbat.kr/video/video2/videotest2.m3u8"
                                ,"https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/video2.png",0,videoCategory8,grapher1),
                        new Video("쭈꾸미 품절!!!일보직전!!","Y","2021년 02월 12일 16시 00분","N","https://subdomain.ahnbat.kr/video/video1/videotest1.m3u8"
                                ,"https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/video1.png",0,videoCategory8,grapher4),
                        new Video("피자나라 치킨공주","Y","2021년 02월 12일 16시 00분","N","https://subdomain.ahnbat.kr/video/video2/videotest2.m3u8"
                                ,"https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/video3.png",0,videoCategory8,grapher2),

                        //알쓸신템
                        new Video("사라 사라 사라 양말좀 사라~~","Y","2021년 02월 12일 16시 00분","N","https://subdomain.ahnbat.kr/video/video2/videotest2.m3u8"
                                ,"https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/video2.png",0,videoCategory9,grapher4),
                        new Video("이걸 안들어와??","Y","2021년 02월 12일 16시 00분","N","https://subdomain.ahnbat.kr/video/video1/videotest1.m3u8"
                                ,"https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/test.png",0,videoCategory9,grapher2),
                        new Video("롱패딩 딱대!!","Y","2021년 02월 12일 16시 00분","N","https://subdomain.ahnbat.kr/video/video2/videotest2.m3u8"
                                ,"https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/video1.png",0,videoCategory9,grapher3)
                        );



        List<Video> savedVideo = (List<Video>) videoRepository.saveAll(videoList);

        String url1 = "https://subdomain.ahnbat.kr/video/video1/videotest1.m3u8";
        //String url1 = "C:\\home1\\ffmpeg-4.3.2-2021-02-02-full_build\\ffmpeg-4.3.2-2021-02-02-full_build\\bin\\video1\\videotest1.m3u8";
        StreamingRepository.put(url1,0);
        StreamingSizeRepository.put(url1,GetFileMetaData.fileMetaData(url1));
        System.out.println(GetFileMetaData.fileMetaData(url1));

        String url2 = "https://subdomain.ahnbat.kr/video/video2/videotest2.m3u8";
        //String url2 = "C:\\home1\\ffmpeg-4.3.2-2021-02-02-full_build\\ffmpeg-4.3.2-2021-02-02-full_build\\bin\\video2\\videotest2.m3u8";
        StreamingRepository.put(url2,0);
        StreamingSizeRepository.put(url2,GetFileMetaData.fileMetaData(url2));
        System.out.println(GetFileMetaData.fileMetaData(url2));

    }

    //https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/pizza.jpg 사진 더미데이터







}
