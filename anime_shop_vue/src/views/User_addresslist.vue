<template>
    <div class="user_address_main">
        <p class="user_address_top">地址管理</p>

        <div class="addressList_main">
            <van-address-list
                    v-model="chosenAddressId"
                    :list=this.list
                    @select="onSelect"
                    @add="onAdd"
                    @edit="onEdit"
            />
            <div style="margin-bottom: 20px">

            </div>
        </div>


    </div>
</template>

<script>
    import {Toast} from "vant";

    export default {
        name: "User_addressList",
        data() {
            return {
                chosenAddressId:'',
                list: [],
                user:''
            }
        },
        created() {
            const _this=this;
            let user=JSON.parse(sessionStorage.getItem("user"))
            this.user=user
            this.chosenAddressId=user.userAddressId
            if( this.chosenAddressId===0){
                Toast.fail("还未选择默认地址！");
            }
            axios.get("http://localhost:8040/UserInfo/findAddressList/"+user.userId).then(function (resp) {
                    if(resp.data.code===0){
                        _this.list=resp.data.data
                    }
            })
        },
        methods: {
            onSelect(item){
                const _this=this;
                let userUPDto={
                    userId: this.user.userId,
                    userAddressId: item.id
                }
                let user1={
                    userId:this.user.userId,
                    username: this.user.username,
                    password:this.user.password,
                    userAddressId: '',
                    userIcon: this.user.userIcon
                }
                axios.put("http://localhost:8040/UserInfo/updateUserAddressId/"+userUPDto.userId+
                    "/"+userUPDto.userAddressId).then(function (resp) {
                        if(resp.data.code===0){
                            user1.userAddressId=item.id
                            sessionStorage.removeItem("user");
                            sessionStorage.setItem("user",JSON.stringify(user1));
                            Toast(resp.data.data);
                        }
                })
            },
            onAdd() {
                Toast('新增地址');
                this.$router.push("/user_addressAdd")
            },
            onEdit(item) {
                this.$router.push({
                    path:"/user_addressEdit",
                    query:{
                        addressId:item.id
                    }
                })
            },
        },
    }
</script>

<style scoped>
    .user_address_top {
        width: 100%;
        height: 100px;
        font-size: 24px;
        background-color: #ff4500;
        text-align: center;
        line-height: 100px;
        color: white;
        margin: 0;
    }

    .addressList_main{
        width: 100%;
    }

    .van-address-list__bottom{
        margin-bottom: 50px;
    }
</style>