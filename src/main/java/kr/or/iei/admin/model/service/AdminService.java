package kr.or.iei.admin.model.service;

import java.util.List;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.iei.admin.model.dao.AdminDao;
import kr.or.iei.admin.model.dto.AdminBusinessAuth;
import kr.or.iei.admin.model.dto.AdminBusinessAuthListData;
import kr.or.iei.admin.model.dto.Faq;
import kr.or.iei.admin.model.dto.FaqListData;
import kr.or.iei.admin.model.dto.HospitalReport;
import kr.or.iei.admin.model.dto.HospitalReportListData;
import kr.or.iei.admin.model.dto.MemberReport;
import kr.or.iei.admin.model.dto.MemberReportListData;
import kr.or.iei.admin.model.dto.Notice;
import kr.or.iei.admin.model.dto.NoticeListData;
import kr.or.iei.admin.model.dto.Review;
import kr.or.iei.hospital.model.dto.BusinessAuth;
import kr.or.iei.member.model.dto.Member;

@Service
public class AdminService {
	@Autowired
	private AdminDao adminDao;
	
	
	public NoticeListData selectAllNotice(int reqPage) {
		
		//페이지당 게시슬 갯수 10개? 좀 더 많이 보여줄까.....
		int numPerPage = 10;
		
		int end = reqPage*numPerPage;
		int start = end - numPerPage + 1;
		List list = adminDao.selectAllNotice(start,end);
		int totalCount = adminDao.selectAllBoardCount();
		
		
		int totalPage = 0;
		if(totalCount%numPerPage == 0) {
			totalPage = totalCount/numPerPage;
		}else {
			totalPage = totalCount/numPerPage+1;			
		}
		
		
		int pageNaviSize = 10;
		
		
		int pageNo =((reqPage -1)/pageNaviSize)*pageNaviSize + 1;
		
		String pageNavi = "<ul class='pagination box'>";
		if(pageNo !=1) {
			pageNavi += "<li>";
			pageNavi += "<a class='page-item' href='/admin/noticeList?reqPage="+ (pageNo-1) +"'>";
			pageNavi += "<span class='material-icons'>chevron_left</span>";
			pageNavi += "</a></li>";
		}
		
		for(int i = 0;i<pageNaviSize;i++) {
			if(pageNo == reqPage) {
				pageNavi += "<li>";
				pageNavi += "<a class='active' href='/admin/noticeList?reqPage="+ (pageNo) +"'>";
				pageNavi += pageNo;
				pageNavi += "</a></li>";
			}else {				
				pageNavi += "<li>";
				pageNavi += "<a href='/admin/noticeList?reqPage="+ (pageNo) +"'>";
				pageNavi += pageNo;
				pageNavi += "</a></li>";
			}
			pageNo++;
			
			if(pageNo > totalPage) {
				break;
			}
		}
		
		
		if(pageNo <= totalPage) {
			pageNavi += "<li>";
			pageNavi += "<a class='page-item' href='/admin/noticeList?reqPage="+ (pageNo) +"'>";
			pageNavi += "<span class='material-icons'>chevron_right</span>";
			pageNavi += "</a></li>";
		}
		
		pageNavi += "</ul>";
		
		NoticeListData nld = new NoticeListData(list, pageNavi);
		return nld;
	}


