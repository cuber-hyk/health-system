<template>
  <div id="app">
    <div
      class="page"
      v-if="$store.state.user.user_id === 0 || url.indexOf($route.path) !== -1"
    >
      <router-view />
    </div>
    <el-container v-else>
      <el-aside width="auto">
        <page-side-vertical></page-side-vertical>
      </el-aside>

      <el-container style="height: 100vh; overflow: auto">
        <el-header style="height: auto; padding: 0px">
          <page_header />
        </el-header>

        <page-tag></page-tag>

        <el-main style="padding: 0px">
          <router-view></router-view>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<style>
@import "/css/base.css";
@import "/css/theme.css";
@import "/css/diy.css";

.mr-1 {
  margin-right: 1rem;
}

.quill-editor {
  background: #ffffff;
}

.ql-editor {
  min-height: 25rem;
}

.editor {
  min-height: 500px;
}

.el-dialog {
  width: calc(100% - 1rem);
  max-width: 40rem;
}

.field_action {
  text-align: center;
}

.field_action .el-transfer {
  display: inline-block;
  text-align: left;
}

input::-webkit-outer-spin-button,
input::-webkit-inner-spin-button {
  -webkit-appearance: none;
}

input[type="number"] {
  &::-webkit-outer-spin-button,
  &::-webkit-inner-spin-button {
    -webkit-appearance: none;
  }

  -moz-appearance: textfield;
}

/* @media (min-width: 576px) {s */
/* ::-webkit-scrollbar-track-piece {
			background-color: #f8f8f8;
		} */

/* 滚动条的宽度 */
/* ::-webkit-scrollbar {
			width: .5rem;
			height: .5rem;
		} */

/* 滚动条的设置 */
/* ::-webkit-scrollbar-thumb {
			background-color: rgba(0, 0, 0, 0.5);
			background-clip: padding-box;
			min-height: 1rem;
			border-radius: 0.5rem;
		} */
/* } */

.avatar-uploader .el-upload-dragger {
  /* width: 178px; */
  width: 400px;
}

table a {
  color: inherit;
  text-decoration: none;
}

.el-input {
  max-width: 22.625rem;
  line-height: 35px !important;
}

body {
  padding: 0;
  margin: 0;
  background-color: #efeff4;
}

main {
  margin: 0 auto;
  overflow-x: hidden;
  width: 100%;
}

header i {
  font-size: 1.75rem;
}

article {
  box-sizing: border-box;
  height: calc(100vh - 3.375rem);
  padding: 0.5rem;
  overflow-y: auto;
}

button i::before {
  font-weight: bold;
}

.el-breadcrumb {
  padding: 0.5rem;
}

.page {
  display: flex;
  align-items: stretch;
}

.btn_full {
  width: 100%;
}

.el-table .cell {
  white-space: nowrap;
}

.ql-editor {
  height: 300px;
}

.diy_panel .el-radio {
  padding: 9px 15px 9px 15px;
}

.diy_panel .el-checkbox {
  padding: 9px 15px 9px 15px;
}

.bg {
  background: white;
}

.form.p_4 {
  padding: 1rem;
}

.form .el-input {
  width: initial;
}

.mt {
  margin-top: 1rem;
}

.mr-1 {
  margin-right: 1rem;
}

.el-table .table_class {
  background: rgba(150, 150, 150, 0.1);
  text-align: center;
}

.text_center {
  text-align: center;
}

.warp {
  width: 100%;
}

.el-col {
  overflow: hidden;
}

.ql-picker {
  height: initial !important;
}

.el-form-item__label {
  font-size: 14px;
}
</style>

<script>
export default {
  data() {
    return {
      url: ["/login", "/forgot", "/register"],
    };
  },
  methods: {
    init() {
      var token = $.db.get("token");
      if (token) {
        this.$store.commit("user_set", {
          token,
        });
      }
    },
  },
  created() {
    this.init();
  },
};
</script>
