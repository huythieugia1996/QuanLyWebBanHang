<template>
  <div>
    <div class="col-12 col-title">
      <div class="col-md-6 float-left margin-left">
        <p class="Text-tile">Danh sách sale</p>
        <p class="Text-tile-2">Trang chủ ● Khuyến mại</p>
      </div>
      <div class="col-md-6 float-right">
        <button
          type="button"
          class="btn btn-primary float-right btn-add"
          @click="create()"
        >
          <i class="cil-plus"></i>
          Thêm mới
        </button>
         <!-- <button
          type="button"
          class="btn btn-primary float-right btn-add"
          @click="detail()"
        >
          <i class="cil-plus"></i>
          Chi tiết
        </button> -->
      </div>
    </div>
    <nav class="col-12 navbar justify-content-between">
      <!-- <a class="navbar-brand"></a>
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
      </form> -->
    </nav>
    
      <CCardBody>
        <CNav justified variant="tabs">
          <CNavItem active><p @click="getAllSale()" style="margin-bottom: 0">Tất cả</p></CNavItem>
          <CNavItem><p @click="getSaleNow()" style="margin-bottom: 0">Đang diễn ra</p></CNavItem>
          <CNavItem><p @click="getAboutSale()" style="margin-bottom: 0">Sắp diễn ra</p></CNavItem>
          <CNavItem><p @click="getSaleEnd()" style="margin-bottom: 0">Đã kết thúc</p></CNavItem>
        </CNav>
        <table class="table table-hover">
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
            <tr  v-for="item in pageOfItems" :key="item.id">
              <th>{{ item.id }}</th>
              <th scope="row" class="td-table">{{ item.name }}</th>
              <td scope="row" class="td-table" style="text-align:center">{{ getDateString(item.startTime) }}</td>
              <td scope="row" class="td-table" style="text-align:center">{{ getDateString(item.endTime) }}</td>
              <td scope="row" class="td-table td-center" style="text-align:center">
                  <span class="badge rounded-pill bg-primary" v-if="item.status == 'đang sale'">{{ item.status }}</span>
                  <span class="badge rounded-pill bg-danger" v-else-if="item.status == 'Đã dừng'">{{ item.status }}</span>
                  <span class="badge rounded-pill bg-success" v-else>{{ item.status }}</span>
              </td>
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
      </CCardBody>
    <!-- <div class="pb-0 pt-0" style="text-align: center">
      <jw-pagination
        :maxPages="15"
        :items="getData"
        @changePage="onChangePage"
      ></jw-pagination>
    </div> -->
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "Quanlykhuyenmai",
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
    // onChangePage(pageOfItems) {
    //   this.pageOfItems = pageOfItems;
    // },
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
    SaleEnd() {
      this.$router.push("/sale/quanlysaleEnd");
    },
    SaleNow() {
      this.$router.push("/sale/quanlysaleEnd");
    },
     onChangePage(pageOfItems) {
      this.pageOfItems = pageOfItems;
    },
     create() {
      this.$router.push("/sale/quanlysaleCreate");
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
    detail(){
      this.$router.push("/sale/quanlysaleDetail");
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
    getAllSale() {
      axios
        .get( this.$store.state.MainLink + "admin/sale/get",
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
    getSaleNow() {
      axios
        .get(this.$store.state.MainLink + "admin/sale/getSaleNow", {
          headers: {
            Authorization: this.$store.state.userToken,
          },
        })
        .then((response) => {
          this.getData = response.data.object;
          console.log(response);
        })
        .catch((e) => {
          console.log(e);
        });
    },
    getAboutSale() {
      axios
        .get(this.$store.state.MainLink + "admin/sale/getSaleAboutStart", {
          headers: {
            Authorization: this.$store.state.userToken,
          },
        })
        .then((response) => {
          this.getData = response.data.object;
          console.log(response);
        })
        .catch((e) => {
          console.log(e);
        });
    },
    getSaleEnd() {
      axios
        .get(this.$store.state.MainLink + "admin/sale/getSellEnd", {
          headers: {
            Authorization: this.$store.state.userToken,
          },
        })
        .then((response) => {
          this.getData = response.data.object;
          console.log(response);
        })
        .catch((e) => {
          console.log(e);
        });
    },
    getDateString(date) {
      return `${new Date(date).getFullYear()}-${
        new Date(date).getMonth() + 1
      }-${new Date(date).getDate()} ${new Date(date).getHours()}:${new Date(
        date
      ).getMinutes()}:${new Date(date).getSeconds()}`;
    },
  },

};
</script>

<style>
.Title-table {
  text-align: center;
}
</style>
