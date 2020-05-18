module.exports = {
    publicPath: process.env.NODE_ENV === 'production' ? '/xplore/' : '/',
    configureWebpack: {
        devtool: 'source-map'
    }
};
