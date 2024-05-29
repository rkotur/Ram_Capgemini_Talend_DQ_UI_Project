package com.example.program.Services;

import com.example.program.models.DqProUpdateSchemaModel;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;


@Service
public class DqProUpdateSchemaService implements JpaRepository<DqProUpdateSchemaModel, Long> {

    @Procedure(name = "public.dq_pro_update_schema")
    void callProcedure(
            @Param("p_schema_name") String pSchemaName,
            @Param("p_table_name") String pTableName,
            @Param("p_column_name") String pColumnName,
            @Param("p_check_name") String pCheckName,
            @Param("p_rule_id") String pRuleId,
            @Param("p_rule_type") String pRuleType,
            @Param("P_trnas_type") String pTransType
    ) {

    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends DqProUpdateSchemaModel> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends DqProUpdateSchemaModel> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<DqProUpdateSchemaModel> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public DqProUpdateSchemaModel getOne(Long aLong) {
        return null;
    }

    @Override
    public DqProUpdateSchemaModel getById(Long aLong) {
        return null;
    }

    @Override
    public DqProUpdateSchemaModel getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends DqProUpdateSchemaModel> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends DqProUpdateSchemaModel> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends DqProUpdateSchemaModel> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends DqProUpdateSchemaModel> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends DqProUpdateSchemaModel> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends DqProUpdateSchemaModel> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends DqProUpdateSchemaModel, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends DqProUpdateSchemaModel> S save(S entity) {
        return null;
    }

    @Override
    public <S extends DqProUpdateSchemaModel> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<DqProUpdateSchemaModel> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public List<DqProUpdateSchemaModel> findAll() {
        return null;
    }

    @Override
    public List<DqProUpdateSchemaModel> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(DqProUpdateSchemaModel entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends DqProUpdateSchemaModel> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<DqProUpdateSchemaModel> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<DqProUpdateSchemaModel> findAll(Pageable pageable) {
        return null;
    }
}
