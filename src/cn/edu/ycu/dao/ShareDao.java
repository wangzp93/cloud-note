package cn.edu.ycu.dao;

import java.util.List;

import cn.edu.ycu.entity.Share;
import cn.edu.ycu.entity.ShareBean;

public interface ShareDao {
	public int saveShare(Share share);
	public List<Share> findShares(ShareBean shareBean);
	public Share findShare(ShareBean shareBean);
	public int delShare(ShareBean shareBean);
}
