<template>
    <div code="address-edit">
        <van-nav-bar
                left-text="返回"
                left-arrow
                @click-left="()=>{this.$router.push('/address/list')}"
                title="地址编辑"/>
        <van-address-edit
                :area-list="areaList"
                show-delete
                show-set-default
                @save="onSave"
                @delete="onDelete"
                @change-detail="onChangeDetail"
        />
    </div>
</template>

<script>
    import areajs from '@/assets/area.js'
    export default {
        name: "AddressEdit",
        data: function () {
            return {
                areaList: {},
            }
        },
        methods: {
            onSave: function () {
                this.$toast("save");
            },
            onDelete: function () {
                this.$toast('delete');
            },
            onChangeDetail: function () {
                this.$toast("change")
            }
        },
        created() {
            this.areaList = areajs
        }
    }
</script>

<style scoped>

</style>