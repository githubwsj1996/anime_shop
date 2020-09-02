<template>
    <div class="cart_main">
        <van-cell :title="this.title" class="cart_top"/>

        <div class="user_cart_list">
            <!--操作-->
            <div class="cart_manage">
                <van-checkbox v-model="checkAll" @click="onCheckAll">全选</van-checkbox>
                <van-button :disabled=deleteBtn class="cart_manage_btu" type="danger" @click="onDelete">删除</van-button>
            </div>

            <!--商品列表-->
            <div class="cart_center" v-for="(data,index) in this.vanCardValues">
                <van-checkbox v-model="data.check"
                              @change="onCheckbox(data,index)"
                              ref="checkbox"/>

                <van-card :price="data.price"
                          :title="data.title"
                          :desc="data.desc"
                          :thumb="data.thumb"
                />
                <van-stepper
                        style="position: absolute;right: 30px;display: block"
                        v-model="data.quantity"
                        theme="round"
                        disable-input
                        min="1"
                        :max=data.maxQuantity
                        @plus="onPlus(data)"
                        @minus="onMinus(data)"
                />
            </div>
        </div>
        <div class="user_cart_bottom"></div>

        <!--结算-->
        <van-submit-bar class="cart_submit" :disabled=submitBtn :price=this.totalPrice*100 button-text="结算"
                        @submit="onSubmit"/>
    </div>
</template>

<script>
    import {Toast} from 'vant';
    import {Dialog} from "vant";

    export default {
        data() {
            return {
                user: "",
                title: "",
                vanCardValues: "",
                cartValues: [],
                checkAll: false,
                deleteBtn: true,
                submitBtn: true,
                totalPrice: 0,
            };
        },
        created() {
            this.user = JSON.parse(sessionStorage.getItem("user"));
            if(this.user==null){
                Toast.fail("小主，还未登录呢！");
                this.$router.push("/login");
            }
            this.title = this.user.username + "的购物车";
            const _this = this;
            axios.get("http://localhost:8040/Cart/findAllByUserId/" + this.user.userId).then(function (resp) {
                _this.vanCardValues = resp.data.data;
            });

        },
        methods: {
            onCheckAll() {
                if (this.checkAll === false) {
                    this.totalPrice = 0
                    this.cartValues = []
                }
                let prices = null;
                this.vanCardValues = this.vanCardValues.map(e => {
                    e.check = this.checkAll;
                    if (e.check === false) {
                        prices = e.quantity * parseInt(e.price)
                        this.totalPrice += prices
                    } else if (e.check !== this.checkAll) {
                        prices = e.quantity * parseInt(e.price);
                        this.totalPrice += prices;
                    }
                    return e;
                });
            },
            onCheckbox(data, index) {
                let price = null;
                let a = 0;
                let cartValue = {}
                if (data.check === true) {
                    this.vanCardValues.forEach(e => {
                        if (e.check === true) {
                            price = data.quantity * parseInt(data.price);
                            a += 1;
                            if (a === this.vanCardValues.length) {
                                this.checkAll = true
                            }
                        }
                    })
                    this.deleteBtn = false;
                    this.submitBtn = false;
                    this.totalPrice += price;
                    cartValue = {
                        cartId: data.cartId,
                        price: parseInt(data.price) * 100,
                        quantity: data.quantity,
                        addressId: this.user.userAddressId
                    }
                    this.cartValues.push(cartValue);
                } else {
                    this.vanCardValues.forEach(e => {
                        if (e.check === false) {
                            price = data.quantity * parseInt(data.price);
                        }
                    })
                    this.totalPrice -= price;
                    this.deleteBtn = true;
                    this.submitBtn = true;
                    this.checkAll = false;
                    this.cartValues.splice(index, 1);
                }
                console.log(this.cartValues)
            },

            onPlus(item) {
                axios.put("http://localhost:8040/Cart/addQuantity/" + item.cartId).then(function (resp) {
                    if (resp.data.code === 0) {
                        Toast.success(resp.data.data)
                        history.go(0);
                    } else {
                        Toast.fail(resp.data.data)
                        history.go(0);
                    }
                })
            },
            onMinus(item) {
                axios.put("http://localhost:8040/Cart/reduceQuantity/" + item.cartId).then(function (resp) {
                    if (resp.data.code === 0) {
                        Toast.success(resp.data.data)
                        history.go(0);
                    } else {
                        Toast.fail(resp.data.data)
                        history.go(0);
                    }
                })
            },
            onDelete() {
                Dialog.confirm({
                    title: '确认删除？',
                    message: '是否要删除商品？',
                }).then(() => {
                    axios.post("http://localhost:8040/Cart/deleteUserCart", this.cartValues).then(function (resp) {
                        if (resp.data.code === 0) {
                            Toast.success(resp.data.data);
                            history.go(0);
                        }
                    })
                }).catch(() => {
                    Toast.fail("取消删除！");
                    history.go(0);
                })

            },
            onSubmit() {
                const _this = this;
                if (this.user.userAddressId === 0) {
                    Toast.fail("对不起,您收获地址还未设置，请添加收货地址！")
                    this.$router.push("/user_addressList");
                } else {
                    axios.post("http://localhost:8040/Order/saveCartOrders", this.cartValues).then(function (resp) {
                        if (resp.data.code === 0) {
                            Toast.success("订单创建成功！");
                            axios.get("http://localhost:8040/Order/getExpire/" + resp.data.data);
                            _this.$router.push({
                                path: "/anime_order",
                                query: {
                                    cartOrderId: resp.data.data
                                }
                            })
                        }
                    })
                }
            }
        }
    }
</script>

<style scoped>
    .cart_main {
        width: 100%;
        margin: 0;
        padding: 0;
    }

    .cart_top {
        width: 100%;
        height: 100px;
        background-color: #fa742b;
        font-size: 24px;
        text-align: center;
        line-height: 80px;
        color: white;
    }

    .user_cart_list {
        width: 100%;
        margin-left: 10px;
        margin-top: 10px;
        margin-bottom: 150px;
    }

    .cart_manage {
        width: 100%;
        height: 30px;
        display: flex;
        margin: 10px auto;
    }

    .cart_manage_btu {
        display: flex;
        height: 30px;
        margin-left: 20px;
    }

    .cart_center {
        width: 100%;
        margin: 30px auto;
    }

    .van-card__content {
        text-align: center;
    }

    .cart_submit {
        margin-bottom: 50px;
    }

    .user_cart_bottom {
        margin-bottom: 100px;

    }
</style>