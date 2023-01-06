<template>
  <CRow>
    <CCol sm="6" lg="4">
      <CWidgetDropdown color="info" text="Tổng  đơn hàng tháng này">
        <template #footer>
          <!-- <CChartLineSimple
            pointed
            class="mt-3 mx-3"
            style="height: 70px"
            :data-points="[65, 59, 84, 84, 51, 55, 40]"
            point-hover-background-color="primary"
            label="Members"
            labels="months"
          /> -->
          <p style="font-size: 42px; font-weight;text-align:center">
            {{ countOrder }}
          </p>
        </template>
      </CWidgetDropdown>
    </CCol> 
    <CCol sm="6" lg="4">
      <!-- <CWidgetDropdown color="info" :header="getSuccessComfimCountOrder" text="Tổng số đơn hoàn thành"> -->
      <CWidgetDropdown color="success" text="Tổng số đơn hoàn thành">
        <template #footer>
          <!-- <CChartLineSimple
            pointed
            class="mt-3 mx-3"
            style="height: 70px"
            :data-points="[1, 18, 9, 17, 34, 22, 11]"
            point-hover-background-color="info"
            :options="{ elements: { line: { tension: 0.00001 } } }"
            label="Members"
            labels="months"
          /> -->
          <p style="font-size: 42px; font-weight;text-align:center">
            {{ getSuccessComfimCountOrder }}
          </p>
        </template>
      </CWidgetDropdown>
    </CCol>
    <CCol sm="6" lg="4">
      <CWidgetDropdown color="warning" text="Số đơn chờ xác nhận">
        <!-- <CWidgetDropdown color="danger" :header="unconfirm.length" text="Số đơn chưa hoàn thành"> -->
        <template #footer>
          <!-- <CChartBarSimple
            class="mt-3 mx-3"
            style="height: 70px"
            background-color="rgb(250, 152, 152)"
            label="Members"
            labels="months"
          /> -->
          <p style="font-size: 42px; font-weight;text-align:center">
            {{ unconfirm.length }}
          </p>
        </template>
      </CWidgetDropdown>
    </CCol>
    <CCol sm="6" lg="4">
      <!-- <CWidgetDropdown color="warning" :header="ComfimCountOrder" text="Số đơn đã xác nhận"> -->
      <CWidgetDropdown color="info" text="Số đơn đã xác nhận">
        <template #footer>
          <!-- <CChartLineSimple
            class="mt-3"
            style="height: 70px"
            background-color="rgba(255,255,255,.2)"
            :data-points="[78, 81, 80, 45, 34, 12, 40]"
            :options="{ elements: { line: { borderWidth: 2.5 } } }"
            point-hover-background-color="warning"
            label="Members"
            labels="months"
          /> -->
          <p style="font-size: 42px; font-weight;text-align:center">
            {{ ComfimCountOrder }}
          </p>
        </template>
      </CWidgetDropdown>
    </CCol>
    <CCol sm="6" lg="4">
      <!-- <CWidgetDropdown color="primary" :header="getErrorCountOrder" text="Đơn lỗi"> -->
      <CWidgetDropdown color="secondary" text="Đơn lỗi">
        <template #footer>
          <!-- <CChartLineSimple
            pointed
            class="mt-3 mx-3"
            style="height: 70px"
            :data-points="[65, 59, 84, 84, 51, 55, 40]"
            point-hover-background-color="primary"
            label="Members"
            labels="months"
          /> -->
          <p style="font-size: 42px; font-weight;text-align:center">
            {{ getErrorCountOrder }}
          </p>
        </template>
      </CWidgetDropdown>
    </CCol>
    <CCol sm="6" lg="4">
      <!-- <CWidgetDropdown color="warning" :header="CancerCountOrder" text="Số đơn bị hủy"> -->
      <CWidgetDropdown color="danger" text="Số đơn bị hủy">
        <template #footer>
          <!-- <CChartLineSimple
            class="mt-3"
            style="height: 70px"
            background-color="rgba(255,255,255,.2)"
            :data-points="[78, 81, 80, 45, 34, 12, 40]"
            :options="{ elements: { line: { borderWidth: 2.5 } } }"
            point-hover-background-color="warning"
            label="Members"
            labels="months"
          /> -->
          <p style="font-size: 42px; font-weight;text-align:center">
            {{ CancerCountOrder }}
          </p>
        </template>
      </CWidgetDropdown>
    </CCol>
  </CRow>
