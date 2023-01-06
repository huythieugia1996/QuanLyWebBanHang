<template>
  <div class="uk-offcanvas-content">
    <main>
      <section class="uk-section uk-section-small">
        <div class="uk-container">
          <div class="uk-grid-medium uk-child-width-1-1" uk-grid>
            <div class="uk-text-center">
              <ul class="uk-breadcrumb uk-flex-center uk-margin-remove">
                <li>
                  <router-link to="/" style="text-decoration: none">
                    <a>Trang chủ</a>
                  </router-link>
                </li>
                <li><span>Blog</span></li>
              </ul>
              <h1 class="uk-margin-small-top uk-margin-remove-bottom">Blog</h1>
            </div>
            <div>
              <div class="uk-grid-medium" uk-grid>
                <section class="uk-width-1-1 uk-width-expand@m">
                  <div class="uk-grid-medium uk-grid-match uk-child-width-1-1 uk-child-width-1-2@s"  uk-grid>
                    <div v-for="item in getData" :key="item.id">
                      <a @click="detailBlog(item.id)">
                        <article
                          class="
                            uk-card
                            uk-card-default
                            uk-card-small
                            uk-article
                            uk-overflow-hidden
                            uk-box-shadow-hover-large
                            uk-height-1-1
                            tm-ignore-container
                          "
                        >
                          <div class="tm-ratio tm-ratio-16-9">
                            <figure class="tm-media-box uk-cover-container uk-margin-remove">
                              <img
                                :src="item.photo"
                                alt="Everything You Need to Know About the MacBook Pro"
                                uk-cover="uk-cover"
                              />
                            </figure>
                          </div>
                          <div class="uk-card-body">
                            <div class="uk-article-body">
                              <div
                                class="uk-article-meta uk-margin-xsmall-bottom"
                              >
                                <time>{{ item.timeCreated }}</time>
                              </div>
                              <div>
                                <h3 class="uk-margin-remove">
                                  {{ item.title }}
                                </h3>
                              </div>
                              <div class="uk-margin-small-top">
                                <p>{{ item.shortText }}</p>
                              </div>
                            </div>
                          </div>
                        </article>
                      </a>
                    </div>
                  </div>
                </section>
                <aside class="uk-width-1-4 uk-visible@m tm-aside-column">
                  <section
                    class="uk-card uk-card-default uk-card-small"
                    uk-sticky="offset: 90; bottom: true;"
                  >
                    <nav>
                      <ul class="uk-nav uk-nav-default tm-nav">
                        <li>
                          <router-link to="/about"> Về chúng tôi </router-link>
                        </li>
                        <li>
                          <router-link to="/about">
                            Liên hệ với của hàng
                          </router-link>
                        </li>
                        <li class="uk-active">
                          <router-link to="/blog"> Blog </router-link>
                        </li>
                        <li>
                          <router-link to="/news"> Tin mới </router-link>
                        </li>
                        <li>
                          <router-link to="/faq"> FAQ </router-link>
                        </li>
                        <!-- <li>
                          <router-link to="/delivery"> Delivery </router-link>
                        </li> -->
                      </ul>
                    </nav>
                  </section>
                </aside>
              </div>
            </div>
          </div>
        </div>
      </section>
      <!-- <section class="uk-section uk-section-default uk-section-small">
        <div class="uk-container">
          <div uk-slider>
            <ul
              class="
                uk-slider-items
                uk-child-width-1-1
                uk-child-width-1-2@s
                uk-child-width-1-5@m
                uk-grid
              "
            >
              <li>
                <div
                  class="uk-grid-small uk-flex-center uk-flex-left@s"
                  uk-grid
                >
                  <div><span uk-icon="icon: star; ratio: 2.5;"></span></div>
                  <div class="uk-text-center uk-text-left@s uk-width-expand@s">
                    <div>Mauris placerat</div>
                    <div class="uk-text-meta">
                      Donec mollis nibh dolor, sit amet auctor
                    </div>
                  </div>
                </div>
              </li>
              <li>
                <div
                  class="uk-grid-small uk-flex-center uk-flex-left@s"
                  uk-grid
                >
                  <div><span uk-icon="icon: receiver; ratio: 2.5;"></span></div>
                  <div class="uk-text-center uk-text-left@s uk-width-expand@s">
                    <div>Lorem ipsum</div>
                    <div class="uk-text-meta">
                      Sit amet, consectetur adipiscing elit
                    </div>
                  </div>
                </div>
              </li>
              <li>
                <div
                  class="uk-grid-small uk-flex-center uk-flex-left@s"
                  uk-grid
                >
                  <div><span uk-icon="icon: location; ratio: 2.5;"></span></div>
                  <div class="uk-text-center uk-text-left@s uk-width-expand@s">
                    <div>Proin pharetra</div>
                    <div class="uk-text-meta">
                      Nec quam a fermentum ut viverra
                    </div>
                  </div>
                </div>
              </li>
              <li>
                <div
                  class="uk-grid-small uk-flex-center uk-flex-left@s"
                  uk-grid
                >
                  <div><span uk-icon="icon: comments; ratio: 2.5;"></span></div>
                  <div class="uk-text-center uk-text-left@s uk-width-expand@s">
                    <div>Praesent ultrices</div>
                    <div class="uk-text-meta">
                      Praesent ultrices, orci nec finibus
                    </div>
                  </div>
                </div>
              </li>
              <li>
                <div
                  class="uk-grid-small uk-flex-center uk-flex-left@s"
                  uk-grid
                >
                  <div><span uk-icon="icon: happy; ratio: 2.5;"></span></div>
                  <div class="uk-text-center uk-text-left@s uk-width-expand@s">
                    <div>Duis condimentum</div>
                    <div class="uk-text-meta">
                      Pellentesque eget varius arcu
                    </div>
                  </div>
                </div>
              </li>
            </ul>
            <ul
              class="
                uk-slider-nav uk-dotnav uk-flex-center uk-margin-medium-top
              "
            ></ul>
          </div>
        </div>
      </section> -->
    </main>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      getData: "",
      formData: {
        name: "",
        price: "",
        status: "",
        orderDetails: "",
        blogs: "",
        productCategories: "",
        sales: "",
        productColors: [],
        cartDetails: "",
        productDetails: [],
      },

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
        .get(this.$store.state.MainLink + "customer/blog")
        .then((response) => {
          this.getData = response.data.object;
          // console.log(this.getData);
        })
        .catch((e) => {
          console.log(e);
        });
    },
    callFunction: function () {
      var v = this;
      setTimeout(function () {
        v.getDT();
      }, 2000);
    },
    detailBlog(id) {
      // console.log(id);
      this.$router.push({
        name: "article",
        params: { item: id },
      });
    },
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

<style>
</style>