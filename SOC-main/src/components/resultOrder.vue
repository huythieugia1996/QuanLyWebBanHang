<template>
  <div class="uk-offcanvas-content">
    <!-- <loadingform></loadingform> -->
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
                <li><a>Thanh toán</a></li>
                <li><a>Hóa đơn</a></li>
              </ul>
            </div>
            <div>
              <div class="uk-grid-medium uk-child-width-1-1" uk-grid>
                <section>
                  <article class=" uk-card uk-card-default uk-card-body uk-article tm-ignore-container">
                    <div class="uk-width-1-1 uk-width-expand@m">

                        <div class="uk-card uk-card-default uk-card-small tm-ignore-container">
                            <header class="uk-card-header text-center">
                            <h1 class="uk-h2">Đơn hàng của bạn</h1>
                            </header>
                            <section class="uk-card-body">

                            <table
                                class="
                                uk-table
                                uk-table-small
                                uk-table-justify
                                uk-table-responsive
                                uk-table-divider
                                uk-margin-small-top
                                uk-margin-remove-bottom
                                "
                            >
                                <tbody>
                                <tr>
                                    <th class="uk-width-medium">Họ tên người mua</th>
                                    <td>{{detailUser.fullname}}</td>
                                </tr>
                                <tr>
                                    <th class="uk-width-medium">Email</th>
                                    <td>{{detailUser.email}}</td>
                                </tr>
                                <tr>
                                    <th class="uk-width-medium">Điện thoại</th>
                                    <td>{{detailUser.phone}}</td>
                                </tr>
                                <tr>
                                    <th class="uk-width-medium">Địa chỉ giao hàng</th>
                                    <td>{{detailUser.address}}</td>
                                </tr>
                                <tr>
                                    <th class="uk-width-medium">Tổng số sản phẩm</th>
                                    <td>{{detailProduct.length}}</td>
                                </tr>
                                <tr>
                                    <th class="uk-width-medium">Tổng tiền</th>
                                    <td>{{formatPrice(detailOrder.sumprice)}} đ</td>
                                </tr>
                                <tr>
                                    <th class="uk-width-medium">Trạng thái</th>
                                    <td><span class="uk-label">Chờ xác nhận</span></td>
                                </tr>
                                </tbody>
                            </table>
                            <br>
                            <br>
                            <table class="table" v-for="(item, index) in detailProduct" :key="index">
                                <thead>
                                <tr>
                                    <th scope="col">#{{index + 1}}</th>
                                    <th scope="col">{{item.productId}} </th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td scope="row">Giá sản phẩm</td>
                                    <td>{{formatPrice(item.price)}} đ</td>
                                </tr>
                                <tr>
                                    <td scope="row">Số lượng</td>
                                    <td>{{item.quantity}}</td>
                                </tr>
                                </tbody>
                            </table>
                            </section>
                        </div>
                    </div>
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
import moment from "moment";
import axios from "axios";

export default {
  name: "vnpayresult",
  components: {
  },
  data() {
    return {
        detailOrder: "",
        detailUser: "",
        detailProduct: "",
    };
  },
  created() {
    // this.callFunction();
    this.getDataAccount();
    this.getDataUserOrder();
    // console.log(this.$store.state.orderDetails)
  },
  methods: {
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
    getDate: (time, format = "YYYYMMDD") =>
      time ? moment(time, format).format("DD/MM/YYYY") : "",
    getTime: (time, format = "ssmmhh") =>
      time ? moment(time, format).format("hh:mm:ss") : "",
    formatPrice(value) {
      let val = (value / 1).toFixed(0).replace(".", ",");
      return val.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ".");
    },
    callFunction: function () {
      var v = this;
      setTimeout(function () {
        v.getDataResult();
      }, 0);
    },
    backToHome() {
      this.$router.push({
        name: "Home",
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
    getDataUserOrder() {
      // axios
      //   .get(this.$store.state.MainLink + "customer/orders/" + localStorage.orderDetails)
      //   .then((response) => {
      //     this.detailOrder = response.data.object;
      //     console.log(this.detailOrder);
      //   })
      //   .catch((e) => {
      //     this.error.push(e);
      //     console.log(e);
      //   });
      this.detailOrder = this.$store.state.saveOrderDetail
      this.detailUser = this.detailOrder.customer;
      this.detailProduct = this.detailOrder.orderDetails
      console.log(this.detailOrder);
    },
  },
};
</script>

<style scoped>
.label-custom-detail {
  float: left;
  width: 60%;
  margin: 5px 5px 5px 5px;
  margin-bottom: 25px;
  font-weight: 600;
}
.label-custom-red {
  color: red;
}
</style>