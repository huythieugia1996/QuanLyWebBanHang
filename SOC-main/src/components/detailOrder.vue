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
              <div class="uk-card uk-card-default uk-card-small tm-ignore-container">
                <header class="uk-card-header">
                  <h1 class="uk-h2" style="float: left; margin: 10px 0 0 0">
                    ????n h??ng c???a b???n
                  </h1>
                  <button type="button" class="btn btn-outline-danger btn-size" style="float: right; margin: 10px" v-if="detailOrder.status == '???? x??c nh???n' &&detailOrder.typePayment == false" uk-toggle="target: #review1">
                    Y??u c???u h???y ????n
                  </button>
                  <button
                    type="button"
                    class="btn btn-outline-danger btn-size"
                    style="float: right; margin: 10px"
                    v-else-if="
                      detailOrder.status == 'Ch??? x??c nh???n' &&
                      detailOrder.typePayment == false
                    "
                    uk-toggle="target: #review1"
                  >
                    H???y ????n h??ng
                  </button>
                  <button
                    type="button"
                    class="btn btn-outline-success btn-size"
                    style="float: right; margin: 10px"
                    v-else-if="canRequestOrder && detailOrder.status == 'Giao h??ng th??nh c??ng' "
                    uk-toggle="target: #review2"
                  >
                    Y??u c???u tr??? h??ng
                  </button>
                  <button
                    type="button"
                    class="btn btn-outline-danger btn-size"
                    style="float: right; margin: 10px"
                    v-if="
                      detailOrder.status == 'Y??u c???u tr??? h??ng' &&
                      detailOrder.typePayment == false
                    "
                    uk-toggle="target: #review3"
                  >
                    H???y y??u c???u
                  </button>
                  <button
                    type="button"
                    class="btn btn-outline-danger btn-size"
                    style="float: right; margin: 10px"
                    v-if="
                      detailOrder.status == 'Y??u c???u h???y' &&
                      detailOrder.typePayment == false
                    "
                    uk-toggle="target: #review4"
                  >
                    H???y y??u c???u
                  </button>
                </header>

                <section class="uk-card-body">
                  <h3>
                    <a class="uk-link-heading"
                      >#{{ detailOrder.id }}
                      <span class="uk-text-muted uk-text-small"
                        >T??? ng??y {{ getDate(detailOrder.dateCreated) }}</span
                      >
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
                        <th class="uk-width-medium">H??? t??n ng?????i mua</th>
                        <td>{{ detailUser.fullname }}</td>
                      </tr>
                      <tr>
                        <th class="uk-width-medium">Email</th>
                        <td>{{ detailUser.email }}</td>
                      </tr>
                      <tr>
                        <th class="uk-width-medium">??i???n tho???i</th>
                        <td>{{ detailUser.phone }}</td>
                      </tr>
                      <tr>
                        <th class="uk-width-medium">?????a ch??? giao h??ng</th>
                        <td>{{ detailUser.address }}</td>
                      </tr>
                      <tr>
                        <th class="uk-width-medium">T???ng s??? s???n ph???m</th>
                        <td>{{ detailProduct.length }}</td>
                      </tr>
                      <tr>
                        <th class="uk-width-medium">H??nh th???c thanh to??n</th>
                        <td v-if="detailOrder.typePayment == true">
                          Thanh to??n tr???c tuy???n
                        </td>
                        <td v-else>Thanh to??n khi nh???n h??ng</td>
                      </tr>
                      <tr>
                        <th class="uk-width-medium">T???ng ti???n</th>
                        <td>{{ formatPrice(detailOrder.sumprice) }} ??</td>
                      </tr>
                      <tr>
                        <th class="uk-width-medium">Tr???ng th??i</th>
                        <td v-if="detailOrder.status == 'Ch??? x??c nh???n'">
                          <span class="uk-label">{{ detailOrder.status }}</span>
                        </td>
                        <td
                          v-else-if="
                            detailOrder.status == '???? h???y' ||
                            detailOrder.status == 'Y??u c???u h???y' ||
                            item.status == '????n h??ng l???i'
                          "
                        >
                          <span class="uk-label uk-label-danger">{{
                            detailOrder.status
                          }}</span>
                        </td>
                        <td v-else-if="detailOrder.status == '??ang giao h??ng'">
                          <span
                            class="uk-label"
                            style="background-color: #18f5f5"
                            >{{ detailOrder.status }}</span
                          >
                        </td>
                        <td
                          v-else-if="
                            detailOrder.status == 'Giao h??ng th??nh c??ng'
                          "
                        >
                          <span class="uk-label uk-label-success">{{
                            detailOrder.status
                          }}</span>
                        </td>
                        <td v-else>
                          <span class="uk-label">{{ detailOrder.status }}</span>
                        </td>
                      </tr>
                    </tbody>
                  </table>

                  <br />
                  <br />
                  <h4>
                    <a class="uk-link-heading"># Tr???ng th??i ????n h??ng </a>
                  </h4>
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
                      <tr v-for="(item, index) in statusOrder" :key="index">
                        <th class="uk-width-medium">
                          <div class="text-bold">{{ item.timeChange }}</div>
                          <div>
                            NV thay ?????i: <i>{{ item.changedBy }}</i>
                          </div>
                        </th>
                        <td>
                          <div>{{ item.status }}</div>
                          <i class="uk-width-medium">
                            {{ item.note }}
                          </i>
                        </td>
                      </tr>
                    </tbody>
                  </table>

                  <div v-if="detailOrder.typePayment == true">
                    <br />
                    <br />
                    <h4>
                      <a class="uk-link-heading"
                        ># Th??ng tin thanh to??n online
                      </a>
                      <button type="button" class="btn btn-outline-danger btn-size" style="float: right; margin: 10px" v-if="detailVN.status == 2 && detailOrder.status == 'Ch??? x??c nh???n'" @click="rePay()"> Thanh to??n l???i</button>
                    </h4>
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
                          <th class="uk-width-medium">
                            <div>T??n ng??n h??ng</div>
                          </th>
                          <td>
                            <div>{{ detailVN.bankCode }}</div>
                          </td>
                        </tr>
                        <tr>
                          <th class="uk-width-medium">
                            <div>Ng??y thanh to??n</div>
                          </th>
                          <td>
                            <div>{{ detailVN.createDate }}</div>
                          </td>
                        </tr>
                        <tr>
                          <th class="uk-width-medium">
                            <div>Lo???i th???</div>
                          </th>
                          <td>
                            <div>{{ detailVN.cardType }}</div>
                          </td>
                        </tr>
                        <tr>
                          <th class="uk-width-medium">
                            <div>N???i dung thanh to??n</div>
                          </th>
                          <td>
                            <div>{{ detailVN.orderInfo }}</div>
                          </td>
                        </tr>
                        <tr>
                          <th class="uk-width-medium">
                            <div>T??nh tr???ng thanh to??n</div>
                          </th>
                          <td>
                            <div v-if="detailVN.status == 1">
                              <span class="uk-label uk-label-success"
                                >Thanh to??n th??nh c??ng!</span
                              >
                            </div>
                            <div v-else>
                              <span class="uk-label uk-label-danger"
                                >Thanh to??n th???t b???i!</span
                              >
                            </div>
                          </td>
                        </tr>
                      </tbody>
                    </table>
                  </div>

                  <br />
                  <br />
                  <table
                    class="table"
                    v-for="(item, index) in detailProduct"
                    :key="index"
                  >
                    <thead>
                      <tr>
                        <th scope="col">#{{ index + 1 }}</th>
                        <th scope="col">{{ item.productName }}</th>
                      </tr>
                    </thead>
                    <tbody>
                      <tr>
                        <td scope="row">Gi?? s???n ph???m</td>
                        <td>{{ formatPrice(item.price) }} ??</td>
                      </tr>
                      <tr>
                        <td scope="row">M??u s???n ph???m</td>
                        <td>{{ item.colorName }}</td>
                      </tr>
                      <tr>
                        <td scope="row">S??? l?????ng</td>
                        <td>{{ item.quantity }}</td>
                      </tr>
                    </tbody>
                  </table>
                </section>
              </div>
            </div>

            <div id="review1" uk-modal>
              <div class="uk-modal-dialog uk-modal-body">
                <button
                  class="uk-modal-close-outside"
                  type="button"
                  uk-close
                ></button>
                <h2 class="uk-modal-title uk-text-center">H???y ????n</h2>
                <form class="uk-form-stacked">
                  <div class="uk-grid-small uk-child-width-1-1" uk-grid>
                    <div>
                      <label style="width: 100%">
                        <div class="uk-form-label uk-form-label-required">
                          Ch???n l?? do h???y ????n
                        </div>
                        <select
                          name="reason"
                          id="reason"
                          style="
                            padding: 5px;
                            width: 100%;
                            border-radius: 5px;
                            box-shadow: none;
                          "
                          v-model="note"
                        >
                          <option value="" selected disabled>
                            -- Ch???n l?? do h???y ????n --
                          </option>
                          <option value="Thay ?????i s???n ph???m kh??c">
                            Thay ?????i s???n ph???m kh??c
                          </option>
                          <option value="Thay ?????i th??ng tin nh???n h??ng">
                            Thay ?????i th??ng tin nh???n h??ng
                          </option>
                          <option value="diff">L?? do kh??c</option>
                        </select>
                        <br />
                        <br />
                        <div v-if="note == 'diff'">
                          <div class="uk-form-label uk-form-label-required">
                            Nh???p l?? do kh??c
                          </div>
                          <textarea
                            rows="4"
                            style="
                              width: 100%;
                              border-radius: 5px;
                              padding: 5px 0px 0px 10px;
                            "
                            placeholder="Nh???p l?? do b???n h???y ????n ..."
                            v-model="notediff"
                          ></textarea>
                        </div>
                      </label>
                    </div>
                    <div class="uk-text-center">
                      <button
                        type="button"
                        class="uk-button uk-button-danger"
                        v-if="detailOrder.status == 'Ch??? x??c nh???n'"
                        @click="cancelUserOrder()"
                      >
                        H???y ????n h??ng
                      </button>
                      <button
                        type="button"
                        class="uk-button uk-button-danger"
                        v-else-if="detailOrder.status == '???? x??c nh???n'"
                        @click="requestUserOrder()"
                      >
                        Y??u c???u h???y ????n
                      </button>
                    </div>
                  </div>
                </form>
              </div>
            </div>

            <div id="review2" uk-modal>
              <div class="uk-modal-dialog uk-modal-body">
                <button
                  class="uk-modal-close-outside"
                  type="button"
                  uk-close
                ></button>
                <h2 class="uk-modal-title uk-text-center">Ho??n tr??? s???n ph???m</h2>
                <form class="uk-form-stacked">
                  <div class="uk-grid-small uk-child-width-1-1" uk-grid>
                    <div>
                      <label style="width: 100%">
                        <div class="uk-form-label uk-form-label-required">
                          Ch???n l?? do ho??n tr??? s???n ph???m
                        </div>
                        <select
                          name="reason"
                          id="reason"
                          style="
                            padding: 5px;
                            width: 100%;
                            border-radius: 5px;
                            box-shadow: none;
                          "
                          v-model="note"
                        >
                          <option value="" selected disabled>
                            -- Ch???n l?? do ho??n tr??? s???n ph???m --
                          </option>
                          <option value="Thay ?????i s???n ph???m kh??c">
                            S???n ph???m kh??ng gi???ng nh?? h??nh ???nh mi??u t???
                          </option>
                          <option value="Thay ?????i th??ng tin nh???n h??ng">
                            S???n ph???m l???i/kh??ng s??? d???ng ???????c
                          </option>
                          <option value="diff">L?? do kh??c</option>
                        </select>
                        <br />
                        <br />
                        <div v-if="note == 'diff'">
                          <div class="uk-form-label uk-form-label-required">
                            Nh???p l?? do kh??c
                          </div>
                          <textarea
                            rows="4"
                            style="
                              width: 100%;
                              border-radius: 5px;
                              padding: 5px 0px 0px 10px;
                            "
                            placeholder="Nh???p l?? do b???n ho??n tr??? s???n ph???m ..."
                            v-model="notediff"
                          ></textarea>
                        </div>
                      </label>
                    </div>
                    <div class="uk-text-center">
                      <button
                        type="button"
                        class="uk-button uk-button-danger"
                        @click="checkReturnOrder()"
                      >
                        Y??u c???u ho??n ????n
                      </button>
                    </div>
                  </div>
                </form>
              </div>
            </div>

            <div id="review3" uk-modal>
              <div class="uk-modal-dialog uk-modal-body">
                <button
                  class="uk-modal-close-outside"
                  type="button"
                  uk-close
                ></button>
                <h2 class="uk-modal-title uk-text-center">H???y y??u c???u</h2>
                <form class="uk-form-stacked">
                  <div class="uk-grid-small uk-child-width-1-1" uk-grid>
                    <div>
                      <label style="width: 100%">
                        <div class="uk-form-label uk-form-label-required">
                          Ch???n l?? do h???y y??u c???u
                        </div>
                        <select
                          name="reason"
                          id="reason"
                          style="
                            padding: 5px;
                            width: 100%;
                            border-radius: 5px;
                            box-shadow: none;
                          "
                          v-model="note"
                        >
                          <option value="" selected disabled>
                            -- Ch???n l?? do h???y y??u c???u --
                          </option>
                          <option value="Thay ?????i s???n ph???m kh??c">
                            Y??u c???u sai
                          </option>
                          <option value="Thay ?????i th??ng tin nh???n h??ng">
                            Kh??ng mu???n h???y ????n
                          </option>
                          <option value="diff">L?? do kh??c</option>
                        </select>
                        <br />
                        <br />
                        <div v-if="note == 'diff'">
                          <div class="uk-form-label uk-form-label-required">
                            Nh???p l?? do kh??c
                          </div>
                          <textarea
                            rows="4"
                            style="
                              width: 100%;
                              border-radius: 5px;
                              padding: 5px 0px 0px 10px;
                            "
                            placeholder="Nh???p l?? do b???n kh??ng mu???n h???y ????n ..."
                            v-model="notediff"
                          ></textarea>
                        </div>
                      </label>
                    </div>
                    <div class="uk-text-center">
                      <button
                        type="button"
                        class="uk-button uk-button-danger"
                        @click="cancelReturnOrder()"
                      >
                        H???y y??u c???u
                      </button>
                    </div>
                  </div>
                </form>
              </div>
            </div>

            <div id="review4" uk-modal>
              <div class="uk-modal-dialog uk-modal-body">
                <button
                  class="uk-modal-close-outside"
                  type="button"
                  uk-close
                ></button>
                <h2 class="uk-modal-title uk-text-center">H???y y??u c???u</h2>
                <form class="uk-form-stacked">
                  <div class="uk-grid-small uk-child-width-1-1" uk-grid>
                    <div>
                      <label style="width: 100%">
                        <div class="uk-form-label uk-form-label-required">
                          Ch???n l?? do h???y y??u c???u
                        </div>
                        <select
                          name="reason"
                          id="reason"
                          style="
                            padding: 5px;
                            width: 100%;
                            border-radius: 5px;
                            box-shadow: none;
                          "
                          v-model="note"
                        >
                          <option value="" selected disabled>
                            -- Ch???n l?? do h???y y??u c???u --
                          </option>
                          <option value="Thay ?????i s???n ph???m kh??c">
                            Y??u c???u sai
                          </option>
                          <option value="Thay ?????i th??ng tin nh???n h??ng">
                            Kh??ng mu???n h???y ????n
                          </option>
                          <option value="diff">L?? do kh??c</option>
                        </select>
                        <br />
                        <br />
                        <div v-if="note == 'diff'">
                          <div class="uk-form-label uk-form-label-required">
                            Nh???p l?? do kh??c
                          </div>
                          <textarea
                            rows="4"
                            style="
                              width: 100%;
                              border-radius: 5px;
                              padding: 5px 0px 0px 10px;
                            "
                            placeholder="Nh???p l?? do b???n kh??ng mu???n h???y ????n ..."
                            v-model="notediff"
                          ></textarea>
                        </div>
                      </label>
                    </div>
                    <div class="uk-text-center">
                      <button
                        type="button"
                        class="uk-button uk-button-danger"
                        @click="unCancelOrder()"
                      >
                        H???y y??u c???u
                      </button>
                    </div>
                  </div>
                </form>
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

