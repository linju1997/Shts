<template>
    <van-tabbar v-model="active" active-color="#07c160" @change="change">
        <van-tabbar-item icon="home-o" to="/">主页</van-tabbar-item>
        <van-tabbar-item icon="fire-o" to="/hot">热品</van-tabbar-item>
        <van-tabbar-item icon="add-o" to="/publish">发布</van-tabbar-item>
        <van-tabbar-item icon="chat-o" to="/message">消息</van-tabbar-item>
        <van-tabbar-item icon="user-circle-o" to="/home">我的</van-tabbar-item>
    </van-tabbar>
</template>

<script>
    export default {
        name: "Footer",
        data: function () {
            return {}
        },
        methods: {
            change: function (e) {
                this.$store.commit('changeActive', e);
            }
        },
        computed: {
            active: {
                get: function () {
                    return this.$store.state.tabActive;
                },
                set: function () {

                }
            }
        }
    }
</script>

<style scoped>

</style>