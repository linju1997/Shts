<template>
  <div>
    <p style="line-height: 30px;margin: 10px;">
      <van-tag plain size="medium" v-if="role==='seller'" type="success">卖家</van-tag>
      <van-tag plain size="medium" v-if="role==='buyer'" type="primary">买家</van-tag>
      <span style="margin-left: 10px">{{username}}:</span>
      <span style="margin-left: 20px">{{content}}</span>
    </p>
  </div>
</template>

<script>
    export default {
        props:['role','username','content'],
        name: "Seller",
    }
</script>

<style scoped>

</style>