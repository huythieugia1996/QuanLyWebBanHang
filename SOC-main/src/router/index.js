import Vue from "vue"
import Router from "vue-router"

import Home from "../components/Home.vue"
import error404 from "../components/error404.vue"
import about from "../components/about.vue"
import account from "../components/account.vue"
import article from "../components/article.vue"
import blog from "../components/blog.vue"
import brands from "../components/brands.vue"
// import cart from "../components/cart.vue"
import catalog from "../components/catalog.vue"
import category from "../components/category.vue"
import checkout from "../components/checkout.vue"
import compare from "../components/compare.vue"
import contacts from "../components/contacts.vue"
import delivery from "../components/delivery.vue"
import faq from "../components/faq.vue"
import favorites from "../components/favorites.vue"
import news from "../components/news.vue"
import personal from "../components/personal.vue"
import product from "../components/product.vue"
import setting from "../components/setting.vue"
import subcategory from "../components/subcategory.vue"
import vnpay from "../components/vnpay.vue"
import cartInfo from "../components/cartInfo.vue"
import vnPayResult from "../components/vnPayResult.vue"
import detailOrder from "../components/detailOrder.vue"
import resultOrder from "../components/resultOrder.vue"
import newCart from "../components/newCart.vue"
// import checkout from "../components/checkout.vue"

import Login from "../containers/Login.vue"
import Register from "../containers/Register.vue"
import loadingform from "../containers/loadingform.vue"
import loadingDot from "../containers/loadingDot.vue"
import alertLoginError from "../containers/alertLoginError.vue"
import slideShow from "../containers/slideShow.vue"
import ForgotPass from "../containers/ForgotPass.vue"
import confirmSendEmail from "../containers/confirmSendEmail.vue"
import resetPassword from "../containers/resetPassword.vue"
import starRate from "../containers/starRate.vue"


Vue.use(Router)


const router = new Router({
    mode: 'history',
    routes: [
        { path: "/", component: Home, name: "Home" },
        { path: "/account", component: account, name: "account" },
        { path: "/faq", component: faq, name: "faq" },
        { path: "/news", component: news, name: "news" },
        { path: "/contacts", component: contacts, name: "contacts" },
        { path: "/404", component: error404, name: "error404" },
        { path: "/blog", component: blog, name: "blog" },
        { path: "/about", component: about, name: "about" },
        { path: "/brands", component: brands, name: "brands" },
        { path: "/subcategory", component: subcategory, name: "subcategory" },
        { path: "/compare", component: compare, name: "compare" },
        { path: "/catalog", component: catalog, name: "catalog" },
        { path: "/article", component: article, name: "article", props: true },
        { path: "/cart", component: newCart, name: "cart", props: true },
        { path: "/category", component: category, name: "category", props: true },
        { path: "/checkout", component: checkout, name: "checkout", props: true },
        { path: "/delivery", component: delivery, name: "delivery" },
        { path: "/favorites", component: favorites, name: "favorites" },
        { path: "/personal", component: personal, name: "personal" },
        { path: "/product", component: product, name: "product", props: true },
        { path: "/setting", component: setting, name: "setting" },
        { path: "/vnpay", component: vnpay, name: "vnpay", props: true },
        { path: "/detailorder", component: detailOrder, name: "detailorder", props: true },
        { path: "/cartinfo", component: cartInfo, name: "cartinfo", props: true },
        { path: "/login", component: Login, name: "login" },
        { path: "/register", component: Register, name: "register" },
        { path: "/loading", component: loadingform, name: "loading" },
        { path: "/slideshow", component: slideShow, name: "slideshow" },
        { path: "/loadinglogin", component: loadingDot, name: "loadinglogin" },
        { path: "/alertlogin", component: alertLoginError, name: "alertlogin" },
        { path: "/vnpayresult", component: vnPayResult, name: "vnpayresult" },
        { path: "/forgotpass", component: ForgotPass, name: "forgotpass" },
        { path: "/sendmail", component: confirmSendEmail, name: "sendmail" },
        { path: "/resetpass", component: resetPassword, name: "resetpass" },
        { path: "/resultorder", component: resultOrder, name: "resultorder" },
        { path: "/newcart", component: newCart, name: "newcart", props: true },
        { path: "/starrate", component: starRate, name: "starrate", props: true },
        // { path: "/checkout", component: checkout, name: "checkout", props: true },
    ]
})

export default router