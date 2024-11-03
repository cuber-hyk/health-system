<template>
  <div class="page_search">
	<div class="warp">
	  <div class="container">
		<div class="row">
		  <div class="col-12">
			<div class="card_result_search">
			  <div class="title">搜索结果</div>

				<!--{if filter.article}-->
				<!-- 文章搜索结果 -->
			  <list_result_search
				:list="result_article"
				title="健康百科"
				source_table="article"
			  ></list_result_search>
				<!--{/if}-->


						  <list_result_search
				v-if="$check_action('/registered_users/list', 'get')"
				:list="result_registered_users_user_name"
				title="注册用户用户姓名"
				source_table="registered_users"
			  ></list_result_search>
								  <list_result_search
				v-if="$check_action('/registered_users/list', 'get')"
				:list="result_registered_users_gender"
				title="注册用户性别"
				source_table="registered_users"
			  ></list_result_search>
									  <list_result_search
				v-if="$check_action('/food_classification/list', 'get')"
				:list="result_food_classification_food_classification"
				title="食物分类食物分类"
				source_table="food_classification"
			  ></list_result_search>
									  <list_result_search
				v-if="$check_action('/food_information/list', 'get')"
				:list="result_food_information_food_name"
				title="食物信息食物名称"
				source_table="food_information"
			  ></list_result_search>
								  <list_result_search
				v-if="$check_action('/food_information/list', 'get')"
				:list="result_food_information_food_classification"
				title="食物信息食物分类"
				source_table="food_information"
			  ></list_result_search>
																								  <list_result_search
				v-if="$check_action('/recipe_classification/list', 'get')"
				:list="result_recipe_classification_recipe_classification"
				title="食谱分类食谱分类"
				source_table="recipe_classification"
			  ></list_result_search>
									  <list_result_search
				v-if="$check_action('/recipe_information/list', 'get')"
				:list="result_recipe_information_recipe_name"
				title="食谱信息食谱名称"
				source_table="recipe_information"
			  ></list_result_search>
								  <list_result_search
				v-if="$check_action('/recipe_information/list', 'get')"
				:list="result_recipe_information_recipe_classification"
				title="食谱信息食谱分类"
				source_table="recipe_information"
			  ></list_result_search>
																								</div>
		  </div>
		</div>
	  </div>
	</div>
  </div>
</template>

<script>
import mixin from "../../mixins/page.js";
import list_result_search from "../../components/diy/list_result_search.vue";

