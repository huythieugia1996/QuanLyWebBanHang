<template>
  <div class="uk-offcanvas-content">
    <main>
      <section class="uk-section uk-section-small">
        <div class="uk-container">
          <div class="uk-grid-medium" uk-grid>
            <div class="uk-width-1-1 uk-width-1-4@m tm-aside-column">
              <div
                class="
                  uk-card uk-card-default uk-card-small
                  tm-ignore-container
                "
                uk-sticky="offset: 90; bottom: true; media: @m;"
              >
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
                        <div>
                          <router-link to="setting">
                            <a class="uk-button uk-button-default uk-button-small">
                              <span class="uk-margin-xsmall-right" uk-icon="icon: cog; ratio: .75;"></span>
                              <span>Cài đặt</span></a>
                          </router-link>
                        </div>
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
              <div
                class="
                  uk-card uk-card-default uk-card-small
                  tm-ignore-container
                "
              >
                <header class="uk-card-header">
                  <h1 class="uk-h2">Thông tin cá nhân</h1>
                </header>
                <div class="uk-card-body">
                  <form class="uk-form-stacked">
                    <div class="uk-grid-medium uk-child-width-1-1" uk-grid>
                      <fieldset class="uk-fieldset">
                        <legend class="uk-h4">Liên lạc</legend>
                        <div
                          class="
                            uk-grid-small
                            uk-child-width-1-1
                            uk-child-width-1-2@s
                          "
                          uk-grid
                        > 
                          <div>
                            <label style="width: 100%">
                              <div class="uk-form-label">Tên đăng nhập</div>
                              <input
                                class="uk-input"
                                type="text"
                                v-model="dataUser.username"
                                disabled
                              />
                            </label>
                          </div>
                          <div>
                            <label style="width: 100%">
                              <div class="uk-form-label uk-form-label-required">Tên người dùng</div>
                              <input
                                class="uk-input"
                                type="text"
                                v-model="dataUser.fullname"
                              />
                            </label>
                          </div>
                          <div>
                            <label style="width: 100%">
                              <div class="uk-form-label">Email</div>
                              <input
                                class="uk-input"
                                type="tel"
                                v-model="dataUser.email"
                                disabled
                              />
                            </label>
                          </div>
                          <div>
                            <label style="width: 100%">
                              <div class="uk-form-label uk-form-label-required">Số điện thoại</div>
                              <input
                                class="uk-input"
                                type="tel"
                                v-model="dataUser.phone"
                              />
                            </label>
                          </div>
                          <!-- <div>
                            <label style="width: 100%">
                              <div class="uk-form-label">Ngày sinh</div>
                              <input
                                class="uk-input"
                                type="date"
                                value="1990-01-01"
                              />
                            </label>
                          </div> -->
                        </div>
                      </fieldset>
                      <fieldset class="uk-fieldset">
                        <legend class="uk-h4">Địa chỉ</legend>
                        <!-- <div class="uk-grid-small" uk-grid>
                          <div class="uk-width-1-1">
                            <label>
                              <div class="uk-form-label">Country</div>
                              <select class="uk-select">
                                <option>Choose the country</option>
                                <option value="RU">Russia</option>
                              </select>
                            </label>
                          </div>
                        </div> -->
                        <div class="uk-grid-large" uk-grid>
                          <div class="uk-width-expand">
                            <label style="width: 100%">
                              <div class="uk-form-label uk-form-label-required">Địa chỉ cụ thể</div>
                              <input
                                class="uk-input"
                                type="text"
                                v-model="dataUser.address"
                              />
                            </label>
                          </div>
                        </div>
                        <!-- <div class="uk-grid-small" uk-grid>
                          <div class="uk-width-expand">
                            <label style="width: 100%">
                              <div class="uk-form-label">Street</div>
                              <input
                                class="uk-input"
                                type="text"
                                v-model="this.$store.state.InfoPersonal.address"
                              />
                            </label>
                          </div>
                          <div class="uk-width-expand">
                            <label style="width: 100%">
                              <div class="uk-form-label">House</div>
                              <input
                                class="uk-input"
                                type="text"
                                v-model="this.$store.state.InfoPersonal.address"
                              />
                            </label>
                          </div>
                        </div> -->
                      </fieldset>
                    </div>
                    <br>
                    <p class="text-center" style="color: red">{{checkNull}}</p>
                  </form>
                </div>
                <div class="uk-card-footer uk-text-center">
                  <button
                    class="uk-button uk-button-primary"
                    @click="updateUser(dataUser)"
                  > 
                  <b-icon icon="arrow-clockwise"></b-icon>
                    Cập nhật tài khoản
                  </button>
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
  name: "personal",
  data() {
    return {
      dataUser: {},
      checkNull: ""
    };
  },
  created() {
    this.getDataAccount();
  },
  methods: {
    getDataUser() {
      this.dataUser = this.$store.state.InfoPersonal;
      // console.log(this.dataUser);
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
    updateUser(dataUser) {
      if(dataUser.fullname == '' || dataUser.phone == '' || dataUser.address == ''){
        this.checkNull = "Không được bỏ trống trường thông tin!"
      }else{
        this.checkNull = ""
      let item = {
        fullname: dataUser.fullname,
        phone: dataUser.phone,
        address: dataUser.address,
      };

      axios
        .put(this.$store.state.MainLink + "customer/account/update", item, {
          headers: {
            Authorization: localStorage.userToken,
          },
        })
        .then((response) => {
          // console.log(response.data.object);
          this.$store.state.InfoPersonal = response.data.object;
          this.dataUser = response.data.object;

          this.$toasted.show("Cập nhật thông tin thành công !", {
            type: "success",
            duration: 2000,
          });
        })
        .catch((e) => {
          console.log(e);
          this.$toasted.show("Cập nhật thất bại !", {
            type: "error",
            duration: 2000,
          });
        });
      }


    },
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
          this.error.push(e);
          console.log(e);
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
  },
};
</script>

<style>
</style>