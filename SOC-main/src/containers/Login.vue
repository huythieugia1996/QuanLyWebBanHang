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
            <div class="username">
              <!-- <i class="ms-Icon ms-Icon--Contact"></i> -->
              <!-- <i class="far fa-user"></i> -->
              <!-- <b-icon icon="person"></b-icon> -->
              <input v-model="username" type="text" placeholder="Tài khoản" />
            </div>
            <div class="password">
              <!-- <i class="ms-Icon ms-Icon--Lock"></i> -->
              <!-- <i class="fas fa-lock"></i> -->
              <!-- <b-icon icon="lock"></b-icon> -->
              <input
                v-model="password"
                type="password"
                placeholder="Mật khẩu"
              />
            </div>
            <div class="text-center" style="margin-top: 15px;color: red;">{{loginFail}}</div>
            <div class="submit-button">
              <button type="button" @click="callFunction()" v-if="checkLogin == 0">
                Đăng nhập
              </button>
              <button class="btn btn-primary" type="button" disabled v-else>
                <span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span>
                Đang đăng nhập...
              </button>
            </div>
            <div style="text-align: center; margin-top: 25px">
              <a style="text-decoration: none" @click="forgot()">Quên mật khẩu</a> |
              <a @click="register()">Đăng ký tài khoản mới</a>
            </div>
          </div>
        </div>
      </div>
    </div>
  </main>
</template>

<script>
import axios from "axios";
export default {
  name: "User",
  data() {
    return {
      username: "",
      password: "",
      loginFail: "",
      checkLogin: 0,
      errorAlert: ""
    };
  },
  methods: {
    callFunction: function () {
      var v = this;
      v.loginFail = "",
      v.checkLogin = 1
      setTimeout(function () {
        v.LoginJWT();
        v.callFunctionTotal();
      }, 3000);
    },
    callFunctionTotal: function () {
      var v = this;
      setTimeout(function () {
        if (localStorage.userToken != "") {
          v.getTotalCart();
          v.getDataFavorites();
          v.switchToAccount();
        }
      }, 1000);
    },
    LoginJWT() {
      axios
        .post(this.$store.state.MainLink + "authentication/login", {
          username: this.username,
          password: this.password,
        })
        .then((response) => {
          localStorage.userToken =
            response.data.tokenType + " " + response.data.accessToken;
          this.$store.state.tokenUser = localStorage.userToken;
          // localStorage.nameUser = response.data.username;
          // this.$store.state.userName = response.data.username;
          // console.log(response.data)

          if (response.data.roles[0] == "Director") {
            this.$toasted.show("Đăng nhập bằng tài khoản quản trị viên !", {
              type: "success",
              duration: 2000,
            });
            this.$router.push({
              name: "Home",
            });
          } else if (response.data.roles[0] == "Staff") {
            this.$toasted.show("Đăng nhập bằng tài khoản nhân viên !", {
              type: "success",
              duration: 2000,
            });
            this.$router.push({
              name: "Home",
            });
          } else if (response.data.roles[0] == "User") {
            // console.log("Đăng nhập user");
            this.$toasted.show("Đăng nhập thành công !", {
              type: "success",
              duration: 2000,
            });
            this.$router.push({
              name: "Home",
            });
          } else {
            this.$toasted.show(
              "Thông tin không chính xác, vui lòng nhập lại !",
              {
                type: "error",
                duration: 2000,
              }
            );
            
          }
        })
        .catch((e) => {
          // this.error.push(e);
          console.log(e.response.data.errorMsg);
          this.errorAlert = e.response.data.errorMsg
          this.loginFail = "Thông tin đăng nhập không chính xác!"
          this.checkLogin = 0
          this.showAlert()
          
          // this.$toasted.show("Thông tin không chính xác, vui lòng nhập lại !", {
          //   type: "error",
          //   duration: 2000,
          // });
        });
    },
    showAlert() {
      const options = {title: 'Lỗi', size: 'sm'}
      this.$dialogs.alert(this.errorAlert, options)
      .then(res => {
        console.log(res) // {ok: true|false|undefined}
      })
    },
    getTotalCart() {
      axios
        .get(this.$store.state.MainLink + "customer/cart/get", {
          headers: {
            Authorization: localStorage.userToken,
          },
        })
        .then((response) => {
          this.$store.state.totalCart = response.data.object.length;
          // console.log(response.data.object);
          this.$store.state.StoreCart = response.data.object;
          // console.log(this.$store.state.StoreCart);

          var detailCart = JSON.parse(localStorage.getItem("detailCart")) || [];
          detailCart.push(response.data.object);
          localStorage.setItem("detailCart", JSON.stringify(detailCart));

          this.$store.state.StoreCart = JSON.parse(localStorage.detailCart)[0];

          this.$store.state.StoreCartUser = localStorage;
        })
        .catch((e) => {
          // this.error.push(e);
          console.log(e);
        });
    },
    returnHome() {
      this.$router.push({
        name: "Home",
      });
    },
    forgot(){
      this.$router.push({
        name: "forgotpass",
      });
    },
    getDataFavorites() {
      axios
        .get(this.$store.state.MainLink + "customer/favorite/get", {
          headers: {
            Authorization: localStorage.userToken,
          },
        })
        .then((response) => {
          this.$store.state.totalFavorites = response.data.object;
          console.log(this.$store.state.totalFavorites);
        })
        .catch((e) => {
          this.error.push(e);
          console.log(e);
        });
    },
    switchToAccount() {
      axios
        .get(this.$store.state.MainLink + "customer/account", {
          headers: {
            Authorization: localStorage.userToken,
          },
        })
        .then((response) => {
          this.$store.state.InfoPersonal = response.data.object;
          // console.log(this.$store.state.InfoPersonal);
        })
        .catch((e) => {
          this.error.push(e);
          console.log(e);
        });
    },
    register(){
       this.$router.push({
        name: "register",
      });
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
  /* background-image: url("https://photo2.tinhte.vn/data/attachment-files/2021/06/5511549_img103.jpg"); */
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
