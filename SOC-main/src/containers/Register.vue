<template>
  <main class="login-background" style="z-index: 99">
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
            <div>
              <i class="ms-Icon ms-Icon--Contact"></i>
              <input v-model="username" type="text" placeholder="Tài khoản đăng nhập"/>
              <div style="text-align: center;color: red;margin-top: 10px;">
                {{errorsUsername}}
              </div>
              
            </div>

            <div style="display: flex; margin-top: 25px;">
              <div class="username">
                <i class="ms-Icon ms-Icon--Lock"></i>
                <input v-model="fullname" type="text" placeholder="Tên người dùng"/>
                <div style="text-align: center;color: red;margin-top: 10px;">
                  {{errorsFullname}}
                </div>
              </div>
              <div class="username" style="margin-left: 10px">
                <i class="ms-Icon ms-Icon--Lock"></i>
                <input v-model="email" type="email" id="email" placeholder="Email" />
                <div style="text-align: center;color: red;margin-top: 10px;">
                  {{errorsEmail}}
                </div>
              </div>
            </div>

            <div style="display: flex">
              <div class="username">
                <i class="ms-Icon ms-Icon--Contact"></i>
                <input
                  v-model="password"
                  type="password"
                  placeholder="Mật khẩu"
                />
                <div style="text-align: center;color: red;margin-top: 10px;">
                  {{errorsPassword}} 
                </div>
                
              </div>
              <div class="username" style="margin-left: 10px">
                <i class="ms-Icon ms-Icon--Contact"></i>
                <input
                  v-model="repassword"
                  type="password"
                  placeholder="Xác nhận mật khẩu"
                />
                <div style="text-align: center;color: red;margin-top: 10px;">
                  {{errorsRePassword}} 
                </div>
                <div v-if="password != repassword" style="text-align: center;color: red;margin-top: 15px;">
                  Xác nhận mật khẩu không chính xác!
                </div>
              </div>              
            </div>

            <div class="submit-button">
              <button type="submit" @click="checkEmail()" v-if="checkReg == 0" style="width: 35%;margin: 15px;">Đăng ký</button>
              <button type="submit" @click="returnHome()" v-if="checkReg == 0" style="width: 35%;margin: 15px;">Hủy</button>
              <button
                class="abled"
                type="submit"
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

export default {
  name: "User",
  data() {
    return {
      username: "",
      password: "",
      fullname: "",
      email: "",
      repassword: "",
      errorsEmail: "",
       errorsUsername: "",
        errorsFullname: "",
         errorsPassword: "",
          errorsRePassword: "",
      checkReg: 0,
          // errorsPassword: "",
    };
  },
  methods: {
    returnHome() {
      this.$router.push({
        name: "login",
      });
    },
    checkEmail:function() {
      this.errorsEmail = this.errorsFullname = this.errorsUsername = this.errorsPassword = this.errorsRePassword = "";
      if(!this.email) {
        this.errorsEmail="Email không được bỏ trống";
      } else if(!this.validEmail(this.email)) {
        this.errorsEmail="Email không đúng định dạng";        
      }

      if(!this.username){
          this.errorsUsername="Tài khoản không được bỏ trống";
      } else if (!this.validUserName(this.username)) {
          this.errorsUsername="Tài khoản chỉ được chứa chữ hoặc số viết liền không dấu";
      }

      if(!this.fullname){
          this.errorsFullname="Tên người dùng không được bỏ trống";
      }

      if(!this.password){
          this.errorsPassword="Mật khẩu không được bỏ trống";
      }

      if(!this.repassword){
          this.errorsRePassword="Nhập lại mật khẩu không được bỏ trống";
      }

      if( this.errorsEmail != "Email không được bỏ trống" && this.errorsFullname !="Tên người dùng không được bỏ trống" && this.errorsUsername !="Tài khoản không được bỏ trống" && this.errorsPassword !="Mật khẩu không được bỏ trống" && this.errorsRePassword !="Nhập lại mật khẩu không được bỏ trống" ){
        this.createUser();
      }else{
        console.error("Sai")
      }
      // if(!this.errors.length) return true;
      // e.preventDefault();
    },
    validEmail:function(email) {
      var re = /^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/
      // var re = /^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|"(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21\x23-\x5b\x5d-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])*")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21-\x5a\x53-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])+)\]/
      return re.test(email);
    },
    validUserName:function(username) {
      var re = /^[a-zA-Z0-9]*$/
      // var re = /^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|"(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21\x23-\x5b\x5d-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])*")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21-\x5a\x53-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])+)\]/
      return re.test(username);
    },
    createUser() {
      console.log("Chạy lỗi")
      let item = {
        username: this.username,
        password: this.password,
        fullname: this.fullname,
        email: this.email,
      };
      axios
        .put(this.$store.state.MainLink + "customer/account/create", item)
        .then((response) => {
          console.log(response);
          this.$toasted.show("Đăng ký thành công !", {
            type: "success",
            duration: 2000,
          });
          this.checkReg =  1
          this.$router.push({
            name: "login",
          });
        })
        .catch((e) => {
          console.log(e);
          this.$toasted.show("Đăng ký thất bại !", {
            type: "error",
            duration: 2000,
          });
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
  /* max-width: 440px; */
  width: 600px;
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
.username{
  width: 50%;
}
</style>
