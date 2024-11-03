<template>
  <div class="list_menu">
    <router-link
      :to="o.url"
      class="menu_item"
      :class="{ selected: o.selected }"
      v-for="(o, i) in list_info"
      :key="i"
    >
      <span class="left_span">{{ o.title }}</span>
      <!-- <span class="right_span"><b-icon :icon="o.icon"></b-icon></span> -->
    </router-link>
  </div>
</template>

<script>
export default {
  props: {
    list_info: {
      type: Array,
      default() {
        return [
          {
            title: "资料管理",
            url: "/user/info",
            icon: "",
            selected: false,
          },
          {
            title: "密码管理",
            url: "/user/password",
            icon: "",
            selected: false,
          },
          {
            title: "地址管理",
            url: "/user/address",
            icon: "",
            selected: false,
          },
          {
            title: "收藏",
            url: "/user/collect",
            icon: "",
            selected: false,
          },
          {
            title: "订单管理",
            url: "/order/list",
            icon: "",
            selected: false,
          },
          {
            title: "购物车",
            url: "/cart/list",
            icon: "",
            selected: false,
          },
        ];
      },
    },
  },
  methods: {},
  components: {},
};
</script>

<style scoped>
.list_menu {
  flex: 0 0 25%;
  border-bottom: 1px solid #eee;
  display: flex;
}
.menu_item {
  display: flex;
  padding: 15px 63.5px;
  border: 1px solid #eee;
  border-bottom: none;
  background-color: #fff;
  max-width: 25%;
}
.menu_item.selected {
  background-color: #000;
}
.menu_item.selected span {
  color: #fff !important;
}
.content {
  flex: 0 0 74%;
}
</style>
