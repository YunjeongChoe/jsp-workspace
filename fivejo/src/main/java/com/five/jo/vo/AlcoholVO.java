package com.five.jo.vo;

public class AlcoholVO {

	private int boardNo;
	private String boardTitle;
	private String boardAuthor;

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public String getBoardAuthor() {
		return boardAuthor;
	}

	public void setBoardAuthor(String boardAuthor) {
		this.boardAuthor = boardAuthor;
	}

	@Override
	public String toString() {
		return "AlcoholVO [boardNo=" + boardNo + ", boardTitle=" + boardTitle + ", boardAuthor=" + boardAuthor + "]";
	}

}