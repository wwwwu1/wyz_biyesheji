<template>
	<el-main class="bg edit_wrap">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">

							<el-col v-if="user_group === '管理员' || $check_field('get','sign_in_user') || $check_field('add','sign_in_user') || $check_field('set','sign_in_user')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="签到用户" prop="sign_in_user">
																		<div v-if="user_group !== '管理员'">
							{{ get_user_session_sign_in_user(form['sign_in_user']) }}
							<!--<el-input id="business_name" v-model="form['sign_in_user']" placeholder="请输入签到用户"-->
							<!--v-if="user_group === '管理员' || (form['sign_in_information_id'] && $check_field('set','sign_in_user')) || (!form['sign_in_information_id'] && $check_field('add','sign_in_user'))" :disabled="disabledObj['sign_in_user_isDisabled']"></el-input>-->
							<!--<div v-else-if="$check_field('get','sign_in_user')">{{form['sign_in_user']}}</div>-->
							<el-select v-if="user_group === '管理员' || (form['sign_in_information_id'] && $check_field('set','sign_in_user')) || (!form['sign_in_information_id'] && $check_field('add','sign_in_user'))" id="sign_in_user" v-model="form['sign_in_user']" :disabled="disabledObj['sign_in_user_isDisabled']">
								<el-option v-for="o in list_user_sign_in_user" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
										   :value="o['user_id']">
								</el-option>
							</el-select>
							<el-select v-else-if="$check_field('get','sign_in_user')" id="sign_in_user" v-model="form['sign_in_user']" :disabled="true">
								<el-option v-for="o in list_user_sign_in_user" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
										   :value="o['user_id']">
								</el-option>
							</el-select>
						</div>
						<el-select v-else id="sign_in_user" v-model="form['sign_in_user']" :disabled="disabledObj['sign_in_user_isDisabled']">
							<el-option v-for="o in list_user_sign_in_user" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
																</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','user_name') || $check_field('add','user_name') || $check_field('set','user_name')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="用户姓名" prop="user_name">
												<el-input id="user_name" v-model="form['user_name']" placeholder="请输入用户姓名"
							  v-if="user_group === '管理员' || (form['sign_in_information_id'] && $check_field('set','user_name')) || (!form['sign_in_information_id'] && $check_field('add','user_name'))" :disabled="disabledObj['user_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','user_name')">{{form['user_name']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','sign_in_date') || $check_field('add','sign_in_date') || $check_field('set','sign_in_date')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="签到日期" prop="sign_in_date">
								<el-date-picker :disabled="disabledObj['sign_in_date_isDisabled']" v-if="user_group === '管理员' || (form['sign_in_information_id'] && $check_field('set','sign_in_date')) || (!form['sign_in_information_id'] && $check_field('add','sign_in_date'))" id="sign_in_date"
						v-model="form['sign_in_date']" type="date" placeholder="选择日期">
					</el-date-picker>
					<div v-else-if="$check_field('get','sign_in_date')">{{form['sign_in_date']}}</div>
							</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','sign_in_remarks') || $check_field('add','sign_in_remarks') || $check_field('set','sign_in_remarks')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="签到备注" prop="sign_in_remarks">
								<el-input type="textarea" id="sign_in_remarks" v-model="form['sign_in_remarks']" placeholder="请输入签到备注"
						v-if="user_group === '管理员' || (form['sign_in_information_id'] && $check_field('set','sign_in_remarks')) || (!form['sign_in_information_id'] && $check_field('add','sign_in_remarks'))" :disabled="disabledObj['sign_in_remarks_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','sign_in_remarks')">{{form['sign_in_remarks']}}</div>
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
				field: "sign_in_information_id",
				url_add: "~/api/sign_in_information/add?",
				url_set: "~/api/sign_in_information/set?",
				url_get_obj: "~/api/sign_in_information/get_obj?",
				url_upload: "~/api/sign_in_information/upload?",

				query: {
					"sign_in_information_id": 0,
				},

				form: {
								"sign_in_user": 0, // 签到用户
										"user_name":  '', // 用户姓名
										"sign_in_date":  '', // 签到日期
										"sign_in_remarks":  '', // 签到备注
											"sign_in_information_id": 0, // ID
						
				},
				disabledObj:{
								"sign_in_user_isDisabled": false,
										"user_name_isDisabled": false,
										"sign_in_date_isDisabled": false,
										"sign_in_remarks_isDisabled": false,
										},

	
					// 用户列表
				list_user_sign_in_user: [],
						// 用户组
				group_user_sign_in_user: "",
				
		
		
	
			}
		},
		methods: {


	
	
				/**
			 * 获取普通用户用户列表
			 */
			async get_list_user_sign_in_user() {
                // if(this.user_group !== "管理员" && this.form["sign_in_user"] === 0) {
                //     this.form["sign_in_user"] = this.user.user_id;
                // }
                var json = await this.$get("~/api/user/get_list?user_group=普通用户");
                if(json.result && json.result.list){
                    this.list_user_sign_in_user = json.result.list;
                }
                else if(json.error){
                    console.error(json.error);
                }
			},
					/**
			 * 获取普通用户用户组
			 */
			async get_group_user_sign_in_user() {
							this.form["sign_in_user"] = this.user.user_id;
							var json = await this.$get("~/api/user_group/get_obj?name=普通用户");
				if(json.result && json.result.obj){
					this.group_user_sign_in_user = json.result.obj;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
			get_user_session_sign_in_user(id){
				var _this = this;
				var user_id = {"user_id":id}
				var url = "~/api/"+_this.group_user_sign_in_user.source_table+"/get_obj?"
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
												_this.form["sign_in_user"] = id
									_this.disabledObj['sign_in_user' + '_isDisabled'] = true
						for (var i=0;i<arr.length;i++){
						  if (arr[i]!=='examine_state' && arr[i]!=='examine_reply') {
							for (var j = 0; j < arrForm.length; j++) {
							  if (arr[i] === arrForm[j]) {
								if (arr[i] !== "sign_in_user") {
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
					get_user_sign_in_user(id){
				var obj = this.list_user_sign_in_user.getObj({"user_id":id});
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
						        if (this.form["sign_in_date"].indexOf("-")===-1){
          this.form["sign_in_date"] = this.$toTime(parseInt(this.form["sign_in_date"]),"yyyy-MM-dd")
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


								        if(this.form["sign_in_date"]=="0000-00-00"){
          this.form["sign_in_date"] = null;
        }
				if(parseInt(this.form["sign_in_date"]) > 9999){
					this.form["sign_in_date"] = this.$toTime(parseInt(this.form["sign_in_date"]),"yyyy-MM-dd")
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
					bl = this.$check_action('/sign_in_information/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/sign_in_information/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/sign_in_information/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/sign_in_information/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/sign_in_information/view','get');
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
					this.get_list_user_sign_in_user();
					this.get_group_user_sign_in_user();
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
