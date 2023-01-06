<template>
  <div class="uk-offcanvas-content">
    <!-- <i class="fas fa-cart-plus"></i> -->
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
                <li><span>Danh mục sản phẩm</span></li>
                <li><span>Laptops</span></li>
              </ul>
              <h1 class="uk-margin-small-top uk-margin-remove-bottom">
                Laptops
              </h1>
              <!-- <div class="uk-text-meta uk-margin-xsmall-top">367 items</div> -->
            </div>

            <div>
              <div class="uk-grid-medium" uk-grid>
                <aside
                  class="uk-width-1-4 tm-aside-column tm-filters"
                  id="filters"
                  uk-offcanvas="overlay: true; container: false;"
                >
                  <div class="uk-offcanvas-bar uk-padding-remove">
                    <div
                      class="
                        uk-card
                        uk-card-default
                        uk-card-small
                        uk-flex
                        uk-flex-column
                        uk-height-1-1
                      "
                    >
                      <header class="uk-card-header uk-flex uk-flex-middle">
                        <div class="uk-grid-small uk-flex-1" uk-grid>
                          <div class="uk-width-expand">
                            <div class="uk-h3">Bộ lọc</div>
                          </div>
                          <button
                            class="uk-offcanvas-close"
                            type="button"
                            uk-close
                          ></button>
                        </div>
                      </header>
                      <div
                        class="uk-margin-remove uk-flex-1 uk-overflow-auto"
                        uk-accordion="multiple: true; targets: &gt; .js-accordion-section"
                        style="flex-basis: auto"
                      >
                        <section class="uk-card-small uk-card-body" style="border-top: none;">
                          <h4 class="uk-margin-small-bottom">Danh mục</h4>
                          <div v-for="item in getAllCate" :key="item.id">
                            <section class="uk-card-small uk-card-body" style="padding: 10px 0px 0px 20px;">
                              <h6 class="uk-margin-small-bottom" v-if="item.name != 'Thương hiệu'">{{item.name}}
                              </h6>
                              <div class="uk-accordion-content" v-if="item.name != 'Thương hiệu' && plusdash == 0" style="margin-top: 0">
                                <ul class="uk-nav uk-nav-default" >
                                  <li v-for="items in item.categories" :key="items.id"><a @click="findByCate(items.id)"><b-icon icon="dash" style="color: gray"></b-icon> {{items.name}}</a></li>
                                </ul>
                              </div>
                           </section>
                          </div>
                        </section>
                        <section class="uk-card-body uk-open js-accordion-section">
                          <h4 class="uk-accordion-title uk-margin-remove">
                            Giá thành
                          </h4>
                          <div class="uk-accordion-content">
                            <div class="uk-grid-small uk-child-width-1-2 uk-text-small" uk-grid>
                              <div>
                                <div class="uk-inline">
                                  <span class="uk-form-icon uk-text-xsmall">Từ</span>
                                  <input
                                    class="uk-input"
                                    type="text"
                                    placeholder="~"
                                    v-model="start"
                                  />
                                </div>
                              </div>
                              <div>
                                <div class="uk-inline">
                                  <span class="uk-form-icon uk-text-xsmall">Tới</span>
                                  <input
                                    class="uk-input"
                                    type="text"
                                    placeholder="~"
                                    v-model="end"
                                  />
                                </div>
                              </div>
                              <button
                                class="uk-button uk-button-default uk-width-1-1"
                                @click="searchPrice()"
                                style="margin-left: 15px"
                              >
                                <span class="uk-margin-xsmall-right" uk-search-icon>
                                </span>Tìm kiếm
                              </button>
                            </div>
                          </div>
                        </section>
                        <section
                          class="uk-card-body js-accordion-section uk-open"
                        >
                          <h4 class="uk-accordion-title uk-margin-remove">
                            Thương hiệu
                          </h4>
                          <div class="uk-accordion-content">
                            <ul class="uk-list tm-scrollbox">
                              <li v-for="item in getBrands" :key="item.id">
                                <div class="form-check">
                                  <input class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault1" v-model="selected" @click="searchProduct(item.name)">
                                    <label class="form-check-label" for="flexRadioDefault1">
                                      {{item.name}}
                                    </label>
                                </div>
                              </li>
                            </ul>
                          </div>
                        </section>
                        <div class="uk-card-body">
                          <button
                            class="uk-button uk-button-default uk-width-1-1"
                            @click="resetData()"
                          >
                            <span
                              class="uk-margin-xsmall-right"
                              uk-icon="icon: close; ratio: .75;"
                            ></span
                            >Bỏ lọc
                          </button>
                        </div>
                      </div>
                    </div>
                  </div>
                </aside>
                <div class="uk-width-expand">
                  <div class="uk-grid-medium uk-child-width-1-1" uk-grid>
                    <div>
                      <div class=" uk-card uk-card-default uk-card-small tm-ignore-container">
                        <div class="uk-grid-collapse uk-child-width-1-1" id="products" uk-grid v-if="getData.length > 0">
                          <div class="uk-card-header">
                            <div class="uk-grid-small uk-flex-middle" uk-grid>
                              <div class="uk-width-1-1 uk-width-expand@s uk-flex uk-flex-center uk-flex-left@s uk-text-small">
                                <span class="uk-margin-small-right uk-text-muted">
                                  <i>Hiển thị các sản phẩm liên quan</i>
                                  </span>
                              </div>
                            </div>
                          </div>
                          <div style="min-height: 950px">
                            <div class="uk-grid-collapse uk-child-width-1-3 tm-products-grid js-products-grid" uk-grid>
                              <article class="tm-product-card" v-for="item in pageOfItems" :key="item.id">
                                <div class="tm-product-card-media">
                                  <div class="tm-ratio tm-ratio-4-3">
                                    <a class="tm-media-box">
                                      <div class="tm-product-card-labels">
                                        <span class="uk-label uk-label-warning" v-if="item.discount != 0">Khuyến mại</span>
                                        <!-- <span class="uk-label uk-label-danger">Khuyến mại</span> -->
                                      </div>
                                      <figure class="tm-media-box-wrap">
                                        <img :src="item.photos[0]" @click="detailProduct(item.id)"/>
                                      </figure>
                                    </a>
                                  </div>
                                </div>
                                <div class="tm-product-card-body">
                                  <div class="tm-product-card-info">
                                    <div class="uk-text-meta uk-margin-xsmall-bottom">
                                      Laptop
                                    </div>
                                    <h3 class="tm-product-card-title">
                                      <a class="uk-link-heading" @click="detailProduct(item.id)">{{ item.name }}</a>
                                    </h3>
                                    <!-- <ul class="uk-list uk-text-small tm-product-card-properties">
                                        <li><span class="uk-text-muted">Diagonal display: </span><span>15.4"</span></li>
                                        <li><span class="uk-text-muted">CPU: </span><span>Intel®&nbsp;Core™ i7</span></li>
                                        <li><span class="uk-text-muted">RAM: </span><span>16&nbsp;GB</span></li>
                                        <li><span class="uk-texưt-muted">Video Card: </span><span>AMD Radeon Pro 555</span></li>
                                      </ul> -->
                                  </div>
                                  <div class="tm-product-card-shop">
                                    <div class="tm-product-card-prices">
                                      <del class="uk-text-meta" style="color:red" v-if="item.discount != 0">{{ formatPrice(item.price) }}đ</del>
                                      <div class="tm-product-card-price">
                                        {{ formatPrice(item.price - item.discount) }}đ
                                      </div>
                                    </div>
                                    <div class="tm-product-card-add">
                                      <div
                                        class="
                                          uk-text-meta
                                          tm-product-card-actions
                                        "
                                      >
                                        <a
                                          class="
                                            tm-product-card-action
                                            js-add-to js-add-to-favorites
                                            tm-action-button-active
                                            js-added-to
                                          "
                                          title="Add to favorites"
                                          @click="addToFavorite(item.id)"
                                          v-if="checkFavorites != ''"
                                        >
                                          <b-icon
                                            :icon="
                                              isFavorited(item)
                                                ? 'heart-fill'
                                                : 'heart'
                                            "
                                            style="color: red"
                                          ></b-icon>
                                          <!-- <b-icon icon="heart" style="color: red"></b-icon> -->
                                          <span
                                            class="tm-product-card-action-text"
                                            >Add to favorites</span
                                          >
                                        </a>
                                        <a
                                          class="
                                            tm-product-card-action
                                            js-add-to js-add-to-compare
                                            tm-action-button-active
                                            js-added-to
                                          "
                                          title="Add to compare"
                                          @click="compareProduct(item)"
                                        >
                                          <span
                                            uk-icon="icon: copy; ratio: .75;"
                                          ></span>
                                          <span
                                            class="tm-product-card-action-text"
                                            >Add to compare</span
                                          >
                                        </a>
                                      </div>
                                      <button
                                        class="
                                          uk-button uk-button-primary
                                          tm-product-card-add-button tm-shine
                                          js-add-to-cart
                                        "
                                        uk-toggle="target: #review"
                                        @click="
                                          setItem(item.id,
                                            item.name,
                                            item.photos[0],
                                            item.price,
                                            item.discount,
                                            item.productColors,
                                            item.productColors[0].color.colorName);
                                        ">
                                        <span
                                          class="
                                            tm-product-card-add-button-icon
                                          "
                                          uk-icon="cart"
                                        ></span
                                        ><span
                                          class="
                                            tm-product-card-add-button-text
                                          "
                                          >Add to cart</span
                                        >
                                      </button>
                                    </div>
                                  </div>
                                </div>
                              </article>
                            </div>
                          </div>
                        </div>
                        
                        <div v-else-if="getData.length == 0 && checkLenght == 1">
                          <div>
                            <article class="uk-card uk-card-default uk-card-body uk-article tm-ignore-container">
                                <header class="uk-text-center">
                                  <h1 class="uk-article-title">Không có sản phẩm phù hợp !</h1>
                                </header>
                                <div class="uk-article-body">
                                  <p class="uk-text-lead uk-text-center">
                                    Có thể sản phẩm bạn tìm không có hoặc cửa hàng chưa cập nhật, vui lòng thử lại hoặc tìm kiếm lại sau !
                                  </p>
                            </div>
                            </article>
                          </div>
                        </div>

                        <div v-else style="min-height: 950px">
                          <div class="container">
                            <div class="row row-cols-3">
                              <p class="placeholder-glow">
                                <span class="placeholder col-8"></span>
                                <span class="placeholder col-3"></span>
                                <span class="placeholder col-5"></span>
                                <span class="placeholder col-9"></span>
                                <span class="placeholder col-11"></span>
                              </p>
                              <p class="placeholder-glow">
                                <span class="placeholder col-8"></span>
                                <span class="placeholder col-3"></span>
                                <span class="placeholder col-5"></span>
                                <span class="placeholder col-9"></span>
                                <span class="placeholder col-11"></span>
                              </p>
                              <p class="placeholder-glow">
                                <span class="placeholder col-8"></span>
                                <span class="placeholder col-3"></span>
                                <span class="placeholder col-5"></span>
                                <span class="placeholder col-9"></span>
                                <span class="placeholder col-11"></span>
                              </p>
                              <p class="placeholder-glow">
                                <span class="placeholder col-8"></span>
                                <span class="placeholder col-3"></span>
                                <span class="placeholder col-5"></span>
                                <span class="placeholder col-9"></span>
                                <span class="placeholder col-11"></span>
                              </p>
                              <p class="placeholder-glow">
                                <span class="placeholder col-8"></span>
                                <span class="placeholder col-3"></span>
                                <span class="placeholder col-5"></span>
                                <span class="placeholder col-9"></span>
                                <span class="placeholder col-11"></span>
                              </p>
                              <p class="placeholder-glow">
                                <span class="placeholder col-8"></span>
                                <span class="placeholder col-3"></span>
                                <span class="placeholder col-5"></span>
                                <span class="placeholder col-9"></span>
                                <span class="placeholder col-11"></span>
                              </p>
                              <p class="placeholder-glow">
                                <span class="placeholder col-8"></span>
                                <span class="placeholder col-3"></span>
                                <span class="placeholder col-5"></span>
                                <span class="placeholder col-9"></span>
                                <span class="placeholder col-11"></span>
                              </p>
                              <p class="placeholder-glow">
                                <span class="placeholder col-8"></span>
                                <span class="placeholder col-3"></span>
                                <span class="placeholder col-5"></span>
                                <span class="placeholder col-9"></span>
                                <span class="placeholder col-11"></span>
                              </p>
                              <p class="placeholder-glow">
                                <span class="placeholder col-8"></span>
                                <span class="placeholder col-3"></span>
                                <span class="placeholder col-5"></span>
                                <span class="placeholder col-9"></span>
                                <span class="placeholder col-11"></span>
                              </p>
                            </div>
                          </div>
                          
                        </div>
                        
                      </div>
                      <br>
                      <div class="text-center">
                          <jw-pagination
                            :pageSize=9
                            style="margin-top: 15px"
                            :labels="customLabels"
                            :maxPages="4"
                            :items="getData"
                            @changePage="onChangePage"
                          ></jw-pagination>
                      </div>
                      
                    </div>
                    
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </section>
      <div id="review" uk-modal>
              <div class="uk-modal-dialog uk-modal-body">
                <button
                  class="uk-modal-close-outside"
                  type="button"
                  uk-close
                ></button>
                <h2 class="uk-modal-title uk-text-center">Thông tin sản phẩm</h2>
                <form class="uk-form-stacked">
                  <div class="uk-grid-small uk-child-width-1-1" uk-grid>
                    <div>
                      <label style="width: 100%;">
                        <div class="uk-form-label">
                          Tên sản phẩm
                        </div>
                        <span>
                          {{this.itemProduct.productName}}
                        </span>
                      </label>
                    </div>
                    <div>
                      <label style="width: 100%;">
                        <div class="uk-form-label">
                          Giá sản phẩm
                        </div>
                        <span>
                          {{formatPrice(this.itemProduct.price)}} đ
                        </span>
                      </label>
                    </div>
                    <div>
                      <label style="width: 100%;">
                        <div class="uk-form-label uk-form-label-required">
                          Màu sắc
                        </div>
                        <div style=" display: flex">
                          <div v-for="(item, index) in itemProduct.colorId" :key="index">
                            <!-- <input type="radio" name="productColor"> -->
                            <button type="button" style="width: 35px; height: 35px; margin-right: 1rem; border-radius: 1rem" @click="setColorProduct(item.colorId,item.color.colorName)" :style="{backgroundColor: '#'+item.color.code}">
                              <input type="radio" name="productColor" hidden :value="item.colorId">
                            </button>
                          </div>
                          
                        </div>
                        <br>
                        <p>Đang chọn màu : <strong>{{itemColor}}</strong></p>
                      </label>
                    </div>
                    <div class="uk-text-center">
                      <button type="button" class="uk-button uk-button-primary" 
                      v-if="itemColor"
                      @click="addToCart(
                            itemProduct.productId,
                            itemProduct.productName,
                            itemProduct.photo,
                            itemProduct.price,
                            itemProduct.discount,
                            itemColorId,
                            itemColor
                          )"> 
                        <span class="tm-product-card-add-button-icon" uk-icon="cart"></span>
                      Thêm vào giỏ hàng
                      </button>
                    </div>
                  </div>
                </form>
              </div>
      </div>
    </main>
  </div>
