<template>
    <div class="address">
        <van-nav-bar
                left-text="返回"
                left-arrow
                title="地址列表"
                @click-left="()=>{this.$router.go(-1)}"
        />
        <van-address-list
                v-model="chosenAddressId"
                :list="list"
                @add="addAddress()"
                @edit="editAddress()"
        />
    </div>
</template>

<script>
    export default {
        name: "AddressList",
        data: function () {
            return {
                chosenAddressId: '1',
                list: [
                    {
                        code: '1',
                        name: '张三',
                        tel: '13000000000',
                        address: '浙江省杭州市西湖区文三路 138 号东方通信大厦 7 楼 501 室'
                    },
                    {
                        code: '2',
                        name: '李四',
                        tel: '1310000000',
                        address: '浙江省杭州市拱墅区莫干山路 50 号'
                    }
                ],
                disabledList: [
                    {
                        code: '3',
                        name: '王五',
                        tel: '1320000000',
                        address: '浙江省杭州市滨江区江南大道 15 号'
                    }
                ]
            }
        },
        methods: {
            addAddress:function () {
                this.$router.push('')
            },
            editAddress:function () {
                this.$router.push('/address/edit')
            }
        }
    }
</script>

<style scoped>

</style>