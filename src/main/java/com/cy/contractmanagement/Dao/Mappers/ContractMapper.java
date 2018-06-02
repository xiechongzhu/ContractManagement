package com.cy.contractmanagement.Dao.Mappers;

import com.cy.contractmanagement.Dao.Contract.ContractInfo;
import com.cy.contractmanagement.Dao.Mappers.SqlProvider.ContractProvider;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

@Mapper
public interface ContractMapper {
    @Select("select * from contract_info")
    List<ContractInfo> getAllContracts();

    @SelectProvider(type = ContractProvider.class, method = "findContract")
    List<ContractInfo> findContract(@Param("number") String number, @Param("name") String name,
                                    @Param("status") int status, @Param("classification") int classification,
                                    @Param("leader") String leader, @Param("startDate") String startDate,
                                    @Param("endDate") String endDate, @Param("isDelay") int isDelay);

    @Insert("insert into contract_info(number, name, status, classification, leader," +
            "money, needInvoice, filingTime, cdNumber, requirementTimePlan, requirementTimeReal," +
            "requirementPayMoney, designTimePlan, designTimeReal, designPayMoney, testTimePlan," +
            "testTimeReal, testPayMoney, acceptanceTimePlan, acceptanceTimeReal, acceptancePayMoney," +
            "isDelay) values(#{number}, #{name}, #{status}, #{classification}, #{leader}," +
            "#{money}, #{needInvoice}, #{filingTime}, #{cdNumber}, #{requirementTimePlan}, #{requirementTimeReal}," +
            "#{requirementPayMoney}, #{designTimePlan}, #{designTimeReal}, #{designPayMoney}, #{testTimePlan}," +
            "#{testTimeReal}, #{testPayMoney}, #{acceptanceTimePlan}, #{acceptanceTimeReal}, #{acceptancePayMoney}," +
            "#{isDelay})")
    int insertContract(@Param("number") String number, @Param("name") String name,
                       @Param("status") int status, @Param("classification") int classification,
                       @Param("leader") String leader, @Param("money") float money,
                       @Param("needInvoice") int needInvoice, @Param("filingTime") Date filingTime,
                       @Param("cdNumber") String cdNumber, @Param("requirementTimePlan") Date requirementTimePlan,
                       @Param("requirementTimeReal") Date requirementTimeReal,
                       @Param("requirementPayMoney") float requirementPayMoney,
                       @Param("designTimePlan") Date designTimePlan, @Param("designTimeReal") Date designTimeReal,
                       @Param("designPayMoney") float designPayMoney, @Param("testTimePlan") Date testTimePlan,
                       @Param("testTimeReal") Date testTimeReal, @Param("testPayMoney") float testPayMoney,
                       @Param("acceptanceTimePlan") Date acceptanceTimePlan,
                       @Param("acceptanceTimeReal") Date acceptanceTimeReal,
                       @Param("acceptancePayMoney") float acceptancePayMoney, @Param("isDelay") int isDelay);

    @Select("select * from contract_info where id = #{id}")
    ContractInfo getSingleCOntract(@Param("id") int id);

    @Update("update contract_info set number=#{number}, name=#{name}, status=#{status}, classification=#{classification}," +
            "leader=#{leader}, money=#{money}, needInvoice=#{needInvoice}, filingTime=#{filingTime}, cdNumber=#{cdNumber}," +
            "requirementTimePlan=#{requirementTimePlan}, requirementTimeReal=#{requirementTimeReal}, requirementPayMoney=#{requirementPayMoney}," +
            "designTimePlan=#{designTimePlan}, designTimeReal=#{designTimeReal}, designPayMoney=#{designPayMoney}," +
            "testTimePlan=#{testTimePlan}, testTimeReal=#{testTimeReal}, testPayMoney=#{testPayMoney}," +
            "acceptanceTimePlan=#{acceptanceTimePlan}, acceptanceTimeReal=#{acceptanceTimeReal}, acceptancePayMoney=#{acceptancePayMoney}," +
            "isDelay=#{isDelay} where id=#{id}")
    void modifyContract(@Param("id") int id, @Param("number") String number, @Param("name") String name,
                       @Param("status") int status, @Param("classification") int classification,
                       @Param("leader") String leader, @Param("money") float money,
                       @Param("needInvoice") int needInvoice, @Param("filingTime") Date filingTime,
                       @Param("cdNumber") String cdNumber, @Param("requirementTimePlan") Date requirementTimePlan,
                       @Param("requirementTimeReal") Date requirementTimeReal,
                       @Param("requirementPayMoney") float requirementPayMoney,
                       @Param("designTimePlan") Date designTimePlan, @Param("designTimeReal") Date designTimeReal,
                       @Param("designPayMoney") float designPayMoney, @Param("testTimePlan") Date testTimePlan,
                       @Param("testTimeReal") Date testTimeReal, @Param("testPayMoney") float testPayMoney,
                       @Param("acceptanceTimePlan") Date acceptanceTimePlan,
                       @Param("acceptanceTimeReal") Date acceptanceTimeReal,
                       @Param("acceptancePayMoney") float acceptancePayMoney, @Param("isDelay") int isDelay);
}
