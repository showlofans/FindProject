//package com.findproject.domain;
//
//import java.util.LinkedHashMap;
//import java.util.Map;
//
//public class Cart {
////�����ﳵʵ��
//	//�����ظ���ID��project��
//	//private Map<String,Project> map=new LinkedHashMap()//�ӹ��ﳵ���в��������ޣ�list��
//	//��������ظ�����cartitem��
//	private Map<String,CartItem> map=new LinkedHashMap();//�ӹ��ﳵ���в��������ޣ�list��
//	private double price;//��ס��Ʒ�ж���Ǯ
//	public void add(ProjectBean pjbean){
//		//�����ﳵ����û��Ҫ��ӵ����Ӧ�Ĺ�����
//		CartItem item=map.get(pjbean.getProject_id());
//		if(item==null){
//			item=new CartItem();
//			item.setPj(pjbean);
//			item.setQuantity(1);
//			map.put(pjbean.getProject_id(), item);
//		}
//		else{
//			item.setQuantity(item.getQuantity()+1);
//		}
//	}
//	public Map<String, CartItem> getMap() {
//		return map;
//	}
//
//	public void setMap(Map<String, CartItem> map) {
//		this.map = map;
//	}
//
//	public double getPrice() {
//		double totalprice=0;
//		for(Map.Entry<String, CartItem> entry:map.entrySet())
//		{
//			CartItem item=entry.getValue();
//			totalprice += item.getPrice();
//		}
//		this.price=totalprice;
//		return price;
//	}
//
//	public void setPrice(double price) {
//		this.price = price;
//	}
//
//}
