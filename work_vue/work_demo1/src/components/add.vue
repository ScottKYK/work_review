<template>
    <h1>Goods功能</h1>
    <button type="button" @click="logout()">logout</button><br>
    <button type="button" @click="getAllGoods()">getAllGoods</button><br>
    allGoods : {{ allGoods }}
    <form>
        good id:<input type="index" v-model="_id">
        <button type="button" @click="getGoodsById()">getGoodsById</button><br>
    </form>
    <form>
        good name:<input type="index" v-model="goods.name">
        <button type="button" @click="addGoods()">addGoods</button><br>
    </form>
    <form>
        good id:<input type="index" v-model="_id">
        <button type="button" @click="deleteGoodsById()">deleteGoodsById</button><br>
    </form>
</template>
<script>
import axios from 'axios'
import { v4 as uuidv4 } from 'uuid';
export default {
    data() {
        return {
            goods: {
                _id: "",
                name: "",
                cr_user: "",
                cr_datetime: "",
                up_user: "",
                up_datetime: "",
                account: ""
            },
            uuid: "",
            token: "",
            allGoods: {},
            _id: "",
        }
    },
    methods: {
        async addGoods() {
            this.token = window.sessionStorage.getItem("token");
            axios
                .post("/auth/parse_token", this.token)
                .then(async (response) => {
                    this.goods.account = response.data; //獲取token中的account
                    if (response.status == 403) {
                        this.logout();
                        return;
                    }
                    this.goods._id = uuidv4();
                    const { data: res } = await this.$http.post("/goods/add", this.goods) //向後端發送添加請求
                    console.log(res);
                })

        },
        async getAllGoods() {
            this.token = window.sessionStorage.getItem("token");
            const { data: token_result } = await this.$http.post("/auth/parse_token", this.token)
            if (token_result.status == 403) {
                this.logout();
                return;
            }
            const { data: res } = await this.$http.get("/goods", this.goods)
            this.allGoods = res;
        },
        async getGoodsById() {
            this.token = window.sessionStorage.getItem("token");
            axios
                .post("/auth/parse_token", this.token)
                .then(async (response) => {
                    this.goods.account = response.data; //獲取token中的account
                    if (response.status == 403) {
                        this.logout();
                        return;
                    }
                    const { data: res } = await this.$http.get("/goods/" + this._id, this._id)
                    this.allGoods = res;
                    console.log(res);
                })
        },
        async deleteGoodsById() {
            //驗證token
            this.token = window.sessionStorage.getItem("token");
            axios
                .post("/auth/parse_token", this.token)
                .then(async (response) => {
                    this.goods.account = response.data; //獲取token中的account
                    if (response.status == 403) {
                        this.logout();
                        return;
                    }
                    const { data: res } = await this.$http.delete("/goods/" + this._id, this._id)
                    console.log(res);
                })

        },
        logout() {
            localStorage.clear();
            this.$router.push({ path: "/login" }) //跳轉頁面
        },


    },
    created() {
    }
}
</script>
<style></style>