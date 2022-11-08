package model;

public class Way {
	private int[] wayNodes;
	public Way(int size) {
		wayNodes = new int[size];
	}
	public int[] getWayNodes() {
		return wayNodes;
	}
	public void setWayNodes(int[] wayNodes) {
		this.wayNodes = wayNodes;
	}
	

}
