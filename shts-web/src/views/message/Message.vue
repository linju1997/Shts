<template>
  <div>
    <van-nav-bar title="消息"></van-nav-bar>

    <div>
      y用户
    </div>

    <Footer/>
  </div>
</template>

<script>
    import Footer from '@/components/Footer'

    export default {
        name: "Message",
        components: {Footer},
        beforeMount() {
            this.$store.commit('changeActive', 3);
        }
    }
</script>

<style scoped>

</style>