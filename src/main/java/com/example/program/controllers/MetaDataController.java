package com.example.program.controllers;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.program.Services.DBConnectionRequest;
import com.example.program.Services.DQ_RulesService;
import com.example.program.Services.ETLScheduleService;
import com.example.program.models.DQ_RulesModel;
import com.example.program.models.ETLScheduleModel;
import com.example.program.repository.ETLScheduleRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.program.models.MetaDataModel;
import com.example.program.Services.MetaDataService;

import jakarta.servlet.http.HttpServletResponse;
import com.example.program.repository.SchemaRepository;
import com.example.program.repository.DQ_RulesRepository;

import com.example.program.repository.CallSPsRepository;

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



    public MetaDataController(DQ_RulesRepository dqRulesrepository, SchemaRepository schemaRepository, ETLScheduleRepository etlScheduleRepository) {
        this.dq_rulesrepository = dqRulesrepository;
        this.schemaRepository = schemaRepository;
        this.etlScheduleRepository = etlScheduleRepository;
    }

/*
    @GetMapping("/test")
    public String getAll() {
        return "Metadatamodels";
    }
*/


    //@RequestMapping(value="/getAll", method = RequestMethod.POST)
    @GetMapping("/getAll")
    public String getAll(Model model, @RequestParam String trans, @ModelAttribute DBConnectionRequest connectionRequest, HttpSession session) {

        model.addAttribute("parameter",(trans.equals("1"))?"Profiling":"Custom");

        session.setAttribute("Parameter",trans);

        MetaDataModel metadatamodel = new MetaDataModel();
        model.addAttribute("metadatamodel", metadatamodel);

        List<String> schemaNames =  new ArrayList<>();
        schemaRepository.getSchemas().forEach(e-> schemaNames.add(e.getName()));
        model.addAttribute("schemaNames", schemaNames);

        List<String> tableNames =  new ArrayList<>();
        schemaRepository.getTables("spoton").forEach(e-> tableNames.add(e.getName()));
        model.addAttribute("tableNames", tableNames);

        List<String> columnsNames =  new ArrayList<>();
        schemaRepository.getColumns("spoton","loading").forEach(e-> columnsNames.add(e.getName()));
        model.addAttribute("columnsNames", columnsNames);


        List<String> ruleMetaNames =  new ArrayList<>();
        String v_rule_type = trans.equals("1")?"DQ Profiling":"DQ Custom";
        dq_rulesrepository.getRules(v_rule_type).forEach(e-> ruleMetaNames.add(e.getName()));
        model.addAttribute("ruleMetaNames", ruleMetaNames);

        List<MetaDataModel> stlist = metadataService.getAll();
        model.addAttribute("metadatamodels", stlist);

        // dq_rulesservice

        List<DQ_RulesModel> stlist1 = dq_rulesservice.getAll();
        model.addAttribute("dq_rulesmodel", stlist1);


        List<String> campaign1 =  new ArrayList<>();
        etlschduleservice.findAll().forEach(e-> campaign1.add(e.getCampaignName()));
        model.addAttribute("campaign", campaign1);



        return "Metadatamodels";
    }



    @GetMapping("/addNew")
    public String newMetaDataModel(@ModelAttribute DBConnectionRequest connectionRequest, Model model) {
        MetaDataModel metadatamodel = new MetaDataModel();
        model.addAttribute("metadatamodel", metadatamodel);


        //List<String> databaseNames = schemaRepository.findAllschema_name();
        // Add the list to the model
        // model.addAttribute("databaseNames", databaseNames);

        List<String> schemaNames =  new ArrayList<>();
        schemaRepository.getSchemas().forEach(e-> schemaNames.add(e.getName()));
        model.addAttribute("schemaNames", schemaNames);

        List<String> tableNames =  new ArrayList<>();
        schemaRepository.getTables("spoton").forEach(e-> tableNames.add(e.getName()));
        model.addAttribute("tableNames", tableNames);



/*
        List<String> columnsNames =  new ArrayList<>();
        schemaRepository.getColumns("spoton","loading").forEach(e-> columnsNames.add(e.getName()));
        model.addAttribute("columnnames", columnsNames);
*/
        List<String> columnsNames =  new ArrayList<>();
        schemaRepository.getColumns("spoton","loading").forEach(e-> columnsNames.add(e.getName()));
        model.addAttribute("columnsNames", columnsNames);





// etlScheduleRepository
/*
        List<String> campaignNames =  new ArrayList<>();
        etlScheduleRepository.getCampaign().forEach(e-> campaignNames.add(e.getCampaignName()));
        model.addAttribute("campaignNames", campaignNames);
*/





        /*
        List<String> ruleNames = new ArrayList<>();
        schemaRepository.getRules().forEach(e-> ruleNames.add(e.getName()));
        //schemaRepository.getSchemas().forEach(e-> ruleNames.add(e.getName()));
        model.addAttribute("ruleNames", ruleNames);

        System.out.println("--- Ram printinh values ---- ");
        for (String value : ruleNames) {
            System.out.println(value);
        }
*/

/*
       List<String> ruleMetaNames = new ArrayList<>();
        ruleMetaNames.add("Null_Count");
        ruleMetaNames.add("Blank_Count");
        ruleMetaNames.add("Distinct_Count");
        ruleMetaNames.add("Start_Lower_Case");
        ruleMetaNames.add("Start_Upper_Case");
        ruleMetaNames.add("Alpha_Numeric_Chk");
        ruleMetaNames.add("Only_Numeric_Chk");
        ruleMetaNames.add("Email_Pattern_Chk");
        ruleMetaNames.add("Special_Char_Chk");
        ruleMetaNames.add("Min_value");
        ruleMetaNames.add("Max_value");
        ruleMetaNames.add("Avg");
        model.addAttribute("ruleMetaNames", ruleMetaNames);
*/

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

       String i = (String) session.getAttribute("Parameter");
       String S_DBConnection_Name = (String)  session.getAttribute("S_DBConnection_Name");



       // ---- DELETE Call SPs -----------
        try {
            List v_ret_value;
            v_ret_value = callspsRepository.get_pro_update_schema(S_DBConnection_Name,i,"DELETE",metadataService.getMetaDataModel(id).getDbschema(),metadataService.getMetaDataModel(id).getDbtable(),metadataService.getMetaDataModel(id).getDbcolumn());
            System.out.println("-----------"+i);
        } catch (Exception e) {
            e.printStackTrace();
        }
       // -- End of Delete call SPs --

        metadataService.delete(id);
        return "redirect:/metadata/getAll?trans="+i;
    }

    @Autowired
    private CallSPsRepository callspsRepository;
    private JdbcTemplate jdbcTemplate;

    @PostMapping("/saveNew")
    public String insertMetaDataModel(
            @ModelAttribute(value="metadatamodel") MetaDataModel metadatamodel,  HttpSession session
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
             i = (String) session.getAttribute("Parameter");
        }else{


            metadataService.insert(metadatamodel);
             i = (String) session.getAttribute("Parameter");
            String S_DBConnection_Name = (String) session.getAttribute("S_DBConnection_Name");


            // ----------- SP Call for Insert Records -----
            try {

                List v_ret_value;
                v_ret_value = callspsRepository.get_pro_update_schema(S_DBConnection_Name, i, "INSERT", metadatamodel.getDbschema(), metadatamodel.getDbtable(), metadatamodel.getDbcolumn());
            } catch (Exception e) {
                e.printStackTrace();
            }
            // -------End of SP Call  ----------

        }
        return "redirect:/metadata/getAll?trans=" + i;

    }



    @PostMapping("/update/{id}")
    public String updateMetaDataModel(
            @PathVariable("id") int id,
            @ModelAttribute(value="metadatamodel") MetaDataModel metadatamodel) throws IOException
    {

        metadataService.update(id, metadatamodel);
        return "redirect:/metadatamodels/getAll";
    }


}