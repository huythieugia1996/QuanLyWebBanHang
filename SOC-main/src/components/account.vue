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
                        >
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
                              <span>C??i ?????t</span></a>
                          </router-link>
                        </div>
                        <div>
                          <button
                            class="uk-button uk-button-default uk-button-small"
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
                      <li class="uk-active">
                        <router-link to="account">
                          <a>????n h??ng</a>
                        </router-link>
                      </li>
                      <li>
                        <router-link to="favorites">
                          <a>S???n ph???m y??u th??ch</a>
                        </router-link>
                      </li>
                      <li>
                        <router-link to="personal">
                          <a>Th??ng tin c?? nh??n</a>
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
                  <h1 class="uk-h2">????n h??ng c???a b???n</h1>
                </header>
                <section
                  class="uk-card-body"
                  v-for="(item, index) in pageOfItems"
                  :key="index"
                >
                  <h3>
                    <a class="uk-link-heading" @click="detailOrder(item.id)">#{{ item.id }}
                      <span class="uk-text-muted uk-text-small">T??? ng??y {{ getDate(item.dateCreated) }}</span>
                    </a>
                  </h3>
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
                        <th class="uk-width-medium">T???ng s??? s???n ph???m</th>
                        <td v-if="item.numOfProduct">{{item.numOfProduct}}</td>
                        <td v-else style="color:red">????n h??ng kh??ng c?? s???n ph???m !</td>
                      </tr>
                      <tr>
                        <th class="uk-width-medium">H??nh th???c thanh to??n</th>
                        <td v-if="item.typePayment == true">Thanh to??n tr???c tuy???n</td>
                        <td v-else-if="item.typePayment == false">Thanh to??n khi nh???n h??ng</td>
                      </tr>
                      <tr>
                        <th class="uk-width-medium">T???ng ti???n</th>
                        <td>{{formatPrice(item.sumprice)}} ??</td>
                      </tr>
                      <tr>
                        <th class="uk-width-medium">Tr???ng th??i</th>
                        <td  v-if="item.status == 'Ch??? x??c nh???n'"><span class="uk-label" style="background-color: gray">{{item.status}}</span></td>
                        <td  v-else-if="item.status == '???? x??c nh???n'"><span class="uk-label">{{item.status}}</span></td>
                        <!-- <td><span class="uk-label" v-if="item.status == 'Ch??? x??c nh???n'">Ch??? x??c nh???n</span></td> -->
                        <td v-else-if="item.status == '???? h???y' || item.status == '????n h??ng l???i' || item.status == 'Y??u c???u h???y'">
                          <span class="uk-label uk-label-danger">{{item.status}}</span>
                        </td>
                        <td v-else-if="item.status == '??ang giao h??ng'">
                          <span class="uk-label" style="background-color: #18F5F5;">{{item.status}}</span>
                        </td>
                        <td v-else-if="item.status == 'Giao h??ng th??nh c??ng' || item.status == '???? nh???n l???i h??ng ho??n v???'">
                          <span class="uk-label uk-label-success">{{item.status}}</span>
                        </td>
                        <td v-else>
                          <span class="uk-label">{{item.status}}</span>
                        </td>
                      </tr>
                    </tbody>
                  </table>
                </section>
              </div>
              <div class="text-center">
                          <jw-pagination
                            :pageSize=5
                            style="margin-top: 15px"
                            :labels="customLabels"
                            :maxPages="3"
                            :items="listOrder"
                            @changePage="onChangePage"
                          ></jw-pagination>
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
import moment from "moment";

const customLabels = {
    first: '<<',
    last: '>>',
    previous: '<',
    next: '>'
};

export default {
  name: "account",
  data() {
    return {
      customLabels,
      listOrder: "",
      items: "",
      pageOfItems: [],
    };
  },
  created() {
    this.getDataUserOrder();
    this.getDataAccount();
  },
  methods: {
    onChangePage(pageOfItems) {
      this.pageOfItems = pageOfItems;
    },
    filteredList() {
      return this.getData.filter((data) =>
        data.toLowerCase().includes(this.searchText.value.toLowerCase())
      );
    },
    clearData() {
      this.$store.state.tokenUser = "";
      this.$store.state.totalCart = 0;
      this.$store.state.InfoPersonal = {};
      this.$store.state.userName = "";
      this.$store.state.CompareCart = [];
      this.$store.state.StoreCart = [];
      localStorage.clear();
      this.$router.push({
        name: "login",
      });
    },
    formatPrice(value) {
      let val = (value / 1).toFixed(0).replace(".", ",");
      return val.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ".");
    },
    getDate: (time, format = "YYYY-MM-DD") =>
      time ? moment(time, format).format("DD/MM/YYYY") : "",
    getDataUserOrder() {
      axios
        .get(this.$store.state.MainLink + "customer/orders", {
          headers: {
            Authorization: this.$store.state.tokenUser,
          },
        })
        .then((response) => {
          this.listOrder = response.data.object;
          console.log(this.listOrder);
        })
        .catch((e) => {
          this.error.push(e);
          console.log(e);
        });
    },
    detailOrder(id){
      // console.log(id);
      this.$router.push({
        name: "detailorder",
        params: { item: id },
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
          // console.log(response.data.object);
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

<style>
</style>