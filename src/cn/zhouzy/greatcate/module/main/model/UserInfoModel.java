package cn.zhouzy.greatcate.module.main.model;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;
import cn.zhouzy.greatcate.common.callback.CommonCallback;
import cn.zhouzy.greatcate.contract.UserInfoContract;
import cn.zhouzy.greatcate.entity.User;

public class UserInfoModel implements UserInfoContract.IUserInfoModel
{

	@Override
	public void modifyNickname(String nickname, String objectId, final CommonCallback callback)
	{
		User newUser = new User();
		newUser.setNickName(nickname);
		newUser.update(objectId, new UpdateListener()
		{

			@Override
			public void done(BmobException arg0)
			{
				if (arg0 == null)
				{
					callback.onSuccess(null);
				} else
				{
					callback.onFail(arg0.getMessage());
				}
			}
		});
	}

	@Override
	public void modifyProfile(String profile, String objectId, final CommonCallback callback)
	{
		User newUser = new User();
		newUser.setProfile(profile);
		newUser.update(objectId, new UpdateListener()
		{

			@Override
			public void done(BmobException arg0)
			{
				if (arg0 == null)
				{
					callback.onSuccess(null);
				} else
				{
					callback.onFail(arg0.getMessage());
				}
			}
		});

	}

	@Override
	public void modifyHeadProtrait(String headProtrait, String objectId, CommonCallback callback)
	{

	}

}
