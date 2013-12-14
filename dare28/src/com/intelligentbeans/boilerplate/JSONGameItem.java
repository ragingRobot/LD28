package com.intelligentbeans.boilerplate;
public class JSONGameItem{
	/**************************************************************************************************************************
	 * This Class is used as the object that represents level items in our JSON files
	 **************************************************************************************************************************/
	
	String itemType;
	float x;
	float y;
	float width;
	float height;
	int id;
	boolean toggle = false;
	String name = "";
	Boolean swing = false;
	String nextLevel = "data/levels/level.json";
	String platformType = "data/platforms/platforms.png";
	String background = "data/backgrounds/background.jpg";
	String paralaxName = "data/paralax/paralaxMountian.png";
	float startX;
	float startY;
	boolean lights = false;
	String music ="";
	public JSONGameItem(String itemType, float x, float y, float width, float height) {
		this.itemType = itemType;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
	}

	public JSONGameItem() {

	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}


}