package cn.zhouzy.greatcate.contract;

import android.graphics.Bitmap;
import cn.zhouzy.greatcate.common.callback.CommonCallback;

public interface UserInfoContract
{
	interface IUserInfoPresenter
	{
		void modifyNickname(String nickname, String objectId);

		void modifyProfile(String profile, String objectId);

		void modifyHeadProtrait(Bitmap headProtrait, String objectId);

	}

	interface IUserInfoModel
	{
		void modifyNickname(String nickname, String objectId, CommonCallback callback);

		void modifyProfile(String profile, String objectId, CommonCallback callback);

		void modifyHeadProtrait(Bitmap headProtrait, String objectId, CommonCallback callback);
	}

	interface IUserInfoView
	{
		void onModifySuccessed();

		void onModifyErrored(String errorMsg);
	}
}
