package cn.edu.ycu.service;

import cn.edu.ycu.entity.NoteResult;

public interface CollectService {
	public NoteResult addCollect(NoteResult result);
	public NoteResult loadCollects(String userId);
	public NoteResult loadCollectBody(String collectId);
	public NoteResult delCollect(String collectId);
}
