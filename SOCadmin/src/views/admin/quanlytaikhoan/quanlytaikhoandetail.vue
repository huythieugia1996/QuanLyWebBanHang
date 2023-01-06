<template>
  <div>
    <CRow>
      <CCol md="12" lg="12">
        <CCard>
          <CCardHeader>
            <CIcon name="cil-justify-center" />
            <strong> Thông tin người dùng </strong>
            <CButton type="submit" 
                    size="sm" color="primary" 
                    class="btn btn-custom-size"
                    @click="updateAccount(getData.id)"
                    style="float: right"
                    >
                    <i class="cil-plus"></i>
                    Cập nhật thông tin
            </CButton>
          </CCardHeader>
          
          <CCardBody height="auto">
            <CListGroup>
              <CListGroupItem>
                <div style="width: 15%; float: left; font-weight: 600">
                  Tài khoản 
                </div>
                <span>{{ getData.username }}</span>
              </CListGroupItem>
              <CListGroupItem>
                <div style="width: 15%; float: left; font-weight: 600">
                  Họ và tên 
                </div>
                <span>{{ getData.fullname }}</span>
              </CListGroupItem>
              <CListGroupItem>
                <div style="width: 15%; float: left; font-weight: 600">
                  Email 
                </div>
                <span>{{ getData.email }}</span>
              </CListGroupItem>
              <CListGroupItem>
                <div style="width: 15%; float: left; font-weight: 600">
                  Điện thoại
                </div>
                <span>{{ getData.phone }}</span>
              </CListGroupItem>
              <CListGroupItem>
                <div style="width: 15%; float: left; font-weight: 600">
                  Địa chỉ
                </div>
                <span>{{ getData.address }}</span>
              </CListGroupItem>
              <CListGroupItem>
                <div style="width: 15%; float: left; font-weight: 600">
                  Vai trò
                </div>
                <span>
                    {{getData.roles.join(", ")}}
                </span>
              </CListGroupItem>
            </CListGroup>
          </CCardBody>
        </CCard>
      </CCol>
    </CRow>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "QuanLyTaiKhoanDetail",
  props: {
    item: Number,
  },
  data() {
    return {
      getData: "",
      getUser: {},
      selected: [], // Must be an array reference!
      show: true,

      formCollapsed: true,
    };
  },
  mounted() {
    this.getDetailAccount();
  },
  methods: {
    getDateString(date) {
      return `${new Date(date).getFullYear()}-${
        new Date(date).getMonth() + 1
      }-${new Date(date).getDate()} ${new Date(date).getHours()}:${new Date(
        date
      ).getMinutes()}:${new Date(date).getSeconds()}`;
    },
    formatPrice(value) {
      let val = (value / 1).toFixed(0).replace(".", ",");
      return val.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ".");
    },
    validator(val) {
      return val ? val.length >= 4 : false;
    },
    CancelCreate() {
      this.$router.push("/account/quanlyaccount");
    },
    updateAccount(id) {
    console.log(id)
      this.$router.push(
          {
        name: "Cập nhật thông tin tài khoản",
        params: { item: id },
      }
      );
    },
    getDetailAccount() {
    //   console.log(this.item);
      axios
        .get(
          this.$store.state.MainLink + "admin/account/find?id=" + this.item,
          {
            headers: {
              Authorization: this.$store.state.userToken,
            },
          }
        )
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

<style scoped>
.Title-font-size {
  font-weight: 600;
}
</style>
