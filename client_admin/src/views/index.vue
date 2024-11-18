<template>
  <div class="page_root" id="root_index">
    <div class="warp">
      <div class="container-fluid">
        <el-row>
          <el-col :span="12">
            <profile></profile>
            <el-card style="margin-top: 20px; height: 360px">
              <el-table :data="list_user" style="width: 100%" border>
                <el-table-column prop="username" label="用户名" align="center">
                </el-table-column>

                <el-table-column
                  prop="user_group"
                  label="用户组"
                  align="center"
                >
                  <template slot-scope="scope">
                    {{ scope.row.user_group }}
                  </template>
                </el-table-column>

                <el-table-column prop="phone" label="手机号码" align="center">
                </el-table-column>

                <el-table-column
                  prop="phone_state"
                  label="手机认证"
                  align="center"
                >
                  <template slot-scope="scope">
                    <span v-if="scope.row.phone_state === 0">未认证</span>
                    <span v-if="scope.row.phone_state === 1">认证中</span>
                    <span v-if="scope.row.phone_state === 2">已认证</span>
                    <!--					{{list_is_state.getVal("text",{"value":scope.row.phone_state})}}-->
                  </template>
                </el-table-column>

                <el-table-column
                  prop="login_time"
                  label="上次登录时间"
                  align="center"
                >
                  <template slot-scope="scope">
                    {{
                      $toTime(scope.row["login_time"], "yyyy-MM-dd hh:mm:ss")
                    }}
                  </template>
                </el-table-column>

                <el-table-column
                  prop="create_time"
                  label="创建时间"
                  align="center"
                >
                  <template slot-scope="scope">
                    {{
                      $toTime(scope.row["create_time"], "yyyy-MM-dd hh:mm:ss")
                    }}
                  </template>
                </el-table-column>
              </el-table>
            </el-card>
          </el-col>
          <el-col :span="12">
            <el-row>
              <el-col :span="12">
                <mm_label
                  bg_color="bg_purple"
                  icon="el-icon-user-solid"
                  :url="url_user_count"
                  unit="人"
                  title="用户总量"
                ></mm_label>
              </el-col>
              <el-col :span="12">
                <mm_label
                  bg_color="bg_green"
                  icon="el-icon-view"
                  :url="url_article_hits"
                  unit="次"
                  title="文章浏览总量"
                ></mm_label>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="24">
                <pie-chart
                  class="center-chart"
                  title="食物种类数量占比饼状图"
                  :list="pieData"
                  radius="70%"
                  type="类别占比"
                ></pie-chart>
              </el-col>
            </el-row>
          </el-col>
        </el-row>
      </div>
    </div>
  </div>
</template>
<script>
import mixin from "@/mixins/page.js";
import barChart from "@/components/charts/bar_chart";
import newBarChart from "@/components/charts/new_bar_chart";
import lineChart from "@/components/charts/line_chart";
import newLineChart from "@/components/charts/new_line_chart";
import mm_label from "@/components/mm_label.vue";
import articleTable from "./article/table.vue";
import pieChart from "../components/charts/pie_chart.vue";
import Line_chart from "../components/charts/line_chart.vue";
import Bar_chart from "../components/charts/bar_chart.vue";
import profile from "../views/user/profile.vue";
export default {
  mixins: [mixin],
  name: "Home",
  components: {
    pieChart,
    barChart,
    newBarChart,
    lineChart,
    newLineChart,
    mm_label,
    articleTable,
    Line_chart,
    Bar_chart,
    profile,
  },
  data() {
    return {
      recognitionType: "${model.filter.recognitionType}",
      activeName: "third",
      url_user_count: "~/api/user/count?",
      url_article_hits: "~/api/article/sum?field=hits",
      url_food_count: "~/api/food_information/count?",

      // 饼状图数据
      pieData: [],

      // 数据
      list_user: [],

      // 商品类别
      food_types: [],
    };
  },
  created() {
    this.get_list_user();
    this.init_pie_data();
  },
  mounted() {},
  methods: {
    async get_nickname(list, flag) {
      if (flag) {
        for (let i = 0; i < list.length; i++) {
          await this.$get(
            "~/api/user/get_obj?user_id=" + list[i],
            null,
            (json) => {
              if (json.result) {
                list[i] = json.result.obj.nickname;
              }
            }
          );
        }
      } else {
        for (let i = 0; i < list.length; i++) {
          await this.$get(
            "~/api/user/get_obj?user_id=" + list[i].name,
            null,
            (json) => {
              if (json.result) {
                list[i].name = json.result.obj.nickname;
              }
            }
          );
        }
      }
    },
    async get_list_user() {
      var json = await this.$get("~/api/user/get_list?");
      if (json.result) {
        this.list_user = json.result.list;
      } else if (json.error) {
        console.error(json.error);
      }
    },
    async init_pie_data() {
      // 先获取食物类别
      var json = await this.$get("~/api/food_classification/get_list?");
      if (json.result) {
        this.food_types = json.result.list;

        // 遍历food_types每个类别, 分别请求获取该类别的食品数量
        this.pieData = [];
        // 遍历 food_types 每个类别，分别请求获取该类别的食品数量
        this.food_types.forEach((type) => {
          this.get_food_type_num({
            food_classification: type.food_classification,
          });
        });
      } else if (json.error) {
        console.error(json.error);
      }
    },

    // 获取不同类别的食物数量
    // param:{food_classification: "大米"}
    get_food_type_num(param) {
      this.$get(this.url_food_count, param, (json) => {
        if (json.result || json.result === 0) {
          this.pieData.push({
            value: json.result,
            name: param.food_classification,
          });
          // console.log(this.pieData);
        }
      });
    },
  },
  computed: {
    recognitionHeight() {
      return "830px";
    },
    recognitionUrl() {
      return "https://www.faceplusplus.com.cn/${model.filter.recognitionType}/";
    },
  },
};
</script>

<style scoped="scoped">
thead tr {
  background-color: #ccc !important;
}
.chart {
  display: block;
  width: 100%;
  height: 400px;
  padding: 1rem;
  position: relative;
}

.el-col {
  padding: 0.5rem;
}

.card {
  overflow: hidden;
}

.iframe_box,
.iframe_box_change {
  width: 100%;
  height: 1180px;
  position: relative;
  margin-top: 25px;
}
.iframe_box_change {
  height: 580px;
  padding-top: 50px;
}
.iframe_box .iframe_box_content,
.iframe_box_change .iframe_box_content {
  width: 100%;
  height: 100%;
}
.iframe_box_top {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100px;
  font-size: 25px;
  line-height: 100px;
  background: #fff;
  z-index: 99999999;
  padding-left: 50px;
}
#iframe_box_face div::before {
  content: "";
  width: 100px;
  position: absolute;
  top: 154px;
  right: 129px;
  z-index: 999;
  height: 20px;
  background-color: #ffffff;
}

#iframe_box_face > h1 {
  margin-top: 100px;
  margin-bottom: 20px;
}

.center-chart {
  height: 500px;
  margin-top: 50px;
  margin-left: 40px;
}
</style>
