<template>
	<el-main class="bg edit_wrap">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">

							<el-col v-if="user_group === '管理员' || $check_field('get','forum_title') || $check_field('add','forum_title') || $check_field('set','forum_title')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="论坛标题" prop="forum_title">
												<el-input id="forum_title" v-model="form['forum_title']" placeholder="请输入论坛标题"
							  v-if="user_group === '管理员' || (form['exchange_forum_id'] && $check_field('set','forum_title')) || (!form['exchange_forum_id'] && $check_field('add','forum_title'))" :disabled="disabledObj['forum_title_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','forum_title')">{{form['forum_title']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','forum_type') || $check_field('add','forum_type') || $check_field('set','forum_type')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="论坛类型" prop="forum_type">
								<el-select id="forum_type" v-model="form['forum_type']"
						v-if="user_group === '管理员' || (form['exchange_forum_id'] && $check_field('set','forum_type')) || (!form['exchange_forum_id'] && $check_field('add','forum_type'))">
						<el-option v-for="o in list_forum_type" :key="o['forum_type']" :label="o['forum_type']"
							:value="o['forum_type']">
						</el-option>
					</el-select>
					<div v-else-if="$check_field('get','forum_type')">{{form['forum_type']}}</div>
							</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','forum_tag') || $check_field('add','forum_tag') || $check_field('set','forum_tag')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="论坛标记" prop="forum_tag">
								<el-select id="forum_tag" v-model="form['forum_tag']"
						v-if="user_group === '管理员' || (form['exchange_forum_id'] && $check_field('set','forum_tag')) || (!form['exchange_forum_id'] && $check_field('add','forum_tag'))">
						<el-option v-for="o in list_forum_tag" :key="o" :label="o" :value="o">
						</el-option>
					</el-select>
					<div v-else-if="$check_field('get','forum_tag')">{{form['forum_tag']}}</div>
							</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','publishing_users') || $check_field('add','publishing_users') || $check_field('set','publishing_users')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="发布用户" prop="publishing_users">
																		<div v-if="user_group !== '管理员'">
							{{ get_user_session_publishing_users(form['publishing_users']) }}
							<!--<el-input id="business_name" v-model="form['publishing_users']" placeholder="请输入发布用户"-->
							<!--v-if="user_group === '管理员' || (form['exchange_forum_id'] && $check_field('set','publishing_users')) || (!form['exchange_forum_id'] && $check_field('add','publishing_users'))" :disabled="disabledObj['publishing_users_isDisabled']"></el-input>-->
							<!--<div v-else-if="$check_field('get','publishing_users')">{{form['publishing_users']}}</div>-->
							<el-select v-if="user_group === '管理员' || (form['exchange_forum_id'] && $check_field('set','publishing_users')) || (!form['exchange_forum_id'] && $check_field('add','publishing_users'))" id="publishing_users" v-model="form['publishing_users']" :disabled="disabledObj['publishing_users_isDisabled']">
								<el-option v-for="o in list_user_publishing_users" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
										   :value="o['user_id']">
								</el-option>
							</el-select>
							<el-select v-else-if="$check_field('get','publishing_users')" id="publishing_users" v-model="form['publishing_users']" :disabled="true">
								<el-option v-for="o in list_user_publishing_users" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
										   :value="o['user_id']">
								</el-option>
							</el-select>
						</div>
						<el-select v-else id="publishing_users" v-model="form['publishing_users']" :disabled="disabledObj['publishing_users_isDisabled']">
							<el-option v-for="o in list_user_publishing_users" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
																</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','cover_photo') || $check_field('add','cover_photo') || $check_field('set','cover_photo')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="封面图片" prop="cover_photo">
								<el-upload :disabled="disabledObj['cover_photo_isDisabled']" id="cover_photo" class="avatar-uploader" drag
						accept="image/gif, image/jpeg, image/png, image/jpg" action="" :http-request="upload_cover_photo"
						:show-file-list="false" v-if="user_group === '管理员' || (form['exchange_forum_id'] && $check_field('set','cover_photo')) || (!form['exchange_forum_id'] && $check_field('add','cover_photo'))">
						<img v-if="form['cover_photo']" :src="$fullUrl(form['cover_photo'])" class="avatar">
						<i v-else class="el-icon-plus avatar-uploader-icon"></i>
					</el-upload>
					<el-image v-else-if="$check_field('get','cover_photo')" style="width: 100px; height: 100px"
						:src="$fullUrl(form['cover_photo'])" :preview-src-list="[$fullUrl(form['cover_photo'])]">
						<div slot="error" class="image-slot">
							<img src="../../../public/img/error.png" style="width: 90px; height: 90px" />
						</div>
					</el-image>
							</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','publishing_content') || $check_field('add','publishing_content') || $check_field('set','publishing_content')" :xs="24" :sm="24" :lg="24" class="el_form_editor_warp">
				<el-form-item label="发布内容" prop="publishing_content">
					<quill-editor v-model.number="form['publishing_content']"
						v-if="user_group === '管理员' || (form['exchange_forum_id'] && $check_field('set','publishing_content')) || (!form['exchange_forum_id'] && $check_field('add','publishing_content')) ">
					</quill-editor>
					<div v-else-if="$check_field('get','publishing_content')" v-html="form['publishing_content']"></div>
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
				field: "exchange_forum_id",
				url_add: "~/api/exchange_forum/add?",
				url_set: "~/api/exchange_forum/set?",
				url_get_obj: "~/api/exchange_forum/get_obj?",
				url_upload: "~/api/exchange_forum/upload?",

				query: {
					"exchange_forum_id": 0,
				},

				form: {
								"forum_title":  '', // 论坛标题
										"forum_type":  '', // 论坛类型
										"forum_tag":  '', // 论坛标记
										"publishing_users": 0, // 发布用户
										"cover_photo":  '', // 封面图片
										"publishing_content":  '', // 发布内容
									"examine_state": "未审核",
							"examine_reply": "",
							"exchange_forum_id": 0, // ID
						
				},
				disabledObj:{
								"forum_title_isDisabled": false,
										"forum_type_isDisabled": false,
										"forum_tag_isDisabled": false,
										"publishing_users_isDisabled": false,
										"cover_photo_isDisabled": false,
										"publishing_content_isDisabled": false,
										},

	
						// 论坛类型选项列表
				list_forum_type: [""],
	
						// 论坛标记选项列表
				list_forum_tag: ['精品帖子','普通帖子'],
	
		
					// 用户列表
				list_user_publishing_users: [],
						// 用户组
				group_user_publishing_users: "",
				
		
	
			}
		},
		methods: {


	
	
			
				/**
			 * 获取论坛类型列表
			 */
			async get_list_forum_type() {
				var json = await this.$get("~/api/forum_classification/get_list?");
				if(json.result && json.result.list){
					this.list_forum_type = json.result.list;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
	
			
	
			
	
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
					/**
			 * 获取普通用户用户组
			 */
			async get_group_user_publishing_users() {
							this.form["publishing_users"] = this.user.user_id;
							var json = await this.$get("~/api/user_group/get_obj?name=普通用户");
				if(json.result && json.result.obj){
					this.group_user_publishing_users = json.result.obj;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
			get_user_session_publishing_users(id){
				var _this = this;
				var user_id = {"user_id":id}
				var url = "~/api/"+_this.group_user_publishing_users.source_table+"/get_obj?"
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
												_this.form["publishing_users"] = id
									_this.disabledObj['publishing_users' + '_isDisabled'] = true
						for (var i=0;i<arr.length;i++){
						  if (arr[i]!=='examine_state' && arr[i]!=='examine_reply') {
							for (var j = 0; j < arrForm.length; j++) {
							  if (arr[i] === arrForm[j]) {
								if (arr[i] !== "publishing_users") {
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
			 * 上传封面图片
			 * @param {Object} param 图片参数
			 */
			upload_cover_photo(param){
						this.uploadFile(param.file, "cover_photo");
					},
	
	
			
	
		
			/**
			 * 获取对象之前
			 * @param {Object} param
			 */
			get_obj_before(param) {
				var form = "";
													
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
																$.db.del("form");
				return param;
			},

			/**
			 * 获取对象之后
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_after(json, func){


																		

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
					bl = this.$check_action('/exchange_forum/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/exchange_forum/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/exchange_forum/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/exchange_forum/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/exchange_forum/view','get');
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
						this.get_list_forum_type();
									this.get_list_user_publishing_users();
					this.get_group_user_publishing_users();
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
