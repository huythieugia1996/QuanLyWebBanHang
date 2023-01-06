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
                <li><span>Tin tức</span></li>
              </ul>
              <h1 class="uk-margin-small-top uk-margin-remove-bottom">
                Tin tức
              </h1>
            </div>
            <div>
              <div class="uk-grid-medium" uk-grid>
                <section class="uk-width-1-1 uk-width-expand@m">
                  <section
                    class="
                      uk-card uk-card-default uk-card-small uk-card-body
                      tm-ignore-container
                    "
                  >
                    <ul class="uk-list uk-list-large uk-list-divider">
                      <li>
                        <article class="uk-article">
                          <div class="uk-article-body">
                            <div
                              class="uk-article-meta uk-margin-xsmall-bottom"
                            >
                              <time>June 4, 2018</time>
                            </div>
                            <div>
                              <h3>
                                <a
                                  target="_blank"
                                  class="uk-link-heading"
                                  href="https://www.thegioididong.com/tin-tuc/nghe-don-la-iphone-14-1343505"
                                  >APPLE chuẩn bị cho ra mắt Iphone 14</a
                                >
                              </h3>
                            </div>
                            <div class="uk-margin-small-top">
                              <p>
                                APPLE đã bắt tay vào thiết kế Iphone 14 với
                                những tính năng vượt trội, nhằm đem lại những
                                trải nghiệm tốt hơn cho người dùng.
                              </p>
                            </div>
                          </div>
                        </article>
                      </li>
                      <li>
                        <article class="uk-article">
                          <div class="uk-article-body">
                            <div
                              class="uk-article-meta uk-margin-xsmall-bottom"
                            >
                              <time>June 4, 2018</time>
                            </div>
                            <div>
                              <h3>
                                <a
                                 target="_blank"
                                  class="uk-link-heading"
                                  href="https://genk.vn/thu-nghiem-that-bai-facebook-quyet-dinh-se-khong-hoat-dong-duoi-hinh-thuc-2-news-feed-nhu-ke-hoach-ban-dau-20180302100424965.chn"
                                  >Facebook sẽ bỏ hình thức 2 News Feed riêng
                                  biệt vì thử nghiệm không thành công</a
                                >
                              </h3>
                            </div>
                            <div class="uk-margin-small-top">
                              <p>
                                Sau khi thử nghiệm không thành công hình thức
                                hiển thị bản tin theo 2 news feed. Facebook đã
                                quyết định từ bỏ hình thức này
                              </p>
                            </div>
                          </div>
                        </article>
                      </li>
                      <li>
                        <article class="uk-article">
                          <div class="uk-article-body">
                            <div
                              class="uk-article-meta uk-margin-xsmall-bottom"
                            >
                              <time>May 29, 2018</time>
                            </div>
                            <div>
                              <h3>
                                <a
                                 target="_blank"
                                  class="uk-link-heading"
                                  href="https://tinhte.vn/thread/facebook-thu-nghiem-nhieu-tinh-nang-xem-video-co-khu-vuc-news-feed-chi-danh-cho-video.2517204/"
                                  >Facebook thử nghiệm Video Feed - khu News
                                  Feed chỉ dành riêng cho Video</a
                                >
                              </h3>
                            </div>
                            <div class="uk-margin-small-top">
                              <p>
                                Với mục tiêu phát triển tương tác của người dùng
                                Facebook và video trên trang, hãng tiếp tục cho
                                thử nghiệm Video Feed - khu News Feed chỉ dành
                                riêng cho Video.
                              </p>
                            </div>
                          </div>
                        </article>
                      </li>
                    </ul>
                  </section>
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
                        <li>
                          <router-link to="/blog"> Blog </router-link>
                        </li>
                        <li class="uk-active">
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
    </main>
  </div>
</template>

<script>
export default {};
</script>

<style>
</style>