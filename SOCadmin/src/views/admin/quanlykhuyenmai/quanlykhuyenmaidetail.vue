<template>
  <div>
    <CRow>
      <CCol md="12" lg="7">
        <CCard>
          <CCardHeader>
            <CIcon name="cil-justify-center" />
            <!-- <i class="cil-image"></i>  -->
            <strong>Chi tiết nhóm</strong>
          </CCardHeader>
          <CCardBody>
            <CCarousel arrows indicators animate height="auto">
              <CCarouselItem
                image="https://photocross.net/wp-content/uploads/2020/03/anh-chan-dung.jpg"
                style="width: auto; height: auto;  center center no-repeat;"
              />
              <CCarouselItem
                style="width: auto; height: auto;  center center no-repeat;"
                image="https://photocross.net/wp-content/uploads/2020/03/anh-chan-dung.jpg"
              />
              <CCarouselItem
                style="width: auto; height: auto;  center center no-repeat;"
                image="https://photocross.net/wp-content/uploads/2020/03/anh-chan-dung.jpg"
              />
            </CCarousel>
          </CCardBody>
        </CCard>
      </CCol>
      <CCol md="12" lg="5">
        <CCard>
          <CCardHeader>
            <CIcon name="cil-justify-center" />
            <strong> Thông tin chi tiết sale </strong>
          </CCardHeader>
          <CCardBody height="auto">
            <CListGroup>
              <CListGroupItem>
                <span class="Title-font-size">Mã khuyến mại : </span>
                <span>{{ formData.saleId }}</span>
              </CListGroupItem>
              <CListGroupItem>
                <span class="Title-font-size">Mã sản phẩm : </span>
                <span>{{ getDataG.name }}</span>
              </CListGroupItem>
              <CListGroupItem>
                <span class="Title-font-size">Số lượng : </span>
                <span>{{ getDataG.createdDate }}</span>
              </CListGroupItem>
               <CListGroupItem>
                <span class="Title-font-size">Số tiền được giảm: </span>
                <span>{{ getDataG.createdDate }}</span>
              </CListGroupItem>
            </CListGroup>
          </CCardBody>
        </CCard>

        <!-- themmoi -->
        <CCard>
          <CCardHeader>
            <CIcon name="cil-justify-center" />
            <strong> Thêm thành viên nhóm </strong>
          </CCardHeader>
          <CCardBody height="auto">
            <CListGroup>
              <CListGroupItem
                >Chọn sản phẩm:
                <select
                  class="custom-select input-custom-border-none"
                  id="inputGroupSelect01"
                  style="width: 60%"
                  v-model="formDataAddUtoG.userId"
                  :required="true"
                >
                  <option selected disabled>Chọn User</option>
                  <option
                    id=""
                    v-for="item in getAllUserId"
                    :value="item.userId"
                    :key="item.userId"
                  >
                    {{ item.userName }}
                  </option>
                </select>
              </CListGroupItem>
            </CListGroup>
          </CCardBody>
        </CCard>
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
            @click="addMemberinGroup()"
          >
            <i class="cil-plus"></i>
            Lưu
          </CButton>
        </CCol>
      </CCol>
      <div class="col-12 col-title">
        <div class="col-md-6 float-left margin-left">
          <p class="Text-tile">Danh sách thành viên</p>
        </div>
      </div>
      <table class="table">
        <thead>
          <tr>
            <th scope="col" colspan="1">STT</th>
            <th scope="col" class="Title-table" colspan="2">Mã nhân viên</th>
            <th scope="col" class="Title-table td-action" colspan="3">
              Tên nhân viên
            </th>
            <th scope="col" class="Title-table td-action" colspan="4">
              Hành động
            </th>
            <th class="Title-table"></th>
          </tr>
        </thead>
        <tbody class="text-center">
          <tr v-for="(item, index) in getAllMember" :key="item.id">
            <th scope="row" colspan="1" class="td-table">{{ index + 1 }}</th>

            <th scope="row" colspan="2" class="td-table">{{ item.staffID }}</th>
            <th scope="row" colspan="3" class="td-table">
              {{ item.userName }}
            </th>
            <th scope="row" colspan="4" class="td-table">
              <button
                type="button"
                class="btn btn-danger btn-size"
                @click="RemoveFromGroup(item.id)"
              >
                <i class="cil-trash"></i>
              </button>
            </th>
          </tr>
        </tbody>
      </table>
    </CRow>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "quanlyluongnhanviendetail",
//   props: {
//     idG:"" ,
//   },
  data() {
    return {
      getDataG: "",
      formData: {
        saleId: parseInt(this.$route.params.id),
        discount: "",
        productId:"",
        quantity:""
      },
      getAllMember: "",

      formCollapsed: true,
      getAllUserId: "",
      formAllUserId: {
        userId: "",
        username: "",
      },
      formDataAddUtoG: {
        groupId: this.idG,
        userId: "",
      },
      getUnewtoGr: "",
    };
  },
  created() {
    this.getInfoGr();
    this.getAllMemberinGr();
    this.getAllUserIdnoGroup();
  },
  methods: {
    CancelCreate() {},

    getInfoGr() {
      // console.log(this.idG);
      axios
        .get(
          "http://localhost:8800/security/api/v2/guest/groups/get/" + this.idG
        )
        .then((response) => {
          this.getDataG = response.data;
          console.log(response.data);
        })
        .catch((e) => {
          console.log(e);
        });
    },

    getAllMemberinGr() {
      axios
        .get(
          "http://localhost:8080/security/api/v2/guest/groups/allmember/get/" +
            this.idG
        )
        .then((response) => {
          this.getAllMember = response.data;

          console.log(response.data);
        })
        .catch((e) => {
          console.log(e);
        });
    },

    getAllUserIdnoGroup() {
      axios
        .get("http://localhost:8800/security/api/v2/guest/groups/getuserid")
        .then((response) => {
          this.getAllUserId = response.data;
          // console.log("data allUnoGr: " + response.data);
        })
        .catch((e) => {
          console.log("Lỗi catch: " + e);
        });
    },

    addMemberinGroup() {
      console.log("idGroup: " + this.idG);
      console.log("idUser: " + this.formDataAddUtoG.userId);
      axios
        .post(
          "http://localhost:8800/security/api/v2/guest/groups/addmember",
          this.formDataAddUtoG
        )
        .then((response) => {
          this.getUnewtoGr = response.data;
          console.log(this.getUnewtoGr);
          this.$toasted.success("Thêm thành công", {
            duration: 2000,
          });
          this.getAllMemberinGr();
          this.getAllUserIdnoGroup();
        })
        .catch((e) => {
          console.log("Lỗi catch: " + e);
          this.$toasted.error("Thêm thất bại", {
            duration: 2000,
          });
        });
    },

    RemoveFromGroup(id) {
      axios
        .delete(
          "http://localhost:8800/security/api/v2/guest/groups/removemember/" +
            id
        )
        .then((response) => {
          if (response.status == 200) {
            this.$toasted.success("Xoá thành công", {
              duration: 2000,
            });
            this.getAllMemberinGr();
            this.getAllUserIdnoGroup();
          } else {
            console.log("res-Xoá thất bại: " + response.status);
            this.$toasted.error("Xoá thất bại, id không tồn tại", {
              duration: 2000,
            });
          }
        })
        .catch((e) => {
          console.log("Lỗi catch: " + e);
          this.$toasted.error("Xoá thất bại", {
            duration: 2000,
          });
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
