package com.pl.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(
	name="kardex"
)
public class KardexModel {

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	@OneToMany(fetch=FetchType.EAGER, mappedBy = "kardex", orphanRemoval = true,  cascade = CascadeType.ALL)
	private Collection<MovimientoAlmacenModel> movimiento = new ArrayList<>();
	@Column(name="idinsumo")
	private Integer idInsumo;
	private Integer relacion;
	private Integer stock;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
    public Collection<MovimientoAlmacenModel> getMovimiento() {
        return movimiento;
    }
    public void setMovimiento(Collection<MovimientoAlmacenModel> movimiento) {
        this.movimiento = movimiento;
    }
    public Integer getIdInsumo() {
		return idInsumo;
	}
	public void setIdInsumo(Integer idInsumo) {
		this.idInsumo = idInsumo;
	}
	public Integer getRelacion() {
		return relacion;
	}
	public void setRelacion(Integer relacion) {
		this.relacion = relacion;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}	
	
}
