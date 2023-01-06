<template>
  <div class="uk-offcanvas-content" style="min-height: 700px">
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
                <li><span>Giỏ hàng</span></li>
              </ul>
              <h1 class="uk-margin-small-top uk-margin-remove-bottom">
                Giỏ hàng
              </h1>
            </div>
            <div>
              <div class="uk-grid-medium" uk-grid>
                <div class="uk-width-1-1 uk-width-expand@m">
                  <div
                    class="
                      uk-card uk-card-default uk-card-small
                      tm-ignore-container
                    "
                  >
                    <header
                      class="
                        uk-card-header
                        uk-text-uppercase
                        uk-text-muted
                        uk-text-center
                        uk-text-small
                        uk-visible@m
                      "
                    >
                      <div class="uk-grid-small uk-child-width-1-2" uk-grid>
                        <div>Sản phẩm</div>
                        <div>
                          <div
                            class="uk-grid-small uk-child-width-expand"
                            uk-grid
                          >
                            <div>Giá</div>
                            <div>Giảm giá</div>
                            <div class="tm-quantity-column">Số lượng</div>
                            <div>Tổng</div>
                            <div class="uk-width-auto">
                              <div style="width: 20px"></div>
                            </div>
                          </div>
                        </div>
                      </div>
                    </header>
                    <div v-if="DetailsCart.length > 0">
                      <div
                        class="uk-card-body"
                        v-for="(item, index) in DetailsCart"
                        :key="index"
                      >
                        <div
                          class="
                            uk-grid-small
                            uk-child-width-1-1
                            uk-child-width-1-2@m
                            uk-flex-middle
                          "
                          uk-grid
                        >
                          <!-- Product cell-->
                          <div>
                            <div class="uk-grid-small" uk-grid>
                              <div class="uk-width-1-3">
                                <div class="tm-ratio tm-ratio-4-3">
                                  <a class="tm-media-box">
                                    <figure class="tm-media-box-wrap">
                                      <img
                                        :src="item.photo"
                                        alt="Ảnh của sản phẩm"
                                      />
                                    </figure>
                                  </a>
                                </div>
                              </div>
                              <div
                                class="uk-width-expand"
                                style="font-size: 14px"
                              >
                                <div class="uk-text-meta">Laptop</div>
                                <a class="uk-link-heading">{{
                                  item.productName
                                }}</a>
                                <div class="uk-text-meta">
                                  Màu sắc: {{ item.colorName }}
                                </div>
                              </div>
                            </div>
                          </div>
                          <!-- Other cells-->
                          <div>
                            <div
                              class="
                                uk-grid-small
                                uk-child-width-1-1
                                uk-child-width-expand@s
                                uk-text-center
                              "
                              uk-grid
                              style="font-size: 14px"
                            >
                              <div>
                                <div class="uk-text-muted uk-hidden@m">Giá</div>
                                <div style="">
                                  {{ formatPrice(item.price) }} đ
                                </div>
                              </div>
                              <div>
                                <div class="uk-text-muted uk-hidden@m">
                                  Giảm giá
                                </div>
                                <div>-{{ formatPrice(item.discount) }} đ</div>
                              </div>
                              <div class="tm-cart-quantity-column">
                                <!-- <a onclick="increment(-1, 'product-1')" uk-icon="icon: minus; ratio: .75" v-if="item.quantity > 0"></a> -->
                                <input
                                  class="uk-input tm-quantity-input"
                                  id="product-1"
                                  type="number"
                                  maxlength="1"
                                  v-model="item.quantity"
                                  min="1"
                                  max="5"
                                  style="height: 30px"
                                  oninput="this.value = Math.abs(this.value)"
                                />
                                <!-- <a onclick="increment(+1, 'product-1')" uk-icon="icon: plus; ratio: .75"></a> -->
                                <i
                                  v-if="item.quantity > 5"
                                  class=""
                                  style="color: red"
                                >
                                  Mua số lượng lớn vui lòng liên hệ shop để được
                                  nhận giá khác!
                                </i>
                                <br />
                                <span
                                  v-if="left[item.productId] > 0"
                                  style="opacity: 0.75"
                                >
                                  <strong>còn lại: </strong>
                                  <em>{{ left[item.productId] }}</em></span
                                >
                              </div>

                              <div>
                                <div class="uk-text-muted uk-hidden@m">
                                  Tổng
                                </div>
                                {{
                                  formatPrice(
                                    (totalPrice.value =
                                      (item.price - item.discount) *
                                      item.quantity)
                                  )
                                }}
                                đ
                                <!-- <div>$1599.00</div> -->
                              </div>
                              <div class="uk-width-auto@s">
                                <a
                                  class="uk-text-danger"
                                  uk-tooltip="Remove"
                                  uk-toggle="target: #review0"
                                  @click="setIndex(index, item.id)"
                                >
                                  <span uk-icon="close"></span>
                                </a>
                              </div>
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>

                    <div
                      class="text-center"
                      style="margin: 15px 15px 15px 15px"
                      v-else
                    >
                      <img
                        src="images/emptycart.png"
                        alt=""
                        width="150px"
                        style="margin-bottom: 25px"
                      />
                      <p>Oh ! Có vẻ như bạn chưa có sản phẩm nào cần mua.</p>
                      <p>Hãy tiếp tục mua sắm nào !</p>
                      <!-- <button
                          class="btn-custom-back btn btn-outline-primary"
                          @click="backToCategory()"
                        >
                          Các sản phẩm khác
                        </button> -->
                    </div>
                    <div class="uk-card-footer">
                      <span class="uk-margin-small-right">
                        <i
                          >Để có thể xem lại hóa đơn thanh toán của mình, vui
                          lòng <a><strong>đăng nhập</strong></a> vào hệ thống
                          !</i
                        >
                      </span>
                      <!-- <div class="uk-inline"><a class="uk-form-icon uk-form-icon-flip" href="#" uk-icon="arrow-right"></a>
                            <input class="uk-input uk-form-width-small" type="text">
                          </div> -->
                    </div>
                  </div>
                </div>
                <div class="uk-width-1-1 tm-aside-column uk-width-1-4@m">
                  <div
                    class="
                      uk-card uk-card-default uk-card-small
                      tm-ignore-container
                    "
                    uk-sticky="offset: 30; bottom: true; media: @m;"
                  >
                    <div class="uk-card-body">
                      <div class="uk-grid-small" uk-grid>
                        <div class="uk-width-expand uk-text-muted uk-text-bold">
                          Giao tới
                        </div>
                      </div>
                    </div>
                    <div
                      class="uk-card-body"
                      v-if="this.$store.state.tokenUser"
                    >
                      <!-- <div class="uk-grid-small uk-flex-middle" uk-grid>
                          <div class="uk-width-expand uk-text-bolder">Trịnh Ngọc Quang</div>
                          <div class="uk-width-expand uk-text-bolder">0868987911</div>
                        </div> -->
                      <div class="row">
                        <div class="col-7 uk-text-bolder">
                          {{ dataUser.fullname }}
                        </div>
                        <div class="col-5 uk-text-bolder">
                          {{ dataUser.phone }}
                        </div>
                      </div>
                      <!-- <a class="uk-button uk-button-primary uk-margin-small uk-width-1-1" href="checkout.html">checkout</a> -->
                    </div>
                  </div>
                  <div
                    class="
                      uk-card uk-card-default uk-card-small
                      tm-ignore-container
                    "
                    uk-sticky="offset: 30; bottom: true; media: @m;"
                    style="margin-top: 15px"
                  >
                    <div class="uk-card-body">
                      <div class="uk-grid-small" uk-grid>
                        <div class="uk-width-expand uk-text-muted">
                          Tạm tính
                        </div>
                        <div>{{ formatPrice(sumTotal) }} đ</div>
                      </div>
                      <div class="uk-grid-small" uk-grid>
                        <div class="uk-width-expand uk-text-muted">
                          Giảm giá
                        </div>
                        <div class="uk-text-danger">
                          - {{ formatPrice(sumDiscount) }} đ
                        </div>
                      </div>
                    </div>
                    <div class="uk-card-body">
                      <div class="uk-grid-small uk-flex-middle" uk-grid>
                        <div class="uk-width-expand uk-text-muted">
                          Tổng cộng
                        </div>
                        <div class="uk-text-lead uk-text-bolder">
                          {{ formatPrice(sumTotal - sumDiscount) }} đ
                        </div>
                      </div>
                      <br />
                      <!-- <br> -->
                      <!-- <br> -->
                      <a
                        class="
                          uk-button
                          uk-button-primary
                          uk-margin-small
                          uk-width-1-1
                        "
                        @click="checkout()"
                        v-if="canBuy()"
                        >Mua hàng</a
                      >
                      <!-- <a
                        class="
                          uk-button
                          uk-button-primary
                          uk-margin-small
                          uk-width-1-1
                        "
                        @click="checkout()"
                        v-if="DetailsCart.length > 0"
                        >Mua hàng</a
                      > -->
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </section>
      <div id="review0" uk-modal>
        <div class="uk-modal-dialog uk-modal-body">
          <button
            class="uk-modal-close-outside"
            type="button"
            uk-close
          ></button>
          <h2 class="uk-modal-title uk-text-center">Xác nhận</h2>
          <form class="uk-form-stacked">
            <div class="uk-grid-small uk-child-width-1-1" uk-grid>
              <div>
                <label style="width: 100%" class="uk-text-center">
                  <i> Bạn chắn chắn muốn xóa sản phẩm khỏi giỏ hàng? </i>
                </label>
              </div>
              <br />
              <br />
              <br />
              <div class="uk-text-center">
                <button
                  class="uk-button uk-button-danger uk-modal-close"
                  type="button"
                  style="border: none; color: white"
                  @click="deleteProduct()"
                >
                  <span uk-icon="trash"></span> Xóa sản phẩm
                </button>
                <!-- <button type="button" class="uk-button uk-button-danger" @click="deleteProduct()"> 
                        <button class="uk-modal-close-outside" type="button" uk-close hidden></button>
                        Xóa sản phẩm
                      </button> -->
              </div>
            </div>
          </form>
        </div>
      </div>
    </main>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "cart",
  props: {
    CartDetail: [],
  },
  created() {
    // this.getCartDetail();
    this.getDataAccount();
    this.getDTDT();
    this.getDataUser();
    this.callFunction();
  },
  data() {
    return {
      formData: {
        number: "",
      },
      soLuong: {
        input: "",
        value: 1,
      },
      totalPrice: {
        input: "",
        value: 0,
      },
      sumTotal1: {
        input: "",
        value: 0,
      },
      // quantityCart: {
      //   quantity: 1,
      // },
      DetailsCart: [],
      TotalCart: [],
      totalPriceProduct: [],
      sumTotal: 0,
      sumDiscount: 0,
      dataUser: {},
      indexCart: 0,
      idCart: 0,
      left: {},
    };
  },
  methods: {
    canBuy() {
      console.log(this.left);
      if (this.DetailsCart.length <= 0) return false;
      return (
        this.DetailsCart.filter(
          (item) =>
            item.quantity <= 0 ||
            parseInt(item.quantity) > this.left[item.productId]
        ).length <= 0
      );
    },
    setIndex(index, id) {
      this.indexCart = index;
      this.idCart = id;
    },
    getDataUser() {
      axios
        .get(this.$store.state.MainLink + "customer/account", {
          headers: {
            Authorization: localStorage.userToken,
          },
        })
        .then((response) => {
          this.$store.state.InfoPersonal = response.data.object;
          this.dataUser = this.$store.state.InfoPersonal;
        })
        .catch((e) => {
          this.error.push(e);
          console.log(e);
        });
    },
    formatPrice(value) {
      let val = (value / 1).toFixed(0).replace(".", ",");
      return val.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ".");
    },
    getDTDT() {
      if (this.$store.state.tokenUser) {
        axios
          .get(this.$store.state.MainLink + "customer/cart/get", {
            headers: {
              Authorization: localStorage.userToken,
            },
          })
          .then((response) => {
            // console.log(response);
            this.$store.state.totalCart = response.data.object.length;
            this.$store.state.StoreCart = response.data.object;
            this.DetailsCart = this.$store.state.StoreCart;
            console.log(this.DetailsCart);
            for (let item in this.DetailsCart) {
              this.totalPriceProduct.push(response.data.object[item].price);
            }

            // console.log(this.DetailsCart)
          })
          .catch((e) => {
            console.log(e);
          });
      } else {
        this.DetailsCart = this.$store.state.StoreCart;
        this.left = {};
        this.$store.state.StoreCart.forEach((item) => {
              axios
                .get(
                  this.$store.state.MainLink +
                    `customer/products/getbycolorofid?id=${item.productId}&colorId=${item.colorId}`
                )
                .then((response) => {
                  this.left = {
                    ...this.left,
                    [item.productId]: response.data.object,
                  };
                });
            });
        console.log(this.DetailsCart);
      }
    },
    callFunction: function () {
      var v = this;
      setTimeout(function () {
        v.sumPrice();
      }, 200);
    },
    sumPrice() {
      this.sumTotal = this.sumDiscount = 0;
      // this.sumDiscount
      for (let i = 0; i < this.DetailsCart.length; i++) {
        this.sumTotal +=
          this.DetailsCart[i].price * this.DetailsCart[i].quantity;
      }
      for (let i = 0; i < this.DetailsCart.length; i++) {
        this.sumDiscount +=
          this.DetailsCart[i].discount * this.DetailsCart[i].quantity;
      }
      return this.sumTotal, this.sumDiscount;
    },
    deleteProduct() {
      if (this.$store.state.tokenUser) {
        axios
          .delete(
            this.$store.state.MainLink + "customer/cart/delete/" + this.idCart,
            {
              headers: {
                Authorization: localStorage.userToken,
              },
            }
          )
          .then((response) => {
            console.log(response);
            this.getDTDT();
            this.sumTotal = 0;
            this.callFunction();
            this.$toasted.show("Đã xoá sản phẩm khỏi giỏ hàng !", {
              type: "error",
              duration: 2000,
            });
          })
          .catch((error) => {
            this.$toasted.show(error.response.data.errorMsg, {
              type: "error",
              duration: 2000,
            });
          });
      } else {
        this.DetailsCart.splice(this.indexCart, 1);
        this.sumTotal = 0;
        this.sumPrice();
        this.$toasted.show("Đã xoá sản phẩm khỏi giỏ hàng !", {
          type: "error",
          duration: 2000,
        });
      }
    },
    backToCategory() {
      this.$router.push({
        name: "category",
      });
    },
    payment() {
      this.$router.push({
        name: "cartinfo",
      });
    },
    checkout() {
      this.$store.state.StoreCart = this.DetailsCart;
      // console.log(this.DetailsCart)
      this.$router.push({
        name: "checkout",
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
          this.$store.state.tokenUser = localStorage.userToken;
          this.getTotalCart();
        })
        .catch((e) => {
          console.log(e);
        });
    },
    getTotalCart() {
      if (this.$store.state.tokenUser) {
        axios
          .get(this.$store.state.MainLink + "customer/cart/get", {
            headers: {
              Authorization: localStorage.userToken,
            },
          })
          .then((response) => {
            this.$store.state.totalCart = response.data.object.length;
            this.$store.state.StoreCart = response.data.object;
            response.data.object.forEach((item) => {
              axios
                .get(
                  this.$store.state.MainLink +
                    `customer/products/getbycolorofid?id=${item.productId}&colorId=${item.colorId}`
                )
                .then((response) => {
                  this.left = {
                    ...this.left,
                    [item.productId]: response.data.object,
                  };
                });
            });
          })
          .catch((e) => {
            console.log(e);
          });
      }
    },
  },
  watch: {
    "totalPrice.value": function (value) {
      if (value) {
        this.sumPrice();
      }
    },
  },
};
</script>

