<template>
    <div code="contact">
        <van-nav-bar
                left-text="返回"
                left-arrow
                @click-left="()=>{this.$router.go(-1)}"
                title="联系我们"/>
    </div>
</template>

<script>
    export default {
        name: "contact",
    }
</script>

<style scoped>

</style>