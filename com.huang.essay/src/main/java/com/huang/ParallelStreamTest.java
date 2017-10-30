package com.huang;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class ParallelStreamTest {
	private static final int COUNT = 1000;
	public static void main(String[] args) {
		List<RiderDto> orilist=new ArrayList<RiderDto>();
        for(int i=0;i<COUNT;i++){
        	orilist.add(init());
        }
        final List<RiderDto> copeList=new ArrayList<RiderDto>();
        orilist.parallelStream().forEach(rider -> {
        	RiderDto t = new RiderDto();
        	t.setId(rider.getId());
    		t.setCityId(rider.getCityId());
        	copeList.add(t);
		});
        System.out.println("orilist size:"+orilist.size());
        System.out.println("copeList size:"+copeList.size());
        System.out.println("compare copeList and orilist,result:"+(copeList.size()==orilist.size())); 
	}
	private static RiderDto init() {
		RiderDto t = new RiderDto();
		Random random = new Random();
		t.setId(random.nextInt(2 ^ 20));
		t.setCityId(random.nextInt(1000));
		return t;
	}
	static class RiderDto implements Serializable{
		private static final long serialVersionUID = 1;
		//城市Id
	    private Integer cityId;
	    //骑手Id
	    private Integer id;
		public Integer getCityId() {
			return cityId;
		}
		public void setCityId(Integer cityId) {
			this.cityId = cityId;
		}
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
	}
}
