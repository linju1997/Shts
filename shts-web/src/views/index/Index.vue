<template>
  <div>
    <van-swipe :autoplay="3000" style="height: 204px;">
      <van-swipe-item v-for="(image, index) in images" :key="index">
        <img :src="image" style="width: 100%;height: 200px;"/>
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
            id="123"
            title="小米8"
            describe="配置低，价格贵，抢都抢不到的垃圾智能手机。"
            price="105"
            number="10"
            pic="https://img.yzcdn.cn/public_files/2017/09/05/3bd347e44233a868c99cf0fe560232be.jpg"
            date="2018-03-04 12:12:11"
        />
        <Card title="小米8"
              describe="配置低，价格贵，抢都抢不到的垃圾智能手机。"
              price="105"
              number="10"
              pic="https://img.yzcdn.cn/public_files/2017/09/05/3bd347e44233a868c99cf0fe560232be.jpg"
              date="2018-03-04 12:12:11"/>
        <NoMore/>
      </van-tab>
      <van-tab title="图书">
        <Card title="小米8"
              describe="配置低，价格贵，抢都抢不到的垃圾智能手机。"
              pic="https://img.yzcdn.cn/public_files/2017/09/05/3bd347e44233a868c99cf0fe560232be.jpg"
              price="105"
              number="10"
              date="2018-03-04 12:12:11"/>
        <NoMore/>
      </van-tab>
      <van-tab title="生活用品">
        <Card title="小米8"
              describe="配置低，价格贵，抢都抢不到的垃圾智能手机。"
              pic="https://img.yzcdn.cn/public_files/2017/09/05/3bd347e44233a868c99cf0fe560232be.jpg"
              price="105"
              number="10"
              date="2018-03-04 12:12:11"/>
        <NoMore/>
      </van-tab>
      <van-tab title="配件">
        <Card title="小米8"
              describe="配置低，价格贵，抢都抢不到的垃圾智能手机。"
              pic="https://img.yzcdn.cn/public_files/2017/09/05/3bd347e44233a868c99cf0fe560232be.jpg"
              price="105"
              number="10"
              date="2018-03-04 12:12:11"/>
        <NoMore/>
      </van-tab>
      <van-tab title="其他">
        <Card title="小米8"
              describe="配置低，价格贵，抢都抢不到的垃圾智能手机。"
              price="105"
              number="10"
              date="2018-03-04 12:12:11"/>
        <NoMore/>
      </van-tab>
    </van-tabs>
    <Footer/>
  </div>
</template>

<script>
    import Footer from '@/components/Footer'
    import Card from '@/components/Card'
    import NoMore from '@/components/NoMore'

    export default {
        name: "Index",
        components: {Footer, Card, NoMore},
        data: function () {
            return {
                isLoading: false,
                tabActive: 0,
                images: [
                    'https://img.yzcdn.cn/public_files/2017/09/05/3bd347e44233a868c99cf0fe560232be.jpg',
                    'https://img.yzcdn.cn/public_files/2017/09/05/c0dab461920687911536621b345a0bc9.jpg',
                    'https://img.yzcdn.cn/public_files/2017/09/05/4e3ea0898b1c2c416eec8c11c5360833.jpg',
                    'https://img.yzcdn.cn/public_files/2017/09/05/fd08f07665ed67d50e11b32a21ce0682.jpg',
                ],
                productList: [
                    {
                        id: 123,
                        title: '标题',
                        desc: '商品描述',
                        price: '100',
                        pic: 'https://img30.360buyimg.com/popWaterMark/jfs/t30361/216/596943799/306555/1d57adb1/5bf8b439Na4d2cc5c.jpg',
                        status: 0
                    },
                    // {
                    //     titleName: '标题',
                    //     desc: '商品描述',
                    //     price: '100',
                    //     oldPrice: '199',
                    //     goosPic: 'https://img30.360buyimg.com/popWaterMark/jfs/t30361/216/596943799/306555/1d57adb1/5bf8b439Na4d2cc5c.jpg',
                    //     tag: '热销',
                    //     status: 0
                    //
                    // },
                    // {
                    //     titleName: '标题',
                    //     desc: '商品描述',
                    //     price: '100',
                    //     oldPrice: '199',
                    //     goosPic: 'https://img30.360buyimg.com/popWaterMark/jfs/t30361/216/596943799/306555/1d57adb1/5bf8b439Na4d2cc5c.jpg',
                    //     tag: '热销',
                    //     status: 0
                    //
                    // },
                    // {
                    //     titleName: '标题',
                    //     desc: '商品描述',
                    //     price: '100',
                    //     oldPrice: '199',
                    //     goosPic: 'https://img30.360buyimg.com/popWaterMark/jfs/t30361/216/596943799/306555/1d57adb1/5bf8b439Na4d2cc5c.jpg',
                    //     tag: '热销',
                    //     status: 0
                    // }
                ]
            }
        },
        methods: {
            onRefresh: function () {
                setTimeout(() => {
                    this.$toast('刷新成功');
                    this.isLoading = false;
                    this.count++;
                }, 500);
            }
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