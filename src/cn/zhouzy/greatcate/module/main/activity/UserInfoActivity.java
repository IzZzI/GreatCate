package cn.zhouzy.greatcate.module.main.activity;

import com.nostra13.universalimageloader.core.ImageLoader;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.OnClick;
import cn.bmob.v3.BmobUser;
import cn.zhouzy.greatcate.R;
import cn.zhouzy.greatcate.base.BaseActivity;
import cn.zhouzy.greatcate.base.BaseDialogFragment;
import cn.zhouzy.greatcate.common.utils.DialogUtil;
import cn.zhouzy.greatcate.common.utils.StrUtil;
import cn.zhouzy.greatcate.common.utils.ToastUtil;
import cn.zhouzy.greatcate.common.view.CircleImageView;
import cn.zhouzy.greatcate.common.view.pullview.CommPullToRefreshView;
import cn.zhouzy.greatcate.common.view.pullview.CommPullToRefreshView.OnHeaderRefreshListener;
import cn.zhouzy.greatcate.contract.UserInfoContract;
import cn.zhouzy.greatcate.entity.User;
import cn.zhouzy.greatcate.module.main.presenter.UserInfoPresenter;

public class UserInfoActivity extends BaseActivity implements UserInfoContract.IUserInfoView
{
	@Bind(R.id.tv_title)
	TextView mTitleTextView;
	@Bind(R.id.rl_title_include_container)
	RelativeLayout mTitleContainerRelativeLayout;
	@Bind(R.id.tv_user_info_account)
	TextView mAccountTextView;
	@Bind(R.id.tv_user_info_nickname)
	TextView mNicknameTextView;
	@Bind(R.id.tv_user_info_profile)
	TextView mProfileTextView;
	@Bind(R.id.civ_user_info_head_portrait)
	CircleImageView mHeadPortraitCircleImageView;
	@Bind(R.id.tv_user_info_email)
	TextView mEmailTextView;
	@Bind(R.id.refreshview_user_info_pulltorefreshview)
	CommPullToRefreshView mCommPullToRefreshView;
	private User mUser;
	private UserInfoContract.IUserInfoPresenter mUserInfoPresenter;
	private BaseDialogFragment mDialog;

	@Override
	protected void onCreate(Bundle arg0)
	{
		super.onCreate(arg0);
		setContentView(R.layout.activity_user_info);
		initView();
		initData();
		initListener();
	}

	private void initListener()
	{
		mCommPullToRefreshView.setOnHeaderRefreshListener(new OnHeaderRefreshListener()
		{
			@Override
			public void onHeaderRefresh(CommPullToRefreshView view)
			{
				mCommPullToRefreshView.onHeaderRefreshFinish();
			}
		});
	}

	private void initData()
	{
		mUser = BmobUser.getCurrentUser(User.class);
		if (mUser != null)
		{
			String mAccount = mUser.getUsername();
			String mHeadPortrait = mUser.getIcon();
			String mProfile = mUser.getProfile();
			String mNickname = mUser.getNickName();
			String mEmail = mUser.getEmail();
			if (!StrUtil.strIsNullOrEmpty(mAccount))
			{
				mAccountTextView.setText(mAccount);
			}

			if (!StrUtil.strIsNullOrEmpty(mProfile))
			{
				mProfileTextView.setText(mProfile);
			}

			if (!StrUtil.strIsNullOrEmpty(mNickname))
			{
				mNicknameTextView.setText(mNickname);
			}
			if (!StrUtil.strIsNullOrEmpty(mHeadPortrait))
			{
				ImageLoader.getInstance().displayImage(mHeadPortrait, mHeadPortraitCircleImageView);
			}
			if (!StrUtil.strIsNullOrEmpty(mEmail))
			{
				mEmailTextView.setText(mEmail);
			}

		}
	}

