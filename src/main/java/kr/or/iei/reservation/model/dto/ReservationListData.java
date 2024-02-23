package kr.or.iei.reservation.model.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReservationListData {
	private List list;
	private String pageNavi;
	private List doctorList;
}
