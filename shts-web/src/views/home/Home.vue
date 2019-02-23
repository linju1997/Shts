<template>
    <div class="v">
        <van-nav-bar
                title="个人信息">
            <div slot="right">
                <van-icon @click="()=>{this.$toast({message:'还未开发',position:'top',duration:1000})}" name="setting-o"/>
            </div>
        </van-nav-bar>
        <!--https://avatar-static.segmentfault.com/302/888/3028888032-5ba5928b70cb1_huge256-->

        <van-row>
            <van-col span="9" style="text-align: center">
                <img class="avatar" alt="假装有头像"
                     src="https://avatar-static.segmentfault.com/302/888/3028888032-5ba5928b70cb1_huge256"/>
            </van-col>
            <van-col span="15">
                <div class="username">
                    <span>{{username}}</span>
                    <span class="edit-userinfo" @click="()=>{this.$router.push('/userinfo/edit')}">
                        修改个人资料
                    </span>
                </div>

            </van-col>
        </van-row>

        <van-panel title="个性签名" desc="用户很懒，什么都没有写。" status="OK"></van-panel>
        <van-cell-group style="margin-top: 30px">
            <van-cell icon="records" title="我发布的" to="/oversell" is-link size="large"></van-cell>
            <van-cell icon="gold-coin-o" title="我卖出的" to="/sell" is-link size="large"></van-cell>
            <van-cell icon="cart-circle-o" title="我买到的" to="/buy" is-link size="large"></van-cell>
            <van-cell icon="star-o" title="我的收藏" to="/collection" is-link size="large"></van-cell>
            <van-cell icon="location-o" title="收货地址" to="/address" is-link size="large"></van-cell>
            <van-cell icon="location-o" title="常见问题" to="/question" is-link size="large"></van-cell>
            <van-cell icon="location-o" title="联系我们" to="/contact" is-link size="large"></van-cell>
            <van-cell icon="setting-o" title="设置" to="/setting" is-link size="large"></van-cell>
        </van-cell-group>
        <Footer/>
    </div>
</template>

<script>
    import Footer from '@/components/Footer'

    export default {
        name: "Home",
        components: {Footer},
        data: function () {
            return {
                username: '忆'
            }
        },
        beforeCreate() {
            if (!this.$cm.checkLoginStatus()) {
                this.$router.push('/login');
            }
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

</style>