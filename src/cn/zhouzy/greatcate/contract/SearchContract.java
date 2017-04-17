package cn.zhouzy.greatcate.contract;

import cn.zhouzy.greatcate.common.callback.CommonCallback;

public interface SearchContract
{
	interface ISearchPresenter
	{
		/**
		 * 通过菜谱名搜索
		 * @param name
		 * 菜谱名
		 * @param pn
		 * 分页下标
		 * @param rn
		 * 返回条数 最大30
		 */
		void searchByMenu(String name, String pn, String rn);
	}

	interface ISearchModel
	{
		void searchByMenu(String name, String pn, String rn, CommonCallback callback);
	}
}
