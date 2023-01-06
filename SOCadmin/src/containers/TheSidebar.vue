<template>
  <CSidebar
    fixed
    :minimize="minimize"
    :show="show"
    @update:show="(value) => $store.commit('set', ['sidebarShow', value])"
  >
    <CSidebarBrand class="d-md-down-none" to="/dashboard">
      <!-- <CIcon
        class="c-sidebar-brand-full"
        name="logo"
        size="custom-size"
        :height="35"
        viewBox="0 0 556 134"
        src="https://lh3.googleusercontent.com/VqAe1GqFBVANc-xtglj-yzxsuWeQDUd0lAaczOl2IHHO6EwFhaaS1Kq_vMOgAfe0q5FD9B_x46J6BQw2ktqN8SgeiQWFLEsBQD5sfizFlGLKA5Q2KKMNmoW9TOIKupWFU_GrZY8w2lRjB3-ZV7nBWW_fhoxa8XjXRfkNJAAs0-VQS3anQDAQcZiYYI3ZKbNQfcRhrgZgrWQY8zyLAIsk3tLz6RXHCcggmcC5NVCj3WL4cwCm1LZK3EVx1FI9WE_j0HiAe1e75373sgXhFk2ghW1gDOKOLIye5sBOI-i-RXeqG3cW9rMijqZcz8ZfXqY-wXxoneliX-0UDPdyrMkzWzOkESAZq6PY5GfpD5xeFUkmWFwgtdLGrEeZU03k0y31NtbtYXtcbObjbuIdMUEiXRvQ3ptKEHcv0oWS6ukQYO4nc9prNNE34PqUOE-hwlidhY3VZBD3JFedPzFIXP06sGmyuHcrDgEoiCxRNxWLIcqfvSQwrJmJcLU1W6rkcduo8N0HJFgmiCtxvll6BLr5Jm4WYAo3u6B088uJvHOyIA-fAQyEPdSURdSGuXIrHg3xACVjmlW0KqTOYXKsLOFTPG4QmAX5lx6d2yXNccrQ1Tnb14ScZLyydZRv-x4kJkGOp_DP21uya_sq7mR1iY45gVBQLHpnoKJGZs_bKSKPVghDjYGXsMFmWtHFyKVd9h398cYOhQGyOTnF5Hb7hv6ThS0=w300-h121-no?authuser=0"
      />
      <CIcon
        class="c-sidebar-brand-minimized"
        name="logo"
        size="custom-size"
        :height="35"
        viewBox="0 0 110 134"
        src="https://lh3.googleusercontent.com/VqAe1GqFBVANc-xtglj-yzxsuWeQDUd0lAaczOl2IHHO6EwFhaaS1Kq_vMOgAfe0q5FD9B_x46J6BQw2ktqN8SgeiQWFLEsBQD5sfizFlGLKA5Q2KKMNmoW9TOIKupWFU_GrZY8w2lRjB3-ZV7nBWW_fhoxa8XjXRfkNJAAs0-VQS3anQDAQcZiYYI3ZKbNQfcRhrgZgrWQY8zyLAIsk3tLz6RXHCcggmcC5NVCj3WL4cwCm1LZK3EVx1FI9WE_j0HiAe1e75373sgXhFk2ghW1gDOKOLIye5sBOI-i-RXeqG3cW9rMijqZcz8ZfXqY-wXxoneliX-0UDPdyrMkzWzOkESAZq6PY5GfpD5xeFUkmWFwgtdLGrEeZU03k0y31NtbtYXtcbObjbuIdMUEiXRvQ3ptKEHcv0oWS6ukQYO4nc9prNNE34PqUOE-hwlidhY3VZBD3JFedPzFIXP06sGmyuHcrDgEoiCxRNxWLIcqfvSQwrJmJcLU1W6rkcduo8N0HJFgmiCtxvll6BLr5Jm4WYAo3u6B088uJvHOyIA-fAQyEPdSURdSGuXIrHg3xACVjmlW0KqTOYXKsLOFTPG4QmAX5lx6d2yXNccrQ1Tnb14ScZLyydZRv-x4kJkGOp_DP21uya_sq7mR1iY45gVBQLHpnoKJGZs_bKSKPVghDjYGXsMFmWtHFyKVd9h398cYOhQGyOTnF5Hb7hv6ThS0=w300-h121-no?authuser=0"
      /> -->
      <CIcon
        class="c-sidebar-brand-full"
        name="logo"
        size="custom-size"
        :height="35"
        viewBox="0 0 556 134"
        src="/SOCLogo.png"
      />
      <CIcon
        class="c-sidebar-brand-minimized"
        name="logo"
        size="custom-size"
        :height="35"
        viewBox="0 0 110 134"
        src="/SOCLogo.png"
      />
      <!-- <img src="SOCLogo.png" class="c-sidebar-brand-minimized" name="logo" size="custom-size" :height="35" viewBox="0 0 110 134"/> -->
      <!-- <img src="" alt="Logo" height="35"> -->
    </CSidebarBrand>

    <CRenderFunction flat :content-to-render="$options.nav" />
    <CSidebarMinimizer
      class="d-md-down-none"
      @click.native="$store.commit('set', ['sidebarMinimize', !minimize])"
    />
  </CSidebar>
</template>

<script>
import nav from "./_nav";

export default {
  name: "TheSidebar",
  nav,
  computed: {
    show() {
      return this.$store.state.sidebarShow;
    },
    minimize() {
      return this.$store.state.sidebarMinimize;
    },
  },
};
</script>
