<template>
  <div class="uk-offcanvas-content">
    <!-- <loadingDot class="loadingData"></loadingDot> -->

    <main>
      <section
        class="uk-position-relative uk-visible-toggle uk-light"
        uk-slideshow="min-height: 300; max-height: 600;"
      >
        <ul class="uk-slideshow-items">
          <li style="background-color: #0b0a12">
            <a>
              <figure class="uk-container uk-height-1-1">
                <img
                  src="images/promo/macbook-new.jpg"
                  alt="New Macbook"
                  width="1200"
                  height="600"
                  uk-cover
                />
              </figure>
            </a>
          </li>
          <li style="background-color: #ce071e">
            <a>
              <figure class="uk-container uk-height-1-1">
                <img
                  src="images/promo/fixsize.jpg"
                  alt="iPhone"
                  width="1200"
                  height="600"
                  uk-cover
                />
              </figure>
            </a>
          </li>
          <li style="background-color: #1f2024">
            <a>
              <figure class="uk-container uk-height-1-1">
                <img
                  src="images/promo/asuszenproduo.jpg"
                  alt="iPad"
                  width="1200"
                  height="600"
                  uk-cover
                />
              </figure>
            </a>
          </li>
        </ul>
        <a
          class="uk-position-center-left uk-position-small uk-hidden-hover"
          uk-slideshow-item="previous"
          uk-slidenav-previous
        ></a>
        <a
          class="uk-position-center-right uk-position-small uk-hidden-hover"
          uk-slideshow-item="next"
          uk-slidenav-next
        ></a>
        <div class="uk-position-bottom-center uk-position-small">
          <ul class="uk-slideshow-nav uk-dotnav"></ul>
        </div>
      </section>
      <section class="uk-section uk-section-default uk-section-small">
        <div class="uk-container">
          <div class="row">
            <div class="col" v-for="item in getAllCate" :key="item.id">
              <a class="uk-link-muted uk-text-center uk-display-block uk-padding-small uk-box-shadow-hover-large" @click="getByCate(item.id)">
                  <div class="tm-ratio tm-ratio-4-3">
                    <div class="tm-media-box">
                      <figure class="tm-media-box-wrap">
                        <img
                          class="item-brand"
                          src="images/catalog/laptops.png"
                          alt="Laptops"
                        />
                      </figure>
                  </div>
              </div>
              <div class="text-center">
                {{item.name}}
              </div>
              </a>
            </div>
          </div>
          <div class="uk-margin uk-text-center">
            <router-link to="/category" style="text-decoration: none">
              <a class="uk-link-muted uk-text-uppercase tm-link-to-all"
                ><span>Xem tất cả sản phẩm</span
                ><span uk-icon="icon: chevron-right; ratio: .75;"></span
              ></a>
            </router-link>
          </div>
          <!-- <div
            class="
              uk-grid-small
              uk-child-width-1-2
              uk-child-width-1-3@s
              uk-child-width-1-6@m
            "
            uk-grid
          >
            <div v-for="item in getAllCate" :key="item.id">
              <router-link to="/category" style="text-decoration: none" >
                <a class="uk-link-muted uk-text-center uk-display-block uk-padding-small uk-box-shadow-hover-large" style="text-decoration: none" > 
                  <div class="tm-ratio tm-ratio-4-3">
                    <div class="tm-media-box">
                      <figure class="tm-media-box-wrap">
                        <img
                          class="item-brand"
                          src="images/catalog/laptops.png"
                          alt="Laptops"
                        />
                      </figure>
                    </div>
                  </div>
                  <div class="uk-margin-small-top">
                    <div class="uk-text-truncate">{{item.name}}</div>
                  </div>
                </a>
              </router-link>
            </div>
          </div>
          <div class="uk-margin uk-text-center">
            <router-link to="/category" style="text-decoration: none">
              <a class="uk-link-muted uk-text-uppercase tm-link-to-all"
                ><span>Xem tất cả sản phẩm</span
                ><span uk-icon="icon: chevron-right; ratio: .75;"></span
              ></a>
            </router-link>
          </div> -->
        </div>
      </section>
      <section class="uk-section uk-section-small">
        <div class="uk-container">
          <h2 class="uk-text-center">Sản phẩm đang khuyến mại</h2>
          <div class="uk-card uk-card-default tm-ignore-container">
            <div
              class="
                uk-grid-collapse uk-child-width-1-3 uk-child-width-1-4@m
                tm-products-grid
              "
              uk-grid
            >
              <article
                class="tm-product-card"
                v-for="item in getProductDiscount"
                :key="item.id"
              >
                <div class="tm-product-card-media">
                  <div class="tm-ratio tm-ratio-4-3">
                    <a class="tm-media-box" style="text-decoration: none">
                      <div class="tm-product-card-labels">
                        <span class="uk-label uk-label-warning"
                          >Xu hướng</span
                        >
                        <span class="uk-label uk-label-danger" v-if="item.discount != 0">Khuyến mại</span>
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
                      <a
                        class="uk-link-heading"
                        @click="detailProduct(item.id)"
                        >{{ item.name.substr(6,40) }} ...</a
                      >
                    </h3>
                  </div>
                  <div class="tm-product-card-shop">
                    <div class="tm-product-card-prices">
                      <del class="uk-text-meta" style="color:red" v-if="item.discount != 0">{{ formatPrice(item.price) }}đ</del>
                      <div class="tm-product-card-price">
                        {{ formatPrice(item.price - item.discount) }}đ
                      </div>
                    </div>
                    <div class="tm-product-card-add">
                      <div class="uk-text-meta tm-product-card-actions">
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
                            :icon="isFavorited(item) ? 'heart-fill' : 'heart'"
                            style="color: red"
                          ></b-icon>
                          <!-- <b-icon icon="heart" style="color: red"></b-icon> -->
                          <span class="tm-product-card-action-text"
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
                          <span uk-icon="icon: copy; ratio: .75;"></span>
                          <span class="tm-product-card-action-text"
                            >Add to compare</span
                          >
                        </a>
                      </div>
                      <!-- {{item.productColors[0]}} -->
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
                          class="tm-product-card-add-button-icon"
                          uk-icon="cart"
                        ></span>
                        <span class="tm-product-card-add-button-text"
                          >add to cart</span
                        >
                      </button>
                    </div>
                  </div>
                </div>
                <!-- {{item.productColors[0].color.colorName}} -->
              </article>
            </div>
          </div>
          <div class="uk-margin uk-text-center">
            <router-link to="/category" style="text-decoration: none">
              <a class="uk-link-muted uk-text-uppercase tm-link-to-all"
                ><span>Toàn bộ sản phẩm</span
                ><span uk-icon="icon: chevron-right; ratio: .75;"></span
              ></a>
            </router-link>
          </div>
        </div>
      </section>
      <section class="uk-section uk-section-small">
        <div class="uk-container">
          <h2 class="uk-text-center">Sản phẩm nổi bật</h2>
          <div class="uk-card uk-card-default tm-ignore-container">
            <div
              class="
                uk-grid-collapse uk-child-width-1-3 uk-child-width-1-4@m
                tm-products-grid
              "
              uk-grid
            >
              <article
                class="tm-product-card"
                v-for="item in getData"
                :key="item.id"
              >
                <div class="tm-product-card-media">
                  <div class="tm-ratio tm-ratio-4-3">
                    <a class="tm-media-box" style="text-decoration: none">
                      <div class="tm-product-card-labels">
                        <span class="uk-label uk-label-warning"
                          >Xu hướng</span
                        >
                        <span class="uk-label uk-label-danger" v-if="item.discount != 0">Khuyến mại</span>
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
                      <a
                        class="uk-link-heading"
                        @click="detailProduct(item.id)"
                        >{{ item.name.substr(6,40) }} ...</a
                      >
                    </h3>
                  </div>
                  <div class="tm-product-card-shop">
                    <div class="tm-product-card-prices">
                      <del class="uk-text-meta" style="color:red" v-if="item.discount != 0">{{ formatPrice(item.price) }}đ</del>
                      <div class="tm-product-card-price">
                        {{ formatPrice(item.price - item.discount) }}đ
                      </div>
                    </div>
                    <div class="tm-product-card-add">
                      <div class="uk-text-meta tm-product-card-actions">
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
                            :icon="isFavorited(item) ? 'heart-fill' : 'heart'"
                            style="color: red"
                          ></b-icon>
                          <!-- <b-icon icon="heart" style="color: red"></b-icon> -->
                          <span class="tm-product-card-action-text"
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
                          <span uk-icon="icon: copy; ratio: .75;"></span>
                          <span class="tm-product-card-action-text"
                            >Add to compare</span
                          >
                        </a>
                      </div>
                      <!-- {{item.productColors[0]}} -->
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
                          class="tm-product-card-add-button-icon"
                          uk-icon="cart"
                        ></span>
                        <span class="tm-product-card-add-button-text"
                          >add to cart</span
                        >
                      </button>
                    </div>
                  </div>
                </div>
                <!-- {{item.productColors[0].color.colorName}} -->
              </article>
            </div>
          </div>
          <div class="uk-margin uk-text-center">
            <router-link to="/category" style="text-decoration: none">
              <a class="uk-link-muted uk-text-uppercase tm-link-to-all"
                ><span>Toàn bộ sản phẩm</span
                ><span uk-icon="icon: chevron-right; ratio: .75;"></span
              ></a>
            </router-link>
          </div>
        </div>
      </section>
      <section class="uk-section uk-section-small">
        <div class="uk-container">
          <h2 class="uk-text-center">Sản phẩm mới</h2>
          <div class="uk-card uk-card-default tm-ignore-container">
            <div
              class="
                uk-grid-collapse uk-child-width-1-3 uk-child-width-1-4@m
                tm-products-grid
              "
              uk-grid
            >
              <article
                class="tm-product-card"
                v-for="item in getNewData"
                :key="item.id"
              >
                <div class="tm-product-card-media">
                  <div class="tm-ratio tm-ratio-4-3">
                    <a class="tm-media-box" style="text-decoration: none">
                      <div class="tm-product-card-labels">
                        <span class="uk-label uk-label-warning" v-if="item.discount != 0">Khuyến mại</span>
                        <span class="uk-label uk-label-danger">Mới</span>
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
                      <a
                        class="uk-link-heading"
                        @click="detailProduct(item.id)"
                        >{{ item.name.substr(6, 40) }} ...</a
                      >
                    </h3>
                  </div>
                  <div class="tm-product-card-shop">
                    <div class="tm-product-card-prices">
                      <del class="uk-text-meta" style="color:red" v-if="item.discount != 0">{{ formatPrice(item.price) }}đ</del>
                      <div class="tm-product-card-price">
                        {{ formatPrice(item.price - item.discount) }}đ
                      </div>
                    </div>
                    <div class="tm-product-card-add">
                      <div class="uk-text-meta tm-product-card-actions">
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
                            :icon="isFavorited(item) ? 'heart-fill' : 'heart'"
                            style="color: red"
                          ></b-icon>
                          <!-- <b-icon icon="heart" style="color: red"></b-icon> -->
                          <span class="tm-product-card-action-text"
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
                          <span uk-icon="icon: copy; ratio: .75;"></span>
                          <span class="tm-product-card-action-text"
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
                        "
                      >
                        <span
                          class="tm-product-card-add-button-icon"
                          uk-icon="cart"
                        ></span>
                        <span class="tm-product-card-add-button-text"
                          >add to cart</span
                        >
                      </button>
                    </div>
                  </div>
                </div>
              </article>
            </div>
          </div>
          <div class="uk-margin uk-text-center">
            <router-link to="/category" style="text-decoration: none">
              <a class="uk-link-muted uk-text-uppercase tm-link-to-all"
                ><span>Toàn bộ sản phẩm</span
                ><span uk-icon="icon: chevron-right; ratio: .75;"></span
              ></a>
            </router-link>
          </div>
        </div>
      </section>
      <section class="uk-section uk-section-default uk-section-small">
        <div class="uk-container">
          <h2 class="uk-text-center">Thương hiệu phổ biến</h2>
          <div class="uk-margin-medium-top" uk-slider="finite: true">
            <div class="uk-position-relative">
              <div class="uk-grid-small uk-flex-middle" uk-grid>
                <div class="uk-visible@m">
                  <a uk-slidenav-previous uk-slider-item="previous"></a>
                </div>
                <div class="uk-width-expand uk-slider-container">
                  <ul
                    class="
                      uk-slider-items
                      uk-child-width-1-3
                      uk-child-width-1-6@s
                      uk-grid
                      uk-grid-large
                    "
                  >
                    <li>
                      <div class="tm-ratio tm-ratio-16-9">
                        <a
                          class="uk-link-muted tm-media-box tm-grayscale"
                          title="Apple"
                          @click="getByCate('12')"
                        >
                          <figure class="tm-media-box-wrap">
                            <img src="images/brands/apple.svg" alt="Apple" />
                          </figure>
                        </a>
                      </div>
                    </li>
                    <li>
                      <div class="tm-ratio tm-ratio-16-9">
                        <a
                          class="uk-link-muted tm-media-box tm-grayscale"
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
                      <div class="tm-ratio tm-ratio-16-9">
                        <a
                          class="uk-link-muted tm-media-box tm-grayscale"
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
                      <div class="tm-ratio tm-ratio-16-9">
                        <a
                          class="uk-link-muted tm-media-box tm-grayscale"
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
                      <div class="tm-ratio tm-ratio-16-9">
                        <a
                          class="uk-link-muted tm-media-box tm-grayscale"
                          title="Intel"
                          @click="getByCate('Intel')"
                        >
                          <figure class="tm-media-box-wrap">
                            <img src="images/brands/intel.svg" alt="Intel" />
                          </figure>
                        </a>
                      </div>
                    </li>
                    <li>
                      <div class="tm-ratio tm-ratio-16-9">
                        <a
                          class="uk-link-muted tm-media-box tm-grayscale"
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
                      <div class="tm-ratio tm-ratio-16-9">
                        <a
                          class="uk-link-muted tm-media-box tm-grayscale"
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
                      <div class="tm-ratio tm-ratio-16-9">
                        <a
                          class="uk-link-muted tm-media-box tm-grayscale"
                          title="Lenovo"
                          @click="getByCate('7')"
                        >
                          <figure class="tm-media-box-wrap">
                            <img src="images/brands/lenovo.svg" alt="Lenovo" />
                          </figure>
                        </a>
                      </div>
                    </li>
                    <li>
                      <div class="tm-ratio tm-ratio-16-9">
                        <a
                          class="uk-link-muted tm-media-box tm-grayscale"
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
                      <div class="tm-ratio tm-ratio-16-9">
                        <a
                          class="uk-link-muted tm-media-box tm-grayscale"
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
                      <div class="tm-ratio tm-ratio-16-9">
                        <a
                          class="uk-link-muted tm-media-box tm-grayscale"
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
                      <div class="tm-ratio tm-ratio-16-9">
                        <a
                          class="uk-link-muted tm-media-box tm-grayscale"
                          title="Canon"
                          @click="getByCate('Canon')"
                        >
                          <figure class="tm-media-box-wrap">
                            <img src="images/brands/canon.svg" alt="Canon" />
                          </figure>
                        </a>
                      </div>
                    </li>
                  </ul>
                </div>
                <div class="uk-visible@m">
                  <a uk-slider-item="next" uk-slidenav-next></a>
                </div>
              </div>
            </div>
            <ul
              class="
                uk-slider-nav
                uk-dotnav
                uk-flex-center
                uk-margin-medium-top
                uk-hidden@m
              "
            ></ul>
          </div>
          <div class="uk-margin uk-text-center">
            <a class="uk-link-muted uk-text-uppercase tm-link-to-all" @click="switchToBrand()"
              ><span>Xem tất cả thương hiệu</span
              ><span uk-icon="icon: chevron-right; ratio: .75;"></span
            ></a>
          </div>
        </div>
      </section>
      <section class="uk-section uk-section-small">
        <div class="uk-container">
          <h2 class="uk-text-center">Blog</h2>
          <div
            class="
              uk-grid-medium
              uk-grid-match
              uk-child-width-1-1
              uk-child-width-1-3@s
            "
            uk-grid
          >
            <div v-for="items in getBlogHL" :key="items.id">
              <a @click="switchToBlog(items.id)">
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
                    <figure
                      class="tm-media-box uk-cover-container uk-margin-remove"
                    >
                      <img
                        :src="items.photo"
                        alt="Everything You Need to Know About the MacBook Pro"
                        uk-cover="uk-cover"
                      />
                    </figure>
                  </div>
                  <div class="uk-card-body">
                    <div class="uk-article-body">
                      <div class="uk-article-meta uk-margin-xsmall-bottom">
                        <time>{{ items.timeCreated }}</time>
                      </div>
                      <div>
                        <h3 class="uk-margin-remove">
                          {{ items.title }}
                        </h3>
                      </div>
                      <div class="uk-margin-small-top">
                        <p>
                          {{ items.shortText }}
                        </p>
                      </div>
                    </div>
                  </div>
                </article>
              </a>
            </div>
          </div>
          <div class="uk-margin uk-text-center">
            <router-link to="/blog" style="text-decoration: none">
            <a class="uk-link-muted uk-text-uppercase tm-link-to-all">
              <span>Xem tất cả bài viết</span>
              <span uk-icon="icon: chevron-right; ratio: .75;"></span>
            </a>
            </router-link>
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
                        <!-- <input class="uk-input" type="text" required /> -->
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

