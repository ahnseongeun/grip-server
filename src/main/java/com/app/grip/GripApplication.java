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
import com.app.grip.src.video.videoParticipant.VideoParticipantRepository;
import com.app.grip.src.video.videoParticipant.models.VideoParticipant;
import com.app.grip.src.videoCategory.VideoCategory;
import com.app.grip.src.videoCategory.VideoCategoryRepository;
import com.app.grip.src.watchMyVideo.WatchMyVideoRepository;
import com.app.grip.src.watchMyVideo.models.WatchMyVideo;
import com.app.grip.utils.GetDateTime;
import com.app.grip.utils.GetFileMetaData;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
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
    private final WatchMyVideoRepository watchMyVideoRepository;
    private final VideoParticipantRepository videoParticipantRepository;
    private final GetDateTime getDateTime;
    private final HashMap<Long, Integer> likeRepository;
    private final HashMap<String, Integer> StreamingRepository;
    private final HashMap<String, Integer> StreamingSizeRepository;

    public GripApplication(VideoCategoryRepository videoCategoryRepository,
                           AdvertisementRepository advertisementRepository,
                           UserRepository userRepository, VideoRepository videoRepository,
                           StoreRepository storeRepository,
                           ProductCategoryRepository productCategoryRepository,
                           ProductRepository productRepository, ReviewRepository reviewRepository,
                           CouponRepository couponRepository,
                           WatchMyVideoRepository watchMyVideoRepository,
                           VideoParticipantRepository videoParticipantRepository,
                           GetDateTime getDateTime,
                           @Qualifier("streaming") HashMap<String, Integer> streamingRepository,
                           @Qualifier("streamingSize") HashMap<String, Integer> streamingSizeRepository,
                           GetFileMetaData getFileMetaData,
                           HashMap<Long, Integer> likeRepository) {
        this.videoCategoryRepository = videoCategoryRepository;
        this.advertisementRepository = advertisementRepository;
        this.userRepository = userRepository;
        this.videoRepository = videoRepository;
        this.storeRepository = storeRepository;
        this.productCategoryRepository = productCategoryRepository;
        this.productRepository = productRepository;
        this.reviewRepository = reviewRepository;
        this.couponRepository = couponRepository;
        this.watchMyVideoRepository = watchMyVideoRepository;
        this.videoParticipantRepository = videoParticipantRepository;
        this.getDateTime = getDateTime;
        this.StreamingRepository = streamingRepository;
        this.StreamingSizeRepository = streamingSizeRepository;
        this.likeRepository = likeRepository;
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
                ,"https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/user1.png",
                "010-1111-1111", "1995-03-10", "test1@naver.com", "M",
                "N", 1, "Y", "111111");

        User user2 = new User("안철수","철수입니다"
                        ,"https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/user1.png",
                        "010-1111-1112", "1995-03-12", "test2@naver.com", "M",
                        "N", 1, "Y", "111112");

        User user3 = new User("홍철수","철수입니다"
                ,"https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/user1.png",
                "010-1111-1113", "1995-03-13", "test3@naver.com", "W",
                "N", 1, "Y", "111113");

        User grapher1 = new User("이철수","철수입니다"
                ,"https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/user1.png",
                "010-1111-1114", "1995-03-14", "test4@naver.com", "M",
                "N", 50, "Y", "111114");

        User grapher2 = new User("이철박","철수입니다"
                ,"https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/user1.png",
                "010-1111-1115", "1995-03-15", "test5@naver.com", "M",
                "N", 50, "Y", "111115");

        User grapher3 = new User("박철이","철수입니다"
                ,"https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/user1.png",
                "010-1111-1116", "1995-03-16", "test6@naver.com", "M",
                "N", 50, "Y", "111116");

        User grapher4 = new User("김수철","철수입니다"
                ,"https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/user.png",
                "010-1111-1117", "1995-03-17", "test7@naver.com", "M",
                "N", 50, "Y", "111117");

        User admin = new User("박철수","철수입니다"
                ,"https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/user1.png",
                "010-1111-1118", "1995-03-18", "test8@naver.com", "M",
                "N", 100, "Y", "111118");

        User userFace1 = new User("김도윤","도윤입니다"
                ,"https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/user1.png",
                "010-1111-1100", "1990-03-10", "test1@gmail.com", "M",
                "F", 1, "Y", "222221");

        User userFace2 = new User("윤서윤","서윤입니다"
                ,"https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/user1.png",
                "010-1111-1122", "1996-03-13", "test2@gmail.com", "W",
                "F", 1, "Y", "222222");

        User grapherFace1 = new User("김하준","하준입니다"
                ,"https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/user1.png",
                "010-1111-1133", "1993-03-12", "test3@gmail.com", "M",
                "F", 50, "Y", "222223");

        User grapherFace2 = new User("정수아","수아입니다"
                ,"https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/user1.png",
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
        ProductCategory productCategory1 = new ProductCategory("패션의류");
        ProductCategory productCategory2 = new ProductCategory("패션잡화");
        ProductCategory productCategory3 = new ProductCategory("주얼리");
        ProductCategory productCategory4 = new ProductCategory("뷰티");
        ProductCategory productCategory5 = new ProductCategory("디지털/가전");
        ProductCategory productCategory6 = new ProductCategory("가구/인테리어");
        ProductCategory productCategory7 = new ProductCategory("출산/유아동");
        ProductCategory productCategory8 = new ProductCategory("식품");

        final List<ProductCategory> productCategoryList = Arrays.asList(productCategory1,
                productCategory2, productCategory3, productCategory4, productCategory5, productCategory6,
                productCategory7, productCategory8);
        List<ProductCategory> savedProductCategory = (List<ProductCategory>) productCategoryRepository.saveAll(productCategoryList);


        /**
         * 상품 더미 데이터 삽입
         */
        Product product1 = new Product("블링 블링 금목걸이 팔아요~", "멀리서봐도 아름답고 반짝 반짝 빛나는 24K 금목걸이 팝니다~", 200000,
                "https://spng.subpng.com/20180906/vqw/kisspng-earring-jewellery-necklace-kalyan-jewellers-gold-jewellery-transparent-png-arts-5b91ec7d692563.1314281415362899174307.jpg");
        product1.setStore(store1);
        product1.setProductCategory(productCategory3);

        Product product2 = new Product("핫이슈~ 땡땡이 원피스~", "요즘 핫한 그 원피스! 품질 좋고 싸게 팝니다~", 88000,
                "https://img1.daumcdn.net/thumb/R720x0/?fname=http%3A%2F%2Ft1.daumcdn.net%2Fliveboard%2Ftag%2Fa9d8ddfca41d4139ad073089a4d6958e.jpg");
        product2.setStore(store2);
        product2.setProductCategory(productCategory1);

        Product product3 = new Product("품절 임박! 핸드메이드 주스~", "직접 만든 핸드메이드 주스~ 품절되기 전에 빨리와서 구매해주세요!", 50000,
                "http://bompack.com/web/product/extra/small/201809/dd9b17fb93e8ee27b0875fef7768fb0e.jpg");
        product3.setStore(store3);
        product3.setProductCategory(productCategory8);

        Product product4 = new Product("신제품! 노트북 팝니다", "출시 기념으로 싸게 팝니다~ 많이들 사러 와주세용~~", 1000000,
                "https://byline.network/wp-content/uploads/2020/05/ASUS-ExpertBook-B9-side.jpg");
        product4.setStore(store4);
        product4.setProductCategory(productCategory5);

        Product product5 = new Product("올겨울 인싸템! 양털 팝니다", "요즘 인싸라면 없어서는 안되는 아이템! 양털 겉옷! 싸게 판매할때 장만하세요~", 59999,
                "https://i.ytimg.com/vi/kUmHOpg_UZY/hqdefault.jpg");
        product5.setStore(store5);
        product5.setProductCategory(productCategory2);

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
        reviewPictureList = Arrays.asList(new ReviewPicture("https://photo.newsen.com/mphoto/2013/12/17/201312170013599310_1.jpg"));
        review2.setUser(user2);
        review2.setProduct(product2);
        review2.setReviewPictureList(reviewPictureList);

        Review review3 = new Review(5, "식품같은 경우는 신선도가 생명인데 빠른 배송으로 하루만에 배송을 받았습니다! 유기농 재료로 직접 만들어서 먹는 순간 건강해지는 느낌! 정말 강추합니다.");
        reviewPictureList = Arrays.asList(new ReviewPicture("https://dimg.donga.com/wps/NEWS/IMAGE/2015/03/07/69995569.1.jpg"),
                new ReviewPicture("http://image.dongascience.com/Photo/2017/03/14909336653856.jpg"));
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
        review5.setProduct(product4);

        Review review6 = new Review(2, "배송은 빨랐으나 생각보다 상품이 별로네요. 비추합니다.");
        review6.setUser(userFace1);
        review6.setProduct(product1);

        Review review7 = new Review(5, "너무 너무 이쁜 원피스입니다! 입는순간..!! 반해버렸습니다.");
        reviewPictureList = Arrays.asList(new ReviewPicture("http://m.baddiary.com/web/product/medium/20191219/1108dc41b462954c52256d74d631e3ec.jpg"));
        review7.setUser(user1);
        review7.setProduct(product2);
        review7.setReviewPictureList(reviewPictureList);

        Review review8 = new Review(4, "배송이 살짝 아쉽긴 했으나...!! 딱맞고 예쁜 옷입니다! 굿굿굿!");
        reviewPictureList = Arrays.asList(new ReviewPicture("http://m.soosulhwa.com/web/product/medium/202010/91c02d835d14baae719ac23bd5d37bd2.jpg"));
        review8.setUser(user3);
        review8.setProduct(product2);
        review8.setReviewPictureList(reviewPictureList);

        Review review9 = new Review(5, "역시! 후기가 좋은 이유가 있습니다!! 남친과 데이트때 입을려고 샀는데 너무 너무 이쁜 원피스입니다! 남친도 옷을 이쁘다고 얼마나 칭찬하던지! 추천합니다!");
        reviewPictureList = Arrays.asList(new ReviewPicture("http://img4.tmon.kr/cdn3/deals/2020/12/19/2032893610/front_dfe4f_t0a8u.jpg"));
        review9.setUser(userFace1);
        review9.setProduct(product2);
        review9.setReviewPictureList(reviewPictureList);

        Review review10 = new Review(5, "QNA도 너무 너무 친절했고 배송도 너무 빨랐습니다. 마음에 듭니다!");
        reviewPictureList = Arrays.asList(new ReviewPicture("https://thumb.mt.co.kr/06/2018/07/2018072009471344251_1.jpg"));
        review10.setUser(userFace2);
        review10.setProduct(product2);
        review10.setReviewPictureList(reviewPictureList);

        Review review11 = new Review(5, "요즘 핫한 양털 후리스! 이거 입고 갔더니 친구들이 어디서 샀냐고 합니다 ㅋㅋㅋ 완전 핫해졌어요 추천 추천");
        reviewPictureList = Arrays.asList(new ReviewPicture("https://pbs.twimg.com/media/EfDgo5TUcAYaJFk.jpg"));
        review11.setUser(user1);
        review11.setProduct(product5);
        review11.setReviewPictureList(reviewPictureList);

        Review review12 = new Review(4, "하태 하태! 학교에서 하태! 싸고 저렴하게 구입한것 같습니다 강추!");
        reviewPictureList = Arrays.asList(new ReviewPicture("http://image.auction.co.kr/itemimage/1a/f2/06/1af206c286.jpg"));
        review12.setUser(userFace1);
        review12.setProduct(product5);
        review12.setReviewPictureList(reviewPictureList);

        Review review13 = new Review(5, "요즘 학교에 후리스 없는 친구가 있나요? 그만큼 핫한 제품을 이렇게 저렴하게!! 심지어 완전 트랜디한 제품을! 만족합니다!!");
        reviewPictureList = Arrays.asList(new ReviewPicture("http://m.modaloco.com/web/product/big/20191204/205ea4aa65f247bd104e4e9f5a3f920a.jpg"));
        review13.setUser(user3);
        review13.setProduct(product5);
        review13.setReviewPictureList(reviewPictureList);

        Review review14 = new Review(5, "추운날씨에 멋까지 챙길 수 있는 효자템입니다!! 배송도 빠르고 문의도 되게 친절하고 빠르게 답변해주셨어요!");
        reviewPictureList = Arrays.asList(new ReviewPicture("http://m.ajoonholic.com/web/product/big/201911/506543dbe51b03566d1579d5d47d1270.jpg"));
        review14.setUser(user2);
        review14.setProduct(product5);
        review14.setReviewPictureList(reviewPictureList);

        final List<Review> reviewList = Arrays.asList(review1,review2,review3,review4,review5,
                review6,review7,review8,review9,review10,review11,review12,review13,review14);
        List<Review> savedReview = (List<Review>) reviewRepository.saveAll(reviewList);


        /**
         * 쿠폰 더미 데이터 삽입
         */
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);
        Date effectiveDate = new Date();
        try {
            effectiveDate = dateFormat.parse("2021-02-28 23:59:59");
        } catch (ParseException exception) {
            exception.printStackTrace();
        }
        Coupon coupon1 = new Coupon("회원가입 감사 할인 쿠폰", 1000, 10000, effectiveDate);
        coupon1.setUser(user1);
        Coupon coupon2 = new Coupon("2월 할인 쿠폰", 2000, 20000, effectiveDate);
        coupon2.setUser(user2);
        Coupon coupon3 = new Coupon("2월 할인쿠폰", 3000, 30000, effectiveDate);
        coupon3.setUser(user3);
        Coupon coupon4 = new Coupon("선착순 100명 할인 쿠폰", 4000, 40000, effectiveDate);
        coupon4.setUser(userFace1);
        Coupon coupon5 = new Coupon("첫 구매 감사 할인 쿠폰", 5000, 50000, effectiveDate);
        coupon5.setUser(userFace2);
        Coupon coupon6 = new Coupon("발렌타인 기념 할인 쿠폰", 6000, 60000, effectiveDate);
        coupon6.setUser(user1);
        Coupon coupon7 = new Coupon("이벤트 적용 할인 쿠폰", 7000, 70000, effectiveDate);
        coupon7.setUser(user2);
        Coupon coupon8 = new Coupon("5명 추천 감사 쿠폰", 8000, 80000, effectiveDate);
        coupon8.setUser(user3);
        Coupon coupon9 = new Coupon("10번 구매 감사 쿠폰", 9000, 90000, effectiveDate);
        coupon9.setUser(userFace1);
        Coupon coupon10 = new Coupon("VIP 전용 쿠폰", 10000, 1000000, effectiveDate);
        coupon10.setUser(userFace2);

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
                        new Video("곽스타","N", getDateTime.getCustomDataTime("plus",4L),"N","https://subdomain.ahnbat.kr/video/video1/videotest1.m3u8"
                                ,"https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/video1.png",0,videoCategory1,grapher1),
                        new Video("네이플 본사 공식몰","N",getDateTime.getCustomDataTime("plus",4L),"N","https://subdomain.ahnbat.kr/video/video2/videotest2.m3u8"
                                ,"https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/video3.png",0,videoCategory1,grapher2),
                        new Video("고랭고랭","N",getDateTime.getCustomDataTime("plus",4L),"N","https://subdomain.ahnbat.kr/video/video1/videotest1.m3u8"
                                ,"https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/video2.png",0,videoCategory1,grapher3),

                        //전체 라이브
                        new Video("2부시작5초전","Y",getDateTime.getCustomDataTime("minus",2L),"N","https://subdomain.ahnbat.kr/video/video1/videotest1.m3u8"
                                ,"https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/video1.png",0,videoCategory3,grapher1),
                        new Video("오버티 55-88까지 맨투맨","Y",getDateTime.getCustomDataTime("minus",2L),"N","https://subdomain.ahnbat.kr/video/video2/videotest2.m3u8"
                                ,"https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/video2.png",0,videoCategory3,grapher2),
                        new Video("짧은라방, 구두가방","Y",getDateTime.getCustomDataTime("minus",2L),"N","https://subdomain.ahnbat.kr/video/video1/videotest1.m3u8"
                                ,"https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/test.png",0,videoCategory3,grapher3),

                        //소호몰 언니
                        new Video("째즈언니","Y",getDateTime.getCustomDataTime("minus",2L),"N","https://subdomain.ahnbat.kr/video/video2/videotest2.m3u8"
                                ,"https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/video1.png",0,videoCategory4,grapher1),
                        new Video("신사%세일해용, 10만원이상 선물","Y",getDateTime.getCustomDataTime("minus",2L),"N","https://subdomain.ahnbat.kr/video/video1/videotest1.m3u8"
                                ,"https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/test.png",0,videoCategory4,grapher2),
                        new Video("반지할인, 새해복_언니들","Y",getDateTime.getCustomDataTime("minus",2L),"N","https://subdomain.ahnbat.kr/video/video1/videotest1.m3u8"
                                ,"https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/video3.png",0,videoCategory4,grapher4),

                        //스타일링
                       new Video("2부시작5초전","Y",getDateTime.getCustomDataTime("minus",2L),"N","https://subdomain.ahnbat.kr/video/video2/videotest2.m3u8"
                                ,"https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/video2.png",0,videoCategory5,grapher2),
                        new Video("짧은라방, 구두가방","Y",getDateTime.getCustomDataTime("minus",2L),"N","https://subdomain.ahnbat.kr/video/video1/videotest1.m3u8"
                                ,"https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/test.png",0,videoCategory5,grapher3),
                        new Video("올해는 나 장가갈꺼래...","Y",getDateTime.getCustomDataTime("minus",2L),"N","https://subdomain.ahnbat.kr/video/video1/videotest1.m3u8"
                                ,"https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/video1.png",0,videoCategory5,grapher4),

                        //신인
                       new Video("오늘도 수고했어~","Y",getDateTime.getCustomDataTime("minus",2L),"N","https://subdomain.ahnbat.kr/video/video2/videotest2.m3u8"
                                ,"https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/video3.png",0,videoCategory6,grapher1),
                        new Video("다시 소통 방송 들어와요ㅠㅠ","Y",getDateTime.getCustomDataTime("minus",2L),"N","https://subdomain.ahnbat.kr/video/video1/videotest1.m3u8"
                                ,"https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/video2.png",0,videoCategory6,grapher3),
                        new Video("소통과 판매 논녀와용...","Y",getDateTime.getCustomDataTime("minus",2L),"N","https://subdomain.ahnbat.kr/video/video2/videotest2.m3u8"
                                ,"https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/test.png",0,videoCategory6,grapher4),

                        //뷰티 꿀팁
                        new Video("복주머니!!!","Y",getDateTime.getCustomDataTime("minus",2L),"N","https://subdomain.ahnbat.kr/video/video2/videotest2.m3u8"
                                ,"https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/test.png",0,videoCategory7,grapher4),
                        new Video("셀로니아 줄기세포 샴푸!!","Y",getDateTime.getCustomDataTime("minus",2L),"N","https://subdomain.ahnbat.kr/video/video1/videotest1.m3u8"
                                ,"https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/video3.png",0,videoCategory7,grapher3),
                        new Video("피부에 양보하세요","Y",getDateTime.getCustomDataTime("minus",2L),"N","https://subdomain.ahnbat.kr/video/video2/videotest2.m3u8"
                                ,"https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/video2.png",0,videoCategory7,grapher2),

                        //먹방쿡방
                       new Video("크로플+누텔라","Y",getDateTime.getCustomDataTime("minus",2L),"N","https://subdomain.ahnbat.kr/video/video2/videotest2.m3u8"
                                ,"https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/video2.png",0,videoCategory8,grapher1),
                        new Video("쭈꾸미 품절!!!일보직전!!","Y",getDateTime.getCustomDataTime("minus",2L),"N","https://subdomain.ahnbat.kr/video/video1/videotest1.m3u8"
                                ,"https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/video1.png",0,videoCategory8,grapher4),
                        new Video("피자나라 치킨공주","Y",getDateTime.getCustomDataTime("minus",2L),"N","https://subdomain.ahnbat.kr/video/video2/videotest2.m3u8"
                                ,"https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/video3.png",0,videoCategory8,grapher2),

                        //알쓸신템
                        new Video("사라 사라 사라 양말좀 사라~~","Y",getDateTime.getCustomDataTime("minus",2L),"N","https://subdomain.ahnbat.kr/video/video2/videotest2.m3u8"
                                ,"https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/video2.png",0,videoCategory9,grapher4),
                        new Video("이걸 안들어와??","Y",getDateTime.getCustomDataTime("minus",2L),"N","https://subdomain.ahnbat.kr/video/video1/videotest1.m3u8"
                                ,"https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/test.png",0,videoCategory9,grapher2),
                        new Video("롱패딩 딱대!!","Y",getDateTime.getCustomDataTime("minus",2L),"N","https://subdomain.ahnbat.kr/video/video2/videotest2.m3u8"
                                ,"https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/video1.png",0,videoCategory9,grapher3)
                        );


        List<Video> savedVideo = (List<Video>) videoRepository.saveAll(videoList);


        // 비디오 참여자 등록
        final List<VideoParticipant> videoParticipantList =
                Arrays.asList(
                        new VideoParticipant(user1,videoList.get(4)),
                        new VideoParticipant(user1,videoList.get(5)),
                        new VideoParticipant(user1,videoList.get(6)),
                        new VideoParticipant(user1,videoList.get(7)),
                        new VideoParticipant(user2,videoList.get(8)),
                        new VideoParticipant(user2,videoList.get(9)),
                        new VideoParticipant(user2,videoList.get(10)),
                        new VideoParticipant(user2,videoList.get(11)),
                        new VideoParticipant(user3,videoList.get(12)),
                        new VideoParticipant(user3,videoList.get(13)),
                        new VideoParticipant(user3,videoList.get(14)),
                        new VideoParticipant(user3,videoList.get(15))
                );
        videoParticipantRepository.saveAll(videoParticipantList);

        // 내가 본 비디오 삽입
        final List<WatchMyVideo> watchMyVideoList =
                Arrays.asList(
                        new WatchMyVideo(user1,videoList.get(4)),
                        new WatchMyVideo(user1,videoList.get(5)),
                        new WatchMyVideo(user1,videoList.get(6)),
                        new WatchMyVideo(user1,videoList.get(7)),
                        new WatchMyVideo(user2,videoList.get(8)),
                        new WatchMyVideo(user2,videoList.get(9)),
                        new WatchMyVideo(user2,videoList.get(10)),
                        new WatchMyVideo(user2,videoList.get(11)),
                        new WatchMyVideo(user3,videoList.get(12)),
                        new WatchMyVideo(user3,videoList.get(13)),
                        new WatchMyVideo(user3,videoList.get(14)),
                        new WatchMyVideo(user3,videoList.get(15))
                );

        watchMyVideoRepository.saveAll(watchMyVideoList);




        // 좋아요 삽입
        for(int i = 4;i <= 24; i++){
            likeRepository.put((long)i,i*10);
        }

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
