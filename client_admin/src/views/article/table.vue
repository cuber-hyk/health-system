<template>
  <el-main class="bg table_wrap">
    <el-form
      label-position="right"
      :model="query"
      class="form p_4"
      size="small"
      :inline="true"
      label-width="40px"
      style="padding: 0"
    >
      <el-form-item label="标题">
        <el-input v-model="query.title"></el-input>
      </el-form-item>

      <el-form-item label="标签">
        <el-input v-model="query.tag"></el-input>
      </el-form-item>

      <el-form-item label="分类">
        <el-select v-model="query.type">
          <el-option
            v-for="(o, i) in list_article_type"
            :value="o.name"
            :key="i"
            :label="o.name"
          ></el-option>
        </el-select>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="search()" class="search_btn_find"
          >查询</el-button
        >
        <el-button @click="reset()" class="search_btn_reset">重置</el-button>
        <el-button
          v-if="
            user_group == '管理员' || $check_action('/article/table', 'del')
          "
          class="search_btn_del"
          type="danger"
          :disabled="multiple"
          @click="delInfo()"
          >删除</el-button
        >

        <el-button
          v-if="
            user_group == '管理员' || $check_action('/article/table', 'set')
          "
          :disabled="single"
          class="search_btn_set"
          type="success"
          @click="handleUpdate"
          >修改</el-button
        >
        <router-link
          v-if="user_group == '管理员' || $check_action('/article/view')"
          to="./view?"
          ><el-button
            class="el-button el-button--default el-button--primary search_btn_add"
            >添加</el-button
          >
        </router-link>
      </el-form-item>
      <!-- <el-col :xs="24" :sm="24" :lg="6" class="el_form_search_wrap">
          <el-form-item label="标题" label-width="68px">
            <el-input v-model="query.title"></el-input>
          </el-form-item>
        </el-col> -->

      <!-- <el-col :xs="24" :sm="24" :lg="6" class="el_form_search_wrap">
          <el-form-item label="标签">
            <el-input v-model="query.tag"></el-input>
          </el-form-item>
        </el-col> -->

      <!-- <el-col :xs="24" :sm="24" :lg="6" class="el_form_search_wrap">
          <el-form-item label="分类">
            <el-select v-model="query.type">
              <el-option
                v-for="(o, i) in list_article_type"
                :value="o.name"
                :key="i"
                :label="o.name"
              ></el-option>
            </el-select>
          </el-form-item>
        </el-col> -->

      <el-col :xs="24" :sm="24" :lg="6" class="search_btn_wrap">
        <!-- <el-col :xs="24" :sm="24" :lg="24" class="search_btn_1">
            <el-form-item>
              <el-button
                type="primary"
                @click="search()"
                class="search_btn_find"
                >查询</el-button
              >
              <el-button @click="reset()" class="search_btn_reset"
                >重置</el-button
              >
              <el-button
                v-if="
                  user_group == '管理员' ||
                  $check_action('/article/table', 'del')
                "
                class="search_btn_del"
                type="danger"
                @click="delInfo()"
                >删除</el-button
              >
              <router-link
                v-if="user_group == '管理员' || $check_action('/article/view')"
                to="./view?"
                ><el-button
                  class="el-button el-button--default el-button--primary search_btn_add"
                  >添加</el-button
                >
              </router-link>
            </el-form-item>
          </el-col> -->

        <!-- <el-col :xs="24" :sm="12" :lg="12" class="search_btn_2">
						<el-form-item>
						</el-form-item>
					</el-col> -->
      </el-col>
    </el-form>
    <el-table
      :data="list"
      @selection-change="selectionChange"
      @sort-change="$sortChange"
      style="width: 100%"
      max-height="560"
      tooltip-effect="dark"
      stripe
    >
      <el-table-column fixed type="selection" width="50" align="center">
      </el-table-column>

      <el-table-column
        sortable
        prop="title"
        label="标题"
        width="250"
        align="center"
      >
      </el-table-column>

      <el-table-column prop="img" label="封面图" min-width="110" align="center">
        <template slot-scope="scope">
          <el-image
            style="width: 100px; height: 100px"
            :src="$fullUrl(scope.row.img)"
            :preview-src-list="[$fullUrl(scope.row.img)]"
          >
            <div slot="error" class="image-slot">
              <img src="/img/error.png" style="width: 90px; height: 90px" />
            </div>
          </el-image>
        </template>
      </el-table-column>

      <el-table-column
        sortable
        prop="type"
        label="文章分类"
        min-width="120"
        align="center"
      >
      </el-table-column>

      <el-table-column
        sortable
        prop="hits"
        label="点击数"
        min-width="100"
        align="center"
      >
      </el-table-column>

      <el-table-column
        prop="source"
        label="来源"
        min-width="180"
        align="center"
      >
        <template slot-scope="scope">
          <a :href="scope.row.url">
            {{ scope.row.source }}
          </a>
        </template>
      </el-table-column>

      <el-table-column prop="tag" label="标签" min-width="180" align="center">
      </el-table-column>

      <el-table-column
        sortable
        prop="create_time"
        label="创建时间"
        min-width="200"
        align="center"
      >
        <template slot-scope="scope">
          {{ $toTime(scope.row["create_time"], "yyyy-MM-dd hh:mm:ss") }}
        </template>
      </el-table-column>

      <el-table-column
        sortable
        prop="update_time"
        label="更新时间"
        min-width="200"
        align="center"
      >
        <template slot-scope="scope">
          {{ $toTime(scope.row["update_time"], "yyyy-MM-dd hh:mm:ss") }}
        </template>
      </el-table-column>

      <!-- 操作 -->
      <el-table-column fixed="right" label="操作" width="160" align="center">
        <template slot-scope="scope">
          <router-link
            v-if="
              user_group == '管理员' || $check_action('/article/table', 'set')
            "
            class="el-button el-button--small is-plain el-button--primary"
            :to="'./article/view?' + field + '=' + scope.row[field]"
            size="small"
          >
            详情
          </router-link>
          <router-link
            v-if="
              user_group == '管理员' || $check_action('/article/table', 'set')
            "
            class="el-button el-button--small is-plain el-button--primary"
            :to="
              '../comment/table?size=10&page=1&source_table=article&source_field=' +
              field +
              '&source_id=' +
              scope.row[field]
            "
            size="small"
          >
            查看评论
          </router-link>
          <p v-else>当前用户无权限</p>
        </template>
      </el-table-column>
      <!-- /操作 -->
    </el-table>

    <!-- 分页器 -->
    <div class="mt text_center">
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="query.page"
        :page-sizes="[4, 8, 16, 100]"
        :page-size="query.size"
        layout="total, sizes, prev, pager, next, jumper"
        :total="count"
      >
      </el-pagination>
    </div>
    <!-- /分页器 -->
  </el-main>
</template>

<script>
import mixin from "@/mixins/page.js";

export default {
  mixins: [mixin],
  data() {
    return {
      // 获取数据地址
      url_get_list: "~/api/article/get_list?like=0",
      url_del: "~/api/article/del?",

      // 字段ID
      field: "article_id",

      // 详情路径
      viewPath: "/article/view",

      // 查询
      query: {
        size: 4,
        page: 1,
        title: "",
        tag: "",
        type: "",
      },

      // 数据
      list: [],

      // 文章类型
      list_article_type: [],

      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
    };
  },
  methods: {
    deleteRow(index, rows) {
      rows.splice(index, 1);
    },

    table_class({ row, column, rowIndex, columnIndex }) {
      return "table_class";
    },

    /**
     * 获取文章分类
     */
    async get_article_type() {
      var json = await this.$get("~/api/article_type/get_list?");

      // console.log(json.result);
      if (json.result) {
        this.list_article_type = json.result.list;
      } else if (json.error) {
        console.error(json.error);
      }
    },
  },
  created() {
    this.get_article_type();
  },
};
</script>

<style type="text/css">
</style>
