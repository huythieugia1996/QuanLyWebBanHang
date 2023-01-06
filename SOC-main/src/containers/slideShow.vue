<template>
  <div id="slideshow">
    <div class="slide-wrapper">
      <div class="slide">
        <img
          src="images/promo/macbook-new.jpg"
        />
      </div>
      <div class="slide">
        <img
          src="images/promo/iphone.jpg"
        />
      </div>
      <div class="slide">
        <img
          src="images/promo/ipad.jpg"
        />
      </div>
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style scoped>
/* CSS reset */
body {
  padding: 2%;
  text-align: center;
}

#slideshow {
  overflow: hidden;
  height: 600px;
  width: 1200px;
  margin: 0 auto;
}

.slide-wrapper {
  width: 3600px;
  -webkit-animation: slide 14s ease infinite;
}

.slide {
  float: left;
  height: 600px;
  width: 1200px;
}


@-webkit-keyframes slide {
  20% {margin-left: 0px;}
  30% {margin-left: -1200px;}
  50% {margin-left: -1200px;}
  60% {margin-left: -2400px;}
  80% {margin-left: -2400px;}
}

</style>