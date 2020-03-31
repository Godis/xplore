<template>
  <el-card :body-style="{ padding: '0px' }">
    <img src="http://lorempixel.com/400/500/food/" class="image">
    <div style="padding: 14px;">
      <span>{{ product.brand }} {{ product.category }}</span>
      <div class="bottom clearfix">
        <time class="time">3 days ago</time>
        <el-button class="button">Operating</el-button>
      </div>
    </div>
  </el-card>
</template>

<script lang="ts">
import Vue from 'vue';
export default Vue.extend({
  name: 'ProductItem',
  props: {
    product: {
      type: Object,
      required: true,
    },
  },
});
</script>

<style lang="sass" scoped>

</style>
