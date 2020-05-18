import cuid from 'cuid';

export function asStringObject(obj: {}) {

    const stringyObject: any = {};

    const entries: Array<[string, any]> = Object.entries(obj);

    entries.forEach(([key, val]) => {
      if (key === 'size') {
        stringyObject[key] = val.toString();
      } else {
        stringyObject[key] = val;
      }
    });

    return stringyObject;
}

export const initProduct = () => ({
  cid: cuid(),
  brand: '',
  category: '',
  colour: '',
  region: '',
  size: '',
  description: {},
});

export interface Product {
  id: string;
  cid: string;
  category: string;
  brand: string;
  colour: string;
  region: string;
  size: number;
  description: {};
}

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
