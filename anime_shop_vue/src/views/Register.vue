<template>
    <div>
        <div class="register_top">
            <p style="margin: 0 auto;padding: 0;">个人注册</p>
        </div>

        <div class="register_main">
            <!--用户表单 -->
            <van-form validate-first @submit="onSubmit">
                <van-field
                        v-model="username"
                        clearable
                        label="用户名"
                        right-icon="question-o"
                        placeholder="请输入用户名"
                        left-icon="contact"
                        @blur="checkName_r"
                        @click-right-icon="$toast('用户名可以由字母,数字,下划线组成')"
                        :rules="[{required : true,message:'用户名不能为空'},
                                 {validator: this.$validator_user.validator_userName1,message:'输入的长度不能超过20位,请重新输入'},
                                 {validator: this.$validator_user.validator_userName2,message:'输入的字符含有特殊字符,请重新输入'},
                                ]"
                />
                <!--密码-->
                <van-field
                        v-model="password"
                        clearable
                        type="password"
                        label="密码"
                        right-icon="question-o"
                        placeholder="请输入密码"
                        left-icon="closed-eye"
                        @click-right-icon="$toast('密码必须是数字、字母、下划线')"
                        :rules="[{required : true,message:'密码不能为空'},
                                 {validator: this.$validator_user.validator_passWord1, message: '登录密码不能少于6个字符'}]"
                />
                <!--确认密码-->
                <van-field
                        v-model="compassWord"
                        label="确认密码"
                        clearable
                        name="compassWord"
                        type="password"
                        placeholder="请输入确认密码"
                        left-icon="closed-eye"
                        right-icon="question-o"
                        @click-right-icon="$toast('确认密码必须与密码相同一致')"
                        :rules="[{required : true, message: '确认密码不能为空' },
                                 {validator: this.$validator_user.validator_compassWord1, message: '确认密码不能少于6个字符'},
                                 {validator: validator_compassWord1, message: '登录密码与确认密码不一致！'}]"
                />
                <!--管理员选择-->
                <div class="admin_switch">
                    <span :style="colorType">管理员</span>
                    <van-switch :value="checked" @input="onInput"></van-switch>
                </div>
                <!--登录按钮-->
                <div class="register_btn">
                    <van-button type="danger" size="large" @click="onReset">取消</van-button>
                    <van-button type="primary" size="large" native-type="submit"
                                style="margin-top: 10px;margin-bottom: 10px">
                        登录
                    </van-button>
                </div>
            </van-form>

        </div>
    </div>
</template>

<script>
    import Toast from "vant/lib/toast";
    import Dialog from "vant/lib/dialog";

    export default {
        name: "register",
        data() {
            return {
                username: "",
                password: "",
                compassWord: "",
                checked: false,
                colorType: {},
            }
        },

        methods: {
            //确认密码验证
            validator_compassWord1(val) {
                return val === this.password;
            },
            //管理选择
            onInput(checked) {
                Dialog.confirm({
                    title: '提醒',
                    message: '是否需要注册管理员？',
                }).then(() => {
                    this.checked = checked;
                });

                if (checked === false) {
                    this.colorType = ""
                } else {
                    this.colorType = "color:red"
                }
            },
            //检索用户名是否重复
            checkName_r() {
                if (this.username!==null) {
                    axios.get("http://localhost:8040//UserInfo/checkName/" + this.username).then(function (resp) {
                        if (resp.data.code === 0) {
                            Toast.fail(resp.data.data);
                            history.go(0);
                        }
                    })
                }
            },
            //取消
            onReset() {
                history.go(-1)
            },
            //注册
            onSubmit() {
                const _this = this;
                let role = null;
                let perm = null;
                if (this.checked === false) {
                    role = "user"
                    perm = "0"
                } else {
                    role = "admin"
                    perm = "1"
                }
                let user = {
                    username: this.username,
                    password: this.password,
                    role: role,
                    perm: perm
                }
                axios.post("http://localhost:8040//UserInfo/register", user).then(function (resp) {
                    if (resp.data.code === 0) {
                        Toast.success(resp.data.data);
                        _this.$router.push("/login");
                    } else {
                        Toast.fail(resp.data.data);
                    }
                })
            },

        }
    }
</script>

<style scoped>
    .register_top {
        width: 100%;
        height: 60px;
        font-size: 24px;
        line-height: 60px;
        text-align: center;
        color: #f6371e;
        margin: 0;
        display: flex;
    }

    .admin_switch {
        position: absolute;
        right: 30px;
    }

    .register_btn {
        margin-top: 50px;
    }
</style>