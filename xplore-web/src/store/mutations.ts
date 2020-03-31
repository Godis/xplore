import { MutationTree } from 'vuex';
import { RootState } from './state';

const mutations: MutationTree<RootState> = {
  fetchProductsSuccess(state, payload) {
    state.product.list = payload;
  },
  deleteProductSuccess(state, cid) {
    state.product.list = state.product.list.filter((product) => product.cid !== cid);
  },
};

export default mutations;
