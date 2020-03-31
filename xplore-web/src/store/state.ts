import cuid from 'cuid';

export const initProduct = () => ({
  cid: cuid(),
  brand: '',
  category: '',
  colour: '',
  region: '',
  size: '',
  description: {},
});

export interface RootState {
  product: {
    list: any[];
    newProduct?: any;
    loading: boolean;
  };
}

const state: RootState = {
  product: {
    list: [],
    newProduct: initProduct(),
    loading: false,
  },
};

export default state;

/*
{
   "brand":"Zara",
   "category":"Shoes",
   "colour":"Black",
   "region":"UK",
   "size":"14",
   "description":{
      "heel":"Slim",
      "kind":"Flat",
      "design":"Kinky"
   }
}
*/
