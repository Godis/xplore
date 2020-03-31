<template>
  <div id="app">
    <el-container>
      <el-header>
          <el-menu class="el-menu-demo" mode="horizontal" width="100%" :router="true">
            <el-menu-item index="/">Home</el-menu-item>
            <el-menu-item index="/products">View Products</el-menu-item>
          </el-menu>
      </el-header>
      <el-main>
        <router-view/>
      </el-main>
    </el-container>
  </div>
</template>

<style lang="scss">
body {
  margin: 0;
}
img {
  max-width: 100%;
  height: auto;
}
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

</style>
