package cn.zhouzy.greatcate.contract;

import cn.zhouzy.greatcate.common.callback.CommonCallback;

public interface LoginContract
{
	interface ILoginPresenter
	{
		/**
		 * 登录
		 * @param email 邮箱地址
		 * @param password 密码
		 */
		void login(String email, String password);

		void thirdPartyLogin(String name);
	}

	interface ILoginModel
	{

		void login(String email, String password, CommonCallback callback);

		void thirdPartyLogin(String name, CommonCallback callback);
	}

	interface ILoginVew
	{
		void onLoginSuccessed(Object object);

		void onLoginFailed(String errorMsg);
	}
}
