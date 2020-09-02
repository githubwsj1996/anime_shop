<template>
    <div class="user_actions">
        <div class="actions_top">
            <van-image
                    round
                    width="5rem"
                    height="5rem"
                    :src=this.user.userIcon
                    style="margin: auto 20px"
            >
                <template v-slot:error>加载失败</template>
            </van-image>
            <span style="font-size:20px">{{this.user.username}},欢迎登录 ^_^ ！</span>
        </div>

        <div class="actions_center">
            <van-cell @click="user_Order" title="我的订单" is-link class="actions_center_cell"/>
            <van-cell @click="user_info" title="我的信息" is-link class="actions_center_cell"/>
            <van-cell @click="user_address" title="地址管理" is-link class="actions_center_cell"/>
        </div>

        <div class="actions_bottom">
            <van-button @click="loginOut" round block type="danger" size="large">
                退出 / 注销登录
            </van-button>
        </div>
    </div>
</template>

<script>
    import {Toast} from 'vant'
    export default {
        name: "User_actions",
        data() {
            return {
                user: JSON.parse(sessionStorage.getItem("user"))
            }
        },
        created() {
            if(this.user==null){
                Toast.fail("小主，还未登录呢！");
                this.$router.push("/login");
            }
        },
        methods: {
            user_Order() {
                this.$router.push("/user_order")
            },
            user_info() {
                this.$router.push("/user_info")
            },
            user_address() {
                this.$router.push("/user_addressList")
            },
            loginOut() {
                sessionStorage.removeItem("user")
                axios.put("http://localhost:8040/UserInfo/loginOut/"+this.user.username).then(e=>{
                    if(e.data.code===0){
                        Toast.success(e.data.data);
                    }
                })
                this.$router.push("/login")
            }
        }

    }
</script>

<style scoped>
    .actions_top {
        width: 100%;
        height: 100px;
        display: flex;
        background-color: orangered;
        color: white;
    }

    .actions_top span {
        line-height: 100px;
        margin-left: 10px;
    }

    .actions_center {
        width: 100%;
        margin-top: 20px;
    }

    .actions_center_cell {
        font-size: 18px;
        margin-top: 10px;
    }

    .actions_bottom {
        width: 100%;
        position: fixed;
        bottom: 0;
        left: 0;
        margin: 60px auto;
    }
</style>