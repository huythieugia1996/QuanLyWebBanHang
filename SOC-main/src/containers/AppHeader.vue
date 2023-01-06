<template>
<div>
  <header>
    <div>
      <div class="uk-navbar-container uk-light uk-visible@m tm-toolbar-container">
        <div class="uk-container" uk-navbar>
          <div class="uk-navbar-left">
            <nav>
              <ul class="uk-navbar-nav">
                <li>
                  <a
                    ><span
                      class="uk-margin-xsmall-right"
                      uk-icon="icon: receiver; ratio: .75;"
                    ></span
                    ><span class="tm-pseudo">8 800 799 99 99</span></a
                  >
                </li>
                <li>
                  <div class="uk-navbar-item">
                    <span
                      class="uk-margin-xsmall-right"
                      uk-icon="icon: clock; ratio: .75;"
                    ></span
                    >Thời gian mở cửa 08:00–22:00
                  </div>
                </li>
              </ul>
            </nav>
          </div>
          <div class="uk-navbar-right">
            <nav>
              <ul class="uk-navbar-nav">
                <li v-if="this.$store.state.userName">
                  <a style="text-decoration: none"
                    >Xin chào, {{ this.$store.state.userName }}</a
                  >
                </li>

                <li v-else>
                  <a style="text-decoration: none" @click="switchToLogin()"
                    >Bạn chưa đăng nhập!</a
                  >
                </li>
                <li>
                  <router-link to="/news">
                    <a style="text-decoration: none">Tin tức</a>
                  </router-link>
                </li>
                <li>
                  <router-link to="/faq">
                    <a style="text-decoration: none">FAQ</a>
                  </router-link>
                </li>
              </ul>
            </nav>
          </div>
        </div>
      </div>
      <div class="uk-navbar-container tm-navbar-container" uk-sticky="cls-active: tm-navbar-container-fixed">
        <div class="uk-container" uk-navbar>
          <div class="uk-navbar-left">
            <button
              class="uk-navbar-toggle uk-hidden@m"
              uk-toggle="target: #nav-offcanvas"
              uk-navbar-toggle-icon
            ></button>
            <router-link to="/"
              ><a class="uk-navbar-item uk-logo"
                ><img
                  src="images/SOCStore.png"
                  width="120px"
                  height="auto"
                  alt="Logo" /></a
            ></router-link>

            <nav class="uk-visible@m">
              <ul class="uk-navbar-nav">
                <li>
                    <a class="a-custom" style="text-decoration: none" @click="switchToCategory()">Danh mục
                      <span class="uk-margin-xsmall-left" uk-icon="icon: chevron-down; ratio: .75;"></span>
                    </a>
                  <div class="uk-navbar-dropdown uk-margin-remove uk-padding-remove-vertical" uk-drop="pos: bottom-justify;delay-show: 125;delay-hide: 50;duration: 75;boundary: .tm-navbar-container;boundary-align: true;pos: bottom-justify;flip: x">
                    <div class="uk-container">
                      <ul class="uk-navbar-dropdown-grid uk-child-width-1-5" uk-grid>
                        <li v-for="item in getCategory" :key="item.id">
                          <div class="uk-margin-top uk-margin-bottom">
                              <a class="uk-link-reset" style="text-decoration: none" @click="switchToCategory()">
                                <img class="uk-display-block uk-margin-auto uk-margin-bottom"
                                  src="images/catalog/computers.svg"
                                  width="80"
                                  height="80"
                                />
                                <div class="uk-text-bolder" style="color: gray; text-decoration: none">
                                  {{item.name}}
                                </div>
                              </a>
                            <ul class="uk-nav uk-nav-default">
                                <li v-for="items in item.categories" :key="items.id"><a @click="getByCate(items.id)">{{items.name}}</a></li>
                              </ul>
                          </div>
                        </li>
                      </ul>
                    </div>
                  </div>
                </li>
                <li>
                  <router-link to="/brands">
                    <a class="a-custom"
                      >Thương hiệu<span
                        class="uk-margin-xsmall-left"
                        uk-icon="icon: chevron-down; ratio: .75;"
                      ></span
                    ></a>
                  </router-link>

                  <div
                    class="
                      uk-navbar-dropdown
                      uk-margin-remove
                      uk-padding-remove-vertical
                    "
                    uk-drop="pos: bottom-justify;delay-show: 125;delay-hide: 50;duration: 75;boundary: .tm-navbar-container;boundary-align: true;pos: bottom-justify;flip: x"
                  >
                    <div
                      class="
                        uk-container
                        uk-container-small
                        uk-margin-top
                        uk-margin-bottom
                      "
                    >
                      <ul class="uk-grid-small uk-child-width-1-6" uk-grid>
                        <li>
                          <div class="tm-ratio tm-ratio-4-3">
                            <a
                              class="
                                uk-link-muted
                                uk-text-center
                                uk-display-block
                                uk-padding-small
                                uk-box-shadow-hover-large
                                tm-media-box
                              "
                              title="Apple"
                              @click="getByCate('12')"
                            >
                              <figure class="tm-media-box-wrap">
                                <img
                                  src="images/brands/apple.svg"
                                  alt="Apple"
                                />
                              </figure>
                            </a>
                          </div>
                        </li>
                        <li>
                          <div class="tm-ratio tm-ratio-4-3">
                            <a
                              class="
                                uk-link-muted
                                uk-text-center
                                uk-display-block
                                uk-padding-small
                                uk-box-shadow-hover-large
                                tm-media-box
                              "
                              title="Samsung"
                              @click="getByCate('Samsung')"
                            >
                              <figure class="tm-media-box-wrap">
                                <img
                                  src="images/brands/samsung.svg"
                                  alt="Samsung"
                                />
                              </figure>
                            </a>
                          </div>
                        </li>
                        <li>
                          <div class="tm-ratio tm-ratio-4-3">
                            <a
                              class="
                                uk-link-muted
                                uk-text-center
                                uk-display-block
                                uk-padding-small
                                uk-box-shadow-hover-large
                                tm-media-box
                              "
                              title="Sony"
                              @click="getByCate('Sony')"
                            >
                              <figure class="tm-media-box-wrap">
                                <img src="images/brands/sony.svg" alt="Sony" />
                              </figure>
                            </a>
                          </div>
                        </li>
                        <li>
                          <div class="tm-ratio tm-ratio-4-3">
                            <a
                              class="
                                uk-link-muted
                                uk-text-center
                                uk-display-block
                                uk-padding-small
                                uk-box-shadow-hover-large
                                tm-media-box
                              "
                              title="Microsoft"
                              @click="getByCate('Microsoft')"
                            >
                              <figure class="tm-media-box-wrap">
                                <img
                                  src="images/brands/microsoft.svg"
                                  alt="Microsoft"
                                />
                              </figure>
                            </a>
                          </div>
                        </li>
                        <li>
                          <div class="tm-ratio tm-ratio-4-3">
                            <a
                              class="
                                uk-link-muted
                                uk-text-center
                                uk-display-block
                                uk-padding-small
                                uk-box-shadow-hover-large
                                tm-media-box
                              "
                              title="Intel"
                              @click="getByCate('Intel')"
                            >
                              <figure class="tm-media-box-wrap">
                                <img
                                  src="images/brands/intel.svg"
                                  alt="Intel"
                                />
                              </figure>
                            </a>
                          </div>
                        </li>
                        <li>
                          <div class="tm-ratio tm-ratio-4-3">
                            <a
                              class="
                                uk-link-muted
                                uk-text-center
                                uk-display-block
                                uk-padding-small
                                uk-box-shadow-hover-large
                                tm-media-box
                              "
                              title="HP"
                              @click="getByCate('13')"
                            >
                              <figure class="tm-media-box-wrap">
                                <img src="images/brands/hp.svg" alt="HP" />
                              </figure>
                            </a>
                          </div>
                        </li>
                        <li>
                          <div class="tm-ratio tm-ratio-4-3">
                            <a
                              class="
                                uk-link-muted
                                uk-text-center
                                uk-display-block
                                uk-padding-small
                                uk-box-shadow-hover-large
                                tm-media-box
                              "
                              title="LG"
                              @click="getByCate('LG')"
                            >
                              <figure class="tm-media-box-wrap">
                                <img src="images/brands/lg.svg" alt="LG" />
                              </figure>
                            </a>
                          </div>
                        </li>
                        <li>
                          <div class="tm-ratio tm-ratio-4-3">
                            <a
                              class="
                                uk-link-muted
                                uk-text-center
                                uk-display-block
                                uk-padding-small
                                uk-box-shadow-hover-large
                                tm-media-box
                              "
                              title="Lenovo"
                              @click="getByCate('7')"
                            >
                              <figure class="tm-media-box-wrap">
                                <img
                                  src="images/brands/lenovo.svg"
                                  alt="Lenovo"
                                />
                              </figure>
                            </a>
                          </div>
                        </li>
                        <li>
                          <div class="tm-ratio tm-ratio-4-3">
                            <a
                              class="
                                uk-link-muted
                                uk-text-center
                                uk-display-block
                                uk-padding-small
                                uk-box-shadow-hover-large
                                tm-media-box
                              "
                              title="ASUS"
                              @click="getByCate('9')"
                            >
                              <figure class="tm-media-box-wrap">
                                <img src="images/brands/asus.svg" alt="ASUS" />
                              </figure>
                            </a>
                          </div>
                        </li>
                        <li>
                          <div class="tm-ratio tm-ratio-4-3">
                            <a
                              class="
                                uk-link-muted
                                uk-text-center
                                uk-display-block
                                uk-padding-small
                                uk-box-shadow-hover-large
                                tm-media-box
                              "
                              title="Acer"
                              @click="getByCate('8')"
                            >
                              <figure class="tm-media-box-wrap">
                                <img src="images/brands/acer.svg" alt="Acer" />
                              </figure>
                            </a>
                          </div>
                        </li>
                        <li>
                          <div class="tm-ratio tm-ratio-4-3">
                            <a
                              class="
                                uk-link-muted
                                uk-text-center
                                uk-display-block
                                uk-padding-small
                                uk-box-shadow-hover-large
                                tm-media-box
                              "
                              title="Dell"
                              @click="getByCate('10')"
                            >
                              <figure class="tm-media-box-wrap">
                                <img src="images/brands/dell.svg" alt="Dell" />
                              </figure>
                            </a>
                          </div>
                        </li>
                        <li>
                          <div class="tm-ratio tm-ratio-4-3">
                            <a
                              class="
                                uk-link-muted
                                uk-text-center
                                uk-display-block
                                uk-padding-small
                                uk-box-shadow-hover-large
                                tm-media-box
                              "
                              title="Canon"
                              @click="getByCate('Canon')"
                            >
                              <figure class="tm-media-box-wrap">
                                <img
                                  src="images/brands/canon.svg"
                                  alt="Canon"
                                />
                              </figure>
                            </a>
                          </div>
                        </li>
                      </ul>
                      <div class="uk-text-center uk-margin">
                        <a
                          class="uk-link-muted uk-text-uppercase tm-link-to-all"
                          ><span>Xem tất cả</span
                          ><span
                            uk-icon="icon: chevron-right; ratio: .75;"
                          ></span
                        ></a>
                      </div>
                    </div>
                  </div>
                </li>
                <li>
                  <router-link to="/blog"
                    ><a class="a-custom">Blogs</a>
                  </router-link>
                </li>
                <li>
                  <router-link to="/about"
                    ><a class="a-custom">Về chúng tôi</a>
                  </router-link>
                </li>
                <li>
                  <router-link to="/contacts"
                    ><a class="a-custom">Liên hệ</a>
                  </router-link>
                </li>
              </ul>
            </nav>
          </div>
          
          <div class="uk-navbar-right">

            <a class="uk-navbar-toggle tm-navbar-button" href="#" uk-search-icon></a>
              <div class="uk-navbar-dropdown uk-padding-small uk-margin-remove" uk-drop="mode: click;cls-drop: uk-navbar-dropdown;boundary: .tm-navbar-container;boundary-align: true;pos: bottom-justify;flip: x">
                <div class="uk-container">
                  <div class="uk-grid-small uk-flex-middle" uk-grid>
                    <div class="uk-width-expand">
                      <div class="uk-search uk-search-navbar uk-width-1-1">
                        <input class="uk-search-input" type="search" placeholder="Tìm kiếm ..." v-model="searchProduct">
                      </div>
                    </div>
                    <div class="uk-width-auto"><a class="uk-navbar-dropdown-close" uk-search-icon @click="getSearchProduct()"></a></div>
                  </div>
                </div>
              </div>

            <router-link to="/compare">
              <a class="uk-navbar-item uk-link-muted uk-visible@m tm-navbar-button">
                <span uk-icon="copy"></span>
                <span class="uk-badge">{{
                  this.$store.state.CompareCart.length
                }}</span>
              </a>
            </router-link>

            <div v-if="this.$store.state.tokenUser">
              <!-- <a class="uk-navbar-item uk-link-muted tm-navbar-button" uk-icon="user" @click="switchToAccount()" v-if="this.$store.state.tokenUser == ''"></a> -->
              <a class="uk-navbar-item uk-link-muted tm-navbar-button" uk-icon="user" @click="switchToAccount()"></a>
              <div class="uk-padding-small uk-margin-remove" uk-dropdown="pos: bottom-right; offset: -10; delay-hide: 200;" style="min-width: 150px" >
                <ul class="uk-nav uk-dropdown-nav">
                  <li>
                    <router-link to="/account">
                      <a> Đơn đã đặt </a>
                    </router-link>
                  </li>
                  <li>
                    <router-link to="/favorites">
                      <a style="text-decoration: none"> Sản phẩm yêu thích </a>
                    </router-link>
                  </li>
                  <li>
                    <router-link to="/personal"
                      ><a style="text-decoration: none">Cá nhân</a></router-link
                    >
                  </li>
                  <li class="uk-nav-divider"></li>

                  <li>
                    <a @click="clearData()">Đăng xuất</a>
                  </li>
                </ul>
              </div>
            </div>
            <div v-else>
              <a class="uk-navbar-item uk-link-muted tm-navbar-button" uk-icon="user" @click="switchToAccount()"></a>
            </div>
            <div>
              <a class="uk-navbar-item uk-link-muted tm-navbar-button" uk-icon="cart" @click="switchToCart()">
                <span class="uk-badge" v-if="this.$store.state.tokenUser == ''">
                  {{ this.$store.state.StoreCart.length }}
                </span>
                <span class="uk-badge" v-else>
                  {{ this.$store.state.totalCart }}
                </span>
              </a>
            </div>
          </div>
        </div>
      </div>
    </div>
  </header>
  <div id="nav-offcanvas" uk-offcanvas="overlay: true">
        <aside class="uk-offcanvas-bar uk-padding-remove">
          <div class="uk-card uk-card-default uk-card-small tm-shadow-remove">
            <header class="uk-card-header uk-flex uk-flex-middle">
              <div><a class="uk-link-muted uk-text-bold" href="#">8 800 799 99 99</a>
                <div class="uk-text-xsmall uk-text-muted" style="margin-top: -2px;">
                  <div>Thời gian mở cửa 08:00–22:00</div>
                </div>
              </div>
            </header>
            <nav class="uk-card-small uk-card-body">
              <ul class="uk-nav-default uk-nav-parent-icon uk-list-divider" uk-nav>
                <li class="uk-parent">
                    <a @click="switchToCategory()">Danh mục</a>
                  <ul class="uk-nav-sub uk-list-divider">
                    <li v-for="item in getCategory" :key="item.id"><a @click="getByCate(item.id)">{{item.name}}</a></li>
                  </ul>
                </li>
                <li class="uk-parent"><a>Thương hiệu</a>
                  <ul class="uk-nav-sub uk-list-divider">
                    <li><a @click="getByCate('12')">Apple</a></li>
                    <li><a @click="getByCate('Samsung')">Samsung</a></li>
                    <li><a @click="getByCate('Sony')">Sony</a></li>
                    <li><a @click="getByCate('Microsoft')">Microsoft</a></li>
                    <li><a @click="getByCate('Intel')">Intel</a></li>
                    <li><a @click="getByCate('13')">HP</a></li>
                    <li><a @click="getByCate('LG')">LG</a></li>
                    <li><a @click="getByCate('7')">Lenovo</a></li>
                    <li><a @click="getByCate('9')">ASUS</a></li>
                    <li><a @click="getByCate('8')">Acer</a></li>
                    <li><a @click="getByCate('10')">Dell</a></li>
                    <li><a @click="getByCate('Canon')">Canon</a></li>
                    <li class="uk-text-center"><a class="uk-link-muted uk-text-uppercase tm-link-to-all"><span>Xem tất cả</span><span uk-icon="icon: chevron-right; ratio: .75;"></span></a>
                    </li>
                  </ul>
                </li>
                <li>
                  <router-link to="/blog"><a >Blog</a></router-link>
                </li>
                <li><router-link to="/about"><a >Về chúng tôi</a></router-link>
                </li>
                <li><router-link to="/contacts"><a >Liên hệ</a></router-link>
                </li>
                <li><router-link to="/compare"><a >So sánh</a></router-link></li>
              </ul>
            </nav>
            <nav class="uk-card-small uk-card-body">
              <ul class="uk-nav uk-nav-default">
                <li><router-link to="/news"><a >Tin tức</a></router-link></li>
                <li><router-link to="/faq"><a >FAQ</a></router-link></li>
              </ul>
            </nav>
            <nav class="uk-card-body">
              <ul class="uk-iconnav uk-flex-center">
                <li><a title="Facebook" uk-icon="facebook"></a></li>
                <li><a title="Twitter" uk-icon="twitter"></a></li>
                <li><a title="YouTube" uk-icon="youtube"></a></li>
                <li><a title="Instagram" uk-icon="instagram"></a></li>
              </ul>
            </nav>
          </div>
        </aside>
      </div>
