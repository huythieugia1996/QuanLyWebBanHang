<template>
  <div>
    <div class="col-12 col-title">
      <div class="col-md-6 float-left margin-left">
        <p class="Text-tile">Danh sách hóa đơn</p>
        <p class="Text-tile-2">Trang chủ ● Hóa đơn</p>
      </div>
      <div class="col-md-6 float-right">
        <button
          type="button"
          class="btn btn-primary float-right btn-add"
          @click="CreateNewProduct()"
        >
          <i class="cil-plus"></i>
          Thêm mới
        </button>
      </div>
    </div>
    <table class="table table-hover">
      <thead>
        <tr>
          <th scope="col">Mã hóa đơn</th>
          <th scope="col" class="Title-table">Username</th>
          <th scope="col" class="Title-table td-action">Tổng tiền</th>
          <th class="Title-table">Thời gian thanh toán</th>
          <th class="Title-table">Trạng thái</th>
          <th>

          </th>
        </tr>
      </thead>
      <tbody>
        <tr
          v-for="item in pageOfItems"
          :key="item.id"
        >
          <th>{{ item.id }}</th>
          <th scope="row" class="td-table-custom" @click="detailOrder(item.id)">{{ item.username }}</th>
          <td class="td-table status-color-out td-action">
            {{ formatPrice(item.sumprice) }} đ
          </td>
          <td scope="row" class="td-table-custom">
            {{ getDateString(item.dateCreated) }}
          </td>
          <td scope="row" class="td-table td-center" style="text-align:center">
                  <span class="badge rounded-pill bg-secondary" v-if="item.status == 'Chờ xác nhận'">{{ item.status }}</span>
                  <span class="badge rounded-pill bg-success" v-else-if="item.status == 'Giao hàng thành công'">{{ item.status }}</span>
                  <span class="badge rounded-pill bg-primary" v-else-if="item.status == 'Đã xác nhận'">{{ item.status }}</span>
                  <span class="badge rounded-pill bg-danger" v-else>{{ item.status }}</span>
          </td>
          <th class="text-center">
              <CButton class="mr-1" v-if="item.status == 'Chờ xác nhận'" @click="successModal = true,setId(item.id)">
               <i class="cil-check-circle" style="color: green; text-align: center;"></i>
              </CButton>
              <CButton class="mr-1" v-if="item.status == 'Đã xác nhận'" @click="successModal = true,setId(item.id)">
               <i class="cil-car-alt" style="color: green; text-align: center;"></i>
              </CButton>
              <CButton class="mr-1" v-if="item.status == 'Đã xác nhận'" @click="successModal = true,setId(item.id)">
               <i class="cil-reload" style="color: red; text-align: center;"></i>
              </CButton>
              <CButton class="mr-1" v-if="item.status == 'Chờ xác nhận'" @click="successModal = true,setId(item.id)">
               <i class="cil-reload" style="color: red; text-align: center;"></i>
              </CButton>
              <CButton @click="darkModal = true,setId(item.id)" class="mr-1" v-if="item.status != 'Giao hàng thành công' && item.status != 'Đã hủy'">
               <i class="cil-x-circle" style="color: red; text-align: center;"></i>
              </CButton>
          </th>
        </tr>
      </tbody>
    </table>
    <div class="pb-0 pt-3 text-center">
      <jw-pagination
        :maxPages="15"
        :items="getData"
        @changePage="onChangePage"
      ></jw-pagination>
    </div>
    <CModal
      :show.sync="darkModal"
      :no-close-on-backdrop="true"
      :centered="true"
      title="Hủy đơn"
      size="lg"
      color="danger"
    >
      <CTextarea
                label="Lý do hủy đơn"
                placeholder="Nội dung ..."
                horizontal
                rows="9"
                v-model="note"
      />
      
      <template #header>
        <h6 class="modal-title">Xác nhận</h6>
        <CButtonClose @click="darkModal = false" class="text-white"/>
      </template>
      <template #footer>
        <CButton @click="darkModal = false" color="secondary">Hủy</CButton>
        <CButton @click="darkModal = false, cancelOrder()" color="danger">Hủy đơn hàng</CButton>
        <!-- <CButton @click="darkModal = false, dontSell()" color="danger" v-else-if="setTitleModal == 'ngừng kinh doanh'">{{AcctionButton}}</CButton> -->
      </template>
    </CModal>

    <CModal
      :show.sync="successModal"
      :no-close-on-backdrop="true"
      :centered="true"
      title="Xác nhận đơn hàng"
      size="lg"
      color="success"
    >
      <CTextarea
                label="Ghi chú"
                placeholder="Nội dung ..."
                horizontal
                rows="9"
                v-model="note"
      />
      
      <template #header>
        <h6 class="modal-title">Xác nhận</h6>
        <CButtonClose @click="successModal = false" class="text-white"/>
      </template>
      <template #footer>
        <CButton @click="successModal = false" color="secondary">Hủy</CButton>
        <CButton @click="successModal = false, confirmOrder()" color="success">Xác nhận đơn hàng</CButton>
        <!-- <CButton @click="darkModal = false, dontSell()" color="danger" v-else-if="setTitleModal == 'ngừng kinh doanh'">{{AcctionButton}}</CButton> -->
      </template>
    </CModal>

  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "Quanlyhoadon",
  data() {
    return {
      pageOfItems: [],
      successModal: false,
      darkModal : false,
      setIdOrder: "",
      getData: "",
      note: "",
      formData: {
        name: "",
        price: "",
        status: "",
        orderDetails: "",
        blogs: "",
        productCategories: "",
        sales: "",
        productColors: [],
        cartDetails: "",
        productDetails: [],
      },
    };
  },
  created() {
    this.getAllProduct();
  },
  methods: {
    setId(id){
      this.setIdOrder = id
    },
    formatPrice(value) {
      let val = (value / 1).toFixed(0).replace(".", ",");
      return val.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ".");
    },
     onChangePage(pageOfItems) {
      this.pageOfItems = pageOfItems;
    },
    getDateString(date) {
      return `${new Date(date).getFullYear()}-${
        new Date(date).getMonth() + 1
      }-${new Date(date).getDate()} ${new Date(date).getHours()}:${new Date(
        date
      ).getMinutes()}:${new Date(date).getSeconds()}`;
    },
    CreateNewProduct() {
      this.$router.push({
        name: "Thêm mới hóa đơn",
      });
    },
    detailOrder(id) {
      this.$router.push({
        name: "Thông tin chi tiết hóa đơn",
        params: { item: id },
      });
    },
    getAllProduct() {
      axios
        .get(this.$store.state.MainLink + "customer/orders/getall",
         {
            headers: {
              Authorization: this.$store.state.userToken,
            },
          })
        .then((response) => {
          this.getData = response.data.object;
          // console.log(response.data.object);
        })
        .catch((e) => {
          console.log(e);
        });
    },
    confirmOrder(){
      let item = {
        note : this.note
      }
      console.log(item);
      axios
        .post(this.$store.state.MainLink + "admin/orders/confimOrder?id=" + this.setIdOrder, 
          item,
         {
            headers: {
              Authorization: this.$store.state.userToken,
            },
          })
        .then(() => {
          this.getAllProduct()
          this.note = ""
        })
        .catch((e) => {
          console.log(e);
        });
    },
    cancelOrder(){
      let item = {
        note : this.note
      }
      axios
        .post(this.$store.state.MainLink + "admin/orders/cancerOrder?id=" + this.setIdOrder, item,
         {
            headers: {
              Authorization: this.$store.state.userToken,
            },
          })
        .then(() => {
          this.getAllProduct()
          this.note = ""
        })
        .catch((e) => {
          console.log(e);
        });
    }
  },
};
</script>

<style>
.Title-table {
  text-align: center;
}
.td-table-custom {
  text-align: center;
}
</style>
