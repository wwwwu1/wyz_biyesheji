<template>
	<el-main class="bg edit_wrap">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">

							<el-col v-if="user_group === '管理员' || $check_field('get','data_name') || $check_field('add','data_name') || $check_field('set','data_name')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="动漫名称" prop="data_name">
												<el-input id="data_name" v-model="form['data_name']" placeholder="请输入动漫名称"
							  v-if="user_group === '管理员' || (form['learning_materials_id'] && $check_field('set','data_name')) || (!form['learning_materials_id'] && $check_field('add','data_name'))" :disabled="disabledObj['data_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','data_name')">{{form['data_name']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','data_cover') || $check_field('add','data_cover') || $check_field('set','data_cover')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="动漫封面" prop="data_cover">
								<el-upload :disabled="disabledObj['data_cover_isDisabled']" id="data_cover" class="avatar-uploader" drag
						accept="image/gif, image/jpeg, image/png, image/jpg" action="" :http-request="upload_data_cover"
						:show-file-list="false" v-if="user_group === '管理员' || (form['learning_materials_id'] && $check_field('set','data_cover')) || (!form['learning_materials_id'] && $check_field('add','data_cover'))">
						<img v-if="form['data_cover']" :src="$fullUrl(form['data_cover'])" class="avatar">
						<i v-else class="el-icon-plus avatar-uploader-icon"></i>
					</el-upload>
					<el-image v-else-if="$check_field('get','data_cover')" style="width: 100px; height: 100px"
						:src="$fullUrl(form['data_cover'])" :preview-src-list="[$fullUrl(form['data_cover'])]">
						<div slot="error" class="image-slot">
							<img src="../../../public/img/error.png" style="width: 90px; height: 90px" />
						</div>
					</el-image>
							</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','data_type') || $check_field('add','data_type') || $check_field('set','data_type')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="动漫类型" prop="data_type">
												<el-input id="data_type" v-model="form['data_type']" placeholder="请输入动漫类型"
							  v-if="user_group === '管理员' || (form['learning_materials_id'] && $check_field('set','data_type')) || (!form['learning_materials_id'] && $check_field('add','data_type'))" :disabled="disabledObj['data_type_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','data_type')">{{form['data_type']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','data_introduction') || $check_field('add','data_introduction') || $check_field('set','data_introduction')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="动漫介绍" prop="data_introduction">
												<el-input id="data_introduction" v-model="form['data_introduction']" placeholder="请输入动漫介绍"
							  v-if="user_group === '管理员' || (form['learning_materials_id'] && $check_field('set','data_introduction')) || (!form['learning_materials_id'] && $check_field('add','data_introduction'))" :disabled="disabledObj['data_introduction_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','data_introduction')">{{form['data_introduction']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','information_video') || $check_field('add','information_video') || $check_field('set','information_video')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="动漫视频" prop="information_video">
												<el-upload v-if="user_group === '管理员' || (form['learning_materials_id'] && $check_field('set','information_video')) || (!form['learning_materials_id'] && $check_field('add','information_video'))" class="upload-demo" drag
						action="" style="max-width: 300px;width: 100%;" :http-request="upload_information_video" :limit="1" accept="video/ogg,video/mp4,video/webm">
						<i class="el-icon-upload"></i>
						<div class="el-upload__text">将视频拖到此处，或<em>点击上传</em></div>
					</el-upload>
					<div v-else-if="$check_field('get','information_video')">
						<el-button type="primary" @click="download(form['information_video'])">下载<i
								class="el-icon-download el-icon--right"></i></el-button>
					</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','reference_integral') || $check_field('add','reference_integral') || $check_field('set','reference_integral')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="参考积分" prop="reference_integral">
								<el-input-number id="reference_integral" v-model.number="form['reference_integral']"
						v-if="user_group === '管理员' || (form['learning_materials_id'] && $check_field('set','reference_integral')) || (!form['learning_materials_id'] && $check_field('add','reference_integral'))" :disabled="disabledObj['reference_integral_isDisabled']"></el-input-number>
					<div v-else-if="$check_field('get','reference_integral')">{{form['reference_integral']}}</div>
							</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','data_content') || $check_field('add','data_content') || $check_field('set','data_content')" :xs="24" :sm="24" :lg="24" class="el_form_editor_warp">
				<el-form-item label="动漫内容" prop="data_content">
					<quill-editor v-model.number="form['data_content']"
						v-if="user_group === '管理员' || (form['learning_materials_id'] && $check_field('set','data_content')) || (!form['learning_materials_id'] && $check_field('add','data_content')) ">
					</quill-editor>
					<div v-else-if="$check_field('get','data_content')" v-html="form['data_content']"></div>
				</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','note_details') || $check_field('add','note_details') || $check_field('set','note_details')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="备注详情" prop="note_details">
								<el-input type="textarea" id="note_details" v-model="form['note_details']" placeholder="请输入备注详情"
						v-if="user_group === '管理员' || (form['learning_materials_id'] && $check_field('set','note_details')) || (!form['learning_materials_id'] && $check_field('add','note_details'))" :disabled="disabledObj['note_details_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','note_details')">{{form['note_details']}}</div>
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
				field: "learning_materials_id",
				url_add: "~/api/learning_materials/add?",
				url_set: "~/api/learning_materials/set?",
				url_get_obj: "~/api/learning_materials/get_obj?",
				url_upload: "~/api/learning_materials/upload?",

				query: {
					"learning_materials_id": 0,
				},

				form: {
								"data_name":  '', // 动漫名称
										"data_cover":  '', // 动漫封面
										"data_type":  '', // 动漫类型
										"data_introduction":  '', // 动漫介绍
										"information_video":  '', // 动漫视频
										"reference_integral":  0, // 参考积分
										"data_content":  '', // 动漫内容
										"note_details":  '', // 备注详情
											"learning_materials_id": 0, // ID

				},
				disabledObj:{
								"data_name_isDisabled": false,
										"data_cover_isDisabled": false,
										"data_type_isDisabled": false,
										"data_introduction_isDisabled": false,
										"information_video_isDisabled": false,
					          			"reference_integral_isDisabled": false,
										"data_content_isDisabled": false,
										"note_details_isDisabled": false,
										},










			}
		},
		methods: {




						/**
			 * 上传动漫封面
			 * @param {Object} param 图片参数
			 */
			upload_data_cover(param){
						this.uploadFile(param.file, "data_cover");
					},






						/**
			 * 上传动漫视频
			 * @param {Object} param 视频参数
			 */
			upload_information_video(param){
						this.uploadFile(param.file, "information_video");
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
					bl = this.$check_action('/learning_materials/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/learning_materials/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/learning_materials/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/learning_materials/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/learning_materials/view','get');
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
