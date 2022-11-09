package model;

public class Way {
	private int[] wayNodes;
	private int[][] fOsf;
	public Way(int[] way) {
		wayNodes = way;
	}
	public Way(int[][] fOfS) {
		this.fOsf=fOfS;
	}
	public Way() {

	}
	public int[] getWayNodes() {
		return wayNodes;
	}
	public void setWayNodes(int[] wayNodes) {
		this.wayNodes = wayNodes;
	}
	public int[][] getfOsf() {
		return fOsf;
	}
	public void setfOsf(int[][] fOsf) {
		this.fOsf = fOsf;
	}
}
