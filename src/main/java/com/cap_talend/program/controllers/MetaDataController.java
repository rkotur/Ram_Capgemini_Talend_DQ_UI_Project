package com.cap_talend.program.controllers;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.cap_talend.program.repository.*;
import com.cap_talend.program.Services.DBConnectionRequest;
import com.cap_talend.program.Services.DQ_RulesService;
import com.cap_talend.program.Services.ETLScheduleService;
import com.cap_talend.program.dto.MetaData_AddDTO;
import com.cap_talend.program.dto.MetaData_SearchDTO;
import com.cap_talend.program.repository.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.cap_talend.program.models.MetaDataModel;
import com.cap_talend.program.Services.MetaDataService;

import jakarta.servlet.http.HttpServletResponse;

// DatabaseConnectionCheck


@Controller
@RequestMapping("/metadata")
public class MetaDataController {

    @Autowired(required=true)
    private MetaDataService metadataService;

    @Autowired(required=true)
    private DQ_RulesService dq_rulesservice;

    @Autowired(required=true)
    private ETLScheduleService etlschduleservice;


    private final DQ_RulesRepository dq_rulesrepository;
    private final SchemaRepository schemaRepository;
    private final ETLScheduleRepository etlScheduleRepository;

    private final DBNameDTORepository dbNameDTORepository;


    public MetaDataController(DQ_RulesRepository dqRulesrepository, SchemaRepository schemaRepository, ETLScheduleRepository etlScheduleRepository, DBNameDTORepository dbNameDTORepository) {
        this.dq_rulesrepository = dqRulesrepository;
        this.schemaRepository = schemaRepository;
        this.etlScheduleRepository = etlScheduleRepository;
        this.dbNameDTORepository = dbNameDTORepository;
    }


    //@RequestMapping(value="/getAll", method = RequestMethod.POST)
    @GetMapping("/getAll")
    //public String getAll(Model model, @RequestParam String trans, @ModelAttribute DBConnectionRequest connectionRequest, HttpSession session) {
    public String getAll(Model model,
                         @ModelAttribute MetaData_SearchDTO metadata_searchDTO,
                         @ModelAttribute MetaData_AddDTO metadata_addDTO,
                         @RequestParam String trans,
                         @RequestParam String campaign_name,
                         @ModelAttribute DBConnectionRequest connectionRequest,
                         @RequestParam(defaultValue = "0") int page,
                         HttpSession session) {

        model.addAttribute("metadata_searchDTO", metadata_searchDTO);
        model.addAttribute("metadata_addDTO", metadata_addDTO);

        model.addAttribute("parameter", (trans.equals("1")) ? "Profiling" : "Custom");
        //model.addAttribute("campaign",campaign_name);

        session.setAttribute("trans", trans);
        //session.setAttribute("campaign",campaign_name);


        MetaDataModel metadatamodel = new MetaDataModel();
        model.addAttribute("metadatamodel", metadatamodel);
        //model.addAttribute("metadatamodelDTO", new MetaDataDTO());



        List<String> schemaNames = new ArrayList<>();
        schemaRepository.getSchemas().forEach(e -> schemaNames.add(e.getName()));
        model.addAttribute("schemaNames", schemaNames);

        List<String> tableNames = new ArrayList<>();
        schemaRepository.getTables("-").forEach(e -> tableNames.add(e.getName()));
        model.addAttribute("tableNames", tableNames);

        List<String> columnsNames = new ArrayList<>();
        schemaRepository.getColumns("-", "-").forEach(e -> columnsNames.add(e.getName()));
        model.addAttribute("columnsNames", columnsNames);


        List<String> ruleMetaNames = new ArrayList<>();
        String v_rule_type = trans.equals("1") ? "DQ Profiling" : "DQ Custom";
        schemaRepository.getRules(v_rule_type,"","","").forEach(e -> ruleMetaNames.add(e.getName()));
        model.addAttribute("ruleMetaNames", ruleMetaNames);
        //model.addAttribute("ruleMetaNames", null);


        List<String> campaign1 = new ArrayList<>();
        etlschduleservice.findAll().forEach(e -> campaign1.add(e.getCampaignName()));
        model.addAttribute("campaign", campaign1);



        //List<MetaDataModel> stlist = metadataService.findByCampaignName(campaign_name);
        //model.addAttribute("metadatamodels", stlist);


        Pageable pageable = PageRequest.of(page, 10); // 10 items per page
        ;
        Page<MetaDataModel> metadataPage = metadataService.getMetadata(pageable, campaign_name);
        model.addAttribute("metadataPage", metadataPage);
        model.addAttribute("campaign_name", campaign_name);

        return "metadata/metadatamodels";
    }



