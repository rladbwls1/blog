package mini.spring.mvc;

import java.io.File;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import mini.spring.mvc.bean.NoticeDTO;
import mini.spring.mvc.bean.NoticeReplyDTO;
import mini.spring.mvc.service.NoticeService;

@Controller
@RequestMapping("/n/*")
public class NoticeController {
	@Autowired
	private NoticeService service;
	
	@RequestMapping("list")
	public String list(Model model) {
		model.addAttribute("list",service.getlist());
		model.addAttribute("map",service.countReply());
		return "notice/noticeList";
	}
	@RequestMapping("writeForm")
	public String writeForm() {
		return "notice/noticeWriteForm";
	}
	@RequestMapping("writePro")
	public String writePro(NoticeDTO dto,ArrayList<MultipartFile> files,
			 HttpServletRequest request) {
		int isfile=0;
		for(MultipartFile f:files) {
			if(!f.getOriginalFilename().equals("")) {
				isfile++;
			}
		}
		dto.setIsfile(isfile);
		service.insert(dto);
		
		if(isfile>0) {
			String path=request.getServletContext().getRealPath("/resources/file/notice/");
			service.fileUpload(files,path);
		}
		
		return "redirect:/n/list";
	}
	@RequestMapping("replyPro")
	public String replyPro(NoticeReplyDTO dto, Model model) {
		service.insertReply(dto);
		model.addAttribute("num",dto.getNoticenum());
		return "redirect:/n/content";
	}
	@RequestMapping("content")
	public String content(int num, Model model) {
		model.addAttribute("filenames",service.getFilenames(num));
		model.addAttribute("notice",service.getNotice(num));
		model.addAttribute("replys", service.replys(num));
		model.addAttribute("imgfiles",service.getImgFiles(num));
		return "notice/noticeContent";
	}
	@RequestMapping("updateForm")
	public String updateForm(int num, Model model) {
		model.addAttribute("notice",service.getNotice(num));
		model.addAttribute("fileList",service.getFilenames(num));
		return "notice/noticeUpdateForm";
	}
	@RequestMapping("updatePro")
	public String updatePro(NoticeDTO dto,Model model, String deletefiles, HttpServletRequest request,ArrayList<MultipartFile> files) {
		String path=request.getServletContext().getRealPath("/resources/file/notice/");
		int isfile=dto.getIsfile();
		if(deletefiles!=null) {
			isfile=service.updateProFile(deletefiles, path,isfile);		//파일 삭제
		}
		for(MultipartFile f:files) {
			if(!f.getOriginalFilename().equals("")) {
				isfile++;
			}
		}
		dto.setIsfile(isfile);
		service.update(dto);							//글 수정
		if(isfile>0) {
			service.fileUpload2(files, path, dto.getNum());	//파일 업로드
		}
		model.addAttribute("num",dto.getNum());
		return "redirect:/n/content";
	}
	@RequestMapping("delete")
	public String delete(int num,HttpServletRequest request) {
		String path=request.getServletContext().getRealPath("/resources/file/notice/");
		service.delete(num,path);
		return "redirect:/n/list";
	}
	@RequestMapping("deleteReply")
	public String deleteReply(int num,int noticenum,Model model) {
		service.deleteReply(num);
		model.addAttribute("num",noticenum);
		return "redirect:/n/content";
	}
	
	@RequestMapping("download")
	public ModelAndView download(HttpServletRequest request,String filename) {
		String filePath=request.getServletContext().getRealPath("/resources/file/notice/");
		File file=new File(filePath+filename);
		ModelAndView mv= new ModelAndView("downView","downFile",file);
		return mv;
	}
	
	
}
