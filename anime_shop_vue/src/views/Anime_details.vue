<template>
    <div class="anime_details_main">
        <!--头部导航-->
        <van-nav-bar
                :title="this.animeDetails.title"
                left-text="返回"
                left-arrow
                @click-left="onClickLeft"
        />

        <!--轮播图-->
        <van-swipe class="my-swipe" :autoplay="3000" indicator-color="white">
            <van-swipe-item v-for="(image,index) in this.animeDetails.thumb" :key="index">
                <img :src="image.url" style="height: 200px;width: 100%"/>
            </van-swipe-item>
        </van-swipe>

        <!--商品详情-->
        <div class="anime_details_content">
            <p style="font-weight: bold">{{this.animeDetails.desc}}</p>
            <p>品牌类型:{{this.animeDetails.animeCategory.category_name}}</p>
            <p>产品参数:</p>
            <p v-for="tag in this.animeDetails.tag">{{tag.name}}</p>
            <p>
              <span style="position: absolute;left: 10px;bottom:120px;color: darkgrey;">
                <van-icon name="eye-o" size="18" >浏览量:&nbsp;&nbsp;{{this.count}}</van-icon>
              </span>
              <span style="color: red;position: absolute;right: 20px;bottom:120px;font-size: 24px">售价:￥{{this.price}}.00</span>
            </p>
        </div>

        <!--商品导航-->
        <van-goods-action style="margin-bottom: 60px">
            <van-goods-action-icon icon="cart-o" text="购物车" :badge="this.badge" @click="goCart"/>
            <van-goods-action-button type="warning" text="加入购物车" @click="onClickInsert"/>
            <van-goods-action-button type="danger" text="立即购买" @click="onClickBuy"/>
        </van-goods-action>

        <!--商品规格-->
        <van-sku
                v-model="show"
                :sku="sku"
                :goods="goods"
                :hide-stock="sku.hide_stock"
                @buy-clicked="onCartClicked"
        >
            <template #sku-actions="props">
                <div class="van-sku-actions">
                    <van-button
                            square
                            size="large"
                            type="danger"
                            @click="props.skuEventBus.$emit('sku:buy')"
                    >{{goods_button}}
                    </van-button>
                </div>
            </template>
        </van-sku>
    </div>
</template>

<script>
    import {Toast} from "vant";

    export default {
        data() {
            return {
                animeDetails: "",
                price: "",
                count:"",
                show: false,
                goods: "",
                sku: "",
                goods_button: "",
                badge: 0,
                user: "",
                animeId: this.$route.query.animeId,
            };
        },
        created() {
            const _this = this;
            let user1 = JSON.parse(sessionStorage.getItem("user"));
            this.user = user1;
            axios.get("http://localhost:8040/Goods/findById/" + this.animeId).then(function (resp) {
                    _this.animeDetails = resp.data.data;
                    _this.price = parseInt(resp.data.data.price);
                    _this.count = resp.data.data.count;
                });

            axios.get("http://localhost:8040/Specs/findByAnimeId/" + this.animeId).then(function (resp) {
                    _this.sku = resp.data.data.skuVO;
                    _this.goods = resp.data.data.goodsVO;
                });

            axios.get("http://localhost:8040/Cart/countUserCart/" + this.user.userId).then(function (resp) {
                        _this.badge = resp.data.data;
                });
        },
        methods: {
            onClickLeft() {
                history.go(-1);
            },
            onClickInsert() {
                this.show = true;
                this.goods_button = "加入购物车";
            },
            onClickBuy() {
                this.show = true;
                this.goods_button = "立即下单";
            },
            onCartClicked(item) {
                const _this = this;
                if (this.user == null) {
                    Toast.fail("小主，还未登录呢!")
                    this.$router.push("login");
                } else if (this.goods_button === "加入购物车") {
                    let user_cart = {
                        userId: this.user.userId,
                        animeId: this.animeId,
                        specsId: item.selectedSkuComb.s1,
                        quantity: item.selectedNum
                    };
                    axios.post("http://localhost:8040/Cart/saveCart", user_cart).then(function (resp) {
                        if (resp.data.code === 0) {
                            history.go(0);
                        } else {
                            Toast(resp.data.data);
                        }
                    });
                } else {
                    if (this.user.userAddressId === 0) {
                        Toast.fail("对不起,您收获地址还未设置，请添加收货地址！")
                        this.$router.push("/user_addressList");
                    } else {
                        let order = {
                            price: item.selectedSkuComb.price,
                            userId: this.user.userId,
                            addressId: this.user.userAddressId,
                            animeId: this.animeId,
                            specsId: item.selectedSkuComb.s1,
                            quantity: item.selectedNum
                        }
                        axios.post("http://localhost:8040/Order/promptlyOrder", order).then(function (resp) {
                            if (resp.data.code === 0) {
                                Toast.success("下单成功,请支付！")
                                axios.get("http://localhost:8040/Order/getExpire/" + resp.data.data);
                                _this.$router.push({
                                    path: '/anime_order',
                                    query: {
                                        orderId: resp.data.data
                                    }
                                });
                            } else {
                                Toast.fail("下单失败,请重新尝试！");
                            }
                        })
                    }
                }
            },
            goCart() {
                this.$router.push("/user_cart");
            }
        }
    };
</script>

<style scoped>
    .anime_details_main {
        width: 100%;
        margin: 0;
        padding: 0;
    }

    .anime_details_content {
        width: 100%;
        margin: 20px auto;
    }
</style>