	public NoticeListData searchNoitce(int reqPage, String type, String keyword) {
		
		int numPerPage = 10;
		
		int end = reqPage*numPerPage;
		int start = end - numPerPage + 1;
		
		List list = null;
		
		if(type.equals("title")) {
			list = adminDao.selectSearchTitle(start,end,keyword);	
		}		
		else if(type.equals("writer")) {
			list = adminDao.selectSearchWriter(start,end,keyword);	
		}
	
		int totalCount = 0;
		if(type.equals("title")) {
			totalCount = adminDao.titleTotalCount(keyword);
		}else if(type.equals("writer")) {
			totalCount = adminDao.writerTotalCount(keyword);
		}
		
		
		int totalPage = 0;
		if(totalCount%numPerPage == 0) {
			totalPage = totalCount/numPerPage;
		}else {
			totalPage = totalCount/numPerPage+1;			
		}
		
		
		int pageNaviSize = 10;
		
		
		int pageNo =((reqPage -1)/pageNaviSize)*pageNaviSize + 1;
		
		String pageNavi = "<ul class='pagination box'>";
		if(pageNo !=1) {
			pageNavi += "<li>";
			pageNavi += "<a class='page-item' href='/admin/noticeList?reqPage="+ (pageNo-1) +"'>";
			pageNavi += "<span class='material-icons'>chevron_left</span>";
			pageNavi += "</a></li>";
		}
		
		for(int i = 0;i<pageNaviSize;i++) {
			if(pageNo == reqPage) {
				pageNavi += "<li>";
				pageNavi += "<a class='active' href='/admin/searchNoitce?reqPage="+ (pageNo-1) +"&type="+type+"&keyword="+keyword+"'>";
				pageNavi += pageNo;
				pageNavi += "</a></li>";
			}else {				
				pageNavi += "<li>";
				pageNavi += "<a href='/admin/searchNotice?reqPage="+ (pageNo-1) +"&type="+type+"&keyword="+keyword+"'>";
				pageNavi += pageNo;
				pageNavi += "</a></li>";
			}
			pageNo++;
			
			if(pageNo > totalPage) {
				break;
			}
		}
		
		
		if(pageNo <= totalPage) {
			pageNavi += "<li>";
			pageNavi += "<a class='page-item' href='/admin/searchNotice?reqPage="+ (pageNo-1) +"&type="+type+"&keyword="+keyword+"'>";
			pageNavi += "<span class='material-icons'>chevron_right</span>";
			pageNavi += "</a></li>";
		}
		
		pageNavi += "</ul>";
		
		NoticeListData nld = new NoticeListData(list, pageNavi);
		return nld;
	}

	@Transactional
	public int insertNotice(Notice n) {
		int result = adminDao.insertNotice(n);
		return result;
	}


	public Notice searchNoticeDetail(int noticeNo) {
		Notice n = adminDao.searchNoticeDetail(noticeNo);
		return n;
	}

	@Transactional
	public int deleteNotice(int noticeNo) {
		int result = adminDao.deleteNotice(noticeNo);
		return result;
	}


	public MemberReportListData selectAllMemberReport(int reqPage) {
		
		//페이지당 게시슬 갯수 10개? 좀 더 많이 보여줄까.....
		int numPerPage = 10;
		
		int end = reqPage*numPerPage;
		int start = end - numPerPage + 1;
		List list = adminDao.selectAllMemberReport(start,end);
		int totalCount = adminDao.selectAllMemberReportCount();
		
		
		int totalPage = 0;
		if(totalCount%numPerPage == 0) {
			totalPage = totalCount/numPerPage;
		}else {
			totalPage = totalCount/numPerPage+1;			
		}
		
		
		int pageNaviSize = 10;
		
		
		int pageNo =((reqPage -1)/pageNaviSize)*pageNaviSize + 1;
		
		String pageNavi = "<ul class='pagination box'>";
		if(pageNo !=1) {
			pageNavi += "<li>";
			pageNavi += "<a class='page-item' href='/admin/manageReport?reqPage="+ (pageNo-1) +"'>";
			pageNavi += "<span class='material-icons'>chevron_left</span>";
			pageNavi += "</a></li>";
		}
		
		for(int i = 0;i<pageNaviSize;i++) {
			if(pageNo == reqPage) {
				pageNavi += "<li>";
				pageNavi += "<a class='active' href='/admin/manageReport?reqPage="+ (pageNo) +"'>";
				pageNavi += pageNo;
				pageNavi += "</a></li>";
			}else {				
				pageNavi += "<li>";
				pageNavi += "<a href='/admin/manageReport?reqPage="+ (pageNo) +"'>";
				pageNavi += pageNo;
				pageNavi += "</a></li>";
			}
			pageNo++;
			
			if(pageNo > totalPage) {
				break;
			}
		}
		
		
		if(pageNo <= totalPage) {
			pageNavi += "<li>";
			pageNavi += "<a class='page-item' href='/admin/manageReport?reqPage="+ (pageNo) +"'>";
			pageNavi += "<span class='material-icons'>chevron_right</span>";
			pageNavi += "</a></li>";
		}
		
		pageNavi += "</ul>";
		
		MemberReportListData mrld = new MemberReportListData(list,pageNavi);
		return mrld;
	}