<style scope>
.checkbox-product {
  vertical-align: middle;
  width: 40px;
}
.title-checkbox-all {
  float: left;
  margin: 15px 0px 0px 20px;
}
.icon-trash {
  margin: 20px 20px 0px 0px;
  float: right;
}
.font-li {
  margin-left: 20px;
  font-weight: bold;
  font-family: roboto, sans-serif;
}
.uk-form-label {
  text-align: left;
}
.span-right {
  margin-left: 80px;
  color: gray;
}
.span-total {
  color: red;
  font-size: 20px;
  font-weight: 700;
  text-align: center;
}
.img-product {
  height: 70px;
}
.size-img-cart-product {
  width: 50%;
}
.text-color-red {
  color: red;
}
table.table-custom-border {
  border-color: white;
}
.custom-font-size-td {
  font-size: 14px;
  vertical-align: middle;
}
.btn-custom-color {
  border: none;
  background: white;
  float: right;
}
.btn-custom-color-product {
  border: none;
  background: white;
  float: right;
  margin-top: 20px;
}
.custom-size-price {
  width: 100px;
}
.custom-size-qty {
  width: 100px;
  text-align: center;
}
.custom-size-total {
  width: 100px;
}
.btn-muahang {
  border: none;
  color: white;
  background: red;
  border-radius: 5px;
  font-weight: 600;
  width: 100%;
  height: 35px;
}
.custom-input-total {
  border: none;
  padding: 5px;
  padding-left: 15px;
  max-width: 50px;
  border-bottom: 1px solid silver;
  box-shadow: none;
}
</style>
