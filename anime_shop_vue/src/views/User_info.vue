<template>
    <div class="user_info_main">
        <p class="user_info_top">个人信息</p>

        <div class="user_info_center">
            <van-form validate-first @failed="onFailed" @submit="onSubmit">
                <van-field
                        v-model="username"
                        label="用户名称"
                        clearable
                        name="userName"
                        placeholder="请输入用户名称"
                        :rules="[{required : true, message: '用户名称不能位空' },
                                 {validator: this.$validator_user.validator_userName1, message: '输入的长度不能超过20位,请重新输入'},
                                 {validator: this.$validator_user.validator_userName2, message: '输入的字符含有特殊字符,请重新输入'}]"
                />
                <van-field
                        v-model="password"
                        label="登录密码"
                        clearable
                        name="passWord"
                        type="password"
                        placeholder="请输入登录密码"
                        @input="onInput()"
                        @clear="onClear()"
                        :rules="[{required : true, message: '登录密码不能为空' },
                                 {validator: this.$validator_user.validator_passWord1, message: '登录密码不能少于6个字符'},]"
                />
                <van-field
                        v-model="compassWord"
                        label="确认密码"
                        clearable
                        name="compassWord"
                        type="password"
                        placeholder="请输入确认密码"
                        :rules="[{required : true, message: '确认密码不能为空' },
                                 {validator: this.$validator_user.validator_compassWord1, message: '确认密码不能少于6个字符'},
                                 {validator: validator_compassWord2, message: '登录密码与确认密码不一致！'}]"
                />

                <!--头像上传-->
                <van-field name="uploader" label="文件上传">
                    <template #input>
                        <van-uploader
                                v-model="uploader"
                                :max-size="1000 * 1024"
                                :max-count="1"
                                @oversize="onOversize"
                                :before-read="beforeRead"
                                :after-read="afterRead"/>
                    </template>
                </van-field>

                <div style="margin: 16px;">
                    <van-button round block type="info" native-type="submit">
                        保存
                    </van-button>
                </div>
            </van-form>
        </div>
    </div>

</template>

<script>
    import {Toast} from "vant";

    export default {
        name: "User_info",
        data() {
            return {
                user: '',
                username: '',
                password: '',
                compassWord: '',
                uploader: [],
                imgUrl: ''
            };
        },
        created() {
            const _this = this;
            this.user = JSON.parse(sessionStorage.getItem("user"));
            axios.get("http://localhost:8040/UserInfo/findByUid/" + this.user.userId).then(function (resp) {
                _this.username = resp.data.data.username;
                _this.password = resp.data.data.password;
                _this.compassWord = resp.data.data.password;
                _this.uploader = resp.data.data.uploader
            })
        },
        methods: {
            //提交表单且验证不通过后触发
            validator_compassWord2(val) {
                return val === this.password;

            },
            onFailed(errorInfo) {
                console.log('failed', errorInfo);
            },
            onInput() {
                this.compassWord = "";
            },
            onClear() {
                this.compassWord = "";
            },
            // 上传前置处理，返回布尔值
            beforeRead(file) {
                if (file.type !== 'image/jpeg') {
                    Toast('请上传 jpg 格式图片');
                    return false;
                }
                return true;
            },
            //限制文件上传文件大小
            onOversize(file) {
                Toast('文件大小不能超过 1MB');
            },
            //文件上传完毕后会触发 after-read 回调函数
            async afterRead(file) {
                const _this = this;
                let params = new FormData()
                params.append('file', file.file)

                let config = {
                    headers: { //添加请求头
                        'Content-Type': 'multipart/form-data'
                    }
                }
                axios.post("http://localhost:8040/UserInfo/uploadIcon", params, config).then(function (resp) {
                    if (resp.data.code === 0) {
                        Toast.success("头像上传成功！")
                        _this.imgUrl = resp.data.data
                        _this.user.userIcon=resp.data.data
                    } else {
                        Toast.fail("头像上传失败！")
                    }

                })
            },
            //提交保存
            onSubmit() {
                const _this = this;
                if(this.password===this.user.password){
                    this.password=null;
                }
                let userInfoDto = {
                    userId: this.user.userId,
                    username: this.username,
                    password: this.password,
                    imgUrl: this.user.userIcon
                }
                axios.put("http://localhost:8040/UserInfo/updateUserInfo", userInfoDto).then(function (resp) {
                    let userInfo = resp.data.data;
                    if (resp.data.code === 0) {
                        let user3 = {
                            userId: userInfo.userId,
                            username: userInfo.username,
                            password: userInfo.password,
                            userAddressId: _this.user.userAddressId,
                            userIcon: userInfo.userIcon
                        }
                        sessionStorage.removeItem("user")
                        sessionStorage.setItem("user", JSON.stringify(user3))
                        Toast.success("保存成功！")
                        _this.$router.push("/user_actions")
                    } else {
                        Toast.fail(resp.data.data);
                    }
                })
            }


        },
    }
</script>

<style scoped>
    .user_info_top {
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