export default {
  name: "detailOrder",
  props: {
    item: Number,
  },
  data() {
    return {
      detailOrder: "",
      detailProduct: "",
      detailUser: "",
      detailVN: "",
      statusOrder: "",
      notediff: "",
      note: "",
      checkDate: "",
      canRequestOrder: true,
    };
  },
  created() {
    this.getDataAccount();
    this.getDataUserOrder();
    this.getDetailVNPAY();
    // console.log(this.item)
  },
  methods: {
    cancelUserOrder() {
      if (this.note != "diff") {
        this.postNoteCancel();
      } else {
        this.note = this.notediff;
        this.postNoteCancel();
      }
    },
    checkReturnOrder() {
      if (this.note != "diff") {
        this.returnOrder();
      } else {
        this.note = this.notediff;
        this.returnOrder();
      }
    },
    returnOrder() {
      let itemNote = {
        note: this.note,
      };
      axios
        .put(
          this.$store.state.MainLink +
            "customer/orders/confimReturnsUser?id=" +
            this.item,
          itemNote,
          {
            headers: {
              Authorization: this.$store.state.tokenUser,
            },
          }
        )
        .then((response) => {
          console.log(response.data.object);
          this.getDataUserOrder();
          this.$toasted.show("H???y ????n h??ng th??nh c??ng !", {
            type: "success",
            duration: 2000,
          });
        })
        .catch((e) => {
          this.$toasted.show("H???y ????n h??ng th???t b???i !", {
            type: "error",
            duration: 2000,
          });
          console.log(e);
        });
    },
    cancelReturnOrder() {
      if (this.note != "diff") {
        this.cancelReturn();
      } else {
        this.note = this.notediff;
        this.cancelReturn();
      }
    },
    cancelReturn() {
      let itemNote = {
        note: this.note,
      };
      axios
        .put(
          this.$store.state.MainLink +
            "customer/orders/unCancerOrderUser?id=" +
            this.item,
          itemNote,
          {
            headers: {
              Authorization: this.$store.state.tokenUser,
            },
          }
        )
        .then(() => {
          // console.log(response.data.object);
          this.getDataUserOrder();
          this.$toasted.show("H???y ????n h??ng th??nh c??ng !", {
            type: "success",
            duration: 2000,
          });
        })
        .catch((e) => {
          this.$toasted.show("H???y ????n h??ng th???t b???i !", {
            type: "error",
            duration: 2000,
          });
          console.log(e);
        });
    },
    unCancelOrder() {
      if (this.note != "diff") {
        this.requestCancelOrder();
      } else {
        this.note = this.notediff;
        this.requestCancelOrder();
      }
    },
    requestCancelOrder() {
      let itemNote = {
        note: this.note,
      };
      axios
        .put(
          this.$store.state.MainLink +
            "customer/orders/unConfimReturnsUser?id=" +
            this.item,
          itemNote,
          {
            headers: {
              Authorization: this.$store.state.tokenUser,
            },
          }
        )
        .then(() => {
          // console.log(response.data.object);
          this.getDataUserOrder();
          this.$toasted.show("H???y ????n h??ng th??nh c??ng !", {
            type: "success",
            duration: 2000,
          });
        })
        .catch((e) => {
          this.$toasted.show("H???y ????n h??ng th???t b???i !", {
            type: "error",
            duration: 2000,
          });
          console.log(e);
        });
    },
    requestUserOrder() {
      if (this.note != "diff") {
        this.putNoteCancel();
      } else {
        this.note = this.notediff;
        this.putNoteCancel();
      }
    },
    postNoteCancel() {
      let itemNote = {
        note: this.note,
      };
      axios
        .put(
          this.$store.state.MainLink +
            "customer/orders/cancerOrderUser?id=" +
            this.item,
          itemNote,
          {
            headers: {
              Authorization: this.$store.state.tokenUser,
            },
          }
        )
        .then((response) => {
          console.log(response.data.object);
          this.getDataUserOrder();
          this.$toasted.show("H???y ????n h??ng th??nh c??ng !", {
            type: "success",
            duration: 2000,
          });
        })
        .catch((e) => {
          this.$toasted.show("H???y ????n h??ng th???t b???i !", {
            type: "error",
            duration: 2000,
          });
          console.log(e);
        });
    },
    putNoteCancel() {
      let itemNote = {
        note: this.note,
      };
      axios
        .put(
          this.$store.state.MainLink +
            "customer/orders/requestCancerOrderUser?id=" +
            this.item,
          itemNote,
          {
            headers: {
              Authorization: this.$store.state.tokenUser,
            },
          }
        )
        .then((response) => {
          console.log(response.data.object);
          this.getDataUserOrder();
          this.$toasted.show("G???i y??u c???u th??nh c??ng !", {
            type: "success",
            duration: 2000,
          });
        })
        .catch((e) => {
          this.$toasted.show("G???i y??u c???u th???t b???i !", {
            type: "error",
            duration: 2000,
          });
          console.log(e);
        });
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
        .get(this.$store.state.MainLink + "customer/orders/" + this.item, {
          headers: {
            Authorization: this.$store.state.tokenUser,
          },
        })
        .then((response) => {
          console.log(response.data.object);

          this.detailOrder = response.data.object;
          this.detailUser = response.data.object.customer;
          this.detailProduct = response.data.object.orderDetails;
          this.statusOrder = response.data.object.orderManagements;
          let statusSuccess = response.data.object.orderManagements.filter(
            (detail) => detail.status == "Giao h??ng th??nh c??ng"
          );
          console.log(statusSuccess);
          if (statusSuccess.length > 1) {
            this.canRequestOrder = false;
          } else if (statusSuccess.length > 0) {
            this.canRequestOrder = moment(
              statusSuccess[0].timeChange,
              "dd-MM-yyyy HH:mm:ss"
            )
              .add(3, 'd')
              .isBefore(moment.now());
          } else {
            this.canRequestOrder = true;
          }
        })
        .catch((e) => {
          this.error.push(e);
          console.log(e);
        });
    },
    getDetailVNPAY() {
      axios
        .get(
          this.$store.state.MainLink + "customer/pay/getresult/" + this.item,
          {
            headers: {
              Authorization: this.$store.state.tokenUser,
            },
          }
        )
        .then((response) => {
          // console.log(response.data.object);
          this.detailVN = response.data.object;
          console.log(this.detailVN);
        })
        .catch((e) => {
          this.error.push(e);
          console.log(e);
        });
    },
    getDataAccount() {
      if (localStorage.userToken) {
        axios
          .get(this.$store.state.MainLink + "customer/account", {
            headers: {
              Authorization: localStorage.userToken,
            },
          })
          .then((response) => {
            this.$store.state.userName = response.data.object.fullname;
            this.$store.state.tokenUser = localStorage.userToken;
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
    rePay(){      
          let infoCart = {
            OrderInfo: "thanh to??n m??y t??nh",
            ordersId: this.item,
            returnURL: "http://150.95.105.29/vnPayResult"
          };
            axios
              .post(
                this.$store.state.MainLink + "customer/pay/getpayurl",
                infoCart
              )
              .then((response) => {
                window.location = response.data.object;
              });
    }
  },
};
</script>

<style>
</style>