<template>
  <div id="publish">
    <van-nav-bar
      title="商品发布"
    >
    </van-nav-bar>
    <van-cell-group>
      <van-field
        v-model="title"
        left-icon="new-o"
        label="标题"
        placeholder="商品标题"
      >
      </van-field>
      <van-field
        v-model="desc"
        type="textarea"
        label="商品描述"
        placeholder="描述宝贝的转手原因，使用感受以及几成新，可以更好的找到买家哦。"
        left-icon="description"
        :autosize="{ maxHeight: 100, minHeight: 50 }"
      >
      </van-field>
      <van-field
        v-model="num"
        type="number"
        left-icon="points"
        label="商品数量"
      >>
      </van-field>
      <van-field
        v-model="price"
        type="number"
        label="商品价格"
        left-icon="balance-o"
        right-icon="gold-coin-o"
      >
      </van-field>
    </van-cell-group>
    <div style="height:50px;">
      <span style="line-height: 50px;margin-left:20px;">图片上传</span>
      <van-uploader
        :after-read="fileUpload"
        style="margin-left: 10px;">
        <van-icon name="photograph" size="90px;">
        </van-icon>
      </van-uploader>
      <span style="margin-left: 25%;" @click="clickSelectType">选择类型：{{type}}</span>
      <van-popup
        v-model="show"
        position="bottom"
      >
        <van-picker :columns="types" @change="selectTyle"></van-picker>
      </van-popup>
    </div>
    <div class="upfiles" v-show="pic!==''">
      <img :src="pic"/>
    </div>
    <van-button
      type="default"
      :block="true"
      class="btn-publish"
      @click="commit"
    >
      确认发布
    </van-button>
    <Footer/>
  </div>
</template>

<script>
  import Footer from '@/components/Footer'
  import {publishGoods} from "../../config/getData";

  export default {
    data: function () {
      return {
        show: false,
        title: '',
        desc: '',
        price: 0,
        num: 1,
        pic: '',
        type: '其他',
        types: ['图书', '生活用品', '配件', '其他']
      }
    },
    name: "Publish",
    components: {Footer},
    beforeMount() {
      this.$store.commit('changeActive', 2);
    },
    methods: {
      fileUpload: function (file) {
        this.pic = file.content;
      },
      selectTyle: function (tm, value) {
        this.type = value;
        this.show = false;
      },
      clickSelectType: function () {
        this.show = true;
        console.log("yes");
      },
      commit: function () {
        publishGoods({
          title: this.title,
          desc: this.desc,
          quantity: this.quantity,
          price: this.price,
          type: this.type,
          img: this.pic
        }).then((ret) => {
          if (ret.status === 'success') {
            this.$toast("商品发布成功");
          }else {
            this.$toast(ret.errMsg)
          }
        })
      }
    }
  }
</script>

<style scoped>

  .btn-publish {
    width: 94%;
    color: #000;
    background-color: yellow;
    border-radius: 5px;
    border-width: 0;
    cursor: pointer;
    position: fixed;
    bottom: 100px;
    left: 0;
    right: 0;
    margin: 0 auto;
  }

  .upfiles {
    width: 98%;
    text-align: center;
  }

  .upfiles img {
    width: 250px;
    height: 250px;
  }
</style>
