<template>
  <div class="v">
    <van-nav-bar
      title="个人信息"
    >
      <div slot="right">
        <van-icon
          @click="()=>{this.$toast({message:'还未开发',position:'top',duration:1000})}"
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
        <div class="username">
          <span>{{ userInfo.username }}</span>
          <span
            class="edit-userinfo"
            @click="()=>{this.$router.push('/userinfo/edit')}"
          >
            个人资料
          </span>
        </div>
      </van-col>
    </van-row>

    <van-panel
      title="个性签名"
      :desc="userInfo.remark"
      status="OK"></van-panel>
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
        title="我买到的"
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
  import {getUserInfo} from '@/config/getData'

  export default {
    name: "Home",
    components: {Footer},
    data: function () {
      return {
        userInfo: {
          username: '用户01'
        }
      }
    },
    methods: {
      exit: function () {
        this.$router.push('/login');
      }
    },
    created() {
      getUserInfo().then(res => {
        if (res.status === 200) {
          this.userInfo = res.data;
          this.$store.commit('changeUserInfo', this.userInfo);
        } else {
          this.$toast(res.errMsg);
          if (res.status === 40002) {
            this.$router.push('/login');
          }
        }
      })
    },
    beforeMount() {
      this.$store.commit('changeActive', 4);
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
    margin: 20px 10px;
  }
</style>
