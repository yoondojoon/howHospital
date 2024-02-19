package kr.or.iei.etc.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/etc")
public class EtcController {
	@GetMapping("/pharmacy")
	public String pharmacy() {
		return "etc/searchPharmacy";
	}
	
	
	@ResponseBody
	@GetMapping("/pharmacyInfo")
	public List pharmacyInfo(String pageNo) {
		String url = "https://apis.data.go.kr/B552657/ErmctInsttInfoInqireService/getParmacyListInfoInqire";
		String serviceKey = "LxQKysMvvVlGF+KIGwFiZBiZMmIHlq8evNwirB1BUJ/2EXczsoGjLsX4u41ITvSzySrmEvTrQ443KgTEL9JOSQ==";
		String numOfRows = "8";
		String resultType = "xml";
		ArrayList<Pharmacy> list = new ArrayList<Pharmacy>();
		
		try {
			Document document = Jsoup.connect(url)
				.data("serviceKey", serviceKey)
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
}
