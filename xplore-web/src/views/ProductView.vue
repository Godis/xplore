<template>
  <div>
    <el-row :gutter="20">
      <el-col :span="20" :offset="2">
        <el-table :data="product.list" stripe border style="width: 100%">
          <el-table-column label="Category">
            <template slot-scope="scope">
              <el-select :value="scope.row.category" @change="onUpdateGenericallyByCid(scope.row.cid, 'category', $event)">
                <el-option v-for="category in categories" :key="category" :label="category" :value="category">
                </el-option>
              </el-select>
            </template>
          </el-table-column>
          <el-table-column label="Brand">
            <template slot-scope="scope">
              <el-input :value="scope.row.brand" @input="onUpdateGenericallyByCid(scope.row.cid, 'brand', $event)">
              </el-input>
            </template>
          </el-table-column>
          <el-table-column label="Colour">
            <template slot-scope="scope">
              <el-select :value="scope.row.colour" @change="onUpdateGenericallyByCid(scope.row.cid, 'colour', $event)">
                <el-option v-for="colour in colours" :key="colour" :label="colour" :value="colour">
                </el-option>
              </el-select>
            </template>
          </el-table-column>
          <el-table-column label="Size">
            <template slot-scope="scope">
              <el-select :value="scope.row.region" @change="onUpdateGenericallyByCid(scope.row.cid, 'region', $event)" style="width: 40%; margin-right: 10%">
                <el-option v-for="region in regions" :key="region" :label="region" :value="region">
                </el-option>
              </el-select>
              <el-input :value="scope.row.size" @input="onUpdateGenericallyByCid(scope.row.cid, 'size', $event)" type="number" min="4" max="50" step="0.5" style="width: 40%"></el-input>
            </template>
          </el-table-column>
          <el-table-column label="Description" width="501px">
            <template slot-scope="scope">
              <el-input
                v-for="(description, index) in scope.row.description"
                :value="description"
                @input="onUpdateOfArrayGenericallyByCid(scope.row.cid, 'description', $event, scope.row.description, index)"
                :key="index"
                type="text"
                style="width: 140px; margin-left: 5px; margin-right: 5px"
              />
              <el-button @click="onAddDescriptionByCid(scope.row.cid)" type="text" size="small">Add</el-button>
            </template>
          </el-table-column>
          <el-table-column label="Actions" width="120px">
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
      'fetchProducts', 'updateProductByCid', 'updateProductGenericallyByCid',
      'cloneProductByCid', 'deleteProductByCid', 'addDescriptionByCid',
    ]),
    onUpdateOfArrayGenericallyByCid(cid: string, field: string, newValue: string, original: string[], index: number) {
      newValue === '' ? original.splice(index, 1) : original[index] = newValue;
      this.updateProductGenericallyByCid({cid, field, newValue: original});
    },
    onUpdateGenericallyByCid(cid: string, field: string, newValue: string) {
      this.updateProductGenericallyByCid({cid, field, newValue});
    },
    async onCloneByCid(cid: string) {
      await this.cloneProductByCid(cid);
    },
    onDeleteByCid(cid: string) {
      this.deleteProductByCid(cid);
    },
    onAddDescriptionByCid(cid: string) {
      this.addDescriptionByCid(cid);
    },
  },
  mounted() {
    this.fetchProducts();
  },
});
</script>
