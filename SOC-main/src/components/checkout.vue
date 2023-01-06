<template>
  <div class="uk-offcanvas-content">
    <main>
      <section class="uk-section uk-section-small">
        <div class="uk-container">
          <div class="uk-grid-medium uk-child-width-1-1" uk-grid>
            <section class="uk-text-center">
              <a class="uk-link-muted uk-text-small" @click="returnCart()">
                <span class="uk-margin-xsmall-right" uk-icon="icon: arrow-left; ratio: .75;"></span>
                Quay lại giỏ hàng
              </a>
              <h1 class="uk-margin-small-top uk-margin-remove-bottom">
                Thanh toán
              </h1>
            </section>
            <section>
              <div class="uk-grid-medium" uk-grid>
                <form
                  class="
                    uk-form-stacked uk-width-1-1
                    tm-checkout
                    uk-width-expand@m
                  "
                >
                  <div class="uk-grid-medium uk-child-width-1-1" uk-grid>
                    <section>
                      <h2 class="tm-checkout-title">Thông tin liên lạc</h2>
                      <div
                        class="
                          uk-card uk-card-default uk-card-small uk-card-body
                          tm-ignore-container
                        "
                      >
                        <div class="uk-grid-small uk-child-width-1-1 uk-child-width-1-2@s" uk-grid>
                          <div>
                            <label class="full-size">
                              <div class="uk-form-label uk-form-label-required">
                                Họ tên
                              </div>
                              <input class="uk-input" type="text" v-model="dataUser.fullname" required />
                            </label>
                          </div>
                          <div>
                            <label class="full-size">
                              <div class="uk-form-label uk-form-label-required">
                                Email
                              </div>
                              <input class="uk-input" type="email" v-model="dataUser.email" required />
                            </label>
                          </div>
                          <div>
                            <label class="full-size">
                              <div class="uk-form-label uk-form-label-required">
                                Số điện thoại
                              </div>
                              <input class="uk-input" type="tel" v-model="dataUser.phone" required />
                            </label>
                          </div>
                          <div>
                            <label class="full-size">
                              <div class="uk-form-label uk-form-label-required">
                                Địa chỉ giao hàng
                              </div>
                              <input class="uk-input" type="text" v-model="dataUser.address" required />
                            </label>
                          </div>
                          <div class="col-12" style="color: red; text-align: center">
                            <p>{{validateForm}}</p>
                          </div>
                          
                        </div>
                      </div>
                    </section>
                    <section>
                      <h2 class="tm-checkout-title">Phương thức thanh toán</h2>
                      <div
                        class="
                          uk-card uk-card-default uk-card-small
                          tm-ignore-container
                        "
                      >
                        <div class="uk-card-body">
                          <div
                            class="
                              uk-grid-small
                              uk-grid-match
                              uk-child-width-1-1
                              uk-child-width-1-3@s
                              uk-flex-center
                            "
                            uk-switcher="toggle: &gt; * &gt; .tm-choose"
                            uk-grid
                          >
                            <div class="form-check">
                              <div class="" style="text-decoration: none">
                                <input
                                    class="form-check-input"
                                    type="radio"
                                    name="flexRadioDefault"
                                    id="flexRadioDefault1"
                                    value="1"
                                    v-model="typeOfPay"
                                  />
                                <div class="tm-choose-title">
                                  Thanh toán khi nhận hàng
                                </div>
                                <div class="uk-text-meta">
                                  Trả tiền mặt khi nhận hàng
                                </div>
                              </div>
                            </div>
                            <div class="form-check">
                              <div style="text-decoration: none">
                                <input
                                  class="form-check-input"
                                  type="radio"
                                  name="flexRadioDefault"
                                  id="flexRadioDefault2"
                                  v-model="typeOfPay"
                                  value="2"
                                />
                                <div class="tm-choose-title">
                                  Thanh toán trực tuyến
                                </div>
                                <div class="uk-text-meta">
                                  Thanh toán qua cổng thông tin VNPAY
                                </div>
                              </div>
                            </div>
                          </div>
                        </div>
                        <div class="uk-card-footer">
                          <div
                            class="
                              uk-grid-small
                              uk-flex-middle
                              uk-flex-center
                              uk-text-center
                            "
                            uk-grid
                          >
                            <div class="uk-text-meta">
                              <span
                                class="uk-margin-xsmall-right"
                                uk-icon="icon: lock; ratio: .75;"
                              ></span
                              >Bảo mật thanh toán bởi
                            </div>
                            <div>
                              <img
                                src="images/logovnpay.svg"
                                title="Verified by VNPAY"
                                style="height: 25px"
                              />
                            </div>
                          </div>
                        </div>
                      </div>
                    </section>
                  </div>
                </form>
                <div class="uk-width-1-1 uk-width-1-4@m tm-aside-column">
                  <div
                    class="
                      uk-card uk-card-default uk-card-small
                      tm-ignore-container
                    "
                    uk-sticky="offset: 30; bottom: true; media: @m;"
                  >
                    <section class="uk-card-body">
                      <h4>Danh sách sản phẩm</h4>
                      <div class="uk-grid-small" uk-grid v-for="item in DetailsOrder" :key="item.id">
                        <div class="uk-width-expand">
                          <div class="uk-text-small">
                            {{item.productName}}
                          </div>
                          <div class="uk-text-meta">{{item.quantity}} × {{ formatPrice(item.price) }} đ</div>
                        </div>
                        <div class="uk-text-right">
                          <div>{{ formatPrice(item.price*item.quantity) }} đ</div>
                        </div>
                      </div>
                    </section>
                    <section class="uk-card-body">
                      <div class="uk-grid-small" uk-grid>
                        <div class="uk-width-expand">
                          <div class="uk-text-muted">Giao tới</div>
                        </div>
                      </div>
                      <div class="uk-text-left uk-grid-small">
                          <div>{{dataUser.fullname}}</div>
                          <br>
                          <div>{{dataUser.phone}}</div>
                          <br>
                          <div>{{dataUser.email}}</div>
                          <br>
                          <div>{{dataUser.address}}</div>
                      </div>
                      <hr>
                      <div class="uk-grid-small" uk-grid>
                        <div class="uk-width-expand">
                          <div class="uk-text-muted">Thanh toán</div>
                        </div>
                        <div class="uk-text-right">
                          <div v-if="typeOfPay == 2">Trực tuyến</div>
                          <div v-else-if="typeOfPay == 1">Khi nhận hàng</div>
                          <div v-else></div>
                        </div>
                      </div>
                    </section>
                    <section class="uk-card-body">
                      <div class="uk-grid-small" uk-grid>
                        <div class="uk-width-expand">
                          <div class="uk-text-muted">Tạm tính</div>
                        </div>
                        <div class="uk-text-right">
                          <div>{{ formatPrice(sumTotal) }} đ</div>
                        </div>
                      </div>
                      <div class="uk-grid-small" uk-grid>
                        <div class="uk-width-expand">
                          <div class="uk-text-muted">Giảm giá</div>
                        </div>
                        <div class="uk-text-right">
                          <div class="uk-text-danger">- {{ formatPrice(sumDiscount) }}đ</div>
                        </div>
                      </div>
                    </section>
                    <section class="uk-card-body">
                      <div class="uk-grid-small uk-flex-middle" uk-grid>
                        <div class="uk-width-expand">
                          <div class="uk-text-muted">Tổng</div>
                        </div>
                        <div class="uk-text-right">
                          <div class="uk-text-lead uk-text-bolder">{{ formatPrice(sumPricePro.value = sumTotal - sumDiscount) }} đ</div>
                        </div>
                      </div>
                      <div v-if="dataUser.fullname != '' || dataUser.email != '' || dataUser.phone != '' || dataUser.address != ''">
                        <button v-if="checkPay == true" class=" uk-button uk-button-primary uk-margin-small uk-width-1-1" uk-toggle="target: #review9">
                          Đặt hàng
                        </button>
                        <button v-else class="btn btn-primary uk-button uk-button-primary uk-margin-small uk-width-1-1" type="button" disabled>
                          <span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span>
                          Vui lòng chờ ...
                        </button>
                      </div>

                    </section>
                  </div>
                </div>
              </div>
            </section>
          </div>
        </div>
      </section>
      <div id="review9" uk-modal>
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
                      <label style="width: 100%;" class="uk-text-center">
                        <h5>
                          Bạn chắn chắn muốn thanh toán hóa đơn này?
                        </h5>
                        <i>
                          Đơn hàng sẽ không thể thay đổi sau khi đặt hàng, nếu có bất kỳ thay đổi nào, vui lòng liên hệ với nhân viên!
                        </i>
                      </label>
                    </div>
                    <br>
                    <br>
                    <br>
                    <div class="uk-text-center">
                      <button class="uk-button uk-button-primary uk-modal-close" type="button" style="border: none; color: white" @click="payment()"><span  uk-icon="check"></span> Thanh toán hóa đơn</button>                      
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
      validateForm: "",
      checkPay: true,
      sumDiscount: 0,
      sumPricePro: {
        input: "",
        value: 0,
      },
      showAlert: ""
    };
  },
  created() {
    this.getOrderUser();
    this.sumPrice();
    this.getDataUser();
    this.getDataAccount();

  },
  methods: {
    formatPrice(value) {
      let val = (value / 1).toFixed(0).replace(".", ",");
      return val.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ".");
    },
    showAlert1() {
      const options = {title: 'Lỗi', size: 'sm'}
      this.$dialogs.alert(this.showAlert, options)
      .then(res => {
        console.log(res) // {ok: true|false|undefined}
      })
    },
    getDataUser() {
      this.dataUser = {
        address: this.$store.state.InfoPersonal.address,
        email: this.$store.state.InfoPersonal.email,
        fullname: this.$store.state.InfoPersonal.fullname,
        phone: this.$store.state.InfoPersonal.phone,
      };
    },
    getOrderUser() {
      this.DetailsOrder = this.$store.state.StoreCart;
      console.log(this.DetailsOrder)
      this.orderDetails = this.$store.state.StoreCart.map((detail) => {
        return {
          productId: detail.productId,
          quantity: detail.quantity,
          price: detail.price,
          discount: detail.discount,
          colorId: detail.colorId
        };
      });
    },
    payment() {
      
      this.checkPay = false;
      // console.log(this.checkPay)
      if(this.dataUser.fullname == '' || this.dataUser.email == '' || this.dataUser.phone == '' || this.dataUser.address == ''){
        this.validateForm = "Vui lòng không để trống thông tin !"
        this.checkPay = true
      }else{
        this.addToOrder()
      }
    },
    sumPrice() {
      // this.sumTotal = this.sumDiscount = 0

      for (let i = 0; i < this.DetailsOrder.length; i++) {
        this.sumTotal +=
          this.DetailsOrder[i].price * this.DetailsOrder[i].quantity;
      }
      for (let i = 0; i < this.DetailsOrder.length; i++) {
        this.sumDiscount += this.DetailsOrder[i].discount * this.DetailsOrder[i].quantity;
      }
      return this.sumTotal, this.sumDiscount;
    },
    addToOrder() {
      let item = {
        sumprice: this.sumPricePro.value,
        customer: this.dataUser,
        orderDetails: this.orderDetails,
      };
      console.log(item)
      this.$store.state.saveOrderDetail = item
      this.checkPay = false
      axios
        .post(this.$store.state.MainLink + "customer/orders/new", item, {
          headers: {
            Authorization: localStorage.userToken,
          },
        })
        .then((response) => {
          let infoCart = {
            OrderInfo: "thanh toán máy tính",
            ordersId: response.data.object.id,
            returnURL: "http://150.95.105.29/vnPayResult"
          };
          this.$store.state.orderDetails = response.data.object.id;
          localStorage.orderDetails = response.data.object.id;
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
        .catch((error) => {
          console.log(error.response.data)
          if(error.response.data.errorMsg){
            this.showAlert = error.response.data.errorMsg
          }else{
            this.showAlert = Object.values(error.response.data.validateDetails).join(", ");
          }
          this.showAlert1();
          this.checkPay = true
        });
    },
    returnCart() {
      this.$router.push({
        name: "cart",
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
        })
        .catch((e) => {

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

<style scoped>
label.full-size {
  width: 100%;
}
</style>