<template>
    <div>
        <p class="user_addressEdit_top">地址编辑</p>
        <van-address-edit
                :area-list="areaList"
                :address-info=this.address_info
                show-delete
                save-button-text="保存"
                @save="onSave"
                @delete="onDelete"
        />
    </div>
</template>

<script>
    import AreaList from '../utils/area';
    import {Toast} from 'vant';

    export default {
        name: "AddressEdit",
        data() {
            return {
                areaList: AreaList,
                address_info: ''
            }
        },
        created() {
            const _this = this;
            let addressId = this.$route.query.addressId;
            axios.get("http://localhost:8040/UserInfo/findByAddressId/" + addressId).then(function (resp) {
                _this.address_info = resp.data.data
            })
        },
        methods: {
            onSave(item) {
                let addressDto1 = {
                    id: item.id,
                    name: item.name,
                    tel: item.tel,
                    province: item.province,
                    city: item.city,
                    county: item.county,
                    addressDetail: item.addressDetail,
                    areaCode: item.areaCode,
                }
                const _this = this;
                axios.put("http://localhost:8040/UserInfo/updateAddress", addressDto1).then(function (resp) {
                    if (resp.data.code === 0) {
                        Toast.success(resp.data.data);
                        _this.$router.push("/user_addresslist");
                    }
                })
            },
            onDelete(item) {
                let user = JSON.parse(sessionStorage.getItem("user"));
                let user2 = {
                    userId: user.userId,
                    username: user.username,
                    password:user.password,
                    userAddressId: 0,
                    userIcon: user.userIcon
                }
                let a = null;
                if (item.id === user.userAddressId) {
                    a = 0;
                } else {
                    a = 1;
                }
                const _this=this;
                axios.delete("http://localhost:8040/UserInfo/deleteAddress/"
                    +user.userId+"/"+item.id+"/"+a).then(function (resp) {
                    if(resp.data.code===0){
                        if(item.id === user.userAddressId){
                            sessionStorage.removeItem("user");
                            sessionStorage.setItem("user",JSON.stringify(user2));
                            _this.$router.push("/user_addresslist");
                        }
                        Toast.success("收货地址,删除成功！");
                        _this.$router.push("/user_addresslist");
                    }
                })
            }
        }
    }
</script>

<style scoped>
    .user_addressEdit_top {
        width: 100%;
        height: 100px;
        font-size: 24px;
        background-color: #ff4500;
        text-align: center;
        line-height: 100px;
        color: white;
        margin: 0;
    }
</style>