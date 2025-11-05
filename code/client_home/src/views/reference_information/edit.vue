<template>
	<div class="diy_edit page_reference_information" id="reference_information_edit">
		<div class='warp'>
			<div class='container'>
				<div class='row diy_edit_content_box'>
						<div v-if="$check_field('set','data_name') || $check_field('add','data_name') || $check_field('get','data_name')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								动漫名称:
							</span>
						</div>
								<!-- 文本 -->
									<div class="diy_field diy_text">
							<input type="text" id="form_data_name" v-model="form['data_name']" placeholder="请输入动漫名称" v-if="(form['data_name'] && $check_field('set','data_name')) || (!form['data_name'] && $check_field('add','data_name'))"  :disabled="disabledObj['data_name_isDisabled']"/>
							<span v-else-if="$check_field('get','data_name')">{{ form['${obj.name}'] }}</span>
						</div>
										</div>
							<div v-if="$check_field('set','data_type') || $check_field('add','data_type') || $check_field('get','data_type')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								动漫类型:
							</span>
						</div>
								<!-- 文本 -->
									<div class="diy_field diy_text">
							<input type="text" id="form_data_type" v-model="form['data_type']" placeholder="请输入动漫类型" v-if="(form['data_type'] && $check_field('set','data_type')) || (!form['data_type'] && $check_field('add','data_type'))"  :disabled="disabledObj['data_type_isDisabled']"/>
							<span v-else-if="$check_field('get','data_type')">{{ form['${obj.name}'] }}</span>
						</div>
										</div>
							<div v-if="$check_field('set','reference_integral') || $check_field('add','reference_integral') || $check_field('get','reference_integral')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								参考积分:
							</span>
						</div>
								<!-- 数字 -->
						<div class="diy_field diy_number">
							<input type="number" id="form_reference_integral" v-model.number="form['reference_integral']" placeholder="请输入参考积分" v-if="(form['reference_integral'] && $check_field('set','reference_integral')) || (!form['reference_integral'] && $check_field('add','reference_integral'))" :disabled="disabledObj['reference_integral_isDisabled']" />
							<span v-else-if="$check_field('get','reference_integral')">{{ form['${obj.name}'] }}</span>
						</div>
							</div>
							<div v-if="$check_field('set','reference_user') || $check_field('add','reference_user') || $check_field('get','reference_user')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								参考用户:
							</span>
						</div>
						<div class="diy_field diy_down">
							<select id="form_reference_user" :disabled="disabledObj['reference_user_isDisabled']" v-model="form['reference_user']" v-if="(form['reference_user'] && $check_field('set','reference_user')) || (!form['reference_user'] && $check_field('add','reference_user'))" >
								<option v-for="o in list_user_reference_user" :value="o['user_id']">
									{{o['nickname'] + '-' + o['username']}}
								</option>
							</select>
							<span v-else-if="$check_field('get','reference_user')">{{ form['reference_user'] }}</span>
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
							<div v-if="$check_field('set','reference_time') || $check_field('add','reference_time') || $check_field('get','reference_time')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								参考时间:
							</span>
						</div>
								<!-- 日期 -->
						<div class="diy_field diy_date">
							<input type="date" :disabled="disabledObj['reference_time_isDisabled']" id="form_reference_time" v-model="form['reference_time']" placeholder="请输入参考时间" v-if="(form['reference_time'] && $check_field('set','reference_time')) || (!form['reference_time'] && $check_field('add','reference_time'))" />
							<span v-else-if="$check_field('get','reference_time')">{{ form['${obj.name}'] }}</span>
						</div>
							</div>
							<div v-if="$check_field('set','detailed_remarks') || $check_field('add','detailed_remarks') || $check_field('get','detailed_remarks')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								详情备注:
							</span>
						</div>
								<!-- 多文本 -->
						<div class="diy_field diy_desc">
							<textarea id="form_detailed_remarks" v-model="form['detailed_remarks']" v-if="(form['detailed_remarks'] && $check_field('set','detailed_remarks')) || (!form['detailed_remarks'] && $check_field('add','detailed_remarks'))" :disabled="disabledObj['detailed_remarks_isDisabled']" />
							<span v-else-if="$check_field('get','detailed_remarks')">{{ form['${obj.name}'] }}</span>
						</div>
							</div>
							<div v-if="$check_field('set','information_documents') || $check_field('add','information_documents') || $check_field('get','information_documents')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								动漫文件:
							</span>
						</div>
								<!-- 文件 -->
						<input type="file" style="display: none;" id="form_file_information_documents" title="information_documents" @change="change_file($event.target.files,'information_documents')"/>
						<!-- 修改权限 -->
						<div class="diy_field diy_img" v-if="form['information_documents'] && $check_field('set','information_documents')">
							<label for="form_file_information_documents">
								<!--<span>{{form['information_documents']}} </span>-->
								<a :href="$fullUrl(form['information_documents'])" target="_blank" style="color: rgb(64, 158, 255);">点击下载</a>
							</label>
						</div>
						<!-- 添加权限 -->
						<div class="diy_field diy_img" v-else-if="!form['information_documents'] && $check_field('add','information_documents')">
							<label for="form_file_information_documents">
								<div class="btn_add_img">
									<span>+</span>
								</div>
							</label>
						</div>
						<!-- 查询权限 -->
						<div class="diy_field diy_img" v-else-if="$check_field('get','information_documents')">
							<span>{{form['information_documents']}} </span>
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
				url_get_obj: "~/api/reference_information/get_obj?",
				url_add: "~/api/reference_information/add?",
				url_set: "~/api/reference_information/set?",

				// 登录权限
				oauth: {
					"signIn": true,
					"user_group": []
				},

				// 查询条件
				query: {
						"data_name": "",
							"data_type": "",
							"reference_integral": 0,
							"reference_user": 0,
							"user_name": "",
							"reference_time": "",
							"detailed_remarks": "",
							"information_documents": "",
						"reference_information_id": 0,
				},

				obj: {
						"data_name":  '', // 动漫名称
							"data_type":  '', // 动漫类型
							"reference_integral":  0, // 参考积分
							"reference_user": 0, // 参考用户
							"user_name":  '', // 用户姓名
							"reference_time": new Date().getTime(),
							"detailed_remarks":  '', // 详情备注
							"information_documents":  '', // 动漫文件
						"reference_information_id": 0,
				},

				// 表单字段
				form: {
						"data_name":  '', // 动漫名称
							"data_type":  '', // 动漫类型
							"reference_integral":  0, // 参考积分
							"reference_user": 0, // 参考用户
							"user_name":  '', // 用户姓名
							"reference_time": new Date().getTime(),
							"detailed_remarks":  '', // 详情备注
							"information_documents":  '', // 动漫文件
						"reference_information_id": 0,
				},
				disabledObj:{
						"data_name_isDisabled": false,
							"data_type_isDisabled": false,
									"reference_user_isDisabled": false,
							"user_name_isDisabled": false,
							"reference_time_isDisabled": false,
							"detailed_remarks_isDisabled": false,
							"information_documents_isDisabled": false,
					},

												// 用户列表
				list_user_reference_user: [],

				// ID字段
				field: "reference_information_id",

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
					async get_user_session_reference_user(){
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
								_this.form["reference_user"] = user_id
								_this.disabledObj['reference_user' + '_isDisabled'] = true
								for (var i=0;i<arr.length;i++){
                  if (arr[i]!=='examine_state' && arr[i]!=='examine_reply') {
                    for (var j = 0; j < arrForm.length; j++) {
                      if (arr[i] === arrForm[j]) {
                        if (arr[i] !== "reference_user") {
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
				this.$post("~/api/reference_information/upload?", form, (res) => {
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
												        if (JSON.stringify(this.form["reference_time"]).indexOf("-")===-1) {
          this.form["reference_time"] = this.$toTime(parseInt(this.form["reference_time"]), "yyyy-MM-dd")
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
														this.get_user_session_reference_user();
					this.get_list_user_reference_user();
															},
	}
</script>

<style>




</style>
