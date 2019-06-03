<template>
  <div class="v">
    <van-nav-bar
      title="个人信息"
    >
      <div slot="right">
        <van-icon
          @click="()=>{this.$router.push('/userinfo/edit')}"
          name="setting-o">
        </van-icon>
      </div>
    </van-nav-bar>
    <van-row>
      <van-col
        span="9"
        style="text-align: center"
      >
        <img
          class="avatar"
          alt="假装有头像"
          src="https://avatar-static.segmentfault.com/302/888/3028888032-5ba5928b70cb1_huge256"
        >
      </van-col>
      <van-col span="15">
        <div class="username" @click="()=>{this.$router.push('/userinfo/edit')}">
          <span style="width: 120px;">{{ userInfo.username }}</span>
        </div>
      </van-col>
    </van-row>

    <van-panel
      title="个性签名"
      :desc="userInfo.remark"
      status="OK">
    </van-panel>
    <van-cell-group style="margin-top: 30px">
      <van-cell
        icon="records"
        title="我发布的"
        to="/oversell"
        is-link
        size="large">
      </van-cell>
      <van-cell
        icon="cart-circle-o"
        title="我的订单"
        to="/buy"
        is-link
        size="large">
      </van-cell>
      <van-cell
        icon="star-o"
        title="我的收藏"
        to="/collection"
        is-link
        size="large">
      </van-cell>
      <van-cell
        icon="location-o"
        title="用户反馈"
        to="/feedback"
        is-link
        size="large">
      </van-cell>
      <van-cell
        icon="setting-o"
        title="关于"
        to="/about"
        is-link
        size="large">
      </van-cell>
    </van-cell-group>
    <van-button
      class="exit"
      type="danger"
      @click="exit"
      :block="true"
    >
      退出登录
    </van-button>
    <Footer/>
  </div>
</template>

<script>
  import Footer from '@/components/Footer'
  import {exit} from "../../config/getData"
  import {mapState} from 'vuex'

  export default {
    name: "Home",
    components: {Footer},
    data: function () {
      return {}
    },
    methods: {
      exit: function () {
        exit()
        this.$store.commit("exit")
        this.$router.push('/login')
      }
    },
    created() {
    },
    computed: {
      ...mapState(['userInfo'])
    },
    beforeMount() {
      this.$store.commit('changeActive', 4)
    }
  }
</script>

<style scoped>

  .avatar {
    width: 80px;
    height: 80px;
    margin: 20px;
    border-radius: 40px;
  }

  .username {
    font-size: 30px;
    font-weight: bold;
    line-height: 124px;
  }

  .edit-userinfo {
    font-size: 16px;
    float: right;
    color: #d0dbb7;
    margin-right: 25px;
  }

  .exit {
    width: 95%;
    margin: 20px 10px;
  }
</style>
