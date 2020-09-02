<template>
    <div class="user_order">
        <p class="user_order_top">{{this.user.username}}的订单页面</p>

        <div class="user_order_main">
            <van-tabs type="line" title-active-color="red" @click="onClick">
                <!--未支付-->
                <van-tab title="未支付">
                    <div class="notPay" v-for="item in this.orders">
                        <!--订单信息-->
                        <van-cell class="notPay_top" style="margin-top: 10px">
                            <van-col style="margin-left: 10px;font-size: 13px">
                                <van-icon name="location-o" style="margin-right: 10px;"/>
                                <span>收货人：{{item.username}}</span>
                                <span style="display:block;">联系电话:{{item.tel}}</span>
                                <span style="display:block;">收货地址：{{item.userAddress}}</span>
                                <span style="display:block;">订单编号:{{item.orderId}}</span>
                            </van-col>
                        </van-cell>

                        <!--商品信息-->
                        <van-card v-for="data in item.animeOrderVOList"
                                class="notPay_center"
                                :num=data.quantity
                                :price=data.price
                                :desc=data.desc
                                :title=data.title
                                :thumb=data.thumb
                        />

                        <van-cell-group class="goods-cell-group">
                            <van-cell class="goods-express">
                                <van-col span="21">配送方式</van-col>
                                <van-col>快递</van-col>
                            </van-cell>
                        </van-cell-group>

                        <van-cell-group class="goods-cell-group">
                            <van-cell class="goods-express" style="font-weight: bold">
                                <van-col span="20">商品金额</van-col>
                                <van-col style="color: red;">￥{{item.price}}</van-col>
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
                                <van-col span="20">支付状态</van-col>
                                <van-col style="color: red;">未支付</van-col>
                            </van-cell>
                        </van-cell-group>

                        <van-cell-group>
                            <van-cell class="goods-express" style="font-weight: bold">
                                <van-col span="20">支付剩余时间</van-col>
                               <van-count-down  @finish="finishTime(item)" :time=(item.time)*1000 style="color: red"/>
                            </van-cell>
                        </van-cell-group>

                        <div class="notPay_payBtu">
                            <van-dropdown-menu direction="up" style="width: 150px;">
                                <van-dropdown-item v-model="value" :options=item.option @change="cancelOrder(value,item)"/>
                            </van-dropdown-menu>
                            <div class="notPay_submit">
                                合计:<span style="margin-right: 10px;color: red">&nbsp;￥ &nbsp; {{item.totalPrice/100+".00"}}元</span>
                                <van-button round type="danger" @click="no_conPay(item)">
                                    确认支付
                                </van-button>
                            </div>
                            <van-divider
                                    :style="{ color: '#e50c25', borderColor: '#fa1946'}"
                            />
                        </div>
                    </div>

                    <div class="noPay_bottom"></div>

                </van-tab>

                <!--已支付-->
                <van-tab title="已支付">
                    <div class="yesPay" v-for="item in this.orders">
                        <!--订单信息-->
                        <van-cell class="notPay_top" style="margin-top: 10px">
                            <van-col style="margin-left: 10px;font-size: 13px">
                                <van-icon name="location-o" style="margin-right: 10px;"/>
                                <span>收货人：{{item.username}} </span>
                                <span style="display:block;">联系电话:{{item.tel}}</span>
                                <span style="display:block;">收货地址：{{item.userAddress}}</span>
                                <span style="display:block;">订单编号:{{item.orderId}}</span>
                            </van-col>
                        </van-cell>

                        <!--商品信息-->
                        <van-card v-for="data in item.animeOrderVOList"
                                class="notPay_center"
                                :num=data.quantity
                                :price=data.price
                                :desc=data.desc
                                :title=data.title
                                :thumb=data.thumb
                        />


                        <van-cell-group class="goods-cell-group">
                            <van-cell class="goods-express">
                                <van-col span="21">配送方式</van-col>
                                <van-col>快递</van-col>
                            </van-cell>
                            <van-cell class="goods-express" style="font-weight: bold">
                                <van-col span="20">商品金额</van-col>
                                <van-col style="color: red;">￥{{item.price}}</van-col>
                            </van-cell>
                            <van-cell class="goods-express" style="font-weight: bold">
                                <van-col span="20">运费</van-col>
                                <van-col style="color: red;">￥20</van-col>
                            </van-cell>
                            <van-cell class="goods-express" style="font-weight: bold">
                                <van-col span="20">支付状态</van-col>
                                <van-col style="color: red;">{{item.cause}}</van-col>
                            </van-cell>
                            <van-cell class="goods-express" style="font-weight: bold">
                                <van-col span="20">合计金额</van-col>
                                <van-col style="color: red;display: inline">￥{{item.totalPrice/100}}</van-col>
                            </van-cell>
                        </van-cell-group>

                        <van-divider
                                :style="{ color: '#0c6ee5', borderColor: '#193efa' }"
                        />

                    </div>
                    <div class="yesPay_bottom" />
                </van-tab>

                <!--已取消-->
                <van-tab title="已取消">
                    <div class="cancelPay" v-for="item in this.orders">
                        <!--订单信息-->
                        <van-cell class="notPay_top" style="margin-top: 10px">
                            <van-col style="margin-left: 10px;font-size: 13px">
                                <van-icon name="location-o" style="margin-right: 10px;"/>
                                <span>收货人：{{item.username}} </span>
                                <span style="display:block;">联系电话:{{item.tel}}</span>
                                <span style="display:block;">收货地址：{{item.userAddress}}</span>
                                <span style="display:block;">订单编号:{{item.orderId}}</span>
                            </van-col>
                        </van-cell>

                        <!--商品信息-->
                        <van-card v-for="data in item.animeOrderVOList"
                                  class="notPay_center"
                                  :num=data.quantity
                                  :price=data.price
                                  :desc=data.desc
                                  :title=data.title
                                  :thumb=data.thumb
                        />


                        <van-cell-group class="goods-cell-group">
                            <van-cell class="goods-express">
                                <van-col span="21">配送方式</van-col>
                                <van-col>快递</van-col>
                            </van-cell>
                            <van-cell class="goods-express" style="font-weight: bold">
                                <van-col span="20">商品金额</van-col>
                                <van-col style="color: red;">￥{{item.price}}</van-col>
                            </van-cell>
                            <van-cell class="goods-express" style="font-weight: bold">
                                <van-col span="20">运费</van-col>
                                <van-col style="color: red;">￥20</van-col>
                            </van-cell>
                            <van-cell class="goods-express" style="font-weight: bold">
                                <van-col span="20">合计金额</van-col>
                                <van-col style="color: red;display: inline">￥{{item.totalPrice/100}}</van-col>
                            </van-cell>
                            <van-cell class="goods-express" style="font-weight: bold;width: 100%;" size="large;">
                                <van-col span="20">取消原因</van-col>
                                <van-col style="color: red;">{{item.cause}}</van-col>
                            </van-cell>
                            <van-button round type="danger" @click="delOrder(item)"  style="position: absolute;right: 10px;bottom: 18px">
                                删除订单
                            </van-button>
                        </van-cell-group>

                        <van-divider
                                :style="{ color: '#0c6ee5', borderColor: '#193efa' }"
                        />

                    </div>
                    <div class="cancel_bottom" />
                </van-tab>
            </van-tabs>
        </div>

    </div>
