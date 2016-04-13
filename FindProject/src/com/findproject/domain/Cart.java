//package com.findproject.domain;
//
//import java.util.LinkedHashMap;
//import java.util.Map;
//
//public class Cart {
////代表购物车实体
//	//出现重复《ID，project》
//	//private Map<String,Project> map=new LinkedHashMap()//从购物车里有查找需求（无：list）
//	//避免出现重复对象（cartitem）
//	private Map<String,CartItem> map=new LinkedHashMap();//从购物车里有查找需求（无：list）
//	private double price;//记住商品有多少钱
//	public void add(ProjectBean pjbean){
//		//看购物车中有没有要添加的书对应的购物项
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
