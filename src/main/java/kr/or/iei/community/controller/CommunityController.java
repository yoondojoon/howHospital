package kr.or.iei.community.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletResponse;
import kr.or.iei.FileUtils;
import kr.or.iei.community.model.dto.Community;
import kr.or.iei.community.model.dto.CommunityFile;
import kr.or.iei.community.model.dto.CommunityListData;
import kr.or.iei.community.model.service.CommunityService;
import kr.or.iei.member.model.dto.Member;

@Controller
@RequestMapping(value="/community")
public class CommunityController {
	@Autowired
	private CommunityService communityService;
	@Value("${file.root}")
	private String root;
	@Autowired
	private FileUtils fileUtils;

	@GetMapping(value="/communityMain")
	public String communityMain(String keyword, int reqPage, Model model) {
		CommunityListData cld = communityService.selectCommunityList(keyword, reqPage);
		if(!cld.getList().isEmpty()) {
			model.addAttribute("boardList", cld.getList());
			model.addAttribute("pageNavi", cld.getPageNavi());
		}
		return "community/communityMain";
	}
	
	@GetMapping(value="/communityWriteFrm")
	public String communityWriteFrm() {
		return "community/communityWriteFrm";
	}
	
	@ResponseBody
	@PostMapping(value="/editor", produces = "plain/text;charset=utf-8")
	public String editorUpload(MultipartFile upfile) {
		String savepath = root+"/community/editor/";
		String filepath = fileUtils.upload(savepath, upfile);
		return "/community/editor/"+filepath; 
	}
	
	@PostMapping(value="/write")
	public String write(Community c, MultipartFile[] upfile, Model model) {
		List<CommunityFile> fileList = new ArrayList<CommunityFile>();
		if(!upfile[0].isEmpty()) {
			//파일 저장할 경로
			//C:/Temp/upload/notice/
			String savepath = root+"/community/";
			for(MultipartFile file : upfile) {
				//업로드한 파일명 추출
				String filename = file.getOriginalFilename();
				String filepath = fileUtils.upload(savepath, file);
				CommunityFile communityFile = new CommunityFile();
				communityFile.setFilename(filename);
				communityFile.setFilepath(filepath);
				fileList.add(communityFile);
			}
		}
		int result = communityService.insertCommunityBoard(c, fileList);		
		if(result == (fileList.size()+1)) {
			model.addAttribute("titleMsg","게시물이 등록되었습니다.");
			model.addAttribute("msg","병원어떼 매거진 페이지에서 작성된 글을 확인하세요.");
			model.addAttribute("loc", "/community/communityMain?reqPage=1");
		}else {
			model.addAttribute("titleMsg","게시물 등록에 실패했습니다.");
			model.addAttribute("msg","관리자에게 문의하세요.");
			model.addAttribute("loc","/");			
		}
		return "common/modalMsg";
	}
	
	@GetMapping(value="/communityView")
	public String communityView(int boardNo, @SessionAttribute(required=false) Member member, Model model) {
		int memberNo = 0;
		if(member != null) {
			memberNo = member.getMemberNo();
		}
		Community c = communityService.selectOneBoard(boardNo);
		if(c == null) {
			model.addAttribute("titleMsg","조회 실패");
			model.addAttribute("msg","이미 삭제된 게시물입니다.");
			model.addAttribute("loc", "/community/communityMain?reqPage=1");
			return "common/modalMsg";
		}else {
			c.setMemberNo(memberNo);
			model.addAttribute("c", c);
			return "community/communityView";
		}
	}
	
	@GetMapping(value="/filedown")
	public void filedown(CommunityFile file, HttpServletResponse response) {
		String savepath = root+"/community/";
		fileUtils.downloadFile(savepath,file.getFilename(),file.getFilepath(),response);
	}
	
	@GetMapping(value="/delete")
	public String delete(int boardNo, Model model) {
		List fileList = communityService.deleteCommunityBoard(boardNo);
		if(fileList != null) {
			String savepath = root+"/community/";
			for(Object item : fileList) {
				CommunityFile file = (CommunityFile)item;
				fileUtils.deleteFile(savepath, file.getFilepath());
			}
			model.addAttribute("titleMsg","삭제 성공");
			model.addAttribute("msg","게시글이 삭제되었습니다.");
			model.addAttribute("loc", "/community/communityMain?reqPage=1");
			return "common/modalMsg";		
		}else {
			model.addAttribute("titleMsg", "삭제 실패");
			model.addAttribute("msg", "관리자에게 문의하세요.");
			model.addAttribute("loc", "/community/communityView?boardNo="+boardNo);
		}
		return "common/modalMsg";
	}
	
	@GetMapping(value="/updateFrm")
	public String updateFrm(int boardNo, Model model) {
		int memberNo = 0;
		Community c = communityService.getOneBoard(boardNo);
		model.addAttribute("c", c);
		return "community/communityUpdateFrm";
	}
	
	@PostMapping(value="/update")
	public String update(Community c, MultipartFile[] upfile, int[] delFileNo, Model model) {
		 List<CommunityFile> fileList = new ArrayList<CommunityFile>();
		 String savepath = root+"/community/";
		 if(!upfile[0].isEmpty()) {
			 for(MultipartFile file : upfile) {
				 String filename = file.getOriginalFilename();
				 String filepath = fileUtils.upload(savepath, file);
				 CommunityFile communityFile = new CommunityFile();
				 communityFile.setFilename(filename);
				 communityFile.setFilepath(filepath);
				 communityFile.setBoardNo(c.getBoardNo());
				 fileList.add(communityFile);
			 }
		 }
		 List delFileList = communityService.updateCommunityBoard(c, fileList, delFileNo);
		 if(delFileList != null) {
			 for(Object item : delFileList) {
				 CommunityFile communityFile = (CommunityFile)item;
				 fileUtils.deleteFile(savepath, communityFile.getFilepath());
			 }
			 return "redirect:/community/communityView2?boardNo="+c.getBoardNo();
		 }else {
			 model.addAttribute("titleMsg", "수정 실패");
			 model.addAttribute("msg", "처리 중 문제가 발생했습니다.");
			 model.addAttribute("loc", "/community/communityView2?boardNo="+c.getBoardNo()); 
			 return "common/modalMsg";
		 }
	}
	
	@GetMapping(value="/communityView2")
	public String communityView2(int boardNo, @SessionAttribute(required=false) Member member, Model model) {
		int memberNo = 0;
		Community c = communityService.getOneBoard(boardNo);
		if(member != null) {
			memberNo = member.getMemberNo();
			c.setMemberNo(memberNo);
		}
		if(c == null) {
			model.addAttribute("titleMsg","조회 실패");
			model.addAttribute("msg","이미 삭제된 게시물입니다.");
			model.addAttribute("loc", "/community/communityMain?reqPage=1");
			return "common/modalMsg";
		}else {
			model.addAttribute("c", c);
			return "community/communityView";
		}
	}
}










