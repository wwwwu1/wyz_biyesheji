<template>
	<div class="diy_edit page_reporting_information" id="reporting_information_edit">
		<div class='warp'>
			<div class='container'>
				<div class='row diy_edit_content_box'>
						<div v-if="$check_field('set','forum_title') || $check_field('add','forum_title') || $check_field('get','forum_title')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								论坛标题:
							</span>
						</div>
								<!-- 文本 -->
									<div class="diy_field diy_text">
							<input type="text" id="form_forum_title" v-model="form['forum_title']" placeholder="请输入论坛标题" v-if="(form['forum_title'] && $check_field('set','forum_title')) || (!form['forum_title'] && $check_field('add','forum_title'))"  :disabled="disabledObj['forum_title_isDisabled']"/>
							<span v-else-if="$check_field('get','forum_title')">{{ form['${obj.name}'] }}</span>
						</div>
										</div>
							<div v-if="$check_field('set','forum_type') || $check_field('add','forum_type') || $check_field('get','forum_type')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								论坛类型:
							</span>
						</div>
								<!-- 文本 -->
									<div class="diy_field diy_text">
							<input type="text" id="form_forum_type" v-model="form['forum_type']" placeholder="请输入论坛类型" v-if="(form['forum_type'] && $check_field('set','forum_type')) || (!form['forum_type'] && $check_field('add','forum_type'))"  :disabled="disabledObj['forum_type_isDisabled']"/>
							<span v-else-if="$check_field('get','forum_type')">{{ form['${obj.name}'] }}</span>
						</div>
										</div>
							<div v-if="$check_field('set','publishing_users') || $check_field('add','publishing_users') || $check_field('get','publishing_users')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								发布用户:
							</span>
						</div>
						<div class="diy_field diy_down">
							<select id="form_publishing_users" :disabled="disabledObj['publishing_users_isDisabled']" v-model="form['publishing_users']" v-if="(form['publishing_users'] && $check_field('set','publishing_users')) || (!form['publishing_users'] && $check_field('add','publishing_users'))" >
								<option v-for="o in list_user_publishing_users" :value="o['user_id']">
									{{o['nickname'] + '-' + o['username']}}
								</option>
							</select>
							<span v-else-if="$check_field('get','publishing_users')">{{ form['publishing_users'] }}</span>
						</div>
					</div>
							<div v-if="$check_field('set','report_users') || $check_field('add','report_users') || $check_field('get','report_users')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								举报用户:
							</span>
						</div>
						<div class="diy_field diy_down">
							<select id="form_report_users" :disabled="disabledObj['report_users_isDisabled']" v-model="form['report_users']" v-if="(form['report_users'] && $check_field('set','report_users')) || (!form['report_users'] && $check_field('add','report_users'))" >
								<option v-for="o in list_user_report_users" :value="o['user_id']">
									{{o['nickname'] + '-' + o['username']}}
								</option>
							</select>
							<span v-else-if="$check_field('get','report_users')">{{ form['report_users'] }}</span>
						</div>
					</div>
							<div v-if="$check_field('set','reporting_time') || $check_field('add','reporting_time') || $check_field('get','reporting_time')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								举报时间:
							</span>
						</div>
								<!-- 日期 -->
						<div class="diy_field diy_date">
							<input type="date" :disabled="disabledObj['reporting_time_isDisabled']" id="form_reporting_time" v-model="form['reporting_time']" placeholder="请输入举报时间" v-if="(form['reporting_time'] && $check_field('set','reporting_time')) || (!form['reporting_time'] && $check_field('add','reporting_time'))" />
							<span v-else-if="$check_field('get','reporting_time')">{{ form['${obj.name}'] }}</span>
						</div>
							</div>
							<div v-if="$check_field('set','report_content') || $check_field('add','report_content') || $check_field('get','report_content')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								举报内容:
							</span>
						</div>
								<!-- 多文本 -->
						<div class="diy_field diy_desc">
							<textarea id="form_report_content" v-model="form['report_content']" v-if="(form['report_content'] && $check_field('set','report_content')) || (!form['report_content'] && $check_field('add','report_content'))" :disabled="disabledObj['report_content_isDisabled']" />
							<span v-else-if="$check_field('get','report_content')">{{ form['${obj.name}'] }}</span>
						</div>
							</div>
	


					<div v-if="user_group === '管理员' || $check_examine()" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								审核状态:
							</span>
						</div>
						<div class="diy_field diy_select" v-if="$check_action('/reporting_information/edit','examine')">
							<!--<span> {{ form['examine_state'] }} </span>-->
							<select v-model="form['examine_state']">
								<option value="未审核">
									未审核
								</option>
								<option value="已通过">
									已通过
								</option>
								<option value="未通过">
									未通过
								</option>
							</select>
						</div>
						<div class="diy_field diy_text" v-else>
							<span>
								{{ form['examine_state'] }}
							</span>
						</div>
					</div>
					<div v-if="user_group === '管理员' || $check_examine()" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								审核回复:
							</span>
						</div>
						<div class="diy_field diy_desc" v-if="$check_action('/reporting_information/edit','examine')">
							<textarea v-model="form['examine_reply']"></textarea>
						</div>
						<div class="diy_field diy_text" v-else>
							<span>
								{{ form['examine_reply'] }}
							</span>
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
				url_get_obj: "~/api/reporting_information/get_obj?",
				url_add: "~/api/reporting_information/add?",
				url_set: "~/api/reporting_information/set?",

				// 登录权限
				oauth: {
					"signIn": true,
					"user_group": []
				},

				// 查询条件
				query: {
						"forum_title": "",
							"forum_type": "",
							"publishing_users": 0,
							"report_users": 0,
							"reporting_time": "",
							"report_content": "",
						"reporting_information_id": 0,
				},

				obj: {
						"forum_title":  '', // 论坛标题
							"forum_type":  '', // 论坛类型
							"publishing_users": 0, // 发布用户
							"report_users": 0, // 举报用户
							"reporting_time": new Date().getTime(),
							"report_content":  '', // 举报内容
						"examine_state": "未审核",
					"examine_reply": "",
					"reporting_information_id": 0,
				},

				// 表单字段
				form: {
						"forum_title":  '', // 论坛标题
							"forum_type":  '', // 论坛类型
							"publishing_users": 0, // 发布用户
							"report_users": 0, // 举报用户
							"reporting_time": new Date().getTime(),
							"report_content":  '', // 举报内容
						"examine_state": "未审核",
					"examine_reply": "",
					"reporting_information_id": 0,
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
					
				// ID字段
				field: "reporting_information_id",

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
					async get_user_session_report_users(){
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
								_this.form["report_users"] = user_id
								_this.disabledObj['report_users' + '_isDisabled'] = true
								for (var i=0;i<arr.length;i++){
                  if (arr[i]!=='examine_state' && arr[i]!=='examine_reply') {
                    for (var j = 0; j < arrForm.length; j++) {
                      if (arr[i] === arrForm[j]) {
                        if (arr[i] !== "report_users") {
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
				this.$post("~/api/reporting_information/upload?", form, (res) => {
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
										        if (JSON.stringify(this.form["reporting_time"]).indexOf("-")===-1) {
          this.form["reporting_time"] = this.$toTime(parseInt(this.form["reporting_time"]), "yyyy-MM-dd")
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
												this.get_list_user_publishing_users();
						this.get_user_session_report_users();
					this.get_list_user_report_users();
									},
	}
</script>

<style>




</style>
