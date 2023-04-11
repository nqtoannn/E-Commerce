package ptithcm.model.address;

import java.util.Collection;

import javax.management.ConstructorParameters;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Province")
public class Ward{
	@Column(name ="id")
	private Integer id;
	@Column(name="name")
	private String name;
	@Column(name="prefix")
	private String prefix;
	@ManyToOne
	@Column(name="province_id")
	private Province province;
	@ManyToOne
	@Column(name="district_id")
	private District district;
	
	
	public Ward() {
		super();
	}
	
	public Ward(int id, String name, String prefix, Province province, District district) {
		super();
		this.id = id;
		this.name = name;
		this.prefix=prefix;
		this.province=province;
		this.district=district;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public Province getProvince() {
		return province;
	}

	public void setProvince(Province province) {
		this.province = province;
	}

	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}
	
	
	
	
	
	
}