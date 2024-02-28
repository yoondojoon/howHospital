package kr.or.iei.etc.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.iei.etc.model.dto.Pharmacy;
import kr.or.iei.reservation.model.dto.ReceiptData;
import kr.or.iei.reservation.model.service.ReservationService;

@Controller
@RequestMapping("/etc")
public class EtcController {
	@Autowired
	private ReservationService reservationService;
	@GetMapping("/pharmacy")
	public String pharmacy() {
		return "etc/searchPharmacy";
	}
	
	
	@ResponseBody
	@GetMapping("/pharmacyInfo")
	public List pharmacyInfo(String pageNo, String sidoCode, String sigoonCode, String pharmName) {
		String url = "https://apis.data.go.kr/B552657/ErmctInsttInfoInqireService/getParmacyListInfoInqire";
		String serviceKey = "UFGrd/O5mTNg+TYGZxNFlnlBLQCKvE9E+GsHp90xe1s3LU0qkZCyFHnPx7d2RFhY2PX97qRd4q5a1gXeOlR/lA==";
		String numOfRows = "10";
		String resultType = "xml";
		ArrayList<Pharmacy> list = new ArrayList<Pharmacy>();
		
		try {
			Document document = Jsoup.connect(url)
				.data("serviceKey", serviceKey)
				.data("Q0",sidoCode)
				.data("Q1",sigoonCode)
				.data("QN",pharmName)
				.data("pageNo",pageNo)
				.data("numOfRows",numOfRows)
				.data("resultType",resultType)
				.ignoreContentType(true)
				.get();
			System.out.println(document.toString());//
			Elements elements = document.select("item");
			for(int i=0; i < elements.size(); i++) {
				Element item = elements.get(i);
				String dutyName = item.select("dutyName").text();
				String dutyAddr = item.select("dutyAddr").text();
				String dutyTel1 = item.select("dutyTel1").text();
				String lat = item.select("wgs84Lat").text();
				String lng = item.select("wgs84Lon").text();
				Pharmacy p = new Pharmacy(dutyName, dutyAddr, dutyTel1, lat, lng);
				list.add(p);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	@GetMapping("/receipt")
	public String receipt(int reservationNo, Model model) {
		ReceiptData list = reservationService.getReceipt(reservationNo);
		model.addAttribute("receipt", list);
		return "etc/receipt";
	}

}