    @GetMapping("/addNew")
    public String newMetaDataModel(@ModelAttribute DBConnectionRequest connectionRequest,@RequestParam String trans, Model model) {

        MetaDataModel metadatamodel = new MetaDataModel();
        model.addAttribute("metadatamodel", metadatamodel);


        List<String> schemaNames =  new ArrayList<>();
        schemaRepository.getSchemas().forEach(e-> schemaNames.add(e.getName()));
        model.addAttribute("schemaNames", schemaNames);

        List<String> tableNames =  new ArrayList<>();
        schemaRepository.getTables("-").forEach(e-> tableNames.add(e.getName()));
        model.addAttribute("tableNames", tableNames);


        List<String> columnsNames =  new ArrayList<>();
        schemaRepository.getColumns("-","loading").forEach(e-> columnsNames.add(e.getName()));
        model.addAttribute("columnsNames", columnsNames);

        List<String> ruleMetaNames = new ArrayList<>();
        String v_rule_type = trans.equals("1") ? "DQ Profiling" : "DQ Custom";


        schemaRepository.getRules(v_rule_type,"Test","Test","Test").forEach(e -> ruleMetaNames.add(e.getName()));
        model.addAttribute("ruleMetaNames", ruleMetaNames);
        //model.addAttribute("ruleMetaNames", null);

        return "add-metadatamodel";
    }

