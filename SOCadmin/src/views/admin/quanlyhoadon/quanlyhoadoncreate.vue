<template>
  <div>
    <CRow>
      <CCol md="12">
        <CButton
          type="reset"
          size="sm"
          color="danger"
          class="btn btn-custom-size"
          @click="CancelCreate()"
        >
          <i class="cil-x"></i>
          Huỷ
        </CButton>
        <CButton
          type="submit"
          size="sm"
          color="primary"
          class="btn btn-custom-size"
        >
          <i class="cil-plus"></i>
          Lưu sản phẩm
        </CButton>
      </CCol>
      <br />
      <br />

      <CCol md="12" lg="12">
        <CCard>
          <CCardHeader>
            <CIcon name="cil-justify-center" />
            <strong> Thông tin khách hàng </strong>
          </CCardHeader>
          <CCardBody height="auto">
            <CListGroup>
              <CListGroupItem>
                <div style="width: 15%; float: left; font-weight: 600">
                  Họ và tên
                </div>

                <input
                  type="text"
                  class="input-custom-border-none"
                  placeholder="Nhập họ tên khách hàng"
                  style="width: 70%"
                />
              </CListGroupItem>
              <CListGroupItem>
                <div style="width: 15%; float: left; font-weight: 600">
                  Email
                </div>
                <input
                  type="text"
                  class="input-custom-border-none"
                  placeholder="Nhập email khách hàng"
                  style="width: 70%"
                />
              </CListGroupItem>
              <CListGroupItem>
                <div style="width: 15%; float: left; font-weight: 600">
                  Điện thoại
                </div>

                <input
                  type="text"
                  class="input-custom-border-none"
                  placeholder="Số điện thoại"
                  style="width: 70%"
              /></CListGroupItem>
              <CListGroupItem>
                <div style="width: 15%; float: left; font-weight: 600">
                  Địa chỉ
                </div>
                <input
                  type="text"
                  class="input-custom-border-none"
                  placeholder="Địa chỉ cụ thể"
                  style="width: 70%"
              /></CListGroupItem>
            </CListGroup>
          </CCardBody>
        </CCard>
      </CCol>
      <CCol md="12" lg="12">
        <CCard>
          <CCardHeader>
            <CIcon name="cil-justify-center" />
            <strong> Chi tiết đơn hàng </strong>
          </CCardHeader>
          <CCardBody height="auto">
            <CListGroup>
              <table class="table">
                <thead>
                  <tr>
                    <th scope="col">
                      <button @click="createNewValue()" style="border: none">
                        +
                      </button>
                    </th>
                    <th scope="col">Sản phẩm</th>
                    <th scope="col">Số lượng</th>
                    <th scope="col">Đơn giá</th>
                    <th scope="col">Giảm giá</th>
                    <th scope="col"></th>
                  </tr>
                </thead>
                <tbody>
                  <tr v-for="(item, index) in orderDetails" :key="index">
                    <th scope="row">{{ index + 1 }}</th>
                    <td>
                      <select
                        class="custom-select"
                        id="inputGroupSelect03"
                        aria-label="Example select with button addon"
                        v-model="item.productId"
                      >
                        <option selected>Chọn sản phẩm</option>
                        <option
                          v-for="items in getData"
                          :key="items.id"
                          :value="items.id"
                        >
                          {{ items.name }}
                        </option>
                      </select>
                    </td>
                    <td><input type="text" v-model="item.quantity" /></td>
                    <td>{{ (item.price = item.quantity) }} đ</td>
                    <td><input type="text" v-model="item.discount" /></td>
                    <td>
                      <i
                        class="cil-trash"
                        style="color: red"
                        @click="deleteProduct(index)"
                      ></i>
                    </td>
                  </tr>
                </tbody>
              </table>
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
  name: "QuanLySanPhamDetail",

  data() {
    return {
      selected: [], // Must be an array reference!
      show: true,
      horizontal: { label: "col-3", input: "col-9" },
      selectedOption: "some value",

      orderDetails: [],

      formCollapsed: true,
    };
  },
  created() {
    this.getAllProduct();
  },
  methods: {
    validator(val) {
      return val ? val.length >= 4 : false;
    },
    CancelCreate() {
      this.$router.push("/bill/quanlyhoadonlist");
    },
    UpdateProduct() {
      this.$router.push("/quanlysanphamupdate");
    },
    createNewValue() {
      let item = {
        productId: 0,
        quantity: 0,
        price: 0,
        discount: 0,
      };
      this.orderDetails.push(item);
      console.log(this.orderDetails);
    },
    deleteProduct(index) {
      //   console.log(index);
      this.orderDetails.splice(index, 1);
    },
    getAllProduct() {
      axios
        .get(this.$store.state.MainLink + "customer/products")
        .then((response) => {
          this.getData = response.data.object;
          //   console.log(response.data.object);
        })
        .catch((e) => {
          console.log(e);
        });
    },
  },
};
</script>

<style scope>
.btn-custom-size {
  width: 130px;
  margin-left: 15px;
  float: right;
}
.input-custom-border-none {
  border: none;
  box-shadow: none;
  box-sizing: border-box;
}
.title-td {
  width: 35%;
}
</style>