export default {
  name: "HomePage",
  components: {
    // loadingDot,
  },
  data() {
    return {
      getData: "",
      getBlogHL: "",
      getAllCate: "",
      getNewData: "",
      getProductDiscount: "",
      itemProduct: {},
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
        checkFavorites: "",
      },
      itemColor: "",
      itemColorId: "",
      productFavorites: [],
    };
  },
  created() {
    this.getDT();
    this.getCategory();
    this.getBlog();
    this.getDataAccount();
    this.getNewProduct();
  },
  methods: {
    setColorProduct(id, name){
      this.itemColorId = id;
      this.itemColor = name;
      console.log(id, name);
    },
    switchToBlog(id) {
      this.$router.push({
        name: "article",
        params: { item: id },
      });
    },
    switchToBrand(){
      this.$router.push({
        name: "brands",
      });
    },
    formatPrice(value) {
      let val = (value / 1).toFixed(0).replace(".", ",");
      return val.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ".");
    },
    getDT() {
      this.checkFavorites = this.$store.state.tokenUser;
      axios
        .get(this.$store.state.MainLink + "customer/products/trending")
        .then((response) => {
          this.getData = response.data.object;
          // console.log(this.getData);
        })
        .catch((e) => {
          console.log(e);
        });

      axios
        .get(this.$store.state.MainLink + "customer/products/discount")
        .then((response) => {
          this.getProductDiscount = response.data.object;
          // console.log(this.getData);
        })
        .catch((e) => {
          console.log(e);
        });
    },
    getNewProduct() {
      this.checkFavorites = this.$store.state.tokenUser;
      axios
        .get(this.$store.state.MainLink + "customer/products/mostnew")
        .then((response) => {
          this.getNewData = response.data.object;
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
    detailProduct(id) {
      // console.log(id);
      this.$router.push({
        name: "product",
        params: { item: id },
      });
    },
    addToCart(id, name, photos, price, discount, colorId, colorName) {


      
      if (this.$store.state.tokenUser == "") {
        // let item = {
        //   productName: name,
        //   productId: id,
        //   photo: photos,
        //   price: price,
        //   quantity: 1,
        //   discount: discount,
        //   colorId: colorId,
        //   colorName: colorName,
        // };
        
    

      
      // let added = false;
      // let inNew = true;
      // this.$store.state.StoreCart = this.$store.state.StoreCart.map(
      //   product =>{
      //     if(product.productId == id){
      //       inNew = false;
      //       if(product.colorId != colorId){
      //         added = true;
      //         return product;
      //       }else{
      //         return {...product, quantity: product.quantity + 1}
      //       }
      //     }else{
      //       return product;
      //     }
      //   }
      // )
      // if(added || inNew){
      //   this.$store.state.StoreCart.push(item);
      // }



      
      //   this.$toasted.show("Đã thêm vào giỏ hàng !", {
      //     type: "success",
      //     duration: 2000,
      //   });


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
        // console.log(item)
        this.$store.state.StoreCart.push(item);
        axios
          .post(this.$store.state.MainLink + "customer/cart/new", item, {
            headers: {
              Authorization: this.$store.state.tokenUser,
            },
          })
          .then(() => {
            // console.log(response);
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
                // for (let item in this.DetailsCart) {
                //   this.totalPriceProduct.push(response.data.object[item].price);
                // }
                this.$toasted.show("Đã thêm vào giỏ hàng !", {
                  type: "success",
                  duration: 2000,
                });
              })
              .catch((e) => {
                console.log(e);
                this.$toasted.show("Thêm giỏ hàng thất bại !", {
                  type: "error",
                  duration: 2000,
                });
              });
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
    addToFavorite(item) {
      let productFavorites = { productId: item };
      // console.log(productFavorites);
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
      // this.getDataFavorites();
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
          // this.error.push(e);
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
    getBlog() {
      axios
        .get(this.$store.state.MainLink + "customer/blog")
        .then((response) => {
          this.getBlogHL = response.data.object.slice(0, 3);

          // console.log(this.getBlogHL);
        })
        .catch((e) => {
          console.log(e);
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
    getCategory() {
      axios
          .get(this.$store.state.MainLink + "customer/categories?active=true")
          .then((response) => {
            // console.log(response.data.object[2].categories);
            this.getAllCate = response.data.object[2].categories
            // this.getBrands = response.data.object[1].categories
            // console.log(this.getBrands)
            // this.$store.state.StoreCart = response.data.object;
          })
          .catch((e) => {
            this.error.push(e);
            console.log(e);
          });
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
      // console.log(colorName)
    }
  },
};
</script>

<style scope>
a {
  text-decoration: none;
}
</style>
