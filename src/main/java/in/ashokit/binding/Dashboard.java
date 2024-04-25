package in.ashokit.binding;

public class Dashboard {
	private Integer totalEnq;
	private Long openEnq;
	private Long enrolledEnq;
	private Long lostEnq;

	public Integer getTotalEnq() {
		return totalEnq;
	}

	public void setTotalEnq(Integer totalEnquries) {
		this.totalEnq = totalEnquries;
	}

	public Long getOpenEnq() {
		return openEnq;
	}

	public void setOpenEnq(Long openEnq) {
		this.openEnq = openEnq;
	}

	public Long getEnrolledEnq() {
		return enrolledEnq;
	}

	public void setEnrolledEnq(Long enrolledEnq) {
		this.enrolledEnq = enrolledEnq;
	}

	public Long getLostEnq() {
		return lostEnq;
	}

	public void setLostEnq(Long lostEnq) {
		this.lostEnq = lostEnq;
	}

}
