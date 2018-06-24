package cn.edu.ycu.impl;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.ycu.dao.ShareDao;
import cn.edu.ycu.entity.Note;
import cn.edu.ycu.entity.NoteResult;
import cn.edu.ycu.entity.Share;
import cn.edu.ycu.entity.ShareBean;
import cn.edu.ycu.service.ShareService;
import cn.edu.ycu.util.NoteUtil;
@Service("shareService")
public class ShareImpl implements ShareService{
	@Resource
	private ShareDao shareDao;
	/**
	 * 添加分享
	 * @param result
	 * @return
	 */
	@Transactional
	public NoteResult addShare(NoteResult result) {
		if(result.getStatus() == 0){
			Note note = (Note)result.getData();
			if(note != null){
				Share share = new Share();
				share.setYcu_note_id(note.getYcu_note_id());
				share.setYcu_user_id(note.getYcu_user_id());
				share.setYcu_share_title(note.getYcu_note_title());
				share.setYcu_share_body(note.getYcu_note_body());
				share.setYcu_share_id(NoteUtil.createId());
				if(shareDao.saveShare(share) == 1){
					return result;
				}
				result.setStatus(4);
				result.setMsg("插入条目不唯一!");
				return result;
			}
			result.setStatus(3);
			result.setMsg("该笔记不存在");
		}
		result.setData(null);
		return result;
//		NoteResult result = new NoteResult();
//		if(note == null){
//			result.setStatus(2);
//			result.setMsg("该笔记不存在!");
//			return result;
//		}
//		Share share = new Share();
//		share.setCn_note_id(note.getCn_note_id());
//		share.setYcu_user_id(note.getCn_user_id());
//		share.setYcu_share_title(note.getCn_note_title());
//		share.setYcu_share_body(note.getCn_note_body());
//		share.setYcu_share_id(NoteUtil.createId());
//		if(shareDao.saveShare(share) != 1){
//			result.setStatus(3);
//			result.setMsg("插入条目不唯一!");
//			return result;
//		}
//		result.setStatus(0);
//		result.setMsg("分享成功!");
//		return result;
	}
	/**
	 * 加载分享列表(non-Javadoc)
	 * @see cn.edu.ycu.service.ShareService#loadShares(java.lang.String, int)
	 */
	@Transactional(readOnly = true)
	public NoteResult loadShares(String shareTitle, int page) {
		NoteResult result = new NoteResult();
		ShareBean shareBean = new ShareBean();
		int count = shareBean.getCount();
		int begin = (page - 1) * count;
		if(shareTitle != null){
			shareBean.setShareTitle("%" + shareTitle + "%");
		}
		shareBean.setBegin(begin);
		shareBean.setCount(count);
		List<Share> shareList = shareDao.findShares(shareBean);
		if(shareList.size() == 0){
			result.setStatus(1);
			result.setMsg("已加载全部搜索内容!");
			return result;
		}
		result.setStatus(0);
		result.setData(shareList);
		return result;
	}
	/**
	 * 加载分享内容(non-Javadoc)
	 * @see cn.edu.ycu.service.ShareService#loadShareBody(java.lang.String)
	 */
	@Transactional(readOnly = true)
	public NoteResult loadShareBody(String shareId, String userId) {
		NoteResult result = new NoteResult();
		ShareBean shareBean = new ShareBean();
		shareBean.setShareId(shareId);
		Share share = shareDao.findShare(shareBean);
		if(share == null){
			result.setStatus(1);
			result.setMsg("该笔记不存在!");
			return result;
		}
		if(userId != null){
			share.setYcu_user_id(userId);
		}
		result.setStatus(0);
		result.setData(share);
		return result;
	}
	/**
	 * 删除分享(non-Javadoc)
	 * @see cn.edu.ycu.service.ShareService#delShare(java.lang.String)
	 */
	@Transactional
	public NoteResult delShare(String shareId) {
		NoteResult result = new NoteResult();
		ShareBean shareBean = new ShareBean();
		shareBean.setShareId(shareId);
		Share share = shareDao.findShare(shareBean);
		if(share == null){
			result.setStatus(1);
			result.setMsg("该内容不存在!");
			return result;
		}
		String noteId = share.getYcu_note_id();
		if(shareDao.delShare(shareBean) > 1){
			result.setStatus(2);
			result.setMsg("删除条目不唯一!");
			return result;
		}
		result.setStatus(0);
		result.setMsg("删除分享成功!");
		result.setData(noteId);
		return result;
	}
}
