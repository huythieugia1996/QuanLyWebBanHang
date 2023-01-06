<template>
  <div></div>
</template>

<script>

import axios from 'axios'
// import VueAxios from 'vue-axios'

export default {
  created() {
    this.getDT();
  },
  methods: {
    getDT() {
      axios
        .get("https://javamahtest.herokuapp.com/api/customer/products")
        .then((response) => {
          console.log(this.response)
          this.getData = response.data;
        })
        .catch((e) => {
          this.error.push(e);
          console.log(e);
        });
      console.log(this.getData);
      
    },
    callFunction: function() {
      var v = this;
      setTimeout(function() {
        v.getDT();
      }, 2000);
    },
    postDT() {
      const formData = new FormData();
      for(let index in this.formData){
        formData.append(index, this.formData.index);
      }
      console.log(this.formData);
      axios
        .post("https://javamahtest.herokuapp.com/api/customer/products", this.formData)
        .then(function() {
          alert("Success!");
        })
        .catch(function(error) {
          alert(error);
        });
        this.callFunction();
    },
    resetInput() {
      this.formData.name = this.formData.birthday = this.formData.address = this.formData.phonenumber = this.formData.martital = this.formData.interests = this.formData.job =
        "";
    },
    deleteItem(id) {
      console.log(id);
      axios
        .delete("https://javamahtest.herokuapp.com/api/customer/products/" + id)
        .then(function thanhcong() {
          alert("Success!");
          
        })
        .catch(function(error) {
          alert(error);
        });
      this.callFunction();
    },
    updateItem(row) {
      console.log(row);
      this.$router.push({
        name: "Update",
        params: { update: row },
      });
    },
  },
}
</script>

<style>

</style>