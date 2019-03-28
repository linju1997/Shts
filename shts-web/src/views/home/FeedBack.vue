<template>
  <div id="contact">
    <van-nav-bar
      left-text="返回"
      left-arrow
      @click-left="()=>{this.$router.push('/home')}"
      title="用户反馈">
    </van-nav-bar>
    <div
      id="form"
      class="form-body"
    >
      <van-cell-group style="overflow: hidden;">
        <van-field
          label="反馈类型"
          left-icon="question-o"
          v-model="type"
          placeholder="商品出售 | 商品下单 | BUG反馈"
          class="field-body">
        </van-field>
        <van-field
          label="联系方式"
          left-icon="contact"
          v-model="contact"
          type="text"
          placeholder="QQ | 手机号码 | 邮箱">
        </van-field>
        <van-field
          label="反馈内容"
          left-icon="edit"
          v-model="content"
          type="textarea"
          :autosize="{ maxHeight: 200, minHeight: 50 }"
          placeholder="在这里填下你的反馈内容">
        </van-field>
      </van-cell-group>

      <van-row class="form-commit">
        <van-col span="11">
          <van-button
            type="default"
            @click="reset"
            :block="true"
          >
            重置
          </van-button>
        </van-col>
        <van-col
          span="11"
          offset="2"
        >
          <van-button
            type="default"
            @click="commit"
            :block="true"
          >
            提交反馈
          </van-button>
        </van-col>
      </van-row>
    </div>
  </div>
</template>

<script>
  import {feedback} from "../../config/getData";

  export default {
    name: "Contact",
    data: function () {
      return {
        "type": '',
        "contact": '',
        "content": '',
        "recentContent": ''
      }
    },
    methods: {
      reset: function () {
        this.type = '';
        this.contact = '';
        this.content = '';
      },
      commit: function () {
        if (this.content === '') {
          this.$toast("请输入反馈内容");
          return;
        }
        if (this.recentContent === this.content) {
          this.$toast("请勿重复提交相同的内容");
          return;
        }

        feedback({type: this.type, contact: this.contact, content: this.content}).then((res) => {
          if (res.status === 200) {
            this.recentContent = this.content;
            this.$toast("提交成功");
          } else {
            this.$toast(res.errMsg);
          }
        });
      }
    }
  }
</script>

<style scoped>
  #form .form-commit {
    margin: 20px 10px;
  }
</style>
