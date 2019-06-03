<template>
  <div>
    <van-swipe :autoplay="3000" style="height: 204px;">
      <van-swipe-item v-for="(item,index) in swipeList" :key="index">
          <img :src="item.img" @click="call(item.id)" style="width: 100%;height: 200px;"/>
      </van-swipe-item>
    </van-swipe>
    <van-tabs v-model="tabActive"
              class="van-tabs"
              animated
              swipeable
              sticky
    >
      <van-tab title="新品">
        <Card
          v-for="(item,index) in productList"
          :key="index"
          :id="item.id"
          :title="item.title"
          :describe="item.desc"
          :price="item.price"
          :number="item.quantity"
          :pic="item.img"
          :date="item.createTime"
        />
      </van-tab>
      <van-tab title="图书">
      </van-tab>
      <van-tab title="生活用品">
      </van-tab>
      <van-tab title="配件">
      </van-tab>
    </van-tabs>
    <Footer/>
  </div>
</template>

<script>
  import Footer from '@/components/Footer'
  import Card from '@/components/Card'
  import {swipeGoods, newPublishGoods} from "../../config/getData";

  export default {
    name: "Index",
    components: {Footer, Card},
    data: function () {
      return {
        isLoading: false,
        tabActive: 0,
        productList: [],
        swipeList:[]
      }
    },
    methods: {
      onRefresh: function () {
        setTimeout(() => {
          this.$toast('刷新成功');
          this.isLoading = false;
          this.swipeList++;
        }, 500);
      },
      call(id){
        this.$router.push('/publishGoods/'+id)
      }
    },
    created() {
      swipeGoods().then(res => {
        if (res.status === 'success') {
          this.swipeList.push(...res.data)
        }
      })
      newPublishGoods().then(res => {
        if (res.status === 'success') {
          this.productList.push(...res.data)
        }
      })
    },
    beforeMount() {
      this.$store.commit('changeActive', 0);
    }

  }
</script>

<style scoped>
  .van-tabs {
    margin-top: -4px;
    padding-bottom: 65px;
    cursor: pointer;
  }
</style>
