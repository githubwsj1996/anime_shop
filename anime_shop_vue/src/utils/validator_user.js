export  default {
    //表单校验
    //校验函数返回 true 表示校验通过，false 表示不通过
    //用户名验证
    validator_userName1(val) {
        return val.length <= 20;
    },
    validator_userName2(val) {
        let pattern = new RegExp(
            "[`~!@#$^&*()=|{}':;',\\[\\].<>《》/%?~！@#￥……&*（）——|{}【】‘；：”“'。，、？]"
        );
        return !pattern.test(val);
    },
    //登录密码验证
    validator_passWord1(val) {
        return val.length >=6;
    },
    //确认密码验证
    validator_compassWord1(val) {
        return val.length >=6;
    },
    //


}
