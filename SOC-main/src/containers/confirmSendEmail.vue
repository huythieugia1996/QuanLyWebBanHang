<template>
  <main class="login-background" style="z-index: 99">
    <!-- <button @click="getTotalCart()">Click here !</button> -->
    <div class="login-form vpi" style="border-radius: 1em">
      <div class="login-form-bg"></div>
      <div class="login">
        <div class="login-bg"></div>
        <div class="login-content">
          <div class="login-logo">
            <a @click="returnHome()">
              <img src="images/SOCStore.png" alt="SOC-Store LOGO" />
            </a>
          </div>
          <div>
            <p>
              Thư xác nhận đã được gửi tới email của bạn! Vui lòng kiểm tra.
            </p>
          </div>
          <div>
            <p>Sau 30s nếu không có thư, vui lòng gửi xác nhận lại tại đây !</p>
            <div class="submit-button">
              <button
                class="abled"
                type="button"
                @click="forgotPass()"
                disabled
                v-if="confirmEmail == 0"
              >
                <div class="spinner-border text-light" role="status">
                  <span class="sr-only"></span>
                </div>
              </button>
              <button
                class="abled"
                type="button"
                @click="resetEmail()"
                v-if="confirmEmail == 1"
              >
                Gửi xác nhận
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </main>
</template>

<script>
// import axios from "axios";
export default {
  name: "User",
  data() {
    return {
      //   email: "",
      confirmEmail: "",
    };
  },
  created() {
    this.callFunction();
  },
  methods: {
    returnHome() {
      this.$router.push({
        name: "Home",
      });
    },
    callFunction: function () {
      var v = this;
      setTimeout(function () {
        v.changeButton();
      }, 30000);
    },
    changeButton() {
      this.confirmEmail = 1;
    },
    resetEmail(){
      this.confirmEmail = 0;
      this.callFunction();
      this.$toasted.show(
              "Đã gửi lại email !",
              {
                type: "success",
                duration: 2000,
              }
            );
    }
  },
};
</script>

<style scoped>
* {
  -moz-box-sizing: border-box;
  -webkit-box-sizing: border-box;
  box-sizing: border-box;
}
body {
  margin: 0;
  padding: 0;
}
main {
  position: fixed;
  width: 100%;
  height: 100%;
  display: flex;
  background-image: url("https://img4.thuthuatphanmem.vn/uploads/2020/12/26/ha-noi-sunset-wallpaper_021547797.jpg");
  background-size: cover;
}
.login-form {
  font-family: Segoe UI WestEuropean, Segoe UI, -apple-system,
    BlinkMacSystemFont, Roboto, Helvetica Neue, sans-serif;
  position: relative;
  margin: auto;
  max-width: 440px;
  width: 440px;
  padding: 44px;
  /*! margin-bottom: 28px; */
  background-color: #fff;
  -webkit-box-shadow: 0 2px 6px rgba(0, 0, 0, 0.2);
  -moz-box-shadow: 0 2px 6px rgba(0, 0, 0, 0.2);
  box-shadow: 0 2px 6px rgba(0, 0, 0, 0.2);
  min-width: 320px;
  min-height: 338px;
  overflow: hidden;
  top: 0;
  bottom: 0;
}
.sps-am-ban {
  background-image: url("/img/login-bg.jpg");
  background-size: cover;
}
.vpi {
  background-image: url("/img/login-bg-vpi.png");
  background-size: cover;
}
.login-form.border {
  border-radius: 7px;
}
.login-form .login-logo {
  width: 100%;
  overflow: hidden;
  text-align: center;
  margin-bottom: 30px;
}
.login-form .login-logo img {
  max-width: 50%;
}
.login-form.vpi .login-logo img {
  max-width: 100%;
  width: 300px;
}
.login-form .login-content input {
  border: 1px solid #b6b7b9;
  padding: 14px 14px 14px 42px;
  width: 100%;
  color: rgb(27, 27, 27);
  font-size: 15px;
  box-shadow: none !important;
  outline: none;
  border-radius: 4px;
  border-image-source: none;
  overflow: hidden;
}
.login-form .login-content input:hover,
.login-form .login-content input:focus {
  border-image-slice: 1;
  border-image-source: linear-gradient(to right, #f89021, #db2927);
  border-color: transparent !important;
}
.login-form.vpi .login-content input:hover,
.login-form.vpi .login-content input:focus {
  border-image-slice: 1;
  border-image-source: linear-gradient(to right, #a55018, #d68847);
  border-color: transparent !important;
}
.login-form .login-content .username,
.login-form .login-content .password {
  position: relative;
}
.login-form .login-content .username {
  margin-bottom: 15px;
}
.login-form .login-content .ms-Icon {
  position: absolute;
  top: 13px;
  left: 15px;
}
.login-form button {
  margin-top: 30px;
  width: 50%;
  border-radius: 19px;
  box-shadow: none;
  border: 0;
  padding: 10px;
  font-size: 15px;
  /* background: linear-gradient(90deg, #f89021, #db2927 80%); */
  color: #fff !important;
}
.sps-am-ban button {
  background: #fff;
  color: rgb(27, 27, 27) !important;
}
.login-form .submit-button {
  text-align: center;
}
.login-form.vpi button.disabled {
  background: #33ccff;
  color: #fff;
  border-radius: 4px;
}
.login-form.vpi button.abled {
  background: #1a3e70;
  color: #fff;
  border-radius: 4px;
}
.login-background {
  background-color: #eeeeee;
  margin-top: -10%;
  height: 110%;
}
</style>
