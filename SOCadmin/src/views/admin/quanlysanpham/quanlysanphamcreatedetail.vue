<template>
  <div>
    <CRow>
      <CCol md="12">
        <CButton
          type="reset"
          size="sm"
          color="danger"
          class="btn btn-custom-size"
        >
          <i class="cil-x"></i>
          Huỷ
        </CButton>
        <CButton
          type="submit"
          size="sm"
          color="primary"
          class="btn btn-custom-size"
          @click="createProductDetail()"
        >
          <i class="cil-plus"></i>
          Lưu sản phẩm
        </CButton>

      </CCol>
      <br />
      
      <br />
      <CCol md="12" lg="5">
        <CCard>
          <CCardHeader>
            <CIcon name="cil-justify-center" />
            <strong> Thông tin của sản phẩm </strong>
          </CCardHeader>
          <CCardBody height="auto">
          </CCardBody>
        </CCard>
      </CCol>
      <br>

      <br>
       <table class="table">
                <thead>
                  <tr>
                    <th scope="col">
                      <button @click="createNewValue()">Thêm mới</button>
                    </th>
                    <th scope="col" class="Title-table" colspan="1">
                      Tên thông số
                    </th>
                    <th scope="col" class="Title-table td-action">
                      Gía trị thông số
                    </th>
                  </tr>
                </thead>
                <tbody>
                  <tr v-for="(item, index) in newObject" :key="index">
                    <th scope="row" class="td-table">{{ index + 1 }}</th>
                    <td class="td-table status-color-out td-action">
                      <input 
                      v-model="item.propertyName"
                      />
                    </td>
                    <td class="td-table status-color-in td-action">
                      <input 
                      v-model="item.propertyValue"
                      />
                    </td>
                  </tr>
                </tbody>
              </table>

    </CRow>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "QuanLySanPhamCreate",

  data() {
    return {
      newObject: [
      ],
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
  methods: {
    validator(val) {
      return val ? val.length >= 4 : false;
    },
    getData(){
       let data = [{
        propertyName: this.item.propertyName,
        propertyValue: this.item.propertyValue,
        
      }]
      console.log(data)
    },
    createNewValue() {
      let item = {
        productId: parseInt(this.$route.params.id),
        propertyName: "",
        propertyValue: "",
      };
      this.newObject.push(item);
    },
    createProductDetail() {
      console.log(this.newObject);
      const json = JSON.stringify(this.newObject);
      axios
        .post(
          "http://localhost:8800/api/admin/products/newproductdetail/"+ this.$route.params.id, json, {
  headers: {
    // Overwrite Axios's automatically set Content-Type
    'Content-Type': 'application/json'
  }})
        .then((response) => {
        console.log(response)
        // this.$router.push(`/admin/quanlysanphamcreatecolor/${response.data.object.id}`);
        })
        .catch(function (error) {
          alert(error);
        });
      // this.callFunction();
    },
    CancelCreate() {
      this.$router.push("/quanlysanphamlist");
    },
    UpdateProduct() {
      this.$router.push("/quanlysanphamupdate");
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
