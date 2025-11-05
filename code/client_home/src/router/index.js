import Vue from 'vue';
import VueRouter from 'vue-router';
import index from '../views/index.vue'
import login from '../views/account/login.vue';

Vue.use(VueRouter)

const routes = [
	// 主页ss
	{
		path: '/',
		name: 'index',
		component: index
	},
	// 登录
	{
		path: '/account/login',
		name: 'login',
		component: login
	},
	// 忘记密码
	{
		path: '/account/forgot',
		name: 'forgot',
		component: () => import('../views/account/forgot.vue')
	},
	// 注册账号
	{
		path: '/account/register',
		name: 'register',
		component: () => import('../views/account/register.vue')
	},
	// 媒体图片
	{
		path: '/media/image',
		name: 'media_image',
		component: () => import('../views/media/image.vue')
	},
	// 音乐
	{
		path: '/media/music',
		name: 'media_music',
		component: () => import('../views/media/music.vue')
	},
	// 媒体视频
	{
		path: '/media/video',
		name: 'media_video',
		component: () => import('../views/media/video.vue')
	},
	// 文章路由
	{
		path: '/article/list',
		name: 'article_list',
		component: () => import('../views/article/list.vue')
	},
	{
		path: '/article/details',
		name: 'article_details',
		component: () => import('../views/article/details.vue')
	},
	// 浏览网站
	// 收藏路由
	{
		path: '/user/collect',
		name: 'collect_list',
		component: () => import('../views/user/collect.vue')
	},






	// 公告路由
	{
		path: '/notice/list',
		name: 'notice_list',
		component: () => import('../views/notice/list.vue')
	},
	{
		path: '/notice/details',
		name: 'notice_details',
		component: () => import('../views/notice/details.vue')
	},





		// 动漫交流列表路由
	{
		path: '/exchange_forum/list',
		name: '/exchange_forum_list',
		component: () => import('../views/exchange_forum/list.vue')
	},

		// 动漫交流详情路由
	{
		path: '/exchange_forum/details',
		name: '/exchange_forum_details',
		component: () => import('../views/exchange_forum/details.vue')
	},
			// 举报信息添加路由
	{
		path: '/reporting_information/edit',
		name: '/reporting_information_edit',
		component: () => import('../views/reporting_information/edit.vue')
	},



		// 动漫视频列表路由
	{
		path: '/learning_materials/list',
		name: '/learning_materials_list',
		component: () => import('../views/learning_materials/list.vue')
	},

		// 动漫视频详情路由
	{
		path: '/learning_materials/details',
		name: '/learning_materials_details',
		component: () => import('../views/learning_materials/details.vue')
	},
			// 参考信息添加路由
	{
		path: '/reference_information/edit',
		name: '/reference_information_edit',
		component: () => import('../views/reference_information/edit.vue')
	},


			// 签到信息添加路由
	{
		path: '/sign_in_information/edit',
		name: '/sign_in_information_edit',
		component: () => import('../views/sign_in_information/edit.vue')
	},



	// 用户路由
	{
		path: '/user/index',
		name: 'user_index',
		component: () => import('../views/user/index.vue')
	},
	// 基本信息
	{
		path: '/user/info',
		name: 'user_info',
		component: () => import('../views/user/info.vue')
	},
	// 找回密码
	{
		path: '/user/password',
		name: 'user_password',
		component: () => import('../views/user/password.vue')
	},

	// 搜索
	{
		path: '/search',
		name: 'search',
		component: () => import('../views/search/index.vue')
	},
	// 局部搜索
	{
		path: '/search/details',
		name: 'search_details',
		component: () => import('../views/search/details.vue')
	}
]

const router = new VueRouter({
	mode: 'history',
	base: process.env.BASE_URL,
	routes
})

router.afterEach((to, from, next) => {
	let title = "在线动漫信息交流平台-home";
	document.title = title;
	document.logo = "在线动漫信息交流平台"
})

export default router
