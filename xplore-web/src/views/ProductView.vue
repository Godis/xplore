<template>
  <div>
    <el-row :gutter="20">
      <el-col :span="20" :offset="2">
        <el-table :data="product.list" stripe border style="width: 100%">
          <el-table-column label="Category">
            <template slot-scope="scope">
              <el-select @change="onUpdateByCid(scope.row.cid)" v-model="scope.row.category">
                <el-option v-for="category in categories" :key="category" :label="category" :value="category">
                </el-option>
              </el-select>
            </template>
          </el-table-column>
          <el-table-column label="Brand">
            <template slot-scope="scope">
              <el-input @change="onUpdateByCid(scope.row.cid)" v-model="scope.row.brand"></el-input>
            </template>             
          </el-table-column>
          <el-table-column label="Colour">
            <template slot-scope="scope">
              <el-select @change="onUpdateByCid(scope.row.cid)" v-model="scope.row.colour">
                <el-option v-for="colour in colours" :key="colour" :label="colour" :value="colour">
                </el-option>
              </el-select>
            </template>             
          </el-table-column>
          <el-table-column label="Size">
            <template slot-scope="scope">
              <el-select @change="onUpdateByCid(scope.row.cid)" v-model="scope.row.region" style="width: 40%; margin-right: 10%">
                <el-option v-for="region in regions" :key="region" :label="region" :value="region">
                </el-option>
              </el-select>
              <el-input @change="onUpdateByCid(scope.row.cid)" v-model="scope.row.size" type="number" min="4" max="50" step="0.5" style="width: 40%"></el-input>
            </template>             
          </el-table-column>
           <el-table-column label="Size">
            <template slot-scope="scope">

            </template>
          </el-table-column>
          <el-table-column label="Actions" width="120">
            <template slot-scope="scope">
              <el-button @click="onCloneByCid(scope.row.cid)" type="text" size="small">Clone</el-button>
              <el-button @click="onDeleteByCid(scope.row.cid)" type="text" size="small">Delete</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-col>
    </el-row>
  </div>
</template>

<script lang="ts">
import Vue from 'vue';
import { mapActions, mapState } from 'vuex';
import axios from 'axios';

import config from '../config';

export default Vue.extend({
  name: 'ProductView',
  data() {
    return {
        categories: ['Shoes', 'Clothes'],
        colours: ['Aquamarine', 'Blue', 'Brown', 'Black', 'Cyan', 'Green', 'Grey', 'Indigo', 'Lime', 'Magenta',
         'Maroon', 'Navy', 'Olive', 'Orange', 'Pink', 'Purple', 'Red', 'Silver', 'Tan', 'Teal', 'Turquoise', 'Violet',
         'White', 'Yellow'],
        regions: ['UK', 'US', 'EU'],
    };
  },
  computed: {
    ...mapState([
      'product',
    ]),
  },
  methods: {
    ...mapActions([
      'fetchProducts', 'updateProductByCid', 'cloneProductByCid', 'deleteProductByCid',
    ]),
    create() {
      // this.$router.push('/products/create');
    },
    onUpdateByCid(cid: string) {
      this.updateProductByCid(cid);
    },
    async onCloneByCid(cid: string) {
      await this.cloneProductByCid(cid);
      this.fetchProducts();
    },
    onDeleteByCid(cid: string) {
      this.deleteProductByCid(cid);
    },
  },
  mounted() {
    this.fetchProducts();
  },
});
</script>
