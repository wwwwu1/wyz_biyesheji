<template>
	<div class="diy_edit page_sign_in_information" id="sign_in_information_edit">
		<div class='warp'>
			<div class='container'>
				<div class='row diy_edit_content_box'>
						<div v-if="$check_field('set','sign_in_user') || $check_field('add','sign_in_user') || $check_field('get','sign_in_user')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								签到用户:
							</span>
						</div>
						<div class="diy_field diy_down">
							<select id="form_sign_in_user" :disabled="disabledObj['sign_in_user_isDisabled']" v-model="form['sign_in_user']" v-if="(form['sign_in_user'] && $check_field('set','sign_in_user')) || (!form['sign_in_user'] && $check_field('add','sign_in_user'))" >
								<option v-for="o in list_user_sign_in_user" :value="o['user_id']">
									{{o['nickname'] + '-' + o['username']}}
								</option>
							</select>
							<span v-else-if="$check_field('get','sign_in_user')">{{ form['sign_in_user'] }}</span>
						</div>
					</div>
							<div v-if="$check_field('set','user_name') || $check_field('add','user_name') || $check_field('get','user_name')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								用户姓名:
							</span>
						</div>
								<!-- 文本 -->
									<div class="diy_field diy_text">
							<input type="text" id="form_user_name" v-model="form['user_name']" placeholder="请输入用户姓名" v-if="(form['user_name'] && $check_field('set','user_name')) || (!form['user_name'] && $check_field('add','user_name'))"  :disabled="disabledObj['user_name_isDisabled']"/>
							<span v-else-if="$check_field('get','user_name')">{{ form['${obj.name}'] }}</span>
						</div>
										</div>
							<div v-if="$check_field('set','sign_in_date') || $check_field('add','sign_in_date') || $check_field('get','sign_in_date')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								签到日期:
							</span>
						</div>
								<!-- 日期 -->
						<div class="diy_field diy_date">
							<input type="date" :disabled="disabledObj['sign_in_date_isDisabled']" id="form_sign_in_date" v-model="form['sign_in_date']" placeholder="请输入签到日期" v-if="(form['sign_in_date'] && $check_field('set','sign_in_date')) || (!form['sign_in_date'] && $check_field('add','sign_in_date'))" />
							<span v-else-if="$check_field('get','sign_in_date')">{{ form['${obj.name}'] }}</span>
						</div>
							</div>
							<div v-if="$check_field('set','sign_in_remarks') || $check_field('add','sign_in_remarks') || $check_field('get','sign_in_remarks')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								签到备注:
							</span>
						</div>
								<!-- 多文本 -->
						<div class="diy_field diy_desc">
							<textarea id="form_sign_in_remarks" v-model="form['sign_in_remarks']" v-if="(form['sign_in_remarks'] && $check_field('set','sign_in_remarks')) || (!form['sign_in_remarks'] && $check_field('add','sign_in_remarks'))" :disabled="disabledObj['sign_in_remarks_isDisabled']" />
							<span v-else-if="$check_field('get','sign_in_remarks')">{{ form['${obj.name}'] }}</span>
						</div>
							</div>
	




				</div>
				<div class="diy_edit_submit_box row">
					<div class="col-12">
						<div class="btn_box">
							<button class="btn_submit" @click="submit()">提交</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
	import mixin from "@/mixins/page.js";
	export default {
		mixins: [mixin],
		components: {},
		data() {
			return {
				url_get_obj: "~/api/sign_in_information/get_obj?",
				url_add: "~/api/sign_in_information/add?",
				url_set: "~/api/sign_in_information/set?",

				// 登录权限
				oauth: {
					"signIn": true,
					"user_group": []
				},

				// 查询条件
				query: {
						"sign_in_user": 0,
							"user_name": "",
							"sign_in_date": "",
							"sign_in_remarks": "",
						"sign_in_information_id": 0,
				},

				obj: {
						"sign_in_user": 0, // 签到用户
							"user_name":  '', // 用户姓名
							"sign_in_date": new Date().getTime(),
							"sign_in_remarks":  '', // 签到备注
						"sign_in_information_id": 0,
				},

				// 表单字段
				form: {
						"sign_in_user": 0, // 签到用户
							"user_name":  '', // 用户姓名
							"sign_in_date": new Date().getTime(),
							"sign_in_remarks":  '', // 签到备注
						"sign_in_information_id": 0,
				},
				disabledObj:{
						"sign_in_user_isDisabled": false,
							"user_name_isDisabled": false,
							"sign_in_date_isDisabled": false,
							"sign_in_remarks_isDisabled": false,
					},

						// 用户列表
				list_user_sign_in_user: [],
							
				// ID字段
				field: "sign_in_information_id",

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
					async get_user_session_sign_in_user(){
				var _this = this;
				var json = await this.$get("~/api/user_group/get_obj?name=普通用户");
				if(json.result && json.result.obj){
					var source_table = json.result.obj.source_table;
					var user_id = _this.$store.state.user.user_id;
					if (user_id){
						var url = "~/api/"+source_table+"/get_obj?"
						this.$get(url, {"user_id":_this.$store.state.user.user_id}, function(res) {
							if (res.result && res.result.obj) {
								var arr = []
								for (let key in res.result.obj) {
									arr.push(key)
								}
								var arrForm = []
								for (let key in _this.form) {
									arrForm.push(key)
								}
								_this.form["sign_in_user"] = user_id
								_this.disabledObj['sign_in_user' + '_isDisabled'] = true
								for (var i=0;i<arr.length;i++){
                  if (arr[i]!=='examine_state' && arr[i]!=='examine_reply') {
                    for (var j = 0; j < arrForm.length; j++) {
                      if (arr[i] === arrForm[j]) {
                        if (arr[i] !== "sign_in_user") {
                          _this.form[arrForm[j]] = res.result.obj[arr[i]]
                          _this.disabledObj[arrForm[j] + '_isDisabled'] = true
                          break;
                        }
                      }
                    }
                  }
								}
							}
						});
					}
				}
				else if(json.error){
					console.error(json.error);
				}
			},
	
				
				
				
	
			/**
			 * 修改文件
			 * @param { Object } files 上传文件对象
			 * @param { String } str 表单的属性名
			 */
			change_file(files, str) {
				var form = new FormData();
				form.append("file", files[0]);
				this.$post("~/api/sign_in_information/upload?", form, (res) => {
					if (res.result) {
						this.form[str] = res.result.url;
					} else if (res.error) {
						this.$toast(res.error.message);
					}
				});
			},

			/**
			 * 获取对象后获取缓存表单
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_before(param){
				var form = $.db.get("form");
				// if (form) {
        //   delete(form.examine_state)
        //   delete(form.examine_reply)
        //   this.obj = $.push(this.obj ,form);
				// 	this.form = $.push(this.form ,form);
				// }
				// var arr = []
				// for (let key in form) {
				// 	arr.push(key)
				// }
				// for (var i=0;i<arr.length;i++){
				// 	this.disabledObj[arr[i] + '_isDisabled'] = true
				// }
        if (form) {
          var arr = []
          for (let key in form) {
            arr.push(key)
          }
          var arrForm = []
          for (let key in this.form) {
            arrForm.push(key)
          }
          for (var i=0;i<arr.length;i++){
            if (arr[i]!=='examine_state' && arr[i]!=='examine_reply') {
              for (var j = 0; j < arrForm.length; j++) {
                if (arrForm[j] === arr[i]) {
                  this.form[arrForm[j]] = form[arr[i]]
                  this.obj[arrForm[j]] = form[arr[i]]
                  this.disabledObj[arrForm[j] + '_isDisabled'] = true
                  break;
                }
              }
            }
          }
        }
						        if (JSON.stringify(this.form["sign_in_date"]).indexOf("-")===-1) {
          this.form["sign_in_date"] = this.$toTime(parseInt(this.form["sign_in_date"]), "yyyy-MM-dd")
        }
			
        $.db.del("form");
				return param;
			},

			/**
			 * 获取对象后获取缓存表单
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_after(json ,func){
				// var form = $.db.get("form");
				// var obj = Object.assign({} ,form ,this.obj);
				// if (obj) {
        //   delete(obj.examine_state)
        //   delete(obj.examine_reply)
				// 	this.obj = $.push(this.obj ,obj);
				// }
				// if (form) {
        //   delete(form.examine_state)
        //   delete(form.examine_reply)
				// 	this.form = $.push(this.form ,form);
				// }
				if(func){
					func(json);
				}
			},

		},
		created() {
					this.get_user_session_sign_in_user();
					this.get_list_user_sign_in_user();
												},
	}
</script>

<style>




</style>
