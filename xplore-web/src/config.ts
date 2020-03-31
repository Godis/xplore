const isProduction = process.env.NODE_ENV === 'production';
export default {
  isProduction,
  apiBaseUrl: process.env.VUE_APP_API_BASE_URL,
};
