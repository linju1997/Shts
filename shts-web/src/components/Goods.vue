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
           src="https://img.yzcdn.cn/public_files/2017/09/05/3bd347e44233a868c99cf0fe560232be.jpg"/>
    </div>
    <div style="margin: 10px;">商品标题：{{$route.params.id}}</div>
    <div style="margin: 10px;">发&nbsp;&nbsp;布&nbsp;&nbsp;者：{{user.username}}</div>
    <van-tag style="margin: 0 5px;" plain type="success">价格:{{goods.price}}元</van-tag>
    <van-tag style="margin: 0 5px;" plain type="danger">数量:{{goods.quantity}}</van-tag>
    <van-tag style="margin: 0 5px;" plain type="danger">发布时间:{{goods.date}}</van-tag>
    <div style="margin: 10px;">
      详细描述：{{goods.desc}}
    </div>
    <van-goods-action>
      <van-goods-action-mini-btn
          icon="star-o"
          text="收藏"
          @click="onClickMiniBtn">
      </van-goods-action-mini-btn>
      <van-goods-action-big-btn
          primary
          text="联系卖家"
          @click="contactSeller"
      >
      </van-goods-action-big-btn>
    </van-goods-action>
  </div>
</template>

<script>
    export default {
        name: "Goods",
        data: function () {
            return {
                goods: {
                    title: '小米8',
                    price: 111.3,
                    quantity: 19,
                    desc:'酒店房卡零九分快乐到家啊兰蔻水分看电视了就罚款了手机打开附件打开啦就是艰苦拉萨啊手机打开发链接ask按时的房间奥克兰撒撒娇的反馈按时地方阿斯顿发链接的',
                    date:'2018-03-04 12:22'
                },
                user: {
                    username: '忆'
                }
            }
        },
        created() {
          
        },
        methods:{
            contactSeller:function () {
                this.$router.push('/contact/'+this.user.username)
            }
        }
    }
</script>

<style scoped>

</style>