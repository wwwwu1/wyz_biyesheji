<template>
	<el-main class="bg edit_wrap">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">

							<el-col v-if="user_group === '管理员' || $check_field('get','data_name') || $check_field('add','data_name') || $check_field('set','data_name')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="动漫名称" prop="data_name">
												<el-input id="data_name" v-model="form['data_name']" placeholder="请输入动漫名称"
							  v-if="user_group === '管理员' || (form['reference_information_id'] && $check_field('set','data_name')) || (!form['reference_information_id'] && $check_field('add','data_name'))" :disabled="disabledObj['data_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','data_name')">{{form['data_name']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','data_type') || $check_field('add','data_type') || $check_field('set','data_type')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="动漫类型" prop="data_type">
												<el-input id="data_type" v-model="form['data_type']" placeholder="请输入动漫类型"
							  v-if="user_group === '管理员' || (form['reference_information_id'] && $check_field('set','data_type')) || (!form['reference_information_id'] && $check_field('add','data_type'))" :disabled="disabledObj['data_type_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','data_type')">{{form['data_type']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','reference_integral') || $check_field('add','reference_integral') || $check_field('set','reference_integral')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="参考积分" prop="reference_integral">
								<el-input-number id="reference_integral" v-model.number="form['reference_integral']"
						v-if="user_group === '管理员' || (form['reference_information_id'] && $check_field('set','reference_integral')) || (!form['reference_information_id'] && $check_field('add','reference_integral'))" :disabled="disabledObj['reference_integral_isDisabled']"></el-input-number>
					<div v-else-if="$check_field('get','reference_integral')">{{form['reference_integral']}}</div>
							</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','reference_user') || $check_field('add','reference_user') || $check_field('set','reference_user')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="参考用户" prop="reference_user">
																		<div v-if="user_group !== '管理员'">
							{{ get_user_session_reference_user(form['reference_user']) }}
							<!--<el-input id="business_name" v-model="form['reference_user']" placeholder="请输入参考用户"-->
							<!--v-if="user_group === '管理员' || (form['reference_information_id'] && $check_field('set','reference_user')) || (!form['reference_information_id'] && $check_field('add','reference_user'))" :disabled="disabledObj['reference_user_isDisabled']"></el-input>-->
							<!--<div v-else-if="$check_field('get','reference_user')">{{form['reference_user']}}</div>-->
							<el-select v-if="user_group === '管理员' || (form['reference_information_id'] && $check_field('set','reference_user')) || (!form['reference_information_id'] && $check_field('add','reference_user'))" id="reference_user" v-model="form['reference_user']" :disabled="disabledObj['reference_user_isDisabled']">
								<el-option v-for="o in list_user_reference_user" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
										   :value="o['user_id']">
								</el-option>
							</el-select>
							<el-select v-else-if="$check_field('get','reference_user')" id="reference_user" v-model="form['reference_user']" :disabled="true">
								<el-option v-for="o in list_user_reference_user" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
										   :value="o['user_id']">
								</el-option>
							</el-select>
						</div>
						<el-select v-else id="reference_user" v-model="form['reference_user']" :disabled="disabledObj['reference_user_isDisabled']">
							<el-option v-for="o in list_user_reference_user" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
																</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','user_name') || $check_field('add','user_name') || $check_field('set','user_name')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="用户姓名" prop="user_name">
												<el-input id="user_name" v-model="form['user_name']" placeholder="请输入用户姓名"
							  v-if="user_group === '管理员' || (form['reference_information_id'] && $check_field('set','user_name')) || (!form['reference_information_id'] && $check_field('add','user_name'))" :disabled="disabledObj['user_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','user_name')">{{form['user_name']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','reference_time') || $check_field('add','reference_time') || $check_field('set','reference_time')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="参考时间" prop="reference_time">
								<el-date-picker :disabled="disabledObj['reference_time_isDisabled']" v-if="user_group === '管理员' || (form['reference_information_id'] && $check_field('set','reference_time')) || (!form['reference_information_id'] && $check_field('add','reference_time'))" id="reference_time"
						v-model="form['reference_time']" type="date" placeholder="选择日期">
					</el-date-picker>
					<div v-else-if="$check_field('get','reference_time')">{{form['reference_time']}}</div>
							</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','detailed_remarks') || $check_field('add','detailed_remarks') || $check_field('set','detailed_remarks')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="详情备注" prop="detailed_remarks">
								<el-input type="textarea" id="detailed_remarks" v-model="form['detailed_remarks']" placeholder="请输入详情备注"
						v-if="user_group === '管理员' || (form['reference_information_id'] && $check_field('set','detailed_remarks')) || (!form['reference_information_id'] && $check_field('add','detailed_remarks'))" :disabled="disabledObj['detailed_remarks_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','detailed_remarks')">{{form['detailed_remarks']}}</div>
							</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','information_documents') || $check_field('add','information_documents') || $check_field('set','information_documents')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="动漫文件" prop="information_documents">
												<div v-if="disabledObj['information_documents_isDisabled']">
						<div v-if="$check_field('get','information_documents')">
							<el-button type="primary" @click="$download($fullUrl(form['information_documents']),form['information_documents'])">下载<i
									class="el-icon-download el-icon--right"></i></el-button>
						</div>
					</div>
					<div v-else>
						<el-upload v-if="user_group === '管理员' || (form['reference_information_id'] && $check_field('set','information_documents')) || (!form['reference_information_id'] && $check_field('add','information_documents'))" class="upload-demo" drag
								   action="" style="max-width: 300px;width: 100%;" :http-request="upload_information_documents" :limit="1" accept="">
							<i class="el-icon-upload"></i>
							<div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
						</el-upload>
						<div v-else-if="$check_field('get','information_documents')">
							<el-button type="primary" @click="$download($fullUrl(form['information_documents']),form['information_documents'])">下载<i
									class="el-icon-download el-icon--right"></i></el-button>
						</div>
					</div>
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
				field: "reference_information_id",
				url_add: "~/api/reference_information/add?",
				url_set: "~/api/reference_information/set?",
				url_get_obj: "~/api/reference_information/get_obj?",
				url_upload: "~/api/reference_information/upload?",

				query: {
					"reference_information_id": 0,
				},

				form: {
								"data_name":  '', // 动漫名称
										"data_type":  '', // 动漫类型
										"reference_integral":  0, // 参考积分
										"reference_user": 0, // 参考用户
										"user_name":  '', // 用户姓名
										"reference_time":  '', // 参考时间
										"detailed_remarks":  '', // 详情备注
										"information_documents":  '', // 动漫文件
											"reference_information_id": 0, // ID

				},
				disabledObj:{
								"data_name_isDisabled": false,
										"data_type_isDisabled": false,
					          			"reference_integral_isDisabled": false,
										"reference_user_isDisabled": false,
										"user_name_isDisabled": false,
										"reference_time_isDisabled": false,
										"detailed_remarks_isDisabled": false,
										"information_documents_isDisabled": false,
										},





					// 用户列表
				list_user_reference_user: [],
						// 用户组
				group_user_reference_user: "",





			}
		},
		methods: {










				/**
			 * 获取普通用户用户列表
			 */
			async get_list_user_reference_user() {
                // if(this.user_group !== "管理员" && this.form["reference_user"] === 0) {
                //     this.form["reference_user"] = this.user.user_id;
                // }
                var json = await this.$get("~/api/user/get_list?user_group=普通用户");
                if(json.result && json.result.list){
                    this.list_user_reference_user = json.result.list;
                }
                else if(json.error){
                    console.error(json.error);
                }
			},
					/**
			 * 获取普通用户用户组
			 */
			async get_group_user_reference_user() {
							this.form["reference_user"] = this.user.user_id;
							var json = await this.$get("~/api/user_group/get_obj?name=普通用户");
				if(json.result && json.result.obj){
					this.group_user_reference_user = json.result.obj;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
			get_user_session_reference_user(id){
				var _this = this;
				var user_id = {"user_id":id}
				var url = "~/api/"+_this.group_user_reference_user.source_table+"/get_obj?"
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
												_this.form["reference_user"] = id
									_this.disabledObj['reference_user' + '_isDisabled'] = true
						for (var i=0;i<arr.length;i++){
						  if (arr[i]!=='examine_state' && arr[i]!=='examine_reply') {
							for (var j = 0; j < arrForm.length; j++) {
							  if (arr[i] === arrForm[j]) {
								if (arr[i] !== "reference_user") {
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
					get_user_reference_user(id){
				var obj = this.list_user_reference_user.getObj({"user_id":id});
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
			 * 上传动漫文件
			 * @param {Object} param 文件参数
			 */
			upload_information_documents(param){
						this.uploadFile(param.file, "information_documents");
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
												        if (this.form["reference_time"].indexOf("-")===-1){
          this.form["reference_time"] = this.$toTime(parseInt(this.form["reference_time"]),"yyyy-MM-dd")
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


																	        if(this.form["reference_time"]=="0000-00-00"){
          this.form["reference_time"] = null;
        }
				if(parseInt(this.form["reference_time"]) > 9999){
					this.form["reference_time"] = this.$toTime(parseInt(this.form["reference_time"]),"yyyy-MM-dd")
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
					bl = this.$check_action('/reference_information/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/reference_information/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/reference_information/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/reference_information/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/reference_information/view','get');
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
											this.get_list_user_reference_user();
					this.get_group_user_reference_user();
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
