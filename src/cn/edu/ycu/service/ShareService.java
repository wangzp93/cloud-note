package cn.edu.ycu.service;

import cn.edu.ycu.entity.NoteResult;

public interface ShareService {
	public NoteResult addShare(NoteResult result);
	public NoteResult loadShares(String shareTitle, int page);
	public NoteResult loadShareBody(String shareId, String userId);
	public NoteResult delShare(String shareId);
}
