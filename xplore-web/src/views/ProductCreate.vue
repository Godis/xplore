<template>
    <div>
        <el-row :gutter="20">
          <el-col :span="23" :offset="1">
            <ValidationObserver tag="form" v-slot="{ untouched, invalid, errors }" @submit.prevent="submit">
                <div class="el-form-item">
                    <label>Category</label>
                    <select name="category" v-model="product.category">
                        <option
                            v-for="category in categories"
                            :key="category"
                        >{{ category }}</option>
                    </select>
                </div>
                <div class="el-form-item">
                    <label>Brand</label>
                    <ValidationProvider vid="brand" name="brand" rules="required">
                        <input v-model="product.brand" class="input" type="text" placeholder="Enter a brand">
                    </ValidationProvider>
                </div>
                <div class="el-form-item">
                    <label>Colour</label>
                    <input v-model="product.colour" class="input" type="text" list="colours">
                    <datalist id="colours">
                        <option
                            v-for="colour in colours"
                            :key="colour"
                        >{{ colour }}</option>
                    </datalist>
                </div>
                <div class="el-form-item">
                    <label>Region</label>
                    <select name="region" rules="required" v-model="product.region">
                        <option
                            v-for="region in regions"
                            :key="region"
                        >{{ region }}</option>
                    </select>
                </div>
                <div class="el-form-item">
                    <label>Size</label>
                    <input v-model="product.size" type="number" min="4" max="50" step="0.5" readonly>
                    <input name="size" v-model="product.size" type="range" min="4" max="50" step="0.5">
                </div>
                <div v-if="product.category == 'Shoes'" class="el-form-item">
                    <label>Heel</label>
                    <input v-model="product.description.heel" class="input" type="text">
                </div>
                <div v-if="product.category == 'Shoes'" class="el-form-item">
                    <label>Kind</label>
                    <input v-model="product.description.kind" class="input" type="text">
                </div>
                <div v-if="product.category == 'Shoes'" class="el-form-item">
                    <label>Design</label>
                    <input v-model="product.description.design" class="input" type="text">
                </div>
                <div class="el-form-item">
                    <button class="button is-primary" :disabled="untouched || invalid">Submit</button>
                </div>
                <div class="el-form-item">
                    <span id="error">{{ ...Object.values(errors).flat() }}</span>
                </div>
            </ValidationObserver>
          </el-col>
        </el-row>
    </div>
</template>

<script lang="ts">
import Vue from 'vue';
import axios from 'axios';
import { ValidationProvider, ValidationObserver, extend } from 'vee-validate';
import { required } from 'vee-validate/dist/rules';
import config from '../config';

extend('required', required);

Vue.component('ValidationObserver', ValidationObserver);
Vue.component('ValidationProvider', ValidationProvider);

export default Vue.extend({
  name: 'ProductCreate',
  data() {
    return {
        categories: ['Shoes', 'Clothes'],
        colours: ['Aquamarine', 'Blue', 'Brown', 'Black', 'Cyan', 'Green', 'Grey', 'Indigo', 'Lime', 'Magenta',
         'Maroon', 'Navy', 'Olive', 'Orange', 'Pink', 'Purple', 'Red', 'Silver', 'Tan', 'Teal', 'Turquoise', 'Violet',
         'White', 'Yellow'],
        regions: ['UK', 'US', 'EU'],
        product: {
            category: 'Shoes',
            brand: '',
            colour: '',
            region: '',
            size: '4',
            description: {},
        },
    };
  },
  methods: {
    submit() {
        const product = this.product;

        if (product.category !== 'Shoes') { product.description = {}; }

        alert(JSON.stringify(product));

        axios.post(`${config.apiBaseUrl}/products`, product)
            .then((_) => location.href = '/products');
    },
  },
});
</script>
