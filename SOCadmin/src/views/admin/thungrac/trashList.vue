<template>
  <div>
    <div class="col-12 col-title">
      <div class="col-md-6 float-left margin-left">
        <p class="Text-tile">Danh sách sản phẩm</p>
        <p class="Text-tile-2">Trang chủ ● Sản phẩm</p>
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
    <nav class="col-12 navbar justify-content-between">
      <a class="navbar-brand"></a>
      <form class="form-inline">
        <input
          @change="searchProduct()"
          class="form-control mr-sm-2"
          type="search"
          placeholder="Search"
          aria-label="Search"
          style="box-shadow: none"
          v-model="searchString"
        />
      </form>
    </nav>
    <table class="table table-hover">
      <thead>
        <tr>
          <th scope="col">STT</th>
          <th scope="col" class="Title-table" colspan="1">Tên sản phẩm</th>
          <th scope="col" class="Title-table td-action" colspan="1">
            Tình trạng sản phẩm
          </th>
          <th class="Title-table"></th>
        </tr>
      </thead>
      <tbody>
        <!-- <div class="spinner-grow" role="status" >
          <span class="visually-hidden">Loading...</span>
        </div>
        <div class="spinner-border" role="status">
          <span class="visually-hidden">Loading...</span>
        </div> -->
        <th colspan="4" v-if="pageOfItems == ''">
          <div class="text-center">
            <div class="spinner-grow" role="status">
              <span class="visually-hidden">Loading...</span>
            </div>
          </div>  
        </th>

        <tr v-for="item in pageOfItems" :key="item.id" class="text-center">
          <th>{{ item.id }}</th>
          <td scope="row" class="td-table"  @click="DetailProduct(item.id)">{{ item.name }}</td>
          <td :style="{ color: Status(item) }">
            {{ item.status }}
          </td>
          <td class="td-table td-action">
            <CButton  @click="darkModal = true, setId(item.id), setTitle()" class="mr-1" v-if="item.status != 'Không kinh doanh' && item.status != 'Ngừng kinh doanh'">
              <i class="cil-reload"></i>
              </CButton>
            <CButton  @click="darkModal = true, setId(item.id), setTitle1()" class="mr-1" v-if="item.status == 'Không kinh doanh' || item.status == 'Ngừng kinh doanh'">
               <i class="cil-trash" style="color: red; text-align: center;"></i>
              </CButton>
          </td>
        </tr>
      </tbody>
    </table>
    <!-- <div class="card-body"></div> -->
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
      title="Modal title 2"
      size="lg"
      color="danger"
    >
      Bạn có chắc muốn {{setTitleModal}} sản phẩm này ?
      <template #header>
        <h6 class="modal-title">Xác nhận</h6>
        <CButtonClose @click="darkModal = false" class="text-white"/>
      </template>
      <template #footer>
        <CButton @click="darkModal = false" color="secondary">Hủy</CButton>
        <CButton @click="darkModal = false, deleteProduct()" color="danger" v-if="setTitleModal == 'xóa'">{{AcctionButton}}</CButton>
        <CButton @click="darkModal = false, dontSell()" color="danger" v-else-if="setTitleModal == 'ngừng kinh doanh'">{{AcctionButton}}</CButton>
      </template>
    </CModal>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "QuanLySanPhamList",
  data() {
    return {
      darkModal: false,
      setIdProduct: "",
      pageOfItems: [],
      getData: "",
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
      searchString: "",
      setTitleModal: "",
      AcctionButton: "",
    };
  },
  created() {
    this.getAllProduct();
  },
  methods: {
    setTitle(){
      this.setTitleModal = "ngừng kinh doanh"
      this.AcctionButton = this.setTitleModal.charAt(0).toUpperCase() +   this.setTitleModal.slice(1);
    },
    setTitle1(){
      this.setTitleModal = "xóa"
      this.AcctionButton = this.setTitleModal.charAt(0).toUpperCase() +   this.setTitleModal.slice(1);
    },
    setId(id){
      this.setIdProduct = id
    },
    onChangePage(pageOfItems) {
      this.pageOfItems = pageOfItems;
    },
    searchProduct() {
      axios
        .get(
          this.$store.state.MainLink + "admin/products?find="
        )
        .then((response) => {
          this.getData = response.data.object;
        })
        .catch((e) => {
          console.log(e);
        });
    },
    CreateNewProduct() {
      this.$router.push("/admin/quanlysanphamcreate");
    },
    UpdateProduct() {
      this.$router.push("/admin/quanlysanphamcreatedetail");
    },
    DetailProduct(id) {
      this.$router.push({
        name: "Thông tin chi tiết sản phẩm",
        params: { item: id },
      });
    },
    deleteProduct() {
      axios
        .delete(
          this.$store.state.MainLink + "admin/products/delete/" + this.setIdProduct,
          {
            headers: {
              Authorization: this.$store.state.userToken,
            },
          }
        )
        .then(() => {
          this.getAllProduct();
        })
        .catch((e) => {
          console.log(e);
        });
    },
    dontSell(){
      axios
        .delete(
          this.$store.state.MainLink + "admin/products/dontSell/" + this.setIdProduct,
          {
            headers: {
              Authorization: this.$store.state.userToken,
            },
          }
        )
        .then(() => {
          this.getAllProduct();
        })
        .catch((e) => {
          console.log(e);
        });
    },
    Status(products) {
      switch (products.status) {
        case "Đang bán":
          return "green";
        case "Không kinh doanh":
          return " gray";
        case "Hàng sắp về":
          return "black";
        case "Chưa có":
          return " orange";
        case "Hết hàng":
          return " red";
        case "Ngừng kinh doanh":
          return " blue";
        case "Đã xóa":
          return " red";
      }
    },
    getAllProduct() {
      axios
        .get(this.$store.state.MainLink + "admin/products/getListDelete",
          {
            headers: {
              Authorization: this.$store.state.userToken,
            },
          })
        .then((response) => {
          this.getData = response.data.object;
        })
        .catch((e) => {
          console.log(e);
        });
    },
  },
};
</script>

<style>
.Title-table {
  text-align: center;
}
</style>
