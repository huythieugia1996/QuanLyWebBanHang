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
                <li><span>FAQ</span></li>
              </ul>
              <h1 class="uk-margin-small-top uk-margin-remove-bottom">FAQ</h1>
            </div>
            <div>
              <div class="uk-grid-medium" uk-grid>
                <section class="uk-width-1-1 uk-width-expand@m">
                  <article
                    class="
                      uk-card
                      uk-card-default
                      uk-card-small
                      uk-card-body
                      uk-article
                      tm-ignore-container
                    "
                  >
                    <ul
                      class="uk-list-divider uk-list-large"
                      uk-accordion="multiple: true"
                    >
                      <li>
                        <a class="uk-accordion-title" href="#">
                          <b
                            >Tôi mua sản phẩm tại SOC shop sẽ có khác biệt gì
                            ?</b
                          >
                        </a>
                        <div class="uk-accordion-content">
                          <p>
                            SOC shop là đại lý ủy quyền bán máy tính chính hãng
                            tại Việt Nam, SOC shop chỉ bán một loại mặt hàng duy
                            nhất đó là : <b> HÀNG CHÍNH HÃNG.</b>
                          </p>
                        </div>
                      </li>
                      <li>
                        <a class="uk-accordion-title" href="#"
                          ><b
                            >Tại sao hàng chính hãng lại bán đắt hơn hàng xách
                            tay bán tại các cửa hàng ?</b
                          ></a
                        >
                        <div class="uk-accordion-content">
                          <p>
                            Hàng chính hãng là hàng được kiểm định chất lượng
                            bởi Bộ TT & TT, vì vậy chất lượng hàng hoá được đảm
                            bảo 100% là hàng chuẩn, có nguồn gốc rõ ràng. Với
                            hàng xách tay bán bên ngoài thị trường, giá tuy rẻ
                            hơn nhưng chất lượng hoàn toàn không đảm bảo, không
                            dán tem kiểm định của Bộ TT & TT cũng như tem của
                            đơn vị nhập khẩu (SOC), các linh kiện cấu thành
                            thiết bị thường là những linh kiện kém chất lượng
                            được mua về từ Trung Quốc theo số lượng lớn, các cửa
                            hàng tự tiến hành lắp ghép bằng tay các linh kiện đó
                            lại để tạo thành 1 sản phẩm sau đó bán cho người
                            tiêu dùng.
                          </p>
                        </div>
                      </li>
                      <li>
                        <a class="uk-accordion-title" href="#"
                          ><p>
                            <b
                              >Tôi sẽ được ưu đãi và khuyến mại gì khii mua hàng
                              tại SOC SHOP ?</b
                            >
                          </p></a
                        >
                        <div class="uk-accordion-content">
                          <p>
                            Tùy từng thời điểm, sản phẩm sẽ có những khuyến mại
                            khác nhau. SOC Shop cam kết sẽ mang đến anh/chị
                            những khuyến mại kèm những dịch vụ chăm sóc khách
                            hàng tốt nhất. Để tìm hiểu về khuyến mại, anh / chị
                            vui lòng gọi 1800 9999 (miễn phí) để được tư vấn.
                          </p>
                        </div>
                      </li>
                      <li>
                        <a class="uk-accordion-title" href="#"
                          ><b
                            >Những dịch vụ chăm sóc khách hàng của SOC SHOP là
                            gì ?</b
                          ></a
                        >
                        <div class="uk-accordion-content">
                          <p>
                            Sản phẩm có lỗi phần cứng thuộc phạm vi bảo hành sẽ
                            được hưởng chính sách 1 đổi 1 trong vòng 01 tháng kể
                            từ ngày mua máy; từ tháng thứ 02 đến tháng thứ 12,
                            sản phẩm sẽ được bảo hành theo đúng chính sách của
                            Hãng Apple. Miễn phí cài đặt ứng dụng/ game và bảo
                            dưỡng trong quá trình sử dụng
                          </p>
                        </div>
                      </li>
                      <li>
                        <a class="uk-accordion-title" href="#"
                          ><b
                            >SOC SHOP có giao hàng tại nhà không ? Chất lượng
                            sản phẩm có bị thay đổi đổi không ?</b
                          ></a
                        >
                        <div class="uk-accordion-content">
                          <p>
                            SOC Shop giao hàng miễn phí trên toàn quốc, cam kết
                            giao hàng trong 60 phút. Và chất lượng hoàn toàn đảm
                            bảo, 100% hàng chính hãng - nguyên seal khi đến tận
                            tay Anh/ chị. Anh/chị có thể ấn nút "Mua Ngay" hoặc
                            gọi số tổng đài 1800 9999để được hỗ trợ
                          </p>
                        </div>
                      </li>
                    </ul>
                  </article>
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
                        <li>
                          <router-link to="/news"> Tin mới </router-link>
                        </li>
                        <li class="uk-active">
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
      <section class="uk-section uk-section-default uk-section-small">
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
      </section>
    </main>
  </div>
</template>

<script>
export default {};
</script>

<style>
</style>