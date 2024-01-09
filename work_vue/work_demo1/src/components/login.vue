<template>
    <form>
        account:<input type="index" v-model="loginForm.account"><br>
        password:<input type="index" v-model="loginForm.password"><br>
        <button type="button" @click="submit()">Submit</button>
    </form>
</template>

<script>
export default {
    data() {
        return {
            loginForm: {
                account: "user1",
                password: "123"
            },
        }
    },
    methods: {
        async submit() {
            // 後端訪問的請求進行連結
            const { data: res } = await this.$http.post("/auth/login", this.loginForm)
            console.log(res);
            if (res != null) {//判斷res結果
                window.sessionStorage.setItem("token", res.token) //將token對象存在網頁cookie中
                window.sessionStorage.setItem("account", res.account) //將account對象存在網頁session中
                this.$router.push({ path: "/add" }) //跳轉頁面
            }
            
        }
    }
}
</script>

<style></style>