<template>
  <div>
    <div class="col-12 col-title">
      <div class="col-md-6 float-left margin-left">
        <p class="Text-tile">Danh sách thể loại</p>
        <p class="Text-tile-2">Trang chủ ● Thể loại</p>
      </div>

      <!-- Hiện thị modal thêm mới -->
      <div class="col-md-6 float-right">
        <b-button id="show-btn" @click="showModal">Thêm mới</b-button>
        <b-modal ref="my-modal" hide-footer title="Thêm mới thể loại">
          <div class="form-group">
            <label>Tên danh mục</label>
            <input type="text" class="form-control" v-model="categoryName" placeholder="Tên danh mục">
          </div>
          <div class="form-group">
            <label>Danh mục cha</label>
            <select class="custom-select mr-sm-2" v-model="selectedCategoryParent" placeholder="Danh mục cha">
              <option v-for="user in listCategoryParent" :key="user.id" :value="user.id">
                {{ user.text }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label>Trạng thái</label>
            <select class="custom-select mr-sm-2" v-model="selectedStatus" placeholder="Trạng thái">
              <option selected value="true">Hoạt động</option>
              <option value="false">Không Hoạt động</option>
            </select>
          </div>
          <div style="width: 100%;">
            <b-button style="width: 20%;float: right;margin: 10px;"  variant="outline-danger"  @click="hideModal">Lưu</b-button>
            <b-button style="width: 20%;float: right;margin: 10px;"  variant="outline-warning"  @click="toggleModal">Hủy</b-button>
          </div>
        </b-modal>
      </div>
      <!-- Kết thúc Modal -->
    </div>
    <table class="table">
      <thead>
        <tr @click="detailCategory()">
          <th scope="col">STT</th>
          <th scope="col" class="Title-table td-action" style="text-align:center">
            Tên
          </th>
          <th scope="col" class="Title-table td-action">
            Loại
          </th>
          <th scope="col" class="Title-table td-action">
            Trạng thái
          </th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(item, index) in getData" :key="index">
          <th>{{ index + 1 }}</th>
          <td scope="row" class="td-table" style="width: 35%;">
            <a class="custom-a" data-bs-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false"
              aria-controls="collapseExample">
              - {{ item.name }}
            </a>
            <div class="collapse" id="collapseExample">
              <div class="">
                <div v-for="items in item.categories" :key="items.id" style="margin-left: 15%">+ {{ items.name }}</div>
              </div>
            </div>
          </td>
          <td scope="row" class="td-table td-center">{{ item.type }}</td>
          <td scope="row" class="td-table td-center">
            <span class="badge rounded-pill bg-success" v-if="item.status == true">{{ item.status }}</span>
            <span class="badge rounded-pill bg-danger" v-else>{{ item.status }}</span>
          </td>
        </tr>
      </tbody>
    </table>
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
      listCategoryParent: [],
      selectedCategoryParent: null,
      selectedStatus: null,
      categoryName: "",
    };
  },
  created() {
    this.getAllCategory();
    this.listCategoryParent = this.$store.state.ListCategory;

  },
  methods: {
    onChangePage(pageOfItems) {
      this.pageOfItems = pageOfItems;

      console.log(this.listCategoryPar);
    },
    // Thêm mới danh mục
    showModal() {
      this.$refs['my-modal'].show()
    },
    hideModal() {
      this.createCatagory();
    },
    toggleModal() {
      this.$refs['my-modal'].hide();
    },

    createCatagory(){

      var body = {
        name: this.categoryName,
        type: this.selectedCategoryParent,
        status: this.selectedStatus
      };
      axios.post(this.$store.state.MainLink + "admin/categories/newcategory",body, {
            headers: {
              Authorization: this.$store.state.userToken,
            },
          })
          .then((response) => {
            this.getAllCategory();
            this.$refs['my-modal'].hide();
        })
        .catch((e) => {
          console.log(e);
        });
    },

    getAllCategory() {
      axios
        .get(this.$store.state.MainLink + "customer/categories",
          {
            headers: {
              Authorization: this.$store.state.userToken,
            },
          })
        .then((response) => {
          this.getData = response.data.object;
          console.log(this.getData)
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

.td-center {
  text-align: center;
}

.custom-a {
  text-decoration: none;
  color: black;
}
</style>
