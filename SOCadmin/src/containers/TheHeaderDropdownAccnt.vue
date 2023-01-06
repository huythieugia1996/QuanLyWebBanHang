<template>
  <CDropdown
    inNav
    class="c-header-nav-items"
    placement="bottom-end"
    add-menu-classes="pt-0"
  >
    <template #toggler>
      <CHeaderNavLink style="text-decoration: none">
        <div>Xin chào,{{ userName }} !   </div>
        <div class="c-avatar">
          <img src="/img/avatars/avatar.png" class="c-avatar-img" />
        </div>
      </CHeaderNavLink>
    </template>
    <CDropdownHeader tag="div" class="text-center" color="light">
      <strong>Settings</strong>
    </CDropdownHeader>
    <CDropdownItem>
      <CIcon name="cil-user" /> Thông tin tài khoản
    </CDropdownItem>
    <CDropdownItem> <CIcon name="cil-settings" /> Cài đặt </CDropdownItem>
    <CDropdownItem>
      <CIcon name="cil-dollar" /> Payments
      <CBadge color="secondary" class="mfs-auto">{{ itemsCount }}</CBadge>
    </CDropdownItem>
    <CDropdownItem>
      <CIcon name="cil-file" /> Projects
      <CBadge color="primary" class="mfs-auto">{{ itemsCount }}</CBadge>
    </CDropdownItem>
    <CDropdownDivider />
    <!-- <CDropdownItem>
      <CIcon name="cil-shield-alt" /> Lock Account
    </CDropdownItem> -->
    <CDropdownItem >
      <router-link to="/pages/login" style="text-decoration: none" >
        <CIcon name="cil-lock-locked"/> 
        <a @click="resetLocal()">Đăng xuất</a>
      </router-link>
    </CDropdownItem>
  </CDropdown>
</template>

<script>
export default {
  name: "TheHeaderDropdownAccnt",
  data() {
    return {
      itemsCount: 42,
      userName: "",
    };
  },
  created() {
    // console.log(this.$store.state.userName);
    this.setUserName();
  },
  methods: {
    setUserName() {
      this.userName = localStorage.username
      this.$store.state.userToken =
            localStorage.usertoken
    },
    resetLocal(){
      console.log("Chạy ở đây");
      window.localStorage.clear();
    }
  },
};
</script>

<style scoped>
.c-icon {
  margin-right: 0.3rem;
}
</style>