	@Transactional
	public boolean deletChk(String deleteList) {
		StringTokenizer sT1 = new StringTokenizer(deleteList, "/");
		boolean result=true;
		while(sT1.hasMoreTokens()) {
			int reportNo = Integer.parseInt(sT1.nextToken());
			int deleteResult = adminDao.deleteChk(reportNo);
			
			if(deleteResult == 0) {
				result = false;
				break;
			}
		}
		return result;
	}


	public MemberReport searchReportDetail(int reportNo) {
		MemberReport mr = adminDao.searchReportDetail(reportNo);
		return mr;
	}

	@Transactional
	public int deleteReport(int reportNo) {
		int result = adminDao.deleteReport(reportNo);
		return result;
	}

	@Transactional
	public int confirmReport(int reportNo) {
		int result = adminDao.confirmReport(reportNo);
		return result;
	}
	
	
	//사업자인증 리스트 조회하는 코드
	public AdminBusinessAuthListData selectAllBusinessAuth(int reqPage) {
		int numPerPage = 10;
		
		int end = reqPage*numPerPage;
		int start = end - numPerPage + 1;
		List list = adminDao.selectAllBusinessAuth(start,end);
		int totalCount = adminDao.selectAllBusinessAuthCount();
		
		
		int totalPage = 0;
		if(totalCount%numPerPage == 0) {
			totalPage = totalCount/numPerPage;
		}else {
			totalPage = totalCount/numPerPage+1;			
		}
		
		
		int pageNaviSize = 10;
		
		
		int pageNo =((reqPage -1)/pageNaviSize)*pageNaviSize + 1;
		
		String pageNavi = "<ul class='pagination box'>";
		if(pageNo !=1) {
			pageNavi += "<li>";
			pageNavi += "<a class='page-item' href='/admin/businessAuthList?reqPage="+ (pageNo-1) +"'>";
			pageNavi += "<span class='material-icons'>chevron_left</span>";
			pageNavi += "</a></li>";
		}
		
		for(int i = 0;i<pageNaviSize;i++) {
			if(pageNo == reqPage) {
				pageNavi += "<li>";
				pageNavi += "<a class='active' href='/admin/businessAuthList?reqPage="+ (pageNo) +"'>";
				pageNavi += pageNo;
				pageNavi += "</a></li>";
			}else {				
				pageNavi += "<li>";
				pageNavi += "<a href='/admin/businessAuthList?reqPage="+ (pageNo) +"'>";
				pageNavi += pageNo;
				pageNavi += "</a></li>";
			}
			pageNo++;
			
			if(pageNo > totalPage) {
				break;
			}
		}
		
		
		if(pageNo <= totalPage) {
			pageNavi += "<li>";
			pageNavi += "<a class='page-item' href='/admin/businessAuthList?reqPage="+ (pageNo) +"'>";
			pageNavi += "<span class='material-icons'>chevron_right</span>";
			pageNavi += "</a></li>";
		}
		
		pageNavi += "</ul>";
		
		AdminBusinessAuthListData abld = new AdminBusinessAuthListData(list, pageNavi);
		return abld;
	}


	public AdminBusinessAuth confirmAuth(int businessAuthNo) {
		AdminBusinessAuth aba = adminDao.confirmAuth(businessAuthNo);
		List fileList = adminDao.confirmAuthFile(businessAuthNo);
		aba.setFileList(fileList);
		return aba;
	}

