package com.cn.ld.base.domain;

import java.io.Serializable;

public abstract class BaseIdDomain<T extends Serializable> extends BaseDomain {

	private static final long serialVersionUID = 1L;
	/** 唯一标识属性 */
	private T id;

	public BaseIdDomain() {
		super();
	}

	public BaseIdDomain(T id) {
		super();

		setId(id);
	}

	/**
	 * 获取唯一标识
	 * 
	 * @return
	 */
	public T getId() {
		return id;
	}

	/**
	 * 设置唯一标识
	 * 
	 * @param id
	 */
	public void setId(T id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BaseIdDomain<?> other = (BaseIdDomain<?>) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
