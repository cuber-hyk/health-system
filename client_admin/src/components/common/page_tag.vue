<template>
  <div class="tags-wrap">
    <el-tag
      v-for="(item, index) in tags"
      :key="item.label"
      :closable="item.name !== 'index'"
      :effect="$route.name === item.name ? 'dark' : 'plain'"
      @click="changeMenu(item)"
      @close="handleClose(item, index)"
      size="small"
    >
      {{ item.label }}
    </el-tag>
  </div>
</template>

<script>
import { mapState } from "vuex";

export default {
  name: "pageTag",
  data() {
    return {};
  },
  computed: {
    // 获取tabsList 返回对象 对tags就行...解构
    ...mapState({
      tags: (state) => (state.tag ? state.tag.tabsList : []),
    }),
  },
  methods: {
    // 点击tag跳转的功能
    changeMenu(item) {
      this.$router.push({ name: item.name });
    },

    // 点击tag删除的功能
    handleClose(item, index) {
      const len = this.tags.length;
      this.$store.commit("closeTag", item);

      // 删除之后的跳转逻辑
      if (item.name !== this.$route.name) {
        return;
      }

      // 删除最后一项
      if (index === len - 1) {
        this.$router.push({ name: this.tags[index - 1].name });
        console.log(this.tags[index - 1].name);
      } else {
        this.$router.push({ name: this.tags[index].name });
      }
    },
  },
};
</script>

<style lang="less" scoped>
.tags-wrap {
  padding-left: 10px;
  .el-tag {
    margin: 5px;
    cursor: pointer;
  }
}
</style>