	private void refresh()
	{
		mUser = BmobUser.getCurrentUser(User.class);
		if (mUser != null)
		{
			String mAccount = mUser.getUsername();
			String mHeadPortrait = mUser.getIcon();
			String mProfile = mUser.getProfile();
			String mNickname = mUser.getNickName();
			String mEmail = mUser.getEmail();
			if (!StrUtil.strIsNullOrEmpty(mAccount))
			{
				mAccountTextView.setText(mAccount);
			} else
			{
				mAccountTextView.setText("");
			}

			if (!StrUtil.strIsNullOrEmpty(mProfile))
			{
				mProfileTextView.setText(mProfile);
			} else
			{
				mProfileTextView.setText(getResources().getString(R.string.profile_defualt));
			}

			if (!StrUtil.strIsNullOrEmpty(mNickname))
			{
				mNicknameTextView.setText(mNickname);
			} else
			{
				mNicknameTextView.setText("");
			}

			if (!StrUtil.strIsNullOrEmpty(mHeadPortrait))
			{
				ImageLoader.getInstance().displayImage(mHeadPortrait, mHeadPortraitCircleImageView);
			} else
			{
				mHeadPortraitCircleImageView.setImageResource(R.drawable.head_portrait);
			}
			if (!StrUtil.strIsNullOrEmpty(mEmail))
			{
				mEmailTextView.setText(mEmail);
			} else
			{
				mEmailTextView.setText("");
			}

		}
	}

	private void initView()
	{
		mTitleTextView.setText(getResources().getString(R.string.user_info));
		mTitleContainerRelativeLayout.getBackground().setAlpha(255);
		mUserInfoPresenter = new UserInfoPresenter(this);
	}

	@OnClick(
	{ R.id.rl_user_info_account_container, R.id.rl_user_info_email_container,
			R.id.rl_user_info_headportrait_container, R.id.rl_user_info_nickname_container,
			R.id.rl_user_info_profile_container, R.id.rl_user_info_qq_container,
			R.id.rl_user_info_wechat_container, R.id.rl_user_info_weibo_container, R.id.btn_back })
	void OnClick(View v)
	{
		switch (v.getId())
		{
		case R.id.btn_back:
			setResult(RESULT_OK);
			finish();
			break;
		case R.id.rl_user_info_nickname_container:
			modifyNickName();
			break;
		case R.id.rl_user_info_profile_container:
			modifyProfile();
			break;

		default:
			break;
		}
	}

	private void modifyProfile()
	{
		if (mUser != null)
		{
			String hintMsg = mUser.getProfile();
			mDialog = DialogUtil.showBaseDialog(this, false, new OnClickListener()
			{

				@Override
				public void onClick(DialogInterface dialog, int which)
				{
					if (mDialog != null && mUser != null)
					{
						String profile = mDialog.getEditTextMsg();
						String objectId = mUser.getObjectId();
						mUserInfoPresenter.modifyProfile(profile, objectId);

					}

				}
			}, getResources().getString(R.string.modify_profile), hintMsg);
		}
	}

	private void modifyNickName()
	{
		if (mUser != null)
		{
			String hintMsg = mUser.getNickName();
			mDialog = DialogUtil.showBaseDialog(this, false, new OnClickListener()
			{

				@Override
				public void onClick(DialogInterface dialog, int which)
				{
					if (mDialog != null && mUser != null)
					{
						String nickname = mDialog.getEditTextMsg();
						String objectId = mUser.getObjectId();
						mUserInfoPresenter.modifyNickname(nickname, objectId);

					}

				}
			}, getResources().getString(R.string.modify_nickname), hintMsg);
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		if (keyCode == KeyEvent.KEYCODE_BACK)
		{
			setResult(RESULT_OK);
			finish();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public void onModifySuccessed()
	{
		refresh();
	}

	@Override
	public void onModifyErrored(String errorMsg)
	{
		ToastUtil.showToast(this, errorMsg);
	}

}