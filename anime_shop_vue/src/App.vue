<template>
  <div class="home" >
    <van-tabbar v-show="true" route v-model="active"
                v-for="item1 in $router.options.routes"
                v-if="item1.show">

      <van-tabbar-item v-for="item2 in item1.children"
                       :icon="item2.iconName"
                       replace :to="item2.path">
        {{item2.name}}
      </van-tabbar-item>

    </van-tabbar>

    <div>
      <router-view></router-view>
    </div>
  </div>
</template>

<script>
import HelloWorld from '@/components/HelloWorld.vue'
export default {
  name: 'app',
  components: {
    HelloWorld
  },
  data() {
    return {
      active: 0,
    };
  }
}
</script>

<style>
#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
