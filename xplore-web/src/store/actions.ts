import { ActionTree } from 'vuex';
import axios from 'axios';
import config from '../config';
import { RootState, initProduct } from './state';

export const actions: ActionTree<RootState, any> = {
  async fetchProducts({ commit }) {

    const res = await axios.get(`${config.apiBaseUrl}/products`);

    const products = res.data.map(product => {
      return {
        ...initProduct(),
        ...product,
      };
    });

    commit('fetchProductsSuccess', products);
  },
  async updateProductByCid({ commit, state }, cid) {

    let product = state.product.list.find((product) => product.cid === cid);

    let stringyProduct: any = {}
    Object.entries(product).forEach(([key, val]) => {
      if (key == 'size') {
        stringyProduct[key] = val.toString()
      } else {
        stringyProduct[key] = val
      }
    });

    const res = await axios.put(`${config.apiBaseUrl}/products/${product.id}`, stringyProduct);    

    console.log(res)
  },
  async cloneProductByCid({ commit, state }, cid) {
    
    let product = state.product.list.find((product) => product.cid === cid);

    let stringyProduct: any = {}
    Object.entries(product).forEach(([key, val]) => {
      if (key == 'size') {
        stringyProduct[key] = val.toString()
      } else {
        stringyProduct[key] = val
      }
    });
    
    const res = await axios.post(`${config.apiBaseUrl}/products`, stringyProduct);
    
    console.log(res)
  },
  async deleteProductByCid({ commit, state }, cid) {

    let product = state.product.list.find((product) => product.cid === cid);

    const res = await axios.delete(`${config.apiBaseUrl}/products/${product.id}`);
    
    commit('deleteProductSuccess', cid)
  },
};

export default actions;

// Create -> add a temporary blank row
// Save -> Send changes to server
// Modify -> edit existing row
// Delete -> Delete a row
