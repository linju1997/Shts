<template>
    <div id="question">
        <van-nav-bar
                left-text="返回"
                left-arrow
                @click-left="()=>{this.$router.go(-1)}"
                title="常见问题"></van-nav-bar>
    </div>
</template>

<script>
    export default {
        name: "Question"
    }
</script>

<style scoped>

</style>