	@Transactional
	public boolean authConfirmSuccess(int businessAuthNo) {
		boolean result = true;
		BusinessAuth ba = adminDao.searchMember(businessAuthNo);
		int changeStatusResult = adminDao.authConfirmSuccess(ba);
		if(changeStatusResult == 0) {
			result = false;
		}else {
			int deleteAuthResult = adminDao.deleteAuthInfo(businessAuthNo);
		}
		
		
		return result;
	}

	@Transactional
	public boolean authConfirmFail(int businessAuthNo) {
		boolean result = true;
		int deleteAuthResult = adminDao.deleteAuthInfo(businessAuthNo);
		if(deleteAuthResult ==0) {
			result = false;
		}
		return result;
	}


	public FaqListData selectAllFaq() {
		List<Faq> categoryList = adminDao.selectCategory();
		List<Faq> contentList = adminDao.selectContentList();
		
		FaqListData fld = new FaqListData();
		fld.setCategoryList(categoryList);
		fld.setContentList(contentList);
		return fld;
	}


	public List<Review> selectAllReview() {
		List<Review> list = adminDao.selectAllReview();
		return list;
	}

	@Transactional
	public int deleteReview(int reviewNo) {
		
		int result = adminDao.deleteReview(reviewNo);
		return result;
	}


	public List selectAllMember() {
		List<Member> list = adminDao.selectAllMember(); 
		return list;
	}

	@Transactional
	public int unBlock(int memberNo) {
		int result = adminDao.unBlock(memberNo);
		return result;
	}

	@Transactional
	public int block(int memberNo) {
		int result = adminDao.block(memberNo);
		return result;
	}


	public HospitalReportListData selectAllHospitalReport(int reqPage) {
		int numPerPage = 10;
		
		int end = reqPage*numPerPage;
		int start = end - numPerPage + 1;
		List list = adminDao.selectAllHospitalReport(start,end);
		int totalCount = adminDao.selectAllHospitalReportCount();
		
		
		int totalPage = 0;
		if(totalCount%numPerPage == 0) {
			totalPage = totalCount/numPerPage;
		}else {
			totalPage = totalCount/numPerPage+1;			
		}
		
		
		int pageNaviSize = 10;
		
		
		int pageNo =((reqPage -1)/pageNaviSize)*pageNaviSize + 1;
		
		String pageNavi = "<ul class='pagination box'>";
		if(pageNo !=1) {
			pageNavi += "<li>";
			pageNavi += "<a class='page-item' href='/admin/manageHospitalReport?reqPage="+ (pageNo-1) +"'>";
			pageNavi += "<span class='material-icons'>chevron_left</span>";
			pageNavi += "</a></li>";
		}
		
		for(int i = 0;i<pageNaviSize;i++) {
			if(pageNo == reqPage) {
				pageNavi += "<li>";
				pageNavi += "<a class='active' href='/admin/manageHospitalReport?reqPage="+ (pageNo) +"'>";
				pageNavi += pageNo;
				pageNavi += "</a></li>";
			}else {				
				pageNavi += "<li>";
				pageNavi += "<a href='/admin/manageHospitalReport?reqPage="+ (pageNo) +"'>";
				pageNavi += pageNo;
				pageNavi += "</a></li>";
			}
			pageNo++;
			
			if(pageNo > totalPage) {
				break;
			}
		}
		
		
		if(pageNo <= totalPage) {
			pageNavi += "<li>";
			pageNavi += "<a class='page-item' href='/admin/manageHospitalReport?reqPage="+ (pageNo) +"'>";
			pageNavi += "<span class='material-icons'>chevron_right</span>";
			pageNavi += "</a></li>";
		}
		
		pageNavi += "</ul>";
		
		HospitalReportListData hrld = new HospitalReportListData(list, pageNavi);
		return hrld;
	
	}


	public HospitalReport selectOneHospitalReport(int reportNo) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
