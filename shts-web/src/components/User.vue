<template>
  <div>
    <van-nav-bar
        :title="seller.username"
        left-arrow
        @click-left="()=>{this.$router.push('/message')}"
    >
    </van-nav-bar>
    <div ref="content">
      <message
          v-for="(item,index) in history"
          :key="index"
          :role="item.role"
          :username="item.username"
          :content="item.content">
      </message>
    </div>

    <van-cell-group style="position: fixed;bottom: 0;width: 100%;">
      <van-field v-model="content" placeholder="想跟TA说点什么呢">
        <van-button @click="sendMessage" slot="button" size="small" type="primary">发送</van-button>
      </van-field>
    </van-cell-group>
  </div>
</template>

<script>
    import Message from './Message'

    export default {
        name: "User",
        components: {Message},
        data: function () {
            return {
                seller: {
                    id: 123,
                    username: '砍价不卖',
                },
                user: {
                    id: 333,
                    username: '忆'
                },
                content: '',
                history: []
            }
        },
        methods: {
            sendMessage: function () {
                if (this.content !== '') {
                    this.history.push({
                        role: 'buyer',
                        username: this.user.username,
                        content: this.content
                    });
                    this.content = '';
                }
            }
        }
    }
</script>

<style scoped>

</style>
