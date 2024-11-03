<template>
  <footer class="page_footer">
    <div class="wrap">
      <div class="container">
        <div class="row">
          <div class="col">
            <div class="div1">
              <div></div>
            </div>
            <div class="div2">
              <div></div>
            </div>
            <div class="div3">
              <div></div>
            </div>
            <div class="div4">
              <div></div>
            </div>
            <div class="div5">
              <div></div>
            </div>
            <div class="div6">
              <div></div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </footer>
</template>

<script>
import mixin from "@/mixins/component.js";

export default {
  mixins: [mixin],
  components: {},
  data() {
    return {};
  },
  methods: {},
  created() {
  },
};
</script>

<style scoped="scoped">

* {
  list-style: none;
}

ul {
  margin-left: 30px;
}

.page_footer {
  height: 150px;
}

.page_footer .wrap {
  background-color: transparent;
}


</style>
