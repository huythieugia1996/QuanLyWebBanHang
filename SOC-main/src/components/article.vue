<template>
  <div class="uk-offcanvas-content">
    <main>
      <section class="uk-section uk-section-small">
        <div class="uk-container">
          <div class="uk-grid-medium uk-child-width-1-1" uk-grid>
            <section class="uk-text-center">
              <ul class="uk-breadcrumb uk-flex-center uk-margin-remove">
                <li>
                  <router-link to="/" style="text-decoration: none">
                    <a>Trang chủ</a>
                  </router-link>
                </li>
                <li><a>Blog</a></li>
              </ul>
            </section>
            <section>
              <div class="uk-grid-medium uk-child-width-1-1" uk-grid>
                <section>
                  <article class="uk-card uk-card-default uk-card-body uk-article tm-ignore-container">
                    <header class="uk-text-center">
                      <h1 class="uk-article-title">
                        {{ getData.title }}
                      </h1>
                    </header>
                    <section
                      class="section-custom"
                      v-for="(item, index) in getBlogDetail"
                      :key="index"
                    >
                      <div class="tm-wrapper uk-text-center">
                        <figure>
                          <a
                            ><img
                              :src="item.content"
                              v-if="item.type == 'photo'"
                          /></a>
                        </figure>
                      </div>
                      <p v-if="item.type == 'content'">
                        {{ item.content }}
                      </p>
                      <p v-if="item.type == 'text'">
                        {{ item.content }}
                      </p>
                    </section>
                  </article>
                </section>
                <section>
                            <div class="uk-grid-small uk-grid-divider" uk-grid>
                              <div
                                class="
                                  uk-width-1-1 uk-width-1-5@s uk-text-center
                                  tm-reviews-column
                                "
                              >
                                <div class="uk-text-meta uk-text-uppercase">
                                  Nhận xét
                                </div>
                                <div class="uk-margin-small-top uk-text-meta">
                                  dựa trên 1 nhận xét
                                </div>
                                <button
                                  class="uk-button  uk-button-primary  uk-margin-top  uk-width-1-1" uk-toggle="target: #review">
                                  Viết nhận xét
                                </button>
                              </div>
                              <div class="uk-width-1-1 uk-width-expand@s" v-if="getData.comments != null">
                                <div
                                  class="
                                    uk-grid-small
                                    uk-grid-divider
                                    uk-child-width-1-1
                                  "
                                  uk-grid
                                >
                                  <article v-for="itemC in getData.comments" :key="itemC.id">
                                    <section
                                      class="uk-grid-small uk-child-width-1-1"
                                      uk-grid
                                    >
                                      <header>
                                        <div class="uk-h4 uk-margin-remove">
                                          {{itemC.name}}
                                        </div>
                                        <time class="uk-text-meta"
                                          >{{itemC.timeCreated}}</time
                                        >
                                      </header>
                                      <div>
                                        <div>
                                          <p>
                                            {{itemC.detail}}
                                          </p>
                                        </div>
                                      </div>
                                    </section>
                                  </article>
                                </div>
                              </div>
                            </div>
                </section>
              </div>
            </section>
          </div>
          <div id="review" uk-modal>
              <div class="uk-modal-dialog uk-modal-body">
                <button
                  class="uk-modal-close-outside"
                  type="button"
                  uk-close
                ></button>
                <h2 class="uk-modal-title uk-text-center">Nhận xét của bạn</h2>
                <form class="uk-form-stacked">
                  <div class="uk-grid-small uk-child-width-1-1" uk-grid>
                    <div>
                      <label style="width: 100%;">
                        <div class="uk-form-label uk-form-label-required">
                          Họ tên
                        </div>
                        <input class="uk-input" type="text" required v-model="comments.name"/>
                      </label>
                    </div>
                    <div>
                      <label style="width: 100%;">
                        <div class="uk-form-label">
                          Email
                        </div>
                        <input class="uk-input" type="email" required v-model="comments.email"/>
                      </label>
                    </div>
                    <div>
                      <label style="width: 100%;">
                        <div class="uk-form-label uk-form-label-required">
                          Nhận xét
                        </div>
                        <textarea
                          class="uk-textarea"
                          rows="5"
                          required
                          v-model="comments.detail"
                        ></textarea>
                      </label>
                    </div>
                    <div class="uk-text-center">
                      <button type="button" class="uk-button uk-button-primary" @click="sendComments(getData.id)" > 
                      Gửi
                      </button>
                    </div>
                  </div>
                </form>
              </div>
          </div>
        </div>
      </section>
    </main>
  </div>
</template>

<script>
import axios from "axios";
export default {
  props: {
    item: Number,
  },
  data() {
    return {
      getData: "",
      getBlogDetail: "",
      review: 0,
      comments: {
        email: "",
        name: "",
        blogId: 0,
        detail: ""
      },
      // formData: {
      //   // id: this.item,
      //   name: "",
      //   price: "",
      //   status: "",
      //   orderDetails: "",
      //   blogs: "",
      //   productCategories: "",
      //   sales: "",
      //   productColors: [],
      //   cartDetails: "",
      //   productDetails: [],
      // },

      // getData: []
    };
  },
  created() {
    this.getBlog();
    this.getDataAccount();
    // this.callFunction();
  },
  methods: {
    formatPrice(value) {
      let val = (value / 1).toFixed(2).replace(".", ",");
      return val.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ".");
    },
    getBlog() {
      axios
        .get(this.$store.state.MainLink + "customer/blog/" + this.item)
        .then((response) => {
          this.getData = response.data.object;
          this.getBlogDetail = response.data.object.blogDetails;
          // console.log(this.getBlogDetail);
          // console.log(this.getData)
        })
        .catch((e) => {
          console.log(e);
        });
    },
    sendComments(id){
      let item = {
        blogId: id,
        name : this.comments.name,
        email : this.comments.email,
        detail : this.comments.detail
      }
      axios
        .post(this.$store.state.MainLink + "customer/comment/new", item)
        .then(() => {
          this.getBlog();
          this.$toasted.show("Đã đăng bình luận!", {
          type: "success",
          duration: 2000,
        });
        })
        .catch((e) => {
          console.log(e);
        });
    },
    // detailBlog(id) {
    //   console.log(id);
    //   this.$router.push({
    //     name: "article",
    //     params: { item: id },
    //   });
    // },
    getDataAccount() {
      

      axios
        .get(this.$store.state.MainLink + "customer/account", {
          headers: {
            Authorization: localStorage.userToken,
          },
        })
        .then((response) => {
          this.$store.state.userName = response.data.object.fullname;
          this.$store.state.tokenUser = localStorage.userToken
          this.$store.state.InfoPersonal = response.data.object;
          this.getTotalCart();
          // console.log(response.data.object);
        })
        .catch((e) => {
          // this.error.push(e);
          console.log(e);
        });
          
    },
    getTotalCart() {
      axios
        .get(this.$store.state.MainLink + "customer/cart/get", {
          headers: {
            Authorization: localStorage.userToken,
          },
        })
        .then((response) => {
          this.$store.state.totalCart = response.data.object.length;
          this.$store.state.StoreCart = response.data.object;
        })
        .catch((e) => {
          console.log(e);
        });
    },
  },
};
</script>

<style scope>
.section-custom {
  margin-top: 5%;
}
</style>