</template>

<script>
import axios from "axios";
const customLabels = {
    first: '<<',
    last: '>>',
    previous: '<',
    next: '>'
};

export default {
  data() {
    return {
      items: "",
      customLabels,
      pageOfItems: [],
      getData: "",
      checkLenght: 0,
      formData: {
        product_id: "",
        quantiy: "",
        price: "",
      },
      checkFavorites: "",
      CartDetail: [],
      DetailCompare: [],
      searchText: "",
      start: "",
      end: "",
      getNeed: "",
      getTypeMachine: "",
      getTypeProduct: "",
      getAllCate: "",
      plusdash: 0,
      getBrands: "",
      selected: false,
      itemColor: "",
      itemColorId: "",
      itemProduct: {},
    };
  },
  created() {
    this.getDataAccount();
    this.getDT();
    this.getCategory();
    this.callFunction();
  },
  methods: {
    setColorProduct(id, name){
      this.itemColorId = id;
      this.itemColor = name;
    },
    setItem(id, name, photos, price, discount, colorId, colorName){
      this.itemProduct ={
          productName: name,
          productId: id,
          photo: photos,
          price: price,
          quantity: 1,
          discount: discount,
          colorId: colorId,
          colorName: colorName,
      }
    },
    onChangePage(pageOfItems) {
      this.pageOfItems = pageOfItems;
    },
    filteredList() {
      return this.getData.filter((data) =>
        data.toLowerCase().includes(this.searchText.value.toLowerCase())
      );
    },
    formatPrice(value) {
      let val = (value / 1).toFixed(0).replace(".", ",");
      return val.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ".");
    },
    getDT() {
      this.checkFavorites = this.$store.state.tokenUser;
      this.item = this.$store.state.categorySearch
      this.itemSearch = this.$store.state.searchProduct
      // console.log(this.item);
      if (this.item != 0) {
        axios
          .get(
            this.$store.state.MainLink + "customer/products?cate=" + this.item
          )
          .then((response) => {
            this.getData = response.data.object;
            // console.log(this.getData);
          })
          .catch((e) => {
            console.log(e);
          });
      } else if(this.itemSearch != 0){
        axios
          .get(
            this.$store.state.MainLink + "customer/products?find=" + this.itemSearch
          )
          .then((response) => {
            this.getData = response.data.object;
            // console.log(this.getData);
          })
          .catch((e) => {
            console.log(e);
          });
      } else {
        axios
          .get(this.$store.state.MainLink + "customer/products")
          .then((response) => {
            this.getData = response.data.object;
            // console.log(this.getData)
          })
          .catch((e) => {
            console.log(e);
          });
      }
    },
    resetData() {
      this.checkLenght = 0
      this.selected = false;
      this.getData = 0
      axios
        .get(this.$store.state.MainLink + "customer/products")
        .then((response) => {
          this.getData = response.data.object;
        })
        .catch((e) => {
          console.log(e);
        });
    },
    callFunction: function () {
      var v = this;
      setTimeout(function () {
        v.checkLenght = 1
        console.log(v.checkLenght);
      }, 3000);
    },
    detailProduct(id) {
      this.$router.push({
        name: "product",
        params: { item: id },
      });
    },
    addToCart(id, name, photos, price, discount, colorId, colorName) {
      // console.log(this.$store.state.StoreCart)
      
      if (this.$store.state.tokenUser == "") {
        // 
        
              let isnew = true;
        for (var item of this.$store.state.StoreCart) {
          if (item.productId == id && item.colorId == colorId) {
            isnew = false;
            break;
          }
        }
        if (isnew) {
          this.$store.state.StoreCart.push({
            productName: name,
            productId: id,
            photo: photos,
            price: price,
            quantity: 1,
            discount: discount,
            colorId: colorId,
            colorName: colorName,
          });
        } else {
          var idx = 0;
          for(let i = 0; i<this.$store.state.StoreCart.length;i++ ){
            if(this.$store.state.StoreCart[i].productId == id && this.$store.state.StoreCart[i].colorId == colorId){
              idx = i;
              break
            }
          }
          var it = Object.assign({}, this.$store.state.StoreCart[idx]);
          this.$store.state.StoreCart.splice(idx, 1, {
            ...it,
            quantity: it.quantity + 1,
          });
        } 
        this.$toasted.show("Đã thêm vào giỏ hàng !", {
          type: "success",
          duration: 2000,
        });
      } else {
        let item = {
          productId: id,
          price: price,
          quantity: 1,
          colorId: colorId,
        };
        this.$store.state.StoreCart.push(item);
        axios
          .post(this.$store.state.MainLink + "customer/cart/new", item, {
            headers: {
              Authorization: this.$store.state.tokenUser,
            },
          })
          .then((response) => {
            console.log(response);
            axios
              .get(this.$store.state.MainLink + "customer/cart/get", {
                headers: {
                  Authorization: this.$store.state.tokenUser,
                },
              })
              .then((response) => {
                this.$store.state.totalCart = response.data.object.length;
                this.$store.state.StoreCart = response.data.object;
                this.DetailsCart = this.$store.state.StoreCart;
                for (let item in this.DetailsCart) {
                  this.totalPriceProduct.push(response.data.object[item].price);
                }
              })
              .catch((e) => {
                console.log(e);

              });
          });

        this.$toasted.show("Đã thêm vào giỏ hàng !", {
          type: "success",
          duration: 2000,
        });
      }
      this.itemColor = this.itemColorId = ""
    },
    compareProduct(item) {
      if (this.$store.state.CompareCart.length < 2) {
        this.$store.state.CompareCart.push(item);
        this.$toasted.show("Đã chọn sản phẩm để so sánh!", {
          type: "success",
          duration: 2000,
        });
      } else {
        this.$toasted.show("Bạn đã chọn tối đa 2 sản phẩm cần so sánh!", {
          type: "error",
          duration: 2000,
        });
      }
    },
    searchProduct(nameProduct) {
      // console.log(nameProduct)
      axios
        .get(
          this.$store.state.MainLink + "customer/products?find=" + nameProduct
        )
        .then((response) => {
          this.getData = response.data.object;
          // console.log(response.data.object);
        })
        .catch((e) => {
          console.log(e);
        });
    },
    searchPrice() {
      // console.log(this.start + this.end);
      axios
        .get(
          this.$store.state.MainLink +
            "customer/products/price?end=" +
            this.end +
            "&start=" +
            this.start
        )
        .then((response) => {
          this.getData = response.data.object;
          // console.log(response.data.object);
        })
        .catch((e) => {
          console.log(e);
        });
    },
    addToFavorite(item) {
      let productFavorites = { productId: item };
      axios
        .post(
          this.$store.state.MainLink + "customer/favorite/add",
          productFavorites,
          {
            headers: {
              Authorization: this.$store.state.tokenUser,
            },
          }
        )
        .then(() => {
          // console.log(response);
          this.getDataFavorites();
        });
    },
    getDataFavorites() {
      axios
        .get(this.$store.state.MainLink + "customer/favorite/get", {
          headers: {
            Authorization: this.$store.state.tokenUser,
          },
        })
        .then((response) => {
          // console.log(response.data.object);
          this.$store.state.totalFavorites = response.data.object;
        })
        .catch((e) => {
          this.error.push(e);
          console.log(e);
        });
    },
    isFavorited(product) {
      if (this.$store.state.tokenUser == "") {
        return false;
      } else {
        return (
          this.$store.state.totalFavorites.filter(
            (Prct) => Prct.id == product.id
          ).length > 0
        );
      }
    },
    getCategory() {
      axios
          .get(this.$store.state.MainLink + "customer/categories?active=true")
          .then((response) => {
            console.log(response.data.object);
            this.getAllCate = response.data.object
            this.getBrands = response.data.object[1].categories
            // console.log(this.getBrands)
          })
          .catch((e) => {
            this.error.push(e);
            console.log(e);
          });
    },
    findByCate(id){
      this.getData = 0
      axios
          .get(
            this.$store.state.MainLink + "customer/products?cate=" +id
          )
          .then((response) => {
            this.getData = response.data.object;
            this.callFunction();
          })
          .catch((e) => {
            console.log(e);
          });
    },
    getDataAccount() {
      
      if(localStorage.userToken){
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
      }      
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
    searchProductHear(){
      axios
        .get(
          this.$store.state.MainLink + "customer/products?find=" + this.$store.state.searchProduct
        )
        .then((response) => {
          this.getData = response.data.object;
          // console.log(response.data.object);
        })
        .catch((e) => {
          console.log(e);
        });
    }
  },
  watch: {
    '$store.state.categorySearch': function (){
      this.getDT()
    },
    '$store.state.searchProduct': function (){
      this.getDT()
    }
  }
};
</script>

<style>
a {
  text-decoration: none;
}
</style>