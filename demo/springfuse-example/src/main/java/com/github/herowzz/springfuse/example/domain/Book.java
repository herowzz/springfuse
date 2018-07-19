package com.github.herowzz.springfuse.example.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import com.github.herowzz.springfuse.data.domain.BaseEntity;
import com.github.herowzz.springfuse.data.domain.BaseUidEntity;
import com.github.herowzz.springfuse.example.domain.refrence.BookTypeEnum;

@Entity
@DynamicInsert
@DynamicUpdate
@Where(clause = BaseEntity.SOFT_DELETED_CLAUSE)
public class Book extends BaseUidEntity {

	private static final long serialVersionUID = -7001927790018290769L;

	private String name;

	private BookTypeEnum bookType;

	private int page;

	private BigDecimal sellPrice;

	private LocalDateTime pubDate;

	private String address;

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(foreignKey = @ForeignKey(name = "none"))
	private Shop shop;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public LocalDateTime getPubDate() {
		return pubDate;
	}

	public void setPubDate(LocalDateTime pubDate) {
		this.pubDate = pubDate;
	}

	public BookTypeEnum getBookType() {
		return bookType;
	}

	public void setBookType(BookTypeEnum bookType) {
		this.bookType = bookType;
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public BigDecimal getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(BigDecimal sellPrice) {
		this.sellPrice = sellPrice;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Book [id=");
		builder.append(id);
		builder.append(", createTime=");
		builder.append(createTime);
		builder.append(", updateTime=");
		builder.append(updateTime);
		builder.append(", name=");
		builder.append(name);
		builder.append(", bookType=");
		builder.append(bookType);
		builder.append(", page=");
		builder.append(page);
		builder.append(", sellPrice=");
		builder.append(sellPrice);
		builder.append(", pubDate=");
		builder.append(pubDate);
		builder.append(", ");
		if (shop != null) {
			builder.append("shop=");
			builder.append(shop.getId());
		}
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((bookType == null) ? 0 : bookType.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + page;
		result = prime * result + ((pubDate == null) ? 0 : pubDate.hashCode());
		result = prime * result + ((sellPrice == null) ? 0 : sellPrice.hashCode());
		result = prime * result + ((shop == null) ? 0 : shop.hashCode());
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
		Book other = (Book) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (bookType != other.bookType)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (page != other.page)
			return false;
		if (pubDate == null) {
			if (other.pubDate != null)
				return false;
		} else if (!pubDate.equals(other.pubDate))
			return false;
		if (sellPrice == null) {
			if (other.sellPrice != null)
				return false;
		} else if (sellPrice.compareTo(other.sellPrice) != 0)
			return false;
		if (shop == null) {
			if (other.shop != null)
				return false;
		} else if (!shop.equals(other.shop))
			return false;
		return true;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
