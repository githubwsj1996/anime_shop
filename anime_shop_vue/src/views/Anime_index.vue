<template>
    <div style="width: 100%;margin: 0;padding: 0;">
        <!--滚动条-->
        <van-notice-bar left-icon="volume-o" :scrollable="false" style="margin-bottom: 5px">
            <van-swipe vertical class="notice-swipe"
                       :autoplay="2000"
                       :show-indicators="false"
            >
                <van-swipe-item v-for="data in swipeItems">
                    今日热销商品:&nbsp;{{data.title}}&nbsp;手办;价格:&nbsp;{{data.price}}元!
                </van-swipe-item>
            </van-swipe>
        </van-notice-bar>

        <!--搜索框-->
        <van-search
                v-model="searchValue"
                show-action
                label="手办"
                shape="round"
                placeholder="请输入搜索关键词"
                @search="onSearch"
        >
            <template #action>
                <div @click="onSearch">搜索</div>
            </template>
        </van-search>

        <!--分类-->
        <van-cell
                is-link
                :title="categoryName"
                @click="show = true"
                style="color: red;text-align: center"
        />
        <van-action-sheet
                v-model="show"
                :actions="actions"
                cancel-text="取消"
                close-on-click-action
                @select="onSelect"
        />

        <!--商品列表-->
        <div class="index_main" v-for="data in animeGoods" style="margin-top: 10px">
            <van-card
                    class="card_index"
                    :price="data.price"
                    :desc="data.desc"
                    :title="data.title"
                    :thumb="data.thumb"
                    @click="onCard(data.anime_id)"
            >
                <template #tags>
                    <van-tag
                            plain
                            type="danger"
                            v-for="tag in data.tag"
                            style="margin-left: 10px"
                    >{{tag.name}}
                    </van-tag>
                </template>
            </van-card>
        </div>

        <!--分页  :total-items 总记录数 :items-per-page 每页显示 -->
        <van-pagination
                class="page_index"
                v-model="currentPage"
                :total-items="totalItems"
                :items-per-page="5"
                v-show="pageShow"
                @change="changePage"
        ></van-pagination>

        <div class="anime_index_bottom"></div>
    </div>
</template>

<script>
    import {Toast} from 'vant';

    export default {
        data() {
            return {
                animeGoods: "",
                currentPage: "",
                totalItems: "",
                swipeItems: '',
                searchValue: "",
                categoryName: "手办分类",
                show: false,
                actions: [],
                pageShow: true
            };
        },
        created() {
            const _this = this;
            axios
                .get("http://localhost:8040/Goods/findAllPage/1/5")
                .then(function (resp) {
                    _this.animeGoods = resp.data.data.animeInfoVOList;
                    _this.totalItems = resp.data.data.countPages;
                    _this.actions = resp.data.data.categories;
                });

            axios.get("http://localhost:8040/Order/countDetailsByAnimeId").then((e) => {
                if (e.data.code === 0) {
                    _this.swipeItems = e.data.data
                }
            })
        },
        methods: {
            changePage(currentPage) {
                const _this = this;
                axios
                    .get("http://localhost:8040/Goods/findAllPage/" + currentPage + "/5")
                    .then(function (resp) {
                        _this.animeGoods = resp.data.data.animeInfoVOList;
                        _this.totalItems = resp.data.data.countPages;
                    });
            },
            onSearch() {
              const _this=this;
                if (this.searchValue == null) {
                    Toast.fail("请输入搜索商品");
                } else {
                    axios.get("http://localhost:8040//Goods/searchGoods/" + this.searchValue).then(e => {
                      if(e.data!=null){
                        _this.animeGoods=e.data
                        _this.pageShow=false
                      }else{
                        Toast.fail("小主,没有搜索到该商品！");
                        history.go(0);
                      }
                    })
                }
            },
            onCard(val) {
                this.$router.push({
                    path: "/anime_details",
                    query: {
                        animeId: val
                    }
                });
            },
            onSelect(item) {
                this.categoryName = item.name;
                if (item.name === "全部") {
                    history.go(0);
                }
                const _this = this;
                axios
                    .get("http://localhost:8040//Goods/findAllByCategory/" + item.name)
                    .then(function (resp) {
                        _this.animeGoods = resp.data.data.animeInfoVOList;
                        _this.pageShow = false;
                    });
            }
        }
    };
</script>

<style>
    .notice-swipe {
        height: 40px;
        line-height: 40px;
    }

    .card_index {
        margin-top: 5px;
        font-size: 18px;
    }

    .page_index {
        margin-top: 10px;
        margin-bottom: 10px;
    }

    .anime_index_bottom {
        margin-bottom: 60px;
    }

    .van-card__content {
        text-align: center;
        font-size: 14px;
        font-weight: bold;
    }

    .van-card__desc {
        height: 20px;
        color: #646566;
        line-height: 20px;
        width: 200px;
        margin: 5px auto;
    }

    .van-tag--danger.van-tag--plain {
        color: #ee0a24;
        margin: 3px auto;
    }
</style>