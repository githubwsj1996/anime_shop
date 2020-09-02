<template>
    <div class="error" >
        <div>
            <van-button  type="primary" round @click="goBack">返回上一页</van-button>
        </div>

    </div>
</template>

<script>
    export default {
        name: "ErrorInfo",
        methods:{
            goBack(){
                this.$router.push("/")
            }

        }
    }
</script>

<style scoped>
    .error{
        width: 100%;
        height: 800px;
        background: url("../../public/static/images/404.jpg") fixed no-repeat;
        background-size: 100% 100% ;
    }
</style>