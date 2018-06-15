package COM.SDS.APP;

import java.util.ArrayList;

import COM.SDS.FRAME.Service;
import COM.SDS.SERVICE.CustomerService;
import COM.SDS.VO.CustomerVO;
import COM.SDS.VO.ItemVO;

public class TestOfPK {

	public static void main(String[] args) {
		Service<String, CustomerVO> service = new CustomerService();
		ArrayList<CustomerVO> getcustomers =null;
		ArrayList<ItemVO> getitems =null;
		try {
			service.register(new CustomerVO("id01","pwd01","로이조"), new ItemVO("id01","마체테",300));
		
			
			getcustomers=service.getCustomer("마체테");
			System.out.println(getcustomers);
			getcustomers=service.get();
			getitems=service.getItem("id02");
			System.out.println(getitems);
			//예외, DB가 죽었을때, id가 잘못되었을때.
		} catch (Exception e) {
			//e.printStackTrace();
			System.out.println("조회 실패");
		}
	}
  
}
