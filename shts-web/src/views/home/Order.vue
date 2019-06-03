<template>
  <div id="oversell">
    <van-nav-bar
      left-text="返回"
      left-arrow
      @click-left="()=>{this.$router.push('/home')}"
      title="我的订单">
    </van-nav-bar>
    <van-tabs v-model="active"
              color="#1989fa">
      <van-tab title="全部">内容 1</van-tab>
      <van-tab title="交易中">内容 2</van-tab>
      <van-tab title="已完成">内容 3</van-tab>
    </van-tabs>
  </div>
</template>

<script>
    export default {
        name: "Contact",
        data: function () {
            return {
                productList: [
                    {
                        titleName: '标题',
                        desc: '商品描述',
                        price: '100',
                        oldPrice: '199',
                        goosPic: 'https://img30.360buyimg.com/popWaterMark/jfs/t30361/216/596943799/306555/1d57adb1/5bf8b439Na4d2cc5c.jpg',
                        tag: '热销',
                        status: 2 // 交易完成
                    },
                    {
                        titleName: '标题',
                        desc: '商品描述',
                        price: '100',
                        oldPrice: '199',
                        goosPic: 'https://img30.360buyimg.com/popWaterMark/jfs/t30361/216/596943799/306555/1d57adb1/5bf8b439Na4d2cc5c.jpg',
                        tag: '热销',
                        status: 2 // 交易完成

                    },
                    {
                        titleName: '标题',
                        desc: '商品描述',
                        price: '100',
                        oldPrice: '199',
                        goosPic: 'https://img30.360buyimg.com/popWaterMark/jfs/t30361/216/596943799/306555/1d57adb1/5bf8b439Na4d2cc5c.jpg',
                        tag: '热销',
                        status: 1 // 已售出

                    },
                    {
                        titleName: '标题',
                        desc: '商品描述',
                        price: '100',
                        oldPrice: '199',
                        goosPic: 'https://img30.360buyimg.com/popWaterMark/jfs/t30361/216/596943799/306555/1d57adb1/5bf8b439Na4d2cc5c.jpg',
                        tag: '热销',
                        status: 0 // 在售中
                    }
                ]
            }
        }
    }
</script>

<style scoped>

</style>
