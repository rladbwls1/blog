package mini.spring.mvc.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import mini.spring.mvc.bean.NoticeDTO;
import mini.spring.mvc.bean.NoticeReplyDTO;
import mini.spring.mvc.repository.NoticeMapper;

@Service
public class NoticeServiceImpl implements NoticeService{
	@Autowired
	private NoticeMapper mapper;

	@Override
	public void insert(NoticeDTO dto) {
		mapper.insert(dto);
	}

	@Override
	public ArrayList<NoticeDTO> getlist() {
		return mapper.list();
	}

	@Override
	public NoticeDTO getNotice(int num) {
		return mapper.content(num);
	}

	@Override
	public void update(NoticeDTO dto) {
		mapper.update(dto);
	}

	@Override
	public void delete(int num, String path) {
		if(isfile(num)) {
			deleteFiles(num, path);
			mapper.deleteFile(num);
		}
		mapper.deleteReplys(num);
		mapper.delete(num);
	}
	//isfile >0 판단하는 메서드
	public boolean isfile(int num) {
		boolean result=mapper.isfile(num)>0;
		return result;
	}
	//파일 삭제하는 메서드
	public void deleteFiles(int num, String path) {
		ArrayList<String> filenames=getFilenames(num);
		for(String filename:filenames) {
			File copy=new File(path+filename);
			if(copy.isFile()) {
				copy.delete();
			}
		}
	}
	@Override
	public int updateProFile(String deletefiles,String path,int isfile) {
		int num=isfile;
		for(String filename:deletefiles.split(",")) {
			File copy=new File(path+filename);
			if(copy.isFile()) {
				copy.delete();
				mapper.deleteFileByName(filename.trim());
				num--;
			}
		}
		return num;
	}
	
	//첨부파일 업로드 및 insert
	@Override
	public void fileUpload(ArrayList<MultipartFile> files, String path) {
		int noticenum=mapper.maxNum();
		int i=1;
		for(MultipartFile f:files) {
			if(!f.getOriginalFilename().equals("")) {
				String filename=f.getOriginalFilename();
				String ext=filename.substring(filename.indexOf("."));
				String type=f.getContentType().split("/")[0];
				filename="file_"+noticenum+"_"+i+ext;
				i++;
				File copy=new File(path+filename);
				mapper.fileInsert(noticenum, filename,type);
				try {
					f.transferTo(copy);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	//첨부파일 업로드 및 insert
	@Override
	public void fileUpload2(ArrayList<MultipartFile> files, String path,int num) {
		int i=1;
		if(mapper.checkFile(num)!=0) {
			String a=mapper.checkFileNum(num);
			a=a.split("_")[2];
			a=a.substring(0,a.indexOf("."));
			i=Integer.parseInt(a);
			i++;
		}
		for(MultipartFile f:files) {
			if(!f.getOriginalFilename().equals("")) {
				String filename=f.getOriginalFilename();
				String ext=filename.substring(filename.indexOf("."));
				String type=f.getContentType().split("/")[0];
				filename="file_"+num+"_"+i+ext;
				i++;
				File copy=new File(path+filename);
				mapper.fileInsert(num, filename,type);
				try {
					f.transferTo(copy);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public ArrayList<String> getFilenames(int num) {
		return mapper.getFilenames(num);
	}
	
	//이미지 파일인 경우 따로 list 생성
	public ArrayList<String> getImgFiles(ArrayList<String> list){
		ArrayList<String> imgList=null;
		for(String filename:list) {
			
		}
		return imgList;
	}

	@Override
	public void insertReply(NoticeReplyDTO dto) {
		mapper.insertReply(dto);
	}

	@Override
	public ArrayList<NoticeReplyDTO> replys(int num) {
		return mapper.replys(num);
	}

	@Override
	public void deleteReply(int num) {
		mapper.deleteReply(num);
	}

	@Override
	public ArrayList<String> getImgFiles(int num) {
		return mapper.imgFiles(num);
	}

	@Override
	public HashMap<Integer, Integer> countReply() {
		HashMap<Integer, Integer> map=new HashMap<>();
		for(NoticeDTO dto:mapper.list()) {
			map.put(dto.getNum(),mapper.replys(dto.getNum()).size());
		}
		return map;
	}

	
	
	
	

}
