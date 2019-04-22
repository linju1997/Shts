<template>
  <div>
    <van-nav-bar title="买卖秀"></van-nav-bar>
    <Footer/>
  </div>
</template>

<script>
    import Footer from '@/components/Footer'

    export default {
        name: "Index",
        components: {Footer},
        data: function () {
            return {
                images: [
                    'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1552743216&di=43311be6420653140a1bb7f11660eaab&imgtype=jpg&er=1&src=http%3A%2F%2Fn1.itc.cn%2Fimg8%2Fwb%2Frecom%2F2016%2F01%2F03%2F145182320630000112.JPEG',
                    'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1552148496402&di=6f4ca3beaff7a8d3805061aee5d1af96&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2Fd8f9d72a6059252d50e3ea3d3f9b033b5bb5b917.jpg',
                ]
            }
        },
        beforeMount() {
            this.$store.commit('changeActive', 1);
        }
    }
</script>

<style scoped>

</style>