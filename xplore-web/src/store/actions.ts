import { ActionTree } from 'vuex';
import axios from 'axios';
import config from '../config';
import { RootState, Product, initProduct, asStringObject } from './state';

export const actions: ActionTree<RootState, any> = {
  async fetchProducts({ commit }) {

    const res = await axios.get(`${config.apiBaseUrl}/products`);

    const products = res.data.map((p: Product) => {
      return {
        ...initProduct(),
        ...p,
      };
    });

    commit('fetchProductsSuccess', products);
  },
  async updateProductByCid({ commit, state }, cid) {

    let product = state.product.list.find((p) => p.cid === cid);

    let stringyProduct: any = asStringObject(product);

    const res = await axios.put(`${config.apiBaseUrl}/products/${product.id}`, stringyProduct);
  },
  async cloneProductByCid({ commit, state }, cid) {

    let product = state.product.list.find((p) => p.cid === cid);

    let stringyProduct: any = asStringObject(product);

    const res = await axios.post(`${config.apiBaseUrl}/products`, stringyProduct);
  },
  async deleteProductByCid({ commit, state }, cid) {

    let product = state.product.list.find((p) => p.cid === cid);

    const res = await axios.delete(`${config.apiBaseUrl}/products/${product.id}`);
    
    commit('deleteProductSuccess', cid);
  },
};

export default actions;

// Create -> add a temporary blank row
// Save -> Send changes to server
// Modify -> edit existing row
// Delete -> Delete a row
