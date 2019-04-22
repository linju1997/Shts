<template>
  <div class="card" @click="()=>{this.$router.push('/goods/'+this.id)}">
    <div class="image">
      <img :src="pic"/>
    </div>
    <div class="content">
      <div class="title">{{title}}</div>
      <div class="describe">{{describe}}</div>
      <div style="position: absolute;bottom: 10px;">
        <van-tag style="margin: 0 5px;" plain type="success">{{price}}元</van-tag>
        <van-tag style="margin: 0 5px;" plain type="danger">数量:{{number}}</van-tag>
        <van-tag style="margin: 0 5px;" plain type="primary">{{date}}</van-tag>
      </div>
    </div>
    <hr style="width: 98%;color: gray;opacity: 0.1;margin: 0;">
  </div>
</template>

<script>
    export default {
        props: ['title', 'describe', 'price', 'number', 'date','pic','id'],
        name: 'Footer',
        data: function () {
            return {}
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
  .card {
    position: relative;
  }

  .card .image {
    position: absolute;
    left: 0;
    top: 0;
    width: 120px;
    height: 120px;
    overflow: hidden;
  }

  .card .image img {
    width: 100%;
    height: 114px;
    padding: 3px;
  }

  .card .content {
    padding-left: 140px;
    min-height: 120px;
    border-bottom-width: 10px;
  }
  .card .content .title{
    padding: 5px;
    font-weight: bold;
  }
  .card .content .describe{
    font-size: 13px;
    font-weight: 500;
    margin-left: 5px;
  }
</style>