package pastequecsg;


import java.awt.Graphics;

import java.io.Serializable;

abstract class Shape implements Serializable{

	private static final long serialVersionUID = 1L;
	protected int co=0;
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	public void paint(Graphics g){

		};
	
		public void paintComponent(Graphics g){
	}
		public int getCo() {
			return co;
		}

		public void setCo(int co) {
			this.co = co;
		}
		public boolean containt (int a, int b) {
	    	return true;
	    }
		public int getX() {
			return x;
		}

		public void setX(int x) {
			this.x = x;
		}
		public int getY() {
			return y;
		}

		public void setY(int y) {
			this.y = y;
		}
		public void setHeight(int height) {
	    	this.height = height ;
			
		}

		public void setWidth(int width) {
			this.width = width;
			
		}
		public int getHeight() {
	    	return this.height;
			
		}

		public int getWidth() {
			return this.width;
			
		}


}
