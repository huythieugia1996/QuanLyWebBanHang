<template>
  <main class="login-background" style="z-index: 99">
    <!-- <div v-if="checkEmail != 0">
      <loadingDot style="margin-left: -25px; margin-top: -150px"></loadingDot>
    </div> -->

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
              <i class="ms-Icon ms-Icon--Contact"></i>
              <input
                v-model="email"
                type="email"
                id="email"
                placeholder="Nhập vào đây email của bạn"
              />
              <div style="text-align: center;color: red;margin-top: 15px;">
                {{ error }}
              </div>
              
            </div>
            
            <div class="submit-button">
              <button type="submit" @click="forgotPass()" v-if="checkEmail == 0" style="width: 35%;margin: 15px;">Gửi xác nhận</button>
              <button type="submit" @click="returnHome()" v-if="checkEmail == 0" style="width: 35%;margin: 15px;">Hủy</button>
              <button
                class="abled"
                type="submit"
                @click="forgotPass()"
                disabled
                v-else
              >
                <div class="spinner-border text-light" role="status">
                  <span class="sr-only"></span>
                </div>
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </main>
</template>

<script>
import axios from "axios";
// import loadingDot from "../containers/loadingDot.vue";
export default {
  name: "User",
  // components: {
  //   loadingDot,
  // },
  data() {
    return {
      email: "",
      error: "",
      checkEmail: 0,
    };
  },
  methods: {
    returnHome() {
      this.$router.push({
        name: "login",
      });
    },
    callFunction: function () {
      var v = this;
      setTimeout(function () {
        v.callFunctionTotal();
      }, 0);
    },
    forgotPass() {
      this.checkEmail = 1;
      this.error = ""
      console.log(this.email);
      axios
        .put(
          this.$store.state.MainLink +
            "customer/account/resetpass?email=" +
            this.email
        )
        .then((response) => {
          console.log(response);
          // this.callFunction();
          this.checkEmail = 1;
          this.$router.push({
            name: "sendmail",
          });
        })
        .catch((e) => {
          this.checkEmail = 0;
          console.log(e);
          this.error = "Email không tồn tại hoặc sai định dạng!"
        });
    },
    checkForm:function(e) {
      this.errors = [];
      if(!this.email) {
        this.errors.push("Email bắt buộc phải nhập");
      } else if(!this.validEmail(this.email)) {
        this.errors.push("Email sai định dạng");        
      }
      if(!this.errors.length) return true;
      e.preventDefault();
    },
    // validEmail:function(email) {
    //   var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    // return re.test(email);
    // }
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
