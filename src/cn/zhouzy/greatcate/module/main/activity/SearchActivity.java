package cn.zhouzy.greatcate.module.main.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import butterknife.Bind;
import butterknife.OnClick;
import cn.zhouzy.greatcate.R;
import cn.zhouzy.greatcate.base.BaseActivity;

public class SearchActivity extends BaseActivity
{
	@Bind(R.id.lv_search_recent)
	ListView mRecentListView;
	@Bind(R.id.lv_search_result)
	ListView mResultListView;
	@Bind(R.id.rl_search_title_include_container)
	RelativeLayout mTitleContainerRelativeLayout;

	@Override
	protected void onCreate(Bundle arg0)
	{
		super.onCreate(arg0);
		setContentView(R.layout.activity_search);
		initView();
	}

	private void initView()
	{
		mTitleContainerRelativeLayout.getBackground().setAlpha(255);
	}

	@OnClick(
	{ R.id.btn_search_back, R.id.tv_item_search_hot_1, R.id.tv_item_search_hot_2,
			R.id.tv_item_search_hot_3 })
	void OnClick(View v)
	{
		switch (v.getId())
		{
		case R.id.btn_search_back:
			finish();
			break;

		default:
			break;
		}

	}

}
