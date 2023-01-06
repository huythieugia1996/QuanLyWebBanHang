<template>
  <main class="login-background" style="z-index: 99">
    <div v-if="checkReToken == 0">
      <loadingDot style="margin-left: -25px; margin-top: -150px"></loadingDot>
    </div>

    <div class="login-form vpi" v-else-if="checkReToken == 1" style="border-radius: 1em">
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
            <div class="username">
              <i class="ms-Icon ms-Icon--Contact"></i>
              <input
                v-model="password"
                type="password"
                placeholder="Mật khẩu mới"
              />
            </div>
            <div class="password">
              <i class="ms-Icon ms-Icon--Lock"></i>
              <input
                v-model="rePass"
                type="password"
                placeholder="Nhập lại mật khẩu"
              />
              <p v-if="password == rePass"></p>
              <p
                v-else
                style="color: red; margin-top: 10px; text-align: center"
              >
                Nhập lại mật khẩu không chính xác!
              </p>
            </div>
            <div class="submit-button">
              <button
                type="button"
                @click="changePass()"
                v-if="
                  password == rePass &&
                  password != '' &&
                  rePass != '' &&
                  buttonReload == 0
                "
              >
                Đổi lại mật khẩu mới
              </button>
              <button type="button" v-else-if="buttonReload == 1">
                <div class="spinner-border text-light" role="status">
                  <span class="sr-only"></span>
                </div>
              </button>
              <button type="button" v-else disabled>
                Đổi lại mật khẩu mới
              </button>
              
            </div>
          </div>
        </div>
      </div>
    </div>

    <div class="login-form vpi" v-else>
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
            <div style="text-align: center">
              Token sai hoặc đã hết hạn, vui lòng thử lại!
            </div>
            <div class="submit-button">
              <button type="button" @click="returnHome()">Hủy</button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </main>
</template>

<script>
import axios from "axios";
import loadingDot from "../containers/loadingDot.vue";
export default {
  name: "resetPass",
  components: {
    loadingDot,
  },
  data() {
    return {
      password: "",
      rePass: "",
      checkRePass: "",
      checkReToken: 0,
      buttonReload: 0,
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
    callFunctionLogin: function () {
      var v = this;
      setTimeout(function () {
        v.returnLogin();
      }, 2000);
    },
    returnLogin() {
      this.$router.push({
        name: "login",
      });
    },
    callFunctionLoading: function () {
      var v = this;
      setTimeout(function () {
        v.loadingFormDot();
      }, 1000);
    },
    loadingFormDot() {
      this.$toasted.show("Đổi mật khẩu thành công !", {
        type: "success",
        duration: 2000,
      });
    },
    callFunction: function () {
      var v = this;
      setTimeout(function () {
        v.checkToken();
      }, 2000);
    },
    checkToken() {
      console.log(this.$route.query.token);
      axios
        .put(
          this.$store.state.MainLink +
            "customer/account/checkTokenReset?token=" +
            this.$route.query.token
        )
        .then((response) => {
          if (response.data.object == true) {
            this.checkReToken = 1;
          } else {
            this.checkReToken = 2;
          }
          console.log(this.checkReToken);
        })
        .catch((e) => {
          this.error.push(e);
          console.log(e);
        });
    },
    changePass() {
      let item = {
        password: this.password,
        token: this.$route.query.token,
      };
      this.buttonReload = 1;
      axios
        .put(this.$store.state.MainLink + "authentication/forgot", item)
        .then((response) => {
          console.log(response);
          this.callFunctionLoading();
          this.callFunctionLogin();
        })
        .catch((e) => {
          this.error.push(e);
          console.log(e);
        });
    },
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
  background: linear-gradient(90deg, #f89021, #db2927 80%);
  color: #fff !important;
}
.sps-am-ban button {
  background: #fff;
  color: rgb(27, 27, 27) !important;
}
.login-form .submit-button {
  text-align: center;
}
.login-form.vpi button {
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
