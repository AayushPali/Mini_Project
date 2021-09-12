package model;

public class AppraisalHistory {
	/*
	 * Columns-	eid,
	 * appraisal_date
	 * old_role_id
	 * new_role_id
	 */

	private int eid;
	private String date;
	private int oldRId;
	private int newRId;
	public AppraisalHistory() {
		// TODO Auto-generated constructor stub
	}
	public AppraisalHistory(int eid, String date, int oldRId, int newRId) {
		super();
		this.eid = eid;
		this.date = date;
		this.oldRId = oldRId;
		this.newRId = newRId;
	}
	public int getEid() {
		return eid;
	}
	public void setEid(int eid) {
		this.eid = eid;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getOldRId() {
		return oldRId;
	}
	public void setOldRId(int oldRId) {
		this.oldRId = oldRId;
	}
	public int getNewRId() {
		return newRId;
	}
	public void setNewRId(int newRId) {
		this.newRId = newRId;
	}
	@Override
	public String toString() {
		return "AppraisalHistory [eid=" + eid + ", date=" + date + ", oldRId=" + oldRId + ", newRId=" + newRId + "]";
	}
	
	
}
