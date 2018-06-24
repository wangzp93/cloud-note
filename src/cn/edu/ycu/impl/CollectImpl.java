package cn.edu.ycu.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.ycu.dao.CollectDao;
import cn.edu.ycu.entity.Collect;
import cn.edu.ycu.entity.CollectBean;
import cn.edu.ycu.entity.NoteResult;
import cn.edu.ycu.entity.Share;
import cn.edu.ycu.service.CollectService;

@Service("collectService")
public class CollectImpl implements CollectService{
	@Resource
	private CollectDao collectDao;
	/**
	 * 添加收藏(non-Javadoc)
	 * @see cn.edu.ycu.service.CollectService#addCollect(cn.edu.ycu.entity.Share, java.lang.String)
	 */
	@Transactional
	public NoteResult addCollect(NoteResult result) {
		Share share = (Share)result.getData();
		CollectBean collectBean = new CollectBean();
		String collectId = share.getYcu_share_id() + "+" + share.getYcu_user_id();
		collectBean.setCollectId(collectId);
		if(collectDao.findCollect(collectBean) != null){
			result.setStatus(2);
			result.setMsg("该笔记已收藏!");
			return result;
		}
		Collect collect = new Collect();
		collect.setYcu_user_id(share.getYcu_user_id());
		collect.setYcu_collect_title(share.getYcu_share_title());
		collect.setYcu_collect_body(share.getYcu_share_body());
		collect.setYcu_collect_id(collectId);
		if(collectDao.saveCollect(collect) != 1){
			result.setStatus(3);
			result.setMsg("更新条目不唯一!");
			return result;
		}
		result.setStatus(0);
		result.setMsg("收藏成功!");
		result.setData(null);
		return result;
	}
	/**
	 * 加载收藏列表(non-Javadoc)
	 * @see cn.edu.ycu.service.CollectService#loadCollects(java.lang.String)
	 */
	@Transactional(readOnly = true)
	public NoteResult loadCollects(String userId) {
		NoteResult result = new NoteResult();
		CollectBean collectBean = new CollectBean();
		collectBean.setUserId(userId);
		List<Collect> collects = collectDao.findCollects(collectBean);
		result.setStatus(0);
		result.setData(collects);
		return result;
	}
	/**
	 * 加载收藏内容(non-Javadoc)
	 * @see cn.edu.ycu.service.CollectService#loadCollectBody(java.lang.String)
	 */
	@Transactional(readOnly = true)
	public NoteResult loadCollectBody(String collectId) {
		NoteResult result = new NoteResult();
		CollectBean collectBean = new CollectBean();
		collectBean.setCollectId(collectId);
		Collect collect = collectDao.findCollect(collectBean);
		if(collect == null){
			result.setStatus(1);
			result.setMsg("该条目不存在!");
			return result;
		}
		result.setStatus(0);
		result.setData(collect);
		return result;
	}
	/**
	 * 删除收藏(non-Javadoc)
	 * @see cn.edu.ycu.service.CollectService#delCollect(java.lang.String)
	 */
	@Transactional
	public NoteResult delCollect(String collectId) {
		NoteResult result = new NoteResult();
		CollectBean collectBean = new CollectBean();
		collectBean.setCollectId(collectId);
		if(collectDao.findCollect(collectBean) == null){
			result.setStatus(1);
			result.setMsg("该条目不存在!");
			return result;
		}
		if(collectDao.delCollect(collectBean) > 1){
			result.setStatus(2);
			result.setMsg("删除条目不唯一!");
			return result;
		}
		result.setStatus(0);
		result.setMsg("删除收藏成功!");
		return result;
	}
}
