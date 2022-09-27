package com.di.step3;

public class Assembler {
	
	
	private IFreeBoardDao freeBoardDao;
	private IFreeBoardService freeBoardService1;
	private IFreeBoardService freeBoardService2;
	private IFreeBoardService freeBoardService3;
	public Assembler() {
		freeBoardDao = new FreeBoardDaoMysql();//daoOracle대신 mysql로 변경할 때 여기만 바꾸면 됨 
		freeBoardService1 = new FreeBoardServiceImpl1(freeBoardDao);
		freeBoardService2 = new FreeBoardServiceImpl2(freeBoardDao);
		freeBoardService3 = new FreeBoardServiceImpl3(freeBoardDao);
		
	}
	public IFreeBoardDao getFreeBoardDao() {
		return freeBoardDao;
	}
	public void setFreeBoardDao(IFreeBoardDao freeBoardDao) {
		this.freeBoardDao = freeBoardDao;
	}
	public IFreeBoardService getFreeBoardService1() {
		return freeBoardService1;
	}
	public void setFreeBoardService1(IFreeBoardService freeBoardService1) {
		this.freeBoardService1 = freeBoardService1;
	}
	public IFreeBoardService getFreeBoardService2() {
		return freeBoardService2;
	}
	public void setFreeBoardService2(IFreeBoardService freeBoardService2) {
		this.freeBoardService2 = freeBoardService2;
	}
	public IFreeBoardService getFreeBoardService3() {
		return freeBoardService3;
	}
	public void setFreeBoardService3(IFreeBoardService freeBoardService3) {
		this.freeBoardService3 = freeBoardService3;
	}
	
	
}
