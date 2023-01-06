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
                <li><span>Giỏ hàng</span></li>
              </ul>
              <h1 class="uk-margin-small-top uk-margin-remove-bottom">
                Giỏ hàng
              </h1>
            </div>

            <!-- <p></p> -->
            <div v-if="this.DetailsCart.length > 0">
              <div style="margin-bottom: 25px">
                <div class="uk-grid-medium" uk-grid>
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
                      <table class="table table-custom-border" id="">
                        <tbody>
                          <tr>
                            <th scope="row" class="checkbox-product">
                              <!-- <input type="checkbox" /> -->
                            </th>
                            <td
                              class="custom-font-size-td size-img-cart-product"
                            >
                              Tất cả sản phẩm
                            </td>
                            <!-- <td style="width: 5%"></td> -->
                            <td class="custom-font-size-td custom-size-price">
                              Đơn giá
                            </td>
                            <td class="custom-font-size-td custom-size-price">Giảm giá</td>
                            <td class="custom-font-size-td custom-size-qty">
                              Số lượng
                            </td>
                            <td class="custom-font-size-td custom-size-total">
                              Thành tiền
                            </td>
                            <td>
                              <button class="btn-custom-color">
                                <b-icon
                                  icon="trash"
                                  style="color: red"
                                ></b-icon>
                              </button>
                            </td>
                          </tr>
                        </tbody>
                      </table>
                    </article>
                  </section>
                  <aside class="uk-width-1-4 uk-visible@m tm-aside-column">
                    <section
                      class="uk-card uk-card-default uk-card-small"
                      uk-sticky="offset: 90; bottom: true;"
                    >
                      <nav>
                        <ul class="uk-nav uk-nav-default tm-nav">
                          <li><a href="about.html">Giao tới</a></li>
                          <li v-if="this.$store.state.tokenUser">
                            <div class="font-li">
                              <span>{{ dataUser.fullname }}</span> |
                              <span>{{ dataUser.phone }}</span>
                            </div>
                            <!-- {{ this.$store.state.InfoPersonal }} -->
                          </li>

                          <li></li>
                        </ul>
                      </nav>
                    </section>
                  </aside>
                </div>
              </div>
              <!-- {{item.quantity}} -->
              <div>
                <div class="uk-grid-medium" uk-grid>
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
                      <table class="table">
                        <tbody>
                          <tr
                            v-for="(item, index) in DetailsCart"
                            :key="item.id"
                            @onchange="sumPrice()"
                          >
                            <th scope="row" class="checkbox-product">
                              <input type="checkbox" />
                            </th>
                            <td class="">
                              <img
                                class="img-product"
                                :src="item.photo"
                                alt=""
                              />
                            </td>
                            <td>
                              {{ item.productName.substr(6, 25) }} ...
                              <!-- <div class="uk-link-heading">
                                
                              </div> -->
                            </td>
                            <td class="custom-font-size-td custom-size-price">
                              {{ formatPrice(item.price) }} đ
                            </td>
                            <td class="custom-font-size-td custom-size-price text-center">
                              {{ formatPrice(item.discount) }} đ
                            </td>
                            <td class="custom-font-size-td custom-size-qty">
                              <input
                                class="custom-input-total"
                                type="number"
                                placeholder=""
                                v-model="item.quantity"
                                min="1"
                                max="5"
                                pattern="[0-9]"
                                oninput="this.value = Math.abs(this.value)"
                              />
                            </td>
                            <td
                              class="
                                text-color-red
                                custom-font-size-td custom-size-total
                              "
                            >
                              {{
                                formatPrice(
                                  (totalPrice.value =
                                    (item.price - item.discount)* item.quantity)
                                )
                              }}
                              đ
                            </td>
                            <td>
                              <button
                                class="btn-custom-color-product"
                                @click="deleteProduct(index, item.id)"
                              >
                                <b-icon
                                  icon="trash"
                                  style="color: red"
                                ></b-icon>
                              </button>
                            </td>
                          </tr>
                        </tbody>
                      </table>
                    </article>
                  </section>
                  <aside class="uk-width-1-4 uk-visible@m tm-aside-column">
                    <section
                      class="uk-card uk-card-default uk-card-small"
                      uk-sticky="offset: 90; bottom: true;"
                    >
                      <nav>
                        <ul class="uk-nav uk-nav-default tm-nav">
                          <li class="uk-active">
                            <a
                              >Tạm tính
                              <span class="span-right"
                                >{{ formatPrice(sumTotal) }} đ</span
                              ></a
                            >
                          </li>
                          <li class="uk-active">
                            <a
                              >Giảm giá
                              <span class="span-right">0 đ</span>
                            </a>
                          </li>
                          <hr />
                          <li>
                            <a>
                              <div>Tổng cộng</div>
                            </a>
                            <div class="span-total">
                              {{ formatPrice(sumTotal) }} đ
                            </div>
                          </li>
                          <br />
                          <br />
                          <br />

                          <li>
                            <button class="btn-muahang" @click="payment()">
                              Mua Hàng
                            </button>
                          </li>
                        </ul>
                      </nav>
                    </section>
                  </aside>
                </div>
              </div>
            </div>
            <div v-else style="text-align: center">
              <img
                src="images/emptycart.png"
                alt=""
                width="150px"
                style="margin-bottom: 25px"
              />
              <p>Oh ! Có vẻ như bạn chưa có sản phẩm nào cần mua.</p>
              <p>Hãy tiếp tục mua sắm nào !</p>
              <button
                class="btn-custom-back btn btn-outline-primary"
                @click="backToCategory()"
              >
                Các sản phẩm khác
              </button>
            </div>
          </div>
        </div>
      </section>
      <!-- <section class="uk-section uk-section-default uk-section-small">
        <div class="uk-container">
          <div uk-slider>
            <ul
              class="
                uk-slider-items
                uk-child-width-1-1
                uk-child-width-1-2@s
                uk-child-width-1-5@m
                uk-grid
              "
            >
              <li>
                <div
                  class="uk-grid-small uk-flex-center uk-flex-left@s"
                  uk-grid
                >
                  <div><span uk-icon="icon: star; ratio: 2.5;"></span></div>
                  <div class="uk-text-center uk-text-left@s uk-width-expand@s">
                    <div>Mauris placerat</div>
                    <div class="uk-text-meta">
                      Donec mollis nibh dolor, sit amet auctor
                    </div>
                  </div>
                </div>
              </li>
              <li>
                <div
                  class="uk-grid-small uk-flex-center uk-flex-left@s"
                  uk-grid
                >
                  <div><span uk-icon="icon: receiver; ratio: 2.5;"></span></div>
                  <div class="uk-text-center uk-text-left@s uk-width-expand@s">
                    <div>Lorem ipsum</div>
                    <div class="uk-text-meta">
                      Sit amet, consectetur adipiscing elit
                    </div>
                  </div>
                </div>
              </li>
              <li>
                <div
                  class="uk-grid-small uk-flex-center uk-flex-left@s"
                  uk-grid
                >
                  <div><span uk-icon="icon: location; ratio: 2.5;"></span></div>
                  <div class="uk-text-center uk-text-left@s uk-width-expand@s">
                    <div>Proin pharetra</div>
                    <div class="uk-text-meta">
                      Nec quam a fermentum ut viverra
                    </div>
                  </div>
                </div>
              </li>
              <li>
                <div
                  class="uk-grid-small uk-flex-center uk-flex-left@s"
                  uk-grid
                >
                  <div><span uk-icon="icon: comments; ratio: 2.5;"></span></div>
                  <div class="uk-text-center uk-text-left@s uk-width-expand@s">
                    <div>Praesent ultrices</div>
                    <div class="uk-text-meta">
                      Praesent ultrices, orci nec finibus
                    </div>
                  </div>
                </div>
              </li>
              <li>
                <div
                  class="uk-grid-small uk-flex-center uk-flex-left@s"
                  uk-grid
                >
                  <div><span uk-icon="icon: happy; ratio: 2.5;"></span></div>
                  <div class="uk-text-center uk-text-left@s uk-width-expand@s">
                    <div>Duis condimentum</div>
                    <div class="uk-text-meta">
                      Pellentesque eget varius arcu
                    </div>
                  </div>
                </div>
              </li>
            </ul>
            <ul
              class="
                uk-slider-nav uk-dotnav uk-flex-center uk-margin-medium-top
              "
            ></ul>
          </div>
        </div>
      </section> -->
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
      dataUser: {},
    };
  },
  methods: {
    getDataUser() {
      if (this.$store.state.tokenUser) {
        axios
          .get(this.$store.state.MainLink + "customer/account", {
            headers: {
              Authorization: this.$store.state.tokenUser,
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
      }
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
            console.log(response);
            // console.log(response)
            this.$store.state.totalCart = response.data.object.length;
            this.$store.state.StoreCart = response.data.object;
            this.DetailsCart = this.$store.state.StoreCart;
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
      this.sumTotal = 0
      for (let i = 0; i < this.DetailsCart.length; i++) {
        this.sumTotal +=
          (this.DetailsCart[i].price - this.DetailsCart[i].discount) * this.DetailsCart[i].quantity;
      }
      return this.sumTotal;
    },
    deleteProduct(index, id) {
      if (this.$store.state.tokenUser) {
        axios
          .delete(this.$store.state.MainLink + "customer/cart/delete/" + id, {
            headers: {
              Authorization: localStorage.userToken,
            },
          })
          .then((response) => {
            console.log(response);
            this.getDTDT();
            this.sumTotal = 0;
            this.sumPrice();
            this.$toasted.show("Đã xoá sản phẩm khỏi giỏ hàng !", {
              type: "error",
              duration: 2000,
            });
          })
          .catch(function (error) {
            alert(error);
          });
      } else {
        this.DetailsCart.splice(index, 1);
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
      if(this.$store.state.tokenUser){
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
      }
      
    },
  },
  watch: {
    'totalPrice.value': function (value) {
      // console.log(value);
      if(value){
        // console.log(value);
        this.sumPrice()
      }
    },
    // 'DetailsCart': function (value) {
    //   console.log(value);
    //   if(value){
    //     console.log(value);
    //   }
    // }
  }
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
  width: 45%;
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
