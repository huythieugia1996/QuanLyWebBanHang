<template>
  <div>
    <div class="col-12 col-title">
      <div class="col-md-6 float-left margin-left">
        <p class="Text-tile">Danh sách sale</p>
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
    <table class="table">
      <thead>
        <tr>
          <th scope="col">STT</th>
          <th scope="col" class="Title-table">Tên sale</th>
          <th scope="col" class="Title-table td-action" style="text-align:center" >
            Thời gian bắt đầu sale
          </th>
          <th scope="col" class="Title-table td-action" >
            Thời gian kết thúc
          </th>
          <th scope="col" class="Title-table td-action" >
            Trạng thái
          </th>
          <th class="Title-table"></th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="item in pageOfItems" :key="item.id">
          <th>{{ item.id }}</th>
          <th scope="row" class="td-table">{{ item.name }}</th>
          <th scope="row" class="td-table">{{ item.startTime }}</th>
          <th scope="row" class="td-table">{{ item.endTime }}</th>
          <th scope="row" class="td-table">{{ item.status }}</th>


          <!-- <td :style="{ color: Status(item) }">
            {{ item.status }}
          </td> -->
          <!-- <td class="td-table td-action">
            <button
              type="button"
              class="btn btn-primary btn-size"
              @click="DetailSale(item.id)"
            >
              <i class="cil-folder-open"></i>
            </button>
            <button
              type="button"
              class="btn btn-danger btn-size"
              @click="deleteProduct(item)"
            >
              <i class="cil-trash"></i>
            </button>
          </td> -->
        </tr>
      </tbody>
    </table>
    <div class="card-body"></div>
    <div class="card-footer pb-0 pt-3" style="text-align: center">
      <jw-pagination
        :maxPages="15"
        :items="getData"
        @changePage="onChangePage"
      ></jw-pagination>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "QuanLySanPhamList",
  data() {
    return {
      pageOfItems: [],
      getData: "",
      formData: {
        name: "",
        startTime: "",
        endTime: "",
        status: "",
      },
      searchString: "",
    };
  },
  created() {
    this.getAllSale();

  },
  methods: {
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
    DetailSale(id) {
      this.$router.push({
        name: "Thông tin chi tiết sản phẩm",
        params: { item: id },
      });
    },
    deleteProduct(item) {
      console.log(this.$store.state.tokenUser);
      axios
        .delete(
          this.$store.state.MainLink + "admin/products/delete/" + item.id,
          {
            headers: {
              Authorization: this.$store.state.tokenUser,
            },
          }
        )

        .then((response) => {
          if (response.data.object) {
            alert("Delete thành công.");
          } else {
            alert("Delete thất bại.");
          }
        })
        .catch((e) => {
          console.log(e);
        });
    },
    // Status(products) {

    //   switch (products.status) {
    //     case "Đang bán":
    //       return "green";
    //     case "Không kinh doanh":
    //       return " gray";
    //     case "Hàng sắp về":
    //       return "black";
    //     case "Chưa có":
    //       return " yellow";
    //     case "Hết hàng":
    //       return " red";
    //   }
    // },
    
    getAllSale() {
      axios
        .get( this.$store.state.MainLink + "admin/sale/getSellEnd",
         {
            headers: {
              Authorization: this.$store.state.userToken,
            },
          })
        .then((response) => {
          this.getData = response.data.object;
          console.log(response)
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
