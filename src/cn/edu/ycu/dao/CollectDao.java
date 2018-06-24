package cn.edu.ycu.dao;

import java.util.List;

import cn.edu.ycu.entity.Collect;
import cn.edu.ycu.entity.CollectBean;

public interface CollectDao {
	public int saveCollect(Collect collect);
	public List<Collect> findCollects(CollectBean collectBean);
	public Collect findCollect(CollectBean collectBean);
	public int delCollect(CollectBean collectBean);
}