</template>

<script>
import { CChartLineSimple, CChartBarSimple } from "../charts/index.js";
import axios from "axios";
export default {
  name: "WidgetsDropdown",
  components: { CChartLineSimple, CChartBarSimple },
  data() {
    return {
      ComfimCountOrder: "",
      countOrder: "",
      CancerCountOrder: "",
      unconfirm: "",
      getErrorCountOrder: "",
      getSuccessComfimCountOrder: "",
    };
  },
  mounted() {
    axios
      .get(this.$store.state.MainLink + "admin/report/getConfimCountOrder", {
        headers: {
          Authorization: this.$store.state.userToken,
        },
      })
      .then((response) => {
        console.log(this);
        this.ComfimCountOrder = response.data.object.toString();
        console.log(this.ComfimCountOrder);
      })
      .catch((e) => {
        console.log(e);
      });
    axios
      .get(this.$store.state.MainLink + "admin/report/getCountOrder", {
        headers: {
          Authorization: this.$store.state.userToken,
        },
      })
      .then((response) => {
        this.getData = response.data.object;
        this.countOrder = response.data.object.toString();
        console.log(this.countOrder);
      })
      .catch((e) => {
        console.log(e);
      });
    axios
      .get(this.$store.state.MainLink + "admin/report/getCancerCountOrder", {
        headers: {
          Authorization: this.$store.state.userToken,
        },
      })
      .then((response) => {
        this.getData = response.data.object;
        this.CancerCountOrder = response.data.object.toString();
        console.log(this.CancerCountOrder);
      })
      .catch((e) => {
        console.log(e);
      });
    axios
      .get(this.$store.state.MainLink + "admin/report/unconfirm", {
        headers: {
          Authorization: this.$store.state.userToken,
        },
      })
      .then((response) => {
        this.getData = response.data.object;
        this.unconfirm = response.data.object;
        console.log(this.unconfirm);
      })
      .catch((e) => {
        console.log(e);
      });
    axios
      .get(this.$store.state.MainLink + "admin/report/getErrorCountOrder", {
        headers: {
          Authorization: this.$store.state.userToken,
        },
      })
      .then((response) => {
        this.getData = response.data.object;
        this.getErrorCountOrder = response.data.object;
        console.log(this.getErrorCountOrder);
      })
      .catch((e) => {
        console.log(e);
      });
    axios
      .get(
        this.$store.state.MainLink + "admin/report/getSuccessComfimCountOrder",
        {
          headers: {
            Authorization: this.$store.state.userToken,
          },
        }
      )
      .then((response) => {
        this.getData = response.data.object;
        this.getSuccessComfimCountOrder = response.data.object;
        console.log(this.getSuccessComfimCountOrder);
      })
      .catch((e) => {
        console.log(e);
      });
  },
  methods: {
    getCancerCountOrder() {
      axios
        .get(this.$store.state.MainLink + "admin/report/getCancerCountOrder", {
          headers: {
            Authorization: this.$store.state.userToken,
          },
        })
        .then((response) => {
          this.getData = response.data.object;
          console.log(this.getData);
        })
        .catch((e) => {
          console.log(e);
        });
    },
    getComfimCountOrder() {
      axios
        .get(this.$store.state.MainLink + "admin/report/getComfimCountOrder", {
          headers: {
            Authorization: this.$store.state.userToken,
          },
        })
        .then((response) => {
          this.getData = response.data.object;
          console.log(this.getData);
        })
        .catch((e) => {
          console.log(e);
        });
    },
    getCountOrder() {
      axios
        .get(this.$store.state.MainLink + "api/admin/report/getCountOrder", {
          headers: {
            Authorization: this.$store.state.userToken,
          },
        })
        .then((response) => {
          this.getData = response.data.object;
          this.countOrder = response.data.object;
          console.log(this.getData);
        })
        .catch((e) => {
          console.log(e);
        });
    },
    getListUnConfirmOrders() {
      axios
        .get(this.$store.state.MainLink + "admin/report/unconfirm", {
          headers: {
            Authorization: this.$store.state.userToken,
          },
        })
        .then((response) => {
          this.getData = response.data.object;
          console.log(this.getData);
        })
        .catch((e) => {
          console.log(e);
        });
    },
  },
};
</script>
