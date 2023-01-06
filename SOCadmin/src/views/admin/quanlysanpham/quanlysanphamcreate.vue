<template>
  <div>
    <CRow>
      <CCol md="12">
        
        <CButton type="reset" size="sm" color="danger" class="btn btn-custom-size" @click="backList()">
          <i class="cil-x"></i>
          Huỷ 
        </CButton>
        <CButton type="submit" 
                    size="sm" color="primary" 
                    class="btn btn-custom-size"
                    @click="createProduct()"
                    v-if="showDetail == 0"
                    >
                      <i class="cil-plus"></i>
                      Lưu
        </CButton>
        <CButton type="submit" 
                    size="sm" color="primary" 
                    class="btn btn-custom-size"
                    @click="updateProductDetail()"
                    v-if="showDetail == 1"
                    >
                      <i class="cil-plus"></i>
                      Lưu
        </CButton>
      </CCol>
      <br/>
      <br/>
      <CCol md="12" lg="12" v-if="showDetail == 0">
        <CCard>
          <CCardHeader>
            <CIcon name="cil-justify-center" />
            <strong> Thông tin của sản phẩm </strong>
            
          </CCardHeader>
          <CCardBody height="auto">
            <CListGroup>
              <CListGroupItem
                ><div style="width: 25%; float: left; font-weight: 600">
                  Tên sản phẩm <span style="color: red">*</span>
                </div><input 
                type="text" 
                class="input-custom-border-none" 
                placeholder="Nhập tên máy"
                v-model="formData.name"
                style="width: 60%"
                >
              </CListGroupItem>
              <CListGroupItem>
                <div style="width: 25%; float: left; font-weight: 600">
                  Giá tiền <span style="color: red">*</span>
                </div>
                <input 
                type="text" 
                class="input-custom-border-none" 
                placeholder="Giá máy"
                v-model="formData.price"
                style="width: 60%"
                ></CListGroupItem>
              <CListGroupItem>
                <div style="width: 25%; float: left; font-weight: 600">
                  Thời hạn bảo hành <span style="color: red">*</span>
                </div>
                <input 
                type="number" 
                class="input-custom-border-none" 
                placeholder="Thời hạn"
                v-model="formData.warranty"
                style="width: 10%"
                >tháng
              </CListGroupItem>  

              <div style="margin: 15px 0px 0px 15px;">Các trường có dấu <span style="color: red">*</span> không được bỏ trống! </div>
            </CListGroup>
          </CCardBody>
        </CCard>
      </CCol>
      <CCol md="12" lg="12" v-if="showDetail == 0">
        <CCard>
          <CCardHeader>
            <CIcon name="cil-justify-center" />
            <strong> Thể loại sản phẩm </strong>
          </CCardHeader>
          <CCardBody height="auto">
            <CListGroup>

              <table class="table">
                  <tbody>
                    <tr v-for="(item, index) in getDataCategory" :key="index">
                      <th>{{ item.name }}</th>
                      <td scope="row" class="td-table" style="width: 55%;">
                          <div v-for="items in item.categories" :key="items.id" style="margin-left: 15%">
                            <input class="form-check-input" type="checkbox" :value="items.id" id="flexCheckDefault" @click="saveCategory(items.id)">
                            <label class="form-check-label" for="flexCheckDefault" style="margin-left : 15px">
                              {{items.name}} {{items.id}}
                            </label>
                          </div>
                      </td>
                    </tr>
                  </tbody>
                </table>
            </CListGroup>
          </CCardBody>
        </CCard>
      </CCol>

      <CCol md="12" lg="6" v-if="showDetail == 1">
        <CCard>
          <CCardHeader>
            <CIcon name="cil-justify-center" />
            <strong> Thông tin chi tiết sản phẩm </strong>
          </CCardHeader>
          <CCardBody height="auto">
            <CListGroup>
              <div>
                <i class="" style="font-size: smaller">Với những thông tin là <strong>hình ảnh</strong> ,
                 vui lòng nhập <strong>Tên trường</strong> là <strong>photo</strong></i>  
              </div>
              <table class="table">
                <thead>
                  <tr>
                    <th scope="col">
                      <button @click="createNewValue()" style="border: none">
                        +
                      </button>
                    </th>
                    <th scope="col" class="text-center">Tên trường</th>
                    <th scope="col" class="text-center">Giá trị</th>
                    <th scope="col"></th>
                  </tr>
                </thead>
                <tbody>
                  <tr v-for="(item, index) in orderDetails" :key="index">
                    <th scope="row">{{ index + 1 }}</th>
                    <td><input type="text" v-model="item.propertyName" style="width: 100%;border: none;border-bottom: 1px dashed;"/></td>
                    <td><input type="text" v-model="item.propertyValue" style="width: 100%;border: none;border-bottom: 1px dashed;"/></td>
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

      <CCol md="12" lg="6" v-if="showDetail == 1">
        <CCard>
          <CCardHeader>
            <CIcon name="cil-justify-center" />
            <strong> Màu và số lượng sản phẩm </strong>
            <!-- <button type="button" @click="updateProductColor()">Test</button> -->
          </CCardHeader>
          <CCardBody height="auto">
            <CListGroup>
              <div class="form-check" style="">
                <div>
                  <input class="form-check-input" type="radio" value="Đang bán" v-model="statusProduct" name="flexRadioDefault1" id="flexRadioDefault1">
                  <p>Đang bán</p>
                </div>
                <div>
                  <input class="form-check-input" type="radio" value="Không kinh doanh" v-model="statusProduct" name="flexRadioDefault2" id="flexRadioDefault2">
                  <p>Không kinh doanh</p>
                </div>
              </div>
              <table class="table">
                <thead>
                  <tr>
                    <th scope="col">
                      <button @click="createNewColor()" style="border: none">
                        +
                      </button>
                    </th>
                    <th scope="col">Màu</th>
                    <th scope="col" class="text-center">Số lượng</th>
                    <th scope="col"></th>
                  </tr>
                </thead>
                <tbody>
                  <tr v-for="(item, index) in productColor" :key="index">
                    <th scope="row">{{ index + 1 }}</th>
                    <td>
                      <select class="form-select form-select-sm" aria-label=".form-select-sm example" v-model="item.colorId" style="border: none;border-bottom: 1px dashed;box-shadow: none;">
                        <option selected disabled>Chọn màu ...</option>
                        <option :value="color.id" v-for="color in getDataColor" :key="color.id">{{color.colorName}}</option>
                      </select>
                    </td>
                    <td><input type="text" v-model="item.quantity" style="width: 100%;border: none;border-bottom: 1px dashed;"/></td>
                    <td class="text-center">
                      <i
                        class="cil-trash"
                        style="color: red"
                        @click="deleteColor(index)"
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
import axios from "axios"
export default {
  name: "QuanLySanPhamCreate",
  data() {
    return {
      formData: {
        name: "",
        price: "",
        // status: "",
        warranty: 24,
        productCategories: []
      },
      statusProduct: "",
      orderDetails: [],
      productColor: [],
      showDetail: 0,
      getDataCategory: "",
      getDataColor: "",
      getIdProduct: ""

      // formCollapsed: true,
    };
  },
  created(){
    this.getAllCategory()
    this.getColor()
  },
  methods: {
    validator(val) {
      return val ? val.length >= 4 : false;
    },
    saveCategory(item){
      console.log(item)
      let items = {
        categoryId : item
      }
      this.formData.productCategories.push(items);
      // console.log(this.formData.productCategories)
    },
    createNewValue(){
      let item = {
        propertyName: "",
        propertyValue: ""
      }
      // console.log(item)
      this.orderDetails.push(item);
    },
    deleteProduct(index){
this.orderDetails.splice(index, 1);
    },
    deleteColor(index){
this.productColor.splice(index, 1);
    },
    createNewColor(){
      let item = {
        colorId: "",
        quantity: ""
      }
      // console.log(item)
      this.productColor.push(item);
      // console.log(this.productColor)
    },
    backList(){
      this.$router.push("/admin/quanlysanphamlist")
    },
    createProduct(){
      console.log(this.$store.state.userToken)
      console.log("abc")
      const formData = new FormData();
      for (let index in this.formData) {
        formData.append(index, this.formData.index);
      }
      console.log(this.formData);
      axios
        .post(
          this.$store.state.MainLink + "admin/products/newproduct",
          this.formData,{
            headers: {
              Authorization: this.$store.state.userToken,
            },
          }
        )
        .then((response) => {
        this.getIdProduct = response.data.object.id;
        console.log(this.getIdProduct)
        this.showDetail = 1
        })
        .catch(function(error) {
          alert(error);
        });
    },
    updateProductDetail(){
      console.log(this.orderDetails)
      axios
        .post(
          this.$store.state.MainLink + "admin/products/newproductdetail/" + this.getIdProduct,
          this.orderDetails,{
            headers: {
              Authorization: this.$store.state.userToken,
            },
          }
        )
        .then(() => {
        this.updateProductColor()
        })
        .catch(function(error) {
          alert(error);
        });
    },
    updateProductColor(){
      console.log(this.productColor)
      console.log(this.statusProduct)
      axios
        .post(
          this.$store.state.MainLink + "admin/products/newproductcolor/" + this.getIdProduct + "?statusProduct=" +this.statusProduct,
          this.productColor,{
            headers: {
              Authorization: this.$store.state.userToken,
            },
          }
        )
        .then(() => {
        this.$router.push("/admin/quanlysanphamlist")
        })
        .catch(function(error) {
          alert(error);
        });
    },
    getColor(){
      axios
        .get( this.$store.state.MainLink + "admin/color/get",
         {
            headers: {
              Authorization: this.$store.state.userToken,
            },
          })
        .then((response) => {
          this.getDataColor = response.data.object;
          console.log(this.getDataColor)
        })
        .catch((e) => {
          console.log(e);
        });
    },
    getAllCategory() {
      axios
        .get( this.$store.state.MainLink + "customer/categories?active=true",
         {
            headers: {
              Authorization: this.$store.state.userToken,
            },
          })
        .then((response) => {
          this.getDataCategory = response.data.object;
          console.log(this.getDataCategory)
        })
        .catch((e) => {
          console.log(e);
        });
    },
  },
};
</script>
<style scope>
.btn-custom-size{
  width: 130px;
  margin-left: 15px;
  float: right
}
.input-custom-border-none{
  border: none;
  box-shadow: none;
  box-sizing: border-box;
}
.title-td{
  width: 35%;
}
</style>
