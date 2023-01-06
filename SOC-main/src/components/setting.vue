<template>
  <div class="uk-offcanvas-content">
        <main>
            <section class="uk-section uk-section-small">
                <div class="uk-container">
                    <div class="uk-grid-medium" uk-grid>
                        <div class="uk-width-1-1 uk-width-1-4@m tm-aside-column">
                            <div class="uk-card uk-card-default uk-card-small tm-ignore-container" uk-sticky="offset: 90; bottom: true; media: @m;">
                                <div class="uk-card-header">
                                    <div class="uk-grid-small uk-child-width-1-1" uk-grid>
                                        <section>
                                        <div
                                            class="
                                            uk-width-1-3
                                            uk-width-1-4@s
                                            uk-width-1-2@m
                                            uk-margin-auto
                                            uk-visible-toggle
                                            uk-position-relative
                                            uk-border-circle
                                            uk-overflow-hidden
                                            uk-light
                                            "
                                        >
                                            <img class="uk-width-1-1" src="images/pngegg.png" />
                                            <a
                                            class="
                                                uk-link-reset
                                                uk-overlay-primary
                                                uk-position-cover
                                                uk-hidden-hover
                                            "
                                            href="#"
                                            >
                                            <div class="uk-position-center">
                                                <span uk-icon="icon: camera; ratio: 1.25;"></span>
                                            </div>
                                            </a>
                                        </div>
                                        </section>
                                        <div class="uk-text-center">
                                        <div class="uk-h4 uk-margin-remove">
                                            {{ this.$store.state.InfoPersonal.fullname }}
                                        </div>
                                        <div class="uk-text-meta">
                                            {{ this.$store.state.InfoPersonal.email }}
                                        </div>
                                        </div>
                                        <div>
                                        <div class="uk-grid-small uk-flex-center" uk-grid>
                                            <div><a class="uk-button uk-button-default uk-button-small"><span class="uk-margin-xsmall-right" uk-icon="icon: cog; ratio: .75;"></span><span>Cài đặt</span></a></div>
                                            <div>
                                            <button
                                                class="uk-button uk-button-default uk-button-small"
                                                href="#"
                                                title="Log out"
                                                @click="clearData"
                                            >
                                                <span uk-icon="icon: sign-out; ratio: .75;"></span>
                                            </button>
                                            </div>
                                        </div>
                                        </div>
                                    </div>
                                </div>
                                <div>
                                    <nav>
                                        <ul class="uk-nav uk-nav-default tm-nav">
                                        <li>
                                            <router-link to="account">
                                            <a>Đơn hàng</a>
                                            </router-link>
                                        </li>
                                        <li>
                                            <router-link to="favorites">
                                            <a>Sản phẩm yêu thích</a>
                                            </router-link>
                                        </li>
                                        <li class="uk-active">
                                            <router-link to="personal">
                                            <a>Thông tin cá nhân</a>
                                            </router-link>
                                        </li>
                                        </ul>
                                    </nav>
                                </div>
                            </div>
                        </div>
                        <div class="uk-width-1-1 uk-width-expand@m">
                            <div class="uk-card uk-card-default uk-card-small tm-ignore-container">
                                <header class="uk-card-header">
                                    <h1 class="uk-h2">Cá nhân</h1>
                                </header>
                                <div class="uk-card-body">
                                    <div class="uk-form-stacked">
                                        <div class="uk-grid-medium uk-child-width-1-1" uk-grid>
                                            <fieldset class="uk-fieldset">
                                                <legend class="uk-h4">Đổi mật khẩu</legend>
                                                <div class="uk-grid-small uk-child-width-1-1" uk-grid>
                                                    <div>
                                                        <label style="width: 100%">
                                                            <div class="uk-form-label">Mật khẩu cũ</div>
                                                            <input class="uk-input uk-form-width-large" type="text" style="width: 100%" v-model="formData.oldPassWord">
                                                        </label>
                                                    </div>
                                                    <div>
                                                        <label style="width: 100%">
                                                            <div class="uk-form-label">Mật khẩu mới</div>
                                                            <input class="uk-input uk-form-width-large" type="text" style="width: 100%" v-model="formData.newPassWord">
                                                        </label>
                                                    </div>
                                                    <div>
                                                        <label style="width: 100%">
                                                            <div class="uk-form-label">Nhập lại mật khẩu</div>
                                                            <input class="uk-input uk-form-width-large" type="text" style="width: 100%" v-model="formData.reNewPassWord">
                                                        </label>
                                                        <i style="color: red" v-if="formData.newPassWord != formData.reNewPassWord">Nhập lại mật khẩu không chính xác !</i>
                                                    </div>
                                                    <div class="uk-text-center">
                                                        <button class="uk-button uk-button-primary" @click="updatePass()" v-if="formData.newPassWord == formData.reNewPassWord && formData.newPassWord != '' && formData.oldPassWord != '' && formData.reNewPassWord != ''">
                                                            Cập nhật mật khẩu
                                                        </button>
                                                        <button class="uk-button uk-button-primary" disabled v-else>
                                                            Cập nhật mật khẩu
                                                        </button>
                                                    </div>
                                                </div>
                                            </fieldset>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </main>
    </div>
