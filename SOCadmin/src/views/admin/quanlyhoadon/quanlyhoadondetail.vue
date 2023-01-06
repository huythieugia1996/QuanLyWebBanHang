<template>
  <div>
    <CRow>
      <CCol md="12" lg="6">
        <CCard>
          <CCardHeader>
            <CIcon name="cil-justify-center" />
            <strong> Thông tin hóa đơn </strong>
          </CCardHeader>
          <CCardBody height="auto">
            <CListGroup>
              <CListGroupItem>
                <span class="Title-font-size">Mã đơn hàng : </span>
                <span>{{ getData.id }}</span>
              </CListGroupItem>
              <CListGroupItem>
                <span class="Title-font-size">Tổng tiền : </span>
                <span>{{ formatPrice(getData.sumprice) }} đ</span>
              </CListGroupItem>
              <CListGroupItem>
                <span class="Title-font-size">Thời gian mua hàng : </span>
                <span>{{ getDateString(getData.dateCreated) }}</span>
              </CListGroupItem>
              <!-- <CListGroupItem>
                <span class="Title-font-size">username : </span>
                <span>{{ getData.username }} </span>
              </CListGroupItem> -->
              <CListGroupItem>
                <span class="Title-font-size">Trạng thái : </span>
                <span>{{ getData.status }} </span>
              </CListGroupItem>
            </CListGroup>
          </CCardBody>
        </CCard>
      </CCol>
      <CCol md="12" lg="6">
        <CCard>
          <CCardHeader>
            <CIcon name="cil-justify-center" />
            <strong> Thông tin khách hàng </strong>
          </CCardHeader>
          <CCardBody height="auto">
            <CListGroup>
              <CListGroupItem>
                <span class="Title-font-size">Tên khách hàng : </span>
                <span>{{ getUser.fullname }}</span>
              </CListGroupItem>
              <CListGroupItem>
                <span class="Title-font-size">Email : </span>
                <span>{{ getUser.email }}</span>
              </CListGroupItem>
              <CListGroupItem>
                <span class="Title-font-size">Địa chỉ : </span>
                <span>{{ getUser.address }}</span>
              </CListGroupItem>
              <CListGroupItem>
                <span class="Title-font-size">Điện thoại : </span>
                <span>{{ getUser.phone }}</span>
              </CListGroupItem>
              <CListGroupItem>
                <span class="Title-font-size">Ghi chú : </span>
                <span>{{ getUser.note }}</span>
              </CListGroupItem>
            </CListGroup>
          </CCardBody>
        </CCard>
      </CCol>
      <CCol md="12">
        <CCard>
          <CCardHeader>
            <CIcon name="cil-justify-center" />
            <strong> Thông tin các sản phẩm được mua </strong>
          </CCardHeader>
          <CCardBody height="auto">
            <CListGroup
              v-for="(item, index) in getData.orderDetails"
              :key="index"
            >
              <div style="font-weight: 700; margin: 5px 5px 5px 5px">
                {{ index + 1 }} - {{ item.productName }}
              </div>
              <CListGroupItem>
                <span class="Title-font-size">Giá tiền : </span>
                <span>{{ formatPrice(item.price) }} đ</span>
              </CListGroupItem>
              <CListGroupItem>
                <span class="Title-font-size">Số lượng : </span>
                <span>{{ item.quantity }}</span>
              </CListGroupItem>
              <CListGroupItem>
                <span class="Title-font-size">Giảm giá : </span>
                <span>{{ item.discount }}%</span>
              </CListGroupItem>
              <br />
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
  name: "QuanLyHoaDonDetail",
  props: {
    item: Number,
  },
  data() {
    return {
      getData: "",
      getUser: {},
      selected: [], // Must be an array reference!
      show: true,
      horizontal: { label: "col-3", input: "col-9" },
      options: [
        "Dell",
        "HP",
        "LG",
        "Apple",
        "Asus",
        "Lenovo",
        "VAIO",
        "Acer",
        "Samsung",
        "Toshiba",
        "MSI",
      ],
      selectOptions: [
        "Dell",
        "HP",
        "LG",
        "Apple",
        "Asus",
        "Lenovo",
        "VAIO",
        "Acer",
        "Samsung",
        "Toshiba",
        "MSI",
        {
          value: "some value",
          label: "Selected option",
        },
      ],
      selectedOption: "some value",

      formCollapsed: true,
    };
  },
  created() {
    this.getDetailProduct();
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
      this.$router.push("/quanlysanphamlist");
    },
    UpdateProduct() {
      this.$router.push("/quanlysanphamupdate");
    },
    getDetailProduct() {
      console.log(this.item)
      axios
        .get(this.$store.state.MainLink + "admin/orders/" + this.item, {
          headers: {
            Authorization: this.$store.state.userToken,
          },
        })
        .then((response) => {
          console.log(response)
          this.getData = response.data.object;
          this.getUser = this.getData.customer;
          console.log(this.getUser);
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
