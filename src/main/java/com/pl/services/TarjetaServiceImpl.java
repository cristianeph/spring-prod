package com.pl.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.pl.model.TarjetaModel;
import com.pl.model.TarjetaModel_;
import com.pl.repository.TarjetaRepository;

@Transactional
@Component("tarjetaService")
public class TarjetaServiceImpl implements TarjetaService{
	
	@PersistenceContext
	private EntityManager entityManager;
	
	private final TarjetaRepository tarjetaRepository;
	
	public TarjetaServiceImpl(TarjetaRepository tarjetaRepository){
		
		this.tarjetaRepository = tarjetaRepository;
		
	}

	@Override
	public TarjetaModel save(TarjetaModel tarjeta) {
		
		return this.tarjetaRepository.save(tarjeta);
		
	}

	@Override
	public void deleteById(TarjetaModel tarjeta) {
		
		System.out.println("entro: " + tarjeta.getId());
		
		this.tarjetaRepository.deleteById(tarjeta.getId());
		
	}

	@Override
	public TarjetaModel findById(TarjetaModel tarjeta) {
		
		return this.tarjetaRepository.findById(tarjeta.getId());
		
	}

	@Override
	public Page<TarjetaModel> findAll(TarjetaModel tarjeta) {
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<TarjetaModel> query = builder.createQuery(TarjetaModel.class);
		Root<TarjetaModel> root = query.from(TarjetaModel.class);
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		if(tarjeta.getId() != 0){
			System.out.println(tarjeta.getId());
			predicates.add(builder.equal(root.get(TarjetaModel_.id), tarjeta.getId()));
		}
		
		Predicate[] predicatesArray = new Predicate[predicates.size()];
		
		query.where(builder.and(predicates.toArray(predicatesArray)));
		query.orderBy(builder.desc(root.get("id")));
		
		if(predicates.size() == 0){
			
			System.out.println("NO se mandaron parametros para la busqueda");
			System.out.println("CANTIDAD de resultados: " + tarjeta.getRows());
			
			if(tarjeta.getRows() > 0){
				return (Page<TarjetaModel>) this.tarjetaRepository.findAll(new PageRequest(0, tarjeta.getRows(), Direction.DESC, "id"));
			}else{
				return (Page<TarjetaModel>) this.tarjetaRepository.findAll(new PageRequest(0, Integer.MAX_VALUE, Direction.DESC, "id"));
			}
			
		}else{
			
			System.out.println("SI se mandaron parametros para la busqueda");
			
			Page<TarjetaModel> tarjetas = new PageImpl<TarjetaModel>(entityManager.createQuery(query.select(root)).getResultList()); 
			return tarjetas;
			
		}
		
	}

}
