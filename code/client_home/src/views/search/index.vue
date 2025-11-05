<template>
  <div class="page_search">
	<div class="warp">
	  <div class="container">
		<div class="row">
		  <div class="col-12">
			<div class="card_result_search">
			  <div class="title">搜索结果</div>

				<!-- 文章搜索结果 -->
			  <list_result_search
				:list="result_article"
				title="新闻资讯"
				source_table="article"
			  ></list_result_search>


						  <list_result_search
				v-if="$check_action('/ordinary_users/list', 'get')"
				:list="result_ordinary_users_user_name"
				title="普通用户用户姓名"
				source_table="ordinary_users"
			  ></list_result_search>
								  <list_result_search
				v-if="$check_action('/ordinary_users/list', 'get')"
				:list="result_ordinary_users_user_gender"
				title="普通用户用户性别"
				source_table="ordinary_users"
			  ></list_result_search>
															  <list_result_search
				v-if="$check_action('/forum_classification/list', 'get')"
				:list="result_forum_classification_forum_type"
				title="论坛分类论坛类型"
				source_table="forum_classification"
			  ></list_result_search>
									  <list_result_search
				v-if="$check_action('/exchange_forum/list', 'get')"
				:list="result_exchange_forum_forum_title"
				title="动漫交流论坛标题"
				source_table="exchange_forum"
			  ></list_result_search>
								  <list_result_search
				v-if="$check_action('/exchange_forum/list', 'get')"
				:list="result_exchange_forum_forum_type"
				title="动漫交流论坛类型"
				source_table="exchange_forum"
			  ></list_result_search>
								  <list_result_search
				v-if="$check_action('/exchange_forum/list', 'get')"
				:list="result_exchange_forum_forum_tag"
				title="动漫交流论坛标记"
				source_table="exchange_forum"
			  ></list_result_search>
																		  <list_result_search
				v-if="$check_action('/reporting_information/list', 'get')"
				:list="result_reporting_information_forum_title"
				title="举报信息论坛标题"
				source_table="reporting_information"
			  ></list_result_search>
								  <list_result_search
				v-if="$check_action('/reporting_information/list', 'get')"
				:list="result_reporting_information_forum_type"
				title="举报信息论坛类型"
				source_table="reporting_information"
			  ></list_result_search>
																					  <list_result_search
				v-if="$check_action('/learning_materials/list', 'get')"
				:list="result_learning_materials_data_name"
				title="动漫视频动漫名称"
				source_table="learning_materials"
			  ></list_result_search>
											  <list_result_search
				v-if="$check_action('/learning_materials/list', 'get')"
				:list="result_learning_materials_data_type"
				title="动漫视频动漫类型"
				source_table="learning_materials"
			  ></list_result_search>
																								  <list_result_search
				v-if="$check_action('/reference_information/list', 'get')"
				:list="result_reference_information_data_name"
				title="参考信息动漫名称"
				source_table="reference_information"
			  ></list_result_search>
								  <list_result_search
				v-if="$check_action('/reference_information/list', 'get')"
				:list="result_reference_information_data_type"
				title="参考信息动漫类型"
				source_table="reference_information"
			  ></list_result_search>
														  <list_result_search
				v-if="$check_action('/reference_information/list', 'get')"
				:list="result_reference_information_user_name"
				title="参考信息用户姓名"
				source_table="reference_information"
			  ></list_result_search>
																					  <list_result_search
				v-if="$check_action('/sign_in_information/list', 'get')"
				:list="result_sign_in_information_user_name"
				title="签到信息用户姓名"
				source_table="sign_in_information"
			  ></list_result_search>
								  <list_result_search
				v-if="$check_action('/sign_in_information/list', 'get')"
				:list="result_sign_in_information_sign_in_date"
				title="签到信息签到日期"
				source_table="sign_in_information"
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
						"result_ordinary_users_user_name":[],
								"result_ordinary_users_user_gender":[],
															"result_forum_classification_forum_type":[],
									"result_exchange_forum_forum_title":[],
								"result_exchange_forum_forum_type":[],
								"result_exchange_forum_forum_tag":[],
																		"result_reporting_information_forum_title":[],
								"result_reporting_information_forum_type":[],
																					"result_learning_materials_data_name":[],
											"result_learning_materials_data_type":[],
																								"result_reference_information_data_name":[],
								"result_reference_information_data_type":[],
														"result_reference_information_user_name":[],
																					"result_sign_in_information_user_name":[],
								"result_sign_in_information_sign_in_date":[],
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
	get_ordinary_users_user_name(){
		this.$get("~/api/ordinary_users/get_list?like=0", { page: 1, size: 10, "user_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_ordinary_users_user_name = json.result.list;
			result_ordinary_users_user_name.map(o => o.title = o['user_name'])
	  			this.result_ordinary_users_user_name = result_ordinary_users_user_name
		 	}
		});
	},
						/**
	 * 获取user_gender
	 */
	get_ordinary_users_user_gender(){
		this.$get("~/api/ordinary_users/get_list?like=0", { page: 1, size: 10, "user_gender": this.query.word }, (json) => {
		  if (json.result) {
			var result_ordinary_users_user_gender = json.result.list;
			result_ordinary_users_user_gender.map(o => o.title = o['user_gender'])
	  			this.result_ordinary_users_user_gender = result_ordinary_users_user_gender
		 	}
		});
	},
													/**
	 * 获取forum_type
	 */
	get_forum_classification_forum_type(){
		this.$get("~/api/forum_classification/get_list?like=0", { page: 1, size: 10, "forum_type": this.query.word }, (json) => {
		  if (json.result) {
			var result_forum_classification_forum_type = json.result.list;
			result_forum_classification_forum_type.map(o => o.title = o['forum_type'])
	  			this.result_forum_classification_forum_type = result_forum_classification_forum_type
		 	}
		});
	},
							/**
	 * 获取forum_title
	 */
	get_exchange_forum_forum_title(){
		this.$get("~/api/exchange_forum/get_list?like=0", { page: 1, size: 10, "forum_title": this.query.word }, (json) => {
		  if (json.result) {
			var result_exchange_forum_forum_title = json.result.list;
			result_exchange_forum_forum_title.map(o => o.title = o['forum_title'])
	  			this.result_exchange_forum_forum_title = result_exchange_forum_forum_title
		 	}
		});
	},
						/**
	 * 获取forum_type
	 */
	get_exchange_forum_forum_type(){
		this.$get("~/api/exchange_forum/get_list?like=0", { page: 1, size: 10, "forum_type": this.query.word }, (json) => {
		  if (json.result) {
			var result_exchange_forum_forum_type = json.result.list;
			result_exchange_forum_forum_type.map(o => o.title = o['forum_type'])
	  			this.result_exchange_forum_forum_type = result_exchange_forum_forum_type
		 	}
		});
	},
						/**
	 * 获取forum_tag
	 */
	get_exchange_forum_forum_tag(){
		this.$get("~/api/exchange_forum/get_list?like=0", { page: 1, size: 10, "forum_tag": this.query.word }, (json) => {
		  if (json.result) {
			var result_exchange_forum_forum_tag = json.result.list;
			result_exchange_forum_forum_tag.map(o => o.title = o['forum_tag'])
	  			this.result_exchange_forum_forum_tag = result_exchange_forum_forum_tag
		 	}
		});
	},
																/**
	 * 获取forum_title
	 */
	get_reporting_information_forum_title(){
		this.$get("~/api/reporting_information/get_list?like=0", { page: 1, size: 10, "forum_title": this.query.word }, (json) => {
		  if (json.result) {
			var result_reporting_information_forum_title = json.result.list;
			result_reporting_information_forum_title.map(o => o.title = o['forum_title'])
	  			this.result_reporting_information_forum_title = result_reporting_information_forum_title
		 	}
		});
	},
						/**
	 * 获取forum_type
	 */
	get_reporting_information_forum_type(){
		this.$get("~/api/reporting_information/get_list?like=0", { page: 1, size: 10, "forum_type": this.query.word }, (json) => {
		  if (json.result) {
			var result_reporting_information_forum_type = json.result.list;
			result_reporting_information_forum_type.map(o => o.title = o['forum_type'])
	  			this.result_reporting_information_forum_type = result_reporting_information_forum_type
		 	}
		});
	},
																			/**
	 * 获取data_name
	 */
	get_learning_materials_data_name(){
		this.$get("~/api/learning_materials/get_list?like=0", { page: 1, size: 10, "data_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_learning_materials_data_name = json.result.list;
			result_learning_materials_data_name.map(o => o.title = o['data_name'])
	  			this.result_learning_materials_data_name = result_learning_materials_data_name
		 	}
		});
	},
									/**
	 * 获取data_type
	 */
	get_learning_materials_data_type(){
		this.$get("~/api/learning_materials/get_list?like=0", { page: 1, size: 10, "data_type": this.query.word }, (json) => {
		  if (json.result) {
			var result_learning_materials_data_type = json.result.list;
			result_learning_materials_data_type.map(o => o.title = o['data_type'])
	  			this.result_learning_materials_data_type = result_learning_materials_data_type
		 	}
		});
	},
																						/**
	 * 获取data_name
	 */
	get_reference_information_data_name(){
		this.$get("~/api/reference_information/get_list?like=0", { page: 1, size: 10, "data_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_reference_information_data_name = json.result.list;
			result_reference_information_data_name.map(o => o.title = o['data_name'])
	  			this.result_reference_information_data_name = result_reference_information_data_name
		 	}
		});
	},
						/**
	 * 获取data_type
	 */
	get_reference_information_data_type(){
		this.$get("~/api/reference_information/get_list?like=0", { page: 1, size: 10, "data_type": this.query.word }, (json) => {
		  if (json.result) {
			var result_reference_information_data_type = json.result.list;
			result_reference_information_data_type.map(o => o.title = o['data_type'])
	  			this.result_reference_information_data_type = result_reference_information_data_type
		 	}
		});
	},
												/**
	 * 获取user_name
	 */
	get_reference_information_user_name(){
		this.$get("~/api/reference_information/get_list?like=0", { page: 1, size: 10, "user_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_reference_information_user_name = json.result.list;
			result_reference_information_user_name.map(o => o.title = o['user_name'])
	  			this.result_reference_information_user_name = result_reference_information_user_name
		 	}
		});
	},
																			/**
	 * 获取user_name
	 */
	get_sign_in_information_user_name(){
		this.$get("~/api/sign_in_information/get_list?like=0", { page: 1, size: 10, "user_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_sign_in_information_user_name = json.result.list;
			result_sign_in_information_user_name.map(o => o.title = o['user_name'])
	  			this.result_sign_in_information_user_name = result_sign_in_information_user_name
		 	}
		});
	},
						/**
	 * 获取sign_in_date
	 */
	get_sign_in_information_sign_in_date(){
		this.$get("~/api/sign_in_information/get_list?like=0", { page: 1, size: 10, "sign_in_date": this.query.word }, (json) => {
		  if (json.result) {
			var result_sign_in_information_sign_in_date = json.result.list;
			result_sign_in_information_sign_in_date.map(o => o.title = o['sign_in_date'])
	  			this.result_sign_in_information_sign_in_date = result_sign_in_information_sign_in_date
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
					this.get_ordinary_users_user_name();
							this.get_ordinary_users_user_gender();
														this.get_forum_classification_forum_type();
								this.get_exchange_forum_forum_title();
							this.get_exchange_forum_forum_type();
							this.get_exchange_forum_forum_tag();
																	this.get_reporting_information_forum_title();
							this.get_reporting_information_forum_type();
																				this.get_learning_materials_data_name();
										this.get_learning_materials_data_type();
																							this.get_reference_information_data_name();
							this.get_reference_information_data_type();
													this.get_reference_information_user_name();
																				this.get_sign_in_information_user_name();
							this.get_sign_in_information_sign_in_date();
						  },
  watch: {
	$route() {
	  $.push(this.query, this.$route.query);
	  this.get_article();
				  this.get_ordinary_users_user_name();
						  this.get_ordinary_users_user_gender();
													  this.get_forum_classification_forum_type();
							  this.get_exchange_forum_forum_title();
						  this.get_exchange_forum_forum_type();
						  this.get_exchange_forum_forum_tag();
																  this.get_reporting_information_forum_title();
						  this.get_reporting_information_forum_type();
																			  this.get_learning_materials_data_name();
									  this.get_learning_materials_data_type();
																						  this.get_reference_information_data_name();
						  this.get_reference_information_data_type();
												  this.get_reference_information_user_name();
																			  this.get_sign_in_information_user_name();
						  this.get_sign_in_information_sign_in_date();
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
