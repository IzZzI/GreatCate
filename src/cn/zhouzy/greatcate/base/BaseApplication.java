package cn.zhouzy.greatcate.base;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import android.app.Application;
import cn.bmob.v3.Bmob;
import cn.sharesdk.framework.ShareSDK;

/**
 * Created by gdxw on 2017/3/9.
 */

public class BaseApplication extends Application
{
	@Override
	public void onCreate()
	{
		super.onCreate();
		ImageLoaderConfiguration configuration = ImageLoaderConfiguration.createDefault(this);
		ImageLoader.getInstance().init(configuration);
		ShareSDK.initSDK(this);
		Bmob.initialize(this, "f76cd3c3080057cc0ae9918be21256a1");
	}
}