    @GetMapping("/getTables")
    public @ResponseBody String getTablesFunc(@RequestParam String SchemaName)
    {
        String json = null;
        List<String> tableNames =  new ArrayList<>();
        schemaRepository.getTables(SchemaName).forEach(e-> tableNames.add(e.getName()));

        try {
            json = new ObjectMapper().writeValueAsString(tableNames);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }

    @GetMapping("/getColumns")
    public @ResponseBody String getColumnsFunc(@RequestParam String SchemaName,@RequestParam String TableName)
    {
        String json = null;
        List<String> ColumnNames =  new ArrayList<>();
        schemaRepository.getColumns(SchemaName,TableName).forEach(e-> ColumnNames.add(e.getName()));

        try {
            json = new ObjectMapper().writeValueAsString(ColumnNames);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }

    @GetMapping("/getRules")
    public @ResponseBody String getRulesCols(@RequestParam String RuleType,@RequestParam String SchemaName,@RequestParam String TableName,@RequestParam String ColumnName)
    {
        String json = null;
        List<String> RuleNames =  new ArrayList<>();
        schemaRepository.getRules(RuleType,SchemaName,TableName,ColumnName).forEach(e-> RuleNames.add(e.getName()));

        try {
            json = new ObjectMapper().writeValueAsString(RuleNames);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }


    @GetMapping("/edit/{id}")
    public String editMetaDataModel(@PathVariable("id") int id, Model model, HttpServletResponse response)
            throws IOException {
        MetaDataModel metadatamodel = metadataService.getMetaDataModel(id);
        metadatamodel.setUpdatedOn(metadatamodel.getUpdatedOn());
        model.addAttribute("metadatamodel", metadatamodel);
        return "edit-metadatamodel";
    }

    @PostMapping("/delete/{id}")
    public String deleteMetaDataModel(@PathVariable("id") int id, HttpSession session,MetaDataModel metadatamodel) {

       String i = (String) session.getAttribute("trans");
       String S_DBConnection_Name = (String)  session.getAttribute("S_DBConnection_Name");



       // ---- DELETE Call SPs -----------
        try {
            List v_ret_value;
            v_ret_value = callspsRepository.get_pro_update_schema(S_DBConnection_Name,i,"DELETE",metadataService.getMetaDataModel(id).getDbschema(),metadataService.getMetaDataModel(id).getDbtable(),metadataService.getMetaDataModel(id).getDbcolumn());

        } catch (Exception e) {
            e.printStackTrace();
        }
       // -- End of Delete call SPs --

        metadataService.delete(id);
        return "redirect:/metadata/getAll?trans="+i+"&campaign_name=All";
    }

    @Autowired
    private CallSPsRepository callspsRepository;
    private JdbcTemplate jdbcTemplate;

    @PostMapping("/saveNew")
    public String insertMetaDataModel(
            @ModelAttribute(value="metadatamodel") MetaDataModel metadatamodel,
            HttpSession session
            //,@RequestParam String trans
    ) throws IOException {

        String i;


        if(metadatamodel.getDbschema().isEmpty() || metadatamodel.getDbschema().equals("")
                ||metadatamodel.getDbtable().isEmpty() || metadatamodel.getDbtable().equals("")
                || metadatamodel.getDbtable().equals("-1")
                ||metadatamodel.getDbcolumn().isEmpty() || metadatamodel.getDbcolumn().equals("")
                || metadatamodel.getDbcolumn().equals("-1")
                ||metadatamodel.getDbcheck().isEmpty() || metadatamodel.getDbcheck().equals("")
        )
        {
             //i = (String) session.getAttribute("Parameter");
            i = (String) session.getAttribute("trans");
        }else{


            metadataService.insert(metadatamodel);
            //i = (String) session.getAttribute("Parameter");
            i = (String) session.getAttribute("trans");
            String S_DBConnection_Name = (String) session.getAttribute("S_DBConnection_Name");


            // ----------- SP Call for Insert Records -----
            try {

                List v_ret_value;

                System.out.println("----------------- Before call SP: --get_pro_update_schema-----------------");
                System.out.println("S_DBConnection_Name : "+S_DBConnection_Name);
                System.out.println("i : "+i.toString());
                System.out.println("Dbschema : "+metadatamodel.getDbschema());
                System.out.println("getDbtable : "+metadatamodel.getDbtable());
                System.out.println("getDbcolumn : "+metadatamodel.getDbcolumn());

                String v_rule_type = i.equals("1") ? "DQ Profiling" : "DQ Custom";

                //v_ret_value = callspsRepository.get_pro_update_schema(S_DBConnection_Name, i, "INSERT", metadatamodel.getDbschema(), metadatamodel.getDbtable(), metadatamodel.getDbcolumn());
                v_ret_value = callspsRepository.get_pro_update_schema(S_DBConnection_Name, i, "INSERT", metadatamodel.getDbschema(), metadatamodel.getDbtable(), metadatamodel.getDbcolumn());
            } catch (Exception e) {
                e.printStackTrace();
            }
            // -------End of SP Call  ----------

        }
        return "redirect:/metadata/getAll?trans=" + i+"&campaign_name="+metadatamodel.getCampaign_name();

    }



//-----------------------------------------------------------------------------------------

    @PostMapping("/search")
    public String searchvalue(
            @ModelAttribute(value="metadatamodel") MetaDataModel metadatamodel,
            @RequestParam(value="campaign_name", defaultValue="") String searchCampaignName,
            @RequestParam(value="page", defaultValue="0") int page,
            //@RequestParam(defaultValue = "0") int page,
            HttpSession session,
            Model model

            //,@RequestParam String trans
    ) throws IOException {

        String i;

        i = (String) session.getAttribute("trans");
        System.out.println("------------------searchCampaignName--------------------------------"+searchCampaignName);

        //model.addAttribute("metadatamodel", new MetaDataModel());
        model.addAttribute("campaign", searchCampaignName);

        Pageable pageable = PageRequest.of(page, 10); // 10 items per page
        Page<MetaDataModel> metadataPage = metadataService.getMetadata(pageable);
        model.addAttribute("metadataPage", metadataPage);


        return "redirect:/metadata/getAll?trans=" + i+"&campaign_name="+searchCampaignName;
    }

//-------------------------------------------------------------------------------------------------------------

    @PostMapping("/update/{id}")
    public String updateMetaDataModel(
            @PathVariable("id") int id,
            @ModelAttribute(value="metadatamodel") MetaDataModel metadatamodel) throws IOException
    {

        metadataService.update(id, metadatamodel);
        return "redirect:/metadatamodels/getAll";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

}