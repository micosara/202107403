package kr.or.ddit.service.spring;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jsp.command.PageMaker;
import com.jsp.command.SearchCriteria;
import com.jsp.dto.ReplyVO;
import com.jsp.service.ReplyService;

import kr.or.ddit.dao.spring.ReplyDAOBean;

public class ReplyServiceImpl implements ReplyService{
	
	private ReplyDAOBean replyDAOBean;

	public void setReplyDAO(ReplyDAOBean replyDAOBean) {
		this.replyDAOBean = replyDAOBean;
	}

	@Override
	public Map<String, Object> getReplyList(int bno, SearchCriteria cri) throws SQLException {

		Map<String, Object> dataMap = new HashMap<String, Object>();

		List<ReplyVO> replyList = replyDAOBean.selectReplyListPage(bno, cri);

		int count = replyDAOBean.countReply(bno);

		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(count);

		dataMap.put("replyList", replyList);
		dataMap.put("pageMaker", pageMaker);

		return dataMap;
	}

	@Override
	public void registReply(ReplyVO reply) throws SQLException {
		int rno = replyDAOBean.selectReplySeqNextValue();
		reply.setRno(rno);

		replyDAOBean.insertReply(reply);

	}

	@Override
	public void modifyReply(ReplyVO reply) throws SQLException {

		replyDAOBean.updateReply(reply);

	}

	@Override
	public void removeReply(int rno) throws SQLException {

		replyDAOBean.deleteReply(rno);
	}

	@Override
	public int getReplyListCount(int bno) throws SQLException {
		int count = replyDAOBean.countReply(bno);
		return count;
	}

}
