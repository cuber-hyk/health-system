import Vue from "vue";
import VueRouter from "vue-router";
import index from "../views/index.vue";
import login from "../views/login.vue";
import forgot from "../views/forgot.vue";
Vue.use(VueRouter);

const routes = [
  // 主页
  {
    path: "/",
    name: "index",
    component: index,
    meta: {
      index: 0,
      title: "首页",
    },
  },

  // 登录
  {
    path: "/login",
    name: "login",
    component: login,
    meta: {
      index: 0,
      title: "登录",
    },
  },

  // 忘记密码
  {
    path: "/forgot",
    name: "forgot",
    component: forgot,
    meta: {
      index: 0,
      title: "忘记密码",
    },
  },

  // 修改密码
  {
    path: "/user/password",
    name: "password",
    component: () => import("../views/user/password.vue"),
    meta: {
      index: 0,
      title: "修改密码",
    },
  },

  // 视频播放页
  {
    path: "/media/video",
    name: "video",
    component: () => import("../views/media/video.vue"),
    meta: {
      index: 0,
      title: "视频",
    },
  },

  // 音频播放页
  {
    path: "/media/audio",
    name: "audio",
    component: () => import("../views/media/audio.vue"),
    meta: {
      index: 0,
      title: "音频",
    },
  },

  // 友情链接路由
  // {
  //   path: "/link/table",
  //   name: "link_table",
  //   component: () => import("../views/link/table.vue"),
  //   meta: {
  //     index: 0,
  //     title: "链接列表",
  //     activeMenu: "/link/table",
  //   },
  // },
  // {
  //   path: "/link/view",
  //   name: "link_view",
  //   component: () => import("../views/link/view.vue"),
  //   meta: {
  //     index: 0,
  //     title: "链接详情",
  //   },
  // },

  // 轮播图路由
  {
    path: "/slides/table",
    name: "slides_table",
    component: () => import("../views/slides/table.vue"),
    meta: {
      index: 0,
      title: "轮播图列表",
      activeMenu: "/slides/table",
    },
  },
  {
    path: "/slides/view",
    name: "slides_view",
    component: () => import("../views/slides/view.vue"),
    meta: {
      index: 0,
      title: "轮播图详情",
    },
  },
  // 文章路由
  {
    path: "/article/table",
    name: "article_table",
    component: () => import("../views/article/table.vue"),
    meta: {
      index: 0,
      title: "文章列表",
      activeMenu: "/article/table",
    },
  },
  {
    path: "/article/view",
    name: "article_view",
    component: () => import("../views/article/view.vue"),
    meta: {
      index: 0,
      title: "文章详情",
    },
  },

  // 文章分类路由
  {
    path: "/article_type/table",
    name: "article_type_table",
    component: () => import("../views/article_type/table.vue"),
    meta: {
      index: 0,
      title: "文章分类列表",
    },
  },
  {
    path: "/article_type/view",
    name: "article_type_view",
    component: () => import("../views/article_type/view.vue"),
    meta: {
      index: 0,
      title: "文章分类详情",
    },
  },

  // 广告路由
  // {
  // 	path: '/ad/table',
  // 	name: 'ad_table',
  // 	component: () => import('../views/ad/table.vue'),
  // 	meta: {
  // 		index: 0,
  // 		title: '广告列表'
  // 	}
  // },
  // {
  // 	path: '/ad/view',
  // 	name: 'ad_view',
  // 	component: () => import('../views/ad/view.vue'),
  // 	meta: {
  // 		index: 0,
  // 		title: '广告详情'
  // 	}
  // },

  // 公告路由
  {
    path: "/notice/table",
    name: "notice_table",
    component: () => import("../views/notice/table.vue"),
    meta: {
      index: 0,
      title: "公告信息列表",
      activeMenu: "/notice/table",
    },
  },
  {
    path: "/notice/view",
    name: "notice_view",
    component: () => import("../views/notice/view.vue"),
    meta: {
      index: 0,
      title: "公告信息详情",
    },
  },

  // 评论路由
  {
    path: "/comment/table",
    name: "comment_table",
    component: () => import("../views/comment/table.vue"),
    meta: {
      index: 0,
      title: "评论列表",
    },
  },
  {
    path: "/comment/view",
    name: "comment_view",
    component: () => import("../views/comment/view.vue"),
    meta: {
      index: 0,
      title: "评论详情",
    },
  },

  // 注册用户路由
  {
    path: "/registered_users/table",
    name: "registered_users_table",
    component: () => import("../views/registered_users/table.vue"),
    meta: {
      index: 0,
      title: "registered_users列表",
      activeMenu: "/registered_users/table",
    },
  },
  {
    path: "/registered_users/view",
    name: "registered_users_view",
    component: () => import("../views/registered_users/view.vue"),
    meta: {
      index: 0,
      title: "registered_users详情",
    },
  },
  // 食物分类路由
  {
    path: "/food_classification/table",
    name: "food_classification_table",
    component: () => import("../views/food_classification/table.vue"),
    meta: {
      index: 0,
      title: "food_classification列表",
    },
  },
  {
    path: "/food_classification/view",
    name: "food_classification_view",
    component: () => import("../views/food_classification/view.vue"),
    meta: {
      index: 0,
      title: "food_classification详情",
    },
  },
  // 食物信息路由
  {
    path: "/food_information/table",
    name: "food_information_table",
    component: () => import("../views/food_information/table.vue"),
    meta: {
      index: 0,
      title: "food_information列表",
      activeMenu: "/food_information/table",
    },
  },
  {
    path: "/food_information/view",
    name: "food_information_view",
    component: () => import("../views/food_information/view.vue"),
    meta: {
      index: 0,
      title: "food_information详情",
    },
  },
  // 食谱分类路由
  {
    path: "/recipe_classification/table",
    name: "recipe_classification_table",
    component: () => import("../views/recipe_classification/table.vue"),
    meta: {
      index: 0,
      title: "recipe_classification列表",
    },
  },
  {
    path: "/recipe_classification/view",
    name: "recipe_classification_view",
    component: () => import("../views/recipe_classification/view.vue"),
    meta: {
      index: 0,
      title: "recipe_classification详情",
    },
  },
  // 食谱信息路由
  {
    path: "/recipe_information/table",
    name: "recipe_information_table",
    component: () => import("../views/recipe_information/table.vue"),
    meta: {
      index: 0,
      title: "recipe_information列表",
    },
  },
  {
    path: "/recipe_information/view",
    name: "recipe_information_view",
    component: () => import("../views/recipe_information/view.vue"),
    meta: {
      index: 0,
      title: "recipe_information详情",
    },
  },

  // 用户路由
  {
    path: "/user/table",
    name: "user_table",
    component: () => import("../views/user/table.vue"),
    meta: {
      index: 0,
      title: "用户列表",
      activeMenu: "/user/table",
    },
  },
  {
    path: "/user/view",
    name: "user_view",
    component: () => import("../views/user/view.vue"),
    meta: {
      index: 0,
      title: "用户详情",
    },
  },
  {
    path: "/user/info",
    name: "user_info",
    component: () => import("../views/user/info.vue"),
    meta: {
      index: 0,
      title: "个人信息",
    },
  },
  // 用户组路由
  {
    path: "/user_group/table",
    name: "user_group_table",
    component: () => import("../views/user_group/table.vue"),
    meta: {
      index: 0,
      title: "用户组列表",
      activeMenu: "/user_group/table",
    },
  },
  {
    path: "/user_group/view",
    name: "user_group_view",
    component: () => import("../views/user_group/view.vue"),
    meta: {
      index: 0,
      title: "用户组详情",
    },
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

router.beforeEach((to, from, next) => {
  let token = to.query.token;
  if (token) {
    $.db.set("token", token, 120);
  }
  next();
});

router.afterEach((to, from, next) => {
  let title = "健康饮食推荐系统-admin";
  document.title = title;
});

export default router;