</template>

<script>
import axios from "axios";


export default {
    name: "setting",
    data() {
    return {
      dataUser: {},
      checkNull: "",
      showError: "",
      formData: {
          oldPassWord: "",
          newPassWord: "",
          reNewPassWord: "",
      },
      errorPass: "",
      allError: ""
    };
  },
    created() {
        this.getDataAccount();
        // this.showAlert()
    },
    methods: {
        getDataAccount() {
        
        axios
            .get(this.$store.state.MainLink + "customer/account", {
            headers: {
                Authorization: localStorage.userToken,
            },
            })
            .then((response) => {
            this.$store.state.userName = response.data.object.fullname;
            this.$store.state.tokenUser = localStorage.userToken
            this.$store.state.InfoPersonal = response.data.object;
            this.getDataUser();
            this.getTotalCart();
            // console.log(response.data.object);
            })
            .catch((e) => {
            // this.error.push(e);
            console.log(e);
            });
        },
        getDataUser() {
            this.dataUser = this.$store.state.InfoPersonal;
        },
        clearData() {
            this.$store.state.tokenUser = "";
            this.$store.state.totalCart = 0;
            this.$store.state.InfoPersonal = {};
            this.$store.state.userName = "";
            this.$store.state.CompareCart = [];
            this.$store.state.StoreCart = [];
            window.localStorage.clear();
            this.$router.push({
                name: "login",
            });
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
            this.$store.state.StoreCart = response.data.object;
            })
            .catch((e) => {
            console.log(e);
            });
        },
        showAlert() {
            const options = {title: 'Thông báo', size: 'sm'}
            this.$dialogs.alert(this.allError, options)
            .then(res => {
                console.log(res) // {ok: true|false|undefined}
            })
        },
        updatePass(){
            console.log(this.formData)
            axios
            .put(this.$store.state.MainLink + "customer/account/updatePass",this.formData, {
            headers: {
                Authorization: localStorage.userToken,
            },
            })
            .then((response) => {
                console.log(response)
                this.showError = "Đổi mật khẩu thành công !"
                this.showAlert();
            })
            .catch((e) => {
                console.error(e.response.data.errorMsg)
                if (e.response.data.validateDetails) {
                    console.error(e.response.data)
                    this.errorPass = e.response.data.errorMsg;
                    this.showError = e.response.data.validateDetails.newPassWord;
                    // this.alert(this.errorPass.concat('\n ', this.showError))
                    // this.allError = this.errorPass + this.showError;
                    // this.$alert = this.errorPass + this.showError
                    // alert(this.errorPass.concat('\n ', this.showError))
                    this.allError = this.errorPass.concat(', ', this.showError);
                    // document.write(this.allError + "<br/>");
                }else{
                    console.error(e.response.data)
                    this.errorPass = e.response.data.errorMsg;
                    this.allError = this.errorPass
                    // document.write(this.allError + "<br/>");
                }

            this.showAlert();
            });
        }
    }
}
</script>

<style>

</style>