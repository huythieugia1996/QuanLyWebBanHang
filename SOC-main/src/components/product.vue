<template>
  <div class="uk-offcanvas-content">
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
              <li><a>Laptops</a></li>
              <li><a>Thông tin sản phẩm</a></li>
              <!-- <li>
                <span>{{ getData.name.substr(6, 25) }} ...</span>
              </li> -->
            </ul>
            <h1 class="uk-margin-small-top uk-margin-remove-bottom">
              <!-- {{ getData.name.substr(6, 25) }} ... -->
            </h1>
          </div>
          <div>
            <div class="uk-grid-medium uk-child-width-1-1" uk-grid>
              <div>
                <div
                  class="
                    uk-card uk-card-default uk-card-small
                    tm-ignore-container
                  "
                >
                  <div
                    class="uk-grid-small uk-grid-collapse uk-grid-match"
                    uk-grid
                  >
                    <div class="uk-width-1-1 uk-width-expand@m">
                      <div
                        class="uk-grid-collapse uk-child-width-1-1"
                        uk-slideshow="finite: true; ratio: 4:3;"
                        uk-grid
                      >
                        <div>
                          <ul class="uk-slideshow-items" uk-lightbox>
                            <li
                              v-for="(item, index) in getData.photos"
                              :key="index"
                            >
                              <a
                                class="
                                  uk-card-body
                                  tm-media-box tm-media-box-zoom
                                "
                                :href="item"
                              >
                                <figure class="tm-media-box-wrap">
                                  <img :src="item" />
                                </figure>
                              </a>
                            </li>
                          </ul>
                        </div>
                        <div>
                          <div class="uk-card-body uk-flex uk-flex-center">
                            <div class="uk-width-1-2 uk-visible@s">
                              <div uk-slider="finite: true">
                                <div class="uk-position-relative">
                                  <div class="uk-slider-container">
                                    <ul
                                      class="
                                        tm-slider-items
                                        uk-slider-items
                                        uk-child-width-1-4
                                        uk-grid
                                        uk-grid-small
                                      "
                                    >
                                      <li
                                        :uk-slideshow-item="index"
                                        v-for="(item, index) in getData.photos"
                                        :key="index"
                                      >
                                        <div class="tm-ratio tm-ratio-1-1">
                                          <a
                                            class="
                                              tm-media-box tm-media-box-frame
                                            "
                                          >
                                            <figure class="tm-media-box-wrap">
                                              <img :src="item" /></figure
                                          ></a>
                                        </div>
                                      </li>
                                    </ul>
                                    <!-- <div>
                                      <a
                                        class="
                                          uk-position-center-left-out
                                          uk-position-small
                                        "
                                      
                                        uk-slider-item="previous"
                                        uk-slidenav-previous
                                      ></a
                                      ><a
                                        class="
                                          uk-position-center-right-out
                                          uk-position-small
                                        "
                                      
                                        uk-slider-item="next"
                                        uk-slidenav-next
                                      ></a>
                                    </div> -->
                                  </div>
                                </div>
                              </div>
                            </div>
                            <ul
                              class="uk-slideshow-nav uk-dotnav uk-hidden@s"
                            ></ul>
                          </div>
                        </div>
                      </div>
                    </div>
                    <div class="uk-width-1-1 uk-width-1-3@m tm-product-info">
                      <div class="uk-card-body">
                        <div class="uk-margin">
                          <div class="uk-grid-medium" uk-grid>
                            <div>
                              <div
                                class="
                                  uk-text-large
                                  uk-margin-xsmall-bottom
                                  uk-text-bold
                                "
                              >
                                <p>{{ getData.name }}</p>
                              </div>
                              <div>
                                <span
                                  class="
                                    uk-label
                                    uk-label-warning
                                    uk-margin-xsmall-right
                                  "
                                  >Xu hướng</span
                                >
                                <span
                                  class="
                                    uk-label
                                    uk-label-danger
                                    uk-margin-xsmall-right
                                  "
                                  v-if="getData.discount != 0"
                                  >Khuyến mại</span
                                >
                              </div>
                              <!-- <br>
                              <p>
                                  Màu sắc
                              </p>
                              <div style=" display: flex">
                                  <button style="width: 35px; height: 35px; margin-right: 1rem; border-radius: 1rem" :style="{backgroundColor: '#'+item.color.code}" v-for="item in getData.productColors" :key="item.id" @click="setColorProduct(item.colorId,item.color.colorName)">
                                  </button>
                              </div>
                              <br>
                              <p>Đang chọn màu : <strong>{{itemColor}}</strong></p> -->
                            </div>

                            <div>
                              <div
                                class="uk-text-small uk-margin-xsmall-bottom"
                              >
                                Màu sắc
                              </div>
                              <ul
                                class="uk-subnav uk-subnav-pill tm-variations"
                                uk-switcher
                              >
                                <li
                                  v-for="item in getData.productColors"
                                  :key="item.id"
                                >
                                  <a
                                    class="tm-variation-color"
                                    :uk-tooltip="item.color.colorName"
                                  >
                                    <div
                                      :style="{
                                        backgroundColor: '#' + item.color.code,
                                      }"
                                      @click="
                                        setColorProduct(
                                          item.colorId,
                                          item.color.colorName
                                        )
                                      "
                                    ></div>
                                  </a>
                                </li>
                              </ul>
                              <p>
                                Đang chọn màu : <strong>{{ itemColor }}</strong>
                              </p>
                            </div>
                          </div>
                        </div>
                        <div class="uk-margin">
                          <div
                            class="
                              uk-padding-small
                              uk-background-primary-lighten
                              uk-border-rounded
                            "
                          >
                            <div
                              class="uk-grid-small uk-child-width-1-1"
                              uk-grid
                            >
                              <div>
                                <div
                                  class="tm-product-price"
                                  style="text-align: center"
                                >
                                  {{ formatPrice(getData.price) }}đ
                                </div>
                              </div>
                              <div>
                                <div class="uk-grid-small" uk-grid>
                                  <div style="width: 85%">
                                    <button
                                      v-if="
                                        getData.status == 'Đang bán' &&
                                        itemColor
                                      "
                                      class="
                                        uk-button uk-button-primary
                                        tm-product-add-button tm-shine
                                        js-add-to-cart
                                      "
                                      style="width: 100%"
                                      @click="
                                        addToCart(
                                          getData.id,
                                          getData.name,
                                          getData.photos[0],
                                          getData.price,
                                          getData.discount,
                                          itemColorId,
                                          itemColor
                                        )
                                      "
                                    >
                                      <b-icon
                                        icon="cart-plus"
                                        style="margin-right: 15px"
                                      ></b-icon>
                                      Thêm vào giỏ hàng
                                    </button>
                                    <button
                                      v-else
                                      class="
                                        uk-button uk-button-primary
                                        tm-product-add-button tm-shine
                                        js-add-to-cart
                                      "
                                      style="width: 100%"
                                      @click="
                                        addToCart(
                                          getData.id,
                                          getData.name,
                                          getData.photos[0],
                                          getData.price,
                                          getData.discount,
                                          itemColorId,
                                          itemColor
                                        )
                                      "
                                      disabled
                                    >
                                      <b-icon
                                        icon="cart-plus"
                                        style="margin-right: 15px"
                                      ></b-icon>
                                      Thêm vào giỏ hàng
                                    </button>
                                  </div>
                                  <div
                                    class="
                                      uk-width-auto
                                      uk-width-expand@s
                                      uk-flex
                                      uk-flex-middle
                                      uk-text-meta
                                    "
                                  >
                                    <a
                                      class="
                                        js-add-to js-add-to-compare
                                        tm-action-button-active
                                        js-added-to
                                      "
                                      uk-tooltip="Add to compare"
                                      @click="compareProduct(getData)"
                                    >
                                      <span uk-icon="copy"></span>
                                    </a>
                                  </div>
                                </div>
                              </div>
                            </div>
                          </div>
                        </div>

                        <div class="uk-margin">
                          <div
                            class="
                              uk-padding-small
                              uk-background-muted
                              uk-border-rounded
                            "
                          >
                            <div
                              class="
                                uk-grid-small uk-child-width-1-1 uk-text-small
                              "
                              uk-grid
                            >
                              <div>
                                <div class="uk-grid-collapse" uk-grid>
                                  <span
                                    class="uk-margin-xsmall-right"
                                    uk-icon="cart"
                                  ></span>
                                  <div>
                                    <div class="uk-text-bolder">
                                      Đặt hàng trực tuyến
                                    </div>
                                    <div class="uk-text-small uk-text-muted">
                                      {{ getData.status }}
                                    </div>
                                  </div>
                                </div>
                              </div>
                              <div>
                                <div class="uk-grid-collapse" uk-grid>
                                  <span
                                    class="uk-margin-xsmall-right"
                                    uk-icon="location"
                                  ></span>
                                  <div>
                                    <div class="uk-text-bolder">
                                      Mua tại cửa hàng
                                    </div>
                                    <div class="uk-text-small uk-text-muted">
                                      {{ getData.status }}
                                    </div>
                                  </div>
                                </div>
                              </div>
                              <div>
                                <div class="uk-grid-collapse" uk-grid>
                                  <span
                                    class="uk-margin-xsmall-right"
                                    uk-icon="check"
                                  ></span>
                                  <div>
                                    <div class="uk-text-bolder">
                                      Thời gian bảo hành
                                    </div>
                                    <div class="uk-text-small uk-text-muted">
                                      {{ getData.warranty }} tháng
                                    </div>
                                  </div>
                                </div>
                              </div>
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                    <div
                      class="uk-width-1-1 tm-product-description"
                      id="description"
                    >
                      <header>
                        <nav
                          class="tm-product-nav"
                          uk-sticky="offset: 60; bottom: #description; cls-active: tm-product-nav-fixed;"
                        >
                          <ul
                            class="uk-subnav uk-subnav-pill js-product-switcher"
                            uk-switcher="connect: .js-tabs"
                          >
                            <li>
                              <a class="js-scroll-to-description">Tổng quan</a>
                            </li>
                            <li>
                              <a class="js-scroll-to-description"
                                >Thông số kỹ thuật</a
                              >
                            </li>
                            <li>
                              <a class="js-scroll-to-description">
                                Đánh giá sản phẩm
                              </a>
                            </li>
                            <li>
                              <a class="js-scroll-to-description">Q&A</a>
                            </li>
                          </ul>
                        </nav>
                      </header>
                      <div class="uk-card-body">
                        <div class="uk-switcher js-product-switcher js-tabs">
                          <section>
                            <article class="uk-article">
                              <div class="uk-article-body">
                                <div
                                  v-for="(item, index) in getBlog"
                                  :key="index"
                                >
                                  <h5
                                    style="margin-top: 15px"
                                    class="tm-product-card-title"
                                    v-if="item.type == 'header'"
                                  >
                                    {{ item.content }}
                                  </h5>
                                  <div
                                    class="tm-wrapper uk-text-center"
                                    style="margin: 15px 15px 15px 15px"
                                  >
                                    <figure>
                                      <a>
                                        <img
                                          :src="item.content"
                                          alt="Hình ảnh sản phẩm ..."
                                          v-if="item.type == 'photo'"
                                        />
                                      </a>
                                    </figure>
                                  </div>
                                  <h4
                                    class="uk-text-center"
                                    v-if="item.type == 'content'"
                                  >
                                    {{ item.content }}
                                  </h4>
                                  <p v-else-if="item.type == 'text'">
                                    {{ item.content }}
                                  </p>
                                </div>
                              </div>
                            </article>
                          </section>
                          <section>
                            <h2>Thông số kỹ thuật</h2>
                            <table
                              class="
                                uk-table
                                uk-table-divider
                                uk-table-justify
                                uk-table-responsive
                              "
                            >
                              <tr
                                v-for="itemProduct in getData.productDetails"
                                :key="itemProduct.id"
                              >
                                <th class="uk-width-medium">
                                  {{ itemProduct.propertyName }}
                                </th>
                                <td class="uk-table-expand">
                                  {{ itemProduct.propertyValue }}
                                </td>
                              </tr>
                            </table>
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
                                  Xếp hạng đánh giá
                                </div>
                                <!-- <div class="uk-heading-primary">5.0</div>
                                <div class="uk-flex uk-flex-center">
                                  <ul class="uk-iconnav tm-rating">
                                    <li>
                                      <span
                                        class="uk-text-warning"
                                        uk-icon="star"
                                      ></span>
                                    </li>
                                    <li>
                                      <span
                                        class="uk-text-warning"
                                        uk-icon="star"
                                      ></span>
                                    </li>
                                    <li>
                                      <span
                                        class="uk-text-warning"
                                        uk-icon="star"
                                      ></span>
                                    </li>
                                    <li>
                                      <span
                                        class="uk-text-warning"
                                        uk-icon="star"
                                      ></span>
                                    </li>
                                    <li>
                                      <span
                                        class="uk-text-warning"
                                        uk-icon="star"
                                      ></span>
                                    </li>
                                  </ul>
                                </div> -->
                                <div style="height: 20px">
                                  <div id="rating" style="margin-left: 15px">
                                    <input
                                      type="radio"
                                      id="star5"
                                      name="rating"
                                      value="5"
                                      checked="checked"
                                      @click="setRate(5)"
                                    />
                                    <label
                                      class="full"
                                      for="star5"
                                      title="Awesome - 5 stars"
                                    ></label>

                                    <input
                                      type="radio"
                                      id="star4"
                                      name="rating"
                                      value="4"
                                      @click="setRate(4)"
                                    />
                                    <label
                                      class="full"
                                      for="star4"
                                      title="Pretty good - 4 stars"
                                    ></label>

                                    <input
                                      type="radio"
                                      id="star3"
                                      name="rating"
                                      value="3"
                                      @click="setRate(3)"
                                    />
                                    <label
                                      class="full"
                                      for="star3"
                                      title="Meh - 3 stars"
                                    ></label>

                                    <input
                                      type="radio"
                                      id="star2"
                                      name="rating"
                                      value="2"
                                      @click="setRate(2)"
                                    />
                                    <label
                                      class="full"
                                      for="star2"
                                      title="Kinda bad - 2 stars"
                                    ></label>

                                    <input
                                      type="radio"
                                      id="star1"
                                      name="rating"
                                      value="1"
                                      @click="setRate(1)"
                                    />
                                    <label
                                      class="full"
                                      for="star1"
                                      title="Sucks big time - 1 star"
                                    ></label>
                                  </div>
                                </div>

                                <br />
                                <div class="uk-margin-small-top uk-text-meta">
                                  dựa trên {{ getComment.length }} đánh giá
                                </div>
                                <button
                                  class="
                                    uk-button
                                    uk-button-primary
                                    uk-margin-top
                                    uk-width-1-1
                                  "
                                  uk-toggle="target: #review01"
                                >
                                  Viết đánh giá
                                </button>
                              </div>
                              <div class="uk-width-1-1 uk-width-expand@s">
                                <div
                                  class="
                                    uk-grid-small
                                    uk-grid-divider
                                    uk-child-width-1-1
                                  "
                                  uk-grid
                                >
                                  <article
                                    v-for="item in getComment"
                                    :key="item.id"
                                  >
                                    <section
                                      class="uk-grid-small uk-child-width-1-1"
                                      uk-grid
                                    >
                                      <header>
                                        <div class="uk-h4 uk-margin-remove">
                                          {{ item.name }}
                                        </div>
                                        <time class="uk-text-meta">{{
                                          item.timeCreated
                                        }}</time>
                                      </header>
                                      <div style="position: relative">
                                        <div
                                          id="rating"
                                          style="
                                            position: absolute;
                                            top: 0;
                                            left: 10px;
                                            line-height: 25px;
                                          "
                                        >
                                          <input
                                            type="radio"
                                            :id="`star5${item.id}`"
                                            name="rating"
                                            value="5"
                                            disabled
                                            :checked="setChecked(5, item.rate)"
                                          />
                                          <label
                                            class="full"
                                            disabled
                                            :for="`star5${item.id}`"
                                            title="Awesome - 5 stars"
                                          ></label>

                                          <input
                                            type="radio"
                                            :id="`star4${item.id}`"
                                            name="rating"
                                            value="4"
                                            disabled
                                            :checked="setChecked(4, item.rate)"
                                          />
                                          <label
                                            class="full"
                                            disabled
                                            :for="`star4${item.id}`"
                                            title="Pretty good - 4 stars"
                                          ></label>

                                          <input
                                            type="radio"
                                            :id="`star3${item.id}`"
                                            name="rating"
                                            value="3"
                                            disabled
                                            :checked="setChecked(3, item.rate)"
                                          />
                                          <label
                                            class="full"
                                            disabled
                                            :for="`star3${item.id}`"
                                            title="Meh - 3 stars"
                                          ></label>

                                          <input
                                            type="radio"
                                            :id="`star2${item.id}`"
                                            name="rating"
                                            value="2"
                                            disabled
                                            :checked="setChecked(2, item.rate)"
                                          />
                                          <label
                                            class="full"
                                            disabled
                                            :for="`star2${item.id}`"
                                            title="Kinda bad - 2 stars"
                                          ></label>

                                          <input
                                            type="radio"
                                            :id="`star1${item.id}`"
                                            name="rating"
                                            value="1"
                                            disabled
                                            :checked="setChecked(1, item.rate)"
                                          />
                                          <label
                                            class="full"
                                            disabled
                                            :for="`star1${item.id}`"
                                            title="Sucks big time - 1 star"
                                          ></label>
                                        </div>
                                        <div style="margin-top: 30px">
                                          <p>
                                            {{ item.detail }}
                                          </p>
                                        </div>
                                      </div>
                                    </section>
                                  </article>
                                </div>
                              </div>
                            </div>
                          </section>
                          <section>
                            <ul
                              class="uk-list-divider uk-list-large"
                              uk-accordion="multiple: true"
                            >
                              <li>
                                <a class="uk-accordion-title">
                                  <b
                                    >Tôi mua sản phẩm tại SOC shop sẽ có khác
                                    biệt gì ?</b
                                  >
                                </a>
                                <div class="uk-accordion-content">
                                  <p>
                                    SOC shop là đại lý ủy quyền bán máy tính
                                    chính hãng tại Việt Nam, SOC shop chỉ bán
                                    một loại mặt hàng duy nhất đó là :
                                    <b> HÀNG CHÍNH HÃNG.</b>
                                  </p>
                                </div>
                              </li>
                              <li>
                                <a class="uk-accordion-title"
                                  ><b
                                    >Tại sao hàng chính hãng lại bán đắt hơn
                                    hàng xách tay bán tại các cửa hàng ?</b
                                  ></a
                                >
                                <div class="uk-accordion-content">
                                  <p>
                                    Hàng chính hãng là hàng được kiểm định chất
                                    lượng bởi Bộ TT & TT, vì vậy chất lượng hàng
                                    hoá được đảm bảo 100% là hàng chuẩn, có
                                    nguồn gốc rõ ràng. Với hàng xách tay bán bên
                                    ngoài thị trường, giá tuy rẻ hơn nhưng chất
                                    lượng hoàn toàn không đảm bảo, không dán tem
                                    kiểm định của Bộ TT & TT cũng như tem của
                                    đơn vị nhập khẩu (SOC), các linh kiện cấu
                                    thành thiết bị thường là những linh kiện kém
                                    chất lượng được mua về từ Trung Quốc theo số
                                    lượng lớn, các cửa hàng tự tiến hành lắp
                                    ghép bằng tay các linh kiện đó lại để tạo
                                    thành 1 sản phẩm sau đó bán cho người tiêu
                                    dùng.
                                  </p>
                                </div>
                              </li>
                              <li>
                                <a class="uk-accordion-title"
                                  ><p>
                                    <b
                                      >Tôi sẽ được ưu đãi và khuyến mại gì khii
                                      mua hàng tại SOC SHOP ?</b
                                    >
                                  </p></a
                                >
                                <div class="uk-accordion-content">
                                  <p>
                                    Tùy từng thời điểm, sản phẩm sẽ có những
                                    khuyến mại khác nhau. SOC Shop cam kết sẽ
                                    mang đến anh/chị những khuyến mại kèm những
                                    dịch vụ chăm sóc khách hàng tốt nhất. Để tìm
                                    hiểu về khuyến mại, anh / chị vui lòng gọi
                                    1800 9999 (miễn phí) để được tư vấn.
                                  </p>
                                </div>
                              </li>
                              <li>
                                <a class="uk-accordion-title"
                                  ><b
                                    >Những dịch vụ chăm sóc khách hàng của SOC
                                    SHOP là gì ?</b
                                  ></a
                                >
                                <div class="uk-accordion-content">
                                  <p>
                                    Sản phẩm có lỗi phần cứng thuộc phạm vi bảo
                                    hành sẽ được hưởng chính sách 1 đổi 1 trong
                                    vòng 01 tháng kể từ ngày mua máy; từ tháng
                                    thứ 02 đến tháng thứ 12, sản phẩm sẽ được
                                    bảo hành theo đúng chính sách của Hãng
                                    Apple. Miễn phí cài đặt ứng dụng/ game và
                                    bảo dưỡng trong quá trình sử dụng
                                  </p>
                                </div>
                              </li>
                              <li>
                                <a class="uk-accordion-title"
                                  ><b
                                    >SOC SHOP có giao hàng tại nhà không ? Chất
                                    lượng sản phẩm có bị thay đổi đổi không ?</b
                                  ></a
                                >
                                <div class="uk-accordion-content">
                                  <p>
                                    SOC Shop giao hàng miễn phí trên toàn quốc,
                                    cam kết giao hàng trong 60 phút. Và chất
                                    lượng hoàn toàn đảm bảo, 100% hàng chính
                                    hãng - nguyên seal khi đến tận tay Anh/ chị.
                                    Anh/chị có thể ấn nút "Mua Ngay" hoặc gọi số
                                    tổng đài 1800 9999để được hỗ trợ
                                  </p>
                                </div>
                              </li>
                            </ul>
                          </section>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <!-- Related items-->
              <section>
                <div uk-slider="finite: true">
                  <div
                    class="uk-grid-small uk-flex-middle uk-margin-bottom"
                    uk-grid
                  >
                    <h2 class="uk-width-expand uk-text-center uk-text-left@s">
                      Sản phẩm liên quan
                    </h2>
                    <div class="uk-visible@s">
                      <a
                        class="tm-slidenav"
                        uk-slider-item="previous"
                        uk-slidenav-previous
                      ></a>
                      <a
                        class="tm-slidenav"
                        uk-slider-item="next"
                        uk-slidenav-next
                      ></a>
                    </div>
                  </div>
                  <div>
                    <div
                      class="
                        uk-card uk-card-default uk-card-small
                        tm-ignore-container
                      "
                    >
                      <div class="uk-position-relative">
                        <div class="uk-slider-container">
                          <div
                            class="
                              uk-slider-items
                              uk-grid-collapse
                              uk-child-width-1-3
                              uk-child-width-1-4@m
                              tm-products-grid
                            "
                          >
                            <article
                              class="tm-product-card"
                              v-for="item in getDataTrending"
                              :key="item.id"
                            >
                              <div class="tm-product-card-media">
                                <div class="tm-ratio tm-ratio-4-3">
                                  <a class="tm-media-box">
                                    <div class="tm-product-card-labels">
                                      <span
                                        class="uk-label uk-label-warning"
                                        v-if="item.discount != 0"
                                        >Khuyến mại</span
                                      >
                                      <!-- <span class="uk-label uk-label-danger">trade-in</span> -->
                                    </div>
                                    <figure class="tm-media-box-wrap">
                                      <img
                                        :src="item.photos[0]"
                                        alt='Apple MacBook Pro 15" Touch Bar MPTU2LL/A 256GB (Silver)'
                                        @click="showProductRe(item.id)"
                                      />
                                    </figure>
                                  </a>
                                </div>
                              </div>
                              <div class="tm-product-card-body">
                                <div class="tm-product-card-info">
                                  <div
                                    class="uk-text-meta uk-margin-xsmall-bottom"
                                  >
                                    Laptop
                                  </div>
                                  <h3 class="tm-product-card-title">
                                    <a
                                      class="uk-link-heading"
                                      @click="showProductRe(item.id)"
                                      >{{ item.name }}</a
                                    >
                                  </h3>
                                </div>
                                <div class="tm-product-card-shop">
                                  <div class="tm-product-card-prices">
                                    <del
                                      class="uk-text-meta"
                                      v-if="item.discount != 0"
                                      >{{ item.price }}</del
                                    >
                                    <div class="tm-product-card-price">
                                      {{
                                        formatPrice(item.price - item.discount)
                                      }}
                                      đ
                                    </div>
                                  </div>
                                  <div class="tm-product-card-add">
                                    <div
                                      class="
                                        uk-text-meta
                                        tm-product-card-actions
                                      "
                                    ></div>
                                    <button
                                      class="
                                        uk-button uk-button-primary
                                        tm-product-card-add-button tm-shine
                                        js-add-to-cart
                                      "
                                    >
                                      <span
                                        class="tm-product-card-add-button-icon"
                                        uk-icon="cart"
                                      ></span>
                                      <span
                                        class="tm-product-card-add-button-text"
                                        >add to cart</span
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
                    <ul
                      class="
                        uk-slider-nav
                        uk-dotnav
                        uk-flex-center
                        uk-margin-top
                        uk-hidden@s
                      "
                    ></ul>
                  </div>
                </div>
              </section>
            </div>
            <div id="review01" uk-modal>
              <div class="uk-modal-dialog uk-modal-body">
                <button
                  class="uk-modal-close-outside"
                  type="button"
                  uk-close
                ></button>
                <h2 class="uk-modal-title uk-text-center">Đánh giá</h2>
                <form class="uk-form-stacked">
                  <div class="uk-grid-small uk-child-width-1-1" uk-grid>
                    <div>
                      <label style="width: 100%">
                        <div class="uk-form-label uk-form-label-required">
                          Tên
                        </div>
                        <input
                          class="uk-input"
                          type="text"
                          required
                          v-model="comments.name"
                        />
                      </label>
                    </div>
                    <div>
                      <div class="uk-form-label uk-form-label-required">
                          Xếp hạng
                      </div>
                      <div id="rating" style="">
                        <input
                          type="radio"
                          id="star5"
                          name="rating"
                          value="5"
                          checked="checked"
                          @click="setRate(5)"
                        />
                        <label
                          class="full"
                          for="star5"
                          title="Awesome - 5 stars"
                        ></label>

                        <input
                          type="radio"
                          id="star4"
                          name="rating"
                          value="4"
                          @click="setRate(4)"
                        />
                        <label
                          class="full"
                          for="star4"
                          title="Pretty good - 4 stars"
                        ></label>

                        <input
                          type="radio"
                          id="star3"
                          name="rating"
                          value="3"
                          @click="setRate(3)"
                        />
                        <label
                          class="full"
                          for="star3"
                          title="Meh - 3 stars"
                        ></label>

                        <input
                          type="radio"
                          id="star2"
                          name="rating"
                          value="2"
                          @click="setRate(2)"
                        />
                        <label
                          class="full"
                          for="star2"
                          title="Kinda bad - 2 stars"
                        ></label>

                        <input
                          type="radio"
                          id="star1"
                          name="rating"
                          value="1"
                          @click="setRate(1)"
                        />
                        <label
                          class="full"
                          for="star1"
                          title="Sucks big time - 1 star"
                        ></label>
                      </div>
                    </div>
                    <div>
                      <label style="width: 100%; margin-top: 10px">
                        <div class="uk-form-label">Email</div>
                        <input
                          class="uk-input"
                          type="email"
                          required
                          v-model="comments.email"
                        />
                      </label>
                    </div>
                    <div>
                      <label style="width: 100%">
                        <div class="uk-form-label uk-form-label-required">
                          Đánh giá của bạn
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
                      <!-- <button
                        type="button"
                        class="uk-button uk-button-primary"
                        @click="sendComment(getData.blogs.id)"
                      >
                        Gửi đánh giá
                      </button> -->
                      <button
                        class="uk-button uk-button-primary uk-modal-close"
                        type="button"
                        style="border: none; color: white"
                        @click="sendComment(getData.blogs.id)"
                      >
                        Gửi đánh giá
                      </button>
                    </div>
                  </div>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "product",
  props: {
    item: Number,
  },
  data() {
    return {
      getData: "",
      getBlog: "",
      getComment: "",
      getDataTrending: "",
      formData: {
        id: this.item,
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
      comments: {
        email: "",
        name: "",
        blogId: 0,
        detail: "",
      },
      cartDetails: [],
      itemColor: "",
      itemColorId: "",
      rate: 0,
    };
  },
  created() {
    this.getDataAccount();
    this.getDetailProduct();
    this.getDT();
    // this.callFunction();
  },
  methods: {
    setChecked(value, rate) {
      return value == rate ? "checked" : "";
    },
    setRate(value) {
      this.rate = value;
      console.log(this.rate);
    },
    setColorProduct(id, name) {
      this.itemColorId = id;
      this.itemColor = name;
    },
    formatPrice(value) {
      let val = (value / 1).toFixed(0).replace(".", ",");
      return val.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ".");
    },
    showProductRe(id) {
      console.log(id);
      this.item = id;
      this.getDetailProduct();
    },
    getDetailProduct() {
      console.log(this.item);
      axios
        .get(this.$store.state.MainLink + "customer/products/" + this.item)
        .then((response) => {
          this.getData = response.data.object;
          this.getBlog = response.data.object.blogs.blogDetails;
          console.log(response.data.object.blogs);
          this.getComment = response.data.object.blogs.comments;
          console.log(this.getComment);
          console.log("GetData", this.getData);
          // console.log("GetBlog", this.getBlog);
        })
        .catch((e) => {
          console.log(e);
        });
    },
    addToCart(id, name, photos, price, discount, colorId, colorName) {
      // console.log(id, name, photos, price);
      // console.log(getData);
      if (this.$store.state.tokenUser == "") {
        let item = {
          productName: name,
          productId: id,
          photo: photos,
          price: price,
          quantity: 1,
          discount: discount,
          colorId: colorId,
          colorName: colorName,
        };
        console.log(item);
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
    },
    compareProduct(item) {
      // console.log(item)
      // console.log(this.$store.state.CompareCart.length);
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
    getDT() {
      this.checkFavorites = this.$store.state.tokenUser;
      axios
        .get(this.$store.state.MainLink + "customer/products/trending")
        .then((response) => {
          this.getDataTrending = response.data.object;
          // console.log(this.getData);
        })
        .catch((e) => {
          console.log(e);
        });
    },
    sendComment(id) {
      let item = {
        blogId: id,
        name: this.comments.name,
        email: this.comments.email,
        detail: this.comments.detail,
      };
      // console.log(item);
      axios
        .post(this.$store.state.MainLink + "customer/comment/new", item)
        .then(() => {
          this.getDetailProduct();
          this.$toasted.show("Đã đăng bình luận!", {
            type: "success",
            duration: 2000,
          });
        })
        .catch((e) => {
          this.$toasted.show("Lỗi, vui lòng thử lại!", {
            type: "success",
            duration: 2000,
          });
          console.log(e);
        });
    },
    getDataAccount() {
      if (this.$store.state.tokenUser) {
        axios
          .get(this.$store.state.MainLink + "customer/account", {
            headers: {
              Authorization: localStorage.userToken,
            },
          })
          .then((response) => {
            this.$store.state.userName = response.data.object.fullname;
            this.$store.state.tokenUser = localStorage.userToken;
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
  },
};
</script>

<style>
a {
  text-decoration: none;
}
a.uk-accordion-title,
a.js-scroll-to-description {
  text-decoration: none;
}
@import url(//netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.css);
/*reset css*/
div,
label {
  margin: 0;
  padding: 0;
}
body {
  margin: 20px;
}
h1 {
  font-size: 1.5em;
  margin: 10px;
}
/****** Style Star Rating Widget *****/
#rating {
  border: none;
  float: left;
}
#rating > input {
  display: none;
} /*ẩn input radio - vì chúng ta đã có label là GUI*/
#rating > label:before {
  margin: 5px;
  font-size: 1.25em;
  font-family: FontAwesome;
  display: inline-block;
  content: "\f005";
} /*1 ngôi sao*/
#rating > .half:before {
  content: "\f089";
  position: absolute;
} /*0.5 ngôi sao*/
#rating > label {
  color: #ddd;
  float: right;
} /*float:right để lật ngược các ngôi sao lại đúng theo thứ tự trong thực tế*/
/*thêm màu cho sao đã chọn và các ngôi sao phía trước*/
#rating > input:checked ~ label,
#rating:not(:checked) > label:hover,
#rating:not(:checked) > label:hover ~ label {
  color: #ffd700;
}
/* Hover vào các sao phía trước ngôi sao đã chọn*/
#rating > input:checked + label:hover,
#rating > input:checked ~ label:hover,
#rating > label:hover ~ input:checked ~ label,
#rating > input:checked ~ label:hover ~ label {
  color: #ffed85;
}
</style>