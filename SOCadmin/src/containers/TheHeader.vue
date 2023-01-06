<template>
  <CHeader fixed with-subheader light>
    <CToggler
      in-header
      class="ml-3 d-lg-none"
      @click="$store.commit('toggleSidebarMobile')"
    />
    <CToggler
      in-header
      class="ml-3 d-md-down-none"
      @click="$store.commit('toggleSidebarDesktop')"
    />
    <CHeaderBrand class="mx-auto d-lg-none" to="/dashboard">
      <CIcon
        height="48"
        alt="Logo"
        src="/logoSOC.png"
      />
      <!-- <img src="/SOCLogo.png" alt="alternatetext" height="48"> -->
    </CHeaderBrand>
    <CHeaderNav class="d-md-down-none mr-auto">
      <!-- <CBreadcrumbRouter class="border-0 mb-0" /> -->
    </CHeaderNav>
    <CHeaderNav class="mr-4">
      <TheHeaderDropdownAccnt />
    </CHeaderNav>
  </CHeader>
</template>

<script>
import TheHeaderDropdownAccnt from "./TheHeaderDropdownAccnt";

export default {
  name: "TheHeader",
  components: {
    TheHeaderDropdownAccnt,
  },
};
</script>
