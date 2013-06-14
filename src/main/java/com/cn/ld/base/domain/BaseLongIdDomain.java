package com.cn.ld.base.domain;

public abstract class BaseLongIdDomain extends BaseIdDomain<Long> {

	private static final long serialVersionUID = 1L;

	public BaseLongIdDomain() {
		super();
	}

	public BaseLongIdDomain(Long id) {
		super(id);
	}

	@Override
	public Long getId() {
		return super.getId();
	}

	@Override
	public void setId(Long id) {
		super.setId(id);
	}
}