export default {
  mixins: [mixin],
  data() {
	return {
	  "query": {
		word: "",
	  },
	  "result_article": [],
						"result_registered_users_user_name":[],
								"result_registered_users_gender":[],
									"result_food_classification_food_classification":[],
									"result_food_information_food_name":[],
								"result_food_information_food_classification":[],
																								"result_recipe_classification_recipe_classification":[],
									"result_recipe_information_recipe_name":[],
								"result_recipe_information_recipe_classification":[],
																						};
  },
  methods: {
	/**
	 * 获取文章
	 */
	get_article() {
	  this.$get("~/api/article/get_list?like=0", { page: 1, size: 10, title: this.query.word }, (json) => {
		if (json.result) {
		  this.result_article = json.result.list;
		}
	  });
	},

				/**
	 * 获取user_name
	 */
	get_registered_users_user_name(){
		this.$get("~/api/registered_users/get_list?like=0", { page: 1, size: 10, "user_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_registered_users_user_name = json.result.list;
			result_registered_users_user_name.map(o => o.title = o['user_name'])
	  			this.result_registered_users_user_name = result_registered_users_user_name
		 	}
		});
	},
						/**
	 * 获取gender
	 */
	get_registered_users_gender(){
		this.$get("~/api/registered_users/get_list?like=0", { page: 1, size: 10, "gender": this.query.word }, (json) => {
		  if (json.result) {
			var result_registered_users_gender = json.result.list;
			result_registered_users_gender.map(o => o.title = o['gender'])
	  			this.result_registered_users_gender = result_registered_users_gender
		 	}
		});
	},
							/**
	 * 获取food_classification
	 */
	get_food_classification_food_classification(){
		this.$get("~/api/food_classification/get_list?like=0", { page: 1, size: 10, "food_classification": this.query.word }, (json) => {
		  if (json.result) {
			var result_food_classification_food_classification = json.result.list;
			result_food_classification_food_classification.map(o => o.title = o['food_classification'])
	  			this.result_food_classification_food_classification = result_food_classification_food_classification
		 	}
		});
	},
							/**
	 * 获取food_name
	 */
	get_food_information_food_name(){
		this.$get("~/api/food_information/get_list?like=0", { page: 1, size: 10, "food_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_food_information_food_name = json.result.list;
			result_food_information_food_name.map(o => o.title = o['food_name'])
	  			this.result_food_information_food_name = result_food_information_food_name
		 	}
		});
	},
						/**
	 * 获取food_classification
	 */
	get_food_information_food_classification(){
		this.$get("~/api/food_information/get_list?like=0", { page: 1, size: 10, "food_classification": this.query.word }, (json) => {
		  if (json.result) {
			var result_food_information_food_classification = json.result.list;
			result_food_information_food_classification.map(o => o.title = o['food_classification'])
	  			this.result_food_information_food_classification = result_food_information_food_classification
		 	}
		});
	},
																						/**
	 * 获取recipe_classification
	 */
	get_recipe_classification_recipe_classification(){
		this.$get("~/api/recipe_classification/get_list?like=0", { page: 1, size: 10, "recipe_classification": this.query.word }, (json) => {
		  if (json.result) {
			var result_recipe_classification_recipe_classification = json.result.list;
			result_recipe_classification_recipe_classification.map(o => o.title = o['recipe_classification'])
	  			this.result_recipe_classification_recipe_classification = result_recipe_classification_recipe_classification
		 	}
		});
	},
							/**
	 * 获取recipe_name
	 */
	get_recipe_information_recipe_name(){
		this.$get("~/api/recipe_information/get_list?like=0", { page: 1, size: 10, "recipe_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_recipe_information_recipe_name = json.result.list;
			result_recipe_information_recipe_name.map(o => o.title = o['recipe_name'])
	  			this.result_recipe_information_recipe_name = result_recipe_information_recipe_name
		 	}
		});
	},
						/**
	 * 获取recipe_classification
	 */
	get_recipe_information_recipe_classification(){
		this.$get("~/api/recipe_information/get_list?like=0", { page: 1, size: 10, "recipe_classification": this.query.word }, (json) => {
		  if (json.result) {
			var result_recipe_information_recipe_classification = json.result.list;
			result_recipe_information_recipe_classification.map(o => o.title = o['recipe_classification'])
	  			this.result_recipe_information_recipe_classification = result_recipe_information_recipe_classification
		 	}
		});
	},
																					
  },
  components: { list_result_search },
	created(){
    this.query.word = this.$route.query.word || "";
  },
  mounted() {
	this.get_article();
					this.get_registered_users_user_name();
							this.get_registered_users_gender();
								this.get_food_classification_food_classification();
								this.get_food_information_food_name();
							this.get_food_information_food_classification();
																							this.get_recipe_classification_recipe_classification();
								this.get_recipe_information_recipe_name();
							this.get_recipe_information_recipe_classification();
																					  },
  watch: {
	$route() {
	  $.push(this.query, this.$route.query);
	  this.get_article();
				  this.get_registered_users_user_name();
						  this.get_registered_users_gender();
							  this.get_food_classification_food_classification();
							  this.get_food_information_food_name();
						  this.get_food_information_food_classification();
																						  this.get_recipe_classification_recipe_classification();
							  this.get_recipe_information_recipe_name();
						  this.get_recipe_information_recipe_classification();
																						},
  },
};
</script>

<style scoped>
.card_search {
  text-align: center;
}
.card_result_search>.title {
  text-align: center;
  padding: 10px 0;
}
</style>