</template>

<script>
    import {Toast} from 'vant';
    import {Dialog} from 'vant';
    export default {
        name: "User_order",
        data() {
            return {
                user:'',
                orders:'',
                state:'',
                value: "1",
            }

        },
        created() {
            const _this=this;
            this.user=JSON.parse(sessionStorage.getItem("user"));
            this.state=0;
            axios.get("http://localhost:8040/Order/findByState/"+this.user.userId+"/"+this.state).then(function (resp) {
                   if(resp.data.code===0){
                       _this.orders=resp.data.data
                   }else{
                       Toast.fail("加载失败！");
                   }

            })
        },
        methods: {
            onClick(name,title){
                const _this=this;
                if(title==="未支付"){
                    this.state=0;
                    axios.get("http://localhost:8040/Order/findByState/"+this.user.userId+"/"+this.state).then(function (resp) {
                        _this.orders=resp.data.data
                    })
                }else if(title==="已支付"){
                    this.state=1;
                    axios.get("http://localhost:8040/Order/findByState/"+this.user.userId+"/"+this.state).then(function (resp) {
                        _this.orders=resp.data.data
                    })
                }else if(title==="已取消"){
                    this.state=2;
                    axios.get("http://localhost:8040/Order/findByState/"+this.user.userId+"/"+this.state).then(function (resp) {
                        _this.orders=resp.data.data
                    })
                }
            },
            finishTime(item) {
                const _this=this;
                if(this.state===0){
                    axios.delete("http://localhost:8040/Order/delExpireOrder/"+item.orderId).then(function (resp) {
                        if(resp.data.code===0){
                            Toast.fail(resp.data.data);
                            _this.$store.state.submitBtn=true
                            history.go(0);
                        }
                    })
                }
            },
            cancelOrder(value,item) {
                Dialog.confirm({
                    message: '小主请留步，是否要取消订单吗？',
                }).then(() => {
                     axios.put("http://localhost:8040/Order/cancelOrder/"+item.orderId+"/"+value).then(function (resp) {
                        if(resp.data.code===0){
                            history.go(0);
                        }
                     })
                    }).catch(()=>{
                       history.go(0);
                })
            },
            no_conPay(item) {
                let state=1;
                axios.put("http://localhost:8040/Order/updateState/"+item.orderId+"/"+state).then(function (resp) {
                    if(resp.data.code===0){
                        Toast.success("支付成功");
                        history.go(0);
                    }
                })
            },
            delOrder(item){
                Dialog.confirm({
                    message: '是否要删除订单？',
                }).then(() => {
                    axios.delete("http://localhost:8040/Order/deleteOrder/" + item.orderId).then((resp) => {
                        if (resp.data.code === 0) {
                            Toast.success(resp.data.data);
                            history.go(0);
                        }
                    })
                }).catch(()=>{

                })
            }
        }
    }
</script>

<style scoped>
    .user_order_top {
        width: 100%;
        height: 100px;
        font-size: 24px;
        background-color: #ff4500;
        text-align: center;
        line-height: 100px;
        color: white;
        margin: 0;
    }

    .user_order_main {
        width: 100%;
    }

    .notPay_payBtu {
        margin-left: 10px;
        margin-top: 10px;
        display: flex;
    }
    .notPay_submit{
        width: 100%;
        margin-left: 70px;
    }

    .noPay_bottom {
        margin-bottom: 50px;
    }

    .yesPay_bottom{
        margin-bottom: 50px;
    }

    .cancel_bottom{
        margin-bottom: 50px;
    }

</style>