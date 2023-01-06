<template>
  <div class="uk-offcanvas-content">
    <main>
      <section class="uk-section uk-section-small">
        <div class="uk-container">
          <div class="uk-grid-medium uk-child-width-1-1" uk-grid>
            <div class="uk-text-center">
              <ul class="uk-breadcrumb uk-flex-center uk-margin-remove">
                <li>
                  <router-link to="/" style="text-decoration: none">
                    <a>Trang chủ</a>
                  </router-link>
                </li>
                <li><a>Giỏ hàng</a></li>
                <li><a>Thanh toán</a></li>
              </ul>
            </div>
            <div>
              <div class="uk-grid-medium uk-child-width-1-1" uk-grid>
                <section>
                  <article
                    class="
                      uk-card uk-card-default uk-card-body uk-article
                      tm-ignore-container
                    "
                  >
                    <header class="uk-text-center">
                      <h1 class="uk-article-title">Thông tin giao hàng</h1>
                    </header>
                    <section class="uk-width-1-1 uk-width-expand@m">
                      <article
                        class="
                          uk-card
                          uk-card-default
                          uk-card-small
                          uk-card-body
                          uk-article
                          tm-ignore-container
                        "
                      >
                        <div>
                          <div class="form-group col-md-12 form-margin-top">
                            <label class="label-custom">Họ tên</label>
                            <input
                              type="text"
                              class="form-control-custom form-control"
                              placeholder="Nhập họ tên"
                              v-model="dataUser.fullname"
                            />
                          </div>
                          <div class="form-group col-md-12 form-margin-top">
                            <label class="label-custom">Email</label>
                            <input
                              type="email"
                              class="form-control-custom form-control"
                              placeholder="Nhập email của bạn"
                              v-model="dataUser.email"
                            />
                          </div>
                          <div class="form-group col-md-12 form-margin-top">
                            <label class="label-custom">Số điện thoại</label>
                            <input
                              class="form-control form-control-custom"
                              placeholder="Nhập số điện thoại"
                              v-model="dataUser.phone"
                            />
                          </div>
                          <div class="form-group form-margin-top" style="height: 270px;">
                            <label class="label-custom">Địa chỉ</label>
                            <input
                              type="text"
                              class="form-control form-control-custom"
                              id="inputAddress2"
                              placeholder="Đường Chính Kinh, số nhà ABC123"
                              v-model="dataUser.address"
                            />
                          </div>
                          <div class="form-group col-md-12 form-margin-top">
       
                          </div>
                          <form style="text-align: center">
                            <p style="color: red">{{validateForm}}</p>
                            <button
                              type="button"
                              class="btn btn-outline-danger btn-size"
                              @click="cart()"
                            >
                              Huỷ
                            </button>
                            <button
                              type="button"
                              class="btn btn-outline-primary btn-size"
                              @click="payment()"
                            >
                              Lưu
                            </button>
                          </form>
                        </div>
                      </article>
                    </section>
                  </article>
                </section>
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
  props: {
    item: Number,
  },
  data() {
    return {
      getData: "",
      dataUser: {},
      validateForm: "",
      checkPay: true,
    };
  },
  created() {
    this.getDataAccount();
    this.getDataUser();
  },
  methods: {
    getDataUser() {
      this.dataUser = this.$store.state.InfoPersonal;
    },
    formatPrice(value) {
      let val = (value / 1).toFixed(2).replace(".", ",");
      return val.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ".");
    },
    payment() {
      if(this.dataUser.fullname == '' || this.dataUser.email == '' || this.dataUser.phone == '' || this.dataUser.address == ''){
        this.validateForm = "Vui lòng không để trống thông tin !"
      }else{
        this.$router.push({
        name: "vnpay",
      });
      }
    },
    cart() {
      this.$router.push({
        name: "cart",
        // params: { item: id },
      });
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
          this.getTotalCart();
          console.log(response.data.object);
        })
        .catch((e) => {
          // this.error.push(e);
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

<style scope>
.section-custom {
  margin-top: 5%;
}
td.custom-size-price-total {
  width: 20%;
}
.label-custom {
  float: left;
  width: 20%;
  margin: 5px 5px 5px 5px;
  margin-bottom: 25px;
  font-weight: 600;
}
input.form-control-custom {
  width: 75%;
  float: left;
  margin-bottom: 25px;
  /* border: none;
  border-bottom: 1px solid silver; */
}
.btn-size {
  width: 150px;
  margin: 15px;
}
</style>