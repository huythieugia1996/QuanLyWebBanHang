<template>
  <div class="c-app flex-row align-items-center">
    <CContainer>
      <CRow class="justify-content-center">
        <CCol md="5">
          <CCardGroup>
            <CCard class="p-4">
              <CCardBody>
                <CForm>
                  <h1>Đăng nhập</h1>
                  <p class="text-muted">Đăng nhập vào tài khoản quản trị viên</p>
                  <CInput
                    placeholder="Tài khoản"
                    autocomplete="username email"
                    v-model="username"
                    required
                  >
                    <template #prepend-content
                      ><CIcon name="cil-user"
                    /></template>
                  </CInput>
                  <CInput
                    placeholder="Mật khẩu"
                    type="password"
                    autocomplete="curent-password"
                    v-model="password"
                    required
                  >
                    <template #prepend-content
                      ><CIcon name="cil-lock-locked"
                    /></template>
                  </CInput>
                  <div style="text-align: center; color: red">{{alertError}}</div>
                  <CRow style="margin: 10px 10px 10px 10px">
                    <CCol col="6" class="text-left">
                      <CButton
                        color="primary"
                        class="px-4"
                        style="margin: 10px 10px 10px 10px"
                        @click="LoginJWT()"
                        >Đăng nhập</CButton
                      >
                    </CCol>
                    <CCol col="6" class="text-right text-center">
                      <CButton color="link" class="px-0" style="text-decoration: none;margin-top: 10px;" to="/pages/register">Quên mật khẩu</CButton>
                      <!-- <CButton color="link" class="d-lg-none"
                        >Register now!</CButton
                      > -->
                    </CCol>
                  </CRow>
                </CForm>
              </CCardBody>
            </CCard>
          </CCardGroup>
        </CCol>
      </CRow>
    </CContainer>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "Login",
  data() {
    return {
      username: "",
      password: "",
      alertError: "",
    };
  },
  methods: {
    LoginJWT() {
      if(this.username == '' || this.password == ''){
        this.alertError = "Không được để trống tài khoản và mật khẩu!"
      }else{
      axios
        .post(this.$store.state.MainLink + "authentication/login", {
          username: this.username,
          password: this.password,
        })
        .then((response) => {
          localStorage.usertoken =
            response.data.tokenType + " " + response.data.accessToken;
          localStorage.username = response.data.username;
          this.$store.state.userToken =
            localStorage.usertoken
          this.$store.state.userName = localStorage.username;

          if (response.data.roles[0] == "Director") {
            this.$router.push({
              path: "/dashboard",
            });
          } else if (response.data.roles[0] == "Staff") {
            this.$router.push({
              path: "/dashboard",
            });
          } else {
            console.log("Tài khoản hoặc mật khẩu không chính xác !");
          }
        })
        .catch((e) => {
          this.alertError = "Sai tài khoản hoặc mật khẩu!"
          this.error.push(e);
        });
      }

    },

    getDataUser() {},
    direcToDash() {
      this.$router.push({
        path: "/",
      });
    },
  },
};
</script>
<style scoped>
.form-group {
  margin: 10px;
}
</style>
