package kr.or.ddit.users.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.ddit.admin.vo.QnAReplyVO;
import kr.or.ddit.common.enumpkg.ServiceResult;
import kr.or.ddit.paging.vo.PaginationInfo;
import kr.or.ddit.users.dao.QnADAO;
import kr.or.ddit.users.vo.QnAVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QnAServiceImpl implements QnAService{

	private final QnADAO qnaDAO;

	// 1:1문의 댓글 등록, 댓글여부 'Y'로변경
	@Override
	public int createPost(QnAReplyVO qnAReplyVO) {
		int result = qnaDAO.createPost(qnAReplyVO);
		
		if (result > 0) {
            String qstnNo = qnAReplyVO.getQstnNo();
            QnAVO qna = qnaDAO.selectQnA(qstnNo);
            if (qna != null) {
                // 문의글의 qstnReply를 'Y'로 업데이트
                qna.setQstnReply("Y");
                qnaDAO.updateRpy(qna);
            }
        }
		return result;
	}
	// 이상 태형코드
	
	@Override
	public ServiceResult createQnA(QnAVO qnaVO) {
		int cnt = qnaDAO.insertQnA(qnaVO);
		ServiceResult result = null;
		if(cnt>0) {
			result = ServiceResult.OK;
//			processQnAFiles(qnaVO);
		}else {
			result = ServiceResult.FAIL;
		}
		return result;
	}

	@Override
	public QnAVO retrieveQnA(String qstnNo) {
		return qnaDAO.selectQnA(qstnNo);
	}
	
	@Override
	public QnAReplyVO retrieveQnAReply(String qnstnNo) {
		return qnaDAO.selectQnAReply(qnstnNo);
	}

//	@Override
//	public QnAAttatchVO retrieveAttatch(int qstnattNo) {
//		QnAAttatchVO atch = attatchDAO.selectAttatch(qstnattNo);
//		if(atch==null)
//			throw new BoardNotFoundException(HttpStatus.NOT_FOUND, String.format("%d 해당 파일이 없습니다.", qstnattNo));
//		return null;
//	}

	@Override
	public void retrieveQnAList(PaginationInfo<QnAVO> paging) {
		int totalRecord = qnaDAO.selectTotalRecord(paging);
		paging.setTotalRecord(totalRecord);
		List<QnAVO> dataList = qnaDAO.selectQnAList(paging);
		paging.setDataList(dataList);
		
	}

	@Override
	public ServiceResult modifyQnA(QnAVO qnaVO) {
		int cnt = qnaDAO.updateQnA(qnaVO);
		ServiceResult result = null;
		if(cnt>0) {
			result = ServiceResult.OK;
//			processQnAFiles(qnaVO);
		}else {
			result = ServiceResult.FAIL;
		}
		return result;
	}
	
//	private void processDeleteAttatches(QnAVO saveQnA) {
//		saveQnA.getAttatchList().forEach((atch)->{
//			String saveName = atch.getQstnattSavename();
//			attatchDAO.deleteAttatch(atch.getQstnattNo());
//			try {
//				FileUtils.deleteQuietly(new File(qnaFiles.getFile(), saveName));
//			} catch (IOException e) {
//				throw new RuntimeException(e);
//			}
//		});
//	}

	@Override
	public ServiceResult removeQnA(String qstnNo) {
		int cnt = qnaDAO.deleteQnA(qstnNo);
		ServiceResult result = null;
		if(cnt>0) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAIL;
		}
		return result;
	}

	@Override
	public int createQnaPost(QnAVO qnaVO) {
		return this.qnaDAO.createQnaPost(qnaVO);
	}

	@Override
	public int updateQnaPost(QnAVO qnaVO) {
		return this.qnaDAO.updateQnaPost(qnaVO);
	}

	@Override
	public int deleteQnaPost(QnAVO qnaVO) {
		return this.qnaDAO.deleteQnaPost(qnaVO);
	}

	
}
