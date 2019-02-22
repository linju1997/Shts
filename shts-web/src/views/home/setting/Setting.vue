<template>
    <div id="setting">
        <van-nav-bar
                left-text="返回"
                left-arrow
                @click-left="()=>{this.$router.go(-1)}"
                title="设置">
        </van-nav-bar>
    </div>
</template>

<script>
    export default {
        name: "setting",
    }
</script>

<style scoped>

</style>