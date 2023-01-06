<template>
<!-- <CForm class="row g-3 needs-validation" novalidation >
  
</CForm> -->
<div>
    <CRow>
      <CCol md="12"> 
        <CButton type="reset" size="sm" color="danger" class="btn btn-custom-size" @click="backList()">
          <i class="cil-x"></i>
          Huỷ 
        </CButton>
        <CButton type="submit"  size="sm" color="primary" class="btn btn-custom-size" @click="createBlog()">
                      <i class="cil-plus"></i>
                      Lưu
        </CButton>
      </CCol>
      <br/>
      <br/>
      <CCol md="12" lg="5">
        <CCard>
          <CCardHeader>
            <CIcon name="cil-justify-center" />
            <strong> Thông tin Blog </strong>
            
          </CCardHeader>
          <CCardBody height="auto">
            <CListGroup>
                    <CListGroupItem>
                        <div style="width: 25%; float: left; font-weight: 600">
                        Tiêu đề <span style="color: red">*</span>
                        </div>
                        <!-- <CFormInput id="validationTooltip01" value="Mark" required/> -->
                        <input  
                        type="text" 
                        class="input-custom-border-none" 
                        placeholder="Tiêu đề blog"
                        v-model="title"
                        style="width: 70%"
                        required>
                    </CListGroupItem>
                    <CListGroupItem>
                        <div style="width: 25%; float: left; font-weight: 600">
                        Tóm tắt <span style="color: red">*</span>
                        </div>
                        <input 
                        type="text" 
                        class="input-custom-border-none" 
                        placeholder="Tóm tắt nội dung"
                        v-model="shortText"
                        style="width: 70%"
                        required>
                    </CListGroupItem> 
                    <CListGroupItem>
                        <div style="width: 25%; float: left; font-weight: 600">
                        Ảnh minh họa <span style="color: red">*</span>
                        </div>
                        <input 
                        type="text" 
                        class="input-custom-border-none" 
                        placeholder="Ảnh nổi bật"
                        v-model="photo"
                        style="width: 70%"
                        required>
                    </CListGroupItem> 
            </CListGroup>
            <CListGroup style="margin-top: 15px">
                <strong>Nội dung của blog</strong>
                <table class="table">
                  <thead>
                      <th>
                        <i class="cil-plus" @click="createDetailBlog()"></i>
                      </th>
                      <!-- <th>Thứ tự hiển thị</th> -->
                      <th style="text-align: center;">Thể loại</th>
                      <th style="text-align: center;">Nội dung</th>
                      
                      <th></th>
                  </thead>
                  <tbody>
                    <tr v-for="(item, index) in blogDetails" :key="index">
                      <th>
                          {{index + 1}}
                      </th>
                      <td>
                        <select class="form-select form-select-sm" aria-label=".form-select-sm example" v-model="item.type" style="border: none;border-bottom: 1px dashed;box-shadow: none;">
                            <option disabled>Chọn thể loại</option>
                            <option value="text">Nội dung</option>
                            <option value="photo">Ảnh</option>
                            <option value="content">Tiêu đề</option>
                        </select>
                      </td>
                      <td>
                          <textarea rows="3" type="text" v-model="item.content" style="width: 100%;border: none;border-bottom: 1px dashed;"/>
                      </td>
                      <td>
                          <i class="cil-trash" style="color: red; text-align: center;" @click="deleteRow(index)"></i>
                      </td>
                    </tr>
                  </tbody>
                </table>
            </CListGroup>
          </CCardBody>
        </CCard>
      </CCol>
      <CCol md="12" lg="7">
        <CCard>
          <CCardHeader>
            <CIcon name="cil-justify-center" />
            <strong> Bản xem trước tiêu đề</strong>
          </CCardHeader>
          <CCardBody height="auto">
            <CListGroup>
                <CCarousel arrows indicators animate height="auto" width="100%">
                    <CCarouselItem
                    :image="photo"
                    />
                </CCarousel>
                <h3>{{title}}</h3>
                <p>{{shortText}}</p>
            </CListGroup>
          </CCardBody>
        </CCard>
        <CCard>
          <CCardHeader>
            <CIcon name="cil-justify-center" />
            <strong> Bản xem trước nội dung</strong>
          </CCardHeader>
          <CCardBody height="auto">
            <CListGroup>
                <div v-for="(item, index) in blogDetails" :key="index">
                <h3 v-if="item.type == 'content'">{{item.content}}</h3>
                <p v-else-if="item.type == 'text'">{{item.content}}</p>
                <img v-else-if="item.type == 'photo'" :src="item.content"  width="auto" height="300">
                </div>
            </CListGroup>
          </CCardBody>
        </CCard>
      </CCol>
    </CRow>
    <CToaster placement="top-end">
    <CToast v-for="(toast, index) in toasts" :key="index">
      <CToastHeader closeButton>
      <span class="me-auto fw-bold">{{toast.title}}</span>
      <small>7 min ago</small>
      </CToastHeader>
      <CToastBody>
        {{ toast.content }}
      </CToastBody>  
    </CToast>
  </CToaster>
  </div>
</template>

<script>
import axios from "axios"
import { CToast } from '@coreui/vue'

export default {
  name: "QuanLyBlogCreate",
components: {
    CToast
  },
  data() {
    return {
        toasts: [],
      blogDetails: [],

      title: "",
      shortText: "",
      photo: "",
      valueType: 0,

      // formCollapsed: true,
    };
  },
  created(){
    // this.getAllCategory()
    // this.getColor()
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
    createDetailBlog(){
        this.valueType += 1
      let item = {
        type : "",
        ordinal : this.valueType,
        content : ""
      }
    //   console.log(item)
      this.blogDetails.push(item);
    //   console.log(this.blogDetails)
    },
    deleteRow(index){
        this.valueType  = this.valueType - 1
        this.blogDetails.splice(index, 1);
    },
    backList(){
      this.$router.push("/blog/quanlyblog")
    },
    createBlog(){
        let item = {
            title: this.title,
            shortText : this.shortText,
            photo: this.photo,
            blogDetails : this.blogDetails
        }
        console.log(item)
        axios.post(this.$store.state.MainLink + "admin/blog/new",item,
        {
            headers: {
              Authorization: this.$store.state.userToken,
            },
        })
        .then(() => {
            this.backList()
        })
        .catch(function(error) {
          alert(error);
        });
    }
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
