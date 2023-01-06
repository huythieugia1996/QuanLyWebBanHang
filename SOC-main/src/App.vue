<template>
  <div id="app">
    <AppHeader></AppHeader>
    <router-view></router-view>
    <AppFooter id="footer"></AppFooter>
  </div>
</template>

<script>
import AppHeader from "./containers/AppHeader.vue";
import AppFooter from "./containers/AppFooter.vue";

export default {
  name: "App",
  components: {
    AppHeader,
    AppFooter,
  },
};
</script>

<style>
#footer {
  bottom: 0;
  width: 100%;
}
</style>
