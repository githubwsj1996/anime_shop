<template>
    <div class="anime_order">
        <p class="anime_order_top">购买清单</p>
        <div class="order_main" style="margin-top: 10px">
            <van-card v-for="data in this.orderCad"
                      class="order_main_center"
                      :title=data.title
                      :desc=data.desc
                      :thumb= data.thumb
                      :num=data.quantity
                      :price=data.price
            />

            <van-cell-group class="goods-cell-group">
                <van-cell class="goods-express" style="font-weight: bold">
                    <van-col span="21">配送方式</van-col>
                    <van-col style="color: red;">快递</van-col>
                </van-cell>
            </van-cell-group>

            <van-cell-group class="goods-cell-group">
                <van-cell class="goods-express" style="font-weight: bold" size="large">
                    <van-col span="20">商品金额</van-col>
                    <van-col style="color: red">￥{{this.orderDetails.price}}</van-col>
                </van-cell>
            </van-cell-group>

            <van-cell-group>
                <van-cell class="goods-express" style="font-weight: bold">
                    <van-col span="20">运费</van-col>
                    <van-col style="color: red;">￥20</van-col>
                </van-cell>
            </van-cell-group>

            <van-cell-group>
                <van-cell class="goods-express" style="font-weight: bold">
                    <van-col span="20">支付剩余时间</van-col>
                    <van-count-down  @finish="finishTime" :time=(this.orderDetails.time)*1000 style="color: red"/>
                </van-cell>
            </van-cell-group>

            <van-cell class="order_main_bottom">
                <van-col style="margin-left: 10px;font-size: 13px">
                    <van-icon name="location-o" style="margin-right: 10px;"/>
                    <span>收货人：{{this.orderDetails.username}} </span>
                    <span style="display:block;">联系电话:{{this.orderDetails.tel}}</span>
                    <span style="display:block;">收货地址：{{this.orderDetails.userAddress}}</span>
                    <span style="display:block;">订单编号:{{this.orderDetails.orderId}}</span>
                </van-col>
            </van-cell>
        </div>

        <div class="order_bottom">
            <van-submit-bar
                    class="order_submit"
                    :disabled="submitBtn"
                    :price=this.orderDetails.totalPrice
                    button-text="确认付款"
                    @submit="onSubmit">
            </van-submit-bar>
        </div>


    </div>
</template>

<script>
    import {Toast} from "vant";
    import {Dialog} from "vant";

    export default {
        name: "Anime_order",
        data() {
            return {
                orderId: "",
                cartOrderId:"",
                orderCad: "",
                orderDetails:"",
                submitBtn:this.$store.state.submitBtn
            }
        },
        created() {
            this.orderId = this.$route.query.orderId;
            this.cartOrderId=this.$route.query.cartOrderId;
            const _this = this
            if( this.orderId!=null){
                axios.get("http://localhost:8040/Order/findPromptlyOrder/" + this.orderId).then(function (resp) {
                    _this.orderCad = resp.data.data.animeOrderVOList
                    _this.orderDetails=resp.data.data
                })
            }
            if(this.cartOrderId!=null){
                axios.get("http://localhost:8040/Order/findPromptlyOrder/" + this.cartOrderId).then(function (resp) {
                    _this.orderCad = resp.data.data.animeOrderVOList
                    _this.orderDetails=resp.data.data
                })
            }

        },
        methods: {
            finishTime(){
                this.$store.state.submitBtn=true
                Toast.fail("小主，订单已超时,请重新购买！")
               this.$router.push("/anime_index")

            },
            onSubmit() {
                let state=1;
                this.orderId = this.$route.query.orderId;
                this.cartOrderId=this.$route.query.cartOrderId;
                if( this.orderId!=null){
                    const _this = this;
                    axios.put("http://localhost:8040/Order/updateState/"+this.orderId+"/"+state).then(function (resp) {
                        if(resp.data.code===0){
                            Toast.success("支付成功");
                            _this.$router.push("/user_order");
                        }
                    })
                }
                if(this.cartOrderId!=null){
                    const _this = this;
                    axios.put("http://localhost:8040/Order/updateState/"+this.cartOrderId+"/"+state).then(function (resp) {
                        if(resp.data.code===0){
                            Toast.success("支付成功");
                            _this.$router.push("/user_order");
                        }
                    })
                }

            }
        }
    }
</script>

<style lang="less">
    .anime_order {
        width: 100%;
        height: 100%;
    }

    .anime_order_top {
        width: 100%;
        height: 100px;
        font-size: 24px;
        background-color: #ff4500;
        text-align: center;
        line-height: 100px;
        color: white;
        margin: 0;
    }

    .order_main_bottom {
        margin-bottom: 110px;
    }

    .order_submit {
        margin-bottom: 50px;
    }


</style>