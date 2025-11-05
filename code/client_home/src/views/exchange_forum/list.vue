<template>
	<div class="diy_list page_exchange_forum" id="exchange_forum_list">
		<div class="warp">
			<div class="container diy_list_container">
				<div class="diy_list_title">
					<div class="col">
						<span class="title">动漫交流列表</span>
					</div>
				</div>
				<div class="row diy_list_search">
					<div class="col">
						<!-- 搜索栏 -->
						<div class="view">
							<span class="diy_list_search_title">关键字搜索：</span>
								<b-form-input size="sm" class="mr-sm-2" placeholder="论坛标题搜索" v-model="query['forum_title']" />
									<b-form-input size="sm" class="mr-sm-2" placeholder="论坛类型搜索" v-model="query['forum_type']" />
									<b-form-input size="sm" class="mr-sm-2" placeholder="论坛标记搜索" v-model="query['forum_tag']" />
											<b-button size="sm" @click="search()" >
								<b-icon icon="search"/>
							</b-button>
						</div>
						<!-- /搜索栏 -->
					</div>
				</div>
				<div class="diy_list_select_box">
					<span class="diy_list_select_title">下拉搜索：</span>
						<div class="diy_list_dropdown_box">
						<div class="col">
							<!-- 筛选 -->
							<div class="view">
															<b-dropdown text="论坛类型" variant="outline-dark" left>
									<b-dropdown-item @click="filter_set('全部','forum_type')">全部</b-dropdown-item>
										<b-dropdown-item v-for="(o, i) in list_forum_type" :key="i" @click="filter_set(o['forum_type'],'forum_type')" >
												{{ o['forum_type'] }}
										</b-dropdown-item>
								</b-dropdown>
														<b-dropdown text="论坛标记" variant="outline-dark" left>
									<b-dropdown-item @click="filter_set('全部','forum_tag')">全部</b-dropdown-item>
										<b-dropdown-item v-for="(o,i) in list_forum_tag" @click="filter_set(o,'forum_tag')" >
										{{ o }}
										</b-dropdown-item>
								</b-dropdown>
													</div>
							<!-- /筛选 -->
						</div>
					</div>
					<div class="diy_list_sort_box">
						<div class="col">
							<!-- 排序 -->
							<div class="view">
								<b-dropdown text="排序" variant="outline-dark" left>
										<b-dropdown-item v-for="(o, i) in list_sort" :key="i" @click="set_sort(o)" >
												{{ o.name }}
										</b-dropdown-item>
								</b-dropdown>
							</div>
							<!--/排序 -->
						</div>
					</div>
				</div>

				<div class="row diy_list_box">
					<div class="col">
						<!-- 列表 -->
						<list_exchange_forum :list="list" />
						<!-- /列表 -->
					</div>
				</div>
				<div class="row diy_list_page_box">
					<div class="col overflow-auto flex_cc">
						<!-- 分页器 -->
<!--						<diy_pager v-model="query['page']" :size="query['size']" :count="count" v-on:toPage="toPage" v-on:toSize="toSize" ></diy_pager>-->
            <b-pagination
                v-model="query.page"
                :total-rows="count"
                :per-page="query.size"
                @change="goToPage"
            />
						<!-- /分页器 -->
					</div>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
	import list_exchange_forum from "@/components/diy/list_exchange_forum.vue";
	import diy_pager from "@/components/diy/diy_pager";
	import mixin from "@/mixins/page.js";

	export default {
		mixins: [mixin],
		components: {
			diy_pager,
			list_exchange_forum
		},
		data() {
			return {
				url_get_list: "~/api/exchange_forum/get_list?like=0",

				// 查询条件
				query: {
					keyword: "",
					page: 1,
					size: 10,
								"forum_title": "", // 论坛标题
											"forum_type": "", // 论坛类型
											"forum_tag": "", // 论坛标记
											"examine_state": "已通过", // 审核状态
				},

				// 排序内容
				list_sort: [{
						name: "创建时间从高到低",
						value: "create_time desc",
					},
					{
						name: "创建时间从低到高",
						value: "create_time asc",
					},
					{
						name: "更新时间从高到低",
						value: "update_time desc",
					},
					{
						name: "更新时间从低到高",
						value: "update_time asc",
					},
						{
						name: "论坛标题正序",
						value: "forum_title asc",
					},
					{
						name: "论坛标题倒序",
						value: "forum_title desc",
					},
							{
						name: "论坛类型正序",
						value: "forum_type asc",
					},
					{
						name: "论坛类型倒序",
						value: "forum_type desc",
					},
							{
						name: "论坛标记正序",
						value: "forum_tag asc",
					},
					{
						name: "论坛标记倒序",
						value: "forum_tag desc",
					},
								],

							// 论坛类型列表
				"list_forum_type": [""],
							// 论坛标记列表
				"list_forum_tag": ['精品帖子','普通帖子'],

			}
		},
		methods: {
      get_list_before(param) {
      },
			/**
			 * 筛选选择
			 */
			filter_set(o,key) {
			    if (o == "全部") {
			        this.query[key] = "";
			    } else {
			        this.query[key] = o;
			    }
			    this.search();
			},

			/**
			 * 排序
			 */
			set_sort(o) {
			    this.query.orderby = o.value;
			    this.search();
			},


					/**
			 * 获取论坛类型列表
			 */
			async get_list_forum_type() {
				var json = await this.$get("~/api/forum_classification/get_list?");
				if (json.result) {
					this.list_forum_type = json.result.list;
				} else if (json.error) {
					console.log(json.error);
				}
			},

			/**
			 * 筛选
			 */
												filter_forum_type(o) {
				if (o == "全部") {
					this.query["forum_type"] = "";
				} else {
					this.query["forum_type"] = o;
				}
				this.search();
			},
											filter_forum_tag(o) {
				if (o == "全部") {
					this.query["forum_tag"] = "";
				} else {
					this.query["forum_tag"] = o;
				}
				this.search();
			},
											/**
			 * 重置
			 */
			reset() {
							this.query.forum_title = ""
										this.query.forum_type = ""
										this.query.forum_tag = ""
										this.search();
			},

			// 返回条数
			toSize(i){
				this.query.size = i;
				this.first();
			},

			// 返回页数
			toPage(i){
				this.query.page = i;
				this.first();
			},

      goToPage(v){
        this.query.page = v;
        this.goToNew(v)
      },

		},
		computed: {
		},
		created() {
						/**
			 * 获取论坛类型列表
			 */
			this.get_list_forum_type();
												}
	}
</script>

<style>
</style>
