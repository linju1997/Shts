<template>
  <div id="oversell">
    <van-nav-bar
        left-text="返回"
        left-arrow
        @click-left="()=>{this.$router.push('/home')}"
        title="我发布的">
    </van-nav-bar>
    <div
        v-for="(item,index) in productList"
        :key="index"
    >
      <van-card
          :tag="item.tag"
          :price="item.price"
          :desc="item.desc"
          :title="item.desc"
          :thumb="item.goosPic"
          :origin-price="item.oldPrice"
          currency="￥"
          class="product-card"
      >
        <div slot="footer">
          <van-tag
              v-if="item.status === 0"
              size="large"
              type="danger"
              round
          >
            出售中
          </van-tag>
          <van-tag
              v-if="item.status === 1"
              size="large"
              type="primary"
              round
          >
            交易中
          </van-tag>
          <van-tag
              v-if="item.status === 2"
              size="large"
              type="success"
              round
          >
            已售出
          </van-tag>
          <!--<van-button size="small">评价</van-button>-->
          <!--<van-button size="small">联系卖家</van-button>-->
        </div>
      </van-card>
    </div>
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
                        goosPic: 'https://img30.360buyimg.com/popWaterMark/jfs/t30361/216/596943799/306555/1d57adb1/5bf8b439Na4d2cc5c.jpg',
                        tag: '热销',
                        status: 2 // 交易完成
                    },
                    {
                        titleName: '标题',
                        desc: '商品描述',
                        price: '100',
                        goosPic: 'https://img30.360buyimg.com/popWaterMark/jfs/t30361/216/596943799/306555/1d57adb1/5bf8b439Na4d2cc5c.jpg',
                        tag: '热销',
                        status: 2 // 交易完成

                    },
                    {
                        titleName: '标题',
                        desc: '商品描述',
                        price: '100',
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
  #oversell {
    background-color: white;
  }

  .product-card {
    background-color: white;
    box-shadow: 0 0 2px #aaaaaa;
    width: 98%;
    margin: 5px auto;
  }
</style>
