<template>
  <div>
    <CRow>
      <CCol md="12" lg="7">
        <CCard>
          <CCardHeader>
            <CIcon name="cil-justify-center" />
            <!-- <i class="cil-image"></i>  -->
            <strong> Ảnh sản phẩm</strong>
          </CCardHeader>
          <CCardBody>
            <CCarousel arrows indicators animate height="auto" > 
              <!-- {{getData}} -->
              
              <!-- <CCarouselItem v-for="itemPhoto in getData.photos" :key="itemPhoto"
                image="itemPhoto"
              /> -->

              <div class="container">
                <div class="row row-cols-3">
                  <div class="col" v-for="itemPhoto in getData.photos" :key="itemPhoto">
                    <img :src="itemPhoto" class="d-block w-100" alt="...">
                  </div>
                </div>
              </div>
            </CCarousel>
          </CCardBody>
        </CCard>
      </CCol>
      <CCol md="12" lg="5">
        <CCard>
          <CCardHeader>
            <CIcon name="cil-justify-center" />
            <strong> Thông tin của sản phẩm </strong>
          </CCardHeader>
          <CCardBody height="auto">
            <CListGroup>
              <CListGroupItem>
                <span class="Title-font-size">Mã số máy : </span>
                <span>{{ getData.id }}</span>
              </CListGroupItem>
              <CListGroupItem>
                <span class="Title-font-size">Tên máy : </span>
                <span>{{ getData.name }}</span>
              </CListGroupItem>
              <CListGroupItem>
                <span class="Title-font-size">Thương hiệu : </span>
                <span>{{ getData.id }}</span>
              </CListGroupItem>
              <CListGroupItem>
                <span class="Title-font-size">Giá : </span>
                <span>{{ formatPrice(getData.price) }} đ</span>
              </CListGroupItem>
              <CListGroupItem>
                <span class="Title-font-size">Ngày đăng : </span>
                <span>{{ getData.id }}</span>
              </CListGroupItem>
              <CListGroupItem>
                <span class="Title-font-size">Trạng thái : </span>
                <span>{{ getData.status }}</span>
              </CListGroupItem>
              <CListGroupItem>
                <span class="Title-font-size">Thời gian bảo hành: : </span>
                <span>{{ getData.warranty }} Tháng</span>
              </CListGroupItem>
            </CListGroup>
          </CCardBody>
        </CCard>
      </CCol>

      <CCol md="12">
        <!-- <strong> Thông tin cấu hình sản phẩm </strong> -->
        <table class="table" style="background: white">
          <tbody>
            <thead>
              <tr>
                <th scope="col">Thông tin cấu hình sản phẩm</th>
              </tr>
            </thead>
            <tr v-for="item in getData.productDetails" :key="item">
              <td>{{item.propertyName}}</td>
              <td>{{item.propertyValue}}</td>
            </tr>        
          </tbody>
        </table>
      </CCol>
    </CRow>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "QuanLySanPhamDetail",
  props: {
    item: Number
  },
  data() {
    return {
      getData: "",
      selected: [], // Must be an array reference!
      show: true,
      horizontal: { label: "col-3", input: "col-9" },
      selectedOption: "some value",

      formCollapsed: true,
    };
  },
  created() {
    this.getDetailProduct();
  },
  methods: {
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
      axios
        .get(this.$store.state.MainLink + "customer/products/" + this.item)
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
