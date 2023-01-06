<template>
  <div class="uk-offcanvas-content">
    <main>
      <section class="uk-section uk-section-small">
        <div class="uk-container">
          <div class="uk-grid-medium" uk-grid>
            <div class="uk-width-1-1 uk-width-1-4@m tm-aside-column">
              <div
                class="
                  uk-card uk-card-default uk-card-small
                  tm-ignore-container
                "
                uk-sticky="offset: 90; bottom: true; media: @m;"
              >
                <div class="uk-card-header">
                  <div class="uk-grid-small uk-child-width-1-1" uk-grid>
                    <section>
                      <div
                        class="
                          uk-width-1-3
                          uk-width-1-4@s
                          uk-width-1-2@m
                          uk-margin-auto
                          uk-visible-toggle
                          uk-position-relative
                          uk-border-circle
                          uk-overflow-hidden
                          uk-light
                        "
                      >
                        <img class="uk-width-1-1" src="images/pngegg.png" />
                        <a class="uk-link-reset uk-overlay-primary uk-position-cover uk-hidden-hover">
                          <div class="uk-position-center">
                            <span uk-icon="icon: camera; ratio: 1.25;"></span>
                          </div>
                        </a>
                      </div>
                    </section>
                    <div class="uk-text-center">
                      <div class="uk-h4 uk-margin-remove">
                        {{ this.$store.state.InfoPersonal.fullname }}
                      </div>
                      <div class="uk-text-meta">
                        {{ this.$store.state.InfoPersonal.email }}
                      </div>
                    </div>
                    <div>
                      <div class="uk-grid-small uk-flex-center" uk-grid>
                        <div>
                          <router-link to="setting">
                            <a class="uk-button uk-button-default uk-button-small">
                              <span class="uk-margin-xsmall-right" uk-icon="icon: cog; ratio: .75;"></span>
                              <span>Cài đặt</span></a>
                          </router-link>
                        </div>
                        <div>
                          <button class="uk-button uk-button-default uk-button-small" title="Log out" @click="clearData">
                            <span uk-icon="icon: sign-out; ratio: .75;"></span>
                          </button>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
                <div>
                  <nav>
                    <ul class="uk-nav uk-nav-default tm-nav">
                      <li>
                        <router-link to="account">
                          <a>Đơn hàng</a>
                        </router-link>
                      </li>
                      <li class="uk-active">
                        <router-link to="favorites">
                          <a>Sản phẩm yêu thích</a>
                        </router-link>
                      </li>
                      <li>
                        <router-link to="personal">
                          <a>Thông tin cá nhân</a>
                        </router-link>
                      </li>
                    </ul>
                  </nav>
                </div>
              </div>
            </div>
            <div class="uk-width-1-1 uk-width-expand@m">
              <div
                class="
                  uk-card uk-card-default uk-card-small
                  tm-ignore-container
                "
              >
                <header class="uk-card-header">
                  <h1 class="uk-h2">Sản phẩm yêu thích</h1>
                </header>
                <div class="uk-grid-collapse tm-products-list" uk-grid>
                  <article
                    class="tm-product-card"
                    v-for="item in productFavorites"
                    :key="item.id"
                  >
                    <div class="tm-product-card-media">
                      <div class="tm-ratio tm-ratio-4-3">
                        <a class="tm-media-box">
                          <div class="tm-product-card-labels">
                            <span class="uk-label uk-label-warning" v-if="item.discount != 0">Khuyến mại</span>
                            <!-- <span class="uk-label uk-label-danger">trade-in</span> -->
                          </div>
                          <figure class="tm-media-box-wrap">
                            <img :src="item.photos[0]" />
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
                          <a class="uk-link-heading text-decoration">{{ item.name }}</a>
                        </h3>
                      </div>
                      <div class="tm-product-card-shop">
                        <div class="tm-product-card-prices">
                          <del class="uk-text-meta" v-if="item.discount != 0">{{ formatPrice(item.price) }} đ</del>
                          <div class="tm-product-card-price">
                            {{ formatPrice(item.price - item.discount) }} đ
                          </div>
                        </div>
                        <div class="tm-product-card-add">
                          <div class="uk-text-meta tm-product-card-actions">
                            <a
                              class="
                                tm-product-card-action
                                js-add-to js-add-to-compare
                                tm-action-button-active
                                js-added-to
                              "
                              title="Add to compare"
                              style="text-decoration: none"
                              @click="compareProduct(item)"
                            >
                              <span uk-icon="icon: copy; ratio: .75;"></span>
                              <span class="tm-product-card-action-text"
                                >Thêm vào so sánh</span
                              >
                            </a>
                          </div>
                          <div class="uk-text-meta tm-product-card-actions">
                            <a
                              class="
                                tm-product-card-action
                                js-add-to js-add-to-compare
                                tm-action-button-active
                                js-added-to
                              "
                              title="Delete favorites"
                              style="text-decoration: none"
                              @click="deleteFavorites(item.id)"
                            >
                              <span uk-icon="icon: close; ratio: .75;"></span>
                              <span class="tm-product-card-action-text"
                                >Bỏ yêu thích</span
                              >
                            </a>
                          </div>
                          <button
                            class="
                              uk-button uk-button-primary
                              tm-product-card-add-button tm-shine
                              js-add-to-cart
                            "
                            @click="
                              addToCart(
                                item.id,
                                item.name,
                                item.photos[0],
                                item.price
                              )
                            "
                          >
                            <span
                              class="tm-product-card-add-button-icon"
                              uk-icon="cart"
                            ></span
                            ><span class="tm-product-card-add-button-text"
                              >Thêm vào giỏ hàng</span
                            >
                          </button>
                        </div>
                      </div>
                    </div>
                  </article>
                </div>
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
  name: "favorites",
  data() {
    return {
      productFavorites: [],
    };
  },
  created() {
    this.getDataAccount();
    this.getDataFavorites();
  },
  methods: {
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
    formatPrice(value) {
      let val = (value / 1).toFixed(0).replace(".", ",");
      return val.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ".");
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
          this.productFavorites = this.$store.state.totalFavorites;
        })
        .catch((e) => {
          this.error.push(e);
          console.log(e);
        });
    },
    compareProduct(item) {
      console.log(item);
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
    addToCart(id, name, photos, price) {
      if (this.$store.state.tokenUser == "") {
        let item = {
          name: name,
          id: id,
          photo: photos,
          price: price,
          quantity: 1,
        };
        this.$store.state.StoreCart.push(item);
        this.$toasted.show("Đã thêm vào giỏ hàng !", {
          type: "success",
          duration: 2000,
        });
      } else {
        let item = {
          productId: id,
          price: price,
          quantity: 1,
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
    },
    clearData() {
      this.$store.state.tokenUser = "";
      this.$store.state.totalCart = 0;
      this.$store.state.InfoPersonal = {};
      this.$store.state.userName = "";
      this.$store.state.CompareCart = [];
      this.$store.state.StoreCart = [];
      localStorage.clear();
      this.$router.push({
        name: "login",
      });
    },
    deleteFavorites(item) {
      console.log(item);
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
        .then((response) => {
          console.log(response);
          this.productFavorites = [];
          this.getDataFavorites();
          this.$toasted.show("Đã xóa khỏi sản phẩm yêu thích!", {
            type: "error",
            duration: 2000,
          });
        });
    },
  },
};
</script>

<style scoped>
.text-decoration {
  text-decoration: none;
}
</style>