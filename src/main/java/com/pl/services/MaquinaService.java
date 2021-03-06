package com.pl.services;

import org.springframework.data.domain.Page;

import com.pl.model.MaquinaModel;

public interface MaquinaService {

	MaquinaModel save(MaquinaModel maquina);
	MaquinaModel deleteById(MaquinaModel maquina);
	MaquinaModel findById(MaquinaModel maquina);
	Page<MaquinaModel> findAll(MaquinaModel maquina);

}
