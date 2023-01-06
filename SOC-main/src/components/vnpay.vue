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
                      <h1 class="uk-article-title">Thanh toán hoá đơn</h1>
                    </header>
                    <section
                      class="uk-width-1-1 uk-width-expand@m"
                      style="width: 68%; float: left"
                    >
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
                        <table class="table table-custom-border">
                          <tbody>
                            <tr
                              style="border-bottom: 1px dashed silver"
                              v-for="item in DetailsOrder"
                              :key="item.id"
                            >
                              <td
                                class="
                                  custom-font-size-td
                                  size-img-cart-product
                                "
                                style="width: 15%"
                              >
                                <img :src="item.photo" alt="" width="50px" />
                              </td>
                              <td>
                                {{ item.productName.substr(6, 25) }} ...
                                <br />
                                Số lượng : {{ item.quantity }}
                              </td>
                              <td
                                class="custom-size-price-total"
                                style="vertical-align: middle"
                              >
                                {{ formatPrice(item.price) }} đ
                              </td>
                            </tr>
                            <tr>
                              <th style="width: 20%">Thành tiền</th>
                              <td></td>
                              <td
                                class="custom-size-price-total"
                                style="vertical-align: middle; color: red"
                              >
                                {{ formatPrice(sumTotal) }} đ
                              </td>
                            </tr>
                          </tbody>
                        </table>
                      </article>
                    </section>
                    <aside
                      class="uk-width-1-4 uk-visible@m tm-aside-column"
                      style="width: 28%; float: right"
                    >
                      <section
                        class="uk-card uk-card-default uk-card-small"
                        uk-sticky="offset: 90; bottom: true;"
                      >
                        <nav>
                          <ul
                            class="uk-nav uk-nav-default tm-nav"
                            style="height: 200px"
                          >
                            <li
                              class="uk-active"
                              style="border-bottom: 1px solid silver"
                            >
                              <a>Địa chỉ giao hàng</a>
                            </li>
                            <li style="margin-top: 15px">
                              <div class="font-li">{{ dataUser.fullname }}</div>
                            </li>
                            <li style="margin-top: 5px">
                              <div class="font-li">{{ dataUser.email }}</div>
                            </li>
                            <li style="margin-top: 5px">
                              <div class="font-li">
                                {{ dataUser.address }}
                              </div>
                            </li>
                            <li style="margin-top: 5px">
                              <div class="font-li">
                                Số điện thoại: {{ dataUser.phone }}
                              </div>
                            </li>
                          </ul>
                        </nav>
                      </section>
                    </aside>
                    <section
                      class="uk-width-1-1 uk-width-expand@m"
                      style="width: 68%; float: left; margin-top: 25px"
                    >
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
                        <div>Hình thức thanh toán</div>
                        <div class="form-check">
                          <input
                            class="form-check-input"
                            type="radio"
                            name="flexRadioDefault"
                            id="flexRadioDefault1"
                            value="1"
                            v-model="typeOfPay"
                          />
                          <label
                            class="form-check-label"
                            for="flexRadioDefault1"
                          >
                            Thanh toán khi nhận hàng
                          </label>
                        </div>
                        <div class="form-check">
                          <input
                            class="form-check-input"
                            type="radio"
                            name="flexRadioDefault"
                            id="flexRadioDefault2"
                            v-model="typeOfPay"
                            value="2"
                          />
                          <label
                            class="form-check-label"
                            for="flexRadioDefault2"
                          >
                            Thanh toán trực tuyến (qua cổng VnPay)
                          </label>
                        </div>
                      </article>
                      <button
                        type="button"
                        class="btn btn-outline-secondary btn-danger-custom"
                        @click="test()"
                      >
                        Hủy
                      </button>
                      <button
                        type="button"
                        class="btn btn-outline-danger btn-danger-custom"
                        @click="addToOrder()"
                      >
                        Đặt mua
                      </button>
                      <em class="p-custom" style="margin-top: 5px">
                        (Vui lòng kiểm tra lại đơn hàng trước khi thanh toán)
                      </em>
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
      DetailsOrder: [],
      sumTotal: 0,
      typeOfPay: "1",
      orderDetails: [],
    };
  },
  created() {
    // this.getBlog();
    this.getOrderUser();
    this.sumPrice();
    this.getDataUser();
    this.getDataAccount();
    // console.log(localStorage.userToken);
  },
  methods: {
    formatPrice(value) {
      let val = (value / 1).toFixed(0).replace(".", ",");
      return val.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ".");
    },
    getDataUser() {
      this.dataUser = {
        address: this.$store.state.InfoPersonal.address,
        email: this.$store.state.InfoPersonal.email,
        fullname: this.$store.state.InfoPersonal.fullname,
        phone: this.$store.state.InfoPersonal.phone,
      };
      // console.log(this.dataUser);
      // console.log(this.sumTotal);
    },
    getOrderUser() {
      this.DetailsOrder = this.$store.state.StoreCart;
      this.orderDetails = this.$store.state.StoreCart.map((detail) => {
        return {
          productId: detail.productId,
          quantity: detail.quantity,
          price: detail.price,
          discount: 0,
        };
      });
      // console.log(this.orderDetails);
      // console.log(this.DetailsOrder);
      // for (let i = 0; i < this.DetailsOrder.length; i++) {

      // }
    },
    sumPrice() {
      for (let i = 0; i < this.DetailsOrder.length; i++) {
        this.sumTotal +=
          this.DetailsOrder[i].price * this.DetailsOrder[i].quantity;
      }
      return this.sumTotal;
    },
    addToOrder() {
      let item = {
        sumprice: this.sumTotal,
        customer: this.dataUser,
        orderDetails: this.orderDetails,
      };
      
      axios
        .post(this.$store.state.MainLink + "customer/orders/new", item, {
          headers: {
            Authorization: this.$store.state.tokenUser,
          },
        })
        .then((response) => {
          let infoCart = {
            OrderInfo: "thanh toán máy tính",
            ordersId: response.data.object.id,
            returnURL: "http://150.95.105.29/vnPayResult"
            // returnURL: "http://localhost:8080/vnPayResult"
          };
          this.$store.state.orderDetails = response.data.object.id;
          localStorage.orderDetails = response.data.object.id;
          // console.log(infoCart);
          if (this.typeOfPay == "1") {
            this.$toasted.show("Đặt hàng thành công !", {
              type: "success",
              duration: 2000,
            });
            this.$router.push({
              name: "resultorder",
            })
          } else {
            axios
              .post(
                this.$store.state.MainLink + "customer/pay/getpayurl",
                infoCart
              )
              .then((response) => {
                window.location = response.data.object;
              });
          }
        })
        .catch((e) => {
          this.$toasted.show("Đặt hàng thất bại !", {
            type: "error",
            duration: 2000,
          });
          console.log(e);
        });
    },
    test() {
      this.$router.push({
        name: "Home",
      });
      // this.$router.go("https://www.google.com/");
      // window.location = "https://www.google.com/";
    },
    getDataAccount() {
      
      if(localStorage.userToken){
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
          // console.log(response.data.object);
        })
        .catch((e) => {
          // this.error.push(e);
          console.log(e);
        });
      }      
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
.btn-danger-custom {
  margin: 15px;
  width: 45%;
  /* text-align: center */
}
.p-custom {
  color: gray;
  font-size: 14px;
  margin-top: 5px;
  margin: 0px;
}
</style>