<template>
	<el-main class="bg edit_wrap">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">

							<el-col v-if="user_group === '管理员' || $check_field('get','forum_title') || $check_field('add','forum_title') || $check_field('set','forum_title')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="论坛标题" prop="forum_title">
												<el-input id="forum_title" v-model="form['forum_title']" placeholder="请输入论坛标题"
							  v-if="user_group === '管理员' || (form['reporting_information_id'] && $check_field('set','forum_title')) || (!form['reporting_information_id'] && $check_field('add','forum_title'))" :disabled="disabledObj['forum_title_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','forum_title')">{{form['forum_title']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','forum_type') || $check_field('add','forum_type') || $check_field('set','forum_type')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="论坛类型" prop="forum_type">
												<el-input id="forum_type" v-model="form['forum_type']" placeholder="请输入论坛类型"
							  v-if="user_group === '管理员' || (form['reporting_information_id'] && $check_field('set','forum_type')) || (!form['reporting_information_id'] && $check_field('add','forum_type'))" :disabled="disabledObj['forum_type_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','forum_type')">{{form['forum_type']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','publishing_users') || $check_field('add','publishing_users') || $check_field('set','publishing_users')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="发布用户" prop="publishing_users">
													<el-select v-if="user_group === '管理员' || (form['reporting_information_id'] && $check_field('set','publishing_users')) || (!form['reporting_information_id'] && $check_field('add','publishing_users'))" id="publishing_users" v-model="form['publishing_users']" :disabled="disabledObj['publishing_users_isDisabled']">
							<el-option v-for="o in list_user_publishing_users" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
						<el-select v-else-if="$check_field('get','publishing_users')" id="publishing_users" v-model="form['publishing_users']" :disabled="true">
							<el-option v-for="o in list_user_publishing_users" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','report_users') || $check_field('add','report_users') || $check_field('set','report_users')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="举报用户" prop="report_users">
																		<div v-if="user_group !== '管理员'">
							{{ get_user_session_report_users(form['report_users']) }}
							<!--<el-input id="business_name" v-model="form['report_users']" placeholder="请输入举报用户"-->
							<!--v-if="user_group === '管理员' || (form['reporting_information_id'] && $check_field('set','report_users')) || (!form['reporting_information_id'] && $check_field('add','report_users'))" :disabled="disabledObj['report_users_isDisabled']"></el-input>-->
							<!--<div v-else-if="$check_field('get','report_users')">{{form['report_users']}}</div>-->
							<el-select v-if="user_group === '管理员' || (form['reporting_information_id'] && $check_field('set','report_users')) || (!form['reporting_information_id'] && $check_field('add','report_users'))" id="report_users" v-model="form['report_users']" :disabled="disabledObj['report_users_isDisabled']">
								<el-option v-for="o in list_user_report_users" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
										   :value="o['user_id']">
								</el-option>
							</el-select>
							<el-select v-else-if="$check_field('get','report_users')" id="report_users" v-model="form['report_users']" :disabled="true">
								<el-option v-for="o in list_user_report_users" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
										   :value="o['user_id']">
								</el-option>
							</el-select>
						</div>
						<el-select v-else id="report_users" v-model="form['report_users']" :disabled="disabledObj['report_users_isDisabled']">
							<el-option v-for="o in list_user_report_users" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
																</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','reporting_time') || $check_field('add','reporting_time') || $check_field('set','reporting_time')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="举报时间" prop="reporting_time">
								<el-date-picker :disabled="disabledObj['reporting_time_isDisabled']" v-if="user_group === '管理员' || (form['reporting_information_id'] && $check_field('set','reporting_time')) || (!form['reporting_information_id'] && $check_field('add','reporting_time'))" id="reporting_time"
						v-model="form['reporting_time']" type="date" placeholder="选择日期">
					</el-date-picker>
					<div v-else-if="$check_field('get','reporting_time')">{{form['reporting_time']}}</div>
							</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','report_content') || $check_field('add','report_content') || $check_field('set','report_content')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="举报内容" prop="report_content">
								<el-input type="textarea" id="report_content" v-model="form['report_content']" placeholder="请输入举报内容"
						v-if="user_group === '管理员' || (form['reporting_information_id'] && $check_field('set','report_content')) || (!form['reporting_information_id'] && $check_field('add','report_content'))" :disabled="disabledObj['report_content_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','report_content')">{{form['report_content']}}</div>
							</el-form-item>
			</el-col>
							<el-col :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="审核状态" prop="examine_state">
					<el-select id="examine_state" v-model="form['examine_state']"
						v-if="user_group === '管理员' || (form['examine_state'] && $check_examine()) || (!form['examine_state'] && $check_examine())">
						<el-option key="未审核" label="未审核" value="未审核"></el-option>
						<el-option key="已通过" label="已通过" value="已通过"></el-option>
						<el-option key="未通过" label="未通过" value="未通过"></el-option>
					</el-select>
					<div v-else>{{form["examine_state"]}}</div>
				</el-form-item>
			</el-col>
					<el-col :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="审核回复" prop="examine_reply">
					<el-input id="examine_reply" v-model="form['examine_reply']" placeholder="请输入审核回复"
						v-if="user_group === '管理员' || (form['examine_reply'] && $check_examine()) || (!form['examine_reply'] && $check_examine())"></el-input>
					<div v-else>{{form["examine_reply"]}}</div>
				</el-form-item>
			</el-col>
	
	
	
	
	
	
	
			<el-col :xs="24" :sm="12" :lg="8" class="el_form_btn_warp">
				<el-form-item>
					<el-button type="primary" @click="submit()">提交</el-button>
					<el-button @click="cancel()">取消</el-button>
				</el-form-item>
			</el-col>

		</el-form>
	</el-main>
</template>

<script>
	import mixin from "@/mixins/page.js";

	export default {
		mixins: [mixin],
		data() {
			return {
				field: "reporting_information_id",
				url_add: "~/api/reporting_information/add?",
				url_set: "~/api/reporting_information/set?",
				url_get_obj: "~/api/reporting_information/get_obj?",
				url_upload: "~/api/reporting_information/upload?",

				query: {
					"reporting_information_id": 0,
				},

				form: {
								"forum_title":  '', // 论坛标题
										"forum_type":  '', // 论坛类型
										"publishing_users": 0, // 发布用户
										"report_users": 0, // 举报用户
										"reporting_time":  '', // 举报时间
										"report_content":  '', // 举报内容
									"examine_state": "未审核",
							"examine_reply": "",
							"reporting_information_id": 0, // ID
						
				},
				disabledObj:{
								"forum_title_isDisabled": false,
										"forum_type_isDisabled": false,
										"publishing_users_isDisabled": false,
										"report_users_isDisabled": false,
										"reporting_time_isDisabled": false,
										"report_content_isDisabled": false,
										},

	
		
		
					// 用户列表
				list_user_publishing_users: [],
				
					// 用户列表
				list_user_report_users: [],
						// 用户组
				group_user_report_users: "",
				
		
	
			}
		},
		methods: {


	
	
			
	
			
	
				/**
			 * 获取普通用户用户列表
			 */
			async get_list_user_publishing_users() {
                // if(this.user_group !== "管理员" && this.form["publishing_users"] === 0) {
                //     this.form["publishing_users"] = this.user.user_id;
                // }
                var json = await this.$get("~/api/user/get_list?user_group=普通用户");
                if(json.result && json.result.list){
                    this.list_user_publishing_users = json.result.list;
                }
                else if(json.error){
                    console.error(json.error);
                }
			},
					get_user_publishing_users(id){
				var obj = this.list_user_publishing_users.getObj({"user_id":id});
				var ret = "";
				if(obj){
					if(obj.nickname){
						ret = obj.nickname;}
					else{
						ret = obj.username;
					}
				}
				return ret;
			},
			
	
				/**
			 * 获取普通用户用户列表
			 */
			async get_list_user_report_users() {
                // if(this.user_group !== "管理员" && this.form["report_users"] === 0) {
                //     this.form["report_users"] = this.user.user_id;
                // }
                var json = await this.$get("~/api/user/get_list?user_group=普通用户");
                if(json.result && json.result.list){
                    this.list_user_report_users = json.result.list;
                }
                else if(json.error){
                    console.error(json.error);
                }
			},
					/**
			 * 获取普通用户用户组
			 */
			async get_group_user_report_users() {
							this.form["report_users"] = this.user.user_id;
							var json = await this.$get("~/api/user_group/get_obj?name=普通用户");
				if(json.result && json.result.obj){
					this.group_user_report_users = json.result.obj;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
			get_user_session_report_users(id){
				var _this = this;
				var user_id = {"user_id":id}
				var url = "~/api/"+_this.group_user_report_users.source_table+"/get_obj?"
				this.$get(url, user_id, function(res) {
					if (res.result && res.result.obj) {
						var arr = []
						for (let key in res.result.obj) {
							arr.push(key)
						}
						var arrForm = []
									for (let key in _this.form) {
							arrForm.push(key)
						}
												_this.form["report_users"] = id
									_this.disabledObj['report_users' + '_isDisabled'] = true
						for (var i=0;i<arr.length;i++){
						  if (arr[i]!=='examine_state' && arr[i]!=='examine_reply') {
							for (var j = 0; j < arrForm.length; j++) {
							  if (arr[i] === arrForm[j]) {
								if (arr[i] !== "report_users") {
			                      _this.form[arrForm[j]] = res.result.obj[arr[i]]
			                      _this.disabledObj[arrForm[j] + '_isDisabled'] = true
								  break;
								} else {
								  _this.disabledObj[arrForm[j] + '_isDisabled'] = true
								}
							  }
							}
						  }
						}
					}
				});
			},
					get_user_report_users(id){
				var obj = this.list_user_report_users.getObj({"user_id":id});
				var ret = "";
				if(obj){
					if(obj.nickname){
						ret = obj.nickname;}
					else{
						ret = obj.username;
					}
				}
				return ret;
			},
			
	
			
	
		
			/**
			 * 获取对象之前
			 * @param {Object} param
			 */
			get_obj_before(param) {
				var form = "";
									// 获取缓存数据附加
				form = $.db.get("form");
							$.push(this.form ,form);
													
				if(this.form && form){
					Object.keys(this.form).forEach(key => {
						Object.keys(form).forEach(dbKey => {
							// if(dbKey === "charging_standard"){
							// 	this.form['charging_rules'] = form[dbKey];
							// 	this.disabledObj['charging_rules_isDisabled'] = true;
							// };
							if(key === dbKey){
								this.disabledObj[key+'_isDisabled'] = true;
							}
						})
					})
				}
										        if (this.form["reporting_time"].indexOf("-")===-1){
          this.form["reporting_time"] = this.$toTime(parseInt(this.form["reporting_time"]),"yyyy-MM-dd")
        }
							$.db.del("form");
				return param;
			},

			/**
			 * 获取对象之后
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_after(json, func){


														        if(this.form["reporting_time"]=="0000-00-00"){
          this.form["reporting_time"] = null;
        }
				if(parseInt(this.form["reporting_time"]) > 9999){
					this.form["reporting_time"] = this.$toTime(parseInt(this.form["reporting_time"]),"yyyy-MM-dd")
				}
					

			},

			/**
			 * 提交前验证事件
			 * @param {Object} 请求参数
			 * @return {String} 验证成功返回null, 失败返回错误提示
			 */
			submit_check(param) {
				let msg = null
																								return msg;
			},

			is_view(){
				var bl = this.user_group == "管理员";

				if(!bl){
					bl = this.$check_action('/reporting_information/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/reporting_information/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/reporting_information/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/reporting_information/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/reporting_information/view','get');
					console.log(bl ? "你有视图查询权限视作有查询权限" : "你没有视图查询权限");
				}

				console.log(bl ? "具有当前页面的查看权，请注意这不代表你有字段的查看权" : "无权查看当前页，请注意即便有字段查询权限没有页面查询权限也不行");

				return bl;
			},
			/**
			 * 上传文件
			 * @param {Object} param
			 */
			uploadimg(param) {
				this.uploadFile(param.file, "avatar");
			},

		},
		created() {
									this.get_list_user_publishing_users();
								this.get_list_user_report_users();
					this.get_group_user_report_users();
									},
	}
</script>

<style>
	.avatar-uploader .el-upload {
		border: 1px dashed #d9d9d9;
		border-radius: 6px;
		cursor: pointer;
		position: relative;
		overflow: hidden;
	}

	.avatar-uploader .el-upload:hover {
		border-color: #409EFF;
	}

	.avatar-uploader-icon {
		font-size: 28px;
		color: #8c939d;
		width: 178px;
		height: 178px;
		line-height: 178px;
		text-align: center;
	}

	.avatar {
		width: 178px;
		height: 178px;
		display: block;
	}




</style>
