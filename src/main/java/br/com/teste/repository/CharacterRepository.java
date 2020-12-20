package br.com.teste.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.teste.model.Character;

@Repository
public interface CharacterRepository extends JpaRepository<Character, Long>{

}
