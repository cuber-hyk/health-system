<template>
  <el-menu
    :default-active="activeMenu"
    class="side el-menu-vertical-demo"
    @open="handleOpen"
    @close="handleClose"
    :collapse="isCollapsed"
    background-color="rgb(48, 65, 86)"
    text-color="#fff"
    active-text-color="#ffd04b"
    :router="true"
  >
    <el-menu-item index="/" @click="clickMenu('index', '首页')">
      <i class="el-icon-s-home"></i>
      <span slot="title">首页</span>
    </el-menu-item>

    <el-submenu
      index="web"
      v-show="
        user_group == '管理员' ||
        $check_group([
          '/nav/table',
          '/link/table',
          '/notice/table',
          '/slides/table',
          '/ad/table',
          '/message/table',
        ])
      "
    >
      <template slot="title"
        ><i class="el-icon-data-board"></i><span>网站管理</span></template
      >
      <!-- <el-menu-item v-show="user_group == '管理员' || $check_action('/auth/table')" index="/auth/table">
				<span>权限管理</span>
			</el-menu-item> -->
      <!-- <el-menu-item
        v-show="user_group == '管理员' || $check_action('/link/table')"
        index="/link/table"
        @click="clickMenu('link_table', '友情链接')"
      >
        <span>友情链接</span>
      </el-menu-item> -->
      <el-menu-item
        v-show="user_group == '管理员' || $check_action('/slides/table')"
        index="/slides/table"
        @click="clickMenu('slides_table', '轮播图')"
      >
        <span>轮播图</span>
      </el-menu-item>
      <!-- <el-menu-item v-show="user_group == '管理员' || $check_action('/ad/table')" index="/ad/table">
				<span>广告</span>
			</el-menu-item> -->
      <el-menu-item
        v-show="user_group == '管理员' || $check_action('/notice/table')"
        index="/notice/table"
        @click="clickMenu('notice_table', '公告信息')"
      >
        <span>公告信息</span>
      </el-menu-item>
    </el-submenu>

    <el-submenu
      index="user"
      v-show="
        user_group == '管理员' ||
        $check_group([
          '/user/table',
          '/user_group/table',
          '/registered_users/table',
        ])
      "
    >
      <template slot="title"
        ><i class="el-icon-user-solid"></i><span>人员管理</span></template
      >
      <el-menu-item
        index="/user/table"
        v-show="user_group == '管理员'"
        @click="clickMenu('user_table', '管理员')"
        ><span>管理员</span></el-menu-item
      >
      <el-menu-item
        index="/registered_users/table"
        v-show="
          user_group == '管理员' || $check_action('/registered_users/table')
        "
        @click="clickMenu('registered_users_table', '注册用户')"
        ><span>注册用户</span></el-menu-item
      >
      <el-menu-item
        index="/user_group/table"
        @click="clickMenu('user_group_table', '用户组')"
        ><span>用户组</span></el-menu-item
      >
    </el-submenu>

    <el-menu-item
      index="/food_information/table"
      v-show="user_group == '管理员'"
      @click="clickMenu('food_information_table', '食品信息')"
    >
      <i class="el-icon-potato-strips"></i>
      <span>食品信息</span>
    </el-menu-item>

    <el-submenu
      index="content"
      v-show="
        $check_group(['/article/table', '/article_type/table', '/exam/table'])
      "
    >
      <template slot="title"
        ><i class="el-icon-chat-line-round"></i><span>内容管理</span></template
      >
      <el-menu-item
        v-show="user_group == '管理员' || $check_action('/article/table')"
        index="/article/table"
        @click="clickMenu('article_table', '文章')"
      >
        <!-- <span>文章</span> -->
        <span>{{ $page_title("/article/table") || "文章" }}</span>
      </el-menu-item>
      <el-menu-item
        v-show="user_group == '管理员' || $check_action('/article_type/table')"
        index="/article_type/table"
        @click="clickMenu('article_type_table', '文章分类')"
      >
        <!-- <span>文章类型</span> -->
        <span>{{ $page_title("/article_type/table") || "文章分类" }}</span>
      </el-menu-item>
    </el-submenu>

    <!-- <el-submenu index="more" v-show="auth.length">
      <template slot="title"
        ><i class="el-icon-copy-document"></i><span>模块管理</span></template
      >
      <el-menu-item
        v-for="(o, idx) in auth"
        v-show="$check_action(o.path)"
        :index="o.path"
      >
        <span>{{ o.page_title }}</span>
      </el-menu-item>
    </el-submenu> -->
  </el-menu>
</template>

<script>
export default {
  name: "pageSideVertical",
  data: function () {
    return {
      user_group: this.$store.state.user.user_group,
      user: this.$store.state.user,
    };
  },
  methods: {
    handleOpen(key, keyPath) {},

    handleClose(key, keyPath) {},
    handleCommand(command) {
      if (command === "/quit") {
        this.$get("~/api/user/quit", {}, (res) => {
          this.$store.commit("quit");
          this.$router.push("/login");
        });
      } else if (command.indexOf("http") !== -1) {
        let token = $.db.get("token");
        if (token) {
          location.href = command + "?token=" + token;
        }
      } else {
        this.$router.push(command);
      }
    },
    // 更新tag
    clickMenu(name, label) {
      // 创建 item 对象并存储 label 和 name 属性
      const item = {
        label: label,
        name: name,
      };

      this.$store.commit("updateMenu", item);
    },
  },
  computed: {
    // 当前菜单激活
    activeMenu() {
      const route = this.$route;
      const { meta, path } = route;
      if (meta.activeMenu) {
        return meta.activeMenu;
      }
      return path;
    },
    auth() {
      var tables = this.$store.state.web.auth;
      var lt = [];
      for (var i = 0; i < tables.length; i++) {
        var o = tables[i];
        if (o.path.indexOf("/table") !== -1) {
          let list = ["exam", "article", "article_type", , "comment", "notice"];
          list.push("registered_users");
          if (list.indexOf(o.table_name) === -1) {
            lt.push(o);
          }
        }
      }
      return lt;
    },
    isCollapsed() {
      return this.$store.state.tag.isCollapse;
    },
  },
};
</script>

<style scoped="scoped">
.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 200px;
  min-height: 400px;
}
.el-menu {
  height: 100vh;
  border-right: none;
}
</style>
