 <template>
  <div>
    <div class="col-12 col-title">
      <div class="col-md-6 float-left margin-left">
        <p class="Text-tile">Danh sách category</p>
        <p class="Text-tile-2">Trang chủ ● category</p>
      </div>
      <div class="col-md-6 float-right">
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
          <!-- <th scope="col" class="Title-table">Tên sale</th> -->
          <th scope="col" class="Title-table td-action" style="text-align:center" >
             name
          </th>
          <th scope="col" class="Title-table td-action" >
            type
          </th>
          <th scope="col" class="Title-table td-action" >
           status
          </th>
          <th scope="col" class="Title-table" >
           
          </th>
         
          <!-- <th class="Title-table"></th> -->
          
        </tr>
      </thead>
      <tbody>
        <tr v-for="item in pageOfItems" :key="item.id">
          <th>{{ item.id }}</th>
          <td scope="row" class="td-table">{{ item.name }}</td>
          <td scope="row" class="td-table">{{ item.type }}</td>
          <td scope="row" class="td-table">{{ item.status }}</td>
          <td class="td-table td-action">
            <button
              type="button"
              class="btn btn-primary btn-size"
              @click="DetailProduct(item.id)"
            >
              <i class="cil-folder-open"></i>
            </button>
            <button
              type="button"
              class="btn btn-danger btn-size"
              @click="deleteCategory(item)"
            >
              <i class="cil-trash"></i>
            </button>
          </td>
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
        type: "",
        status: ""
      },
      searchString: "",
    };
  },
  created() {
    this.getAllCategory();

  },
  methods: {
    onChangePage(pageOfItems) {
      this.pageOfItems = pageOfItems;
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
    deleteCategory(item) {
      console.log(this.$store.state.tokenUser);
      axios
        .put(
          this.$store.state.MainLink + "/categories/delete/" + item.id,
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
  
    
    getAllCategory() {
      axios
        .get( this.$store.state.MainLink + "customer/categories",
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
