<template>
  <div>
    <van-nav-bar
      title="商品"
      left-arrow
      @click-left="()=>{this.$router.push('/')}"
    >
    </van-nav-bar>
    <div>
      <img style="height: 200px;width: 100%;"
           :src="goods.img"/>
    </div>
    <div style="margin: 10px;">商品标题：{{goods.title}}</div>
    <div style="margin: 10px;">发&nbsp;&nbsp;布&nbsp;&nbsp;者：{{seller.username}}</div>
    <van-tag style="margin: 0 5px;" plain type="success">价格:{{goods.price}}元</van-tag>
    <van-tag style="margin: 0 5px;" plain type="danger">数量:{{goods.quantity}}</van-tag>
    <van-tag style="margin: 0 5px;" plain type="danger">发布时间:{{goods.createTime}}</van-tag>
    <div style="margin: 10px;">
      详细描述：{{goods.desc}}
    </div>
    <van-goods-action>
      <van-goods-action-mini-btn
        icon="star-o"
        text="收藏"
        @click="collection">
      </van-goods-action-mini-btn>
      <van-goods-action-big-btn
        primary
        text="联系卖家"
        @click="contactSeller(goods.userId)"
      >
      </van-goods-action-big-btn>
    </van-goods-action>
  </div>
</template>

<script>
  import {getGoodsById, getUserInfo} from "../config/getData";

  export default {
    name: "Goods",
    data: function () {
      return {
        goods: {},
        seller: {}
      }
    },
    created() {
      getGoodsById({gid: this.$route.params.id}).then(res => {
        this.goods = res.data
        getUserInfo({id: this.goods.userId}).then(res => {
          this.seller = res.data
        })
      })
    },
    methods: {
      collection: function () {

      },
      contactSeller: function (id) {
        this.$router.push('/contact/' + id)
      }
    }
  }
</script>

<style scoped>

</style>
