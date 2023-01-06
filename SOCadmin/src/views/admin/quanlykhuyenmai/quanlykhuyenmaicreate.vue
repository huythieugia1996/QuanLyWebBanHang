<template>
  <div>
    <CRow>
      <br />
      <br />
      <CCol md="12" lg="7">
        <CCard>
          <CCardHeader>
            <CIcon name="cil-justify-center" />
            <strong> Sản phẩm khuyến mãi</strong>
          </CCardHeader>
          <CListGroupItem
            > 
            <input
              type="text"
              v-model="searchP"
              class="input-custom-border-none"
              placeholder="Nhập tên sản phẩm"
              style="width: 85%;border-bottom: 1px solid gray;padding: 9px;margin-right: 5%;"
            />
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit" @click="searchProduct()">
              <i class="cil-magnifying-glass"></i>
            </button>
          </CListGroupItem>
          <table class="table">
            <thead>
              <tr>
                <th scope="col" class="text-center">STT</th>
                <th scope="col" class="Title-table" colspan="1">Tên sản phẩm</th>
                <th scope="col" class="Title-table" colspan=""></th>
                <!-- <th class="Title-table"></th> -->
              </tr>
            </thead>
            <tbody class="text-center" v-if="getData.length > 0">
              <tr v-for="item in getData" :key="item.id">
                <th>{{ item.id }}</th>
                <th scope="row" class="td-table" colspan="1">
                  {{ item.name.substr(6,25) }} ...
                </th>
                <td class="td-table td-action" colspan="">
                  <button
                    type="button"
                    class="btn btn-primary btn-size"
                    @click="selectUser(item.userId)"
                  >
                    <i class="cil-check-circle">Chọn</i>
                  </button>
                </td>
              </tr>
            </tbody>
            <tbody v-else>
              <th scope="row" class="td-table" colspan="1">Không có dữ liệu</th>
            </tbody>
          </table>
        </CCard>
      </CCol>
      <CCol md="12" lg="5">
        <CCard>
          <CCardHeader>
            <CIcon name="cil-justify-center" />
            <strong>Thông tin khuyến mại</strong>
          </CCardHeader>
          <CCardBody height="auto">
            <CListGroup>
              <div class="form-group">
                <label for="">Tên chương trình khuyến mại:</label>
                <input
                  type="text"
                  class="form-control"
                  name=""
                  id=""
                  aria-describedby="helpId"
                  placeholder=""
                  v-model="formData.name"
                />
              </div>
              <div class="form-group">
                <label for="">Thời gian bắt đầu:</label>
                <input
                  type="date"
                  class="form-control"
                  name=""
                  id=""
                  aria-describedby="helpId"
                  placeholder=""
                  v-model="formData.startTime"
                />
              
              </div>
              <div class="form-group">
                <label for="">Thời gian kết thúc:</label>
                <input
                  type="date"
                  class="form-control"
                  name=""
                  id=""
                  aria-describedby="helpId"
                  placeholder=""
                  v-model="formData.endTime"
                />
              
              </div>
              <div class="form-group">
                <label for="">Trạng thái:</label>
                <input
                  type="text"
                  class="form-control"
                  name=""
                  id=""
                  aria-describedby="helpId"
                  placeholder=""
                  v-model="formData.status"
                />
              
              </div>
            </CListGroup>
          </CCardBody>
        </CCard>
      </CCol>
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
          @click="creatSale()"
        >
          <i class="cil-plus"></i>
          Lưu
        </CButton>
      </CCol>
    </CRow>
    <!-- </form> -->
  </div>
</template>
<script>
import axios from "axios";

export default {
  name: "quanlysalecreate",

  data() {
    return {
      valueoption: 0,
      formCollapsed: true,
      getData: "",
      formData: {
        name: "",
        startTime: "",
        endTime: "",
        statusL:""
      },
      searchP: ""
    };
  },
  created(){
    this.getAllProduct()
  },
  methods: {
    creatSale() {
      // console.log("abc");
      const formData = new FormData();
      for (let index in this.formData) {
        formData.append(index, this.formData.index);
      }
      console.log(this.formData);
      axios
        .post("http://localhost:8800/api/admin/sale/newSale",
          this.formData,
          {
            headers: {
              Authorization: this.$store.state.userToken,
            },
          }
        )
        .then((response) => {
          console.log(response);
          // this.$router.push(`/admin/quanlysanphamcreatedetail/${response.data.object.id}`);
          console.log("Thêm mới thành công!");
        })
        .catch(function (error) {
          console.log("Thêm mới thất bại!");
          alert(error);
        });
    },
    searchProduct(){
      // console.log(this.searchP)
      axios
        .get(
          this.$store.state.MainLink + "customer/products?find=" + this.searchP
        )
        .then((response) => {
          this.getData = response.data.object;
          // console.log(response.data.object);
        })
        .catch((e) => {
          console.log(e);
        });
    },
    getAllProduct() {
      axios
        .get(this.$store.state.MainLink + "customer/products")
        .then((response) => {
          this.getData = response.data.object;
          // console.log(this.getData);
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
