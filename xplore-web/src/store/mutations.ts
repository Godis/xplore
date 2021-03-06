import { MutationTree } from 'vuex';
import { RootState } from './state';

const mutations: MutationTree<RootState> = {
  fetchProductsSuccess(state, payload) {
    state.product.list = payload;
  },
  updateProductSuccess(state, {cid, payload}) {
    mutations.deleteProductSuccess(state, cid);
    mutations.cloneProductSuccess(state, payload);
  },
  deleteProductSuccess(state, cid) {
    state.product.list = state.product.list.filter((product) => product.cid !== cid);
  },
  cloneProductSuccess(state, payload) {
    state.product.list.push(payload);
  },
};

export default mutations;