</div>

</template>

<script>
import axios from "axios";
export default {
  name: "AppHeader",
  data() {
    return {
      quantityCart: "",
      minWidthWindow: "",
      userName: "",
      searchProduct: ""
    };
  },
  created() {
    this.getCategory();
    this.setUserName();
    // console.log(localStorage.nameUser)
  },
  methods: {
    clearData() {
      this.$store.state.tokenUser = "";
      this.$store.state.totalCart = 0;
      this.$store.state.InfoPersonal = {};
      this.$store.state.userName = "";
      this.$store.state.CompareCart = [];
      this.$store.state.StoreCart = [];
      window.localStorage.clear();
      this.$router.push({
        name: "login",
      });
    },
    setUserName(){
      this.userName = localStorage.nameUser
      // console.log(this.userName);
    },
    switchToAccount() {
      if (this.$store.state.tokenUser != "") {
        axios
          .get(this.$store.state.MainLink + "customer/account", {
            headers: {
              Authorization: localStorage.userToken,
            },
          })
          .then((response) => {
            this.$store.state.InfoPersonal = response.data.object;
            // console.log(this.$store.state.InfoPersonal);
          })
          .catch((e) => {
            this.error.push(e);
            console.log(e);
          });
        // console.log("Chuyển qua account");
        this.$router.push({
          name: "personal",
        });
      } else {
        this.clearData()
      }
    },
    switchToCart() {
      if (this.$store.state.tokenUser) {
        axios
          .get(this.$store.state.MainLink + "customer/cart/get", {
            headers: {
              Authorization: localStorage.userToken,
            },
          })
          .then((response) => {
            // console.log(response.data.object);
            this.$store.state.StoreCart = response.data.object;
          })
          .catch((e) => {
            this.error.push(e);
            console.log(e);
          });
        this.$router.push({
          name: "cart",
        });
      } else {
        this.$router.push({
          name: "cart",
        });
      }
    },
    switchToLogin() {
      localStorage.clear();
      this.$router.push({
        name: "login",
      });
    },
    getCategory() {
      axios
          .get(this.$store.state.MainLink + "customer/categories?active=true")
          .then((response) => {
            // console.log(response.data.object);
            this.getCategory = response.data.object
            this.$store.state.getDanhMucCategory = response.data.object
            // this.$store.state.StoreCart = response.data.object;
          })
          .catch((e) => {
            this.error.push(e);
            console.log(e);
          });
    },
    getByCate(item){
      // console.log(item)
      if(this.$router.currentRoute.fullPath != '/category'){
          this.$router.push({
            path: "category",
            // params: { item: item}
          });
          this.$store.state.categorySearch = item;
          console.log(this.$store.state.categorySearch)
      }else{
        this.$store.state.categorySearch = item;
        // console.log(this.$store.state.categorySearch)
      }
    },
    switchToCategory(){
      if(this.$router.currentRoute.fullPath != '/category'){
          this.$router.push({
            path: "category",
          });
          this.$store.state.categorySearch = 0;
          // console.log(this.$store.state.categorySearch)
      }else{
        this.$store.state.categorySearch = 0;
      }
    },
    getSearchProduct(){
      if(this.$router.currentRoute.fullPath != '/category'){
          this.$router.push({
            path: "category",
          });
          this.$store.state.searchProduct = this.searchProduct;
          console.log(this.$store.state.categorySearch)
      }else{
        this.$store.state.searchProduct = this.searchProduct;
        console.log(this.$store.state.searchProduct)
      }
    }
  },
  watch: {
    changeCart: function () {
      this.quantityCart = this.$store.state.StoreCart.length;
    },
    minWidthWindow() {},
    resize: function () {
      {
        alert("resize event detected!");
      }
    },

  },
};
</script>

<style></style>
