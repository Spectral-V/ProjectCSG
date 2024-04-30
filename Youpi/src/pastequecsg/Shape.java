package pastequecsg;


import java.awt.Graphics;

import java.io.Serializable;

abstract class Shape implements Serializable{

	private static final long serialVersionUID = 1L;
	protected int co=0;
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

		protected abstract int getX();



		public int getY() {
			// TODO Auto-generated method stub
			return 0;
		}

		protected abstract void setX(int x);
		protected abstract void setY(int x);


}
