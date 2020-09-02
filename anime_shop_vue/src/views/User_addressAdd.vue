<template>
    <div class="address_insert" style="width: 100%">
        <p class="user_addressAdd_top">新增地址</p>
        <van-address-edit class="addressAdd"
                          :area-list="areaList"
                          show-set-default
                          @save="onSave"
                          :tel-maxlength="telMaxlength"
        />
        <div class="address_cancel" style="width:390px;padding: 10px;">
            <van-button round size="large" type="warning" style="height: 44px;">取消</van-button>
        </div>

    </div>
</template>

<script>
    import AreaList from '../utils/area.js';
    import {Toast} from 'vant';

    export default {
        data() {
            return {
                areaList: AreaList,
                telMaxlength: 11,
                user:JSON.parse(sessionStorage.getItem("user"))
            }
        },
        methods: {
            onSave(item) {
                const _this=this;
                let addressDto={
                    userId:this.user.userId,
                    name:item.name,
                    tel:item.tel,
                    province:item.province,
                    city:item.city,
                    county:item.county,
                    addressDetail:item.addressDetail,
                    areaCode:item.areaCode,
                }
                let user1={
                    userId:this.user.userId,
                    username: this.user.username,
                    password:this.user.password,
                    userAddressId: '',
                    userIcon: this.user.userIcon
                }
                axios.post("http://localhost:8040/UserInfo/saveAddress",addressDto).then(function (resp) {
                            if(resp.data.code===0){
                                Toast.success("添加新地址成功！")
                                _this.$router.push("/user_addresslist")
                                let addressNewId=resp.data.data
                                if(item.isDefault){
                                    axios.put("http://localhost:8040/UserInfo/updateUserAddressId/"+addressDto.userId+
                                        "/"+addressNewId).then(function (resp) {
                                        if(resp.data.code===0){
                                            user1.userAddressId=addressNewId
                                            sessionStorage.removeItem("user");
                                            sessionStorage.setItem("user",JSON.stringify(user1));
                                            history.go(0);
                                        }
                                    })
                                }
                            }else{
                                Toast.fail(resp.data.data)
                            }
                })
            }

        }
    }
</script>

<style scoped>
    .user_addressAdd_top {
        width: 100%;
        height: 100px;
        font-size: 24px;
        background-color: #ff4500;
        text-align: center;
        line-height: 100px;
        color: white;
        margin: 0;
    }

    .addressAdd {
        width: 390px;
    }
</style>