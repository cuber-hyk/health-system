<template>
  <div class="pie_chart" :id="id">饼形图</div>
</template>

<script>
// import echarts from "@/assets/js/echarts.min.js";

export default {
  name: "Home",
  components: {},
  props: {
    id: {
      type: String,
      default: "pieChart",
    },
    title: {
      type: String,
      default: "XXX饼状图",
    },
    list: {
      type: Array,
      required: true,
      default: () => [],
    },
    type: {
      type: String,
      default: "参数",
    },
    radius: {
      type: String,
      default: "50%",
    },
  },
  data() {
    return {
      option: {
        title: {
          text: "上传商品分类数比",
          left: "center",
        },
        tooltip: {
          trigger: "item",
          formatter: "{a} <br/>{b} : {c} ({d}%)",
        },
        legend: {
          orient: "vertical",
          left: "left",
        },
        series: [
          {
            name: "",
            type: "pie",
            radius: "50%",
            data: [],
            emphasis: {
              itemStyle: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: "rgba(0, 0, 0, 0.5)",
              },
            },
          },
        ],
      },
    };
  },
  created() {},
  mounted() {
    // let that = this;
    this.init_chart();
    // setTimeout(function () {
    //   that.init_chart();
    // }, 200);
  },
  methods: {
    init_chart() {
      // 外部参数
      var title = this.title;
      var type = this.type;
      var radius = this.radius;

      // 标题
      if (title) {
        this.option.title.text = title;
      }

      // 分类名
      if (type) {
        this.option.series[0].name = type;
      }

      // radius
      if (radius) {
        this.option.series[0].radius = radius;
      }

      // 参数
      this.option.series[0].data = this.list;

      let myChart = this.$echarts.init(document.getElementById(this.id));
      myChart.setOption(this.option);
    },
  },
  watch: {
    list() {
      this.init_chart();
    },
  },
};
</script>
<style scoped>
.pie_chart {
  width: 100%;
  height: 400px; /* 可以根据需要调整高度 */
}
</style>
